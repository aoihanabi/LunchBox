package com.apps.wag.lunchbox;

public class Recipes {
    private int cod;
    private String title;
    private String duration;
    private String servings;
    private int keenOnCount;
    private float rateAverage;
    private int rateStars;
    private Usuario usuario;
    /*private String image;*/
    private int image;
    private String ingredient;
    private String steps;

    public Recipes(int cod, String title, String duration, String servings, int keenOnCount,
                   float rateAverage, int rateStars, int image/*String image*/, Usuario usuario,
                   String ingredient, String steps) {
        this.cod = cod;
        this.title = title;
        this.duration = duration;
        this.servings = servings;
        this.keenOnCount = keenOnCount;
        this.rateAverage = rateAverage;
        this.rateStars = rateStars;
        this.usuario = usuario;
        this.image = image;
        this.ingredient = ingredient;
        this.steps = steps;
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

//    public String getImage() {
//        return image;
//    }
//
//    public void setImage(String image) {
//        this.image = image;
//    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public Usuario getUsuario(){
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps (String steps) {
        this.steps = steps;
    }
}
