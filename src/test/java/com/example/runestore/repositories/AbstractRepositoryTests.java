package com.example.runestore.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract class AbstractRepositoryTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Método de reset geral
     */
    public void resetID() {
        jdbcTemplate.execute("ALTER TABLE users ALTER COLUMN id RESTART WITH 1");
    }

    public abstract void limpaBancos();

    public abstract void populaBancos();
}
