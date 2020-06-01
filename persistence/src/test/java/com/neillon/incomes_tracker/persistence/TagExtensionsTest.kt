package com.neillon.incomes_tracker.persistence

import com.neillon.incomes_tracker.domain.Tag
import com.neillon.incomes_tracker.persistence.entities.TagEntity
import com.neillon.incomes_tracker.persistence.extensions.toDomain
import com.neillon.incomes_tracker.persistence.extensions.toEntity
import org.junit.Test

class TagExtensionsTest {

    @Test
    fun tagExtensionTest_MustConvertEntityToDomain() {
        val mockEntity = TagEntity(
            id = 1L,
            description = "Test tag",
            incomeId = 1L
        )

        val resultDomain = mockEntity.toDomain()

        assert(resultDomain.id == mockEntity.id)
        assert(resultDomain.description == mockEntity.description)
        assert(resultDomain.incomeId == mockEntity.incomeId)
    }

    @Test
    fun tagExtensionTest_MustConvertDomainToEntity() {
        val mockDomain = Tag(
            id = 1L,
            description = "Test tag",
            incomeId = 1L
        )

        val resultEntity = mockDomain.toEntity()

        assert(resultEntity.id == mockDomain.id)
        assert(resultEntity.description == mockDomain.description)
        assert(resultEntity.incomeId == mockDomain.incomeId)
    }


}