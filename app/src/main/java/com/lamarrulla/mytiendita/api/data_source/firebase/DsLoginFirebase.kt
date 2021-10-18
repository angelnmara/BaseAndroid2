package com.lamarrulla.mytiendita.api.data_source.firebase

import android.app.Activity
import android.util.Log
import androidx.compose.runtime.currentRecomposeScope
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.lamarrulla.mytiendita.data.Res
import com.lamarrulla.mytiendita.data.model.request.LoginReq
import com.lamarrulla.mytiendita.data.model.response.LoginResp
import kotlinx.coroutines.tasks.await
import java.lang.Exception

class DsLoginFirebase(private val activity: Activity) {

    private lateinit var auth: FirebaseAuth
    private lateinit var loginResp:LoginResp
    private val TAG = javaClass.name

    private fun inicializaFirebase(){
        FirebaseApp.initializeApp(activity.applicationContext);
        auth = Firebase.auth
    }

    suspend fun validaLoginFirebase(): Res<LoginResp>{
        try {
            inicializaFirebase()
            val currentUser = auth.currentUser
            if(currentUser!=null){
                loginResp = fillUser(currentUser)
                return Res.Success(loginResp)
            }else{
                val ex = Exception("No se encuentra logeado")
                return Res.Error(ex)
            }
        }catch (ex:Exception){
            return Res.Error(ex)
        }
    }

    suspend fun logoutLoginFirebase():Res<Boolean>{
        try {
            Firebase.auth.signOut()
            return Res.Success(true)
        }catch (ex:Exception){
            return Res.Error(ex)
        }
    }

    suspend fun loginFirebase(loginReq:LoginReq): Res<LoginResp> {
        try {
            inicializaFirebase()
            auth.signInWithEmailAndPassword(loginReq.user, loginReq.password)
                .addOnCompleteListener(activity) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                            val user = auth.currentUser!!
                            loginResp = fillUser(user)
                        //updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.d(TAG, task.toString())
                    }
                }.await()
        }catch (ex:Exception){
            return Res.Error(ex)
        }
        return Res.Success(loginResp);
    }

    private fun fillUser(firebaseUser: FirebaseUser): LoginResp{
        loginResp = LoginResp(
            idLoginResponse = 0,
            uid = firebaseUser.uid,
            email = firebaseUser.email,
            displayName = firebaseUser.displayName,
            isEmailVerified = firebaseUser.isEmailVerified,
            phoneNumber =  firebaseUser.phoneNumber,
            photoUrl = firebaseUser.photoUrl,
            providerId = firebaseUser.providerId
        )
        return loginResp
    }
}