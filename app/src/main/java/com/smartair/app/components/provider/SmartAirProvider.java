package com.smartair.app.components.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.smartair.app.components.database.DatabaseHelper;
import com.smartair.app.models.entities.Device;
import com.smartair.app.models.entities.Indication;

public class SmartAirProvider extends ContentProvider {
    DatabaseHelper databaseHelper;

    // authority
    static final String AUTHORITY = "com.smartair.app.authority";

    // path
    static final String DEVICES_PATH = "devices";
    static final String INDICATIONS_PATH = "indications";

    // Uri
    public static final Uri DEVICE_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + DEVICES_PATH);
    public static final Uri INDICATION_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + INDICATIONS_PATH);

    //code
    public static final int DEVICES_CODE = 0;
    public static final int INDICATIONS_CODE = 1;

    private UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    @Override
    public boolean onCreate() {
        //TODO : added to manifest.xml
        DatabaseHelper.init(getContext());
        databaseHelper = DatabaseHelper.getInstance();
        uriMatcher.addURI(AUTHORITY, DEVICES_PATH, DEVICES_CODE);
        uriMatcher.addURI(AUTHORITY, INDICATIONS_PATH, INDICATIONS_CODE);
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        switch (uriMatcher.match(uri)) {
            case DEVICES_CODE:
                return db.query(Device.Contract.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
            case INDICATIONS_CODE:
                return db.query(Indication.Contract.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
            default:
                throw new IllegalArgumentException("Uri not match");
        }

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
