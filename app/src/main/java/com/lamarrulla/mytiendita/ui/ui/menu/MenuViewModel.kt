package com.lamarrulla.mytiendita.ui.ui.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lamarrulla.mytiendita.api.repo.firebase.RepoLoginFirebase
import com.lamarrulla.mytiendita.data.Res
import kotlinx.coroutines.launch

class MenuViewModel(private val repoLoginFirebase: RepoLoginFirebase):ViewModel() {
    private val TAG = javaClass.name
    private val _validaLogin = MutableLiveData<LoginRespResult>()
    val validaLogin: LiveData<LoginRespResult> = _validaLogin

    fun getValidaLogin(){
        viewModelScope.launch {
            val result = repoLoginFirebase.validaLoginFirebase();
            if(result is Res.Success){
                _validaLogin.value = LoginRespResult(success = result.data)
            }else{
                _validaLogin.value = LoginRespResult(error = 0)
            }
        }
    }
}