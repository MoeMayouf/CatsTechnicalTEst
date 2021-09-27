package com.mayouf.catstechnicaltest.data.source

import com.mayouf.catstechnicaltest.domain.model.Cats
import io.reactivex.Single
import retrofit2.http.GET

interface CatsServiceApi {

    @GET("images/search?limit=10&page=1&order=Desc")
    fun getCats(): Single<List<Cats>>

}