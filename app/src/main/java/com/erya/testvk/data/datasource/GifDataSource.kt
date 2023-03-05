package com.erya.testvk.data.datasource

import com.erya.testvk.domain.models.GifDomain
import com.erya.testvk.network.GifApi

class GifDataSource(private val api: GifApi) {
    suspend fun getRandomGif() : GifDomain {
        val response = api.getRandomGiph()
        return GifDomain(
            id = response.dataResponse?.id,
            url = response.dataResponse?.images?.fixedWidthStill?.url,
            title = response.dataResponse?.title
        )
    }

    suspend fun getSpecialGif(query: String) = api.getSpecialGif(query).dataResponse?.map{
        GifDomain(
            id = it.id,
            url = it.images?.fixedWidthStill?.url,
            title = it.title
        )
    }
}