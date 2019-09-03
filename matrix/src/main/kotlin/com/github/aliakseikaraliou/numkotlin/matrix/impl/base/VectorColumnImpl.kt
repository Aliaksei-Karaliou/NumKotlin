package com.github.aliakseikaraliou.numkotlin.matrix.impl.base

import com.github.aliakseikaraliou.numkotlin.matrix.exceptions.MatrixEmptyException
import com.github.aliakseikaraliou.numkotlin.matrix.exceptions.MatrixIndexOutOfBoundsException
import com.github.aliakseikaraliou.numkotlin.matrix.interfaces.immutable.base.VectorColumn

class VectorColumnImpl<T> internal constructor(override val list: List<T>) : VectorColumn<T>,
    MatrixImpl<T>(list, list.size, 1) {

    override val width: Int
        get() = super<VectorColumn>.width

    override val columns: List<VectorColumn<T>>
        get() = super<VectorColumn>.columns

    override fun get(row: Int, column: Int) = super<VectorColumn>.get(row, column)

    override fun get(i: Int) = when {
        i < list.size -> list[i]
        else -> throw MatrixIndexOutOfBoundsException()
    }
}

fun <T> columnOf(size: Int, creator: (Int) -> T): VectorColumnImpl<T> {
    val list = ArrayList<T>(size)

    for (i in 0 until size) {
        list.add(creator(i))
    }

    return columnOf(list)
}

fun <T> columnOf(item1: T, vararg items: T) = columnOf(items.toMutableList().apply {
    add(0, item1)
})

fun <T> columnOf(list: List<T>) = when {
    list.isEmpty() -> throw MatrixEmptyException()
    else -> VectorColumnImpl(list)
}

