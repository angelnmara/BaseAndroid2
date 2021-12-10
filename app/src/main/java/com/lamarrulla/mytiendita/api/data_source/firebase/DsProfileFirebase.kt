package com.lamarrulla.mytiendita.api.data_source.firebase

import android.app.Activity
import com.lamarrulla.mytiendita.data.Res
import com.lamarrulla.mytiendita.data.model.response.ProfileResp
import com.lamarrulla.mytiendita.utils.Utils
import java.lang.Exception

class DsProfileFirebase(private val activity: Activity) {
    private val utils = Utils(activity)
    private lateinit var profileResp:ProfileResp
    suspend fun getProfile(): Res<ProfileResp> {
        try {
            var auth = utils.inicializaFirebase()
            var user = auth.currentUser
            user?.let {
                 profileResp = ProfileResp(
                    idProfile = 0,
                    name = user.displayName!!,
                    email = user.email!!,
                    photoUrl = user.photoUrl!!,
                    emailVerified = user.isEmailVerified,
                    uid = user.uid
                )
            }
            return Res.Success(profileResp)
        }catch (exception:Exception){
            return Res.Error(exception)
        }
    }
}