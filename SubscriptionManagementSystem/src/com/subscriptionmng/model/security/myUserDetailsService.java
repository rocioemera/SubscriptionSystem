package com.subscriptionmng.model.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.*;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class myUserDetailsService implements UserDetailsService{
	private UserImp userImp;

	public UserImp getUserImp() {
		return userImp;
	}

	public void setUserImp(UserImp userImp) {
		this.userImp = userImp;
	}
	
	@Override
	public UserDetails loadUserByUsername(final String username) 
			throws UsernameNotFoundException, DataAccessException{

		UserImp user = userImp.getUser(username);
		if(user == null){
			throw new UsernameNotFoundException("user not found");
		}
		//List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());

		return buildUserForAuthentication(user);
	}
	
	private User buildUserForAuthentication(UserImp user) 
	{
	    String username = user.getUsername();
	    String password = user.getPassword();
	    boolean enabled = user.getEnable();
	    boolean accountNonExpired = true;
	    boolean credentialsNonExpired = true;
	    boolean accountNonLocked = true;

	    Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
	    
	    for (RoleImp role : user.getUserRoles()) {
	    	setAuths.add(new GrantedAuthorityImpl(role.getRol_name()));
	    }

	    User userSec = new User(username, password, enabled,
	      accountNonExpired, credentialsNonExpired, accountNonLocked, setAuths);
	    return userSec;
	}
}

