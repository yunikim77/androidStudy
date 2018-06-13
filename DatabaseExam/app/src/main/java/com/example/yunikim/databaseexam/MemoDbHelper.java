package com.example.yunikim.databaseexam;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MemoDbHelper extends SQLiteOpenHelper {
    private static MemoDbHelper sInstance;
    private static final int DB_VERSION = 2;
    private static final String DB_NAME = "Memo.db";
    private static final String SQL_CREATE_ENTRIES =
            String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s text)",
                    MemoContract.MemoEntry.TABLE_NAME,
                    MemoContract.MemoEntry._ID,
                    MemoContract.MemoEntry.COLUMN_NAME_TITLE,
                    MemoContract.MemoEntry.COLUMN_NAME_CONTENTS);
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + MemoContract.MemoEntry.TABLE_NAME;

    private MemoDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // DB 스키마가 변경될 때 여기서 데이터를 백업하고 테이블을 삭제한 후 재생성 및 데이터 복원 등을 한다.
        Log.d("로그로그", "여기에 들어왔습니다.");
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public static MemoDbHelper getsInstance(Context context) {
        if(sInstance == null) {
            sInstance = new MemoDbHelper(context.getApplicationContext());
        }
        return sInstance;
    }
}
