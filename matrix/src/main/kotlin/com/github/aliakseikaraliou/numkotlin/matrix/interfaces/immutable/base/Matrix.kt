package com.github.aliakseikaraliou.numkotlin.matrix.interfaces.immutable.base

interface Matrix<T> {
    val height: Int
    val width: Int

    val raws: List<VectorRaw<T>>
    val columns: List<VectorColumn<T>>

    operator fun get(row: Int, column: Int): T
}