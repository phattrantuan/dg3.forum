package com.dg3.forum.forum.util;

import java.util.HashSet;
import java.util.Set;

import com.dg3.forum.forum.entity.Users;
import com.dg3.forum.forum.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UsersRepository usersRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users users = usersRepository.findByEmail(username);

		if (users == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}

		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		grantedAuthorities.add(new SimpleGrantedAuthority(users.getRole()));

		return new User(users.getEmail(), users.getPassword(), grantedAuthorities);

	}

}