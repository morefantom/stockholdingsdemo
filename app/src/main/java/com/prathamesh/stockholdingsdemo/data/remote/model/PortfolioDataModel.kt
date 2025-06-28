package com.prathamesh.stockholdingsdemo.data.remote.model

import com.google.gson.annotations.SerializedName

data class PortfolioDataModel(
    @SerializedName("userHolding")
    val userHolding: List<HoldingDataModel> = emptyList(),
)