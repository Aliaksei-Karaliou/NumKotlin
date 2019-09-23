package com.github.aliakseikaraliou.numkotlin.matrix.impl.int.immutable.utils

import com.github.aliakseikaraliou.numkotlin.matrix.exceptions.MatrixInvalidSizeException
import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.immutable.MatrixImpl
import com.github.aliakseikaraliou.numkotlin.matrix.impl.int.immutable.IntMatrixImpl
import com.github.aliakseikaraliou.numkotlin.matrix.impl.int.immutable.intColumnOf
import com.github.aliakseikaraliou.numkotlin.matrix.impl.int.immutable.intMatrixOf
import com.github.aliakseikaraliou.numkotlin.matrix.impl.int.immutable.intRawOf
import com.github.aliakseikaraliou.numkotlin.matrix.interfaces.int.IntMatrix

fun IntMatrix.transpose() =
    intMatrixOf(width, height) { i, j ->
        this[j, i]
    }

//fun <T> IntMatrix.mutable() =
//    mutableMatrixOf(height, width) { i, j ->
//        this[i, j]
//    }

infix fun IntMatrix.left(matrix: IntMatrix) = matrix right this

infix fun IntMatrix.left(item: Int): IntMatrixImpl = this left intColumnOf(height) { item }

infix fun IntMatrix.right(matrix: IntMatrix): IntMatrixImpl {
    if (height != matrix.height) {
        throw MatrixInvalidSizeException()
    }

    return intMatrixOf(height, width + matrix.width) { i, j ->
        if (j < width) {
            this[i, j]
        } else {
            matrix[i, j - width]
        }
    }
}

infix fun IntMatrix.right(item: Int) = this right intColumnOf(height) { item }

infix fun IntMatrix.up(matrix: IntMatrix) = matrix down this

infix fun IntMatrix.up(item: Int) = this up intRawOf(width) { item }

infix fun IntMatrix.down(matrix: IntMatrix): IntMatrixImpl {
    if (width != matrix.width) {
        throw MatrixInvalidSizeException()
    }

    return intMatrixOf(height + matrix.height, width) { i, j ->
        if (i < height) {
            this[i, j]
        } else {
            matrix[i - height, j]
        }
    }
}

infix fun IntMatrix.down(item: Int) = this down intRawOf(width) { item }

fun MatrixImpl<Int>.intMatrix() = intMatrixOf(height, width) { i, j -> this[i, j] }