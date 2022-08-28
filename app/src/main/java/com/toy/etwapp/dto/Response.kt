package com.toy.etwapp.dto

import com.google.gson.annotations.SerializedName

class Response {

    data class Res(

        @SerializedName("code")
        var code: Int,

        @SerializedName("message")
        var message: String,

        @SerializedName("data")
        var data: List<Food>
    )

    class Food(
        val userId: Long,

        val name: String,

        val pickCount: Long,

        val imgPath: String
    )
}