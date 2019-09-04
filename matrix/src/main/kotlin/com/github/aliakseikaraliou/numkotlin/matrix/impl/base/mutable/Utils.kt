package com.github.aliakseikaraliou.numkotlin.matrix.impl.base.mutable

import com.github.aliakseikaraliou.numkotlin.matrix.interfaces.base.mutable.MutableMatrix

fun <T, R> MutableMatrix<T>.map(transform: (T) -> R) = mutableMatrixOf(height, width) { i, j -> transform(this[i, j]) }
fun <T, R> MutableMatrix<T>.mapIndexed(transform: (Int, Int, T) -> R) =
    mutableMatrixOf(height, width) { i, j -> transform(i, j, this[i, j]) }

fun <T> MutableMatrix<T>.transpose() = mutableMatrixOf(height = width, width = height) { i, j -> this[j, i] }
