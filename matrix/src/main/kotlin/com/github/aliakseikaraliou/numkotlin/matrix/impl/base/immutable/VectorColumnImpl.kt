package com.github.aliakseikaraliou.numkotlin.matrix.impl.base.immutable

import com.github.aliakseikaraliou.numkotlin.matrix.exceptions.MatrixEmptyException
import com.github.aliakseikaraliou.numkotlin.matrix.exceptions.MatrixIndexOutOfBoundsException
import com.github.aliakseikaraliou.numkotlin.matrix.interfaces.base.immutable.VectorColumn

class VectorColumnImpl<T> internal constructor(override val list: List<T>) : VectorColumn<T>,
    MatrixImpl<T>(list, list.size, 1) {

    override fun get(row: Int, column: Int) = super<VectorColumn>.get(row, column)

    override fun get(i: Int) = when {
        i < list.size -> list[i]
        else -> throw MatrixIndexOutOfBoundsException()
    }
}

fun <T> columnOf(height: Int, creator: (Int) -> T): VectorColumnImpl<T> {
    val list = ArrayList<T>(height)

    for (i in 0 until height) {
        list.add(creator(i))
    }

    return columnOf(list)
}

fun <T> columnOf(item1: T, vararg items: T) =
    columnOf(items.toMutableList().apply {
        add(0, item1)
    })

fun <T> columnOf(list: List<T>) = when {
    list.isEmpty() -> throw MatrixEmptyException()
    else -> VectorColumnImpl(list)
}

