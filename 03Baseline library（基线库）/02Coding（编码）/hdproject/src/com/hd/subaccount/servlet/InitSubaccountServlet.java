package com.hd.subaccount.servlet;

import java.io.IOException;
import java.io.PrintWriter;

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

@WebServlet("/initSubaccount")
public class InitSubaccountServlet extends HttpServlet {

	public InitSubaccountServlet() {
		super();
	}


	public void destroy() {
		super.destroy();
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int acc_id = Integer.parseInt(request.getParameter("acc_id"));
		SqlSession session = DBTools.getSession();
		AccountMapper mapper = session.getMapper(AccountMapper.class);
		Response res = new Response(null,"0","");
		
		try {
			Account account = mapper.selectAccountById(acc_id);
			account.setAcc_psd("111111");
			mapper.updateAccount(account, acc_id);
			session.commit();
		} catch(Exception sqlException){
			sqlException.printStackTrace();
			session.rollback();
			res.setMessage("数据库操作出错！");
			res.setStatus("3");
		} finally {
			session.close();
		}
		response.getWriter().print(new JSONObject(res));
	}

	public void init() throws ServletException {
	}

}
