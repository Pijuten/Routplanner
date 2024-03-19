package at.fhtw.routplanner.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Log {
    private StringProperty id;
    private StringProperty date;
    private StringProperty timeStamp;
    private StringProperty difficulty;
    private StringProperty distance;
    private StringProperty timeLength;
    private StringProperty rating;
    public Log(String id, String date,
               String timeStamp,
               String difficulty,
               String distance,
               String timeLength,
               String rating){
        this.id = new SimpleStringProperty(id);
        this.date=new SimpleStringProperty(date);
        this.timeStamp=new SimpleStringProperty(timeStamp);
        this.difficulty=new SimpleStringProperty(difficulty);
        this.distance=new SimpleStringProperty(distance);
        this.timeLength=new SimpleStringProperty(timeLength);
        this.rating=new SimpleStringProperty(rating);
    }

    public String getDate() {
        return date.get();
    }

    public StringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getTimeStamp() {
        return timeStamp.get();
    }

    public StringProperty timeStampProperty() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp.set(timeStamp);
    }

    public String getDifficulty() {
        return difficulty.get();
    }

    public StringProperty difficultyProperty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty.set(difficulty);
    }

    public String getDistance() {
        return distance.get();
    }

    public StringProperty distanceProperty() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance.set(distance);
    }

    public String getTimeLength() {
        return timeLength.get();
    }

    public StringProperty timeLengthProperty() {
        return timeLength;
    }

    public void setTimeLength(String timeLength) {
        this.timeLength.set(timeLength);
    }

    public String getRating() {
        return rating.get();
    }

    public StringProperty ratingProperty() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating.set(rating);
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }
}
