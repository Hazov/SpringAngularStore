package ru.voronasever.voronaStore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.voronasever.voronaStore.payload.request.CreateOrderRequest;


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
    @Column(name = "order_phonenumber")
    String phoneNumber;
    @Column(name = "order_totalprice")
    int totalPrice;
    @Column(name = "order_totalprice")
    @ManyToMany
    Collection<Product> items;
    @JoinColumn(name = "order_address")
    @ManyToOne
    Address address;
    @Column(name = "order_amount")
    Integer amount;


}

