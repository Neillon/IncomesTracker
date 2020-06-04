package com.neillon.incomes_tracker.persistence.dao

import com.neillon.incomes_tracker.persistence.entities.TagEntity
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class TagDaoTest : BaseDaoTest() {

    @Test
    fun tagDao_insert_mustReturnTag() = runBlocking {
        val resultData = mIncomeDatabase.tagDao().insertAndReturn(tagMock)
        tagMock.id = resultData.first().id

        assertThat(resultData, not(nullValue()))
        assertThat(resultData.first(), equalTo(tagMock))
    }

    @Test
    fun tagDao_update_mustReturnTag() = runBlocking {
        val originalTag = mIncomeDatabase.tagDao().getById(1L)

        val tagToUpdate = originalTag.apply {
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