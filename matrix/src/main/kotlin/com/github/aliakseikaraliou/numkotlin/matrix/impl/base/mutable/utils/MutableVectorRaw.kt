package com.github.aliakseikaraliou.numkotlin.matrix.impl.base.mutable.utils

import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.mutable.mutableColumnOf
import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.mutable.mutableRawOf
import com.github.aliakseikaraliou.numkotlin.matrix.interfaces.base.mutable.MutableVectorRaw

fun <T, R> MutableVectorRaw<T>.map(transform: (T) -> R) =
    mutableRawOf(width) { transform(this[it]) }

fun <T, R> MutableVectorRaw<T>.mapIndexed(transform: (Int, T) -> R) =
    mutableRawOf(width) { transform(it, this[it]) }

fun <T> MutableVectorRaw<T>.transpose() = mutableColumnOf(width) { this[it] }

infix fun <T> MutableVectorRaw<T>.left(raw: MutableVectorRaw<T>) = raw right this

infix fun <T> MutableVectorRaw<T>.right(raw: MutableVectorRaw<T>) = mutableRawOf(width + raw.width) { i ->
    if (i < width) {
        this[i]
    } else {
        raw[i - width]
    }
}