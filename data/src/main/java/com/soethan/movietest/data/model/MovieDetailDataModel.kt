package com.soethan.movietest.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.soethan.movietest.data.utils.Constants

@Entity(tableName = "movie_detail")
data class MovieDetailDataModel(

    @SerializedName("backdrop_path")
    var backdrop_path: String,

    @PrimaryKey
    @SerializedName("id")
    var id: Int,

    @ColumnInfo(name = "original_language")
    @SerializedName("original_language")
    var original_language: String,

    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    var overview: String,

    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    var poster_path: String,

    @ColumnInfo(name = "release_date")
    @SerializedName("release_date")
    var release_date: String,

    @ColumnInfo(name = "runtime")
    @SerializedName("runtine")
    var runtime: Int,

    @ColumnInfo(name = "original_title")
    @SerializedName("title")
    var title: String,

    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    var vote_average: Double
) {
    fun getPosterImage() = "${Constants.IMAGE_BASE_URL}$poster_path"
    fun getBackDrop() = "${Constants.IMAGE_BASE_URL}$backdrop_path"

}