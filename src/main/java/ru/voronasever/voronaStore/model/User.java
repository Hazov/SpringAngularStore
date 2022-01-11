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
    @OneToMany()
    Collection<Product> favourite;
    @Column(name = "user_reviews")
    @OneToMany()
    Collection<Feedback> reviews;
    @JoinColumn(name = "user_role")
    @OneToOne()
    Role role;
    @OneToMany()
    Collection<Address> addresses;
}
