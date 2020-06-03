package com.neillon.incomes_tracker.persistence.dao

import com.neillon.incomes_tracker.persistence.entities.IncomeEntity
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import java.time.LocalDate

class IncomeDaoTest : BaseDaoTest() {

    @Test
    fun incomeDao_insert_mustReturnIncome() = runBlocking {
        val resultData = mIncomeDatabase.incomeDao().insertAndReturn(incomeMock)
        incomeMock.id = resultData.income.id

        assertThat(resultData, not(nullValue()))
        assertThat(resultData.income, equalTo(incomeMock))
        assertThat(resultData.tags, `is`(emptyList()))
    }

    @Test
    fun incomeDao_update_mustReturnIncome() = runBlocking {
        val originalIncome = mIncomeDatabase.incomeDao().getById(1L)

        val incomeToUpdate = originalIncome.income.apply {
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