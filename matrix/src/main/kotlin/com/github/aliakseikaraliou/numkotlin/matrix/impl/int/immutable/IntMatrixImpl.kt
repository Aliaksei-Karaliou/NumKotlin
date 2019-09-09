package com.github.aliakseikaraliou.numkotlin.matrix.impl.int.immutable

import com.github.aliakseikaraliou.numkotlin.matrix.exceptions.MatrixEmptyException
import com.github.aliakseikaraliou.numkotlin.matrix.exceptions.MatrixInvalidSizeException
import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.immutable.MatrixImpl
import com.github.aliakseikaraliou.numkotlin.matrix.interfaces.base.immutable.VectorColumn
import com.github.aliakseikaraliou.numkotlin.matrix.interfaces.base.immutable.VectorRaw
import com.github.aliakseikaraliou.numkotlin.matrix.interfaces.int.IntMatrix

class IntMatrixImpl internal constructor(list: List<Int>, height: Int, width: Int) : IntMatrix,
    MatrixImpl<Int>(list, height, width) {
}

fun intMatrixOf(height: Int, width: Int, creator: (Int, Int) -> Int): IntMatrixImpl {
    val list = mutableListOf<Int>()

    for (i in 0 until height * width) {
        val x = i / width
        val y = i % width

        list.add(creator(x, y))
    }

    return intMatrixOf(list, height, width)
}

fun intMatrixOf(lists: List<List<Int>>): MatrixImpl<Int> {
    if (lists.isEmpty()) {
        return com.github.aliakseikaraliou.numkotlin.matrix.impl.base.immutable.matrixOf(emptyList(), 0, 0)
    }

    val height = lists.size
    val width = lists[0].size

    val validSize = lists
        .all { it.size == width }

    if (validSize) {
        return intMatrixOf(lists.flatten(), height, width)
    } else {
        throw MatrixInvalidSizeException()
    }

}

fun intMatrixOf(array1: Array<Int>, vararg arrays: Array<Int>): MatrixImpl<Int> {
    val transformedArray = arrays
        .toMutableList()
        .apply { add(0, array1) }

    val height = arrays.size + 1
    val width = array1.size

    val validSize = arrays
        .all { it.size == width }

    if (validSize) {
        return intMatrixOf(height, width) { i, j -> transformedArray[i][j] }
    } else {
        throw MatrixInvalidSizeException()
    }
}

fun intMatrixOfRaws(raws: List<VectorRaw<Int>>): MatrixImpl<Int> {
    if (raws.isEmpty()) {
        return com.github.aliakseikaraliou.numkotlin.matrix.impl.base.immutable.matrixOf(emptyList(), 0, 0)
    }

    val height = raws.size
    val width = raws[0].width

    val validSize = raws
        .all { it.width == width }

    if (validSize) {
        return intMatrixOf(height, width) { i, j -> raws[i][j] }
    } else {
        throw MatrixInvalidSizeException()
    }
}

fun intMatrixOfColumns(columns: List<VectorColumn<Int>>): MatrixImpl<Int> {
    if (columns.isEmpty()) {
        return intMatrixOf(emptyList(), 0, 0)
    }

    val width = columns.size
    val height = columns[0].height

    val validSize = columns
        .all { it.height == height }

    if (validSize) {
        return intMatrixOf(height, width) { i, j -> columns[j][i] }
    } else {
        throw MatrixInvalidSizeException()
    }
}

fun intMatrixOf(list: List<Int>, height: Int, width: Int) = when {
    list.isEmpty() -> throw MatrixEmptyException()
    height * width != list.size -> throw MatrixInvalidSizeException()
//    height == 1 -> rawOf(list)
//    width == 1 -> columnOf(list)
    else -> IntMatrixImpl(list, height, width)
}

