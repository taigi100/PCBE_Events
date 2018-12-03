package com.bigbang;

public class Main {

    public static void main(String[] args) {
	// write your code here
        EventBus bus = new EventBus();
        NewsEditor editor = new NewsEditor(bus);

        //read news
        bus.subscribe(EventType.NEWS_READ, "domain_2", editor);

        //news created - domain_1 subscriber
        bus.subscribe(EventType.NEWS_CREATED, "domain_1", (EventType v, News... data) -> {
            System.out.println("Domain_1 Subscriber received event NEWS_CREATED: " + v);
            if (data.length > 0)
                System.out.println(data[0]);
        });

        //news created - domain_2 subscriber
        bus.subscribe(EventType.NEWS_CREATED, "domain_2", (EventType v, News... data) -> {
            System.out.println("Domain_2 Subscriber received event: " + v);
            if (data.length > 0)
                System.out.println(data[0]);
        });

        //news created - domain_2 subscriber
        bus.subscribe(EventType.NEWS_CREATED, "domain_2", (EventType v, News... data) -> {
            System.out.println("Domain_2 Subscriber2 received event: " + v);
            if (data.length > 0)
                System.out.println(data[0]);
        });

        //news deleted
        bus.subscribe(EventType.NEWS_DELETE, "domain_1", (EventType v, News... data) -> {
            System.out.println("News_Deleted Subscriber received event: " + v);
            if (data.length > 0){
                for(News n : data)
                    System.out.println(n);
            }
        });

        //news updated
        bus.subscribe(EventType.NEWS_EDITTED, "domain_4", (EventType v, News... data) -> {
            System.out.println("News_Edited Subscriber received event: " + v);
            if (data.length > 0){
                System.out.println("Old news: " + data[0]);
                System.out.println("New news: " + data[1]);
            }
        });

        //news updated
        bus.subscribe(EventType.NEWS_EDITTED, "domain_5", (EventType v, News... data) -> {
            System.out.println("News_Edited Subscriber received event: " + v);
            if (data.length > 0){
                System.out.println("Old news: " + data[0]);
                System.out.println("New news: " + data[1]);
            }
        });

        editor.StartEditor();
    }
}