package com.github.aliakseikaraliou.numkotlin.matrix.test.impl.int.immutable

import com.github.aliakseikaraliou.numkotlin.matrix.exceptions.MatrixIndexOutOfBoundsException
import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.immutable.columnOf
import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.immutable.rawOf
import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.immutable.utils.*
import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.mutable.mutableRawOf
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@Disabled
internal class VectorRaw {

    @Nested
    inner class Base {
        @Test
        fun get1() {
            val raw = rawOf(listOf(1, 2, 3, 4))

            assertEquals(1, raw[0])
            assertEquals(2, raw[1])
            assertEquals(3, raw[2])
            assertEquals(4, raw[3])

            assertThrows(MatrixIndexOutOfBoundsException::class.java) { raw[4] }
        }

        @Test
        fun get2() {
            val raw = rawOf(listOf(1, 2, 3, 4))

            assertEquals(1, raw[0, 0])
            assertEquals(2, raw[0, 1])
            assertEquals(3, raw[0, 2])
            assertEquals(4, raw[0, 3])

            assertThrows(MatrixIndexOutOfBoundsException::class.java) { raw[1, 0] }
            assertThrows(MatrixIndexOutOfBoundsException::class.java) { raw[0, 4] }
        }
    }

    @Nested
    inner class Create {
        @Test
        fun list() {
            val actual = rawOf(listOf(0, 10, 2, 3))

            assertEquals(0, actual[0])
            assertEquals(10, actual[1])
            assertEquals(2, actual[2])
            assertEquals(3, actual[3])
        }

        @Test
        fun array() {
            val actual = rawOf(1, 2, 3)
            val expected = rawOf(listOf(1, 2, 3))

            assertEquals(expected, actual)
        }

        @Test
        fun function() {
            val actual = rawOf(3) { it * it }
            val expected = rawOf(0, 1, 4)

            assertEquals(expected, actual)
        }
    }

    @Nested
    inner class Utils {
        @Test
        fun map() {
            val input = rawOf(1, 2, 3, 4)

            val actual = input.map { it * it * it }
            val expected = rawOf(1, 8, 27, 64)

            assertEquals(expected, actual)
        }

        @Test
        fun mapIndexed() {
            val input = rawOf(1, 2, 3, 4)

            val actual = input.mapIndexed { i, value -> i * value }
            val expected = rawOf(0, 2, 6, 12)

            assertEquals(expected, actual)
        }

        @Test
        fun forEachIndexed() {
            val input = rawOf(1, 2, 3, 4)

            var actual = 0
            input.forEachIndexed { it, value -> actual += it * value }

            val expected = 20

            assertEquals(expected, actual)
        }

        @Test
        fun toList() {
            val input = rawOf(1, 2, 3, 4)

            val actual = input.toList()
            val expected = listOf(1, 2, 3, 4)

            assertEquals(expected, actual)
        }

        @Test
        fun transpose() {
            val input = rawOf(1, 2, 3, 4)

            val actual = input.transpose()
            val expected = columnOf(1, 2, 3, 4)

            assertEquals(expected, actual)
        }

        @Test
        fun toMutableVectorRaw() {
            val input = rawOf(1, 2, 3, 4)

            val actual = input.toMutableVectorRaw()
            val expected = mutableRawOf(1, 2, 3, 4)

            assertEquals(expected, actual)
        }
    }

    @Nested
    inner class Merge {
        @Test
        fun left() {
            val raw1 = rawOf(
                1, 2, 3
            )

            val raw2 = rawOf(
                111, 201, 30
            )

            val actual = raw1 left raw2
            val expected = rawOf(
                111, 201, 30, 1, 2, 3
            )

            assertEquals(expected, actual)
        }

        @Test
        fun right() {
            val raw1 = rawOf(
                1, 2, 3
            )

            val raw2 = rawOf(
                111, 201, 30
            )

            val actual = raw1 right raw2
            val expected = rawOf(
                1, 2, 3, 111, 201, 30
            )

            assertEquals(expected, actual)
        }
    }
}