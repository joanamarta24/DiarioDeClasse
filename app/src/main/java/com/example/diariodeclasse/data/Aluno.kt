package com.example.diariodeclasse.data

import android.net.Uri
import androidx.annotation.DrawableRes

data class Aluno(
    val nome: String,
    @DrawableRes val foto: Int,
    val curso: String,
    val login: String,
    val senha: String,
    val fotoUri: Uri? = null
)