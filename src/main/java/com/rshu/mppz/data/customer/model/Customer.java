package com.rshu.mppz.data.customer.model;

import com.rshu.mppz.data.order.model.Order;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "customers")
@Getter
@Setter
public class Customer implements Serializable {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer customerId;

	@Column(name = "first_name")
	@NotEmpty
	private String firstName;

	@Column(name = "last_name")
	@NotEmpty
	private String lastName;

	@Column(name = "email")
	@NotEmpty
	private String email;

	@Column(name = "orders")
	@OneToMany(mappedBy="customer")
	private Set<Order> orders;

}
