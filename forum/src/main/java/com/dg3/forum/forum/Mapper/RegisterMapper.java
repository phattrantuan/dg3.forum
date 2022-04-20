package com.dg3.forum.forum.Mapper;

import com.dg3.forum.forum.dto.UserRegisterDTO;
import com.dg3.forum.forum.entity.Users;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface RegisterMapper {
    Users fromUserEntityCreateDtoToEntity (UserRegisterDTO userRegisterDTO);
    UserRegisterDTO fromEntityToDto(Users users);
}
