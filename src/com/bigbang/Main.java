package com.bigbang;

public class Main {

    public static void main(String[] args) {
	// write your code here
        EventBus bus = new EventBus();
        NewsEditor editor = new NewsEditor(bus);

        bus.subscribe(EventType.NEWS_CREATED, "test", (EventType v, News... data) -> {
            System.out.println("New event: " + v);
            if (data.length > 0)
                System.out.println(data[0]);
        });

        editor.StartEditor();
    }
}