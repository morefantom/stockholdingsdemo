package com.prathamesh.stockholdingsdemo.data.remote.model

import com.google.gson.annotations.SerializedName

data class HoldingDataModel(
    @SerializedName("symbol")
    val symbol: String = "",
    @SerializedName("quantity")
    val quantity: Double = 0.0,
    @SerializedName("ltp")
    val ltp: Double = 0.0,
    @SerializedName("avgPrice")
    val avgPrice: Double = 0.0,
    @SerializedName("close")
    val close: Double = 0.0,
)