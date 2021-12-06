package com.example.androidplayground

import io.reactivex.Observable
import javax.inject.Inject

class ExecutionRepository @Inject constructor(private val executionEndpoint: ExecutionEndpoint) {
    fun execute(language: String, command: String): Observable<ExecutionResult> {
        return executionEndpoint.execute(CommandRequest(language, command))
    }
}