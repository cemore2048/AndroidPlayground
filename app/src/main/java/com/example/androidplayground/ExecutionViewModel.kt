package com.example.androidplayground

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ExecutionViewModel(
    private val executionRepository: ExecutionRepository,
) : ViewModel() {

    private val executionLiveData = MutableLiveData<String>()
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