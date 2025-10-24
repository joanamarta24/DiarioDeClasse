package com.example.diariodeclasse.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.diariodeclasse.ui.theme.home.HomeScreen
import com.example.diariodeclasse.ui.theme.login.LoginScreen
import com.example.diariodeclasse.ui.theme.session.AuthState
import com.example.diariodeclasse.ui.theme.session.AuthStateViewModel

@Composable
fun AppNavGraph(nav: NavHostController) {
    val vm: AuthStateViewModel = hiltViewModel()
    val authState = vm.state.collectAsState().value

    NavHost(nav, startDestination = when (authState) {
        AuthState.Authenticated -> "home"
        else -> "login"
    }) {
        composable("login") {
            LoginScreen(onLoginSuccess = {
                nav.navigate("home") {
                    popUpTo("login") { inclusive = true }
                }
            })
        }
        composable("home") {
            HomeScreen(onLogout = {
                nav.navigate("login") {
                    popUpTo("home") { inclusive = true }
                }
            })
        }
    }
}