package com.example.cronoapp.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cronoapp.components.CircleButton
import com.example.cronoapp.components.FloatButton
import com.example.cronoapp.components.MainIconButton
import com.example.cronoapp.components.MainTitle
import com.example.cronoapp.components.formatTiempo
import com.example.cronoapp.viewModels.CronometroViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddView(navController: NavController, cronometroVM: CronometroViewModel){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { MainTitle(title = "bryan joto") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    MainIconButton(icon = Icons.Default.ArrowBack) {
                        navController.popBackStack()
                    }
                }
            )
        }
    ) {
        ContentAddView(it, navController, cronometroVM)
    }
}

@Composable
fun ContentAddView(it: PaddingValues, navController: NavController, cronometroVM: CronometroViewModel){
    val state = cronometroVM.state
    LaunchedEffect(state.cronometroActivo){
        cronometroVM.cronos()
    }

    Column(
        modifier = Modifier.padding(it)
            .padding(top = 30.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = formatTiempo(cronometroVM.tiempo),
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold
        )

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            // Iniciar
            CircleButton(
                icon = Icons.Default.PlayArrow,
                enabled = !state.cronometroActivo) {
                cronometroVM.iniciar()
            }

            // Pausar
            CircleButton(
                icon = Icons.Default.AccountBox,
                enabled = state.cronometroActivo) {
                cronometroVM.pausar()
            }

            // Detener
            CircleButton(
                icon = Icons.Default.Build,
                enabled = !state.cronometroActivo) {
                cronometroVM.detener()
            }

            // Mostrar/Guardar
            CircleButton(
                icon = Icons.Default.Call,
                enabled = state.showSaveButton) {
                cronometroVM.showTextField()
            }
        }
    }
}
