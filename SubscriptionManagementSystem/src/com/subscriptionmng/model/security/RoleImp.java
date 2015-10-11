package com.subscriptionmng.model.security;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import com.subscriptionmng.data.RoleMapperImp;
import com.subscriptionmng.service.Role;

import javax.persistence.JoinColumn;

@Entity
@Table(name="ROL")
public class RoleImp implements Serializable, Role{
	
	private static final long serialVersionUID = 1L;
	
	private Long ID;
	private String rol_name;
	private Set<UserImp> userRoles;
	private Long version;
	
	//mapper 
	private RoleMapperImp roleMapper;
	
	@Id
    @Column(name = "ROL_ID")
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
	
	@Column(name = "ROLDESCRIPTION",unique = true)
	public String getRol_name() {
		return rol_name;
	}
	public void setRol_name(String rol_name) {
		this.rol_name = rol_name;
	}
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="USER_ROL",
		joinColumns = {@JoinColumn(name="ROL_ID")},
		inverseJoinColumns = {@JoinColumn(name="USER_ID")}
	)
	public Set<UserImp> getUserRoles() {
		return userRoles;
	}
	
	public void setUserRoles(Set<UserImp> userRoles) {
		this.userRoles = userRoles;
	}
	@Transient
	public RoleMapperImp getRoleMapper() {
		return roleMapper;
	}
	public void setRoleMapper(RoleMapperImp roleMapper) {
		this.roleMapper = roleMapper;
	}
	
	public RoleImp getRole(long role_ID){
		return roleMapper.findById(role_ID);
	}
	
}
