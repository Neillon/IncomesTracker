package com.neillon.incomes_tracker.persistence.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.neillon.incomes_tracker.persistence.entities.IncomeEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalDate

@RunWith(AndroidJUnit4::class)
class IncomeDaoTest : BaseDaoTest() {

    @Test
    @ExperimentalCoroutinesApi
    fun incomeDao_insert_mustReturnIncome() = runBlocking {
        val resultData = mIncomeDatabase.incomeDao().insertAndReturn(incomeMock)
        incomeMock.id = resultData.income.id

        assertThat(resultData, not(nullValue()))
        assertThat(resultData.income, equalTo(incomeMock))
        assertThat(resultData.tags, `is`(emptyList()))
    }

    @Test
    @ExperimentalCoroutinesApi
    fun incomeDao_update_mustReturnIncome() = runBlocking {
        val originalIncome = mIncomeDatabase.incomeDao().insertAndReturn(incomeMock)

        val incomeToUpdate = incomeMock.apply {
            id = originalIncome.income.id
            description = "Updated description for test"
        }

        val updatedIncome = mIncomeDatabase.incomeDao().updateAndReturn(incomeToUpdate)

        assertThat(updatedIncome.income, not(nullValue()))
        assertThat(updatedIncome.income, equalTo(incomeToUpdate))
        assertThat(updatedIncome.tags, `is`(emptyList()))
    }

    companion object {
        private val incomeMock = IncomeEntity(
            description = "Income Test",
            value = 20.0,
            date = LocalDate.now()
        )
    }
}