package com.dg3.forum.forum.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long user_pk;
	public String email;
	public String password;
	public String username;
	public String role;
	public String phone_number;
	public String address;
	public Date date_of_birth;
	public boolean ban_account;
	public String img_avatar;
	public String description;
	public Date created_date;
	public Date expire;

//	
//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name =  )
//	Set<PostThread> setPostThread;//k them trung
	
	//jpin table dai dien cho khoa chinh chua clas
	//inverse join column dai dieb k phai khoa chinh
	public Users(Long user_pk, String email, String password, String username, String role, String phone_number,
			String address, Date date_of_birth, boolean ban_account, String img_avatar, String description,
			Date created_date, Date expire) {
		super();
		this.user_pk = user_pk;
		this.email = email;
		this.password = password;
		this.username = username;
		this.role = role;
		this.phone_number = phone_number;
		this.address = address;
		this.date_of_birth = date_of_birth;
		this.ban_account = ban_account;
		this.img_avatar = img_avatar;
		this.description = description;
		this.created_date = created_date;
		this.expire = expire;
	}

	public Users() {

	}

}
