package com.example.sisreapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.sisreapp.navigation.AppNavigation
import com.example.sisreapp.ui.theme.SISREAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SISREAppTheme {
                AppNavigation()
            }
        }
    }
}
