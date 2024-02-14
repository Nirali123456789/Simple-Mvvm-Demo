package com.example.myapplication.RoomDatabase

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.myapplication.models.Jokes
import com.example.myapplication.converters.Converters
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Jokes::class], version = 1,exportSchema = false)
@TypeConverters(Converters::class)
abstract class CallDatabase : RoomDatabase() {

    abstract val categorydao: CategoryDao


    companion object {
        @Volatile
        public var INSTANCE: CallDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope):CallDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CallDatabase::class.java,
                    "food_item_database"
                ).addCallback(FoodItemCallback(scope))
                    .build()

                INSTANCE = instance

                // return instance
                instance
            }
        }
    }
    private class FoodItemCallback(val scope: CoroutineScope):RoomDatabase.Callback(){
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

//            INSTANCE?.let { foodItemRoomDB ->
//                scope.launch {
//                    // if you want to populate database
//                    // when RoomDatabase is created
//                    // populate here
//                    foodItemRoomDB.categorydao.insert(Jokes("2","100f","",""))
//                }
//            }
        }
    }
}