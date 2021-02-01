package gr.techzombie.noteapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder
import android.widget.Toast

class DbManager {
    val dbName = "database"
    val dbTable = "Notes"
    val columnId = "ID"
    val columnTitle = "Title"
    val columnDescription = "Description"
    val dbVersion = 1
    val sqlCreateTable =
        "CREATE TABLE IF NOT EXISTS " + dbTable + " (" + columnId + " INTEGER PRIMARY KEY, " + columnTitle + " TEXT, " + columnDescription + " TEXT);"
    var sqlDB: SQLiteDatabase? = null

    constructor(context: Context) {
        var db = DatabaseHelper(context)
        sqlDB=db.writableDatabase
    }

    inner class DatabaseHelper : SQLiteOpenHelper {
        var context: Context? = null

        constructor(context: Context) : super(context, dbName, null, dbVersion) {
            this.context = context
        }

        override fun onCreate(db: SQLiteDatabase?) {
            db!!.execSQL(sqlCreateTable)
            Toast.makeText(this.context,"Database created",Toast.LENGTH_LONG).show()
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db!!.execSQL("DROP TABLE IF EXISTS " +  dbTable )
        }


    }

    fun insertDB(values:ContentValues):Long {
        val ID = sqlDB!!.insert(dbTable,"",values)
        return ID
    }

    fun queryDB(projection:Array<String>,selection:String,selectionArgs:Array<String>,sortOrder:String):Cursor{
        val queryBuilder = SQLiteQueryBuilder()
        queryBuilder.tables = dbTable
        val cursor = queryBuilder.query(sqlDB,projection,selection,selectionArgs,null,null,sortOrder)
        return cursor
    }

    fun deleteDB(selection:String,selectionArgs:Array<String>):Int{
        val count = sqlDB!!.delete(dbTable,selection,selectionArgs)
        return count
    }

    fun updateDB(values:ContentValues,selection: String,selectionArgs: Array<String>):Int{
        val count = sqlDB!!.update(dbTable,values,selection,selectionArgs)
        return count
    }
}