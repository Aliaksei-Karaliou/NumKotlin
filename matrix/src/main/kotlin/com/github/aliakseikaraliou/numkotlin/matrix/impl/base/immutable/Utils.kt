package com.github.aliakseikaraliou.numkotlin.matrix.impl.base.immutable

import com.github.aliakseikaraliou.numkotlin.matrix.interfaces.base.immutable.Matrix

fun <T, R> Matrix<T>.map(transform: (T) -> R) = matrixOf(height, width) { i, j -> transform(this[i, j]) }
fun <T, R> Matrix<T>.mapIndexed(transform: (Int, Int, T) -> R) =
    matrixOf(height, width) { i, j -> transform(i, j, this[i, j]) }

fun <T, R> Matrix<T>.forEach(action: (T) -> R) {
    for (i in 0 until height) {
        for (j in 0 until width) {
            action(this[i, j])
        }
    }
}

fun <T, R> Matrix<T>.forEachIndexed(action: (Int, Int, T) -> R) {
    for (i in 0 until height) {
        for (j in 0 until width) {
            action(i, j, this[i, j])
        }
    }
}

fun <T> Matrix<T>.toList(): List<T> {
    val list = mutableListOf<T>()

    for (i in 0 until height) {
        for (j in 0 until width) {
            list.add(this[i, j])
        }
    }

    return list
}

fun <T> Matrix<T>.transpose() = matrixOf(height = width, width = height) { i, j -> this[j, i] }
