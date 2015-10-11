package com.subscriptionmng.model.admin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Version;

import com.subscriptionmng.model.security.UserImp;

@Entity
@Table(name="STAFF")
@PrimaryKeyJoinColumn(name="USER_ID")
public class StaffImp extends UserImp {
	
	private static final long serialVersionUID = 1L;
	private String fullname;
	private String department;
	
	@Column(name = "FULLNAME")
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	@Column(name = "DEPARTMENT")
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	
}
