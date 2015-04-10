package ua.ibreathe.models.entities;

import android.provider.BaseColumns;
import android.util.Log;

public class Indication {
    private String device_name;
    private String date;
    private float temperature;
    private float co2;
    private float humidity;

    public Indication() {}

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getCo2() {
        return co2;
    }

    public void setCo2(float co2) {
        this.co2 = co2;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public static String createTableQuery() {
        Log.d(Indication.class.getSimpleName(), "create table");
        return String.format("create table %s (%s text primary key, %s text)",
                Contract.TABLE_NAME, Contract._ID, Contract.DEVICE_NAME);
    }

    public static class Contract implements BaseColumns {
        public static String DEVICE_NAME = "deviceName";
        public static String TABLE_NAME = "indications";
        public static String TEMPERATURE = "temperature";
        public static String CO2 = "co2";
        public static String HUMIDITY = "humidity";
    }
}
