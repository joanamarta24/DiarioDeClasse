package com.example.diariodeclasse.data.repository

import androidx.browser.trusted.TokenStore
import com.example.diariodeclasse.data.remote.ApiService
import com.example.diariodeclasse.data.remote.LoginRequest
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val api: ApiService,
    private val store: TokenStore
){
    suspend fun login(email:String,senha: String):Boolean{
        val token = api.login(LoginRequest(email,senha)).accessToken
        store.saveToken(token)
        return token.isNotBlank()
    }
    suspend fun me() = api.me()

    suspend fun logout() = store.clearToken()

    fun tokenFlow() = store.token

}