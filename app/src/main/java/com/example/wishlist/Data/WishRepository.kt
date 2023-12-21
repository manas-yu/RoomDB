package com.example.wishlist.Data

import kotlinx.coroutines.flow.Flow


class WishRepository(private val wishDao: WishDao) {
    suspend fun addAWish(wish: Wish) {
        wishDao.addAWish(wish)
    }

    fun getAllWishes(): Flow<List<Wish>> {
        return wishDao.getAllWishes()
    }

    fun getWishByID(id: Long): Flow<Wish> = wishDao.getWishByID(id)
    suspend fun deleteWish(wish: Wish) {
        wishDao.deleteWish(wish)
    }

    suspend fun updateWish(wish: Wish) {
        wishDao.updateWish(wish)
    }


}