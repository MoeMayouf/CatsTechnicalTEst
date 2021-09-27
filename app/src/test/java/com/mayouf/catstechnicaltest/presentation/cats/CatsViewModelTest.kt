package com.mayouf.catstechnicaltest.presentation.cats

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.mayouf.catstechnicaltest.domain.model.Cats
import com.mayouf.catstechnicaltest.domain.repository.ICatsRepository
import com.mayouf.catstechnicaltest.domain.usecase.cats.GetCatsUseCase
import com.mayouf.catstechnicaltest.domain.utils.ThreadScheduler
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.*
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class CatsViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()
    lateinit var repo: ICatsRepository

    @Mock
    lateinit var mockLiveDataObserver: Observer<List<Cats>>

    private var threadScheduler: ThreadScheduler = mock {
        on { io() } doReturn Schedulers.trampoline()
        on { main() } doReturn Schedulers.trampoline()
        on { computation() } doReturn Schedulers.trampoline()
    }

    @Before
    fun `set up`() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `Given useCase returns data, when getCats() called, then update catsLiveData`() {
        //Setting how up the mock behaves
        val resp: List<Cats> = mock()
        repo = mock {
            on { it.getCats() } doReturn Single.just(resp)
        }
        val useCase = GetCatsUseCase(threadScheduler, repo)
        val myViewModel = CatsViewModel(Application(), useCase)
        myViewModel.catsLiveData.observeForever(mockLiveDataObserver)
        myViewModel.loadCats()
        Assert.assertEquals(resp, myViewModel.catsLiveData.value)
    }

    @Test
    fun `Given useCase returns error, when getCats() called, then do not change catsLiveData`() {
        //Setting how up the mock behaves
        repo = mock {
            on { it.getCats() } doReturn Single.error(Exception())
        }
        val useCase = GetCatsUseCase(threadScheduler, repo)
        val myViewModel = CatsViewModel(Application(), useCase)
        myViewModel.catsLiveData.observeForever(mockLiveDataObserver)
        myViewModel.loadCats()
        verify(mockLiveDataObserver, times(0)).onChanged(any())
    }

    @After
    fun tearDown() {
    }
}