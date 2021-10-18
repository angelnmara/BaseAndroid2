package com.lamarrulla.mytiendita.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lamarrulla.mytiendita.api.repo.firebase.RepoLoginFirebase
import com.lamarrulla.mytiendita.data.Res
import kotlinx.coroutines.launch

class NotificationsViewModel(private val repoLoginFirebase: RepoLoginFirebase) : ViewModel() {

    private val TAG = javaClass.name

    private val _logout = MutableLiveData<LogoutRespResult>()
    val logout: LiveData<LogoutRespResult> = _logout

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text

    fun getLogout(){
        viewModelScope.launch {
            val response = repoLoginFirebase.logoutFirebase()
            if (response is Res.Success){
                _logout.value = LogoutRespResult(success = response.data)
            }else{
                _logout.value = LogoutRespResult(error = 0)
            }
        }
    }

}