package com.soethan.movietest.data.mapper

import com.soethan.movietest.data.model.FavoriteMovieDataModel
import com.soethan.movietest.data.model.UpcomingMovieDataModel
import com.soethan.movietest.domain.models.FavoriteDomainModel
import com.soethan.movietest.domain.models.UpcomingDomainModel
import kotlin.math.roundToInt

class FavoriteDomainMapper {


    fun mapItems(items: List<FavoriteMovieDataModel>): List<FavoriteDomainModel> {
        return items.map { mapItem(it) }

    }

    private fun mapItem(movie: FavoriteMovieDataModel): FavoriteDomainModel {
        with(movie) {
            return FavoriteDomainModel(
                backdrop_path = this.backdrop_path.orEmpty(),
                id = this.id,
                original_language = this.originalLang,
                original_title = this.title.orEmpty(),
                overview = this.overview.orEmpty(),
                popularity = this.voteAverage,
                poster_path = getPosterImage(),
                release_date = releasedDate,
                title = title.orEmpty(),
                vote_average = voteAverage,
                vote_count = this.voteAverage.roundToInt()
            )
        }
    }

    fun mapToDataItem(movie:FavoriteDomainModel):FavoriteMovieDataModel{
        with(movie){
            return FavoriteMovieDataModel(
                id = id,
                posterPath = poster_path ,
                overview = overview ,
                title = title,
                releasedDate = release_date ,
                voteAverage = vote_average,
                originalLang = original_language,
                backdrop_path = backdrop_path
            )
        }

    }
}