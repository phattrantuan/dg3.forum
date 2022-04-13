package com.dg3.forum.forum.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "dedicine")
public class Medicine {
	private Long dealer_pk;
	private Long medicine_pk;
	private String name_medicine;
	private String where_production;
	private String price;
	private String effect;
	private String details_medicine;
	private boolean enable_medicine;
	

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "users_medicine"
    ,joinColumns = {
    		@JoinColumn(name="medicine_pk", referencedColumnName = "medicine_pk")//pre is table db, after is variable class
    }//array
    ,inverseJoinColumns = 
    	{@JoinColumn(name="user_pk", referencedColumnName = "user_pk")
    }) //join column represents the key that is in that class
     //join column specify the foreign key being attached
    Set<Medicine> medicines;
}
