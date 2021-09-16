package com.example.webik.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
@Entity
@Table(name = "\"group\"")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_id_seq")
    @SequenceGenerator(name = "group_id_seq", sequenceName = "group_id_seq", allocationSize = 1)
    private String id;

    @Column(name = "name")
    private String name;

    @Transient
    private ArrayList<Group> groups;

    @OneToMany(mappedBy = "group")
    @JsonIgnore
    private ArrayList<Item> items;

    @Transient
    private Group groupParent;


    public Group() {
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        return sb.append('{')
                .append(id)
                .append("_")
                .append(name)
                .append("_")
                .append(items)
                .append("}").toString();
    }
    public void addItem(Item item) {
        this.items.add(item);
        item.setParentGroup(this);
    }
    public void printContent() {
        System.out.printf("Current group name: %s id: %s%n [", name, id);
        System.out.println("Items: ");
        for (Item item : items) {
            System.out.println(item);
        }
        for (Group x : groups) {
            x.printContent();
        }
        System.out.println("]");
    }

    public Group(String id, String name) {
        this.id = id;
        this.name = name;
        groups = new ArrayList<>();
        items = new ArrayList<>();
    }

    public Group getGroupParent() {
        return groupParent;
    }

    public void setGroupParent(Group groupParent) {
        this.groupParent = groupParent;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public String getId() {
        return id;
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
}
