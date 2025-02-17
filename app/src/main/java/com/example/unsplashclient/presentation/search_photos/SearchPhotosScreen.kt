package com.example.unsplashclient.presentation.search_photos

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.unsplashclient.domain.model.Photo
import com.example.unsplashclient.presentation.ScreenRoute
import com.example.unsplashclient.presentation.search_photos.components.PhotoThumbnail
import com.example.unsplashclient.presentation.search_photos.components.SearchBar

@Composable
fun SearchPhotosScreen(
    navController: NavController,
    viewModel: SearchPhotosViewModel = hiltViewModel()
){
    val state = viewModel.state.value
    
    Scaffold (
        topBar = { SearchBar(
            searchText = viewModel.query,
            onSearchTextChanged = { viewModel.query = it },
            onDone = { viewModel.searchPhotos() })}
            ){ paddingValue ->
        Box(modifier = Modifier.fillMaxSize()){
            when {
                state.isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                !state.error.isNullOrBlank() -> {
                    Text(
                        text = state.error,
                        modifier = Modifier.align(Alignment.Center),
                        color = MaterialTheme.colors.error
                    )
                }
                else -> {
                    LazyColumn(modifier = Modifier.padding(paddingValue)){
                        items(state.photos){ photo ->
                            PhotoThumbnail(
                                photo = photo,
                                onClick = {
                                    navController.navigate(ScreenRoute.PhotoDetailScreen.route + "/${photo.photoId}")
                                }
                            )
                        }
                    }
                }
            }

        }

    }

}