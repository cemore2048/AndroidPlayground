package com.example.androidplayground

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ExecutionViewModel(
    private val executionRepository: ExecutionRepository,
) : ViewModel() {

    val executionLiveData = MutableLiveData<String>()


    @SuppressLint("CheckResult")
    fun execute(command: String) {
        executionRepository.execute("python", command)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                executionLiveData.value = it.result
            },
                {
                    Log.d("Error", it.message.toString())
                }
            )
    }

}