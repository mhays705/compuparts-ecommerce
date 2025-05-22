package com.ecommerce.system.backend.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "manufacturers")
public class Manufacturer {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "manufacturer",cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Product> products;

}
