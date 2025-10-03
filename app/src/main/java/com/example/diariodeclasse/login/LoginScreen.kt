package com.example.diariodeclasse.login

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.diariodeclasse.login.cadastro.viewModel

@Composable
fun LoginScreen(loginViewModel: LoginViewModel = viewModel()) {
    val loginUIState by loginViewModel.uiState.collectAsState()

    Column {
        CampoTextoLoginSenha(
            value = loginViewModel.login,
            onValueChange = { loginViewModel.mudarTextoLogin(it) },
            isError = loginUIState.errouLoginOuSenha,
            label = loginUIState.labelLogin
        )
        CampoTextoLoginSenha(
            value = loginViewModel.senha,
            onValueChange = { loginViewModel.mudarTextoSenha(it) },
            isError = loginUIState.errouLoginOuSenha,
            label = loginUIState.labelSenha
        )
        BotaoLogar(
            onClick = {
                loginViewModel.logar()
            }
        )
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
    LoginScreen()
}