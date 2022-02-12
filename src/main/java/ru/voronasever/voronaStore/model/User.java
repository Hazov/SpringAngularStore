package ru.voronasever.voronaStore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    Integer id;

    @Column(name = "user_email")
    String email;

    @Column(name = "user_pass")
    String pass;

    @Column(name = "user_name")
    String name;

    @Column(name = "user_avatar")
    String avatar;

    @JoinColumn(name = "user_cart")
    @OneToOne
    Cart cart;

    @Column(name = "user_favourite")
    @ManyToMany()
    @JoinTable(name = "users_favourites",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = { @JoinColumn(name = "favourite_id") }
    )
    Collection<Product> favourite;

    @Column(name = "user_reviews")
    @ManyToMany()
    @JoinTable(name = "users_reviews",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "review_id") }
    )
    Collection<Review> reviews;

    @JoinColumn(name = "user_role")
    @ManyToOne()
    Role role;

    @Column(name = "user_addresses")
    @OneToMany()
    Collection<Address> addresses;

    @Column(name = "user_orders")
    @OneToMany
    Collection<Order> orders;
}
