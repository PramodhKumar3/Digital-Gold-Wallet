package com.cg.gold.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class AddressDTO {

	private Integer addressId;

	@NotBlank(message = "Street is required")
	@Size(max = 255, message = "Street must be less than 255 characters")
	private String street;

	@NotBlank(message = "City is required")
	@Pattern(regexp = "[A-Za-z]+", message = "City should contain only Alphabets")
	@Size(max = 100, message = "City must be less than 100 characters")
	private String city;

	@NotBlank(message = "State is required")
	@Pattern(regexp = "[A-Za-z\\s]+", message = "State should contain only Alphabets and Spaces")
	@Size(max = 100, message = "State must be less than 100 characters")
	private String state;

	@NotBlank(message = "Postal code is required")
	@Pattern(regexp = "\\d{5,6}", message = "Postal code must be 5 or 6 digits")
	private String postalCode;

	@NotBlank(message = "Country is required")
	@Size(max = 100, message = "Country must be less than 100 characters")
	private String country;

	public AddressDTO() {
	}

	public AddressDTO(Integer addressId, String street, String city, String state, String postalCode, String country) {
		this.addressId = addressId;
		this.street = street;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
		this.country = country;
	}

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
