package com.github.aliakseikaraliou.numkotlin.matrix.impl.base.immutable.utils

import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.immutable.columnOf
import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.immutable.rawOf
import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.mutable.mutableRawOf
import com.github.aliakseikaraliou.numkotlin.matrix.interfaces.base.immutable.VectorRaw

fun <T, R> VectorRaw<T>.map(transform: (T) -> R) =
    rawOf(width) { transform(this[it]) }

fun <T, R> VectorRaw<T>.mapIndexed(transform: (Int, T) -> R) =
    rawOf(width) { transform(it, this[it]) }

fun <T> VectorRaw<T>.forEachIndexed(action: (Int, T) -> Unit) {
    for (i in 0 until width) {
        action(i, this[i])
    }
}

fun <T> VectorRaw<T>.transpose() = columnOf(width) { this[it] }

fun <T> VectorRaw<T>.toList(): List<T> {
    val result = ArrayList<T>(width)

    for (i in 0 until width) {
        result.add(this[i])
    }

    return result
}

fun <T> VectorRaw<T>.mutable() = mutableRawOf(width) { this[it] }

infix fun <T> VectorRaw<T>.left(raw: VectorRaw<T>) = raw right this

infix fun <T> VectorRaw<T>.left(item: T) = this left rawOf(item)

infix fun <T> VectorRaw<T>.right(raw: VectorRaw<T>) = rawOf(width + raw.width) { i ->
    if (i < width) {
        this[i]
    } else {
        raw[i - width]
    }
}

infix fun <T> VectorRaw<T>.right(item: T) = this right rawOf(item)
