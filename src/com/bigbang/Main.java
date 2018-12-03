package com.bigbang;

public class Main {

    public static void main(String[] args) {
	// write your code here
        NewsBus bus = new NewsBus(); // TODO: Change to EventBus
        NewsEditor editor = new NewsEditor(bus);

        bus.subscribe((NewsEvent v, News... data) -> { //TODO: Add in eventtype and channel / domain.
            System.out.println("New event: " + v);
            if (data.length > 0)
                System.out.println(data[0]);
        });

        editor.StartEditor();
    }
}