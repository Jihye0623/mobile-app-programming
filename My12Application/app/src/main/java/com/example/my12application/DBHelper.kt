package com.example.my12application

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context:Context): SQLiteOpenHelper(context, "testdb", null, 1) {
    // 필수 구현 메소드
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table todo_tbl (_id integer primary key autoincrement, todo not null)")
        // 투두 테이블에는 2개의 필드 있음 => 기본키 id와 투두
    }

    // 필수 구현 메소드
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}

// 데이터 베이스에 보관하는 방법
//  new->kotiln class
