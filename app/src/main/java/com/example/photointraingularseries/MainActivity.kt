package com.example.photointraingularseries

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.photointraingularseries.ui.screen.DisplaySequencedImages
import com.example.photointraingularseries.ui.screen.ImagePickerApp
import com.example.photointraingularseries.ui.theme.PhotoInTraingularSeriesTheme
import com.example.photointraingularseries.viewmodel.ImagePickerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel: ImagePickerViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PhotoSeQuenceApp()
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    private fun PhotoSeQuenceApp() {
        val navController = rememberNavController()

        PhotoInTraingularSeriesTheme {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                    NavHost(
                        navController = navController,
                        startDestination = "uploadPic"
                    ) {
                        composable("uploadPic") {
                            ImagePickerApp(
                                viewModel, navController = navController
                            )
                        }
                        composable("generateSeQuence") {
                            DisplaySequencedImages(viewModel, navController)
                        }
                    }
                }
        }
    }
}

