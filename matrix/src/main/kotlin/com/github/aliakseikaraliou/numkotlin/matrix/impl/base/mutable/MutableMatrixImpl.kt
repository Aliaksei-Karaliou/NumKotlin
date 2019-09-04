package com.github.aliakseikaraliou.numkotlin.matrix.impl.base.mutable

import com.github.aliakseikaraliou.numkotlin.matrix.exceptions.MatrixEmptyException
import com.github.aliakseikaraliou.numkotlin.matrix.exceptions.MatrixIndexOutOfBoundsException
import com.github.aliakseikaraliou.numkotlin.matrix.exceptions.MatrixInvalidSizeException
import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.immutable.MatrixImpl
import com.github.aliakseikaraliou.numkotlin.matrix.interfaces.base.immutable.VectorColumn
import com.github.aliakseikaraliou.numkotlin.matrix.interfaces.base.immutable.VectorRaw
import com.github.aliakseikaraliou.numkotlin.matrix.interfaces.base.mutable.MutableMatrix
import com.github.aliakseikaraliou.numkotlin.matrix.interfaces.base.mutable.MutableVectorRaw

open class MutableMatrixImpl<T> internal constructor(override val list: MutableList<T>, height: Int, width: Int) :
    MutableMatrix<T>,
    MatrixImpl<T>(list, height, width) {

    override fun set(row: Int, column: Int, value: T) = when {
        row < height && column < width -> list[matrixIndex(row, column)] = value
        else -> throw MatrixIndexOutOfBoundsException()
    }

    override val raws: List<MutableVectorRaw<T>>
        get() {
            val raws = mutableListOf<MutableVectorRaw<T>>()

            for (i in 0 until height) {
                val listRaw = mutableListOf<T>()

                for (j in 0 until width) {
                    listRaw.add(this[i, j])
                }

                raws.add(mutableRawOf(listRaw))
            }

            return raws
        }
}

fun <T> mutableMatrixOf(height: Int, width: Int, creator: (Int, Int) -> T): MutableMatrixImpl<T> {
    val list = mutableListOf<T>()

    for (i in 0 until height * width) {
        val x = i / width
        val y = i % width

        list.add(creator(x, y))
    }

    return mutableMatrixOf(list, height, width)
}

fun <T> mutableMatrixOf(lists: List<List<T>>): MutableMatrixImpl<T> {
    if (lists.isEmpty()) {
        return mutableMatrixOf(mutableListOf(), 0, 0)
    }

    val height = lists.size
    val width = lists[0].size

    val validSize = lists
        .all { it.size == width }

    if (validSize) {
        return mutableMatrixOf(lists.flatten().toMutableList(), height, width)
    } else {
        throw MatrixInvalidSizeException()
    }

}

fun <T> mutableMatrixOf(array1: Array<T>, vararg arrays: Array<T>): MutableMatrixImpl<T> {

    val transformedArray = arrays
        .toMutableList()
        .apply { add(0, array1) }

    val height = arrays.size + 1
    val width = array1.size

    val validSize = arrays
        .all { it.size == width }

    if (validSize) {
        return mutableMatrixOf(height, width) { i, j -> transformedArray[i][j] }
    } else {
        throw MatrixInvalidSizeException()
    }
}

fun <T> mutableMatrixOfRaws(raws: List<VectorRaw<T>>): MutableMatrixImpl<T> {
    if (raws.isEmpty()) {
        return mutableMatrixOf(mutableListOf(), 0, 0)
    }

    val height = raws.size
    val width = raws[0].width

    val validSize = raws
        .all { it.width == width }

    if (validSize) {
        return mutableMatrixOf(height, width) { i, j -> raws[i][j] }
    } else {
        throw MatrixInvalidSizeException()
    }
}

fun <T> mutableMatrixOfColumns(columns: List<VectorColumn<T>>): MutableMatrixImpl<T> {
    if (columns.isEmpty()) {
        return mutableMatrixOf(mutableListOf(), 0, 0)
    }

    val width = columns.size
    val height = columns[0].height

    val validSize = columns
        .all { it.height == height }

    if (validSize) {
        return mutableMatrixOf(height, width) { i, j -> columns[j][i] }
    } else {
        throw MatrixInvalidSizeException()
    }
}

fun <T> mutableMatrixOf(list: MutableList<T>, height: Int, width: Int): MutableMatrixImpl<T> = when {
    list.isEmpty() -> throw MatrixEmptyException()
    height * width != list.size -> throw MatrixInvalidSizeException()
    height == 1 -> mutableRawOf(list)
//    width == 1 -> columnOf(list)
    else -> MutableMatrixImpl(list, height, width)
}

