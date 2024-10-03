package com.example.mylogin5b

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelperUsuario(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        const val DB_VERSION = 1
        const val DB_NAME = "dbServicios"
        const val TABLE_NAME = "usuarios"
        const val KEY_ID = "id"
        const val USER_LOGIN = "userLogin"
        const val USER_PASS = "userPass"
        const val USER_EMAIL = "userEmail"
        const val USER_NAME = "userNombre" // Cambiado a userNombre para que coincida con tu creación de tabla
    }

    // Variable para crear nuestra tabla
    private val SQL_CREATE_TABLE = """
        CREATE TABLE $TABLE_NAME (
            $KEY_ID INTEGER PRIMARY KEY AUTOINCREMENT,
            $USER_LOGIN TEXT,
            $USER_PASS TEXT,
            $USER_EMAIL TEXT,
            $USER_NAME TEXT
        )
    """.trimIndent()

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
}
