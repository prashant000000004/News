package com.example.ajjtk;

import java.util.ArrayList;

public class NewsModel {
    private int  totalResults;
    private String status;
    private ArrayList<Article> articles;

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Article> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;
    }

    public NewsModel(int totalResults, String status, ArrayList<Article> articles) {
        this.totalResults = totalResults;
        this.status = status;
        this.articles = articles;
    }
}
