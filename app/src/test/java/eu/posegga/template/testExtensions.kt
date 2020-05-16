package eu.posegga.template

import io.reactivex.observers.TestObserver

internal fun <T> TestObserver<T>.assertSingleValueAndGet(): T =
    assertValueCount(1).values().first()
