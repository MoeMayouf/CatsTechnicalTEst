package com.mayouf.catstechnicaltest.data.repository

import com.mayouf.catstechnicaltest.data.source.CatsServiceApi
import com.mayouf.catstechnicaltest.domain.model.Cats
import com.mayouf.catstechnicaltest.domain.repository.ICatsRepository
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Single
import org.junit.Test

class CatsRepositoryImplTest {
    lateinit var sut: ICatsRepository
    lateinit var api: CatsServiceApi

    @Test
    fun `get cat`() {
        val resp: List<Cats> = mock()

        api = mock {
            on { it.getCats() } doReturn Single.just(resp)
        }

        sut = CatsRepositoryImpl(api)

        val testObserver = sut.getCats().test()

        testObserver.assertNoErrors()
        testObserver.assertValue { it == resp }
        testObserver.assertComplete()

        verify(api).getCats()
    }
}