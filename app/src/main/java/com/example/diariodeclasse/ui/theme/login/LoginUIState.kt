package com.example.diariodeclasse.ui.theme.login

data class LoginUIState (
    val errouLoginOuSenha: Boolean = false,
    val labelLogin:String = "Login",
    val labelSenha:String = "Senha",
    val loginSucesso: Boolean = false
)