package com.mayouf.catstechnicaltest.presentation.cats

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.mayouf.catstechnicaltest.domain.model.Cats
import com.mayouf.catstechnicaltest.domain.usecase.cats.GetCatsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CatsViewModel @Inject constructor(
    application: Application,
    private val getCatsUseCase: GetCatsUseCase
) :
    AndroidViewModel(application) {

    val loadingLiveData = MutableLiveData<Boolean>()
    val catsLiveData = MutableLiveData<List<Cats>>()

    fun loadCats() {
        getCatsUseCase.execute(
            onLoading = { loadingLiveData.postValue(true) },
            onSuccess = {
                loadingLiveData.postValue(false)
                catsLiveData.value = it
            },
            onError = { it.printStackTrace() }
        )
    }
}