package com.example.diariodeclasse.Cadastro

data class CadastroUIState (
    val email: String ="",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessage : String? = null,
    val isCadastroSuccessful: Boolean = false

)