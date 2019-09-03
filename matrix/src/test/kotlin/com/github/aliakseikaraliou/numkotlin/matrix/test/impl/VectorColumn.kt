package com.github.aliakseikaraliou.numkotlin.matrix.test.impl

import com.github.aliakseikaraliou.numkotlin.matrix.exceptions.MatrixEmptyException
import com.github.aliakseikaraliou.numkotlin.matrix.exceptions.MatrixIndexOutOfBoundsException
import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.columnOf
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

        @Test
        fun columns() {
            val column = columnOf(1, 2, 3, 4)

            val actual = column.columns
            val expected = listOf(column)

            assertEquals(expected, actual)
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

            assertThrows(MatrixEmptyException::class.java) { columnOf(emptyList<Int>()) }
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
}