package com.challenge.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Edward on 12/10/2016.
 */
@Entity
@Table(name = "receipt")
public class Receipt {
    @Id
    @GeneratedValue
    private long id;

    @Column
    private Date date;

    @Column
    private Float total;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner")
    private User user;

    @OneToMany(mappedBy = "receipt")
    private List<CartItem> cartItems = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public Receipt(){}

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }
}
