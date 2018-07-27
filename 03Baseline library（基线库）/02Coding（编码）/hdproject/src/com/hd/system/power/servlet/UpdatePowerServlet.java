package com.hd.system.power.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;

import com.hd.system.power.mapper.PowerMapper;
import com.hd.tools.DBTools;
import com.hd.tools.Response;

@WebServlet("/updatePower")
public class UpdatePowerServlet extends HttpServlet {

	public UpdatePowerServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean type2 = Integer.parseInt(request.getParameter("type2")) != 0;
		boolean type3 = Integer.parseInt(request.getParameter("type3")) != 0;
		boolean type4 = Integer.parseInt(request.getParameter("type4")) != 0;
		
		Response res = new Response(null,"0","");
		SqlSession sqlSession = DBTools.getSession();
		PowerMapper mapper = sqlSession.getMapper(PowerMapper.class);
		try {
			mapper.updatePower(2, type2);
			mapper.updatePower(3, type3);
			mapper.updatePower(4, type4);
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
