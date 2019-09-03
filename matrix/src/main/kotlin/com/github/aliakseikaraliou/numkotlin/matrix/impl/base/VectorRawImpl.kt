package com.github.aliakseikaraliou.numkotlin.matrix.impl.base

import com.github.aliakseikaraliou.numkotlin.matrix.exceptions.MatrixEmptyException
import com.github.aliakseikaraliou.numkotlin.matrix.exceptions.MatrixIndexOutOfBoundsException
import com.github.aliakseikaraliou.numkotlin.matrix.interfaces.base.VectorRaw

class VectorRawImpl<T> internal constructor(override val list: List<T>) :
    VectorRaw<T>, MatrixImpl<T>(list, 1, list.size) {

    override val height: Int
        get() = super<VectorRaw>.height

    override val width: Int
        get() = list.size

    override val raws: List<VectorRaw<T>>
        get() = super<VectorRaw>.raws

    override fun get(row: Int, column: Int) = super<VectorRaw>.get(row, column)

    override fun get(column: Int) = when {
        column < list.size -> list[column]
        else -> throw MatrixIndexOutOfBoundsException()
    }

    override fun toString(): String {
        return "[${list.joinToString(separator = ", ")}]"
    }
}

fun <T> rawOf(size: Int, creator: (Int) -> T): VectorRawImpl<T> {
    val list = ArrayList<T>(size)

    for (i in 0 until size) {
        list.add(creator(i))
    }

    return rawOf(list)
}

fun <T> rawOf(item1: T, vararg array: T) = rawOf(
    array.toMutableList().apply {
        add(0, item1)
    }
)

fun <T> rawOf(list: List<T>) = when {
    list.isEmpty() -> throw MatrixEmptyException()
    else -> VectorRawImpl(list)
}

