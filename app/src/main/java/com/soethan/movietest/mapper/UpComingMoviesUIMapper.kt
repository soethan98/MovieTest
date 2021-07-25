package com.soethan.movietest.mapper

import com.soethan.movietest.models.UpComingUiModel
import com.soethan.movietest.domain.models.UpcomingDomainModel

class UpComingMoviesUIMapper {

    private fun transform(dataModel: UpcomingDomainModel): UpComingUiModel {
        return UpComingUiModel(
            id= dataModel.id,
            poster_path = dataModel.poster_path,
            release_date = dataModel.release_date,
            title = dataModel.title,
            vote_average = dataModel.vote_average,
            vote_count = dataModel.vote_count,
        )
    }

    fun transform(list: List<UpcomingDomainModel>): List<UpComingUiModel> {
        return list.map {
            transform(it)
        }
    }

}