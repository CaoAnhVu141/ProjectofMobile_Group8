package com.example.projectofmobile_group8

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

class DatabaseHelper(private val context: Context) : SQLiteOpenHelper(context, DB_NAME, null, 1) {

    companion object {
        private const val DB_NAME =
            "Project_database.db" // Tên tệp cơ sở dữ liệu trong thư mục assets
        private const val DB_PATH =
            "/data/data/com.example.projectofmobile_group8/databases/" // Thay bằng package của bạn
    }

    private val databasePath: String
        get() = DB_PATH + DB_NAME

    fun copyDatabase() {
        try {
            val inputStream: InputStream = context.assets.open(DB_NAME) // Mở tệp trong assets
            val outputStream: OutputStream = FileOutputStream(databasePath)

            val buffer = ByteArray(1024)
            var length: Int
            while (inputStream.read(buffer).also { length = it } > 0) {
                outputStream.write(buffer, 0, length)
            }

            outputStream.flush()
            outputStream.close()
            inputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun checkDatabase(): Boolean {
        val dbFile = context.getDatabasePath(DB_NAME)
        return dbFile.exists()
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // Không cần viết logic ở đây vì bạn sao chép database từ assets
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Logic để xử lý nâng cấp database, nếu cần
    }

    fun openDatabase(): SQLiteDatabase {
        return SQLiteDatabase.openDatabase(databasePath, null, SQLiteDatabase.OPEN_READWRITE)
    }

    fun checkLogin(email: String, password: String): Boolean {
        val db = this.readableDatabase
        val query = "SELECT * FROM user WHERE email = ? AND password = ?"
        val cursor = db.rawQuery(query, arrayOf(email, password))
        val isValidLogin = cursor.count > 0
        cursor.close()
        db.close()
        return isValidLogin
    }

    // Hàm kiểm tra email trong cơ sở dữ liệu
    fun isEmailExists(email: String): Boolean {
        // đọc cơ sở dữ liệu
        val db = this.readableDatabase
        // truy vấn tìm đến email của người dùng
        val query = "SELECT * FROM user WHERE email = ?"
        // Thực hiện truy vấn trả về kết quả
        val cursor = db.rawQuery(query, arrayOf(email))
        // kiểm tra bản ghi có tồn tại hay không nếu có trả về true còn không có ngược lại false
        val exists = cursor.count > 0
        // đóng tn lại
        cursor.close()
        //đóng cơ sở dữ liệu lại
        db.close()
        //trả về bản ghi có email trùng khớp
        return exists
    }

    // Thêm vào DatabaseHelper
    fun updatePassword(email: String, newPassword: String): Boolean {
        val db = this.writableDatabase
        val query = "UPDATE user SET password = ? WHERE email = ?"
        val statement = db.compileStatement(query)
        statement.bindString(1, newPassword)
        statement.bindString(2, email)
        val rowsAffected = statement.executeUpdateDelete()
        db.close()
        return rowsAffected > 0 // Trả về true nếu có bản ghi được cập nhật
    }
}
