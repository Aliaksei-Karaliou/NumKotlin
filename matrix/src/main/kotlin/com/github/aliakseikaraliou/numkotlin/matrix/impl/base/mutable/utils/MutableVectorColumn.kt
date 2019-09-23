package com.github.aliakseikaraliou.numkotlin.matrix.impl.base.mutable.utils

import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.mutable.mutableColumnOf
import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.mutable.mutableRawOf
import com.github.aliakseikaraliou.numkotlin.matrix.interfaces.base.mutable.MutableVectorColumn

fun <T, R> MutableVectorColumn<T>.map(transform: (T) -> R) =
    mutableColumnOf(height) { transform(this[it]) }

fun <T, R> MutableVectorColumn<T>.mapIndexed(transform: (Int, T) -> R) =
    mutableColumnOf(height) { transform(it, this[it]) }

fun <T> MutableVectorColumn<T>.transpose() =
    mutableRawOf(height) { this[it] }

fun <T> MutableVectorColumn<T>.toList(): List<T> {
    val result = ArrayList<T>(height)

    for (i in 0 until height) {
        result.add(this[i])
    }

    return result
}

fun <T> MutableVectorColumn<T>.mutable() = mutableColumnOf(height) { this[it] }

infix fun <T> MutableVectorColumn<T>.up(column: MutableVectorColumn<T>) = column down this

infix fun <T> MutableVectorColumn<T>.down(column: MutableVectorColumn<T>) =
    mutableColumnOf(height + column.height) { i ->
        if (i < height) {
            this[i]
        } else {
            column[i - height]
        }
    }