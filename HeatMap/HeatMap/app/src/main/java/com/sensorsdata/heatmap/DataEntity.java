package com.sensorsdata.heatmap;

import java.util.ArrayList;

/**
 * Created by zhangyunxiang on 2018/3/14.
 */

public class DataEntity {

    private ArrayList<ObjectInfo> subjects;

    public class ObjectInfo {
        public String title;
        private String year;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public ArrayList<String> getGenres() {
            return genres;
        }

        public void setGenres(ArrayList<String> genres) {
            this.genres = genres;
        }

        public RatingInfo getRating() {
            return rating;
        }

        public void setRating(RatingInfo rating) {
            this.rating = rating;
        }

        public ImageInfo getImages() {
            return images;
        }

        public void setImages(ImageInfo images) {
            this.images = images;
        }

        private String alt;
        private ArrayList<String> genres;
        private RatingInfo rating;
        private ImageInfo images;
    }

    public class RatingInfo {
        private String average;

        public String getAverage() {
            return average;
        }

        public void setAverage(String average) {
            this.average = average;
        }
    }

    public class ImageInfo {
        private String small;

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }
    }

    public ArrayList<ObjectInfo> getSubjects() {
        return subjects;
    }

    public void setSubjects(ArrayList<ObjectInfo> subjects) {
        this.subjects = subjects;
    }


}
