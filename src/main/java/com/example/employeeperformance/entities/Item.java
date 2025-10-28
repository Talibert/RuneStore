package com.example.employeeperformance.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "itens")
public class Item extends AbstractEntity {

    String name;

    BigDecimal value;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
