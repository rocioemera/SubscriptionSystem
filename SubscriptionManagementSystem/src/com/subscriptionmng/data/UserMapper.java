package com.subscriptionmng.data;

import com.subscriptionmng.model.security.UserImp;

public interface UserMapper {
	public UserImp getUser(String Username);
}
