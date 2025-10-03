package com.example.diariodeclasse.Cadastro

import android.R.attr.password
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CadastroViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CadastroUIState())
    val uiState: StateFlow<CadastroUIState> = _uiState.asStateFlow()

    fun onEmailChange(email: String){
        _uiState.update { it.copy(email = email) }
    }
    fun onPasswordChange(){
        _uiState.update { it.copy(password) }
    }
    fun cadastrarUsuario(){
        _uiState.update { it.copy(isLoading = true, erroMessage = null) }
        viewModelScope.launch {
            try {
                kotlinx.coroutines.delay(200)
                if(_uiState.value.email.isNotEmpty() && _uiState.value.password.isNotEmpty()){
                    _uiState.update { it.copy(isCadastroSuccessful =true,isLoading = false) }
                    println("Aluno ${_uiState.value.email} cadastrado  com sucesso!")
                }else{
                    _uiState.update { it.copy(errorMessage= "Emsil e senha não podem ser vazios",isLoading = false) }
                }
            }catch (e: Exception){
                _uiState.update { it.copy(errorMessage = e.message, isLoading = false) }
            }
        }
    }
}