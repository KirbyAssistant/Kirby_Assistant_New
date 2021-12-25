package ren.imyan.kirby.common.ktx

import kotlinx.coroutines.flow.*
import ren.imyan.kirby.R
import ren.imyan.kirby.common.utils.toast
import retrofit2.HttpException
import timber.log.Timber
import java.net.ConnectException
import java.net.SocketTimeoutException

fun <T> Flow<T>.httpMap() =
    map {
        Result.success(it)
    }

fun <T> Flow<Result<T>>.httpCatch(
    isDefCatch: Boolean = true,
) = catch { error ->
    Timber.e(error)

    if (isDefCatch) {
        when (error) {
            is ConnectException -> toast("网络连接错误\n${error}")
            is SocketTimeoutException -> toast("超时\n${error}")
            is HttpException -> error.response()?.let { toast(it.errorBody().toString()) }
            else -> {
            }
        }
    }
    emit(Result.failure(error))
}

suspend inline fun <T> Flow<T>.next(
    isDefCatch: Boolean = true,
    crossinline doStart: () -> Unit = {},
    crossinline doComplete: () -> Unit = {},
    crossinline doFail: (e: Throwable?) -> Unit = {},
    crossinline doSuccess: suspend T.() -> Unit,
): Unit =
    onStart { doStart() }.httpMap().httpCatch(isDefCatch).onCompletion { doComplete() }
        .collect {
            if (it.isSuccess) {
                doSuccess(it.getOrThrow())
            } else {
                doFail(it.exceptionOrNull())
            }
        }

inline fun <T, R> Flow<T>.safeMap(crossinline block: (Result<T>) -> R) =
    httpMap().httpCatch().map {
        block(it)
    }