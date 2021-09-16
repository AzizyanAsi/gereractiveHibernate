package com.example.webik.repository;

import com.example.webik.models.Basket;
import com.example.webik.service.HibernateConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Optional;

public class BasketRepo {
    public Optional<Basket> getBasket(Long basketId) {
        Session session = HibernateConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Basket basket = session.get(Basket.class, basketId);

        transaction.commit();
        session.close();

        return Optional.ofNullable(basket);
    }
}
