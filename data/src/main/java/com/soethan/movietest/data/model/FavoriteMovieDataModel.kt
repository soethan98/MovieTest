package com.soethan.movietest.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.soethan.movietest.data.utils.Constants

@Entity(tableName = "favourite")
data class FavoriteMovieDataModel(
    @PrimaryKey
    var id: Int = 0,
    @ColumnInfo(name = "poster_path")
    var posterPath: String? = null,
    @ColumnInfo(name = "overview")
    var overview: String? = null,
    @ColumnInfo(name = "original_title")
    var title: String? = null,
    @ColumnInfo(name = "release_date")
    var releasedDate: String? = null,
    @ColumnInfo(name = "vote_average")
    var voteAverage: Double = 0.toDouble(),
    @ColumnInfo(name = "backdrop_path")
    var backdrop_path: String? = null,
    @ColumnInfo(name = "original_language")
    val originalLang: String
){
    fun getPosterImage() = "${Constants.IMAGE_BASE_URL}$posterPath"

}