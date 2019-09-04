package com.github.aliakseikaraliou.numkotlin.matrix.interfaces.base.mutable

import com.github.aliakseikaraliou.numkotlin.matrix.exceptions.MatrixIndexOutOfBoundsException
import com.github.aliakseikaraliou.numkotlin.matrix.interfaces.base.immutable.VectorRaw

interface MutableVectorRaw<T> : MutableMatrix<T>, VectorRaw<T> {
    override fun set(row: Int, column: Int, value: T) = when {
        row == 0 && column < width -> this[row] = value
        else -> throw MatrixIndexOutOfBoundsException()
    }

    operator fun set(x: Int, value: T)
}