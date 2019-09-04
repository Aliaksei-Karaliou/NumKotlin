package com.github.aliakseikaraliou.numkotlin.matrix.interfaces.base.immutable

import com.github.aliakseikaraliou.numkotlin.matrix.exceptions.MatrixIndexOutOfBoundsException

interface VectorColumn<T> : Matrix<T> {
    override val width: Int
        get() = 1

    override fun get(row: Int, column: Int) = if (row < height && column == 0) {
        this[row]
    } else {
        throw MatrixIndexOutOfBoundsException()
    }

    operator fun get(i: Int): T

    override val columns: List<VectorColumn<T>>
        get() = listOf(this)
}