package com.example.photointraingularseries.ui.screen

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.photointraingularseries.util.rememberGalleryThumbnail
import com.example.photointraingularseries.viewmodel.ImagePickerViewModel
import com.skydoves.landscapist.glide.GlideImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImagePickerApp(
    viewModel: ImagePickerViewModel,
    navController: NavController
) {
    val images by viewModel.images.collectAsState()
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let { viewModel.addImage(uri) }
        }
    var listSize by remember {
        mutableStateOf("50")
    }
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    "Upload   Images ",
                   modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center
                )
            })
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = listSize,
                label = { Text("Enter List Size ") },
                onValueChange = { listSize = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(15.dp)
            ) {
                if (images.size > 0)
                    GlideImage(
                        imageModel = images[0],
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(192.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
                TextButtonWithIcon {
                    launcher.launch("image/*")
                }
            }
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (images.size > 1)
                    GlideImage(
                        imageModel = images[1],
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(192.dp)
                            .clip(RoundedCornerShape(8.dp))
                    )
                TextButtonWithIcon {
                    launcher.launch("image/*")
                }
            }
            Button(
                onClick = {
                    if (images.size.toInt() < 2)
                        return@Button
                    viewModel.generateSequencedImage(listSize.toInt())
                    navController.navigate("generateSeQuence")
                }
            )
            {
                Text(text = "Generate Sequence ")
            }
        }
    }
}

@Composable
fun TextButtonWithIcon(onClick: () -> Unit) {
    Button(
        onClick = { onClick.invoke() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = rememberGalleryThumbnail(),
                contentDescription = "Gallery Icon"
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text(text = "Select image ")
        }
    }
}




