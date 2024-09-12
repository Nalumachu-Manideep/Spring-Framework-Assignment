package com.springdemo.entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank(message = "Product name is required")
    @Size(min = 2, max = 100, message = "Product name must be between 2 and 100 characters")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Description is required")
    @Size(min = 5, max = 500, message = "Description must be between 5 and 500 characters")
    @Column(name = "description")
    private String description;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    @Digits(integer = 10, fraction = 2, message = "Price must be a valid number with up to 10 digits and 2 decimal places")
    @Column(name = "price")
    private double price;


    @ManyToMany(mappedBy = "products")
    @ToString.Exclude
    private List<Order> orders;

}
