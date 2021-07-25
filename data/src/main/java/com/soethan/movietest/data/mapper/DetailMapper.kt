package com.soethan.movietest.data.mapper

import com.soethan.movietest.data.model.MovieDetailDataModel
import com.soethan.movietest.domain.models.MovieDetailDomainModel
import kotlin.math.roundToInt

class DetailMapper {
    fun mapItem(item: MovieDetailDataModel): MovieDetailDomainModel {
        with(item) {
            return MovieDetailDomainModel(
                backdrop_path = getBackDrop(),
                id = this.id,
                original_language = this.original_language,
                original_title = this.title.orEmpty(),
                overview = this.overview.orEmpty(),
                popularity = this.vote_average,
                poster_path = getPosterImage(),
                release_date = release_date,
                title = title.orEmpty(),
                video = true,
                vote_average = vote_average,
                vote_count = this.vote_average.roundToInt()
            )
        }
    }
}