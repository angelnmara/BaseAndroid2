package com.lamarrulla.mytiendita.ui.login

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import androidx.lifecycle.viewModelScope
import com.lamarrulla.mytiendita.R
import com.lamarrulla.mytiendita.data.model.repository.LoginRepository
import com.lamarrulla.mytiendita.data.Res
import com.lamarrulla.mytiendita.data.model.repository.ProfileRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val loginRepository: LoginRepository, private val profileRepository: ProfileRepository ,private val context: Context) : ViewModel() {

    private val TAG = javaClass.name
    private val _loginForm = MutableLiveData<LoginFormState>()

    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    private val _registerResult = MutableLiveData<LoginResult>()
    val registerResult: LiveData<LoginResult> = _registerResult

    fun login(username: String, password: String) {
        // can be launched in a separate asynchronous job
        viewModelScope.launch {
            val result = loginRepository.login(username, password)
            if (result is Res.Success) {
                _loginResult.value =
                    LoginResult(success = LoggedInUserView(displayName = result.data.displayName))
            } else {
                _loginResult.value = LoginResult(error = R.string.login_failed)
            }
        }
    }

    fun register(username: String, password: String){
        viewModelScope.launch {
            val result = loginRepository.register(username, password)
            if(result is Res.Success){
                _registerResult.value = LoginResult(success = LoggedInUserView(displayName =  result.data.displayName))
            }else{
                var loginResult: LoginResult =
                when((result as Res.Error).exception.message){
                    context.getString(R.string.in_use) -> LoginResult(error = R.string.in_use)
                    "" -> LoginResult(error = R.string.register_failed)
                    else ->  LoginResult(error = R.string.register_failed)
                }
                _registerResult.value = loginResult
            }
        }
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }

    private fun hasUserData(){

    }
}