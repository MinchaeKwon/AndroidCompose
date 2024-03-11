package com.example.inventory.data

import android.content.Context

interface AppContainer {
    val itemsRepository: ItemsRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    // ItemDao() 인스턴스를 OfflineItemsRepository 생성자에 전달
    // Context를 전달하는 InventoryDatabase 클래스에서 getDatabase()를 호출하여 DB 인스턴스를 인스턴스화하고
    // .itemDao()를 호출하여 Dao 인스턴스를 만듦
    override val itemsRepository: ItemsRepository by lazy {
        OfflineItemsRepository(InventoryDatabase.getDatabase(context).itemDao())
    }
}
