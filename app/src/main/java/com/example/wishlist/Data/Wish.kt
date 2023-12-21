package com.example.wishlist.Data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wish-table")
data class Wish(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name = "wish-title")
    val title: String,
    @ColumnInfo(name = "wish-desc")
    val description: String
)

object DummyData {
    val wishList = listOf<Wish>(
        Wish(title = "GoogleWatch", description = "Watch"),
        Wish(title = "GoogleWatch", description = "Watch"),
        Wish(title = "GoogleWatch", description = "Watch")
    )
}