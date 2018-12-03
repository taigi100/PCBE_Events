package com.bigbang;

public class NewsEditor { // need a thread for pushing events in the bus
    private Thread sendMessagesHandler;

    public NewsEditor(EventBus nb) {
        this.sendMessagesHandler = new Thread(() -> {
            while (true) {
                try {
                    News[] toAdd = new News[]{new News("test", "John Doe")};
                    nb.AddEvent(new Event(EventType.NEWS_CREATED, toAdd));
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void StartEditor() {
        this.sendMessagesHandler.start();
    }
}
