package com.lamarrulla.mytiendita.api.repo_imp.firebase

import com.lamarrulla.mytiendita.api.data_source.firebase.DsProfileFirebase
import com.lamarrulla.mytiendita.api.repo.firebase.RepoProfileFirebase
import com.lamarrulla.mytiendita.data.Res
import com.lamarrulla.mytiendita.data.model.response.ProfileResp

class RepoProfileFirebaseImpl(private val dsProfileFirebase: DsProfileFirebase):RepoProfileFirebase {
    override suspend fun getProfile(): Res<ProfileResp> {
        return dsProfileFirebase.getProfile()
    }
}