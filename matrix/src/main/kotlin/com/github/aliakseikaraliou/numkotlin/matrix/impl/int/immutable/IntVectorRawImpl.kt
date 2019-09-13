package com.github.aliakseikaraliou.numkotlin.matrix.impl.int.immutable

import com.github.aliakseikaraliou.numkotlin.matrix.exceptions.MatrixEmptyException
import com.github.aliakseikaraliou.numkotlin.matrix.exceptions.MatrixIndexOutOfBoundsException
import com.github.aliakseikaraliou.numkotlin.matrix.interfaces.int.IntVectorRaw

open class IntVectorRawImpl internal constructor(override val list: List<Int>) : IntVectorRaw, IntMatrixImpl(list, 1, list.size) {
    override val width: Int
        get() = list.size

    override fun get(row: Int, column: Int) = super<IntVectorRaw>.get(row, column)

    override fun get(i: Int) = when {
        i < list.size -> list[i]
        else -> throw MatrixIndexOutOfBoundsException()
    }

    override fun toString(): String {
        return "[${list.joinToString(separator = ", ")}]"
    }
}

fun intRawOf(size: Int, creator: (Int) -> Int): IntVectorRaw {
    val list = ArrayList<Int>(size)

    for (i in 0 until size) {
        list.add(creator(i))
    }

    return intRawOf(list)
}

fun intRawOf(item1: Int, vararg array: Int) = intRawOf(
    array.toMutableList().apply {
        add(0, item1)
    }
)

fun intRawOf(list: List<Int>) = when {
    list.isEmpty() -> throw MatrixEmptyException()
    else -> IntVectorRawImpl(list)
}

