package com.prathamesh.stockholdingsdemo.data.remote.model

import com.google.gson.annotations.SerializedName

data class PortfolioResponse (
    @SerializedName("data")
    val data: PortfolioDataModel,
)