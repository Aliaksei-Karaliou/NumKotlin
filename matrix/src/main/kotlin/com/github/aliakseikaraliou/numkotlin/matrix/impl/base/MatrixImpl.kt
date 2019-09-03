package com.github.aliakseikaraliou.numkotlin.matrix.impl.base

import com.github.aliakseikaraliou.numkotlin.matrix.exceptions.MatrixEmptyException
import com.github.aliakseikaraliou.numkotlin.matrix.exceptions.MatrixIndexOutOfBoundsException
import com.github.aliakseikaraliou.numkotlin.matrix.exceptions.MatrixInvalidSizeException
import com.github.aliakseikaraliou.numkotlin.matrix.interfaces.base.Matrix
import com.github.aliakseikaraliou.numkotlin.matrix.interfaces.base.VectorColumn
import com.github.aliakseikaraliou.numkotlin.matrix.interfaces.base.VectorRaw

open class MatrixImpl<T> internal constructor(
    internal open val list: List<T>,
    override val height: Int,
    override val width: Int
) : Matrix<T> {

    override val raws: List<VectorRaw<T>>
        get() {
            val raws = mutableListOf<VectorRaw<T>>()

            for (i in 0 until height) {
                val listRaw = mutableListOf<T>()

                for (j in 0 until width) {
                    listRaw.add(this[i, j])
                }

                raws.add(rawOf(listRaw))
            }

            return raws
        }

    override val columns: List<VectorColumn<T>>
        get() {
            val columns = mutableListOf<VectorColumn<T>>()

            for (i in 0 until width) {
                val listRaw = mutableListOf<T>()

                for (j in 0 until height) {
                    listRaw.add(this[j, i])
                }

                columns.add(columnOf(listRaw))
            }

            return columns
        }

    override fun get(row: Int, column: Int) = when {
        row < height && column < width -> list[row * width + column]
        else -> throw MatrixIndexOutOfBoundsException()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MatrixImpl<*>

        if (list != other.list) return false
        if (height != other.height) return false
        if (width != other.width) return false

        return true
    }

    override fun hashCode(): Int {
        var result = list.hashCode()

        result = 31 * result + height
        result = 31 * result + width

        return result
    }

    override fun toString(): String {
        return "[${raws.joinToString(separator = ",\n")}]"
    }

}

fun <T> matrixOf(height: Int, width: Int, creator: (Int, Int) -> T): MatrixImpl<T> {
    val list = mutableListOf<T>()

    for (i in 0 until height * width) {
        val x = i / width
        val y = i % width

        list.add(creator(x, y))
    }

    return matrixOf(list, height, width)
}

fun <T> matrixOf(lists: List<List<T>>): MatrixImpl<T> {
    if (lists.isEmpty()) {
        return matrixOf(emptyList(), 0, 0)
    }

    val height = lists.size
    val width = lists[0].size

    val validSize = lists
        .all { it.size == width }

    if (validSize) {
        return matrixOf(lists.flatten(), height, width)
    } else {
        throw MatrixInvalidSizeException()
    }

}

fun <T> matrixOf(array1: Array<T>, vararg arrays: Array<T>): MatrixImpl<T> {
    val transformedArray = arrays
        .toMutableList()
        .apply { add(0, array1) }

    val height = arrays.size + 1
    val width = array1.size

    val validSize = arrays
        .all { it.size == width }

    if (validSize) {
        return matrixOf(
            height,
            width
        ) { i, j -> transformedArray[i][j] }
    } else {
        throw MatrixInvalidSizeException()
    }
}

fun <T> matrixOfRaws(raws: List<VectorRaw<T>>): MatrixImpl<T> {
    if (raws.isEmpty()) {
        return matrixOf(emptyList(), 0, 0)
    }

    val height = raws.size
    val width = raws[0].width

    val validSize = raws
        .all { it.width == width }

    if (validSize) {
        return matrixOf(height, width) { i, j -> raws[i][j] }
    } else {
        throw MatrixInvalidSizeException()
    }
}

fun <T> matrixOfColumns(columns: List<VectorColumn<T>>): MatrixImpl<T> {
    if (columns.isEmpty()) {
        return matrixOf(emptyList(), 0, 0)
    }

    val width = columns.size
    val height = columns[0].height

    val validSize = columns
        .all { it.height == height }

    if (validSize) {
        return matrixOf(height, width) { i, j -> columns[j][i] }
    } else {
        throw MatrixInvalidSizeException()
    }
}

fun <T> matrixOf(list: List<T>, height: Int, width: Int) = when {
    list.isEmpty() -> throw MatrixEmptyException()
    height * width != list.size -> throw MatrixInvalidSizeException()
    height == 1 -> rawOf(list)
    else -> MatrixImpl(list, height, width)
}