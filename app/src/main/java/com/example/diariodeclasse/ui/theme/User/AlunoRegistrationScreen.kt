package com.example.diariodeclasse.ui.theme.User

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.diariodeclasse.login.cadastro.viewModel

@OptIn (ExperimentalMaterial3Api::class)
@Composable
fun AlunoRegistrationScreen (
    modifier: Modifier,
   alunoViewModel: AlunoViewModel = viewModel()
){
    val uiState by alunoViewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        alunoViewModel.carregarAluno()
    }
    val laucher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        alunoViewModel.onFotoChange(uri)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Cadastro de Usuário", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))



        OutlinedTextField(
            value = uiState.nome,
            onValueChange = { alunoViewModel.onNomeChange(it) },
            label = { Text("Nome") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = uiState.login,
            onValueChange = { alunoViewModel.onLoginChange(it) },
            label = { Text("Login") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = uiState.senha,
            onValueChange = { alunoViewModel.onSenhaChange(it) },
            label = { Text("Senha") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        uiState.fotoUri?.let {
            Image(
                painter = rememberAsyncImagePainter(it),
                contentDescription = "Foto selecionada",
                modifier = Modifier
                    .size(120.dp)
                    .padding(8.dp)
            )
        }

        Button(onClick = { launcher.launch("image/*") }) {
            Text("Selecionar Foto")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { alunoViewModel.cadastrar() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cadastrar")
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (uiState.isLoading) {
            CircularProgressIndicator()
            Spacer(modifier = Modifier.height(16.dp))
            Text("Carregando usuários...")
        } else {

            Text("Usuários cadastrados:", style = MaterialTheme.typography.titleMedium)

            uiState.users.forEach { user ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                ) {
                    user.fotoUri?.let {
                        Image(
                            painter = rememberAsyncImagePainter(it),
                            contentDescription = "Foto",
                            modifier = Modifier.size(50.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("${user.nome} - ${user.login}")
                }
            }
        }
    }
}

