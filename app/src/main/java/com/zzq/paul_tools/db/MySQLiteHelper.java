package com.zzq.paul_tools.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * @author zhuzaiqing
 * @describe
 * @time 2019/8/3 18:35
 */
public class MySQLiteHelper extends SQLiteOpenHelper {
    private Context context;

    public MySQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table Book(id integer primary key autoincrement,name text,price real)");
        Toast.makeText(context, "创建完成", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
