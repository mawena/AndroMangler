package com.example.andromangler.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImagePainter
import coil.compose.ImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.google.accompanist.placeholder.placeholder

@Composable
fun ImageCard(imageUrl: String) {
    val model = ImageRequest.Builder(LocalContext.current)
        .data(imageUrl)
        .size(Size.ORIGINAL)
        .crossfade(true)
        .build()
    val painter = rememberAsyncImagePainter(model)
    Image(
        modifier = Modifier
            .fillMaxWidth(),
        painter = painter,
        contentDescription = null,
        contentScale = ContentScale.FillWidth,
    )
    if(painter.state is AsyncImagePainter.State.Loading){
        CircularProgressIndicator()
    }
}