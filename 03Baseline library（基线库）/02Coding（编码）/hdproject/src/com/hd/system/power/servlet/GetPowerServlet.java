package com.hd.system.power.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

@WebServlet("/getPower")
public class GetPowerServlet extends HttpServlet {


	public GetPowerServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SqlSession sqlSession = DBTools.getSession();
		PowerMapper mapper = sqlSession.getMapper(PowerMapper.class);
		Response res = new Response(null,"0","");
		List<Boolean> list = null;
		try {
			list = mapper.selectPower();
		} catch(Exception ex) {
			ex.printStackTrace();
			sqlSession.rollback();
			res.setStatus("3");
			res.setMessage("数据库操作出错！");
		} finally {
			sqlSession.close();
		}
		Map<String,Object> map = new HashMap();
		map.put("type2", list.get(0)==true?1:0);map.put("type3", list.get(1)==true?1:0);map.put("type4", list.get(2)==true?1:0);
		res.setData(map);
		System.out.println(new JSONObject(res));
		response.getWriter().print(new JSONObject(res));
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
