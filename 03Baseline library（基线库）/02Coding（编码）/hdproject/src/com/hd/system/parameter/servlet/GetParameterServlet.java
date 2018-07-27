package com.hd.system.parameter.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;

import com.hd.accountBusinessContactsmapper.BusinessMapper;
import com.hd.beans.Account;
import com.hd.beans.Business;
import com.hd.tools.DBTools;
import com.hd.tools.Response;

@WebServlet("/getParameter")
public class GetParameterServlet extends HttpServlet {

	public GetParameterServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SqlSession sqlSession = DBTools.getSession();
		BusinessMapper mapper = sqlSession.getMapper(BusinessMapper.class);
		Account account = (Account)request.getSession().getAttribute("account");
		int bus_id = account.getBus_id();
		Response res = new Response(null,"0","");
		Business business = null;
		try {
			business = mapper.selectBusinessById(bus_id);
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
			res.setStatus("3");
			res.setMessage("数据库操作出错！");
		} finally {
			sqlSession.close();
		}
		res.setData(business);
		response.getWriter().print(new JSONObject(res));
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
