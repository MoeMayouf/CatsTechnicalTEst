package com.mayouf.catstechnicaltest.data.repository

import com.mayouf.catstechnicaltest.data.source.CatsServiceApi
import com.mayouf.catstechnicaltest.domain.model.Cats
import com.mayouf.catstechnicaltest.domain.repository.ICatsRepository
import io.reactivex.Single

class CatsRepositoryImpl(private val catsServiceApi: CatsServiceApi) : ICatsRepository {
    override fun getCats(): Single<List<Cats>> = catsServiceApi.getCats()
}