package com.soethan.movietest.di

import com.soethan.movietest.ui.page.favorite.FavoriteViewModel
import com.soethan.movietest.ui.page.moviedetail.MovieDetailViewModel
import com.soethan.movietest.ui.page.popularlist.PopularViewModel
import com.soethan.movietest.ui.page.upcominglist.UpcomingViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@ExperimentalCoroutinesApi
@FlowPreview
val VIEW_MODEL_MODULE = module {
    viewModel { PopularViewModel(get()) }
    viewModel { UpcomingViewModel(get()) }
    viewModel { MovieDetailViewModel(get(),get(),get(),get()) }
    viewModel { FavoriteViewModel(get()) }


}