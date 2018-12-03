package com.bigbang;

import javafx.util.Pair;

public class Event extends Pair<EventType, News[]> {
    public Event(EventType event, News[] news) {
        super(event, news);
    }
}
