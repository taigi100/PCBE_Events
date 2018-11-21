package com.bigbang;

public class NewsEditor { // need a thread for pushing events in the bus
    private Thread sendMessagesHandler;

    public NewsEditor(NewsBus nb) {
        this.sendMessagesHandler = new Thread(() -> {
            while (true) {
                try {
                    nb.CreateNews(new News("test", "John Doe"));
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
