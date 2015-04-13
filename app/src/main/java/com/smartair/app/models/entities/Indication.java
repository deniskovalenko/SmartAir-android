package com.smartair.app.models.entities;

import android.content.ContentValues;
import android.provider.BaseColumns;
import android.util.Log;

public class Indication {
    private String deviceId;
    private long date;
    private float temperature;
    private int co2;
    private float humidity;

    public Indication() {}

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public int getCo2() {
        return co2;
    }

    public void setCo2(int co2) {
        this.co2 = co2;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public ContentValues toCV() {
        ContentValues cv = new ContentValues();
        cv.put(Contract.DATE, date);
        cv.put(Contract.DEVICE_ID, deviceId);

        cv.put(Contract.CO2, co2);
        cv.put(Contract.TEMPERATURE, temperature);
        cv.put(Contract.HUMIDITY, humidity);
        return cv;
    }

    public static String createTableQuery() {
        Log.d(Indication.class.getSimpleName(), "create table");
        return String.format("create table %s (%s integer primary key autoincrement, %s integer, %s text, %s integer, %s real, %s real)",
                Contract.TABLE_NAME,
                Contract._ID,
                Contract.DATE,
                Contract.DEVICE_ID,
                Contract.CO2,
                Contract.TEMPERATURE,
                Contract.HUMIDITY);
    }

    public static class Contract implements BaseColumns {
        public static String TABLE_NAME = "indications";
        public static String DATE = "date";
        public static String DEVICE_ID = "deviceId";
        public static String CO2 = "co2";
        public static String TEMPERATURE = "temperature";
        public static String HUMIDITY = "humidity";
    }
}
