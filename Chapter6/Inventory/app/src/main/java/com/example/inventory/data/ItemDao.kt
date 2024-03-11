package com.example.inventory.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    // suspend를 사용해 별도의 스레드에서 실행될 수 있게 함
    // 데이터베이스 작업을 실행하는 데는 시간이 오래 걸릴 수 있으므로 별도의 스레드에서 실행해야 함

    // 데이터 추가
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Item)

    // 데이터 수정
    @Update
    suspend fun update(item: Item)

    // 데이터 삭제
    @Delete
    suspend fun delete(item: Item)

    // Flow를 반환 유형으로 사용하면 데이터베이스의 데이터가 변경될 때마다 알림을 받게 됨
    // Room은 이 Flow를 자동으로 업데이트하므로 명시적으로 한 번만 데이터를 가져오면 됨
    @Query("SELECT * from items WHERE id = :id")
    fun getItem(id: Int): Flow<Item>

    @Query("SELECT * from items ORDER BY name ASC")
    fun getAllItems(): Flow<List<Item>>
}