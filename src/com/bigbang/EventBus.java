package com.bigbang;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EventBus implements PublisherInterface {

     List<News> news;
     ArrayList<Triplet<EventType, String, ConsumerCallbackInterface>> subscribers;

    public EventBus() {
        this.news = Collections.synchronizedList(new ArrayList<>());
        this.subscribers = new ArrayList<>();
    }

    private void CreateNews(News[] x) {
        for (News n : x) {
            this.news.add(n);
        }
        this.emitEvent(new Event(EventType.NEWS_CREATED, x));

    }

    private void UpdateNews(News oldNews, News newNews) {
        try {
            this.news.remove(oldNews);
            this.news.add(newNews);
        } catch (Exception e) {
            e.printStackTrace();
        }
        News[] n = new News[2];
        n[0] = oldNews;
        n[1] = newNews;
        this.emitEvent(new Event(EventType.NEWS_EDITTED, n));
    }


    private void DeleteNews(News[] x) {
        for (News n : x) {
            try {
                this.news.remove(n);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.emitEvent(new Event(EventType.NEWS_DELETE, x));
    }

    @Override
    public void AddEvent(Event event) {
        switch (event.getKey()){
            case NEWS_CREATED:
                this.CreateNews(event.getValue());
                break;
            case NEWS_DELETE:
                this.DeleteNews(event.getValue());
                break;
            case NEWS_EDITTED:
                this.UpdateNews(event.getValue()[0], event.getValue()[1]);

        }
    }

    public void subscribe(EventType type, String chan, ConsumerCallbackInterface subscriber) {
        this.subscribers.add(new Triplet(type, chan, subscriber));
        if(type == EventType.NEWS_CREATED){
            this.emitEvent(new Event(EventType.NEWS_READ, new News[]{new News(chan)}));
        }
    }

    private void emitEvent(Event event) {
        for(Triplet<EventType, String, ConsumerCallbackInterface> subscriber : this.subscribers) {
            if (subscriber.getFirst().equals(event.getKey())) {
                if(event.getValue() != null) {
                    for (News n : event.getValue()) {
                        if (n == null) continue;
                        if (subscriber.getSecond().equals(n.getDomain()))
                            subscriber.getThird().cb(event.getKey(), event.getValue());
                    }
                }
            }
        }
    }

    public List<News> getNews() {
        return news;
    }
}
