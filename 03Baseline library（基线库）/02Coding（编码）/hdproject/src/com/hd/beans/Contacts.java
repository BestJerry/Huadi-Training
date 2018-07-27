package com.hd.beans;

import java.util.List;

public class Contacts {
     private int con_id;
     private String con_title;
     private String con_name;
     private String con_position;
     private String con_tel;
     private String con_mobile;
     private String con_fax;
     private String con_email;
     public Contacts(){
    	 
     }
     
	  public Contacts(String con_title, String con_name, String con_position, String con_tel, String con_mobile,
			String con_fax, String con_email) {
		super();
		this.con_title = con_title;
		this.con_name = con_name;
		this.con_position = con_position;
		this.con_tel = con_tel;
		this.con_mobile = con_mobile;
		this.con_fax = con_fax;
		this.con_email = con_email;
	}

	public Contacts(int con_id, String con_title, String con_name, String con_position, String con_tel, String con_mobile,
			String con_fax, String con_email) {
		super();
		this.con_id = con_id;
		this.con_title = con_title;
		this.con_name = con_name;
		this.con_position = con_position;
		this.con_tel = con_tel;
		this.con_mobile = con_mobile;
		this.con_fax = con_fax;
		this.con_email = con_email;
	}
	public int getCon_id() {
		return con_id;
	}
	public void setCon_id(int con_id) {
		this.con_id = con_id;
	}
	public String getCon_title() {
		return con_title;
	}
	public void setCon_title(String con_title) {
		this.con_title = con_title;
	}
	public String getCon_name() {
		return con_name;
	}
	public void setCon_name(String con_name) {
		this.con_name = con_name;
	}
	public String getCon_position() {
		return con_position;
	}
	public void setCon_position(String con_position) {
		this.con_position = con_position;
	}
	public String getCon_tel() {
		return con_tel;
	}
	public void setCon_tel(String con_tel) {
		this.con_tel = con_tel;
	}
	public String getCon_mobile() {
		return con_mobile;
	}
	public void setCon_mobile(String con_mobile) {
		this.con_mobile = con_mobile;
	}
	public String getCon_fax() {
		return con_fax;
	}
	public void setCon_fax(String con_fax) {
		this.con_fax = con_fax;
	}
	public String getCon_email() {
		return con_email;
	}
	public void setCon_email(String con_email) {
		this.con_email = con_email;
	}
	@Override
	public String toString() {
		return "contacts [con_id=" + con_id + ", com_title=" + con_title + ", con_name=" + con_name + ", con_position="
				+ con_position + ", con_tel=" + con_tel + ", con_mobile=" + con_mobile + ", con_fax=" + con_fax
				+ ", con_email=" + con_email + "]";
	}
     
}
