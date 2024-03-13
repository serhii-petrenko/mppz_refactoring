package com.rshu.mppz.data.order.model;

import com.rshu.mppz.data.product.model.Product;
import com.rshu.mppz.data.customer.model.Customer;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "created_on")
//    private Date createdOn;
//
//    @Column(name = "status")
//    @CreatedDate
//    private String status;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

//    @Transient
//    private List<Integer> selectedItems;

    @JoinTable(name = "order_product_map",
            joinColumns = @JoinColumn(
                name = "order_id",
                referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "product_id",
                    referencedColumnName = "id"))
    @OneToMany
    private List<Product> products;
}
