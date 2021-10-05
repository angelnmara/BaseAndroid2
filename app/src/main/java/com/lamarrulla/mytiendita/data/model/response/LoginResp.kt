package com.lamarrulla.mytiendita.data.model.response

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LoginResp(
    @PrimaryKey
    val idLoginResponse: Int,
    val uid: String?,
    val email: String?,
    val displayName: String?,
    val isEmailVerified: Boolean?,
    val phoneNumber: String?,
    val photoUrl: Uri?,
    val providerId: String?
)
