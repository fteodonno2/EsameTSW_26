package model.beans;

import java.sql.Timestamp;

public class Feedback {
    private String name;
    private String email;
    private int rating;
    private String comments;

    public Feedback() {}

    public Feedback(String name, String email, int rating, String comments) {
        this.name = name;
        this.email = email;
        this.rating = rating;
        this.comments = comments;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getRating() { return rating; }

    public String getComments() { return comments; }

}