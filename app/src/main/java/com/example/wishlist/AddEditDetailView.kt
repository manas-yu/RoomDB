package com.example.wishlist


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions


import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.TextField
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button

import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

import androidx.navigation.NavController
import com.example.wishlist.Data.Wish
import kotlinx.coroutines.launch

@Composable
fun AddEditDetailView(navController: NavController, id: Long, wishViewModel: WishViewModel) {
    val scaffoldState = rememberScaffoldState()
//    val snackMessage = remember { mutableStateOf("") }
    var snackMessage = ""
    val scope = rememberCoroutineScope()
    if (id != 0L) {
        val wish = wishViewModel.getWishByID(id).collectAsState(initial = Wish(0L, "", ""))
        wishViewModel.wishTitleState = wish.value.title
        wishViewModel.wishDescriptionState = wish.value.description
    } else {
        wishViewModel.wishTitleState = ""
        wishViewModel.wishDescriptionState = ""
    }
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppBarView(
                title =
                if (id == 0L)
                    stringResource(id = R.string.add_wish)
                else
                    stringResource(id = R.string.update_wish)
            ) {
                navController.navigateUp()
            }
        }
    ) {
        Column(
            Modifier
                .padding(it)
                .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            WishTextField(
                onValueChanged = {
                    wishViewModel.wishTitleChange(it)
                }, label = "Title", content = wishViewModel.wishTitleState
            )
            Spacer(modifier = Modifier.height(10.dp))
            WishTextField(
                onValueChanged = {
                    wishViewModel.wishDescriptionChange(it)
                }, label = "Description", content = wishViewModel.wishDescriptionState
            )
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = {
                if (wishViewModel.wishTitleState.isNotEmpty() && wishViewModel.wishDescriptionState.isNotEmpty()) {
                    if (id != 0L) {
                        wishViewModel.updateWish(
                            Wish(
                                id,
                                wishViewModel.wishTitleState.trim(),
                                wishViewModel.wishDescriptionState.trim()
                            )
                        )
                        snackMessage = "Wish Updated"
                        scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(snackMessage)
                            navController.navigateUp()
                        }

                    } else {
                        wishViewModel.addWish(
                            Wish(
                                title = wishViewModel.wishTitleState.trim(),
                                description = wishViewModel.wishDescriptionState.trim()
                            )
                        )
                        snackMessage = "Wish Added"
                        scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(snackMessage)
                            navController.navigateUp()
                        }

                    }
                } else {
                    snackMessage = "Enter All Fields"
                    scope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(snackMessage)
                    }
                }


            }) {
                Text(
                    text = if (id != 0L) {
                        stringResource(id = R.string.update_wish)
                    } else {
                        stringResource(id = R.string.add_wish)
                    }
                )
            }
        }
    }
}


@Composable
fun WishTextField(onValueChanged: (String) -> Unit, label: String, content: String) {
    TextField(value = content, onValueChange = onValueChanged, modifier = Modifier.fillMaxWidth())
}