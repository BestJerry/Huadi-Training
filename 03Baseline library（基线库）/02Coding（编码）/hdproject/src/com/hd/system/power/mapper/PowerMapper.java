package com.hd.system.power.mapper;

import java.util.List;

public interface PowerMapper {
	
	public int updatePower(int account_type,boolean recharge);
	
	public List<Boolean> selectPower();
	
}
