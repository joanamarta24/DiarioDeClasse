package com.example.diariodeclasse

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.diariodeclasse.data.Aluno
import com.example.diariodeclasse.data.DataSource
import com.example.diariodeclasse.login.LoginScreen
import com.example.diariodeclasse.login.cadastro.CadastroScreen
import com.example.diariodeclasse.ui.theme.DiarioDeClasseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val TAG = "MainActivity"
        super.onCreate(savedInstanceState)
        Log.e(TAG, "onCreate Called")

        enableEdgeToEdge()
        setContent {
            DiarioDeClasseTheme {
                var showLogin by remember { mutableStateOf(true) }
                var showCadastro by remember { mutableStateOf(false) }
                var showMainApp by remember { mutableStateOf(false) }

                when {
                    showLogin -> {
                        LoginScreen(
                            onNavigateToCadastro = {
                                showLogin = false
                                showCadastro = true
                            },
                            onLoginSuccess = {
                                showLogin = false
                                showMainApp = true
                            }
                        )
                    }
                    showCadastro -> {
                        CadastroScreen(
                            onNavigateToLogin = {
                                showCadastro = false
                                showLogin = true
                            },
                            onCadastroSuccess = {
                                showCadastro = false
                                showMainApp = true
                            }
                        )
                    }
                    showMainApp -> {
                        MainAppScreen()
                    }
                }
            }
        }
    }

    @Composable
    fun MainAppScreen() {
        Scaffold(
            topBar = {
                DiarioDeClasseTopBar()
            }
        ) { innerPadding ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                ListaDeAlunos(
                    listaDeAlunos = DataSource().carregarAlunos()
                )
            }
        }
    }

    @Composable
    fun ListaDeAlunos(
        modifier: Modifier = Modifier,
        listaDeAlunos: List<Aluno>
    ) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(listaDeAlunos) { aluno ->
                CardAluno(
                    fotoAluno = aluno.foto,
                    nomeAluno = aluno.nome,
                    cursoAluno = aluno.curso
                )
            }
        }
    }

    @Composable
    fun CardAluno(
        @DrawableRes fotoAluno: Int,
        nomeAluno: String,
        cursoAluno: String
    ) {
        var expandir by remember { mutableStateOf(false) }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                ),
            shape = RoundedCornerShape(
                bottomStart = 20.dp,
                topEnd = 20.dp
            ),
            elevation = CardDefaults.cardElevation(5.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = fotoAluno),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                    )

                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 16.dp)
                    ) {
                        Text(
                            text = nomeAluno,
                            style = MaterialTheme.typography.headlineSmall
                        )
                        Text(
                            text = cursoAluno,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    IconButton(
                        onClick = { expandir = !expandir },
                        modifier = Modifier.wrapContentSize(Alignment.CenterEnd)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ExpandMore,
                            contentDescription = if (expandir) "Recolher" else "Expandir"
                        )
                    }
                }

                if (expandir) {
                    DetalhesAluno()
                }
            }
        }
    }

    @Composable
    fun DetalhesAluno() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Nota: 100",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Faltas: 20%",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }

    @OptIn(ExperimentalMaterial3Api @class)
        @Composable
        fun DiarioDeClasseTopBar(modifier: Modifier = Modifier) {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Diário de Classe",
                        style = MaterialTheme.typography.headlineMedium
                    )
                },
                modifier = modifier
            )
        }

        @Preview(showSystemUi = true)
        @Composable
        fun DiarioDeClassePreview() {
            DiarioDeClasseTheme {
                MainAppScreen()
            }
        }
    }
}