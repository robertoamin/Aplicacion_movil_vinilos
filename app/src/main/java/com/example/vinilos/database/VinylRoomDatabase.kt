package com.example.vinilos.database.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.vinilos.models.Album
import com.example.vinilos.models.Band
import com.example.vinilos.models.Collector


@Database(entities = [Album::class, Collector::class, Band::class], version = 1, exportSchema = false)
abstract class VinylRoomDatabase : RoomDatabase() {

    abstract fun albumsDao(): AlbumsDao
    abstract fun collectorsDao(): CollectorsDao
    abstract fun bandsDao(): BandsDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: VinylRoomDatabase? = null

        fun getDatabase(context: Context): VinylRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    VinylRoomDatabase::class.java,
                    "vinyls_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }


}
