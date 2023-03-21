package com.rshu.mppz;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.List;
import java.util.UUID;

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
