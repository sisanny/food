package com.homework.orderapplication.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customer;
    private Long restaurant;
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToMany(mappedBy = "orderId", cascade = CascadeType.MERGE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderItem> items;

    @Override
    public String toString() {
        return "";
    }
}
