package ua.statisticco2app.models.entities;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;
import android.util.Log;

import ua.statisticco2app.components.database.DbEntity;

public class Device extends BaseEntity implements DbEntity<Device>{
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

    @Override
    public ContentValues toCV() {
        return null;
    }

    @Override
    public Device fromCursor(Cursor cursor) {
        return null;
    }

    public static String createTableQuery() {
        Log.d(Device.class.getSimpleName(), "create table");
        return String.format("create table %s (%s text primary key, %s text)", Contract.TABLE_NAME, Contract._ID, Contract.DEVICE_NAME);
    }

    public static class Contract implements BaseColumns{
        public static String DEVICE_NAME = "deviceName";
        public static String TABLE_NAME = "devices";
    }
}
