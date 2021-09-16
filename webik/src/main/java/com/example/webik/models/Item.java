package com.example.webik.models;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_id_seq")
    @SequenceGenerator(name = "item_id_seq", sequenceName = "item_id_seq", allocationSize = 1)
    protected String id;

    @Column(name = "name")
    protected String name;

    @Column(name = "base_price")
    protected double price;
    @Transient
    private String imageUrl;
    @Column(name = "currency")
    protected Currency currency;
    @Transient
    protected Configuration configuration;

    @ManyToOne
    @JoinColumn(name = "group_id")
    @JsonBackReference
    private Group parentGroup;

    @OneToOne(mappedBy = "item", cascade = CascadeType.ALL,
            orphanRemoval = true)
    private ItemDetails itemDetails;

    @ManyToMany(mappedBy = "items")
    private List<Basket> baskets = new ArrayList<>();


    public Item() {

    }

    public Item(String id, String name, double price, Configuration configuration) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.configuration = configuration;
        this.currency = Currency.getInstance(Locale.getDefault());
    }

    public Item(String id, String name, String url, double price, Configuration configuration) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.configuration = configuration;
        this.currency = Currency.getInstance(Locale.getDefault());
        this.imageUrl = url;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        return sb.append('{')
                .append(id)
                .append("_")
                .append(name)
                .append("_")
                .append(calculatePrice())
                .append("_")
                .append(currency)
                .append("_")
                .append(configuration.resolution.name())
                .append("_")
                .append(getImageUrl())
                .append("}").toString();
    }

    public Group getParentGroup() {
        return parentGroup;
    }

    public void setParentGroup(Group parentGroup) {
        this.parentGroup = parentGroup;
    }

    public String getId() {
        return id;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public double calculatePrice() {
        return price * configuration.resolution.getCoefficient();
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ItemDetails getItemDetail() {
        return itemDetails;
    }

    public void setItemDetail(ItemDetails itemDetails) {
        this.itemDetails = itemDetails;
    }

    public void print() {
        System.out.printf("ITEM(%s) - id: {%d} {%s} {%d}%n",
                this.getClass().getSimpleName(), id, name, price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        return id.equals(item.id);
    }
}
