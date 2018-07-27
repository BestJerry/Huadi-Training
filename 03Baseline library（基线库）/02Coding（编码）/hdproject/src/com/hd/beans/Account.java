package com.hd.beans;

import java.util.Date;

public class Account {
     private int acc_id;
     private int account_type;
     private int bus_id;
     private String acc_name;
     private String acc_psd;
     private boolean isStart;
     private Date acc_time;
     
    public Account(){
    	
    }
	public Account(int acc_id, int account_type, int bus_id, String acc_name, String acc_psd, boolean isStart,
			Date acc_time) {
		super();
		this.acc_id = acc_id;
		this.account_type = account_type;
		this.bus_id = bus_id;
		this.acc_name = acc_name;
		this.acc_psd = acc_psd;
		this.isStart = isStart;
		this.acc_time = acc_time;
	}
	public int getAcc_id() {
		return acc_id;
	}
	public void setAcc_id(int acc_id) {
		this.acc_id = acc_id;
	}
	public int getAccount_type() {
		return account_type;
	}
	public void setAccount_type(int account_type) {
		this.account_type = account_type;
	}
	public int getBus_id() {
		return bus_id;
	}
	public void setBus_id(int bus_id) {
		this.bus_id = bus_id;
	}
	public String getAcc_name() {
		return acc_name;
	}
	public void setAcc_name(String acc_name) {
		this.acc_name = acc_name;
	}
	public String getAcc_psd() {
		return acc_psd;
	}
	public void setAcc_psd(String acc_psd) {
		this.acc_psd = acc_psd;
	}
	public boolean getIsStart() {
		return isStart;
	}
	public void setIsStart(boolean isStart) {
		this.isStart = isStart;
	}
	public Date getAcc_time() {
		return acc_time;
	}
	public void setAcc_time(Date acc_time) {
		this.acc_time = acc_time;
	}
	@Override
	public String toString() {
		return "account [acc_id=" + acc_id + ", account_type=" + account_type + ", bus_id=" + bus_id + ", acc_name="
				+ acc_name + ", acc_psd=" + acc_psd + ", isStart=" + isStart + ", acc_time=" + acc_time + "]";
	}
    
	
}
