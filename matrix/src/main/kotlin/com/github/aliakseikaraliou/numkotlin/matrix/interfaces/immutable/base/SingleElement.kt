package com.github.aliakseikaraliou.numkotlin.matrix.interfaces.immutable.base

import com.github.aliakseikaraliou.numkotlin.matrix.exceptions.MatrixIndexOutOfBoundsException

interface SingleElement<T> : VectorRaw<T>, VectorColumn<T> {
    val element: T

    override val height: Int
        get() = 1

    override val width: Int
        get() = 1

    override fun get(row: Int, column: Int) = when {
        row == 0 && column == 0 -> element
        else -> throw MatrixIndexOutOfBoundsException()
    }

    override fun get(i: Int) = when (i) {
        0 -> element
        else -> throw MatrixIndexOutOfBoundsException()
    }



}