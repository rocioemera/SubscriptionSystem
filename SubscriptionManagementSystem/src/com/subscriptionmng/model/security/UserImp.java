package com.subscriptionmng.model.security;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.subscriptionmng.data.UserMapperImp;
import com.subscriptionmng.service.User;


@Entity
@Table(name="USERS")
@Inheritance(strategy=InheritanceType.JOINED)
public class UserImp implements Serializable, User{
	
	private static final long serialVersionUID = 1L;
	
	private Long ID;
	private String username;
	private String password;
	private boolean enable;
	private Set<RoleImp> userRoles;
	
	private Long version;
	
	//to call hibernate methods
	private UserMapperImp userMapper;
	
	@Id
    @Column(name = "USER_ID")
    @GeneratedValue
	public Long getID() {
		return ID;
	}
	public void setID(Long iD) {
		ID = iD;
	}
	
	@Version
    @Column(name="OPTLOCK",columnDefinition = "BIGINT default 0")
	//to manage optimistic lock strategies in long conversations
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	
	@Column(name = "USERNAME",unique = true, updatable =false)
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(name = "PASSWORD")
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name = "ENABLES")
	public boolean getEnable() {
		return enable;
	}
	
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="USER_ROL",
		joinColumns = {@JoinColumn(name="USER_ID")},
		inverseJoinColumns = {@JoinColumn(name="ROL_ID")}
	)
	public Set<RoleImp> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<RoleImp> userRoles) {
		this.userRoles = userRoles;
	}

	@Transient
	public UserMapperImp getUserMapper() {
		return userMapper;
	}

	public void setUserMapper(UserMapperImp userMapper) {
		this.userMapper = userMapper;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "User [ID=" + ID + ", username=" + username + ", password=" + password + "]";
	}

	@Override
	public boolean equals(Object other){
		boolean result=false;
		if(other instanceof UserImp && ((UserImp)other).getID() == this.getID()){
		    result=true;
		}
		return result;
	}
	
	//service 
	public UserImp getUser(String username){
		return userMapper.getUser(username);
	}
}
