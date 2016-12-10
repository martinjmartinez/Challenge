package com.challenge.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by martin on 07/12/16.
 */
@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue
    private long id;
    @Column
    @Size(min = 3, max = 25)
    private String name;
    @Column
    @Size(min = 2, max = 25)
    private String lastname;
    @Column
    @NotNull
    private String email;
    @Column
    @NotNull
    private String address;
    @Column
    @NotNull
    private String password;
    @Column
    @NotNull
    private boolean isAdmin;
    @Column
    @NotNull
    private String role;
    @Column
    private String accountType;
    @Column
    @NotNull
    private boolean isEnterprise;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private List<Product> products = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private List<CartItem> cart = new ArrayList<>();

    public User() {
    }

    public User(String name, String lastname, String email, String address, String password, boolean isAdmin) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.address = address;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin() {
        if(role.equals("Sells Departament") || role.equals("Inventory Departament")){
            isAdmin=true;
        }
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
        setAdmin();
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
        setEnterprise();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public boolean isEnterprise() {
        return isEnterprise;
    }

    public void setEnterprise() {
        if(accountType.equals("Legal")){
            isEnterprise=true;
        }else if(accountType.equals("Final")){
            isEnterprise=false;
        }
    }

    public List<CartItem> getCart() {
        return cart;
    }

    public void setCart(List<CartItem> cart) {
        this.cart = cart;
    }

}
