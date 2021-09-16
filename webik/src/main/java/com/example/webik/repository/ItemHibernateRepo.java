package com.example.webik.repository;

import com.example.webik.database.DatabaseConnection;
import com.example.webik.models.Group;
import com.example.webik.models.Item;
import com.example.webik.repository.mapper.ItemResultSetMapper;
import com.example.webik.service.HibernateConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class ItemHibernateRepo {

    public static final HibernateConfiguration HIBERNATE_CONFIGURATION =
            HibernateConfiguration.getInstance();

    public void attachItemToGroup(String itemId, String groupId) {
        Session session = HIBERNATE_CONFIGURATION.getSession();
        Transaction transaction = session.beginTransaction();

        Item item = session.get(Item.class, itemId);
        Group group = session.get(Group.class, groupId);

        item.setName("newItem");

        group.addItem(item);

        transaction.commit();
        session.close();
    }
    public Item findById(String id) {
        Session session = HIBERNATE_CONFIGURATION.getSession();
        Transaction transaction = session.beginTransaction();
        Item item = session.get(Item.class, id);

        transaction.commit();
        session.close();

        return item;
    }
    public Item insert(Item item) {
        Session session = HIBERNATE_CONFIGURATION.getSession();
        Transaction transaction = session.beginTransaction();

        if (item.getItemDetail() != null) {
            item.getItemDetail().setItem(item);
        }
        session.save(item);

        transaction.commit();

        session.close();

        return item;
    }
    public List<? extends Item> getAllItems() {
        Session session = HIBERNATE_CONFIGURATION.getSession();

        Transaction transaction = session.beginTransaction();

        String q = "from Item i";
        Query<Item> query = session.createQuery(q, Item.class);
        List<? extends Item> items = query.getResultList();

        transaction.commit();
        session.close();

        return items;
    }

    public Item update(Item item) {
        Session session = HIBERNATE_CONFIGURATION.getSession();
        Transaction transaction = session.beginTransaction();

        Item existing = session.get(Item.class, item.getId());
        existing.setName(item.getName());
        existing.setPrice(item.getPrice());

        transaction.commit();

        session.close();

        return existing;
    }
    public boolean updateById(String id) {
        Session session = HIBERNATE_CONFIGURATION.getSession();
        Transaction transaction = session.beginTransaction();

        String q = "update item set item.name=:name " +
                " where item.id = :id";

        Query query = session.createQuery(q);
        query.setParameter("id", id);
        int deleted = query.executeUpdate();

        transaction.commit();
        session.close();

        return deleted != 0;
    }

    public boolean delete(Item item) {
        Session session = HIBERNATE_CONFIGURATION.getSession();

        session.remove(item);

        session.close();

        return true;
    }
    public boolean deleteById(String id) {
        Session session = HIBERNATE_CONFIGURATION.getSession();
        Transaction transaction = session.beginTransaction();

        String q = "delete from Item i" +
                " where i.id = :id";

        Query query = session.createQuery(q);
        query.setParameter("id", id);
        int deleted = query.executeUpdate();

        transaction.commit();
        session.close();

        return deleted != 0;
    }
    public List<Item> findItems(Predicate<Item> searchPredicate) {
        return new ArrayList<>();
    }


}
