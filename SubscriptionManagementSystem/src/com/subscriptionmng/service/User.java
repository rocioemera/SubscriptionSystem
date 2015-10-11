package com.subscriptionmng.service;

import com.subscriptionmng.model.security.UserImp;

public interface User {
	public UserImp getUser(String username);
}
