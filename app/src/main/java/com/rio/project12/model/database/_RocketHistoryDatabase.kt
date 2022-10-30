package com.rio.project12.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rio.project12.model.database.DRocketHistory

@Database(entities = [DRocketHistory::class], version = 1, exportSchema = false)
abstract class RocketHistoryDatabase: RoomDatabase() {
    /**
     * abstract method to get the instance of the dao interface
     */
    abstract fun getHistoryDao() : IRocketHistoryDAO

    /**
     * static block
     */
    companion object{
        /**
         * volatile variable to store the instance of the database [RocketHistoryDatabase]
         */
        @Volatile
        private var INSTANCE:RocketHistoryDatabase? = null

        /**
         * method to generate the instance of the database class (single ton object)
         */
        fun getINSTANCE(context: Context):RocketHistoryDatabase{
            synchronized(this) {
                /*
             * using kotlin smart cast for the control flow statement
             */
                var instance = INSTANCE

                if (instance == null) {
                    // creating the instance for the room database
                    instance = Room.databaseBuilder(
                        context,
                        RocketHistoryDatabase::class.java,
                        "rocket_database"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}