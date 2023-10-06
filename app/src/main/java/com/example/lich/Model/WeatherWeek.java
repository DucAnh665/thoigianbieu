package com.example.lich.Model;


import java.io.Serializable;
import java.util.List;

public class WeatherWeek {
    /**
     * lat : 20.993
     * lon : 105.7779
     * timezone : Asia/Bangkok
     * timezone_offset : 25200
     * current : {"dt":1617701404,"sunrise":1617662740,"sunset":1617707558,"temp":302.15,"feels_like":303.88,"pressure":1012,"humidity":58,"dew_point":293.05,"uvi":0.17,"clouds":20,"visibility":10000,"wind_speed":6.17,"wind_deg":130,"weather":[{"id":801,"main":"Clouds","description":"few clouds","icon":"02d"}]}
     * daily : [{"dt":1617681600,"sunrise":1617662740,"sunset":1617707558,"temp":{"day":301.92,"min":295.14,"max":302.71,"night":296.04,"eve":301.73,"morn":295.45},"feels_like":{"day":304.09,"night":296.09,"eve":303.63,"morn":296.09},"pressure":1015,"humidity":62,"dew_point":294.05,"wind_speed":5.12,"wind_deg":137,"weather":[{"id":500,"main":"Rain","description":"light rain","icon":"10d"}],"clouds":68,"pop":1,"rain":5.65,"uvi":11.35},{"dt":1617768000,"sunrise":1617749087,"sunset":1617793976,"temp":{"day":297.53,"min":295.46,"max":300.54,"night":296.32,"eve":300.45,"morn":295.73},"feels_like":{"day":298.16,"night":296.37,"eve":302.33,"morn":296.37},"pressure":1014,"humidity":82,"dew_point":294.4,"wind_speed":0.62,"wind_deg":214,"weather":[{"id":500,"main":"Rain","description":"light rain","icon":"10d"}],"clouds":100,"pop":0.71,"rain":3.61,"uvi":5.86},{"dt":1617854400,"sunrise":1617835435,"sunset":1617880394,"temp":{"day":301.82,"min":294.99,"max":304.26,"night":295.61,"eve":300.99,"morn":294.99},"feels_like":{"day":304.22,"night":295.71,"eve":303.15,"morn":295.71},"pressure":1011,"humidity":64,"dew_point":294.36,"wind_speed":1.73,"wind_deg":161,"weather":[{"id":501,"main":"Rain","description":"moderate rain","icon":"10d"}],"clouds":77,"pop":0.99,"rain":16.35,"uvi":10.99},{"dt":1617940800,"sunrise":1617921783,"sunset":1617966813,"temp":{"day":295.42,"min":294.46,"max":298.22,"night":294.96,"eve":298.22,"morn":294.46},"feels_like":{"day":295.77,"night":294.92,"eve":298.64,"morn":294.92},"pressure":1016,"humidity":79,"dew_point":291.83,"wind_speed":2.03,"wind_deg":28,"weather":[{"id":501,"main":"Rain","description":"moderate rain","icon":"10d"}],"clouds":100,"pop":1,"rain":5.62,"uvi":4.74},{"dt":1618027200,"sunrise":1618008132,"sunset":1618053231,"temp":{"day":296.71,"min":294.52,"max":300.99,"night":295.54,"eve":300.99,"morn":294.52},"feels_like":{"day":297.31,"night":295.09,"eve":303.15,"morn":295.09},"pressure":1017,"humidity":84,"dew_point":293.98,"wind_speed":3.33,"wind_deg":123,"weather":[{"id":500,"main":"Rain","description":"light rain","icon":"10d"}],"clouds":100,"pop":0.94,"rain":3.98,"uvi":9.17},{"dt":1618113600,"sunrise":1618094481,"sunset":1618139650,"temp":{"day":301.59,"min":295.09,"max":303.79,"night":296.95,"eve":301.72,"morn":295.09},"feels_like":{"day":303.81,"night":295.77,"eve":303.75,"morn":295.77},"pressure":1016,"humidity":64,"dew_point":294.26,"wind_speed":5.24,"wind_deg":164,"weather":[{"id":500,"main":"Rain","description":"light rain","icon":"10d"}],"clouds":90,"pop":0.57,"rain":0.47,"uvi":10},{"dt":1618200000,"sunrise":1618180830,"sunset":1618226069,"temp":{"day":302.4,"min":295.74,"max":303.09,"night":297.18,"eve":303.09,"morn":295.74},"feels_like":{"day":304.78,"night":296.54,"eve":305.87,"morn":296.54},"pressure":1014,"humidity":61,"dew_point":294.18,"wind_speed":5.91,"wind_deg":173,"weather":[{"id":500,"main":"Rain","description":"light rain","icon":"10d"}],"clouds":43,"pop":0.29,"rain":0.15,"uvi":10},{"dt":1618286400,"sunrise":1618267180,"sunset":1618312488,"temp":{"day":299.39,"min":296.03,"max":301.58,"night":297.28,"eve":300.81,"morn":296.03},"feels_like":{"day":299.39,"night":296.85,"eve":303.29,"morn":296.85},"pressure":1013,"humidity":79,"dew_point":295.66,"wind_speed":3.16,"wind_deg":130,"weather":[{"id":500,"main":"Rain","description":"light rain","icon":"10d"}],"clouds":100,"pop":0.71,"rain":1.47,"uvi":10}]
     */

    private double lat;
    private double lon;
    private String timezone;
    private int timezone_offset;
    private CurrentBean current;
    private List<DailyBean> daily;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public int getTimezone_offset() {
        return timezone_offset;
    }

    public void setTimezone_offset(int timezone_offset) {
        this.timezone_offset = timezone_offset;
    }

    public CurrentBean getCurrent() {
        return current;
    }

    public void setCurrent(CurrentBean current) {
        this.current = current;
    }

    public List<DailyBean> getDaily() {
        return daily;
    }

    public void setDaily(List<DailyBean> daily) {
        this.daily = daily;
    }


    @Override
    public String toString() {
        return "WeatherWeek{" +
                "lat=" + lat +
                ", lon=" + lon +
                ", timezone='" + timezone + '\'' +
                ", timezone_offset=" + timezone_offset +
                ", current=" + current +
                ", daily=" + daily +
                '}';
    }

    public static class CurrentBean implements Serializable {
        /**
         * dt : 1617701404
         * sunrise : 1617662740
         * sunset : 1617707558
         * temp : 302.15
         * feels_like : 303.88
         * pressure : 1012
         * humidity : 58
         * dew_point : 293.05
         * uvi : 0.17
         * clouds : 20
         * visibility : 10000
         * wind_speed : 6.17
         * wind_deg : 130
         * weather : [{"id":801,"main":"Clouds","description":"few clouds","icon":"02d"}]
         */

        private int dt;
        private int sunrise;
        private int sunset;
        private double temp;
        private double feels_like;
        private int pressure;
        private int humidity;
        private double dew_point;
        private double uvi;
        private int clouds;
        private int visibility;
        private double wind_speed;
        private int wind_deg;
        private List<WeatherBean> weather;

        public int getDt() {
            return dt;
        }

        public void setDt(int dt) {
            this.dt = dt;
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

        public double getTemp() {
            return temp;
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

        public int getPressure() {
            return pressure;
        }

        public void setPressure(int pressure) {
            this.pressure = pressure;
        }

        public int getHumidity() {
            return humidity;
        }

        public void setHumidity(int humidity) {
            this.humidity = humidity;
        }

        public double getDew_point() {
            return dew_point;
        }

        public void setDew_point(double dew_point) {
            this.dew_point = dew_point;
        }

        public double getUvi() {
            return uvi;
        }

        public void setUvi(double uvi) {
            this.uvi = uvi;
        }

        public int getClouds() {
            return clouds;
        }

        public void setClouds(int clouds) {
            this.clouds = clouds;
        }

        public int getVisibility() {
            return visibility;
        }

        public void setVisibility(int visibility) {
            this.visibility = visibility;
        }

        public double getWind_speed() {
            return wind_speed;
        }

        public void setWind_speed(double wind_speed) {
            this.wind_speed = wind_speed;
        }

        public int getWind_deg() {
            return wind_deg;
        }

        public void setWind_deg(int wind_deg) {
            this.wind_deg = wind_deg;
        }

        public List<WeatherBean> getWeather() {
            return weather;
        }

        public void setWeather(List<WeatherBean> weather) {
            this.weather = weather;
        }


        @Override
        public String toString() {
            return "CurrentBean{" +
                    "dt=" + dt +
                    ", sunrise=" + sunrise +
                    ", sunset=" + sunset +
                    ", temp=" + temp +
                    ", feels_like=" + feels_like +
                    ", pressure=" + pressure +
                    ", humidity=" + humidity +
                    ", dew_point=" + dew_point +
                    ", uvi=" + uvi +
                    ", clouds=" + clouds +
                    ", visibility=" + visibility +
                    ", wind_speed=" + wind_speed +
                    ", wind_deg=" + wind_deg +
                    ", weather=" + weather +
                    '}';
        }

        public static class WeatherBean implements Serializable {
            /**
             * id : 801
             * main : Clouds
             * description : few clouds
             * icon : 02d
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
                return "WeatherBean{" +
                        "id=" + id +
                        ", main='" + main + '\'' +
                        ", description='" + description + '\'' +
                        ", icon='" + icon + '\'' +
                        '}';
            }
        }
    }

    public static class DailyBean implements Serializable {
        /**
         * dt : 1617681600
         * sunrise : 1617662740
         * sunset : 1617707558
         * temp : {"day":301.92,"min":295.14,"max":302.71,"night":296.04,"eve":301.73,"morn":295.45}
         * feels_like : {"day":304.09,"night":296.09,"eve":303.63,"morn":296.09}
         * pressure : 1015
         * humidity : 62
         * dew_point : 294.05
         * wind_speed : 5.12
         * wind_deg : 137
         * weather : [{"id":500,"main":"Rain","description":"light rain","icon":"10d"}]
         * clouds : 68
         * pop : 1
         * rain : 5.65
         * uvi : 11.35
         */

        private double dt;
        private double sunrise;
        private double sunset;
        private TempBean temp;
        private FeelsLikeBean feels_like;
        private int pressure;
        private int humidity;
        private double dew_point;
        private double wind_speed;
        private double wind_deg;
        private double clouds;
        private double pop;
        private double rain;
        private double uvi;
        private List<WeatherBeanX> weather;

        public double getDt() {
            return dt;
        }

        public void setDt(int dt) {
            this.dt = dt;
        }

        public double getSunrise() {
            return sunrise;
        }

        public void setSunrise(int sunrise) {
            this.sunrise = sunrise;
        }

        public double getSunset() {
            return sunset;
        }

        public void setSunset(int sunset) {
            this.sunset = sunset;
        }

        public TempBean getTemp() {
            return temp;
        }

        public void setTemp(TempBean temp) {
            this.temp = temp;
        }

        public FeelsLikeBean getFeels_like() {
            return feels_like;
        }

        public void setFeels_like(FeelsLikeBean feels_like) {
            this.feels_like = feels_like;
        }

        public int getPressure() {
            return pressure;
        }

        public void setPressure(int pressure) {
            this.pressure = pressure;
        }

        public int getHumidity() {
            return humidity;
        }

        public void setHumidity(int humidity) {
            this.humidity = humidity;
        }

        public double getDew_point() {
            return dew_point;
        }

        public void setDew_point(double dew_point) {
            this.dew_point = dew_point;
        }

        public double getWind_speed() {
            return wind_speed;
        }

        public void setWind_speed(double wind_speed) {
            this.wind_speed = wind_speed;
        }

        public double getWind_deg() {
            return wind_deg;
        }

        public void setWind_deg(int wind_deg) {
            this.wind_deg = wind_deg;
        }

        public double getClouds() {
            return clouds;
        }

        public void setClouds(int clouds) {
            this.clouds = clouds;
        }

        public double getPop() {
            return pop;
        }

        public void setPop(int pop) {
            this.pop = pop;
        }

        public double getRain() {
            return rain;
        }

        public void setRain(double rain) {
            this.rain = rain;
        }

        public double getUvi() {
            return uvi;
        }

        public void setUvi(double uvi) {
            this.uvi = uvi;
        }

        public List<WeatherBeanX> getWeather() {
            return weather;
        }

        public void setWeather(List<WeatherBeanX> weather) {
            this.weather = weather;
        }

        @Override
        public String toString() {
            return "DailyBean{" +
                    "dt=" + dt +
                    ", sunrise=" + sunrise +
                    ", sunset=" + sunset +
                    ", temp=" + temp +
                    ", feels_like=" + feels_like +
                    ", pressure=" + pressure +
                    ", humidity=" + humidity +
                    ", dew_point=" + dew_point +
                    ", wind_speed=" + wind_speed +
                    ", wind_deg=" + wind_deg +
                    ", clouds=" + clouds +
                    ", pop=" + pop +
                    ", rain=" + rain +
                    ", uvi=" + uvi +
                    ", weather=" + weather +
                    '}';
        }

        public static class TempBean implements Serializable {

            /**
             * day : 301.92
             * min : 295.14
             * max : 302.71
             * night : 296.04
             * eve : 301.73
             * morn : 295.45
             */

            private double day;
            private double min;
            private double max;
            private double night;
            private double eve;
            private double morn;

            public double getDay() {
                return day;
            }

            public void setDay(double day) {
                this.day = day;
            }

            public double getMin() {
                return min;
            }

            public void setMin(double min) {
                this.min = min;
            }

            public double getMax() {
                return max;
            }
            public int getTemperature(){
                return  (int)((int)(getMax() - 273.15) + (getMin() - 273.15)) / 2;
            }

            public void setMax(double max) {
                this.max = max;
            }

            public double getNight() {
                return night;
            }

            public void setNight(double night) {
                this.night = night;
            }

            public double getEve() {
                return eve;
            }

            public void setEve(double eve) {
                this.eve = eve;
            }

            public double getMorn() {
                return morn;
            }

            public void setMorn(double morn) {
                this.morn = morn;
            }

            @Override
            public String toString() {
                return "TempBean{" +
                        "day=" + day +
                        ", min=" + min +
                        ", max=" + max +
                        ", night=" + night +
                        ", eve=" + eve +
                        ", morn=" + morn +
                        '}';
            }
        }

        public static class FeelsLikeBean implements Serializable {
            /**
             * day : 304.09
             * night : 296.09
             * eve : 303.63
             * morn : 296.09
             */

            private double day;
            private double night;
            private double eve;
            private double morn;

            public double getDay() {
                return day;
            }

            public void setDay(double day) {
                this.day = day;
            }

            public double getNight() {
                return night;
            }

            public void setNight(double night) {
                this.night = night;
            }

            public double getEve() {
                return eve;
            }

            public void setEve(double eve) {
                this.eve = eve;
            }

            public double getMorn() {
                return morn;
            }

            public void setMorn(double morn) {
                this.morn = morn;
            }

            @Override
            public String toString() {
                return "FeelsLikeBean{" +
                        "day=" + day +
                        ", night=" + night +
                        ", eve=" + eve +
                        ", morn=" + morn +
                        '}';
            }
        }

        public static class WeatherBeanX implements Serializable {
            /**
             * id : 500
             * main : Rain
             * description : light rain
             * icon : 10d
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
                return "WeatherBeanX{" +
                        "id=" + id +
                        ", main='" + main + '\'' +
                        ", description='" + description + '\'' +
                        ", icon='" + icon + '\'' +
                        '}';
            }
        }
    }
}

