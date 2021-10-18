package com.lamarrulla.mytiendita.ui.menu

import com.lamarrulla.mytiendita.data.model.response.LoginResp

data class LoginRespResult(
    val success: LoginResp? = null,
    val error: Int? = null
)
