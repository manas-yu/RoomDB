package com.example.wishlist.Data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.wishlist.Data.Wish
import kotlinx.coroutines.flow.Flow

@Dao
abstract class WishDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun addAWish(wishEntity: Wish)

    @Query("select * from `wish-table`")
    abstract fun getAllWishes(): Flow<List<Wish>>

    @Update
    abstract suspend fun updateWish(wishEntity: Wish)

    @Delete
    abstract suspend fun deleteWish(wishEntity: Wish)

    @Query("select * from `wish-table` where id=:id")
    abstract fun getWishByID(id: Long): Flow<Wish>

}