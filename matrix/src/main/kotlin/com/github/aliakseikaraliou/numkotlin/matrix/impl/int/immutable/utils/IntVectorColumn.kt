package com.github.aliakseikaraliou.numkotlin.matrix.impl.int.immutable.utils

import com.github.aliakseikaraliou.numkotlin.matrix.impl.int.immutable.intColumnOf
import com.github.aliakseikaraliou.numkotlin.matrix.impl.int.immutable.intRawOf
import com.github.aliakseikaraliou.numkotlin.matrix.interfaces.int.IntVectorColumn

fun IntVectorColumn.transpose() =
    intRawOf(height) { this[it] }

//fun  IntVectorColumn.mutable() = mutableColumnOf(height) { this[it] }

infix fun IntVectorColumn.up(column: IntVectorColumn) = column down this

infix fun IntVectorColumn.up(item: Int) = this up intColumnOf(item)

infix fun IntVectorColumn.down(column: IntVectorColumn) = intColumnOf(height + column.height) { i ->
    if (i < height) {
        this[i]
    } else {
        column[i - height]
    }
}

infix fun IntVectorColumn.down(item: Int) = this down intColumnOf(item)