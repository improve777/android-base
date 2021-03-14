package com.improve777.common.recyclerview

interface DiffModel {
    val id: Int

    override fun equals(other: Any?) : Boolean
}