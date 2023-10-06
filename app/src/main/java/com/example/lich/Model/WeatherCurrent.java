package com.example.lich.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WeatherCurrent {

    /**
     * coord : {"lon":139,"lat":35}
     * weather : [{"id":800,"main":"Clear","description":"clear sky","icon":"01n"}]
     * base : stations
     * main : {"temp":281.52,"feels_like":278.99,"temp_min":280.15,"temp_max":283.71,"pressure":1016,"humidity":93}
     * wind : {"speed":0.47,"deg":107.538}
     * clouds : {"all":2}
     * dt : 1560350192
     * sys : {"type":3,"id":2019346,"message":0.0065,"country":"JP","sunrise":1560281377,"sunset":1560333478}
     * timezone : 32400
     * id : 1851632
     * name : Shuzenji
     * cod : 200
     */

    private CoordBean coord;
    private String base;
    private MainBean main;
    private WindBean wind;
    private CloudsBean clouds;
    private int dt;
    private SysBean sys;
    private int timezone;
    private int id;
    private String name;
    private int cod;
    private double lastUpdate;
    private List<ListBeanCurrent> weather;

    public double getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(double lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public CoordBean getCoord() {
        return coord;
    }

    public void setCoord(CoordBean coord) {
        this.coord = coord;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public MainBean getMain() {
        return main;
    }

    public void setMain(MainBean main) {
        this.main = main;
    }

    public WindBean getWind() {
        return wind;
    }

    public void setWind(WindBean wind) {
        this.wind = wind;
    }

    public CloudsBean getClouds() {
        return clouds;
    }

    public void setClouds(CloudsBean clouds) {
        this.clouds = clouds;
    }

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public SysBean getSys() {
        return sys;
    }

    public void setSys(SysBean sys) {
        this.sys = sys;
    }

    public int getTimezone() {
        return timezone;
    }

    public void setTimezone(int timezone) {
        this.timezone = timezone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public List<ListBeanCurrent> getWeather() {
        return weather == null ? new ArrayList<>() : weather;
    }

    public void setWeather(List<ListBeanCurrent> weather) {
        this.weather = weather;
    }

    @Override
    public String toString() {
        return "WeatherCurrent{" +
                "coord=" + coord +
                ", base='" + base + '\'' +
                ", main=" + main +
                ", wind=" + wind +
                ", clouds=" + clouds +
                ", dt=" + dt +
                ", sys=" + sys +
                ", timezone=" + timezone +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", cod=" + cod +
                ", weather=" + weather +
                '}';
    }

    public static class CoordBean implements Serializable {
        /**
         * lon : 139
         * lat : 35
         */

        private double lon;
        private double lat;

        public double getLon() {
            return lon;
        }

        public void setLon(int lon) {
            this.lon = lon;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(int lat) {
            this.lat = lat;
        }

        @Override
        public String toString() {
            return "CoordBean{" +
                    "lon=" + lon +
                    ", lat=" + lat +
                    '}';
        }
    }

    public static class MainBean implements Serializable {
        /**
         * temp : 281.52
         * feels_like : 278.99
         * temp_min : 280.15
         * temp_max : 283.71
         * pressure : 1016
         * humidity : 93
         */

        private double temp;
        private double feels_like;
        private double temp_min;
        private double temp_max;
        private String pressure;
        private int humidity;

        public double getTemp() {
            return temp;
        }
        public int getActualTemp(){
            return (int) (temp-273.15);
        }

        public void setTemp(double temp) {
            this.temp = temp;
        }

        public double getFeels_like() {
            return feels_like;
        }

        public void setFeels_like(double feels_like) {
            this.feels_like = feels_like;
        }

        public double getTemp_min() {
            return temp_min;
        }

        public void setTemp_min(double temp_min) {
            this.temp_min = temp_min;
        }

        public double getTemp_max() {
            return temp_max;
        }

        public void setTemp_max(double temp_max) {
            this.temp_max = temp_max;
        }

        public String getPressure() {
            return pressure;
        }

        public void setPressure(String pressure) {
            this.pressure = pressure;
        }

        public int getHumidity() {
            return humidity;
        }

        public void setHumidity(int humidity) {
            this.humidity = humidity;
        }

        @Override
        public String toString() {
            return "MainBean{" +
                    "temp=" + temp +
                    ", feels_like=" + feels_like +
                    ", temp_min=" + temp_min +
                    ", temp_max=" + temp_max +
                    ", pressure='" + pressure + '\'' +
                    ", humidity=" + humidity +
                    '}';
        }
    }

    public static class WindBean implements Serializable {
        /**
         * speed : 0.47
         * deg : 107.538
         */

        private double speed;
        private double deg;

        public double getSpeed() {
            return speed;
        }

        public void setSpeed(double speed) {
            this.speed = speed;
        }

        public double getDeg() {
            return deg;
        }
        public double getWind(){
            return deg/10;
        }

        public void setDeg(double deg) {
            this.deg = deg;
        }

        @Override
        public String toString() {
            return "WindBean{" +
                    "speed=" + speed +
                    ", deg=" + deg +
                    '}';
        }
    }

    public static class CloudsBean implements Serializable {
        /**
         * all : 2
         */

        private int all;

        public int getAll() {
            return all;
        }

        public void setAll(int all) {
            this.all = all;
        }

        @Override
        public String toString() {
            return "CloudsBean{" +
                    "all=" + all +
                    '}';
        }
    }

    public static class SysBean implements Serializable {
        /**
         * type : 3
         * id : 2019346
         * message : 0.0065
         * country : JP
         * sunrise : 1560281377
         * sunset : 1560333478
         */

        private int type;
        private int id;
        private double message;
        private String country;
        private int sunrise;
        private int sunset;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public double getMessage() {
            return message;
        }

        public void setMessage(double message) {
            this.message = message;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public int getSunrise() {
            return sunrise;
        }

        public void setSunrise(int sunrise) {
            this.sunrise = sunrise;
        }

        public int getSunset() {
            return sunset;
        }

        public void setSunset(int sunset) {
            this.sunset = sunset;
        }


        @Override
        public String toString() {
            return "SysBean{" +
                    "type=" + type +
                    ", id=" + id +
                    ", message=" + message +
                    ", country='" + country + '\'' +
                    ", sunrise=" + sunrise +
                    ", sunset=" + sunset +
                    '}';
        }
    }

    public static class ListBeanCurrent implements Serializable {
        /**
         * id : 800
         * main : Clear
         * description : clear sky
         * icon : 01n
         */

        private int id;
        private String main;
        private String description;
        private String icon;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMain() {
            return main;
        }

        public void setMain(String main) {
            this.main = main;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        @Override
        public String toString() {
            return "ListBeanCurrent{" +
                    "id=" + id +
                    ", main='" + main + '\'' +
                    ", description='" + description + '\'' +
                    ", icon='" + icon + '\'' +
                    '}';
        }
    }
}

