package com.hood.merch.models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Version;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private String img;
    @Column
    private String color;
    @Column
    private int price;
    @Column
    private int quantity;

    @Column
    @Version // Предотвращает потерю изменений при конкурентном доступе к данным.
    private int in_stock;

    // Значение поля size не будет загружено из базы в момент загрузки родительского объекта Product.
    // Объект типа Sizes будет загружен при первом обращении к полю size.
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    private Sizes size;


    public Product() {
    }

    public Product(int id, String name, int price, int quantity, String img, String color, int in_stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.img = img;
        this.color = color;
        this.in_stock = in_stock;
    }
}