package com.example.myapplication.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CountryDetails(
    @SerializedName("name")
    var name: String,
    @SerializedName("code")
    var code: String,
    var flag: String
) : Serializable
