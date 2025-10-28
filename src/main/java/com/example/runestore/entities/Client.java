package com.example.runestore.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "clients")
public class Client extends AbstractEntity{
    private String nome;

    @Column(unique = true)
    private String email;

    /**
     * Pode ser null enquanto o usuário estiver fazendo compras sem login, usando apenas o e-mail
     */
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Order> orderList;
}
