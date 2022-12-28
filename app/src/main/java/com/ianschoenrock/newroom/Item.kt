package com.ianschoenrock.newroom

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_table")
class Item(

        @PrimaryKey
        @ColumnInfo(name = "item")
        val item: String,


    )
