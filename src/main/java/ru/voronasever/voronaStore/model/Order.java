package ru.voronasever.voronaStore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    Long id;
    @JoinColumn(name = "order_owner")
    @OneToOne
    User owner;
    @Column(name = "order_phonenumber")
    String phoneNumber;
    @Column(name = "order_amount")
    int amount;
    @Column(name = "order_items")
    @ManyToMany
    Collection<Product> items;
    @Column(name = "order_address")
    @OneToMany
    Collection<Address> address;
}

