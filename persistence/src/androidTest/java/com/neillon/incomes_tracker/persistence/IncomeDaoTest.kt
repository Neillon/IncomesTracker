package com.neillon.incomes_tracker.persistence

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.neillon.incomes_tracker.persistence.databases.IncomeDatabase
import com.neillon.incomes_tracker.persistence.entities.IncomeEntity
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.not
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalDate

@RunWith(AndroidJUnit4::class)
class IncomeDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var mIncomeDatabase: IncomeDatabase
    private val incomeMock = IncomeEntity(
        description = "Income Test",
        value = 20.0,
        date = LocalDate.now()
    )

    @Before
    fun setup() {
        mIncomeDatabase = Room
            .inMemoryDatabaseBuilder(
                InstrumentationRegistry.getInstrumentation().context,
                IncomeDatabase::class.java
            )
            .build()
    }

    @After
    fun finish() {
        mIncomeDatabase.close()
    }

    @Test
    fun incomeDao_insert_mustReturnIncome() = runBlocking {
        val resultData = mIncomeDatabase.incomeDao().insertAndReturn(incomeMock)
        assertThat(resultData, not(nullValue()))
    }
}