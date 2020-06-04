package com.neillon.incomes_tracker.usecase.incomes

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.neillon.incomes_tracker.domain.Income
import com.neillon.incomes_tracker.domain.Tag
import com.neillon.incomes_tracker.domain.contracts.IIncomeRepository
import com.neillon.incomes_tracker.domain.contracts.ITagRepository
import com.neillon.incomes_tracker.usecase.TestCoroutineRule
import io.mockk.coEvery
import io.mockk.mockk
import org.hamcrest.CoreMatchers.not
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalDate

@RunWith(AndroidJUnit4::class)
class SaveIncomeUseCaseTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private val _income =
        Income(description = "", value = 20.0, date = LocalDate.now(), tags = emptyList())
    private val _tags = listOf(
        Tag(1L, "Test Tag", 1L),
        Tag(2L, "Mock Test", 1L)
    )

    @Test
    fun saveIncomeUseCase_invoke_shouldSaveIncomeAndReturnLivedata() =
        testCoroutineRule.runBlockingTest {

            val mockIncomeRepository = mockk<IIncomeRepository>(relaxed = true)
            coEvery { mockIncomeRepository.insert(any()) } returns
                    _income.apply { id = 1L }

            val mockTagRepository = mockk<ITagRepository>(relaxed = true)
            coEvery { mockTagRepository.insert(tags = any()) } returns
                    _tags.mapIndexed { index: Int, tag: Tag ->
                        tag.id = (index + 1).toLong()
                        tag
                    }

            val sut = SaveIncomeUseCase(
                incomeRepository = mockIncomeRepository,
                tagRepository = mockTagRepository
            )

            val result = sut.invoke(SaveIncomeUseCase.Params(_income))

            assertThat(result.value, not(nullValue()))
        }
}