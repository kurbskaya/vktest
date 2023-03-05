package com.erya.testvk.domain.repository

import com.erya.testvk.domain.models.GifDomain

interface GifRepository {
    suspend fun getRandom() : List<GifDomain>
    suspend fun getSpecial(query: String) : List<GifDomain>?
}