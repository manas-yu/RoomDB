package com.example.wishlist

import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismiss

import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wishlist.Data.Wish

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(navController: NavController, viewModel: WishViewModel) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            AppBarView(title = "WishList") {
                Toast.makeText(context, "navButton", Toast.LENGTH_LONG).show()
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
//                    Toast.makeText(context, "FAButton", Toast.LENGTH_LONG).show()
                    navController.navigate(Screen.AddScreen.route + "/0L")
                },
                contentColor = Color.White,
                containerColor = Color.Black
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    ) {
        val wishList = viewModel.getAllWishes.collectAsState(initial = listOf())
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            items(wishList.value, key = { it.id }) { wish ->
//                val dismissedState = rememberDismissState(
//                    confirmValueChange = {
//                        if (it == DismissValue.DismissedToEnd || it == DismissValue.DismissedToStart) {
//                            viewModel.deleteWish(wish = wish)
//                        }
//                        true
//                    }
//                )
//                SwipeToDismiss(
//
//                    state = dismissedState,
//
//                    background = {
//                        val color by animateColorAsState(
//                            targetValue = if (dismissedState.dismissDirection == DismissDirection.EndToStart
//                            ) {
//                                Color.Red
//                            } else {
//                                Color.Transparent
//                            }, label = ""
//                        )
//                        Box(
//                            modifier = Modifier
//                                .fillMaxSize()
//                                .padding(horizontal = 20.dp),
//                            contentAlignment = Alignment.CenterEnd
//
//                        ) {
//                            androidx.compose.material.Icon(
//                                imageVector = Icons.Default.Delete,
//                                contentDescription = null,
//                                tint = Color.White
//                            )
//                        }
//                    },
//                    directions = setOf(DismissDirection.EndToStart, DismissDirection.StartToEnd),
//                    dismissContent = {
//
//                    }
//                )
                WishItem(wish = wish) {
                    navController.navigate(Screen.AddScreen.route + "/${wish.id}")
                }
            }

        }
    }
}

@Composable
fun WishItem(wish: Wish, onClick: () -> Unit) {
    Card(

        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp, top = 8.dp)
            .fillMaxSize()
            .clickable {
                onClick()
            },
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp,
        ),

        ) {
        Column(Modifier.padding(16.dp)) {
            Text(text = wish.title, fontWeight = FontWeight.ExtraBold)
            Text(text = wish.description)
        }
    }
}
