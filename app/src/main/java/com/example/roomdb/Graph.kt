package com.example.roomdb

import android.content.Context
import com.example.roomdb.Data.room.ShoppingListDatabase
import com.example.roomdb.Repository.Repository

object Graph {
    lateinit var db:ShoppingListDatabase
        private set

    val repository by lazy {
        Repository(
            listDao = db.listDao(),
            storeDao = db.storeDao(),
            itemDao = db.itemDao()
        )
    }

    fun provide(context:Context){
        db = ShoppingListDatabase.getDatabase(context)
    }










}