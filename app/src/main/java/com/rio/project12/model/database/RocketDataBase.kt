package com.rio.project12.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * [RocketDataBase] is an Abstract class that extends RoomDatabase
 *     > it creates the instance of the [RocketDatabase] for interacting with room database
 *     Class is abstract to leave unimplemented method as it is.
 *
 */
@Database(entities = [DRocketHistory::class], version = 1, exportSchema = false)
abstract class RocketDataBase : RoomDatabase() {
    /*
     * [getIRocketHistoryDAO] is an abstract method that returns object of [IRocketHistoryDAO] interface
     */
    abstract fun getIRocketHistoryDAO(): IRocketHistoryDAO

    companion object{
        @Volatile
        private var INSTANCE:RocketDataBase? = null

        /**
         * getInstance is a method that creates a singleton object of the interface [IRocketHistoryDAO]
         *  and returns it
         *  Things inside this method are synchronised as creating multi instance is not a singleton
         *  object which occurs during multi threading
         */
        fun getINSTANCE(context: Context): RocketDataBase{
            synchronized(this) {
                /*
                 * using kotlin smart cast for flow control statement
                 */
                var instance = INSTANCE
                if (instance == null) {
                    // creating instance of the RocketDatabase class
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        RocketDataBase::class.java,
                        "rocket_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
                INSTANCE = instance
                return instance
            }
        }
    }
}

