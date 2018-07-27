package com.hd.register;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;

import com.hd.accountBusinessContactsmapper.AccountMapper;
import com.hd.accountBusinessContactsmapper.BusinessMapper;
import com.hd.accountBusinessContactsmapper.ContactsMapper;
import com.hd.beans.Account;
import com.hd.beans.Business;
import com.hd.beans.Contacts;

import com.hd.tools.DBTools;
import com.hd.tools.Response;


@WebServlet(urlPatterns={"/register"})
public class registerServlet extends HttpServlet {
	
	
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
	    	doPost(request, response);
	}
     
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
          	String bus_type=request.getParameter("bus_type");
          	String bus_name=request.getParameter("bus_name");
          	String bus_add=request.getParameter("bus_add");
          	String bus_phone=request.getParameter("bus_phone");
          	String bus_postcode=request.getParameter("bus_postcode");if(bus_postcode.trim().equals(""))bus_postcode=null;
          	int bus_star=Integer.parseInt(request.getParameter("bus_star"));
          	double con_intergral=Double.parseDouble(request.getParameter("con_intergral"));
          	double discount=Double.parseDouble(request.getParameter("discount"));
          	String con_title=request.getParameter("con_title");
          	
          	String con_name=request.getParameter("con_name");
          	String con_position=request.getParameter("con_position");
          	String con_tel=request.getParameter("con_tel");if(con_tel.trim().equals(""))con_tel=null;
          	String con_mobile=request.getParameter("con_mobile");if(con_mobile.trim().equals(""))con_mobile=null;
          	String con_fax=request.getParameter("con_fax");if(con_fax.trim().equals(""))con_fax=null;
          	String con_email=request.getParameter("con_email");
          	String verificationCode=request.getParameter("verificationCode");
          	
          	Response res = new Response(null,"0","");//初始化响应对象
          	PrintWriter out = response.getWriter();//初始化响应的输出对象
          	if(verificationCode == null || verificationCode.equals("") || 
          			!verificationCode.equals(request.getSession().getAttribute("verificationCode"))) {
          			//检验验证码是否正确
          			res.setStatus("1");res.setMessage("验证码错误");
          			//System.out.println
          			JSONObject resJson = new JSONObject(res);//将对象转换成json各式
          			out.print(resJson);//输出json字符串
          			return;//退出doPost方法
          	}
          	
          	int con_id=insertContacts(con_title,con_name,con_position,con_tel,con_mobile,con_fax,con_email);
          	int bus_id = insertBusiness(con_id, bus_type, bus_name, bus_add, bus_phone, bus_postcode, bus_star, con_intergral, discount);
            
            //随机分配初始账户
            String acc_name=getAccName();
          	while(isSame(acc_name)==true){
          		acc_name=getAccName();
          	}
          	//初始密码固定为123456
          	String acc_psd="123456";
          	
          	//插入新账号
          	Account account = new Account(0,1,bus_id,acc_name,acc_psd,true,null);
          	SqlSession session=DBTools.getSession();
    		AccountMapper mapper=session.getMapper(AccountMapper.class);
    		try {
				mapper.insertAccount(account);
				session.commit();
			} catch (Exception e) {
				e.printStackTrace();
				session.rollback();
				res.setStatus("3");res.setMessage("数据库操作出错！");
      			JSONObject resJson = new JSONObject(res);
      			out.print(resJson);
      			return;
			} finally {
				session.close();//session使用完后关闭
			}
    		
    		res.setData(account);
  			JSONObject resJson = new JSONObject(res);//将对象转换成json各式
  			out.print(resJson);//输出json字符串
  			return;//退出doPost方法
    }
    
    /**
     * 随机生成8位账号，其中前四位为随机字母，后四位为随机数字
     */
    private String getAccName(){
    	String acc_name="";
    	Random random=new Random();
      	String charsString="abcdefghijklmnopqrstuvwxyz";
      	for(int i=0;i<4;i++){
      		acc_name+=charsString.charAt((int)(Math.random()*26));
      	}
      	String numsString="0123456789";
      	for(int i=0;i<4;i++){
      		acc_name+=numsString.charAt((int)(Math.random()*10));
      	}
      
      	return acc_name;
    }
    
    
    /**
     * 检查数据库中有无重复的账号
     * @return
     */
    private boolean isSame(String acc_name){
    	SqlSession session=DBTools.getSession();
		AccountMapper mapper=session.getMapper(AccountMapper.class);
		boolean isSame=false;
		try{
			Account account = mapper.selectAccountByName(acc_name);
			if(account != null) isSame = true;
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return isSame;
    }
    
    /**
     * 新增联系人
     */
    public static int insertContacts(String con_title,String con_name,String con_position,String con_tel,String con_mobile,String con_fax,String con_email){
    	int con_id = 0;//要获取的自增主键
		SqlSession session=DBTools.getSession();
		ContactsMapper mapper=session.getMapper(ContactsMapper.class);
		Contacts contacts=new Contacts(con_title, con_name, con_position, con_tel, con_mobile, con_fax, con_email);
		try{
			mapper.insertContacts(contacts);
			con_id=contacts.getCon_id();
			session.commit();
		}catch (Exception e) {
		    e.printStackTrace();
		    System.out.println("contacts fail");
		    session.rollback();
		} finally {
			session.close();
		}
		return con_id;
	}
	
    
    /**
	 * 新增商户
	 */
	public static int insertBusiness(int con_id,String bus_type,String bus_name,String bus_add,String bus_phone,String bus_postcode,int bus_star,double con_intergral,double discount){
		SqlSession session=DBTools.getSession();
		BusinessMapper mapper=session.getMapper(BusinessMapper.class);
		Business business=new Business(con_id, bus_type, bus_name, bus_add, bus_phone, bus_postcode, bus_star, con_intergral, discount);
		int bus_id = 0;//要获取的自增主键
		try{
			mapper.insertBusiness(business);
			bus_id= business.getBus_id();
			session.commit();
		}catch (Exception e) {
		    e.printStackTrace();
		    session.rollback();
		} finally {
			session.close();
		}
		return bus_id;
	}
}
