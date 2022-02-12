package ru.voronasever.voronaStore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    Long id;
    @JoinColumn(name = "review_owner")
    @ManyToOne()
    User owner;
    @Column(name = "review_evaluation")
    int raiting;
    @Column(name = "review_comment")
    String comment;


}
