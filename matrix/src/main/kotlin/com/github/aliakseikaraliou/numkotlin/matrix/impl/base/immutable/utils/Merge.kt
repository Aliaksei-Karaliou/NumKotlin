package com.github.aliakseikaraliou.numkotlin.matrix.impl.base.immutable.utils

import com.github.aliakseikaraliou.numkotlin.matrix.exceptions.MatrixInvalidSizeException
import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.immutable.MatrixImpl
import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.immutable.columnOf
import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.immutable.matrixOf
import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.immutable.rawOf
import com.github.aliakseikaraliou.numkotlin.matrix.interfaces.base.immutable.Matrix
import com.github.aliakseikaraliou.numkotlin.matrix.interfaces.base.immutable.VectorColumn
import com.github.aliakseikaraliou.numkotlin.matrix.interfaces.base.immutable.VectorRaw

infix fun <T> Matrix<T>.left(matrix: Matrix<T>) = matrix right this

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

infix fun <T> Matrix<T>.up(matrix: Matrix<T>) = matrix down this

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

//raw

infix fun <T> VectorRaw<T>.left(raw: VectorRaw<T>) = raw right this

infix fun <T> VectorRaw<T>.right(raw: VectorRaw<T>) = rawOf(width + raw.width) { i ->
    if (i < width) {
        this[i]
    } else {
        raw[i - width]
    }
}

//column

infix fun <T> VectorColumn<T>.up(column: VectorColumn<T>) = column down this

infix fun <T> VectorColumn<T>.down(column: VectorColumn<T>) = columnOf(height + column.height) { i ->
    if (i < height) {
        this[i]
    } else {
        column[i - height]
    }
}