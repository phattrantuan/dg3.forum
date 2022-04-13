package com.dg3.forum.forum.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	public Users(String email, String password, String username, String role, String phone_number, String address,
			Date date_of_birth, boolean ban_account, String img_avatar, String description, Date created_date,
			Date expire, boolean enable_users) {
		super();
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
		this.enable_users = enable_users;
	}

}
