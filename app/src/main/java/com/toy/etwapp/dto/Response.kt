package com.toy.etwapp.dto

import com.google.gson.annotations.SerializedName

class Response {
    data class Food (
        @SerializedName("userId")
        val userId: Long,

        @SerializedName("name")
        val name: String,

        @SerializedName("pickCount")
        val pickCount: Long,

        @SerializedName("imgPath")
        val imgPath: String
    )
}