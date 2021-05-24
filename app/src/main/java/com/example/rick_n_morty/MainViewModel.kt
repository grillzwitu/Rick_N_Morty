package com.example.rick_n_morty

import android.util.Log
import androidx.lifecycle.*
import com.example.rick_n_morty.api.CharRepository
import com.example.rick_n_morty.api.Response
import kotlinx.coroutines.launch

const val TAG = "CharacterViewModel"

class MainViewModel(private val characterRepository: CharRepository) : ViewModel() {

    private val _characterLiveData = MutableLiveData<List<Response>>()
    val characterLiveData: LiveData<List<Response>>
        get() = _characterLiveData

    init {
        getCharacters()
    }

    private fun getCharacters() {
        viewModelScope.launch {
            try {
                _characterLiveData.value = characterRepository.getCharacters().results
                Log.d(TAG, "${_characterLiveData.value}")
            } catch (e: Exception) {
                Log.d(TAG, e.stackTraceToString())
            }
        }
    }

}

class MainViewModelFactory(private val characterRepository: CharRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(characterRepository) as T
        } else {
            throw IllegalArgumentException("UNKNOWN CLASS")
        }
    }
}