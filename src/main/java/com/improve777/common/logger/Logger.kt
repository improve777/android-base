package com.improve777.common.logger

interface Logger {
    fun init(isDebug: Boolean)
    fun v(message: String)
    fun d(message: String)
    fun i(message: String)
    fun w(message: String)
    fun w(t: Throwable)
    fun e(message: String)
    fun e(t: Throwable)
}