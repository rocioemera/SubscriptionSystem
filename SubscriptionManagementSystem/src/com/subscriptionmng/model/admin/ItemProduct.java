package com.subscriptionmng.model.admin;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.InheritanceType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("PRODUCT")
public class ItemProduct extends ItemImp{
	private Integer stock;
	private String family;
	
	@Column(name = "stock",insertable=true, updatable=true)
	public Integer getStock() {
		return stock;
	}
	
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
	@Column(name = "family",insertable=true, updatable=true)
	public String getFamily() {
		return family;
	}
	
	public void setFamily(String family) {
		this.family = family;
	}
}
