package com.github.aliakseikaraliou.numkotlin.matrix.impl.int

import com.github.aliakseikaraliou.numkotlin.matrix.impl.base.immutable.MatrixImpl
import com.github.aliakseikaraliou.numkotlin.matrix.interfaces.int.IntMatrix

class IntMatrixImpl internal constructor(list: List<Int>, height: Int, width: Int) : IntMatrix,
    MatrixImpl<Int>(list, height, width) {
}