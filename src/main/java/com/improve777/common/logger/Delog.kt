package com.improve777.common.logger

import timber.log.Timber

object Delog : Logger {

    override fun init(isDebug: Boolean) {
        if (isDebug) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun v(message: String) {
        Timber.v(message)
    }

    override fun d(message: String) {
        Timber.d(message)
    }

    override fun i(message: String) {
        Timber.i(message)
    }

    override fun w(message: String) {
        Timber.w(message)
    }

    override fun w(t: Throwable) {
        Timber.w(t)
    }

    override fun e(message: String) {
        Timber.e(message)
    }

    override fun e(t: Throwable) {
        Timber.e(t)
    }
}