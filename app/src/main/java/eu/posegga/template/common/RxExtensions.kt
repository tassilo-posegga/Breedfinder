package eu.posegga.template.common

import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

fun <T : Any> Single<T>.subOnIoObserveMain(onSuccess: (T) -> Unit, onError: (Throwable) -> Unit) =
    subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeBy(
        onSuccess = onSuccess,
        onError = onError
    )

fun Completable.subOnIoObserveMain(onComplete: () -> Unit, onError: (Throwable) -> Unit) =
    subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeBy(
        onComplete = onComplete,
        onError = onError
    )