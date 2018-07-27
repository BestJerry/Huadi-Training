package com.hd.operation.modifyPassword;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import com.hd.accountBusinessContactsmapper.AccountMapper;
import com.hd.beans.Account;
import com.hd.tools.DBTools;
import com.hd.tools.Response;

@WebServlet(urlPatterns={"/updatePassword"})
public class updatePassword extends HttpServlet {
	Response res = new Response(null,"0","");//初始化响应对象
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String oldpsd=request.getParameter("oldpsd");
		String newpsd=request.getParameter("newpsd");
		String confirm=request.getParameter("confirm");
		
    	
      	PrintWriter out = response.getWriter();//初始化响应的输出对象
      	if(oldpsd== null||newpsd==null||confirm==null){
      			res.setStatus("1");res.setMessage("输入错误");
      			JSONObject resJson = new JSONObject(res);//将对象转换成json各式
      			out.print(resJson);//输出json字符串
      			return;//退出doPost方法
      	}
      	
      	HttpSession httpSession = request.getSession();
      	Account account=(Account)httpSession.getAttribute("Account");
	    String correct_password=account.getAcc_psd();//获取正确密码
       
		
		if(correct_password!=oldpsd){//原密码与正确密码不一致
			res.setStatus("1");res.setMessage("原密码输入错误");
  			JSONObject resJson = new JSONObject(res);//将对象转换成json各式
  			out.print(resJson);
  			return;
		}else {
			if(newpsd!=confirm){//两次输入密码不一致
				res.setStatus("1");res.setMessage("两次新密码输入不一致！");
      			JSONObject resJson = new JSONObject(res);//将对象转换成json各式
      			out.print(resJson);//输出json字符串
      			return;//退出doPost方法
			}
			else{//修改密码操作
				account.setAcc_psd(newpsd);
				//数据库修改
				int acc_id=account.getAcc_id();
				SqlSession session=DBTools.getSession();
				AccountMapper mapper=session.getMapper(AccountMapper.class);
				try{
				     mapper.updateAccount(account,acc_id);
					System.out.println(account.toString());
					session.commit();
			    	}catch (Exception e) {
					e.printStackTrace();
					session.rollback();
					res.setStatus("3");res.setMessage("数据库操作出错！");
	      			JSONObject resJson = new JSONObject(res);
	      			out.print(resJson);
		  			return;
				} finally {
					session.close();
				}
			}
		}
		    //res.setData(account);
		    res.setStatus("0");
		    res.setMessage("修改密码成功!");
			JSONObject resJson = new JSONObject(res);//将对象转换成json各式
			out.print(resJson);//输出json字符串
			return;//退出doPost方法

	}

}
