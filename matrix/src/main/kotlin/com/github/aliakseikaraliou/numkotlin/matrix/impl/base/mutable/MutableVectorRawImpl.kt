package com.github.aliakseikaraliou.numkotlin.matrix.impl.base.mutable

import com.github.aliakseikaraliou.numkotlin.matrix.exceptions.MatrixEmptyException
import com.github.aliakseikaraliou.numkotlin.matrix.exceptions.MatrixIndexOutOfBoundsException
import com.github.aliakseikaraliou.numkotlin.matrix.interfaces.base.mutable.MutableVectorRaw

class MutableVectorRawImpl<T>(override val list: MutableList<T>) :
    MutableVectorRaw<T>, MutableMatrixImpl<T>(list, 1, list.size) {

    override val height: Int
        get() = 1

    override fun get(i: Int) = when {
        i < list.size -> list[i]
        else -> throw MatrixIndexOutOfBoundsException()
    }

    override fun get(row: Int, column: Int) = when {
        row == 0 && column < list.size -> list[column]
        else -> throw MatrixIndexOutOfBoundsException()
    }

    override fun set(row: Int, column: Int, value: T) = super<MutableVectorRaw>.set(row, column, value)

    override fun set(x: Int, value: T) {
        if (x < list.size) {
            list[x] = value
        } else {
            throw MatrixIndexOutOfBoundsException()
        }
    }
}

fun <T> mutableRawOf(size: Int, creator: (Int) -> T): MutableVectorRawImpl<T> {
    val list = ArrayList<T>(size)

    for (i in 0 until size) {
        list.add(creator(i))
    }

    return mutableRawOf(list)
}

fun <T> mutableRawOf(item1: T, vararg array: T) = mutableRawOf(
    array.toMutableList().apply {
        add(0, item1)
    }
)

fun <T> mutableRawOf(list: List<T>) = when {
    list.isEmpty() -> throw MatrixEmptyException()
    else -> MutableVectorRawImpl(list.toMutableList())
}