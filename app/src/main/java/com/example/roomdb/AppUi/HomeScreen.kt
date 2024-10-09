package com.example.roomdb.AppUi

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roomdb.Data.room.ItemsWithStoreAndList
import com.example.roomdb.Data.room.Models.Item
import com.example.roomdb.Utils.Category

import com.example.roomdb.Utils.Utils
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Composable

fun HomeScreen(
    onNavigate:(Int) -> Unit
){
    val homeViewModel:HomeViewModel = viewModel()
    val homestate = homeViewModel.state
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {onNavigate.invoke(-1)}) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    ){padding ->

        LazyColumn(modifier = Modifier.padding(padding)){
            item{
                LazyRow{
                    items(Utils.category){category: Category ->
                        CategoryItem(
                            iconsRes = category.resId,
                            title = category.title,
                            selected = (homestate.category == category)
                        ) {
                            homeViewModel.onCategoryChange(category)
                        }

                    }

                }
            }
            items(homestate.items){
                ShoppingItems(item = it,
                    isChecked = it.item.isChecked,
                    onCheckedChange = homeViewModel::onItemCheckedChange
                ) {
                     onNavigate.invoke(it.item.id)
                }

            }
        }

    }
}

@Composable
fun CategoryItem(
    @DrawableRes iconsRes: Int,
    title:String,
    selected:Boolean,
    onItemClick:() -> Unit
){
    val interactionSource = remember { MutableInteractionSource() }


    Card(
        modifier = Modifier
            .padding(top = 8.dp, bottom = 8.dp, start = 8.dp)
            .selectable(
                selected = selected,
                interactionSource = interactionSource,
                indication = rememberRipple(),
                onClick = { onItemClick.invoke() }
            ),
        border = BorderStroke(
            1.dp,
            color = if (selected) MaterialTheme.colorScheme.primary.copy(.5f)
            else MaterialTheme.colorScheme.onSurface,

        )


    ){
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Icon(painter = painterResource(id = iconsRes),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }

}

@Composable
fun ShoppingItems(
    item: ItemsWithStoreAndList,
    isChecked: Boolean,
    onCheckedChange: (Item, Boolean) -> Unit,
    onItemClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onItemClick.invoke()
            }
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = item.item.itemName,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(text = item.store.storeName)
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = formatDate(item.item.date),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )

            }
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = "Qty: ${item.item.qty}",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.size(4.dp))
                Checkbox(
                    checked = isChecked,
                    onCheckedChange = {
                        onCheckedChange.invoke(item.item, it)
                    },
                )
            }
        }

    }
}

fun formatDate(date: Date): String =
    SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(date)