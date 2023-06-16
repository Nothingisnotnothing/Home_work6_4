package kg.vohkysan.home_work6_4.di

import kg.vohkysan.home_work6_4.core.network.networkModule
import kg.vohkysan.home_work6_4.data.remote.remoteDataSource

val koinModules = listOf(
    repoModules,
    viewModules,
    remoteDataSource,
    networkModule
)