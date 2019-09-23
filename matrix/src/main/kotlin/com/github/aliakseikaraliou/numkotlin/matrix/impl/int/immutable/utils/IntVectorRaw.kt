package com.github.aliakseikaraliou.numkotlin.matrix.impl.int.immutable.utils

import com.github.aliakseikaraliou.numkotlin.matrix.impl.int.immutable.intColumnOf
import com.github.aliakseikaraliou.numkotlin.matrix.impl.int.immutable.intRawOf
import com.github.aliakseikaraliou.numkotlin.matrix.interfaces.int.IntVectorRaw

fun IntVectorRaw.transpose() = intColumnOf(width) { this[it] }

//fun IntVectorRaw.mutable() = mutableRawOf(width) { this[it] }

infix fun IntVectorRaw.left(raw: IntVectorRaw) = raw right this

infix fun IntVectorRaw.left(item: Int) = this left intRawOf(item)

infix fun IntVectorRaw.right(raw: IntVectorRaw) = intRawOf(width + raw.width) { i ->
    if (i < width) {
        this[i]
    } else {
        raw[i - width]
    }
}

infix fun IntVectorRaw.right(item: Int) = this right intRawOf(item)
