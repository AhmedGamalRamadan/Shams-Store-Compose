package com.ag.projects.shamsstorecompose.utils.helper

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import com.ag.projects.shamsstorecompose.utils.Result
import com.google.gson.JsonParseException
import java.io.IOException

suspend fun <T> ViewModel.handleRequest(
    request: suspend () -> T,
    state: MutableStateFlow<Result<T>>
) {
    try {
        state.emit(Result.Success(request()))
    } catch (networkException: IOException) {
        state.emit(Result.Error("Network error", networkException))
    } catch (jsonException: JsonParseException) {
        state.emit(Result.Error("Data parsing error", jsonException))
    } catch (e: Exception) {
        state.emit(Result.Error("Unexpected error", e))
    }
}