package com.github.aliakseikaraliou.numkotlin.matrix.impl.base

import com.github.aliakseikaraliou.numkotlin.matrix.interfaces.immutable.base.SingleElement
import com.github.aliakseikaraliou.numkotlin.matrix.interfaces.immutable.base.VectorColumn
import com.github.aliakseikaraliou.numkotlin.matrix.interfaces.immutable.base.VectorRaw

class SingleElementImpl<T> internal constructor(override val element: T) :
    SingleElement<T>, MatrixImpl<T>(listOf(element), 1, 1) {

    override val height: Int
        get() = super<SingleElement>.height

    override val width: Int
        get() = super<SingleElement>.width

    override val raws: List<VectorRaw<T>>
        get() = super<SingleElement>.raws

    override val columns: List<VectorColumn<T>>
        get() = super<SingleElement>.columns

    override fun get(row: Int, column: Int) = super<SingleElement>.get(row, column)
}

fun <T> singleElementOf(element: T) = SingleElementImpl(element)