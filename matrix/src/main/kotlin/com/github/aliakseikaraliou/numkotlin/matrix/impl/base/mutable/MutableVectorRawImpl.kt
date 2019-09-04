package com.github.aliakseikaraliou.numkotlin.matrix.impl.base.mutable

import com.github.aliakseikaraliou.numkotlin.matrix.exceptions.MatrixEmptyException
import com.github.aliakseikaraliou.numkotlin.matrix.exceptions.MatrixIndexOutOfBoundsException
import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.immutable.VectorRawImpl
import com.github.aliakseikaraliou.numkotlin.matrix.interfaces.base.mutable.MutableVectorRaw

class MutableVectorRawImpl<T>(override val list: MutableList<T>) : MutableVectorRaw<T>, VectorRawImpl<T>(list) {
    override fun set(x: Int, value: T) {
        if (x < list.size) {
            list[x] = value
        } else {
            throw MatrixIndexOutOfBoundsException()
        }
    }
}

fun <T> mutableVectorRaw(size: Int, creator: (Int) -> T): VectorRawImpl<T> {
    val list = ArrayList<T>(size)

    for (i in 0 until size) {
        list.add(creator(i))
    }

    return mutableVectorRaw(list)
}

fun <T> mutableVectorRaw(item1: T, vararg array: T) = mutableVectorRaw(
    array.toMutableList().apply {
        add(0, item1)
    }
)

fun <T> mutableVectorRaw(list: MutableList<T>) = when {
    list.isEmpty() -> throw MatrixEmptyException()
    else -> MutableVectorRawImpl(list)
}