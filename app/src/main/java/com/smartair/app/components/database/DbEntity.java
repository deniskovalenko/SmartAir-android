package com.smartair.app.components.database;

import android.content.ContentValues;
import android.database.Cursor;

public interface DbEntity<T> {
    ContentValues toCV();
    T fromCursor(Cursor cursor);
}
