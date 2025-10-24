package com.example.diariodeclasse.ui.theme.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreen(onLogout: () -> Unit, vm: HomeViewModel = hiltViewModel()) {
    val state by vm.state.collectAsState()

    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when {
            state.loading -> CircularProgressIndicator()
            state.error != null -> Text("Erro: ${state.error}")
            else -> Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Bem-vindo, ${state.nome}", style = MaterialTheme.typography.headlineMedium)
                Text(state.email)
                Spacer(Modifier.height(16.dp))
                Button(onClick = {
                    vm.logout()
                    onLogout()
                }) { Text("Sair") }
            }
        }
    }
}