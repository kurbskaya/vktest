package com.erya.testvk.data.repository

import com.erya.testvk.data.datasource.GifDataSource
import com.erya.testvk.domain.models.GifDomain
import com.erya.testvk.domain.repository.GifRepository

class GifRepositoryImpl(
    private val dataSource: GifDataSource
) : GifRepository {
    override suspend fun getRandom(): List<GifDomain> {
        val list = ArrayList<GifDomain>()
        for (i in 1..25) {
            list.add(dataSource.getRandomGif())
        }
        return list
    }

    override suspend fun getSpecial(query: String): List<GifDomain>? {
        return dataSource.getSpecialGif(query)?.slice(0..24)
    }
}