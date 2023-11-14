package com.florinda.umcostore.di

import javax.inject.Qualifier


@Qualifier
@Retention
annotation class Dispatcher(val dispatcher: DispatcherAnnotation)


enum class DispatcherAnnotation {
    Default,
    Main,
    Io,
    Unconfined
}