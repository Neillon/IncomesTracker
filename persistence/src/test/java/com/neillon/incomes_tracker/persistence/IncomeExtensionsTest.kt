package com.neillon.incomes_tracker.persistence

import com.neillon.incomes_tracker.domain.Income
import com.neillon.incomes_tracker.persistence.entities.IncomeEntity
import com.neillon.incomes_tracker.persistence.entities.TagEntity
import com.neillon.incomes_tracker.persistence.entities.models.IncomeWithTags
import com.neillon.incomes_tracker.persistence.extensions.toDomain
import com.neillon.incomes_tracker.persistence.extensions.toEntity
import org.junit.Assert
import org.junit.Test
import java.time.LocalDate

class IncomeExtensionsTest {

    @Test
    fun incomeExtension_MustConvertEntityToDomain() {
        val mockEntity = IncomeEntity(
            id = 1L,
            description = "Test Income",
            date = LocalDate.now(),
            value = 12.00
        )

        val result = mockEntity.toDomain()

        assert(result.id == mockEntity.id)
        assert(result.description == mockEntity.description)
        assert(result.date == mockEntity.date)
        assert(result.value == mockEntity.value)
    }

    @Test
    fun incomeExtension_MusConvertDomainToEntity() {
        val mockDomain = Income(
            id = 1L,
            description = "Test Income",
            date = LocalDate.now(),
            value = 12.00,
            tags = emptyList()
        )

        val resultEntity = mockDomain.toEntity()

        assert(resultEntity.id == mockDomain.id)
        assert(resultEntity.description == mockDomain.description)
        assert(resultEntity.date == mockDomain.date)
        assert(resultEntity.value == mockDomain.value)
    }

    @Test
    fun incomeExtension_MusConvertFromIncomeWithTagsToDomain() {
        val mockRelation = IncomeWithTags(
            income = IncomeEntity(
                id = 1L,
                description = "Test Income",
                date = LocalDate.now(),
                value = 12.00),
            tags = listOf(
                TagEntity(
                    id = 1L,
                    description = "First Test Tag",
                    incomeId = 1L
                ),
                TagEntity(
                    id = 2L,
                    description = "Second Test Tag",
                    incomeId = 1L
                )
            )
        )

        val resultDomain = mockRelation.toDomain()

        Assert.assertNotNull(resultDomain)
        Assert.assertNotNull(resultDomain.tags)
        assert(resultDomain.tags.size == mockRelation.tags.size)

        assert(resultDomain.id == mockRelation.income.id)
        assert(resultDomain.description == mockRelation.income.description)
        assert(resultDomain.date == mockRelation.income.date)
        assert(resultDomain.value == mockRelation.income.value)

        for (i in mockRelation.tags.indices) {
            assert(resultDomain.tags[i].id == mockRelation.tags[i].id)
            assert(resultDomain.tags[i].description == mockRelation.tags[i].description)
            assert(resultDomain.tags[i].incomeId == mockRelation.tags[i].incomeId)
        }
    }

}