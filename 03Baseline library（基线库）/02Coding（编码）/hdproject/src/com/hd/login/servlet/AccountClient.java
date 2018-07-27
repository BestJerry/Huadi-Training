/**
 * 
 */
package com.hd.login.servlet;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.hd.beans.Account;
import com.hd.login.mapper.AccountMapper;
import com.hd.tools.DBTools;

/**
 * @author Jerry
 *
 * @date 2018Äê7ÔÂ14ÈÕ
 */
public class AccountClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		selectAllAccount();
	}

	private static void selectAllAccount() {
		SqlSession session = DBTools.getSession();
		AccountMapper mapper = session.getMapper(AccountMapper.class);
		try {
			List<Account> account = mapper.selectAllAccounts();
			// Account account =
			// mapper.selectAccountByNameAndPsw("jerry","123456");
			System.out.println(account.toString());
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
	}
}
