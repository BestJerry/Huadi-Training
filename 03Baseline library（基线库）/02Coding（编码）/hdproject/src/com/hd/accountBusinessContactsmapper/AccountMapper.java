package com.hd.accountBusinessContactsmapper;

import java.util.List;

import com.hd.beans.Account;

public interface AccountMapper {
	/**
	  * 新增账户
	  * @param account
	  * @return
	  * throws Exception
	  */
     public int insertAccount(Account account);
     
     /**
      * 修改账户
      * @param account
      * @param acc_id
      * @return
      * @throws Exception
      * 
      */
     public int updateAccount(Account account,int acc_id);
     
     /**
      * 删除账户
      * @param Account
      * @param acc_id
      * @return 
      * @throws Exception
      */
     public int deleteAccount(int acc_id);
     
     /**
      * 根据acc_id查询账户信息
      * @param acc_id
      * @return
      * @throws Exception
      */
     public Account selectAccountById(int acc_id);
     
     public Account selectAccountByName(String acc_name);
     
     /**
      * 查询所有的账户信息
      * 
      */
     public List<Account> selectAllAccounts();
     
     public List<Account> searchAccounts(int start, int countEachPage, String account_name,int bus_id);
}
