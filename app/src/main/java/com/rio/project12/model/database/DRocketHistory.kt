package com.rio.project12.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rocket_history_table")
data class DRocketHistory (
    @PrimaryKey
    var id:String,
    var name:String,
    var date:String
    )


/**
 * List<DRockHistory>.asDomainModel() is a method that maps the list value to the data class
 * [DRocketHistory]
 */
fun List<DRocketHistory>.asDomainModel(): List<DRocketHistory> {
    return map {
        DRocketHistory(
            id = it.id,
            name=it.name,
            date = it.id
        )
    }
}