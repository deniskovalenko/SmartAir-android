package ua.statisticco2app.models.entities;

public class Device extends BaseEntity {
    String device_id;
    String device_name;

    public Device() {}

    public Device(String name) {
        this.device_name = name;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }
}
