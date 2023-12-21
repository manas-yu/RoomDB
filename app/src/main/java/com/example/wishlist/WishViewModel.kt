package com.example.wishlist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wishlist.Data.Wish
import com.example.wishlist.Data.WishRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class WishViewModel(private val wishRepository: WishRepository = Graph.wishRepository) :
    ViewModel() {
    var wishTitleState by mutableStateOf("")
    var wishDescriptionState by mutableStateOf("")
    fun wishTitleChange(newString: String) {
        wishTitleState = newString
    }

    fun wishDescriptionChange(newString: String) {
        wishDescriptionState = newString
    }

    lateinit var getAllWishes: Flow<List<Wish>>

    init {
        viewModelScope.launch {
            getAllWishes = wishRepository.getAllWishes()
        }

    }

    fun getWishByID(id: Long): Flow<Wish> {
        return wishRepository.getWishByID(id)
    }

    fun addWish(wish: Wish) {
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.addAWish(wish)
        }

    }

    fun updateWish(wish: Wish) {
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.updateWish(wish)
        }

    }

    fun deleteWish(wish: Wish) {
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.deleteWish(wish)
        }

    }
}