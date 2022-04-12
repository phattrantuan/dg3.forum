package com.dg3.forum.forum.mapping;

import org.mapstruct.Mapper;

import com.dg3.forum.forum.dto.Usersdto;
import com.dg3.forum.forum.entity.Users;

@Mapper
public interface UserMapper {
	Users mapper (Usersdto Usersdto);
}
