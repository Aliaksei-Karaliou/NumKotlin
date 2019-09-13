package com.github.aliakseikaraliou.numkotlin.matrix.impl.base.immutable.utils

import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.immutable.columnOf
import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.immutable.matrixOf
import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.immutable.rawOf
import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.mutable.mutableColumnOf
import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.mutable.mutableMatrixOf
import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.mutable.mutableRawOf
import com.github.aliakseikaraliou.numkotlin.matrix.interfaces.base.immutable.Matrix
import com.github.aliakseikaraliou.numkotlin.matrix.interfaces.base.immutable.VectorColumn
import com.github.aliakseikaraliou.numkotlin.matrix.interfaces.base.immutable.VectorRaw

//matrix
fun <T, R> Matrix<T>.map(transform: (T) -> R) =
    matrixOf(height, width) { i, j ->
        transform(this[i, j])
    }

fun <T, R> Matrix<T>.mapIndexed(transform: (Int, Int, T) -> R) =
    matrixOf(height, width) { i, j ->
        transform(i, j, this[i, j])
    }

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
    val list = ArrayList<T>(height * width)

    for (i in 0 until height) {
        for (j in 0 until width) {
            list.add(this[i, j])
        }
    }

    return list
}

fun <T> Matrix<T>.transpose() =
    matrixOf(width, height) { i, j ->
        this[j, i]
    }

fun <T> Matrix<T>.toMutableMatrix() =
    mutableMatrixOf(height, width) { i, j ->
        this[i, j]
    }

//raw
fun <T, R> VectorRaw<T>.map(transform: (T) -> R) =
    rawOf(width) { transform(this[it]) }

fun <T, R> VectorRaw<T>.mapIndexed(transform: (Int, T) -> R) =
    rawOf(width) { transform(it, this[it]) }

fun <T, R> VectorRaw<T>.forEachIndexed(action: (Int, T) -> R) {
    for (i in 0 until width) {
        action(i, this[i])
    }
}

fun <T> VectorRaw<T>.transpose() =
    columnOf(width) { this[it] }

fun <T> VectorRaw<T>.toList(): List<T> {
    val result = ArrayList<T>(width)

    for (i in 0 until width) {
        result.add(this[i])
    }

    return result
}

fun <T> VectorRaw<T>.toMutableVectorRaw() = mutableRawOf(width) { this[it] }


//column
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

fun <T> VectorColumn<T>.toMutable() = mutableColumnOf(height) { this[it] }