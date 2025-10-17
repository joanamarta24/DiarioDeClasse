package com.example.diariodeclasse.ui.theme.User

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.diariodeclasse.data.Aluno
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AlunoViewModel (application: Application) : AndroidViewModel(application){
    private val dataSource = AlunoDataSource(application)
    private val _uiState = MutableStateFlow(AlunoUIState())

    val uiState: StateFlow<AlunoUIState> = )uiState

    fun carregarAluno(){
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            val alunos = dataSource.carregarArquivos()
            _uiState.update {
                it.copy(
                    users = alunos,
                    isLoading = false
                )
            }
        }
    }

    fun onNomeChange(newValue: String) {
        _uiState.update { it.copy(nome = newValue) }
    }

    fun onLoginChange(newValue: String) {
        _uiState.update { it.copy(login = newValue) }
    }

    fun onSenhaChange(newValue: String) {
        _uiState.update { it.copy(senha = newValue) }
    }

    fun onFotoChange(newUri: Uri?) {
        _uiState.update { it.copy(fotoUri = newUri) }
    }

    fun cadastrar() {
        val state = _uiState.value
        if (state.nome.isNotBlank() && state.login.isNotBlank() && state.senha.isNotBlank()) {
            val novoUsuario = Aluno(
                nome = state.nome,
                login = state.login,
                senha = state.senha,
                fotoUri = state.fotoUri
            )
            _uiState.update {
                it.copy(
                    users = it.users + novoUsuario,
                    nome = "",
                    login = "",
                    senha = "",
                    fotoUri = null
                )
            }
        }
    }
}