package ua.statisticco2app.models.entities;

public class Device {
    String deviceId;
    String name;
    String alias;

    public Device(String name, String alias) {
        this.name = name;
        this.alias = alias;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
