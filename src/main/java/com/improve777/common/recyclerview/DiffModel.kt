package com.improve777.common.recyclerview

interface DiffModel {
    val diffId: String

    override fun equals(other: Any?) : Boolean
}