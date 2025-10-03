package com.example.diariodeclasse.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = viewModel(),
    onNavigateToCadastro: () -> Unit,
    onLoginSuccess: () -> Unit
) {
    val loginUIState by loginViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Seus campos de texto de login e senha
        CampoTextoLoginSenha(
            value = loginViewModel.login,
            onValueChange = { loginViewModel.mudarTextoLogin(it) },
            isError = loginUIState.errouLoginOuSenha,
            label = loginUIState.labelLogin,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        CampoTextoLoginSenha(
            value = loginViewModel.senha,
            onValueChange = { loginViewModel.mudarTextoSenha(it) },
            isError = loginUIState.errouLoginOuSenha,
            label = loginUIState.labelSenha,
            modifier = Modifier.padding(vertical = 8.dp)
        )


        BotaoLogar(
            onClick = {
                loginViewModel.logar()
                if (loginUIState.loginSucesso) {
                    onLoginSuccess()
                }
            },
            modifier = Modifier.padding(vertical = 16.dp)
        )
        Button(
            onClick = onNavigateToCadastro,
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text(text = "Não tem uma conta? Cadastre-se")
        }
        if(loginUIState.loginSucesso)
            Text("Logoooou!!!!!")
    }
}

@Composable
fun CampoTextoLoginSenha(
    value: String = "",
    onValueChange: (String) -> Unit,
    label: String = "",
    isError: Boolean = false,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(text = label)
        },
        isError = isError,
        singleLine = true,
        modifier = modifier
    )
}

@Composable
fun BotaoLogar(
    onClick:()-> Unit,
    modifier: Modifier = Modifier
){
    Button(
        onClick = onClick,
        modifier = modifier
    ) {
        Text(
            text =  "Entrar"
        )
    }
}

@Composable
@Preview(showSystemUi = true)
fun PreviewLogin(){
    LoginScreen(
        onNavigateToCadastro = {},
        onLoginSuccess = {}
    )
}
