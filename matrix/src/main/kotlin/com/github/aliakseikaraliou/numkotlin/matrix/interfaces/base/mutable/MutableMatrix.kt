package com.github.aliakseikaraliou.numkotlin.matrix.interfaces.base.mutable

import com.github.aliakseikaraliou.numkotlin.matrix.interfaces.base.immutable.Matrix

interface MutableMatrix<T> : Matrix<T> {
    operator fun set(row: Int, column: Int, value: T)

    override val raws: List<MutableVectorRaw<T>>
}