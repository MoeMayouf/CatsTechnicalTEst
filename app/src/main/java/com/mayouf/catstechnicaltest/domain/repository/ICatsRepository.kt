package com.mayouf.catstechnicaltest.domain.repository

import com.mayouf.catstechnicaltest.domain.model.Cats
import io.reactivex.Single

interface ICatsRepository {

    fun getCats(): Single<List<Cats>>
}