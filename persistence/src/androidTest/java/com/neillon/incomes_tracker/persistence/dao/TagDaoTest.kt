package com.neillon.incomes_tracker.persistence.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.neillon.incomes_tracker.persistence.entities.TagEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TagDaoTest : BaseDaoTest() {

    @Test
    @ExperimentalCoroutinesApi
    fun tagDao_insert_mustReturnIncome() = runBlocking {
        val resultData = mIncomeDatabase.tagDao().insertAndReturn(tagMock)
        tagMock.id = resultData.id

        assertThat(resultData, not(nullValue()))
        assertThat(resultData, equalTo(tagMock))
    }

    @Test
    @ExperimentalCoroutinesApi
    fun tagDao_update_mustReturnIncome() = runBlocking {
        val originalIncome = mIncomeDatabase.tagDao().insertAndReturn(tagMock)

        val tagToUpdate = tagMock.apply {
            id = originalIncome.id
            description = "Updated description for test"
        }

        val updatedTag = mIncomeDatabase.tagDao().updateAndReturn(tagToUpdate)

        assertThat(updatedTag, not(nullValue()))
        assertThat(updatedTag, equalTo(tagToUpdate))
    }

    companion object {
        private val tagMock = TagEntity(description = "Test Tag", incomeId = 1L)
    }
}