package com.example.lich.Model;

public class CurrentInfo {
    private String time;
    private String shortDate;

    /**
     * using for screen protector and widget 1*1
     * */
    private String longDate;

    /**
     * using for widget 1*3
     * */
    private String shortLocation;

    /**
     * using for screen protector and widget 1*1
     * */
    private String longLocation;


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getShortDate() {
        return shortDate;
    }

    public void setShortDate(String shortDate) {
        this.shortDate = shortDate;
    }

    public String getLongDate() {
        return longDate;
    }

    public void setLongDate(String longDate) {
        this.longDate = longDate;
    }

    public String getShortLocation() {
        return shortLocation;
    }

    public void setShortLocation(String shortLocation) {
        this.shortLocation = shortLocation;
    }

    public String getLongLocation() {
        return longLocation;
    }

    public void setLongLocation(String longLocation) {
        this.longLocation = longLocation;
    }

    @Override
    public String toString() {
        return "CurrentInfo{" +
                "time='" + time + '\'' +
                ", shortDate='" + shortDate + '\'' +
                ", longDate='" + longDate + '\'' +
                ", shortLocation='" + shortLocation + '\'' +
                ", longLocation='" + longLocation + '\'' +
                '}';
    }
}
