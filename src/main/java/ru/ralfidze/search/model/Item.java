package ru.ralfidze.search.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URL;

@JsonIgnoreProperties(value = { "items" })
public class Item {

    @JsonProperty("question_id")
    private long questionId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("link")
    private URL link;

    @JsonProperty("creation_date")
    private int creationDate;

    @JsonProperty("score")
    private int score;

    @JsonProperty("is_answered")
    private boolean isAnswered;


    public Owner getOwner() {
        return owner;
    }

    public Item setOwner(Owner owner) {
        this.owner = owner;
        return this;
    }

    private Owner owner;

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public URL getLink() {
        return link;
    }

    public void setLink(URL link) {
        this.link = link;
    }

    public int getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(int creationDate) {
        this.creationDate = creationDate;
    }

    public long getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isAnswered() {
        return isAnswered;
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }

    @Override
    public String toString() {
        return "Item{" +
                "questionId=" + questionId +
                ", title='" + title + '\'' +
                ", link=" + link +
                ", creationDate=" + creationDate +
                ", score=" + score +
                ", isAnswered=" + isAnswered +
                '}';
    }

}
