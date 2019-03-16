package com.apps.wag.lunchbox;

public class Recipes {
    private int cod;
    private String title;
    private String duration;
    private String servings;
    private int keenOnCount;
    private int madeCount;
    private float rateAverage;
    private int rateStars;

    public Recipes(int cod, String title, String duration, String servings, int keenOnCount,
                   int madeCount, float rateAverage, int rateStars) {
        this.cod = cod;
        this.title = title;
        this.duration = duration;
        this.servings = servings;
        this.keenOnCount = keenOnCount;
        this.madeCount = madeCount;
        this.rateAverage = rateAverage;
        this.rateStars = rateStars;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getServings() {
        return servings;
    }

    public void setServings(String servings) {
        this.servings = servings;
    }

    public int getKeenOnCount() {
        return keenOnCount;
    }

    public void setKeenOnCount(int keenOnCount) {
        this.keenOnCount = keenOnCount;
    }

    public int getMadeCount() {
        return madeCount;
    }

    public void setMadeCount(int madeCount) {
        this.madeCount = madeCount;
    }

    public float getRateAverage() {
        return rateAverage;
    }

    public void setRateAverage(float rateAverage) {
        this.rateAverage = rateAverage;
    }

    public int getRateStars() {
        return rateStars;
    }

    public void setRateStars(int rateStars) {
        this.rateStars = rateStars;
    }
}
