package com.github.aliakseikaraliou.numkotlin.matrix.interfaces.base.immutable

import com.github.aliakseikaraliou.numkotlin.matrix.exceptions.MatrixIndexOutOfBoundsException

interface VectorRaw<T> : Matrix<T> {
    override fun get(row: Int, column: Int) = if (row == 0 && column < width) {
        this[column]
    } else {
        throw MatrixIndexOutOfBoundsException()
    }

    operator fun get(i: Int): T
}