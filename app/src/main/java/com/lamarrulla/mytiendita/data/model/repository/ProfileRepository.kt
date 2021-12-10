package com.lamarrulla.mytiendita.data.model.repository

import com.lamarrulla.mytiendita.api.repo.firebase.RepoProfileFirebase
import com.lamarrulla.mytiendita.data.Res
import com.lamarrulla.mytiendita.data.model.login.LoggedProfile

class ProfileRepository(val repoProfileFirebase: RepoProfileFirebase) {
    private lateinit var loggedProfile: LoggedProfile

    suspend fun getProfile(): Res<LoggedProfile> {
        var resultadoProfile = repoProfileFirebase.getProfile()
        if(resultadoProfile is Res.Success){
            loggedProfile = LoggedProfile(
                userName = resultadoProfile.data.name,
                email = resultadoProfile.data.email,
                photoUrl = resultadoProfile.data.email
            )
        }
        return Res.Success(loggedProfile)
    }
}