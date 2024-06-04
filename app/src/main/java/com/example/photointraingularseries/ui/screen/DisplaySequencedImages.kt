package com.example.photointraingularseries.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.photointraingularseries.viewmodel.ImagePickerViewModel
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplaySequencedImages(viewModel: ImagePickerViewModel, navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Sequenced  Images ",
                textAlign = TextAlign.Center
            ) },
                navigationIcon = {
                    IconButton(onClick = {
                        viewModel.clearState()
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
                    }
                }
            )
        }
    )
    {
        var images = viewModel.images.collectAsState().value
        Log.i("Images ", images.toString())
        if (images.size == 2) {
            images.forEach() {
                viewModel.addImage(it)
            }
            val imageList = viewModel.sequencedImages.collectAsState().value
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it), // Add padding to the top
                verticalArrangement = Arrangement.spacedBy(8.dp), // Add spacing between rows
            )
            {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 128.dp), // Set the number of columns
                    contentPadding = PaddingValues(16.dp), // Add padding to the grid
                    verticalArrangement = Arrangement.spacedBy(8.dp), // Add spacing between rows
                    horizontalArrangement = Arrangement.spacedBy(8.dp) // Add spacing between columns
                ) {
                    items(imageList) { imageInfo ->
                        ImageItem(imageInfo)
                    }
                }
            }
        }
    }
}