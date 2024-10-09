package com.example.roomdb.Repository

import com.example.roomdb.Data.room.ItemDao
import com.example.roomdb.Data.room.ListDao
import com.example.roomdb.Data.room.Models.Item
import com.example.roomdb.Data.room.Models.ShoppingList
import com.example.roomdb.Data.room.Models.Store
import com.example.roomdb.Data.room.StoreDao

class Repository(
    private val listDao: ListDao,
    private val storeDao: StoreDao,
    private val itemDao: ItemDao,
) {
    val store = storeDao.getAllStores()
    val getItemsWithListAndStore = listDao.getItemsWithStoreAndList()

    fun getItemWithStoreAndList(id: Int) = listDao
        .getItemWithStoreAndListFilteredById(id)

    fun getItemWithStoreAndListFilteredById(id: Int) =
        listDao.getItemsWithStoreAndListFilteredById(id)

    suspend fun insertList(shoppingList: ShoppingList) {
        listDao.insertShoppingList(shoppingList)
    }

    suspend fun insertStore(store: Store) {
        storeDao.insert(store)
    }

    suspend fun insertItem(item: Item) {
        itemDao.insert(item)
    }

    suspend fun deleteItem(item: Item) {
        itemDao.delete(item)
    }

    suspend fun updateItem(item: Item) {
        itemDao.update(item)
    }


}