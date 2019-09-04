package com.github.aliakseikaraliou.numkotlin.matrix.test.impl.base.immutable

import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.immutable.*
import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.mutable.mutableMatrixOf
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MatrixUtils {
    @Test
    fun map() {
        val input = matrixOf(
            arrayOf(1, 2, 3),
            arrayOf(2, 3, 100)
        )

        val actual = input.map { it * it }
        val expected = matrixOf(
            arrayOf(1, 4, 9),
            arrayOf(4, 9, 10000)
        )

        assertEquals(expected, actual)
    }

    @Test
    fun mapIndexed() {
        val input = matrixOf(
            arrayOf(1, 2, 3),
            arrayOf(2, 3, 100)
        )

        val actual = input.mapIndexed { i, j, value -> (i + j) * value }
        val expected = matrixOf(
            arrayOf(0, 2, 6),
            arrayOf(2, 6, 300)
        )

        assertEquals(expected, actual)
    }

    @Test
    fun forEach() {
        val input = matrixOf(
            arrayOf(1, 2, 3),
            arrayOf(2, 3, 100)
        )

        var actual = 0
        input.forEach { actual += it }

        val expected = 111

        assertEquals(expected, actual)
    }

    @Test
    fun forEachIndexed() {
        val input = matrixOf(
            arrayOf(1, 2, 3),
            arrayOf(2, 3, 100)
        )

        var actual = 0
        input.forEachIndexed { i, j, value -> actual += i * j * value }

        val expected = 203

        assertEquals(expected, actual)
    }

    @Test
    fun toList() {
        val input = matrixOf(
            arrayOf(1, 2, 3),
            arrayOf(2, 3, 100)
        )

        val actual = input.toList()
        val expected = listOf(1, 2, 3, 2, 3, 100)

        assertEquals(expected, actual)
    }

    @Test
    fun transpose() {
        val input = matrixOf(
            arrayOf(1, 2, 3),
            arrayOf(2, 3, 100)
        )

        val actual = input.transpose()
        val expected = matrixOf(
            arrayOf(1, 2),
            arrayOf(2, 3),
            arrayOf(3, 100)
        )

        assertEquals(expected, actual)
    }

    @Test
    fun mutableMatrix() {
        val input = matrixOf(
            arrayOf(1, 2, 3),
            arrayOf(2, 3, 100)
        )

        val actual = input.toMutableMatrix()
        val expected = mutableMatrixOf(
            arrayOf(1, 2, 3),
            arrayOf(2, 3, 100)
        )

        assertEquals(expected, actual)
    }
}