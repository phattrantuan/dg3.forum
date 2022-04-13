package com.dg3.forum.forum.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_pk;
    private String email;
    private String password;
    private String username;
    private String role;
    private String phone_number;
    private String address;
    private Date date_of_birth;
    private boolean ban_account;
    private String img_avatar;
    private String description;
    private Date created_date;
    private Date expire;
    private boolean enable_users;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "users_medicine"
    ,joinColumns = {
    		@JoinColumn(name="user_pk", referencedColumnName = "user_pk")//pre is table db, after is variable class
    }//array
    ,inverseJoinColumns = 
    	{@JoinColumn(name="medicine_pk", referencedColumnName = "medicine_pk")
    }) //join column represents the key that is in that class
     //join column specify the foreign key being attached
    Set<Medicine> medicines;
}
