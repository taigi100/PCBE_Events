package com.bigbang;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NewsBus implements PublisherInterface {

     List<Event> events; // TODO: Change to arraylist, keep the news only, and edit / work on them.
     ArrayList<ConsumerCallbackInterface> subscribers;

    public NewsBus() {
        this.events = Collections.synchronizedList(new ArrayList<>());
        this.subscribers = new ArrayList<>();
    }

    private void CreateNews(News x) {
        this.events.add(new Event(NewsEvent.NEWS_CREATED, x)); //TODO: should we transform this pair in an actual object?
        this.emitEvent(this.events.get(this.events.size() - 1));
    }

    private void UpdateNews() { //TODO: same as Delete, gets the new and the old news object and edits it

    }


    private void DeleteNews() { // TODO: send the news to be deleted with the event

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
