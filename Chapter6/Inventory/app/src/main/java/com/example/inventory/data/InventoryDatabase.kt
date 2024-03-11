package com.example.inventory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class InventoryDatabase : RoomDatabase() {

    // 데이터베이스가 DAO에 관해 알 수 있도록 ItemDao를 반환하는 추상 함수를 클래스 본문 내에서 선언
    abstract fun itemDao(): ItemDao

    // 데이터베이스를 만들거나 가져오는 메서드에 대한 액세스를 허용하고 클래스 이름을 한정자로 사용하는 companion object를 정의
    companion object {
        // 데이터베이스에 관한 null을 허용하는 비공개 변수 Instance를 선언
        @Volatile
        private var Instance: InventoryDatabase? = null

        // 데이터베이스 빌더에 필요한 Context 매개변수를 사용하여 getDatabase() 메서드 정의
        fun getDatabase(context: Context): InventoryDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, InventoryDatabase::class.java, "item_database")
                    .build() // DB 인스턴스 만듦
                    .also { Instance = it } // 최근에 만들어진 db 인스턴스에 대한 참조를 유지
            }
        }
    }
}