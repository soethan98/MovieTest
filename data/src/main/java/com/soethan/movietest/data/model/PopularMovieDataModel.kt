package com.soethan.movietest.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.soethan.movietest.data.utils.Constants

@Entity(tableName = "popular")
data class PopularMovieDataModel(

    @PrimaryKey
    @SerializedName("id")
    var id: Int = 0,

    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    var posterPath: String? = null,

    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    var overview: String? = null,

    @ColumnInfo(name = "original_title")
    @SerializedName("original_title")
    var title: String? = null,

    @ColumnInfo(name = "release_date")
    @SerializedName("release_date")
    var releasedDate: String? = null,

    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    var voteAverage: Double = 0.toDouble(),


    @ColumnInfo(name = "backdrop_path")
    @SerializedName("backdrop_path")
    var backdrop_path: String? = null,

    @ColumnInfo(name = "original_language")
    @SerializedName("original_language")
    val originalLang:String

){
    fun getPosterImage() = "${Constants.IMAGE_BASE_URL}$posterPath"

}