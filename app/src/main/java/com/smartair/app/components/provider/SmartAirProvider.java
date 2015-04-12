package com.smartair.app.components.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.smartair.app.components.database.DatabaseHelper;
import com.smartair.app.models.entities.Device;

public class SmartAirProvider extends ContentProvider {
    DatabaseHelper databaseHelper;

    // // Uri
    // authority
    static final String AUTHORITY = "com.smartair.app.authority";

    // path
    static final String CONTACT_PATH = "devices";

    // Общий Uri
    public static final Uri CONTACT_CONTENT_URI = Uri.parse("content://"
            + AUTHORITY + "/" + CONTACT_PATH);

    @Override
    public boolean onCreate() {
        //TODO : added to manifest.xml
        DatabaseHelper.init(getContext());
        databaseHelper = DatabaseHelper.getInstance();
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        return db.query(Device.Contract.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
