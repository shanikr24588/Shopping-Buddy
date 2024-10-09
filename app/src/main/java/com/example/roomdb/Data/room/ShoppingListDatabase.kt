package com.example.roomdb.Data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.roomdb.Data.room.Models.Item
import com.example.roomdb.Data.room.Models.ShoppingList
import com.example.roomdb.Data.room.Models.Store
import com.example.roomdb.Data.room.converters.DateConverter

@TypeConverters(value = [DateConverter::class])
@Database(
    entities = [ShoppingList::class,Item::class,Store::class],
    version = 1,
    exportSchema = false
)
abstract class ShoppingListDatabase:RoomDatabase() {
    abstract fun listDao():ListDao
    abstract fun itemDao():ItemDao
    abstract fun storeDao():StoreDao

    companion object{
        @Volatile
        var INSTANCE:ShoppingListDatabase? = null
        fun getDatabase(context:Context):ShoppingListDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context,
                    ShoppingListDatabase::class.java,
                    "shopping_db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }

}