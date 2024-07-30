package com.iot.matzip_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBOpenHelper extends SQLiteOpenHelper {
    private static DBOpenHelper instance;
    public static synchronized DBOpenHelper getInstance(Context context) {
        if(instance == null) {
            instance = new DBOpenHelper(context, "matzip_rec", null, 1);
        }
        return  instance;
    }

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "matzip_rec";
    private static final String TABLE_NAME = "MatZip";

    private static final String COLUMN_ID = "id";
    private static final String STORENAME = "storeName";
    private static final String COMMENT = "comment";
    private static final String SPINNER_CATEGORY = "sp_category";
    private static final String MENU_CATEGORY = "menu_category";
    private static final String MAINIMAGE = "mainImage";
    private static final String SOJUIMAGE = "sojuImage";
    private static final String BEERIMAGE = "beerImage";

    public static final String SQL_CREATE_MATZIP = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
            " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + STORENAME + " TEXT, "
            + COMMENT + " TEXT, " + SPINNER_CATEGORY + " TEXT, " + MENU_CATEGORY + " TEXT" + ");";

    private DBOpenHelper(@Nullable Context context, @Nullable String name,
                         @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_MATZIP);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion > 1) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME);
        }
    }
    public long insertData(String storeName, String comment, String sp_category, String menu_category) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SPINNER_CATEGORY, sp_category);
        values.put(MENU_CATEGORY, menu_category);
        values.put(STORENAME, storeName);
        values.put(COMMENT, comment);
        long id = db.insert(TABLE_NAME, null, values);
        db.close();
        return id;
    }
}
