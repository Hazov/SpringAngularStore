package ru.voronasever.voronaStore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Target;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    Integer id;
    @ManyToMany
    @JoinTable(
            name = "carts_products",
            joinColumns = { @JoinColumn(name = "cart_id") },
            inverseJoinColumns = { @JoinColumn(name = "product_id") }
    )
    List<Product> products = new ArrayList<>();

    @OneToOne
    User owner;


}
