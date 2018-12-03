package com.bigbang;

import java.util.Date;
import java.util.Objects;

public class News {
    private String domain;
    private String subDomain;
    private Date firstPublished;
    private Date lastModified;
    private String author;

    public News(String domain, String author) {
        this.domain = domain;
        this.author = author;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getSubDomain() {
        return subDomain;
    }

    public void setSubDomain(String subDomain) {
        this.subDomain = subDomain;
    }

    public Date getFirstPublished() {
        return firstPublished;
    }

    public void setFirstPublished(Date firstPublished) {
        this.firstPublished = firstPublished;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "News{" +
                "domain='" + domain + '\'' +
                ", subDomain='" + subDomain + '\'' +
                ", firstPublished=" + firstPublished +
                ", lastModified=" + lastModified +
                ", author='" + author + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof News)) return false;
        News news = (News) o;
        return Objects.equals(getDomain(), news.getDomain()) &&
                Objects.equals(getSubDomain(), news.getSubDomain()) &&
                Objects.equals(getFirstPublished(), news.getFirstPublished()) &&
                Objects.equals(getLastModified(), news.getLastModified()) &&
                Objects.equals(getAuthor(), news.getAuthor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDomain(), getSubDomain(), getFirstPublished(), getLastModified(), getAuthor());
    }
}
