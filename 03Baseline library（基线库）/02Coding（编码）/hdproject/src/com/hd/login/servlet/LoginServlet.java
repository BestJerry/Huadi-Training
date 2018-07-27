package com.hd.login.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.hd.login.controller.LoginController;

/**
 * Servlet implementation class DispatcherServlet
 */
@WebServlet(name = "LoginServlet", urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);

	}

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		/*
		 * uri is in this form: /contextName/resourceName, for example:
		 * /app10a/product_input. However, in the event of a default context,
		 * the context name is empty, and uri has this form /resourceName, e.g.:
		 * /product_input
		 */

		LoginController loginController = new LoginController();

		JSONObject jsonObject = loginController.handleRequest(request, response);

		PrintWriter out = response.getWriter();

		out.print(jsonObject);

	}

}
