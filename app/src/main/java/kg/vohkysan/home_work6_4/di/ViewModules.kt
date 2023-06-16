package kg.vohkysan.home_work6_4.di

import kg.vohkysan.home_work6_4.ui.actual.ActualViewModel
import kg.vohkysan.home_work6_4.ui.playlists.PlaylistsViewModel
import kg.vohkysan.home_work6_4.ui.videos.VideosViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModules = module {
    viewModel { PlaylistsViewModel(get()) }
    viewModel { VideosViewModel(get()) }
    viewModel { ActualViewModel(get()) }
}