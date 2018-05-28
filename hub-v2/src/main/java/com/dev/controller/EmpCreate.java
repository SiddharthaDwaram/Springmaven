package com.dev.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.dev.DAO.CreateEDAO;
//import com.dev.bean.Employee;
//import com.dev.bean.Employee;
import com.dev.bean.EmpCreateB;



/**
 * Servlet implementation class LoginController
 */
public class EmpCreate extends HttpServlet {

	//private EmpDAO empdao;
	// FOR HR UPDATE EMPLOYEE DETAILS
	CreateEDAO empdao=new CreateEDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(true);
			
			if (request.getParameter("CMD").equalsIgnoreCase("INSERT"))

			{
				empInsert(request, response);
			}
			else if (request.getParameter("CMD").equalsIgnoreCase("UPDATE")){
				
				empUpdate(request, response);
				}
			
			else {
				System.out.println("No CMD");
				response.getWriter().println("Invalid Command");
				
				}
		} catch (Exception e) {
			response.getWriter().println("HanaServlet failed with reason" + e.getMessage());
			

		}

	}

	public void empInsert(HttpServletRequest request, HttpServletResponse response)  {

        // Insert Employee table
        
        try {
               EmpCreateB emp = new EmpCreateB();
               String estatus="";
               emp=getandparsejson(request, response);
               
               if(emp==null)
               {
                     
                     response.getWriter().println("UpdateEmployee-No input data found to update Employee table" );
               }
               else{
                     estatus=empdao.InsertEmp(emp);
                    
                     if (estatus.equalsIgnoreCase("200-Sucess"))
                     {
                            response.getWriter().println("Employee Created sucessfully" );
                            response.setStatus(200);
                     }
                     else if (estatus.equalsIgnoreCase("Employee already exists"))
                     {
                     response.getWriter().println("Employee already exists" );
                     response.setStatus(400);
                     }
                     else if (estatus.equalsIgnoreCase("DBerror"))
                     {
                     response.getWriter().println("Employee Update fail-Database error .kindly contact system admin" );
                     response.setStatus(400);
                     }
                     else{
                    	 response.getWriter().println("Employee Update fail-" +estatus);
                         response.setStatus(400);
                     }              
                                                 
               }
               
        } catch (Exception e) {
               try {
                     response.getWriter().println("Employee Database updation failed-database connection Fail");
               } catch (IOException e1) {
                     // TODO Auto-generated catch block
                     e1.printStackTrace();
               }
               e.getMessage();
               
               }

 }

	public void empUpdate(HttpServletRequest request, HttpServletResponse response)  {

        // Update Employee table
        
        try {
               EmpCreateB emp = new EmpCreateB();
               String estatus="";
               emp=getandparsejson(request, response);
               
               if(emp==null)
               {
                     
                     response.getWriter().println("UpdateEmployee-No input data found to update Employee table" );
               }
               else{
                     estatus=empdao.UpdateEmp(emp);
                    
                     if (estatus.equalsIgnoreCase("202-Sucess"))
                     {
                            response.getWriter().println("Employee Update sucessfully" );
                            response.setStatus(200);
                     }
                     else if (estatus.equalsIgnoreCase("notauthorized"))
                     {
                     response.getWriter().println("Employee is not authorized to update Employee" );
                     response.setStatus(400);
                     }
                     else if (estatus.equalsIgnoreCase("DBerror"))
                     {
                     response.getWriter().println("Employee Update fail-Database error .kindly contact system admin" );
                     response.setStatus(400);
                     }
                     else{
                    	 response.getWriter().println(estatus);
                         response.setStatus(200);
                     }
                                   
                                                 
               }
               
        } catch (Exception e) {
               
               e.getMessage();
               
               }

 }
	//Get json data and convert to object-Employee
    public EmpCreateB getandparsejson(HttpServletRequest request, HttpServletResponse response) throws SQLException{
           EmpCreateB emp = new EmpCreateB();
                        try {
                               
                               BufferedReader reader = request.getReader();
                               StringBuilder sb = new StringBuilder();
                               String line = reader.readLine();
                               JSONObject obj = new JSONObject();

                               while (line != null) {
                                      sb.append(line + "\n");
                                      line = reader.readLine();
                               }
                               reader.close();
                               String params = sb.toString();
                               String[] _params = params.split("&");
                               String jsondata = null;
                               for (String param : _params) {
                                      System.out.println("params(POST)-->" + param);
                                      jsondata = param.toString();
                               }
                               System.out.println("jsondata is -------" + jsondata);
                               // Convert json string to java object
                               try {
                                      JSONParser parser = new JSONParser();
                                      obj = (JSONObject) parser.parse(jsondata);
                                                                       
                                     
                                      /******************************INSERT/Update EMPLOYEE TABLE******************************************/
                                     // if(request.getParameter("CMD").equalsIgnoreCase("INSERT"))
                                      
                                     // {                    
                                                                                                       
                                            String empno = String.valueOf(obj.get("EMPNO"));  
                                            String empname = String.valueOf(obj.get("EMPNAME"));
                                             String firstName = String.valueOf(obj.get("FIRST_NAME"));
                                             String contact = String.valueOf(obj.get("CONTACT"));
                                             String city = String.valueOf(obj.get("CITY"));
                                             String email = String.valueOf(obj.get("EMAIL"));
                                             String pincode = String.valueOf(obj.get("PINCODE"));
                                             String sex = String.valueOf(obj.get("SEX"));
                                             String degree = String.valueOf(obj.get("HIGH_DEGREE"));
                                             String emptype=String.valueOf(obj.get("EMP_TYP"));
                                             String level = String.valueOf(obj.get("LEVL"));
                                             String mgrNo = String.valueOf(obj.get("MGR_NO"));
                                             String ismgr = String.valueOf(obj.get("IS_MGR"));
                                             String loc = String.valueOf(obj.get("LOC"));
                                             String hireDate = String.valueOf(obj.get("HIRE_DT"));
                                             String resDate = String.valueOf(obj.get("RES_DT"));
                                             String exitDate = String.valueOf(obj.get("EXIT_DT"));
                                             String cv = String.valueOf(obj.get("CV_ATTACHED"));
                                             String profile = String.valueOf(obj.get("PROFILE_ATTACHED"));                                                            
                                             
                                             
                                             
                                             
                                             /*if (empno != null && contact != null && city != null && !empno.trim().isEmpty() && !contact.trim().isEmpty()
                                                          && !city.trim().isEmpty()) {*/
                                             
                                             if (empno != null && !empno.trim().isEmpty())
                                             {
                                                   
                                                   //Employee emp = new Employee();
                                                   System.out.println("empno TEST "+empno);
                                                   emp.setEmpNo(empno);
                                                   emp.setEmpName(empname);
                                                   emp.setFirstname(firstName);
                                                   emp.setContact(contact);
                                                   emp.setCity(city);
                                                   emp.setEmail(email);
                                                   emp.setPincode(pincode);
                                                   emp.setSex(sex);
                                                   emp.setHighdegree(degree);
                                                   emp.setEmpType(emptype);
                                                   emp.setLevel(level);
                                                   emp.setMgrNo(mgrNo);
                                                   emp.setLoc(loc);
                                                   emp.setHdate(hireDate);
                                                  emp.setResDate(resDate);
                                                   emp.setExitdate(exitDate);
                                                   emp.setCv_attached(cv);
                                                    emp.setProfile_attached(profile);
                                                   emp.setIs_mgr(ismgr);
                                                   
                                                   
                                                   System.out.println("-----------EMP number--------- "+emp.getEmpNo());
                                             }
                                             else{
                                                    response.getWriter().println(" getandparsejson-To update Employee table need all input data");
                                                   
                                             }
                                      return emp;
                                     // }
                        
                                      
                                      /******************************UPDATE EMPLOYEE TABLE******************************************/
                                                   /* if(request.getParameter("CMD").equalsIgnoreCase("UPDATE"))
                                                    
                                                    {                    
                                                                                                                     
                                                          String empno = String.valueOf(obj.get("EMPNO"));  
                                                          String empname = String.valueOf(obj.get("EMPNAME"));
                                                           String firstName = String.valueOf(obj.get("FIRST_NAME"));
                                                           String contact = String.valueOf(obj.get("CONTACT"));
                                                           String city = String.valueOf(obj.get("CITY"));
                                                           String email = String.valueOf(obj.get("EMAIL"));
                                                           String pincode = String.valueOf(obj.get("PINCODE"));
                                                           String sex = String.valueOf(obj.get("SEX"));
                                                           String degree = String.valueOf(obj.get("HIGH_DEGREE"));
                                                           String emptype=String.valueOf(obj.get("EMP_TYP"));
                                                           String level = String.valueOf(obj.get("LEVL"));
                                                           String mgrNo = String.valueOf(obj.get("MGR_NO"));
                                                           String ismgr = String.valueOf(obj.get("IS_MGR"));
                                                           String loc = String.valueOf(obj.get("LOC"));
                                                           String hireDate = String.valueOf(obj.get("HIRE_DT"));
                                                           String resDate = String.valueOf(obj.get("RES_DT"));
                                                           String exitDate = String.valueOf(obj.get("EXIT_DT"));
                                                           String cv = String.valueOf(obj.get("CV_ATTACHED"));
                                                           String profile = String.valueOf(obj.get("PROFILE_ATTACHED"));                                                            
                                                           
                                                           
                                                           
                                                           if (empno != null && !empno.trim().isEmpty())
                                                           {
                                                                 
                                                                 //Employee emp = new Employee();
                                                                 System.out.println("empno TEST "+empno);
                                                                 emp.setEmpNo(empno);
                                                                 emp.setEmpName(empname);
                                                                 emp.setFirstname(firstName);
                                                                 emp.setContact(contact);
                                                                 emp.setCity(city);
                                                                 emp.setEmail(email);
                                                                 emp.setPincode(pincode);
                                                                 emp.setSex(sex);
                                                                 emp.setHighdegree(degree);
                                                                 emp.setEmpType(emptype);
                                                                 emp.setLevel(level);
                                                                 emp.setMgrNo(mgrNo);
                                                                 emp.setLoc(loc);
                                                                 emp.setHdate(hireDate);
                                                                 emp.setResDate(resDate);
                                                                 emp.setExitdate(exitDate);
                                                                 emp.setCv_attached(cv);
                                                                  emp.setProfile_attached(profile);
                                                                 emp.setIs_mgr(ismgr);
                                                                 
                                                                 
                                                                 System.out.println("-----------EMP number--------- "+emp.getEmpNo());
                                                           }
                                                           else{
                                                                  response.getWriter().println(" getandparsejson-To update Employee table need all input data");
                                                                 
                                                           }
                                                    return emp;
                                                    }*/
                                      
                                      
                               }
                               catch (ParseException e) {
                                      e.printStackTrace();

                               }
                        }
                        catch(Exception e)
                        {
                               e.printStackTrace();
                        }
                        return emp;
                 }

}
	
	
