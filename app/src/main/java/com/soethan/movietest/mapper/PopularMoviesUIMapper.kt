package com.soethan.movietest.mapper

import com.soethan.movietest.models.PopularUiModel
import com.soethan.movietest.domain.models.PopularDomainModel

class PopularMoviesUIMapper {

    private fun transform(dataModel: PopularDomainModel): PopularUiModel {
        return PopularUiModel(
            id= dataModel.id,
            poster_path = dataModel.poster_path,
            release_date = dataModel.release_date,
            title = dataModel.title,
            vote_average = dataModel.vote_average,
            vote_count = dataModel.vote_count,
        )
    }

    fun transform(list: List<PopularDomainModel>): List<PopularUiModel> {
        return list.map {
            transform(it)
        }
    }

}