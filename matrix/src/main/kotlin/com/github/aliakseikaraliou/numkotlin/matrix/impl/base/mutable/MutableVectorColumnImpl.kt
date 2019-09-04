package com.github.aliakseikaraliou.numkotlin.matrix.impl.base.mutable

import com.github.aliakseikaraliou.numkotlin.matrix.exceptions.MatrixEmptyException
import com.github.aliakseikaraliou.numkotlin.matrix.exceptions.MatrixIndexOutOfBoundsException
import com.github.aliakseikaraliou.numkotlin.matrix.interfaces.base.mutable.MutableVectorColumn

class MutableVectorColumnImpl<T> internal constructor(override val list: MutableList<T>) : MutableVectorColumn<T>,
    MutableMatrixImpl<T>(list, list.size, 1) {

    override fun set(i: Int, value: T) {
        if (i < list.size) {
            list[i] = value
        } else {
            throw MatrixIndexOutOfBoundsException()
        }
    }

    override fun set(row: Int, column: Int, value: T) = super<MutableVectorColumn>.set(row, column, value)

    override fun get(row: Int, column: Int) = super<MutableVectorColumn>.get(row, column)

    override fun get(i: Int) = when {
        i < list.size -> list[i]
        else -> throw MatrixIndexOutOfBoundsException()
    }
}

fun <T> mutableColumnOf(size: Int, creator: (Int) -> T): MutableVectorColumnImpl<T> {
    val list = ArrayList<T>(size)

    for (i in 0 until size) {
        list.add(creator(i))
    }

    return mutableColumnOf(list)
}

fun <T> mutableColumnOf(item1: T, vararg items: T) =
    mutableColumnOf(items.toMutableList().apply { add(0, item1) })

fun <T> mutableColumnOf(list: List<T>) = when {
    list.isEmpty() -> throw MatrixEmptyException()
    else -> MutableVectorColumnImpl(list.toMutableList())
}

