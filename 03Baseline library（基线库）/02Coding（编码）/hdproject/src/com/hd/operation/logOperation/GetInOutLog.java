package com.hd.operation.logOperation;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;

import com.hd.beans.Account;
import com.hd.beans.Log;
import com.hd.logOperationMapper.LogMapper;
import com.hd.tools.DBTools;
import com.hd.tools.Response;
@WebServlet("/getInOutLog")
public class GetInOutLog extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public GetInOutLog() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SqlSession session = DBTools.getSession();
		LogMapper mapper = session.getMapper(LogMapper.class);
		Account account = (Account)request.getSession().getAttribute("account");
		List<Log> logs = mapper.selectByAccount(account.getAcc_id());
		response.getWriter().print(new JSONObject(new Response(logs,"0","")));
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public void init() throws ServletException {
		
	}

}
