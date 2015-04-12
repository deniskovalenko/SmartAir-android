package com.smartair.app.models.entities;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;
import android.util.Log;

import org.jetbrains.annotations.NotNull;

public class Device extends BaseEntity {
    private String deviceId;
    private String deviceName;
//    private int delay;
//    private int co2MinLevel;

    private int currentCO2;
    private int deltaCO2;

    private float currentTemperature;
    private float deltaTemperature;

    private float currentHumidity;
    private float deltaHumidity;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public int getCurrentCO2() {
        return currentCO2;
    }

    public void setCurrentCO2(int currentCO2) {
        this.currentCO2 = currentCO2;
    }

    public int getDeltaCO2() {
        return deltaCO2;
    }

    public void setDeltaCO2(int deltaCO2) {
        this.deltaCO2 = deltaCO2;
    }

    public float getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(float currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public float getDeltaTemperature() {
        return deltaTemperature;
    }

    public void setDeltaTemperature(float deltaTemperature) {
        this.deltaTemperature = deltaTemperature;
    }

    public float getCurrentHumidity() {
        return currentHumidity;
    }

    public void setCurrentHumidity(float currentHumidity) {
        this.currentHumidity = currentHumidity;
    }

    public float getDeltaHumidity() {
        return deltaHumidity;
    }

    public void setDeltaHumidity(float deltaHumidity) {
        this.deltaHumidity = deltaHumidity;
    }

    public ContentValues toCV() {
        ContentValues cv = new ContentValues();
        cv.put(Contract._ID, deviceId);
        cv.put(Contract.DEVICE_NAME, deviceName);
        cv.put(Contract.CURRENT_CO2, currentCO2);
        cv.put(Contract.DELTA_CO2, deltaCO2);
        cv.put(Contract.CURRENT_TEMPERATURE, currentTemperature);
        cv.put(Contract.DELTA_TEMPERATURE, deltaTemperature);
        cv.put(Contract.CURRENT_HUMIDITY, currentHumidity);
        cv.put(Contract.DELTA_HUMIDITY, deltaHumidity);
        return cv;
    }

    @NotNull
    public static Device fromCursor(Cursor cursor) {
        Device device = new Device();
        device.deviceId = cursor.getString(0);
        device.deviceName = cursor.getString(1);
        device.currentCO2 = cursor.getInt(2);
        device.deltaCO2 = cursor.getInt(3);

        device.currentTemperature = cursor.getFloat(4);
        device.deltaTemperature = cursor.getFloat(5);
        device.currentHumidity = cursor.getFloat(6);
        device.deltaHumidity = cursor.getFloat(7);

        return device;

    }

    public static String createTableQuery() {
        Log.d(Device.class.getSimpleName(), "create table");
        return String.format("create table %s (%s text primary key, %s text, %s integer, %s integer, %s real, %s real, %s real, %s real)",
                Contract.TABLE_NAME,
                Contract._ID,
                Contract.DEVICE_NAME,
                Contract.CURRENT_CO2,
                Contract.DELTA_CO2,
                Contract.CURRENT_TEMPERATURE,
                Contract.DELTA_TEMPERATURE,
                Contract.CURRENT_HUMIDITY,
                Contract.DELTA_HUMIDITY);
    }

    public static class Contract implements BaseColumns{
        public static String TABLE_NAME = "devices";

        public static String DEVICE_NAME = "deviceName";

        public static String CURRENT_CO2 = "currentCo2";
        public static String DELTA_CO2 = "deltaCO2";
        public static String CURRENT_TEMPERATURE = "currentTemperature";
        public static String DELTA_TEMPERATURE = "deltaTemperature";
        public static String CURRENT_HUMIDITY = "currentHumidity";
        public static String DELTA_HUMIDITY = "deltaHumidity";


    }
}
