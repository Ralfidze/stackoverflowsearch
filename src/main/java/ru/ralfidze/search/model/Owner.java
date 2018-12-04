package ru.ralfidze.search.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URL;

public class Owner {

    @JsonProperty("user_id")
    private long userId;

    @JsonProperty("reputation")
    private long reputation;

    @JsonProperty("user_type")
    private String userType;

    @JsonProperty("display_name")
    private String displayName;

    @JsonProperty("profile_image")
    private String profileImage;

    @JsonProperty("link")
    private URL link;

    public long getUserId() {
        return userId;
    }

    public Owner setUserId(long userId) {
        this.userId = userId;
        return this;
    }

    public long getReputation() {
        return reputation;
    }

    public Owner setReputation(long reputation) {
        this.reputation = reputation;
        return this;
    }

    public String getUserType() {
        return userType;
    }

    public Owner setUserType(String userType) {
        this.userType = userType;
        return this;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Owner setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public Owner setProfileImage(String profileImage) {
        this.profileImage = profileImage;
        return this;
    }

    public URL getLink() {
        return link;
    }

    public Owner setLink(URL link) {
        this.link = link;
        return this;
    }


    @Override
    public String toString() {
        return "Owner{" +
                "userId=" + userId +
                ", reputation=" + reputation +
                ", userType='" + userType + '\'' +
                ", displayName='" + displayName + '\'' +
                ", profileImage='" + profileImage + '\'' +
                ", link=" + link +
                '}';
    }
}
