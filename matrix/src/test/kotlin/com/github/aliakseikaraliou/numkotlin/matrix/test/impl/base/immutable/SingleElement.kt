package com.github.aliakseikaraliou.numkotlin.matrix.test.impl.base.immutable

import com.github.aliakseikaraliou.numkotlin.matrix.exceptions.MatrixIndexOutOfBoundsException
import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.immutable.singleElementOf
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class SingleElement {
    @Nested
    inner class Base {
        @Test
        fun height() {
            val singleElement = singleElementOf(3)

            assertEquals(1, singleElement.height)
        }

        @Test
        fun width() {
            val singleElement = singleElementOf(3)

            assertEquals(1, singleElement.width)
        }

        @Test
        fun columns() {
            val singleElement = singleElementOf(3)

            assertEquals(listOf(singleElement), singleElement.columns)
        }

        @Test
        fun raws() {
            val singleElement = singleElementOf(3)

            assertEquals(listOf(singleElement), singleElement.raws)
        }

        @Test
        fun get1() {
            val singleElement = singleElementOf(3)

            assertEquals(3, singleElement[0])
            assertThrows(MatrixIndexOutOfBoundsException::class.java) { singleElement[1] }
        }

        @Test
        fun get2() {
            val singleElement = singleElementOf(3)

            assertEquals(3, singleElement[0, 0])
            assertThrows(MatrixIndexOutOfBoundsException::class.java) { singleElement[0, 1] }
        }

        @DisplayName("toString()")
        @Test
        fun testToString() {
            val singleElement = singleElementOf(3)

            assertEquals("[3]", singleElement.toString())
        }
    }

    @Nested
    inner class Create {
        @Test
        fun create() {
            val singleElementImpl = singleElementOf(3)

            assertEquals(3, singleElementImpl.element)
        }
    }
}