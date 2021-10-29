package com.lamarrulla.mytiendita.ui.login

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lamarrulla.mytiendita.api.data_source.firebase.DsLoginFirebase
import com.lamarrulla.mytiendita.api.repo.firebase.RepoLoginFirebase
import com.lamarrulla.mytiendita.api.repo_imp.firebase.RepoLoginFirebaseImpl
import com.lamarrulla.mytiendita.data.LoginRepository

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class LoginViewModelFactory(private val repoLoginFirebase: RepoLoginFirebase, private val activity: Activity) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(
                loginRepository = LoginRepository(
                    //dataSource = LoginDataSource(),
                    repoLoginFirebase = RepoLoginFirebaseImpl(DsLoginFirebase(activity))
                ), activity.applicationContext
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}