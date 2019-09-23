package com.github.aliakseikaraliou.numkotlin.matrix.impl.base.mutable.utils

import com.github.aliakseikaraliou.numkotlin.matrix.exceptions.MatrixInvalidSizeException
import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.mutable.MutableMatrixImpl
import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.mutable.mutableColumnOf
import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.mutable.mutableMatrixOf
import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.mutable.mutableRawOf
import com.github.aliakseikaraliou.numkotlin.matrix.interfaces.base.mutable.MutableMatrix

fun <T, R> MutableMatrix<T>.map(transform: (T) -> R) =
    mutableMatrixOf(height, width) { i, j ->
        transform(this[i, j])
    }

fun <T, R> MutableMatrix<T>.mapIndexed(transform: (Int, Int, T) -> R) =
    mutableMatrixOf(height, width) { i, j ->
        transform(i, j, this[i, j])
    }

fun <T> MutableMatrix<T>.toList(): List<T> {
    val list = ArrayList<T>(height * width)

    for (i in 0 until height) {
        for (j in 0 until width) {
            list.add(this[i, j])
        }
    }

    return list
}

fun <T> MutableMatrix<T>.transpose() =
    mutableMatrixOf(width, height) { i, j ->
        this[j, i]
    }

fun <T> MutableMatrix<T>.mutable() =
    mutableMatrixOf(height, width) { i, j ->
        this[i, j]
    }

infix fun <T> MutableMatrix<T>.left(matrix: MutableMatrix<T>) = matrix right this

infix fun <T> MutableMatrix<T>.left(item: T): MutableMatrix<T> = this left mutableColumnOf(height) { item }

infix fun <T> MutableMatrix<T>.right(matrix: MutableMatrix<T>): MutableMatrixImpl<T> {
    if (height != matrix.height) {
        throw MatrixInvalidSizeException()
    }

    return mutableMatrixOf(height, width + matrix.width) { i, j ->
        if (j < width) {
            this[i, j]
        } else {
            matrix[i, j - width]
        }
    }
}

infix fun <T> MutableMatrix<T>.right(item: T) = this right mutableColumnOf(height) { item }

infix fun <T> MutableMatrix<T>.up(matrix: MutableMatrix<T>) = matrix down this

infix fun <T> MutableMatrix<T>.up(item: T) = this up mutableRawOf(width) { item }

infix fun <T> MutableMatrix<T>.down(matrix: MutableMatrix<T>): MutableMatrixImpl<T> {
    if (width != matrix.width) {
        throw MatrixInvalidSizeException()
    }

    return mutableMatrixOf(height + matrix.height, width) { i, j ->
        if (i < height) {
            this[i, j]
        } else {
            matrix[i - height, j]
        }
    }
}

infix fun <T> MutableMatrix<T>.down(item: T) = this down mutableRawOf(width) { item }