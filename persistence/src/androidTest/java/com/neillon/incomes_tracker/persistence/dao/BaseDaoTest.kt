package com.neillon.incomes_tracker.persistence.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.neillon.incomes_tracker.persistence.databases.IncomeDatabase
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import java.time.LocalDate
import java.util.concurrent.Executors

@RunWith(AndroidJUnit4::class)
abstract class BaseDaoTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var mIncomeDatabase: IncomeDatabase

    @Before
    fun setup() {
        mIncomeDatabase = Room
            .inMemoryDatabaseBuilder(
                InstrumentationRegistry.getInstrumentation().context,
                IncomeDatabase::class.java
            )
            .setTransactionExecutor(Executors.newSingleThreadExecutor())
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    db.execSQL("insert into income(income_id, income_description, income_value, income_date) values(1, 'Test Income', 20.0, '${LocalDate.now()}')")
                    db.execSQL("insert into tag(tag_id, tag_description, tag_income_id) values(1, 'Test Tag', 1)")
                }
            })
            .build()
    }

    @After
    fun finish() {
        mIncomeDatabase.close()
    }
}