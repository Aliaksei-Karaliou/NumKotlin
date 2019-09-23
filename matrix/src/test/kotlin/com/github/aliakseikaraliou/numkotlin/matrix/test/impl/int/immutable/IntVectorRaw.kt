package com.github.aliakseikaraliou.numkotlin.matrix.test.impl.int.immutable

import com.github.aliakseikaraliou.numkotlin.matrix.exceptions.MatrixIndexOutOfBoundsException
import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.immutable.utils.mutable
import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.mutable.mutableRawOf
import com.github.aliakseikaraliou.numkotlin.matrix.impl.int.immutable.IntVectorRawImpl
import com.github.aliakseikaraliou.numkotlin.matrix.impl.int.immutable.intColumnOf
import com.github.aliakseikaraliou.numkotlin.matrix.impl.int.immutable.intRawOf
import com.github.aliakseikaraliou.numkotlin.matrix.impl.int.immutable.utils.left
import com.github.aliakseikaraliou.numkotlin.matrix.impl.int.immutable.utils.transpose
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class IntVectorRaw {

    @Nested
    inner class Base {
        @Test
        fun get1() {
            val raw = intRawOf(listOf(1, 2, 3, 4))

            assertEquals(1, raw[0])
            assertEquals(2, raw[1])
            assertEquals(3, raw[2])
            assertEquals(4, raw[3])

            assertThrows(MatrixIndexOutOfBoundsException::class.java) { raw[4] }
        }

        @Test
        fun get2() {
            val raw = intRawOf(listOf(1, 2, 3, 4))

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
            val actual = intRawOf(listOf(0, 10, 2, 3))

            assertEquals(0, actual[0])
            assertEquals(10, actual[1])
            assertEquals(2, actual[2])
            assertEquals(3, actual[3])
        }

        @Test
        fun array() {
            val actual = intRawOf(1, 2, 3)
            val expected = intRawOf(listOf(1, 2, 3))

            assertEquals(expected, actual)
        }

        @Test
        fun function() {
            val actual = intRawOf(3) { it * it }
            val expected = intRawOf(0, 1, 4)

            assertEquals(expected, actual)
        }
    }

    @Nested
    inner class Utils {

        @Test
        fun transpose() {
            val input: IntVectorRawImpl = intRawOf(1, 2, 3, 4)

            val actual = input.transpose()
            val expected = intColumnOf(1, 2, 3, 4)

            assertEquals(expected, actual)
        }

        @Test
        fun toMutableVectorRaw() {
            val input = intRawOf(1, 2, 3, 4)

            val actual = input.mutable()
            val expected = mutableRawOf(1, 2, 3, 4)

            assertEquals(expected, actual)
        }
    }

    @Nested
    inner class Merge {
        @Test
        fun left() {
            val raw1 = intRawOf(1, 2, 3)

            val raw2 = intRawOf(111, 201, 30)

            val actual = raw1.left(raw2)
            val expected = intRawOf(111, 201, 30, 1, 2, 3)

            assertEquals(expected, actual)
        }

        @Test
        fun right() {
//            val raw1 = intRawOf(
//                1, 2, 3
//            )
//
//            val raw2 = intRawOf(
//                111, 201, 30
//            )
//
//            val actual = raw1 right raw2
//            val expected = rawOf(
//                1, 2, 3, 111, 201, 30
//            )
//
//            assertEquals(expected, actual)
        }
    }
}