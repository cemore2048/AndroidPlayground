package com.example.androidplayground

import io.reactivex.Observable

class ExecutionRepository(private val executionEndpoint: ExecutionEndpoint) {
    fun execute(language: String, command: String): Observable<ExecutionResult> {
        return executionEndpoint.execute(language, command)
    }
}