package com.challenge.Model;

import javax.persistence.*;

/**
 * Created by marti on 10/12/2016.
 */

@Entity
@Table(name = "cartitem")
public class CartItem {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private Integer quantity;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private Receipt receipt;

    public CartItem() {
    }

    public CartItem(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
