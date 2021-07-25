package com.soethan.movietest.mapper

import com.soethan.movietest.domain.models.FavoriteDomainModel
import com.soethan.movietest.domain.models.MovieDetailDomainModel
import com.soethan.movietest.models.FavoriteUiModel
import com.soethan.movietest.models.MovieDetailUiModel
import kotlin.math.roundToInt

class FavoriteMoviesUiMapper {

    fun mapItems(items: List<FavoriteDomainModel>): List<FavoriteUiModel> {
        return items.map { mapItem(it) }
    }

    private fun mapItem(dataModel: FavoriteDomainModel): FavoriteUiModel {
        return FavoriteUiModel(
            id = dataModel.id,
            poster_path = dataModel.poster_path,
            release_date = dataModel.release_date,
            title = dataModel.title,
            vote_average = dataModel.vote_average,
            vote_count = dataModel.vote_count,
            backdrop_path = dataModel.backdrop_path,
            lang = dataModel.original_language,
            overview = dataModel.overview,

            )
    }

    fun mapDomainModel(item: MovieDetailUiModel): FavoriteDomainModel {
        with(item) {
            return FavoriteDomainModel(
                backdrop_path = this.backdrop_path.orEmpty(),
                id = this.id,
                original_language = this.lang,
                original_title = this.title.orEmpty(),
                overview = this.overview.orEmpty(),
                popularity = this.vote_average,
                poster_path = poster_path,
                release_date = release_date,
                title = title.orEmpty(),
                vote_average = vote_average,
                vote_count = this.vote_count
            )
        }
    }

}