package com.hd.accountBusinessContactsmapper;

import java.util.List;
import com.hd.beans.Business;

public interface BusinessMapper {
	/**
	  * 新增商家
	  * @param business
	  * @return
	  * throws Exception
	  */
     public int insertBusiness(Business business) throws Exception;
     
     /**
      * 修改商家
      * @param business
      * @param bus_id
      * @return
      * @throws Exception
      * 
      */
     public int updateBusiness(Business business,int bus_id) throws Exception;
     
     /**
      * 删除商家
      * @param business
      * @param bus_id
      * @return 
      * @throws Exception
      */
     public int deleteBusiness(int bus_id)throws Exception;
     
     /**
      * 根据bus_id查询商家信息
      * @param bus_id
      * @return
      * @throws Exception
      */
     public Business selectBusinessById(int bus_id)throws Exception;
     
     /**
      * 查询所有的商家信息
      * 
      */
     public List<Business> selectAllBusiness() throws Exception;
}
