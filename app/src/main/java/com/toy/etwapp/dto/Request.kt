package com.toy.etwapp.dto

import com.google.gson.annotations.SerializedName

class Request {
    data class Req(
        @SerializedName("id")
        val id: Long
    )
}