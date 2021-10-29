package com.lamarrulla.mytiendita.data

import android.util.Log
import com.lamarrulla.mytiendita.api.repo.firebase.RepoLoginFirebase
import com.lamarrulla.mytiendita.data.model.LoggedInUser
import com.lamarrulla.mytiendita.data.model.request.LoginReq
import java.lang.Exception

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository(val repoLoginFirebase: RepoLoginFirebase) {

    private lateinit var loggedInUser:LoggedInUser;

    // in-memory cache of the loggedInUser object
    var user: LoggedInUser? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        user = null
    }

    fun logout() {
        user = null
        //dataSource.logout()
    }

    suspend fun register(username: String, password: String): Res<LoggedInUser>{
        lateinit var loggedInUser:LoggedInUser;
        val loginReq = LoginReq(
            user = username,
            password = password
        )

        val resultado = repoLoginFirebase.registerFirebase(loginReq)

        if(resultado is Res.Success){
            loggedInUser = LoggedInUser(
                userId = resultado.data .uid.toString(),
                displayName = resultado.data.displayName.toString()
            )
            setLoggedInUser(loggedInUser)
        }else{
            return Res.Error((resultado as Res.Error).exception)
        }

        return Res.Success(loggedInUser);
    }

    suspend fun login(username: String, password: String): Res<LoggedInUser> {
        // handle login
        //val result = dataSource.login(username, password)

        val loginReq = LoginReq(
            user = username,
            password = password
        )
        val resultado = repoLoginFirebase.loginFirebase(loginReq)

        if (resultado is Res.Success) {
            loggedInUser = LoggedInUser(
                userId = resultado.data.uid.toString(),
                displayName = resultado.data.displayName.toString()
            )
            setLoggedInUser(loggedInUser)
        }else{
            return Res.Error(Exception(resultado.toString()))
        }

        return Res.Success(loggedInUser)
    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}