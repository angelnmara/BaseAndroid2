package com.lamarrulla.mytiendita.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lamarrulla.mytiendita.api.repo.firebase.RepoLoginFirebase

class VMFatory(private val repoLoginFirebase: RepoLoginFirebase):ViewModelProvider.Factory {
    /*override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(RepoLoginFirebase::class.java).newInstance(repoLoginFirebase)
    }*/

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(RepoLoginFirebase::class.java).newInstance(repoLoginFirebase)
    }
}