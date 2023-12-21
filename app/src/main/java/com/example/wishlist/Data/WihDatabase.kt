package com.example.wishlist.Data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [Wish::class],
    exportSchema = false
)
abstract class WishDatabase : RoomDatabase() {
    abstract fun WishDao(): WishDao
}