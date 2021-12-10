package com.lamarrulla.mytiendita.api.repo.firebase

import com.lamarrulla.mytiendita.data.Res
import com.lamarrulla.mytiendita.data.model.response.ProfileResp

interface RepoProfileFirebase {
    suspend fun getProfile(): Res<ProfileResp>
}