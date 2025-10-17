package com.example.diariodeclasse.ui.theme.User

import android.net.Uri
import com.example.diariodeclasse.data.Aluno

data class AlunoUIState(
    val nome: String = "",
    val login: String = "",
    val senha: String = "",
    val fotoUri: Uri? = null,
    val users: List<Aluno> = emptyList(),
    val isLoading: Boolean = false
)