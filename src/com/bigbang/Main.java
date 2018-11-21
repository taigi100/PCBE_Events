package com.bigbang;

public class Main {

    public static void main(String[] args) {
	// write your code here
        NewsBus bus = new NewsBus();
        NewsEditor editor = new NewsEditor(bus);

        bus.subscribe((NewsEvent v, News... data) -> {
            System.out.println("New event: " + v);
            if (data.length > 0)
                System.out.println(data[0]);
        });

        editor.StartEditor();
    }
}