package com.bigbang;

import java.util.Date;

public class News {
    private String domain;
    private String subDomain;
    private Date firstPublished;
    private Date lastModified;
    private String author;

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
}
