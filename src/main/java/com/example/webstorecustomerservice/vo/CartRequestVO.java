package com.example.webstorecustomerservice.vo;

import lombok.Data;

@Data
public class CartRequestVO {

	private Integer customerId;

	public CartRequestVO(Integer customerId) {
		this.customerId = customerId;
	}

}
