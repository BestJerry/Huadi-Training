package com.hd.beans;

public class Business {
    private int bus_id;
    private int con_id;
    private String bus_type;
    private String bus_name;
    private String bus_add;
    private String bus_phone;
    private String bus_postcode;
    private int bus_star;
    private double con_intergral;
    private double discount;
    public Business(){
    	
    }
    
	public Business(int con_id, String bus_type, String bus_name, String bus_add, String bus_phone, String bus_postcode,
			int bus_star, double con_intergral, double discount) {
		super();
		this.con_id = con_id;
		this.bus_type = bus_type;
		this.bus_name = bus_name;
		this.bus_add = bus_add;
		this.bus_phone = bus_phone;
		this.bus_postcode = bus_postcode;
		this.bus_star = bus_star;
		this.con_intergral = con_intergral;
		this.discount = discount;
	}

	public Business(int bus_id, int con_id, String bus_type, String bus_name, String bus_add, String bus_phone,
			String bus_postcode, int bus_star, double con_intergral, double discount) {
		super();
		this.bus_id = bus_id;
		this.con_id = con_id;
		this.bus_type = bus_type;
		this.bus_name = bus_name;
		this.bus_add = bus_add;
		this.bus_phone = bus_phone;
		this.bus_postcode = bus_postcode;
		this.bus_star = bus_star;
		this.con_intergral = con_intergral;
		this.discount = discount;
	}
	public int getBus_id() {
		return bus_id;
	}
	public void setBus_id(int bus_id) {
		this.bus_id = bus_id;
	}
	public int getCon_id() {
		return con_id;
	}
	public void setCon_id(int con_id) {
		this.con_id = con_id;
	}
	public String getBus_type() {
		return bus_type;
	}
	public void setBus_type(String bus_type) {
		this.bus_type = bus_type;
	}
	public String getBus_name() {
		return bus_name;
	}
	public void setBus_name(String bus_name) {
		this.bus_name = bus_name;
	}
	public String getBus_add() {
		return bus_add;
	}
	public void setBus_add(String bus_add) {
		this.bus_add = bus_add;
	}
	public String getBus_phone() {
		return bus_phone;
	}
	public void setBus_phone(String bus_phone) {
		this.bus_phone = bus_phone;
	}
	public String getBus_postcode() {
		return bus_postcode;
	}
	public void setBus_postcode(String bus_postcode) {
		this.bus_postcode = bus_postcode;
	}
	public int getBus_star() {
		return bus_star;
	}
	public void setBus_star(int bus_star) {
		this.bus_star = bus_star;
	}
	public double getCon_intergral() {
		return con_intergral;
	}
	public void setCon_intergral(double con_intergral) {
		this.con_intergral = con_intergral;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	@Override
	public String toString() {
		return "business [bus_id=" + bus_id + ", con_id=" + con_id + ", bus_type=" + bus_type + ", bus_name=" + bus_name
				+ ", bus_add=" + bus_add + ", bus_phone=" + bus_phone + ", bus_postcode=" + bus_postcode + ", bus_star="
				+ bus_star + ", con_intergral=" + con_intergral + ", discount=" + discount + "]";
	}
    
}
