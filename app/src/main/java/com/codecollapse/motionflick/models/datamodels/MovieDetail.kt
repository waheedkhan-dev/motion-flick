package com.codecollapse.motionflick.models.datamodels

data class MovieDetail(
    var movieName: String? = "",
    var movieCoverPhoto: Int? = 0,
    var movieSubInfo: List<MovieSubInfo>

) {
    data class MovieSubInfo(
        var totalViewIcon : Int?=0,
        var totalViews : String?=""
    ){}
}