package com.homework.orderapplication.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "`order`")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Customer customer;
    @OneToOne
    private Restaurant restaurant;
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToMany(mappedBy = "orderId")
    private List<OrderItem> items;
}
