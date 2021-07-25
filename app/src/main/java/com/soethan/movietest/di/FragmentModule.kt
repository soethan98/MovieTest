package com.soethan.movietest.di

import com.soethan.movietest.ui.page.moviedetail.MovieDetailFragment
import com.soethan.movietest.ui.page.popularlist.PopularFragment
import com.soethan.movietest.ui.page.upcominglist.UpComingFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.androidx.fragment.dsl.fragment
import org.koin.dsl.module

//@ExperimentalCoroutinesApi
//@FlowPreview
//val FRAGMENT_MODULE = module {
////    fragment { PopularFragment(get()) }
//    fragment { UpComingFragment(get()) }
//    fragment { MovieDetailFragment(get()) }
//
//}