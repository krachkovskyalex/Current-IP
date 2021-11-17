package com.krachkovsky.mycurrentip.koin

import com.krachkovsky.mycurrentip.api.ApiService
import com.krachkovsky.mycurrentip.ui.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {

    single { ApiService() }
    viewModel { MainViewModel(get()) }

}