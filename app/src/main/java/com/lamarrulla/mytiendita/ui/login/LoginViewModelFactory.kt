package com.lamarrulla.mytiendita.ui.login

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lamarrulla.mytiendita.api.data_source.firebase.DsLoginFirebase
import com.lamarrulla.mytiendita.api.data_source.firebase.DsProfileFirebase
import com.lamarrulla.mytiendita.api.repo.firebase.RepoLoginFirebase
import com.lamarrulla.mytiendita.api.repo_imp.firebase.RepoLoginFirebaseImpl
import com.lamarrulla.mytiendita.api.repo_imp.firebase.RepoProfileFirebaseImpl
import com.lamarrulla.mytiendita.data.model.repository.LoginRepository
import com.lamarrulla.mytiendita.data.model.repository.ProfileRepository

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class LoginViewModelFactory(private val activity: Activity) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(
                loginRepository = LoginRepository(
                    repoLoginFirebase = RepoLoginFirebaseImpl(DsLoginFirebase(activity))
                ),
                profileRepository = ProfileRepository(
                    repoProfileFirebase = RepoProfileFirebaseImpl(DsProfileFirebase(activity))
                )
                ,activity.applicationContext
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}