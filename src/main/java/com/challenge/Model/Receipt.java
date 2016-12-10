package com.challenge.Model;

import javax.persistence.*;
import java.util.ArrayList;
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner")
    private User user;

    @OneToMany(mappedBy = "receipt")
    private List<CartItem> cartItems = new ArrayList<>();
}
