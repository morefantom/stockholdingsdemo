package com.prathamesh.stockholdingsdemo.domain

import com.prathamesh.stockholdingsdemo.data.remote.model.HoldingDataModel
import com.prathamesh.stockholdingsdemo.domain.mapper.HoldingDataToDomainMapperImpl
import com.prathamesh.stockholdingsdemo.domain.model.HoldingDomainModel
import org.junit.Assert.assertEquals
import org.junit.Test

class HoldingDataToDomainMapperImplTest {

    private val mapper = HoldingDataToDomainMapperImpl()

    @Test
    fun `map should correctly convert HoldingDataModel to HoldingDomainModel`() {
        // Arrange
        val input = HoldingDataModel(
            symbol = "TCS",
            quantity = 10.0,
            avgPrice = 3000.0,
            close = 3050.0,
            ltp = 3100.0
        )

        val expected = HoldingDomainModel(
            symbol = "TCS",
            quantity = 10.0,
            avgPrice = 3000.0,
            close = 3050.0,
            ltp = 3100.0,
            pnl = (3100.0 * 10) - (3000.0 * 10) // 1000.0
        )

        // Act
        val actual = mapper.map(input)

        // Assert
        assertEquals(expected, actual)
    }
}