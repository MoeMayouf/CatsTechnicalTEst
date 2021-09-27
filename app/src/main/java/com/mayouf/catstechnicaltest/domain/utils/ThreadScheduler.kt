package com.mayouf.catstechnicaltest.domain.utils

import io.reactivex.Scheduler

interface ThreadScheduler {
    fun computation(): Scheduler
    fun io(): Scheduler
    fun main(): Scheduler
}