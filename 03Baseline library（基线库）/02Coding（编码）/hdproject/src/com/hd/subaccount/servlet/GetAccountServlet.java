package com.hd.subaccount.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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

@WebServlet("/getAccount")
public class GetAccountServlet extends HttpServlet {


	public GetAccountServlet() {
		super();
	}


	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tPage = request.getParameter("page");
		String tEach = request.getParameter("countEachPage");
		int page = 1, each = 10;
		if(tPage != null) page = Integer.parseInt(tPage);
		if(tEach != null) each = Integer.parseInt(tEach);
		String account_name = request.getParameter("account_name");
		if(account_name == null) account_name = "";
		else account_name = account_name.trim(); //去除前缀和后缀空格
		
		SqlSession sqlSession = DBTools.getSession();
		AccountMapper mapper = sqlSession.getMapper(AccountMapper.class);
		int start = (page-1)*each;
		
		Account account = (Account)request.getSession().getAttribute("account");
		int bus_id = account.getBus_id();
		
		Response res = new Response(null,"0","");
		List<Account> list = null;
		try {
			list = mapper.searchAccounts(start, each, account_name,bus_id);
		} catch(Exception ex) {
			ex.printStackTrace();
			res.setMessage("数据库查询出错！");
			res.setStatus("3");
		} finally {
			sqlSession.close();
		}
		for(Account temp : list) {
			if(temp.getAccount_type() == 1){
				list.remove(temp);
				break;
			}
		}
		res.setData(list);
		response.getWriter().print(new JSONObject(res));
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	public void init() throws ServletException {
	}

}
