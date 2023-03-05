package com.erya.testvk.network.models

import com.google.gson.annotations.SerializedName

data class ResponseModel (
    @SerializedName("data") val dataResponse: DataResponseModel? = null,
    @SerializedName("meta") val metaResponse: MetaResponseModel? = null
)

data class ResponseSearchModel (
    @SerializedName("data") val dataResponse: List<DataResponseModel>? = null,
    @SerializedName("pagination") val metaResponse: PaginationResponseModel? = null
)

data class PaginationResponseModel (
    @SerializedName("count") val countGifs: Int? = null
)

data class MetaResponseModel (
    @SerializedName("response_id") val responseId: String? = null
)

data class FixedWidthResponseModel(
    @SerializedName("url") val url: String? = null,
)

data class DataResponseModel (
    @SerializedName("id") val id: String? = null,
    @SerializedName("url") val url: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("images") val images: ImagesResponseModel? = null,
)

data class ImagesResponseModel (
    @SerializedName("preview_gif") val fixedWidthStill: FixedWidthResponseModel? = null,
)