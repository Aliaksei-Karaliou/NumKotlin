package com.github.aliakseikaraliou.numkotlin.matrix.interfaces.base

import com.github.aliakseikaraliou.numkotlin.matrix.exceptions.MatrixIndexOutOfBoundsException

interface VectorRaw<T> : Matrix<T> {
    override val height: Int
        get() = 1

    override val raws: List<VectorRaw<T>>
        get() = listOf(this)

    override fun get(row: Int, column: Int) = if (row == 0 && column < width) {
        this[column]
    } else {
        throw MatrixIndexOutOfBoundsException()
    }

    operator fun get(column: Int): T
}