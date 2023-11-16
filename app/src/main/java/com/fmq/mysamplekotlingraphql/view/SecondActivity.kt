package com.fmq.mysamplekotlingraphql.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.fmq.mysamplekotlingraphql.databinding.ActivitySecondLayoutBinding
import rx.Observable
import rx.Observer


class SecondActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySecondLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val myObservable: Observable<String?>? = Observable.create { sub -> // "Emit" any data to the subscriber
            sub.onNext("a")
            sub.onNext("b")
            sub.onNext("c")
            // Trigger the completion of the event
            sub.onCompleted()
        }

        val mySubscriber: Observer<String?> = object : Observer<String?> {
            // Triggered for each emitted value
            override fun onNext(s: String?) {
                println("onNext: $s")
                Log.e("Tag OnNext",s.toString())
            }
            // Triggered once the observable is complete
            override fun onCompleted() {
                println("done!")
            }
            // Triggered if there is any errors during the event
            override fun onError(e: Throwable?) {}

        }

        myObservable?.subscribe(mySubscriber)

    }
}