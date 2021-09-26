package com.example.androidplayground

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import coil.load
import coil.transform.CircleCropTransformation
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter
import io.reactivex.rxjava3.internal.operators.observable.ObservableInterval
import io.reactivex.rxjava3.observables.ConnectableObservable
import kotlinx.android.synthetic.main.activity_main.*
import java.io.InputStream
import java.net.URL
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var catImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var image =
            "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.RGO2_JeImrSM658MPIASrwHaEo%26pid%3DApi&f=1"
        variant1.load(android.R.drawable.arrow_up_float)
        variant2.load(android.R.drawable.arrow_up_float) {
            transformations(CircleCropTransformation())
        }

        val worldObserver = Observable.just("world1", "world2", "world3")
            .doOnSubscribe {
                Log.d("Rafa", "subscribed to world")
            }

        val merged = Observable.merge(
            listOf(
                worldObserver,
                helloWithTimer()
            )
        )
            .doOnNext {
                Log.d("Rafa", "next was $it")
            }

        merged.subscribe()

        Thread.sleep(3000)

        merged.subscribe()



    }
}

private fun helloWithTimer(): Observable<Any> {
    val justObservable = Observable.just("hello1", "hello2", "hello3")
        .replay(2)
        .autoConnect()
        .doOnDispose {
            Log.d("Rafa", "on destroy")
        }
        .doOnComplete {
            Log.d("Rafa", "Observering completed")
        }
        .doOnNext {
            Log.d("Rafa", "just observablel emitting $it")
        }
        .doOnTerminate {
            Log.d("Rafa", "Observing terminated")
        }
        .doOnSubscribe {
            Log.d("Rafa", "something subscribed")
        }


    val timer = Observable.interval(1, TimeUnit.SECONDS)
        .doOnSubscribe {
            Log.d("Rafa", "someone subscribed")
        }

    return Observable.combineLatest(timer, justObservable) { time, observe ->
        Log.d("Rafa", "time is $time and observer is $observe")
    }
}
