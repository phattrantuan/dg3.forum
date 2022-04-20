package com.dg3.forum.forum.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDTO{

    private String email;
    private String password;
    private String username;
    private String phone_number;
    private String address;
    private Date date_of_birth;
    private String img_avatar;
}
