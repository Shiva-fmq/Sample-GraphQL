package com.fmq.mysamplekotlingraphql

import com.google.gson.annotations.SerializedName

data class ContinentModel (
    @SerializedName("data")
    val data : ContinentData

)

data class ContinentData(
    @SerializedName("continents")
    val continets : List<Continents>
)

data class Continents(
    @SerializedName("code")
    val code : String,
    @SerializedName("name")
    val name : String
)

