package com.example.blogapp.ui.screen

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.blogapp.viewmodel.BlogViewModel
import com.example.blogapp.model.BlogPost

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BlogScreen(viewModel: BlogViewModel) {
    val blogPosts by viewModel.blogPosts.observeAsState(emptyList())
    val isLoading by viewModel.loading.observeAsState(false)
    val errorMessage by viewModel.error.observeAsState(null)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                // Simple static text without ambiguity
                title = { Text("Blog Posts") }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            when {
                isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                errorMessage != null -> {
                    // Ensure errorMessage is non-null
                    Text(
                        text = errorMessage ?: "Unknown error occurred",
                        modifier = Modifier.align(Alignment.Center),
                        color = Color.Red,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        items(blogPosts) { blogPost ->
                            BlogItem(blogPost)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BlogItem(blogPost: BlogPost) {
    Column {
        // Ensure the title is non-null, providing a fallback if null
        Text(
            text = blogPost.title.rendered,
            style = MaterialTheme.typography.bodyMedium
        )

        // WebView for the blog content (assuming blogPost.link contains the blog URL)
        AndroidView(
            factory = { context ->
                WebView(context).apply {
                    webViewClient = WebViewClient()
                    settings.loadWithOverviewMode = true
                    settings.useWideViewPort = true
                }
            },
            update = {
                it.loadUrl(blogPost.link)
            }
        )
    }
}
