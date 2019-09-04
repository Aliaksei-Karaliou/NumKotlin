package com.github.aliakseikaraliou.numkotlin.matrix.test.impl.base.mutable

import com.github.aliakseikaraliou.numkotlin.matrix.exceptions.MatrixIndexOutOfBoundsException
import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.mutable.mutableRawOf
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class MutableVectorRaw {

    @Nested
    inner class Base {
        @Test
        fun get1() {
            val raw = mutableRawOf(mutableListOf(1, 2, 3, 4))

            assertEquals(1, raw[0])
            assertEquals(2, raw[1])
            assertEquals(3, raw[2])
            assertEquals(4, raw[3])

            assertThrows(MatrixIndexOutOfBoundsException::class.java) { raw[4] }
        }

        @Test
        fun get2() {
            val raw = mutableRawOf(mutableListOf(1, 2, 3, 4))

            assertEquals(1, raw[0, 0])
            assertEquals(2, raw[0, 1])
            assertEquals(3, raw[0, 2])
            assertEquals(4, raw[0, 3])

            assertThrows(MatrixIndexOutOfBoundsException::class.java) { raw[1, 0] }
            assertThrows(MatrixIndexOutOfBoundsException::class.java) { raw[0, 4] }
        }

        @Test
        fun set1() {
            val actual = mutableRawOf(mutableListOf(1, 2, 3, 4))
            actual[0] = 100500

            val expected = mutableRawOf(100500, 2, 3, 4)

            assertEquals(expected, actual)
            assertThrows(MatrixIndexOutOfBoundsException::class.java) { actual[4] = 100 }
        }

        @Test
        fun set2() {
            val actual = mutableRawOf(mutableListOf(1, 2, 3, 4))
            actual[0, 0] = 100500

            val expected = mutableRawOf(100500, 2, 3, 4)

            assertEquals(expected, actual)
            assertThrows(MatrixIndexOutOfBoundsException::class.java) { actual[1, 1] = 100 }
        }
    }

    @Nested
    inner class Create {
        @Test
        fun list() {
            val actual = mutableRawOf(listOf(0, 10, 2, 3))

            assertEquals(0, actual[0])
            assertEquals(10, actual[1])
            assertEquals(2, actual[2])
            assertEquals(3, actual[3])
        }

        @Test
        fun array() {
            val actual = mutableRawOf(1, 2, 3)
            val expected = mutableRawOf(listOf(1, 2, 3))

            assertEquals(expected, actual)
        }

        @Test
        fun function() {
            val actual = mutableRawOf(3) { it * it }
            val expected = mutableRawOf(0, 1, 4)

            assertEquals(expected, actual)
        }
    }
}