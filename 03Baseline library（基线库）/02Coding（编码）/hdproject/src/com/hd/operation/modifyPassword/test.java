package com.hd.operation.modifyPassword;

import java.io.PrintWriter;
import java.nio.MappedByteBuffer;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;

import com.hd.accountBusinessContactsmapper.AccountMapper;
import com.hd.beans.Account;
import com.hd.tools.DBTools;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String oldpsd="123456";
		String newpsd="12345678";
		String confirm="12345678";
		
		SqlSession session=DBTools.getSession();
		Account account = null;
		AccountMapper accountMapper=session.getMapper(AccountMapper.class);
		//UserMapper mapper=session.getMapper(UserMapper.class);
		try{
			account=accountMapper.selectAccountById(1);
		//	UserBean user=mapper.selectUserById(2);
			System.out.println(account.toString());
		
			session.commit();
		}catch (Exception e) {
			e.printStackTrace();
			session.rollback();
			// TODO: handle exception
		}
      
      	if(oldpsd== null||newpsd==null||confirm==null){
      			System.out.println("输入错误");
      			return;
      	}
      	
      	String correct_password = "123456";//正确原密码
    
		if(correct_password!=oldpsd){//原密码与正确密码不一致
			System.out.println("原密码输入错误");
  		
  			return;//退出doPost方法
		}else {//两次输入密码不一致
			if(newpsd!=confirm){
				System.out.println("两次新密码输入不一致！");
      			return;//退出doPost方法
			}
			else{//修改密码操作
				account.setAcc_psd(newpsd);
				SqlSession session1=DBTools.getSession();
				AccountMapper mapper=session1.getMapper(AccountMapper.class);
				try{
					mapper.updateAccount(account,1);
					System.out.println(account.toString());
					session1.commit();
			    	}catch (Exception e) {
					e.printStackTrace();
					session1.rollback();
					System.out.println("出错了");
					//res.setStatus("3");res.setMessage("数据库操作出错！");
	      		//	JSONObject resJson = new JSONObject(res);
	      			
		  			return;
				} finally {
					session1.close();
				}
	

	}
		}

}
}
