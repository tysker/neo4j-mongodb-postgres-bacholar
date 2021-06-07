package dk.ckmwn.dto;

import org.bson.Document;

import java.util.Date;
import java.util.Map;

public class Article {
    private String id, content, summary;
    private double rating;
    private Date createdAt;

    public Keyword getKeyword()
    {
        return keyword;
    }

    public void setKeyword(Keyword keyword)
    {
        this.keyword = keyword;
    }

    private Keyword keyword;

    public Article(String id, String content, String summary, double rating, Date createdAt, Keyword keyword) {
        this.id = id;
        this.content = content;
        this.summary = summary;
        this.rating = rating;
        this.createdAt = createdAt;
        this.keyword = keyword;
    }

    public Article(String content, String summary, double rating, Date createdAt, Keyword keyword) {
        this.content = content;
        this.summary = summary;
        this.rating = rating;
        this.createdAt = createdAt;
        this.keyword = keyword;
    }

    public Article(String id, String content) {
        this.id = id;
        this.content = content;
    }

    public Article() {
    }

    public static Article fromDoc(Document doc) {
        return new Article(doc.get("_id").toString(), doc.get("content").toString());
    }

    public String getContent() {
        return content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
