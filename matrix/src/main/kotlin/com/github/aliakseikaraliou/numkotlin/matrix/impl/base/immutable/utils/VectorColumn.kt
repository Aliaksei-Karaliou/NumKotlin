package com.github.aliakseikaraliou.numkotlin.matrix.impl.base.immutable.utils

import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.immutable.columnOf
import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.immutable.rawOf
import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.mutable.mutableColumnOf
import com.github.aliakseikaraliou.numkotlin.matrix.interfaces.base.immutable.VectorColumn

fun <T, R> VectorColumn<T>.map(transform: (T) -> R) =
    columnOf(height) { transform(this[it]) }

fun <T, R> VectorColumn<T>.mapIndexed(transform: (Int, T) -> R) =
    columnOf(height) { transform(it, this[it]) }

fun <T, R> VectorColumn<T>.forEachIndexed(action: (Int, T) -> R) {
    for (i in 0 until height) {
        action(i, this[i])
    }
}

fun <T> VectorColumn<T>.transpose() =
    rawOf(height) { this[it] }

fun <T> VectorColumn<T>.toList(): List<T> {
    val result = ArrayList<T>(height)

    for (i in 0 until height) {
        result.add(this[i])
    }

    return result
}

fun <T> VectorColumn<T>.mutable() = mutableColumnOf(height) { this[it] }

infix fun <T> VectorColumn<T>.up(column: VectorColumn<T>) = column down this

infix fun <T> VectorColumn<T>.up(item: T) = this up columnOf(item)

infix fun <T> VectorColumn<T>.down(column: VectorColumn<T>) = columnOf(height + column.height) { i ->
    if (i < height) {
        this[i]
    } else {
        column[i - height]
    }
}

infix fun <T> VectorColumn<T>.down(item: T) = this down columnOf(item)