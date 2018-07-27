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
import com.hd.system.power.mapper.PowerMapper;
import com.hd.tools.DBTools;
import com.hd.tools.Response;

@WebServlet("/updateParameter")
public class UpdateParameterServlet extends HttpServlet {

	public UpdateParameterServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		double con_intergral = Double.parseDouble(request.getParameter("con_intergral"));
		double discount = Double.parseDouble(request.getParameter("discount"));
		
		Response res = new Response(null,"0","");
		SqlSession sqlSession = DBTools.getSession();
		BusinessMapper mapper = sqlSession.getMapper(BusinessMapper.class);
		
		Account account = (Account)request.getSession().getAttribute("account");
		int bus_id = account.getBus_id();
		Business business = null;
		
		try {
			business = mapper.selectBusinessById(bus_id);
			business.setCon_intergral(con_intergral);
			business.setDiscount(discount);
			mapper.updateBusiness(business, bus_id);
			sqlSession.commit();
		} catch(Exception ex) {
			ex.printStackTrace();
			sqlSession.rollback();
			res.setStatus("3");
			res.setMessage("数据库操作出错！");
		} finally {
			sqlSession.close();
		}
		response.getWriter().print(new JSONObject(res));
	}

}
