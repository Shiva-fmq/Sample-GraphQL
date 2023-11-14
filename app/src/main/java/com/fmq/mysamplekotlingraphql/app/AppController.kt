package com.fmq.mysamplekotlingraphql.app

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.fmq.mysamplekotlingraphql.repository.MainRepository
import com.fmq.mysamplekotlingraphql.utils.ApolloConfig
import com.fmq.mysamplekotlingraphql.viewmodel.MainViewModel
import com.fmq.mysamplekotlingraphql.viewmodel.common.ViewModelFactory
import com.fmq.mysamplekotlingraphql.viewmodel.common.bindViewModel
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.direct
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class AppController : Application(), KodeinAware {

    companion object {
        lateinit var instance : AppController
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    override val kodein = Kodein.lazy {
        import(androidXModule(this@AppController))
        bind<Kodein>() with singleton { kodein }
        bind<ViewModelProvider.Factory>() with singleton { ViewModelFactory(kodein.direct) }
        bind() from singleton { ApolloConfig.getApolloClient() }
        //====================================================================================================
        //View Model
        //====================================================================================================
        bindViewModel<MainViewModel>() with provider { MainViewModel(instance()) }
        //====================================================================================================
        //Repository
        //====================================================================================================
        bind() from singleton { MainRepository(instance()) }
    }

}

