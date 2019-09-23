package com.github.aliakseikaraliou.numkotlin.matrix.impl.int.immutable

import com.github.aliakseikaraliou.numkotlin.matrix.exceptions.MatrixEmptyException
import com.github.aliakseikaraliou.numkotlin.matrix.exceptions.MatrixIndexOutOfBoundsException
import com.github.aliakseikaraliou.numkotlin.matrix.interfaces.int.IntVectorColumn

class IntVectorColumnImpl internal constructor(override val list: List<Int>) : IntVectorColumn,
    IntMatrixImpl(list, list.size, 1) {
    override fun get(row: Int, column: Int) = super<IntVectorColumn>.get(row, column)

    override fun get(i: Int) = when {
        i < list.size -> list[i]
        else -> throw MatrixIndexOutOfBoundsException()
    }
}

fun intColumnOf(height: Int, creator: (Int) -> Int): IntVectorColumnImpl {
    val list = ArrayList<Int>(height)

    for (i in 0 until height) {
        list.add(creator(i))
    }

    return intColumnOf(list)
}

fun intColumnOf(item1: Int, vararg items: Int) =
    intColumnOf(items.toMutableList().apply {
        add(0, item1)
    })

fun intColumnOf(list: List<Int>) = when {
    list.isEmpty() -> throw MatrixEmptyException()
    else -> IntVectorColumnImpl(list)
}

