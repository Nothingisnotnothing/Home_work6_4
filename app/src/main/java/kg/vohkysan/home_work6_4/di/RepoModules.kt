package kg.vohkysan.home_work6_4.di

import kg.vohkysan.home_work6_4.repository.Repository
import org.koin.dsl.module

val repoModules = module {
    single { Repository(get()) }
}