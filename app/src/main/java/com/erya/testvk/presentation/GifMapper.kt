package com.erya.testvk.presentation

import com.erya.testvk.domain.models.GifDomain
import com.erya.testvk.presentation.models.Gif

object GifMapper {
    fun mapDomainToGif(gifDomain: GifDomain) =
        Gif(
            id = gifDomain.id,
            url = gifDomain.url,
            title = gifDomain.title
        )
}