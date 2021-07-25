package com.soethan.movietest.data.mapper

import com.soethan.movietest.data.model.PopularMovieDataModel
import com.soethan.movietest.data.model.UpcomingMovieDataModel
import com.soethan.movietest.domain.models.PopularDomainModel
import com.soethan.movietest.domain.models.UpcomingDomainModel
import kotlin.math.roundToInt

class UpComingDomainMapper  {

    fun mapItems(items: List<UpcomingMovieDataModel>): List<UpcomingDomainModel> {
        return items.map { mapItem(it) }

    }

    private fun mapItem(movie: UpcomingMovieDataModel): UpcomingDomainModel {
        with(movie) {
            return UpcomingDomainModel(
                backdrop_path = this.backdrop_path.orEmpty(),
                id = this.id,
                original_language = this.originalLang,
                original_title = this.title.orEmpty(),
                overview = this.overview.orEmpty(),
                popularity = this.voteAverage,
                poster_path = getPosterImage(),
                release_date = releasedDate,
                title = title.orEmpty(),
                video = true,
                vote_average = voteAverage,
                vote_count = this.voteAverage.roundToInt()
            )
        }
    }
}
