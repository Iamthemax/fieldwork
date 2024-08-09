package com.sipl.aamdarparivar.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sipl.aamdarparivar.database.dao.AreaDao
import com.sipl.aamdarparivar.database.dao.CasteDao
import com.sipl.aamdarparivar.database.dao.GenderDao
import com.sipl.aamdarparivar.database.dao.UsersDao
import com.sipl.aamdarparivar.database.entity.AreaItem
import com.sipl.aamdarparivar.database.entity.Caste
import com.sipl.aamdarparivar.database.entity.Gender
import com.sipl.aamdarparivar.database.entity.Users

@Database(entities = [Users::class,AreaItem::class,Gender::class, Caste::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UsersDao
    abstract fun areaDao(): AreaDao
    abstract fun casteDao(): CasteDao
    abstract fun genderDao(): GenderDao
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).addCallback(object : RoomDatabase.Callback(){
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                    }
                })
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}