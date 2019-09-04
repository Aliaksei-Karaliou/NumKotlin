package com.github.aliakseikaraliou.numkotlin.matrix.test.impl.base.mutable

import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.mutable.map
import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.mutable.mapIndexed
import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.mutable.mutableMatrixOf
import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.mutable.transpose
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MutableMatrixUtils {
    @Test
    fun map() {
        val input = mutableMatrixOf(
            arrayOf(1, 2, 3),
            arrayOf(2, 3, 100)
        )

        val actual = input.map { it * it }
        val expected = mutableMatrixOf(
            arrayOf(1, 4, 9),
            arrayOf(4, 9, 10000)
        )

        assertEquals(expected, actual)
    }

    @Test
    fun mapIndexed() {
        val input = mutableMatrixOf(
            arrayOf(1, 2, 3),
            arrayOf(2, 3, 100)
        )

        val actual = input.mapIndexed { i, j, value -> (i + j) * value }
        val expected = mutableMatrixOf(
            arrayOf(0, 2, 6),
            arrayOf(2, 6, 300)
        )

        assertEquals(expected, actual)
    }

    @Test
    fun transpose() {
        val input = mutableMatrixOf(
            arrayOf(1, 2, 3),
            arrayOf(2, 3, 100)
        )

        val actual = input.transpose()
        val expected = mutableMatrixOf(
            arrayOf(1, 2),
            arrayOf(2, 3),
            arrayOf(3, 100)
        )

        assertEquals(expected, actual)
    }
}