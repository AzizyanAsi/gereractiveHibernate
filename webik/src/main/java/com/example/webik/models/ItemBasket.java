package com.example.webik.models;

public class ItemBasket {
    private final Item item;
    private final Configuration configuration;

    public ItemBasket(Item item, Configuration configuration) {
        this.item = item;
        this.configuration = configuration;
    }

    public Item getItem() {
        return item;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public double getPrice() {
        return item.calculatePrice();
    }

    public void print() {
        System.out.printf("%s (%s) for %d $\n", item.getName(), configuration.getResolution(),
                item.calculatePrice());
    }
}
