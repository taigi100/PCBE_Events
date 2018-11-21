package com.bigbang;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NewsBus implements PublisherInterface {

     List<Pair<NewsEvent, News>> events;
     ArrayList<ConsumerCallbackInterface> subscribers;

    public NewsBus() {
        this.events = Collections.synchronizedList(new ArrayList<>());
        this.subscribers = new ArrayList<>();
    }

    @Override
    public void CreateNews(News x) {
        this.events.add(new Pair<>(NewsEvent.NEWS_CREATED, x)); //TODO: should we transform this pair in an actual object?
        this.emitEvent(this.events.get(this.events.size() - 1));
    }

    @Override
    public void UpdateNews() { //TODO: decide how you wanna do this, make it a list with aggregated events or literally
        // edit the element in the list

    }

    @Override
    public void DeleteNews() {

    }
}
