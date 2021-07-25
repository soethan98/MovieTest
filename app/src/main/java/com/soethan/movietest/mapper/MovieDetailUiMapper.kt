package com.soethan.movietest.mapper

import com.soethan.movietest.domain.models.MovieDetailDomainModel
import com.soethan.movietest.domain.models.PopularDomainModel
import com.soethan.movietest.models.MovieDetailUiModel
import com.soethan.movietest.models.PopularUiModel



class MovieDetailUiMapper {

     fun mapItem(dataModel: MovieDetailDomainModel): MovieDetailUiModel {
        return MovieDetailUiModel(
            id= dataModel.id,
            poster_path = dataModel.poster_path,
            release_date = dataModel.release_date,
            title = dataModel.title,
            vote_average = dataModel.vote_average,
            vote_count = dataModel.vote_count,
            backdrop_path = dataModel.backdrop_path,
            lang = dataModel.original_language,
            overview = dataModel.overview
        )
    }




}