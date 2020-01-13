package com.example.adabooazeem.ifmatv;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Attrs {
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("thumbnails")
    @Expose
    private List<Thumbnail> thumbnails = null;
    @SerializedName("banners")
    @Expose
    private List<Object> banners = null;
    @SerializedName("languages")
    @Expose
    private List<String> languages = null;
    @SerializedName("ageLimit")
    @Expose
    private Integer ageLimit;
    @SerializedName("genres")
    @Expose
    private List<String> genres = null;
    @SerializedName("countries")
    @Expose
    private List<String> countries = null;
    @SerializedName("type")
    @Expose
    private String type;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Attrs withTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Attrs withDescription(String description) {
        this.description = description;
        return this;
    }

    public List<Thumbnail> getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(List<Thumbnail> thumbnails) {
        this.thumbnails = thumbnails;
    }

    public Attrs withThumbnails(List<Thumbnail> thumbnails) {
        this.thumbnails = thumbnails;
        return this;
    }

    public List<Object> getBanners() {
        return banners;
    }

    public void setBanners(List<Object> banners) {
        this.banners = banners;
    }

    public Attrs withBanners(List<Object> banners) {
        this.banners = banners;
        return this;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public Attrs withLanguages(List<String> languages) {
        this.languages = languages;
        return this;
    }

    public Integer getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(Integer ageLimit) {
        this.ageLimit = ageLimit;
    }

    public Attrs withAgeLimit(Integer ageLimit) {
        this.ageLimit = ageLimit;
        return this;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public Attrs withGenres(List<String> genres) {
        this.genres = genres;
        return this;
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    public Attrs withCountries(List<String> countries) {
        this.countries = countries;
        return this;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Attrs withType(String type) {
        this.type = type;
        return this;
    }

}