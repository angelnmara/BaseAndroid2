package com.lamarrulla.mytiendita.api.repo_imp.firebase

import com.lamarrulla.mytiendita.api.data_source.firebase.DsLoginFirebase
import com.lamarrulla.mytiendita.api.repo.firebase.RepoLoginFirebase
import com.lamarrulla.mytiendita.data.Res
import com.lamarrulla.mytiendita.data.model.request.LoginReq
import com.lamarrulla.mytiendita.data.model.response.LoginResp

class RepoLoginFirebaseImpl(private val dsLoginFirebase: DsLoginFirebase):RepoLoginFirebase {
    override suspend fun loginFirebase(loginReq: LoginReq): Res<LoginResp> {
        return dsLoginFirebase.loginFirebase(loginReq);
    }

    override suspend fun validaLoginFirebase(): Res<LoginResp> {
        return dsLoginFirebase.validaLoginFirebase();
    }

    override suspend fun logoutFirebase(): Res<Boolean> {
        return dsLoginFirebase.logoutLoginFirebase()
    }

}