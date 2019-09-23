package com.github.aliakseikaraliou.numkotlin.matrix.impl.base.immutable.utils

import com.github.aliakseikaraliou.numkotlin.matrix.exceptions.MatrixInvalidSizeException
import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.immutable.MatrixImpl
import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.immutable.columnOf
import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.immutable.matrixOf
import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.immutable.rawOf
import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.mutable.mutableMatrixOf
import com.github.aliakseikaraliou.numkotlin.matrix.interfaces.base.immutable.Matrix

fun <T, R> Matrix<T>.map(transform: (T) -> R) =
    matrixOf(height, width) { i, j ->
        transform(this[i, j])
    }

fun <T, R> Matrix<T>.mapIndexed(transform: (Int, Int, T) -> R) =
    matrixOf(height, width) { i, j ->
        transform(i, j, this[i, j])
    }

fun <T, R> Matrix<T>.forEach(action: (T) -> R) {
    for (i in 0 until height) {
        for (j in 0 until width) {
            action(this[i, j])
        }
    }
}

fun <T, R> Matrix<T>.forEachIndexed(action: (Int, Int, T) -> R) {
    for (i in 0 until height) {
        for (j in 0 until width) {
            action(i, j, this[i, j])
        }
    }
}

fun <T> Matrix<T>.toList(): List<T> {
    val list = ArrayList<T>(height * width)

    for (i in 0 until height) {
        for (j in 0 until width) {
            list.add(this[i, j])
        }
    }

    return list
}

fun <T> Matrix<T>.transpose() =
    matrixOf(width, height) { i, j ->
        this[j, i]
    }

fun <T> Matrix<T>.mutable() =
    mutableMatrixOf(height, width) { i, j ->
        this[i, j]
    }

infix fun <T> Matrix<T>.left(matrix: Matrix<T>) = matrix right this

infix fun <T> Matrix<T>.left(item: T): Matrix<T> = this left columnOf(height) { item }

infix fun <T> Matrix<T>.right(matrix: Matrix<T>): MatrixImpl<T> {
    if (height != matrix.height) {
        throw MatrixInvalidSizeException()
    }

    return matrixOf(height, width + matrix.width) { i, j ->
        if (j < width) {
            this[i, j]
        } else {
            matrix[i, j - width]
        }
    }
}

infix fun <T> Matrix<T>.right(item: T) = this right columnOf(height) { item }

infix fun <T> Matrix<T>.up(matrix: Matrix<T>) = matrix down this

infix fun <T> Matrix<T>.up(item: T) = this up rawOf(width) { item }

infix fun <T> Matrix<T>.down(matrix: Matrix<T>): MatrixImpl<T> {
    if (width != matrix.width) {
        throw MatrixInvalidSizeException()
    }

    return matrixOf(height + matrix.height, width) { i, j ->
        if (i < height) {
            this[i, j]
        } else {
            matrix[i - height, j]
        }
    }
}

infix fun <T> Matrix<T>.down(item: T) = this down rawOf(width) { item }