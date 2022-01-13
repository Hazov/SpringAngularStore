package ru.voronasever.voronaStore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "remind_pass")
public class RemindPass {
    @Column(name = "remind_id")
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "remind_email")
    String email;
    @Column(name = "remind_eenc")
    String eenc;
    @Column(name = "remind_hash")
    String hash;
    @Column(name = "remind_expire")
    long expire;
}
