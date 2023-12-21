package com.example.wishlist

import android.content.Context
import androidx.room.Room
import com.example.wishlist.Data.WishDatabase
import com.example.wishlist.Data.WishRepository

object Graph {
    lateinit var database: WishDatabase
    val wishRepository by lazy {
        WishRepository(wishDao = database.WishDao())
    }

    fun provide(context: Context) {
        database = Room.databaseBuilder(context, WishDatabase::class.java, "wishlist.db").build()
    }
}