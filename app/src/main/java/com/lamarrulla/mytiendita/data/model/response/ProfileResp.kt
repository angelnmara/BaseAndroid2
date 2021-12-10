package com.lamarrulla.mytiendita.data.model.response

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProfileResp(
    @PrimaryKey
    val idProfile: Int,
    val name: String,
    val email: String,
    val photoUrl: Uri,
    val emailVerified:Boolean,
    val uid: String
)
