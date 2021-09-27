package com.mayouf.catstechnicaltest.domain.usecase

import com.mayouf.catstechnicaltest.domain.model.Cats
import com.mayouf.catstechnicaltest.domain.repository.ICatsRepository
import com.mayouf.catstechnicaltest.domain.usecase.cats.GetCatsUseCase
import com.mayouf.catstechnicaltest.domain.utils.ThreadScheduler
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Test

class GetCatsUseCaseTest {
    private lateinit var sut: GetCatsUseCase
    private lateinit var repository: ICatsRepository

    private var threadScheduler: ThreadScheduler = mock {
        on { io() } doReturn Schedulers.trampoline()
        on { main() } doReturn Schedulers.trampoline()
        on { computation() } doReturn Schedulers.trampoline()
    }

    @Test
    fun `verify interactions in success case`() {
        val resp: List<Cats> = mock()

        repository = mock {
            on { getCats() } doReturn Single.just(resp)
        }

        sut = GetCatsUseCase(threadScheduler, repository)

        val testObserver = sut.execute(onLoading = {}, onSuccess = {}, onError = {}).test()

        testObserver.assertNoErrors()
        verify(repository, times(2)).getCats()
    }

    @Test
    fun `verify interactions in failure case`() {

        repository = mock {
            on { getCats() } doReturn Single.error(Exception())
        }

        sut = GetCatsUseCase(threadScheduler, repository)

        val testObserver = sut.execute(onLoading = {}, onSuccess = {}, onError = {}).test()

        testObserver.assertError(Exception::class.java)
        verify(repository, times(2)).getCats()
    }


}