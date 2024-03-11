package com.example.inventory.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items") // 테이블 이름 설정
data class Item(
    @PrimaryKey // 기본 키로 설정
    val id: Int = 0,
    val name: String,
    val price: Double,
    val quantity: Int
)
