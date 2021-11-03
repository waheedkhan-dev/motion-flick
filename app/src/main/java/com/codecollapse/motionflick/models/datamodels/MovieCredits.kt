package com.codecollapse.motionflick.models.datamodels

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieCredits(
    @Json(name = "id")
    var id : Int?=0,
    @Json(name = "cast")
    var movieCast : List<MovieCast> = arrayListOf()
){
    @JsonClass(generateAdapter = true)
    data class MovieCast(
        @Json(name = "adult")
        var adult : Boolean = false,
        @Json(name = "gender")
        var gender : Int ? =0,
        @Json(name = "id")
        var id : Int?=0,
        @Json(name = "known_for_department")
        var known_for_department : String?="",
        @Json(name = "name")
        var name : String?="",
        @Json(name = "original_name")
        var original_name : String?="",
        @Json(name = "popularity")
        var popularity : Float?=0.0f,
        @Json(name = "profile_path")
        var profile_path : String?="",
        @Json(name = "cast_id")
        var cast_id : Int?=0,
        @Json(name = "character")
        var character : String?="",
        @Json(name = "credit_id")
        var credit_id : String?="",
        @Json(name = "order")
        var order : Int?=0
    ){}
}
