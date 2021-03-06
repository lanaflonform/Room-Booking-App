package com.team.application.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="employee")
public class Employee {
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "hotel_id", nullable = false) 
	private Hotel hotel;
	
	@Id
	private String sin;
	
	@OneToMany(mappedBy="employee",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<EmployeeRole> employee_roles=new ArrayList<>();
	
	
    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<CheckedIn> checked_in=new ArrayList<>();
	
	private String given_name;
	private String family_name;
	
	private String street_name;
    private Integer street_number;
    private String city ;
    private String state ;
    private String country ;
    
	public Employee(Hotel hotel, String sin,
			String given_name, String family_name, String street_name, Integer street_number, String city, String state,
			String country) {
		this.hotel = hotel;
		this.sin = sin;
		this.given_name = given_name;
		this.family_name = family_name;
		this.street_name = street_name;
		this.street_number = street_number;
		this.city = city;
		this.state = state;
		this.country = country;
	}
	
	
	public Employee() {}


	public Hotel getHotel() {
		return hotel;
	}
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	public String getSin() {
		return sin;
	}
	public void setSin(String sin) {
		this.sin = sin;
	}
	public String getGiven_name() {
		return given_name;
	}
	public void setGiven_name(String given_name) {
		this.given_name = given_name;
	}
	public String getFamily_name() {
		return family_name;
	}
	public void setFamily_name(String family_name) {
		this.family_name = family_name;
	}
	public String getStreet_name() {
		return street_name;
	}
	public void setStreet_name(String street_name) {
		this.street_name = street_name;
	}
	public Integer getStreet_number() {
		return street_number;
	}
	public void setStreet_number(Integer street_number) {
		this.street_number = street_number;
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
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
}
