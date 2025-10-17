package com.example.diariodeclasse.ui.theme.User

import android.net.Uri // Importe Uri
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
import coil.compose.rememberAsyncImagePainter // <--- IMPORTANTE: Importação para Coil

// Importe a função viewModel() correta
import androidx.lifecycle.viewmodel.compose.viewModel // <--- IMPORTANTE: Importação para ViewModel

@OptIn (ExperimentalMaterial3Api::class)
@Composable
fun AlunoRegistrationScreen (
    modifier: Modifier = Modifier, // Geralmente é bom ter um Modifier com valor padrão
    alunoViewModel: AlunoViewModel = viewModel() // Usa a função viewModel() do compose
){
    val uiState by alunoViewModel.uiState.collectAsState()

    // O LaunchedEffect com Unit só será executado uma vez
    // Certifique-se de que carregarAluno() realmente carrega os usuários
    // e atualiza uiState.users.
    LaunchedEffect(Unit) {
        alunoViewModel.carregarAluno() // Talvez "carregarAlunos()" ou "carregarListaDeAlunos()" seja mais descritivo.
    }

    val imagePickerLauncher = rememberLauncherForActivityResult( // Renomeado para clareza
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        // O uri pode ser nulo se o usuário cancelar a seleção
        uri?.let {
            alunoViewModel.onFotoChange(it)
        }
    }

    Column(
        modifier = modifier // Use o modifier passado como parâmetro
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Cadastro de Usuário", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        // Exibição da foto selecionada
        uiState.fotoUri?.let { uri -> // Garante que a URI não é nula
            Image(
                painter = rememberAsyncImagePainter(uri), // Use a URI aqui
                contentDescription = "Foto selecionada",
                modifier = Modifier
                    .size(120.dp)
                    .padding(8.dp)
            )
        }

        Button(onClick = { imagePickerLauncher.launch("image/*") }) { // Use o launcher corrigido
            Text("Selecionar Foto")
        }

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

            // Usando Column para uma lista vertical de usuários
            Column {
                uiState.users.forEach { user ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                    ) {
                        user.fotoUri?.let { uri -> // O objeto Aluno também deve ter um fotoUri: Uri?
                            Image(
                                painter = rememberAsyncImagePainter(uri),
                                contentDescription = "Foto do usuário",
                                modifier = Modifier.size(50.dp)
                            )
                        } ?: run {
                            Image(
                                painter = rememberAsyncImagePainter(android.R.drawable.ic_menu_gallery), // Exemplo de placeholder
                                contentDescription = "Sem foto",
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
}