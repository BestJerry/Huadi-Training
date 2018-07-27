package com.hd.subaccount.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;

import com.hd.accountBusinessContactsmapper.AccountMapper;
import com.hd.beans.Account;
import com.hd.tools.DBTools;
import com.hd.tools.Response;

@WebServlet("/addSubaccount")
public class AddSubaccountServlet extends HttpServlet {

	public AddSubaccountServlet() {
		super();
	}

	public void destroy() {
		super.destroy();
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Response res = new Response(null, "0" ,"");
		int account_type = Integer.parseInt(request.getParameter("account_type"));
		boolean isStart = Integer.parseInt(request.getParameter("isStart")) != 0;
		
		SqlSession sqlSession = DBTools.getSession();
		AccountMapper mapper = sqlSession.getMapper(AccountMapper.class);
		
		//随机分配账户登录名
        String acc_name=getAccName();
      	while(hasSameName(acc_name,mapper)){
      		acc_name=getAccName();
      	}
      	//随机分配6位账户密码
      	String acc_psd="";
      	String numsString="0123456789";
      	for(int i=0;i<6;i++){
      		acc_psd+=numsString.charAt((int)(Math.random()*10));
      	}
      	int bus_id = ((Account)request.getSession().getAttribute("account")).getBus_id();
      	
      	try {
      		Account account = new Account(0,account_type,bus_id,acc_name,acc_psd,isStart,null);
      		mapper.insertAccount(account);
      		sqlSession.commit();
      		res.setData(account);
      	} catch(Exception ex) {
      		ex.printStackTrace();
      		sqlSession.rollback();
      		res.setStatus("3");
      		res.setMessage("数据库操作出错");
      	} finally {
      		sqlSession.close();
      	}
      	response.getWriter().print(new JSONObject(res));
	}
	
	/**
     * 随机生成8位账号，其中前四位为随机字母，后四位为随机数字
     */
    private String getAccName(){
    	String acc_name="";
    	Random random=new Random();
      	String charsString="abcdefghijklmnopqrstuvwxyz";
      	for(int i=0;i<4;i++){
      		acc_name+=charsString.charAt((int)(Math.random()*26));
      	}
      	String numsString="0123456789";
      	for(int i=0;i<4;i++){
      		acc_name+=numsString.charAt((int)(Math.random()*10));
      	}
      
      	return acc_name;
    }
    
    private boolean hasSameName(String acc_name,AccountMapper mapper) {
    	return mapper.selectAccountByName(acc_name) != null;
    }


	public void init() throws ServletException {
	}

}
