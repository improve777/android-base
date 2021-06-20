package com.improve777.common.recyclerview

interface DiffModel {
    val diffId: Any

    override fun equals(other: Any?) : Boolean
}