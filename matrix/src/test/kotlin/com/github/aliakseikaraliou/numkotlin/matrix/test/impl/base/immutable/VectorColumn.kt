package com.github.aliakseikaraliou.numkotlin.matrix.test.impl.base.immutable

import com.github.aliakseikaraliou.numkotlin.matrix.exceptions.MatrixEmptyException
import com.github.aliakseikaraliou.numkotlin.matrix.exceptions.MatrixIndexOutOfBoundsException
import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.immutable.columnOf
import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.immutable.rawOf
import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.immutable.utils.*
import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.mutable.mutableColumnOf
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class VectorColumn {
    @Nested
    inner class Base {
        @Test
        fun get1() {
            val raw = columnOf(listOf(1, 2, 3, 4))

            assertEquals(1, raw[0])
            assertEquals(2, raw[1])
            assertEquals(3, raw[2])
            assertEquals(4, raw[3])

            assertThrows(MatrixIndexOutOfBoundsException::class.java) { raw[4] }
        }

        @Test
        fun get2() {
            val raw = columnOf(listOf(1, 2, 3, 4))

            assertEquals(1, raw[0, 0])
            assertEquals(2, raw[1, 0])
            assertEquals(3, raw[2, 0])
            assertEquals(4, raw[3, 0])

            assertThrows(MatrixIndexOutOfBoundsException::class.java) { raw[0, 1] }
            assertThrows(MatrixIndexOutOfBoundsException::class.java) { raw[4, 0] }
        }
    }

    @Nested
    inner class Create {
        @Test
        fun list() {
            val raw = columnOf(listOf(1, 2, 3, 4))

            assertEquals(1, raw[0])
            assertEquals(2, raw[1])
            assertEquals(3, raw[2])
            assertEquals(4, raw[3])

            assertThrows(MatrixEmptyException::class.java) {
                columnOf(
                    emptyList<Int>()
                )
            }
        }

        @Test
        fun arrays() {
            val actual = columnOf(1, 2, 3, 4)
            val expected = columnOf(listOf(1, 2, 3, 4))

            assertEquals(expected, actual)
        }

        @Test
        fun function() {
            val actual = columnOf(3) { it * it }
            val expected = columnOf(0, 1, 4)

            assertEquals(expected, actual)
        }
    }

    @Nested
    inner class Utils {
        @Test
        fun map() {
            val input = columnOf(1, 2, 3, 4)

            val actual = input.map { it * it * it }
            val expected = columnOf(1, 8, 27, 64)

            assertEquals(expected, actual)
        }

        @Test
        fun mapIndexed() {
            val input = columnOf(1, 2, 3, 4)

            val actual = input.mapIndexed { i, value -> i * value }
            val expected = columnOf(0, 2, 6, 12)

            assertEquals(expected, actual)
        }

        @Test
        fun forEachIndexed() {
            val input = columnOf(1, 2, 3, 4)

            var actual = 0
            input.forEachIndexed { it, value -> actual += it * value }

            val expected = 20

            assertEquals(expected, actual)
        }

        @Test
        fun transpose() {
            val input = columnOf(1, 2, 3, 4)

            val actual = input.transpose()
            val expected = rawOf(1, 2, 3, 4)

            assertEquals(expected, actual)
        }

        @Test
        fun toList() {
            val input = columnOf(1, 2, 3, 4)

            val actual = input.toList()
            val expected = listOf(1, 2, 3, 4)

            assertEquals(expected, actual)
        }


        @Test
        fun toMutableVectorColumn() {
            val input = columnOf(1, 2, 3, 4)

            val actual = input.toMutable()
            val expected = mutableColumnOf(1, 2, 3, 4)

            assertEquals(expected, actual)
        }
    }

    @Nested
    inner class Merge {
        @Test
        fun up() {
            val column1 = columnOf(1, 2, 3)

            val column2 = columnOf(111, 201, 30)

            val actual = column1 up column2
            val expected = columnOf(
                111, 201, 30, 1, 2, 3
            )

            assertEquals(expected, actual)
        }

        @Test
        fun down() {
            val column1 = columnOf(1, 2, 3)

            val column2 = columnOf(111, 201, 30)

            val actual = column1 down column2
            val expected = columnOf(
                1, 2, 3, 111, 201, 30
            )

            assertEquals(expected, actual)
        }
    }
}