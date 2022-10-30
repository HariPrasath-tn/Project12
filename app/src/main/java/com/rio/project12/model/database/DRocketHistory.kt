package com.rio.project12.model.database
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rio.project12.model.network.rocket.DRocket


/**
 * Data class to mention the template of the table in the database to the ROOM
 *
 * @Entity(tableName = "tableName")
 *      Explanation:
 *          '@' is the notation to mention word coming followed by as the annotation to the ROOM
 *          'Entity' is the annotation to mention the table format to the ROOM by providing a data class
 *                  each parameter in the data class is column in the table
 *          '()' content inside the parenthesis are parameter that mention the property of the db table
 *          'tableName' is the keyword to mention the name of the table mentioning explicitly
 *                  by default the name of the table is the name of the data class
 *
 * data class class_name()
 *      Explanation:
 *          each parameter in the data class is column in table
 *          each has it own unique properties(dataType, defaultValue...)
 *
 *          primary properties such as primary key, unique, etc has to mentioned explicitly
 *          '@PrimaryKey' is the annotation to mention the variable of the data class as a primary key of the table
 *          'autoGeneration' it is the argument of the any property that will indicate the value to be auto incremented for every insertion (if it is assigned as 'true')
 *          '@ColumnInfo' is the annotation to mention each column's properties such as name, defaultValue,...
 *          ==> @ColumnInfo(name="test_name")
 *
 */
@Entity(tableName = "rocket_history")
data class DRocketHistory(
    @PrimaryKey
    val s_no:Int,
    val id:String?,
    val name:String?,
    val image:String?,
    val details:String?,
    val article:String?,
    val wiki:String?,
    val webCast:String?,
    val youtube_id:String?,
    val date_utc:String?,
    val success:Boolean?,
    val launchPadId:String?
)

/**
 * primary key value
 */
var count = 0;

/**
 * List<DRocket>.asDomainModel() is a method that maps the list value to the data class
 * [DRocket] of network with the [DRocketHistory] of database
 */
fun List<DRocket>.asDomainModel(): Array<DRocketHistory> {
    return map {
        DRocketHistory(
            s_no = ++count,
            id = it.id,
            name = it.name,
            image = it.links.patch.large,
            details = it.details,
            article = it.links.article,
            wiki = it.links.wikipedia,
            webCast = it.links.webcast,
            youtube_id = it.links.youtube_id,
            date_utc = it.date_utc,
            success = it.success,
            launchPadId = it.launchpad
        )
    }.toTypedArray()
}