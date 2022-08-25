package com.geektach.kotlin2.data.base

import com.geektach.kotlin2.core.Resource
import kotlinx.coroutines.flow.flow
import okhttp3.Request

abstract class BaseRepository {

    protected fun <T> doRequest(request: suspend () -> T) = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(data = request()))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Error(data = null, message = e.localizedMessage?: "Unknown error"))
        }
    }
}