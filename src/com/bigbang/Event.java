package com.bigbang;

import javafx.util.Pair;

public class Event extends Pair<NewsEvent, News> {
    public Event(NewsEvent event, News news) {
        super(event, news);
    }
}
