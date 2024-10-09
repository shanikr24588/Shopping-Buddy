package com.example.roomdb.Utils

import androidx.annotation.DrawableRes
import com.example.roomdb.R



object Utils {
    val category = listOf(
        Category(title = "Drinks", resId = R.drawable.drinks, id = 0),
        Category(title = "Vegetable", resId = R.drawable.vegetables, id = 1),
        Category(title = "Fruits", resId = R.drawable.fruits, id = 2),
        Category(title = "Cleaning", resId = R.drawable.cleaning, id = 3),
        Category(title = "Electronic", resId = R.drawable.electronics, id = 4),
        Category(title = "None", resId =R.drawable.interested_24 ,id = 10001)
    )
}

data class Category(
    @DrawableRes val resId: Int = -1,
    val title: String = "",
    val id: Int = -1,
)

//data class Category(
//    @DrawableRes val resId: Int = -1,
//    val title: String = "",
//    val id: Int = -1,
//)