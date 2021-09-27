package com.mayouf.catstechnicaltest.domain.usecase.cats

import com.mayouf.catstechnicaltest.domain.model.Cats
import com.mayouf.catstechnicaltest.domain.repository.ICatsRepository
import com.mayouf.catstechnicaltest.domain.usecase.base.SingleUseCase
import com.mayouf.catstechnicaltest.domain.utils.ThreadScheduler
import io.reactivex.Single
import javax.inject.Inject

class GetCatsUseCase @Inject constructor(
    threadScheduler: ThreadScheduler,
    private val repository: ICatsRepository
) :
    SingleUseCase<List<Cats>>(threadScheduler) {
    override fun createSingleUseCase(): Single<List<Cats>> {
        return repository.getCats()
    }
}