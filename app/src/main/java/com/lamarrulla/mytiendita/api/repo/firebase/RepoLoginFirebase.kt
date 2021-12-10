package com.lamarrulla.mytiendita.api.repo.firebase

import com.lamarrulla.mytiendita.data.Res
import com.lamarrulla.mytiendita.data.model.request.LoginReq
import com.lamarrulla.mytiendita.data.model.response.LoginResp

interface RepoLoginFirebase {
    suspend fun loginFirebase(loginReq: LoginReq): Res<LoginResp>
    suspend fun validaLoginFirebase():Res<LoginResp>
    suspend fun logoutFirebase():Res<Boolean>
    suspend fun registerFirebase(loginReq: LoginReq): Res<LoginResp>
    suspend fun updateProfile():Res<LoginResp>
}