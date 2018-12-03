package com.bigbang;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class NewsEditor implements ConsumerCallbackInterface{ // need a thread for pushing events in the bus
    private Thread sendMessagesHandler;
    private Thread deleteMessagesHandler;
    private Thread updateMessagesHandler;
    private HashMap<News, Integer> readers;

    public NewsEditor(EventBus nb) {
        this.readers = new HashMap<>();
        this.sendMessagesHandler = new Thread(() -> {
            while (true) {
                try {
                    ArrayList<News> newsList = new ArrayList<>();
                    for(int i = 0; i < 5; i++){
                        News n1 = new News("domain_" + i, "subdomain_" + (i + 1), "author_" + i);
                        News n2 = new News("domain_" + (i + 1), "subdomain_" + i, "author_" + (i + 1));
                        newsList.add(n1);
                        newsList.add(n2);
                    }
                    //News[] toAdd = new News[]{new News("test", "John Doe")};
                    News[] toAdd = new News[newsList.size()];
                    toAdd = newsList.toArray(toAdd);

                    nb.AddEvent(new Event(EventType.NEWS_CREATED, toAdd));
                    Thread.sleep(200000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        this.deleteMessagesHandler = new Thread(() -> {
            while (true) {
                try {

                    List<News> allNews = nb.getNews();
                    ArrayList<News> todelete = new ArrayList<>();
                    synchronized (allNews){
                        if(allNews.size() > 1) {
                            for(News n : allNews){
                                if (n.getDomain().equals("domain_1")){
                                    todelete.add(n);
                                }
                            }

                            News[] delete = new News[todelete.size()];
                            delete = todelete.toArray(delete);

                            nb.AddEvent(new Event(EventType.NEWS_DELETE, delete));
                        }
                    }

                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        this.updateMessagesHandler = new Thread(() -> {
            while (true) {
                try {
                    ArrayList<News> allNews = new ArrayList<>(nb.getNews());
                    ArrayList<News> toUpdate = new ArrayList<>();
                    if(allNews.size() > 1) {
                        synchronized (allNews){
                            for(News n : allNews){
                                if (n.getDomain().equals("domain_4") || n.getDomain().equals("domain_5")){
                                    News updated = new News(n);
                                    updated.setFirstPublished(new Date());
                                    updated.setLastModified(new Date());
                                    updated.setDomain("new_domain");
                                    toUpdate.add(n);
                                    toUpdate.add(updated);
                                }

                                if(toUpdate.size() == 2){
                                    News[] update = new News[toUpdate.size()];
                                    update = toUpdate.toArray(update);

                                    nb.AddEvent(new Event(EventType.NEWS_EDITTED, update));

                                    toUpdate = new ArrayList<>();
                                }
                            }
                        }
                    }

                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void StartEditor() {
        this.sendMessagesHandler.start();
        this.deleteMessagesHandler.start();
        this.updateMessagesHandler.start();
    }

    @Override
    public void cb(EventType v, News... data) {
        synchronized (this.readers){
            if(this.readers.containsKey(data[0])){
                this.readers.put(data[0], this.readers.get(data[0]) + 1);
            }else{
                this.readers.put(data[0], 1);
            }

            System.out.println("Readers for "+data[0].getDomain()+" "+this.readers.get(data[0]));
        }
    }
}
