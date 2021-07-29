package com.codecollapse.motionflick.models.datamodels

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieDetail(

    var movieSubInfo: List<MovieSubInfo>,
    @Json(name = "adult")
    var adult: Boolean? = false,
    @Json(name = "backdrop_path")
    var backdrop_path: String? = "",
    @Json(name = "belongs_to_collection")
    var belongs_to_collection: String? = "",
    @Json(name = "budget")
    var budget: Int? = 0,
    @Json(name = "genres")
    var genres: List<Genres>,
    @Json(name = "homepage")
    var homepage: String? = "",
    @Json(name = "id")
    var id: Int? = 0,
    @Json(name = "imdb_id")
    var imdb_id: String? = "",
    @Json(name = "original_language")
    var original_language: String? = "",
    @Json(name = "original_title")
    var original_title: String? = "",
    @Json(name = "overview")
    var overview: String? = "",
    @Json(name = "popularity")
    var popularity: Float? = 0.0f,
    @Json(name = "poster_path")
    var poster_path: String? = "",
    @Json(name = "release_date")
    var release_date: String? = "",
    @Json(name = "revenue")
    var revenue: Int? = 0,
    @Json(name = "runtime")
    var runtime: Int? = 0,
    @Json(name = "spoken_languages")
    var spoken_languages: List<Languages>,
    @Json(name = "status")
    var status: String? = "",
    @Json(name = "tagline")
    var tagline: String? = "",
    @Json(name = "title")
    var title: String? = "",
    @Json(name = "video")
    var video: Boolean? = false,
    @Json(name = "vote_average")
    var vote_average: Float? = 0.0f,
    @Json(name = "vote_count")
    var vote_count: Int? = 0

) {
    @JsonClass(generateAdapter = true)
    data class MovieSubInfo(
        var totalViewIcon : Int?=0,
        var totalViews : String?=""
    ){}

    @JsonClass(generateAdapter = true)
    data class Genres(
        @Json(name = "id")
        var id: Int? = 0,
        @Json(name = "name")
        var name: String? = ""
    ) {}

    @JsonClass(generateAdapter = true)
    data class Languages(

        @Json(name = "english_name")
        var english_name: String? = "",
        @Json(name = "iso_639_1")
        var iso_639_1: String? = "",
        @Json(name = "name")
        var name: String? = ""
    ) {}
}