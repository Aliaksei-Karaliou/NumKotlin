package com.github.aliakseikaraliou.numkotlin.matrix.interfaces.base.mutable

import com.github.aliakseikaraliou.numkotlin.matrix.exceptions.MatrixIndexOutOfBoundsException
import com.github.aliakseikaraliou.numkotlin.matrix.interfaces.base.immutable.VectorColumn

interface MutableVectorColumn<T> : MutableMatrix<T>, VectorColumn<T> {
    override fun set(row: Int, column: Int, value: T) = when {
        column == 0 && row < height -> this[row] = value
        else -> throw MatrixIndexOutOfBoundsException()
    }

    operator fun set(i: Int, value: T)
}