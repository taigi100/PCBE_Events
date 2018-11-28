package com.bigbang;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NewsBus implements PublisherInterface {

     List<Event> events;
     ArrayList<ConsumerCallbackInterface> subscribers;

    public NewsBus() {
        this.events = Collections.synchronizedList(new ArrayList<>());
        this.subscribers = new ArrayList<>();
    }

    private void CreateNews(News x) {
        this.events.add(new Event(NewsEvent.NEWS_CREATED, x)); //TODO: should we transform this pair in an actual object?
        this.emitEvent(this.events.get(this.events.size() - 1));
    }

    private void UpdateNews() { //TODO: decide how you wanna do this, make it a list with aggregated events or literally
        // edit the element in the list

    }


    private void DeleteNews() {

    }

    @Override
    public void AddEvent(Event event) { //TODO: Should we change this to Event and Data?
        switch (event.getKey()){
            case NEWS_CREATED:
                this.CreateNews(event.getValue());
        }
    }

    public void subscribe(ConsumerCallbackInterface subscriber) {
        this.subscribers.add(subscriber);
    }

    private void emitEvent(Event event) {
        for(ConsumerCallbackInterface subscriber : this.subscribers) {
            subscriber.cb(event.getKey(), event.getValue());
        }
    }
}
