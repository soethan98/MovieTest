package com.soethan.movietest.data.di

import com.soethan.movietest.data.mapper.DetailMapper
import com.soethan.movietest.data.mapper.FavoriteDomainMapper
import com.soethan.movietest.data.mapper.PopularDomainMapper
import com.soethan.movietest.data.mapper.UpComingDomainMapper
import org.koin.dsl.module

val DATA_MAPPER_MODULE = module {
    factory { PopularDomainMapper() }
    factory { UpComingDomainMapper() }
    factory { DetailMapper() }
    factory { FavoriteDomainMapper() }

}