package com.homework.orderapplication.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<Item> menu;

    @Override
    public String toString() {
        return "";
    }
}
