/**
 * 
 */
package com.hd.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.hd.tools.Response;

/**
 * @author Jerry
 *
 * @date 2018Äê7ÔÂ14ÈÕ
 */
public interface Controller {
	
	JSONObject handleRequest(HttpServletRequest request, HttpServletResponse response);

}
