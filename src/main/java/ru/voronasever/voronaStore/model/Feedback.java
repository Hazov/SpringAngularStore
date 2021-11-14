package ru.voronasever.voronaStore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "feedbacks")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id")
    Long id;
    @JoinColumn(name = "feedback_owner")
    @ManyToOne()
    User owner;
    @Column(name = "feedback_raiting")
    int raiting;
    @Column(name = "feedback_comment")
    String comment;


}
