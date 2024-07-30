package com.iot.matzip_project;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class West_activity extends AppCompatActivity {
    private MatZip_listView liView;

    private DBOpenHelper dbOpenHelper;
    public static final String TABLE_NAME = "MatZip";
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matzip_listview);
        dbOpenHelper = DBOpenHelper.getInstance(this);
        database = dbOpenHelper.getReadableDatabase();
        liView = (MatZip_listView) findViewById(R.id.listView);

        viewData(TABLE_NAME);

    }
    private void viewData(String tableName) {
        if(database != null) {

            MatZip_Adapter adapter = new MatZip_Adapter(this);
            String sql = "SELECT * FROM " + tableName + " WHERE sp_category = '서구'" + ";" ;
            Cursor cursor = database.rawQuery(sql, null);
            liView.setAdapter(adapter);
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); ++i) {
                String storeName = cursor.getString(1);
                String comment = cursor.getString(2);
                String sp_category = cursor.getString(3);
                String menu_category = cursor.getString(4);
                adapter.addStore(storeName, comment, sp_category, menu_category);
                cursor.moveToNext();
                Log.i("test", storeName + comment);
            }
            adapter.notifyDataSetChanged();
            cursor.close();
        }
    }
}