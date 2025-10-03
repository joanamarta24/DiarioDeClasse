package com.example.diariodeclasse.login.cadastro

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.diariodeclasse.Cadastro.CadastroViewModel

@Composable
fun CadastroScreen(
    onNavigateToLogin: () -> Unit,
    viewModel: CadastroViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Tela de Cadastro")

        TextField(
            value = uiState.email,
            onValueChange = { viewModel.onEmailChange(it) },
            label = { Text("Email") },
            modifier = Modifier.padding(vertical = 8.dp)
        )

        TextField(
            value = uiState.password,
            onValueChange = { viewModel.onPasswordChange(it) },
            label = { Text("Senha") },
            modifier = Modifier.Modifier.padding(vertical = 8.dp)
        )

        Button(
            onClick = {
                viewModel.cadastrarUsuario()
                // Ação de navegação após o cadastro (exemplo: voltar para login)
                onNavigateToLogin()
            },
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            Text(text = "Cadastrar")
        }

        Button(
            onClick = onNavigateToLogin,
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text(text = "Já tem uma conta? Voltar para Login")
        }
    }
}

fun viewModel() {
    TODO("Not yet implemented")
}
