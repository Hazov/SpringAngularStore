package ru.voronasever.voronaStore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.voronasever.voronaStore.payload.request.CreateOrderRequest;


import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    Long id;
    @Column(name = "order_create_time")
    Date createTime;
    @Column(name = "order_phone_number")
    String phoneNumber;
    @Column(name = "order_totalprice")
    int totalPrice;
    @Column(name = "order_totalprice")
    @ManyToMany
    @JoinTable(
            name = "orders_products",
            joinColumns = { @JoinColumn(name = "order_id") },
            inverseJoinColumns = { @JoinColumn(name = "product_id") }
    )
    Collection<Product> items;
    @JoinColumn(name = "order_address")
    @ManyToOne
    Address address;
    @Column(name = "order_amount")
    Integer amount;
    @JoinColumn(name = "order_owner")
    @ManyToOne
    User owner;


}

