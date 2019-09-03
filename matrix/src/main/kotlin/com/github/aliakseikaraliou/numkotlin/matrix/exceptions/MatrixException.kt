package com.github.aliakseikaraliou.numkotlin.matrix.exceptions

open class MatrixException : Exception()

class MatrixInvalidSizeException : MatrixException()
class MatrixIndexOutOfBoundsException : MatrixException()
class MatrixEmptyException : MatrixException()