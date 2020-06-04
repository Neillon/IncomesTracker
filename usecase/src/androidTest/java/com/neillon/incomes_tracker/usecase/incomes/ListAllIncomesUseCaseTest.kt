package com.neillon.incomes_tracker.usecase.incomes

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.neillon.incomes_tracker.domain.Income
import com.neillon.incomes_tracker.domain.contracts.IIncomeRepository
import com.neillon.incomes_tracker.usecase.TestCoroutineRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.AdditionalMatchers.not
import java.time.LocalDate

@RunWith(AndroidJUnit4::class)
class ListAllIncomesUseCaseTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private val _incomes = listOf(
        Income(id = 1L, description = "", value = 20.0, date = LocalDate.now(), tags = emptyList()),
        Income(id = 2L, description = "", value = 20.0, date = LocalDate.now(), tags = emptyList())
    )

    @Test
    fun listAllIncomesUseCase_invoke_mustReturnListOfIncomes() =
        testCoroutineRule.runBlockingTest {

            val mockIncomeRepository = mockk<IIncomeRepository>()
            coEvery { mockIncomeRepository.listAll() } returns flow {
                emit(_incomes)
            }

            val sut = ListAllIncomesUseCase(
                coroutineContext = Dispatchers.IO,
                incomeRepository = mockIncomeRepository
            )

            val result = sut.invoke()
            result.collect { incomes ->
                assertThat(incomes, `is`(_incomes))
            }
        }
}