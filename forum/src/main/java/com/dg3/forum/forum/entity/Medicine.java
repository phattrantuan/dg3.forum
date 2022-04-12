package com.dg3.forum.forum.entity;

import lombok.Data;

@Data
public class Medicine {
	private Long dealer_pk;
	private Long medicine_pk;
	private String name_medicine;
	private String where_production;
	private String price;
	private String effect;
	private String details_medicine;
	public Medicine(Long dealer_pk, Long medicine_pk, String name_medicine, String where_production, String price,
			String effect, String details_medicine) {
		super();
		this.dealer_pk = dealer_pk;
		this.medicine_pk = medicine_pk;
		this.name_medicine = name_medicine;
		this.where_production = where_production;
		this.price = price;
		this.effect = effect;
		this.details_medicine = details_medicine;
	}
	public Medicine() {
		
	}
}
