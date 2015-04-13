package com.smartair.app.components.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.smartair.app.constants.DatabaseConstants;
import com.smartair.app.models.entities.Device;
import com.smartair.app.models.entities.Indication;

public class DatabaseHelper extends SQLiteOpenHelper{
    private static DatabaseHelper instance;

    public static void init(Context context) {
        instance = new DatabaseHelper(context);
    }
    public static DatabaseHelper getInstance() {
        if (instance == null)
            throw new IllegalStateException("does not init");
        return instance;
    }

    private DatabaseHelper(Context context) {
        super(context, DatabaseConstants.DATABASE_NAME, null, DatabaseConstants.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Device.createTableQuery());
        db.execSQL(Indication.createTableQuery());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
