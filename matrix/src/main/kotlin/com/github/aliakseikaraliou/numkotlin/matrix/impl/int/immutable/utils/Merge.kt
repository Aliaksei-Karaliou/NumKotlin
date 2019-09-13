package com.github.aliakseikaraliou.numkotlin.matrix.impl.int.immutable.utils

import com.github.aliakseikaraliou.numkotlin.matrix.exceptions.MatrixInvalidSizeException
import com.github.aliakseikaraliou.numkotlin.matrix.impl.int.immutable.IntMatrixImpl
import com.github.aliakseikaraliou.numkotlin.matrix.impl.int.immutable.intColumnOf
import com.github.aliakseikaraliou.numkotlin.matrix.impl.int.immutable.intMatrixOf
import com.github.aliakseikaraliou.numkotlin.matrix.impl.int.immutable.intRawOf
import com.github.aliakseikaraliou.numkotlin.matrix.interfaces.int.IntMatrix
import com.github.aliakseikaraliou.numkotlin.matrix.interfaces.int.IntVectorColumn
import com.github.aliakseikaraliou.numkotlin.matrix.interfaces.int.IntVectorRaw

infix fun IntMatrix.left(matrix: IntMatrix) = matrix right this

infix fun IntMatrix.right(matrix: IntMatrix): IntMatrixImpl {
    if (height != matrix.height) {
        throw MatrixInvalidSizeException()
    }

    return intMatrixOf(height, width + matrix.width) { i, j ->
        if (j < width) {
            this[i, j]
        } else {
            matrix[i, j - width]
        }
    }
}

infix fun IntMatrix.up(matrix: IntMatrix) = matrix down this

infix fun IntMatrix.down(matrix: IntMatrix): IntMatrixImpl {
    if (width != matrix.width) {
        throw MatrixInvalidSizeException()
    }

    return intMatrixOf(height + matrix.height, width) { i, j ->
        if (i < height) {
            this[i, j]
        } else {
            matrix[i - height, j]
        }
    }
}

//raw

infix fun IntVectorRaw.left(raw: IntVectorRaw) = raw right this

infix fun IntVectorRaw.right(raw: IntVectorRaw) = intRawOf(width + raw.width) { i ->
    if (i < width) {
        this[i]
    } else {
        raw[i - width]
    }
}

//column

infix fun IntVectorColumn.up(column: IntVectorColumn) = column down this

infix fun IntVectorColumn.down(column: IntVectorColumn) = intColumnOf(height + column.height) { i ->
    if (i < height) {
        this[i]
    } else {
        column[i - height]
    }
}