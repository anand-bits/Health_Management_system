package com.example.new_trial;

public class Health {
    private String spo2;
    private String temp;
    //private String fall;
    private Long Timestamp;

    public Health() {
    }

    public Health(String spo2, String temp, Long timestamp) {
        this.spo2 = spo2;
        this.temp = temp;
        this.Timestamp = timestamp;
    }

    public String getSpo2() {
        return spo2;
    }

    public String getTemp() {
        return temp;
    }

    public Long getTimestamp() {
        return Timestamp;
    }
// public String getFall() {
      //  return fall;
    //}
}
