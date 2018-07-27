package com.hd.subaccount.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

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

@WebServlet("/updateSubaccountStatus")
public class UpdateSubaccountStatusServlet extends HttpServlet {

	public UpdateSubaccountStatusServlet() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Response res = new Response(null,"0","");
		int acc_id = Integer.parseInt(request.getParameter("acc_id"));
		int isStart = Integer.parseInt(request.getParameter("isStart"));
		
		SqlSession sqlSession = DBTools.getSession();
		AccountMapper mapper = sqlSession.getMapper(AccountMapper.class);
		try {
			Account account = mapper.selectAccountById(acc_id);
			account.setIsStart(isStart!=0);
			mapper.updateAccount(account, acc_id);
			sqlSession.commit();
		} catch(Exception sqlException){
			sqlException.printStackTrace();
			sqlSession.rollback();
			res.setMessage("数据库操作出错！");
			res.setStatus("3");
		} finally {
			sqlSession.close();
		}
		response.getWriter().print(new JSONObject(res));
		
	}

	public void init() throws ServletException {
	}

}
