package com.example.photointraingularseries.ui.screen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.photointraingularseries.data.ImageInfo
import androidx.compose.material3.Text
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.net.toUri
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ImageItem(imageInfo: ImageInfo) {
    Row(modifier = Modifier.padding(8.dp)) {
        Text(text = "${imageInfo.index}.", modifier = Modifier.padding(end = 8.dp))
        GlideImage(
            imageModel = imageInfo.uri,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(8.dp))
        )
    }
}

@Preview
@Composable
fun ImageItemPreview() {
    ImageItem(
        ImageInfo(
            uri = "https://developer.android.com/images/brand/Android_Robot.png".toUri(),
            index = 1
        )
    )
}