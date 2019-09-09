package com.github.aliakseikaraliou.numkotlin.matrix.test.impl.base.mutable

import com.github.aliakseikaraliou.numkotlin.matrix.exceptions.MatrixEmptyException
import com.github.aliakseikaraliou.numkotlin.matrix.exceptions.MatrixIndexOutOfBoundsException
import com.github.aliakseikaraliou.numkotlin.matrix.exceptions.MatrixInvalidSizeException
import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.immutable.columnOf
import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.immutable.rawOf
import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.mutable.mutableMatrixOf
import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.mutable.mutableMatrixOfColumns
import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.mutable.mutableMatrixOfRaws
import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.mutable.mutableRawOf
import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.mutable.utils.map
import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.mutable.utils.mapIndexed
import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.mutable.utils.transpose
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class MutableMatrix {

    @Nested
    inner class Base {
        @Test
        fun set() {
            val actual = mutableMatrixOf(arrayOf(1, 2, 3), arrayOf(2, 3, 4))
            actual[0, 0] = 100

            val expected = mutableMatrixOf(arrayOf(100, 2, 3), arrayOf(2, 3, 4))

            assertEquals(expected, actual)

            assertThrows(MatrixIndexOutOfBoundsException::class.java) {
                actual[0, 4] = 100
            }
        }

        @Test
        fun raws() {
            val matrix = mutableMatrixOf(
                arrayOf(10, 2, 3),
                arrayOf(2, 30, 4),
                arrayOf(30, 4, 50)
            )

            val actual = matrix.raws
            val expected =
                listOf(
                    mutableRawOf(10, 2, 3),
                    mutableRawOf(2, 30, 4),
                    mutableRawOf(30, 4, 50)
                )

            assertEquals(expected, actual)
        }
    }

    @Nested
    inner class Create {
        @Test
        fun function() {
            val actual =
                mutableMatrixOf(3, 4) { i, j -> i + j }
            val expected = mutableMatrixOf(
                mutableListOf(0, 1, 2, 3, 1, 2, 3, 4, 2, 3, 4, 5), 3, 4
            )

            assertEquals(expected, actual)
        }

        @Test
        fun lists() {
            val actual = mutableMatrixOf(listOf(listOf(1, 2, 3), listOf(2, 3, 4)))
            val expected =
                mutableMatrixOf(mutableListOf(1, 2, 3, 2, 3, 4), 2, 3)

            assertEquals(expected, actual)

            assertThrows(MatrixEmptyException::class.java) {
                mutableMatrixOf(listOf<List<Int>>())
            }

            assertThrows(MatrixInvalidSizeException::class.java) {
                mutableMatrixOf(listOf(listOf(1, 2, 3), listOf(2, 3)))
            }
        }

        @Test
        fun arrays() {
            val actual =
                mutableMatrixOf(arrayOf(1, 2, 3), arrayOf(2, 3, 4))

            val expected =
                mutableMatrixOf(mutableListOf(1, 2, 3, 2, 3, 4), 2, 3)

            assertEquals(expected, actual)

            assertThrows(MatrixInvalidSizeException::class.java) {
                mutableMatrixOf(arrayOf(1, 2, 3), arrayOf(2, 4))
            }
        }

        @Test
        fun raws() {
            val actual = mutableMatrixOfRaws(
                listOf(rawOf(1, 2, 3), rawOf(2, 3, 4))
            )

            val expected =
                mutableMatrixOf(mutableListOf(1, 2, 3, 2, 3, 4), 2, 3)

            assertEquals(expected, actual)

            assertThrows(MatrixInvalidSizeException::class.java) {
                mutableMatrixOfRaws(listOf(rawOf(1, 2, 3), rawOf(2, 4)))
            }

            assertThrows(MatrixEmptyException::class.java) {
                mutableMatrixOfRaws<Int>(emptyList())
            }
        }

        @Test
        fun columns() {
            val actual = mutableMatrixOfColumns(
                listOf(columnOf(1, 2, 3), columnOf(2, 3, 4))
            )

            val expected =
                mutableMatrixOf(mutableListOf(1, 2, 2, 3, 3, 4), 3, 2)

            assertEquals(expected, actual)

            assertThrows(MatrixInvalidSizeException::class.java) {
                mutableMatrixOfColumns(listOf(columnOf(1, 2, 3), columnOf(2, 4)))
            }

            assertThrows(MatrixEmptyException::class.java) { mutableMatrixOfColumns<Int>(emptyList()) }
        }
    }

    @Nested
    inner class Utils {
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
}