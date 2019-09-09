package com.github.aliakseikaraliou.numkotlin.matrix.impl.base.mutable.utils

import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.mutable.mutableColumnOf
import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.mutable.mutableMatrixOf
import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.mutable.mutableRawOf
import com.github.aliakseikaraliou.numkotlin.matrix.interfaces.base.mutable.MutableMatrix
import com.github.aliakseikaraliou.numkotlin.matrix.interfaces.base.mutable.MutableVectorColumn
import com.github.aliakseikaraliou.numkotlin.matrix.interfaces.base.mutable.MutableVectorRaw

fun <T, R> MutableMatrix<T>.map(transform: (T) -> R) =
    mutableMatrixOf(height, width) { i, j ->
        transform(this[i, j])
    }
fun <T, R> MutableMatrix<T>.mapIndexed(transform: (Int, Int, T) -> R) =
    mutableMatrixOf(height, width) { i, j ->
        transform(
            i,
            j,
            this[i, j]
        )
    }

fun <T> MutableMatrix<T>.transpose() = mutableMatrixOf(
    height = width,
    width = height
) { i, j -> this[j, i] }

//raw
fun <T, R> MutableVectorRaw<T>.map(transform: (T) -> R) =
    mutableRawOf(width) { transform(this[it]) }

fun <T, R> MutableVectorRaw<T>.mapIndexed(transform: (Int, T) -> R) =
    mutableRawOf(width) { transform(it, this[it]) }

fun <T> MutableVectorRaw<T>.transpose() =
    mutableColumnOf(width) { this[it] }


//column
fun <T, R> MutableVectorColumn<T>.map(transform: (T) -> R) =
    mutableColumnOf(height) { transform(this[it]) }

fun <T, R> MutableVectorColumn<T>.mapIndexed(transform: (Int, T) -> R) =
    mutableColumnOf(height) { transform(it, this[it]) }

fun <T> MutableVectorColumn<T>.transpose() =
    mutableRawOf(height) { this[it] }
