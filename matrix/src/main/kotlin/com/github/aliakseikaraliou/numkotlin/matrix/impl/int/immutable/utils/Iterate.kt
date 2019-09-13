package com.github.aliakseikaraliou.numkotlin.matrix.impl.int.immutable.utils

import com.github.aliakseikaraliou.numkotlin.matrix.impl.int.immutable.intColumnOf
import com.github.aliakseikaraliou.numkotlin.matrix.impl.int.immutable.intMatrixOf
import com.github.aliakseikaraliou.numkotlin.matrix.impl.int.immutable.intRawOf
import com.github.aliakseikaraliou.numkotlin.matrix.interfaces.int.IntMatrix
import com.github.aliakseikaraliou.numkotlin.matrix.interfaces.int.IntVectorColumn
import com.github.aliakseikaraliou.numkotlin.matrix.interfaces.int.IntVectorRaw

fun IntMatrix.transpose() = intMatrixOf(height = width, width = height) { i, j -> this[j, i] }

//raw

fun IntVectorRaw.transpose() = intColumnOf(width) { this[it] }

//column

fun IntVectorColumn.transpose() = intRawOf(height) { this[it] }