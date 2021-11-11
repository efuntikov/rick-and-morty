package com.efuntikov.rickandmorty

import com.badoo.reaktive.disposable.Disposable
import com.badoo.reaktive.single.Single
import com.badoo.reaktive.single.single
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

object CoroutineExtensions {
    private fun Job.asDisposable(): Disposable =
        object : Disposable {
            override val isDisposed: Boolean get() = !isActive

            override fun dispose() {
                cancel()
            }
        }

    private inline fun <T> launchCoroutine(
        context: CoroutineContext = Dispatchers.Default,
        crossinline onSuccess: (T) -> Unit,
        crossinline onError: (Throwable) -> Unit,
        crossinline block: suspend () -> T
    ): Disposable =
        GlobalScope
            .launch(context) {
                try {
                    block()
                } catch (e: Throwable) {
                    onError(e)
                    return@launch
                }.also(onSuccess)
            }
            .asDisposable()

    fun <T> singleFromCoroutine(
        context: CoroutineContext = Dispatchers.Default,
        block: suspend () -> T
    ): Single<T> =
        single { emitter ->
            launchCoroutine(
                context = context,
                onSuccess = emitter::onSuccess,
                onError = emitter::onError,
                block = block
            ).also(emitter::setDisposable)
        }
}