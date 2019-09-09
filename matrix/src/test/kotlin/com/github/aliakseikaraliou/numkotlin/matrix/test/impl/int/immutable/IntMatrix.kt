package com.github.aliakseikaraliou.numkotlin.matrix.test.impl.int.immutable

import com.github.aliakseikaraliou.numkotlin.matrix.exceptions.MatrixEmptyException
import com.github.aliakseikaraliou.numkotlin.matrix.exceptions.MatrixIndexOutOfBoundsException
import com.github.aliakseikaraliou.numkotlin.matrix.exceptions.MatrixInvalidSizeException
import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.immutable.MatrixImpl
import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.immutable.columnOf
import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.immutable.rawOf
import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.immutable.utils.*
import com.github.aliakseikaraliou.numkotlin.matrix.impl.int.immutable.intMatrixOf
import com.github.aliakseikaraliou.numkotlin.matrix.impl.int.immutable.intMatrixOfColumns
import com.github.aliakseikaraliou.numkotlin.matrix.impl.int.immutable.intMatrixOfRaws
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class IntMatrix {
    @Nested
    inner class Base {
        @Test
        fun get() {
            val array = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)

            val matrixImpl = MatrixImpl(array, 4, 3)

            assertEquals(matrixImpl[0, 0], 1)
            assertEquals(matrixImpl[0, 1], 2)
            assertEquals(matrixImpl[0, 2], 3)
            assertEquals(matrixImpl[1, 0], 4)
            assertEquals(matrixImpl[1, 1], 5)
            assertEquals(matrixImpl[1, 2], 6)
            assertEquals(matrixImpl[2, 0], 7)
            assertEquals(matrixImpl[2, 1], 8)
            assertEquals(matrixImpl[2, 2], 9)
            assertEquals(matrixImpl[3, 0], 10)
            assertEquals(matrixImpl[3, 1], 11)
            assertEquals(matrixImpl[3, 2], 12)

            assertThrows(MatrixIndexOutOfBoundsException::class.java) { matrixImpl[3, 3] }
        }

        @Test
        fun height() {
            val array = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
            val matrixImpl = MatrixImpl(array, 4, 3)

            assertEquals(4, matrixImpl.height)
        }

        @Test
        fun width() {
            val array = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
            val matrixImpl = MatrixImpl(array, 4, 3)

            assertEquals(3, matrixImpl.width)
        }

        @DisplayName("hashCode()")
        @Test
        fun testHashCode() {
            val array = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
            val matrixImpl = MatrixImpl(array, 4, 3)

            assertEquals(1354274310, matrixImpl.hashCode())
        }

        @DisplayName("toString()")
        @Test
        fun testToString() {
            val array = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
            val matrixImpl = MatrixImpl(array, 4, 3)

            assertEquals("[[1, 2, 3],\n[4, 5, 6],\n[7, 8, 9],\n[10, 11, 12]]", matrixImpl.toString())
        }

        @Test
        fun raws() {
            val matrix = intMatrixOf(
                arrayOf(10, 2, 3),
                arrayOf(2, 30, 4),
                arrayOf(30, 4, 50)
            )

            val actual = matrix.raws
            val expected = listOf(
                rawOf(10, 2, 3),
                rawOf(2, 30, 4),
                rawOf(30, 4, 50)
            )

            assertEquals(expected, actual)
        }

        @Test
        fun columns() {
            val matrix = intMatrixOf(
                arrayOf(10, 2, 3),
                arrayOf(2, 30, 4),
                arrayOf(30, 4, 50)
            )

            val actual = matrix.columns

            val expected = listOf(
                columnOf(10, 2, 30),
                columnOf(2, 30, 4),
                columnOf(3, 4, 50)
            )

            assertEquals(expected, actual)
        }
    }

    @Nested
    inner class Create {
        @Test
        fun function() {
            val actual =
                intMatrixOf(3, 4) { i, j -> i + j }

            val expected =
                intMatrixOf(listOf(0, 1, 2, 3, 1, 2, 3, 4, 2, 3, 4, 5), 3, 4)

            assertEquals(expected, actual)
        }

        @Test
        fun lists() {
            val actual =
                intMatrixOf(listOf(listOf(1, 2, 3), listOf(2, 3, 4)))

            val expected =
                intMatrixOf(listOf(1, 2, 3, 2, 3, 4), 2, 3)

            assertEquals(expected, actual)

            assertThrows(MatrixEmptyException::class.java) {
                intMatrixOf(listOf())
            }

            assertThrows(MatrixInvalidSizeException::class.java) {
                intMatrixOf(listOf(listOf(1, 2, 3), listOf(2, 3)))
            }
        }

        @Test
        fun arrays() {
            val actual =
                intMatrixOf(arrayOf(1, 2, 3), arrayOf(2, 3, 4))

            val expected =
                intMatrixOf(listOf(1, 2, 3, 2, 3, 4), 2, 3)

            assertEquals(expected, actual)

            assertThrows(MatrixInvalidSizeException::class.java) {
                intMatrixOf(arrayOf(1, 2, 3), arrayOf(2, 4))
            }
        }

        @Test
        fun raws() {
            val actual = intMatrixOfRaws(
                listOf(rawOf(1, 2, 3), rawOf(2, 3, 4))
            )

            val expected =
                intMatrixOf(listOf(1, 2, 3, 2, 3, 4), 2, 3)

            assertEquals(expected, actual)

            assertThrows(MatrixInvalidSizeException::class.java) {
                intMatrixOfRaws(listOf(rawOf(1, 2, 3), rawOf(2, 4)))
            }

            assertThrows(MatrixEmptyException::class.java) {
                intMatrixOfRaws(emptyList())
            }
        }

        @Test
        fun columns() {
            val actual = intMatrixOfColumns(
                listOf(columnOf(1, 2, 3), columnOf(2, 3, 4))
            )

            val expected = intMatrixOf(listOf(1, 2, 2, 3, 3, 4), 3, 2)

            assertEquals(expected, actual)

            assertThrows(MatrixInvalidSizeException::class.java) {
                intMatrixOfColumns(listOf(columnOf(1, 2, 3), columnOf(2, 4)))
            }

            assertThrows(MatrixEmptyException::class.java) { intMatrixOfColumns(emptyList()) }
        }
    }

    @Nested
    inner class Utils {
        @Test
        fun map() {
            val input = intMatrixOf(
                arrayOf(1, 2, 3),
                arrayOf(2, 3, 100)
            )

            val actual = input.map { it * it }
            val expected = intMatrixOf(
                arrayOf(1, 4, 9),
                arrayOf(4, 9, 10000)
            )

            assertEquals(expected, actual)
        }

        @Test
        fun mapIndexed() {
            val input = intMatrixOf(
                arrayOf(1, 2, 3),
                arrayOf(2, 3, 100)
            )

            val actual = input.mapIndexed { i, j, value -> (i + j) * value }
            val expected = intMatrixOf(
                arrayOf(0, 2, 6),
                arrayOf(2, 6, 300)
            )

            assertEquals(expected, actual)
        }

        @Test
        fun forEach() {
            val input = intMatrixOf(
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
            val input = intMatrixOf(
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
            val input = intMatrixOf(
                arrayOf(1, 2, 3),
                arrayOf(2, 3, 100)
            )

            val actual = input.toList()
            val expected = listOf(1, 2, 3, 2, 3, 100)

            assertEquals(expected, actual)
        }

        @Test
        fun transpose() {
            val input = intMatrixOf(
                arrayOf(1, 2, 3),
                arrayOf(2, 3, 100)
            )

            val actual = input.transpose()
            val expected = intMatrixOf(
                arrayOf(1, 2),
                arrayOf(2, 3),
                arrayOf(3, 100)
            )

            assertEquals(expected, actual)
        }

        @Disabled
        @Test
        fun mutableMatrix() {
//            val input = intMatrixOf(
//                arrayOf(1, 2, 3),
//                arrayOf(2, 3, 100)
//            )
//
//            val actual = input.toMutableMatrix()
//            val expected = mutableintMatrixOf(
//                arrayOf(1, 2, 3),
//                arrayOf(2, 3, 100)
//            )
//
//            assertEquals(expected, actual)
        }
    }

    @Nested
    inner class Merge {
        @Test
        fun left() {
            val matrix1 = intMatrixOf(
                arrayOf(1, 2, 3),
                arrayOf(2, 3, 4),
                arrayOf(3, 4, 5)
            )

            val matrix2 = intMatrixOf(
                arrayOf(1, 2, 300),
                arrayOf(2, 3, 400),
                arrayOf(3, 4, 500)
            )

            val matrix3 = intMatrixOf(
                arrayOf(1, 2, 300),
                arrayOf(2, 3, 400)
            )

            val actual = matrix1 left matrix2
            val expected = intMatrixOf(
                arrayOf(1, 2, 300, 1, 2, 3),
                arrayOf(2, 3, 400, 2, 3, 4),
                arrayOf(3, 4, 500, 3, 4, 5)
            )

            assertEquals(expected, actual)

            assertThrows(MatrixInvalidSizeException::class.java) {
                matrix1 left matrix3
            }
        }

        @Test
        fun right() {
            val matrix1 = intMatrixOf(
                arrayOf(1, 2, 3),
                arrayOf(2, 3, 4),
                arrayOf(3, 4, 5)
            )

            val matrix2 = intMatrixOf(
                arrayOf(1, 2, 300),
                arrayOf(2, 3, 400),
                arrayOf(3, 4, 500)
            )

            val matrix3 = intMatrixOf(
                arrayOf(1, 2, 300),
                arrayOf(2, 3, 400)
            )

            val actual = matrix1 right matrix2
            val expected = intMatrixOf(
                arrayOf(1, 2, 3, 1, 2, 300),
                arrayOf(2, 3, 4, 2, 3, 400),
                arrayOf(3, 4, 5, 3, 4, 500)
            )

            assertEquals(expected, actual)

            assertThrows(MatrixInvalidSizeException::class.java) {
                matrix1 right matrix3
            }
        }

        @Test
        fun up() {
            val matrix1 = intMatrixOf(
                arrayOf(1, 2, 3),
                arrayOf(2, 3, 4),
                arrayOf(3, 4, 5)
            )

            val matrix2 = intMatrixOf(
                arrayOf(1, 2, 300),
                arrayOf(2, 3, 400),
                arrayOf(3, 4, 500)
            )

            val matrix3 = intMatrixOf(
                arrayOf(1, 2),
                arrayOf(2, 3),
                arrayOf(3, 4)
            )

            val actual = matrix1 up matrix2
            val expected = intMatrixOf(
                arrayOf(1, 2, 300),
                arrayOf(2, 3, 400),
                arrayOf(3, 4, 500),
                arrayOf(1, 2, 3),
                arrayOf(2, 3, 4),
                arrayOf(3, 4, 5)
            )

            assertEquals(expected, actual)

            assertThrows(MatrixInvalidSizeException::class.java) {
                matrix1 up matrix3
            }
        }

        @Test
        fun down() {
            val matrix1 = intMatrixOf(
                arrayOf(1, 2, 3),
                arrayOf(2, 3, 4),
                arrayOf(3, 4, 5)
            )

            val matrix2 = intMatrixOf(
                arrayOf(1, 2, 300),
                arrayOf(2, 3, 400),
                arrayOf(3, 4, 500)
            )

            val matrix3 = intMatrixOf(
                arrayOf(1, 2),
                arrayOf(2, 3),
                arrayOf(3, 4)
            )

            val actual = matrix1 down matrix2
            val expected = intMatrixOf(
                arrayOf(1, 2, 3),
                arrayOf(2, 3, 4),
                arrayOf(3, 4, 5),
                arrayOf(1, 2, 300),
                arrayOf(2, 3, 400),
                arrayOf(3, 4, 500)
            )

            assertEquals(expected, actual)

            assertThrows(MatrixInvalidSizeException::class.java) {
                matrix1 down matrix3
            }
        }
    }
}