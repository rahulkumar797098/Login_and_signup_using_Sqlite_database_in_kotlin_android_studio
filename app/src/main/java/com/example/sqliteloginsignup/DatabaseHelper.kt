package com.example.sqliteloginsignup

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(private val context: Context):
    SQLiteOpenHelper(context , DATABASE_NAME ,null , DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "UserDatabase.db"    // database name
        private const val DATABASE_VERSION = 1                 // database version
        private const val TABLE_NAME = "data"                  // table name
        private const val COLUMN_ID = "id"                     // column id
        private const val COLUMN_USERNAME = "username"         // column username
        private const val COLUMN_PASSWORD = "password"         // column password
    }

    // override ::::: create table using query
    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = ("CREATE TABLE $TABLE_NAME ( " +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT ," +
                "$COLUMN_USERNAME TEXT , " +
                "$COLUMN_PASSWORD TEXT )")
        db?.execSQL(createTableQuery)
    }

    // override :::::: this is used for database version is upgrade
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion : Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }

    ///////////// ..................... FUNCTION ........................ ///////////////

    fun insertUser(username: String , password : String ): Long {
        val values = ContentValues().apply {
            put(COLUMN_USERNAME , username)
            put(COLUMN_PASSWORD,password)
        }
        val db = writableDatabase      // data modification
        return db.insert(TABLE_NAME , null , values)

    }

    ////// read databse

    fun readUser(username : String , password: String):Boolean {
        val db = readableDatabase
        val selection = "$COLUMN_USERNAME = ? AND $COLUMN_PASSWORD = ? "
        val selectionArgs = arrayOf(username , password)
        val cursor = db.query(TABLE_NAME , null,selection , selectionArgs, null , null,null)
        val userExists = cursor.count > 0
        cursor.close()
        return userExists

    }
}