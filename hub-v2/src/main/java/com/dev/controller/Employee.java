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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dev.DAO.EmpDAO;
//import com.dev.bean.Employee;
//import com.dev.bean.Employee;
import com.dev.bean.EmployeeB;



/**
 * Servlet implementation class LoginController
 */
public class Employee extends HttpServlet {

	//private EmpDAO empdao;
	EmpDAO empdao=new EmpDAO();
	private static final Logger LOGGER = LoggerFactory.getLogger(Employee.class);
	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(true);
			// String input=request.getParameter("UPDATE");
			if (request.getParameter("CMD").equalsIgnoreCase("UPDATE"))

			{
				empUpdate(request, response);
			}
			else if (request.getParameter("CMD").equalsIgnoreCase("SKILL_UPDATE")){
//				System.out.println("SKILL_UPDATE calling...");
				SkillUpdate(request, response);
				}
			else if (request.getParameter("CMD").equalsIgnoreCase("EXP_UPDATE")){
				System.out.println("EXP_UPDATE calling...");
				ExpUpdate(request, response);
				}
			else {
				System.out.println("No CMD");
				response.getWriter().println("Invalid Command");
				
				}
		} catch (Exception e) {
			response.getWriter().println("HanaServlet failed with reason" + e.getMessage());
			// Logger.error("HanaServlet failed with reason" +e.getMessage());

		}

	}

	public void empUpdate(HttpServletRequest request, HttpServletResponse response)  {

        // Update Employee table
        
        try {
               EmployeeB emp = new EmployeeB();
               //List<EmployeeB>  emp = null;
               String estatus="";
               emp=getandparsejson(request, response);
               //emp=processGivenData(request, response);
               if(emp==null)
               {
                     //System.out.println("emp employee number is ");
                     response.getWriter().println("UpdateEmployee-No input data found to update Employee table" );
               }
               else{
                     estatus=empdao.UpdateEmp(emp);
                     /*for(EmployeeB em :emp)
                     
                                                 {
                                                        estatus=empdao.UpdateEmp(em);
                                                        response.getWriter().println("Employee update successfully");
                                                 }*/
                     if (estatus.equalsIgnoreCase("202-Sucess"))
                     {
                            response.getWriter().println("Employee Update sucessfully" );
                            response.setStatus(200);
                     }
                     else if (estatus.equalsIgnoreCase("DBerror"))
                     {
                     response.getWriter().println("Employee Update fail-Database error .kindly contact system admin" );
                     response.setStatus(400);
                     }
                     else{
                    	 response.getWriter().println("message:"+estatus);
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


	// SKILL Update
	public void SkillUpdate(HttpServletRequest request, HttpServletResponse response) throws SQLException {

		// Update Employee table
		List<EmployeeB>  emp = null;
		String estatus="";
		try {
			
			//emp=getandparsejson(request, response,emp);
			emp=processGivenData(request, response);
			
			if(emp.isEmpty())
			{
				
				response.getWriter().println("SkillUpdate-No input data found to update Employee Skill table" );
			}
			else{
				
				//if(empdao.UpdateSkill(emp))
				boolean is_deleted = false;
				for(EmployeeB em :emp)
				{
					estatus=empdao.UpdateSkill(em,is_deleted);
					if(estatus.contains("true")){
						is_deleted = true;
					}
					//response.getWriter().println("Skill set of Employee updated successfully----------" +estatus);
				}
				
						if (estatus.equalsIgnoreCase("202-Sucesstrue"))
								{
									response.getWriter().println("Skill Updated Done sucessfully" );
									response.setStatus(200);
								}
						else if (estatus.equalsIgnoreCase("DBerror"))
								{
								response.getWriter().println("SkillUpdate fail-Database error .kindly contact system admin" );
								response.setStatus(400);
								}
						else{
							 response.getWriter().println("message"+estatus);
	                    	 response.setStatus(400);
						}
						
				}
			} catch (Exception e) {
				try{
					
						response.getWriter().println("Employee Skill updation failed-database connection Fail");
					
				}
				catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e.getMessage();

			}

		}
	// Experience Update
		public void ExpUpdate(HttpServletRequest request, HttpServletResponse response) throws SQLException {

			// Update Exp table
			List<EmployeeB>  emp = null;
			String estatus="";
			try {
				
				emp=processGivenData(request, response);
				
				if(emp==null)
				{
					response.getWriter().println("EXPUpdate-No input data found to update Employee Exp table" );
				}
				else{
					//empdao.UpdateSkill(emp);
					boolean is_deleted = false;
					for(EmployeeB em :emp){
						estatus=empdao.UpdateExperience(em,is_deleted);
						if(estatus.contains("true")){
							is_deleted = true;
						}
							//response.getWriter().println("Experience of Employee updated successfully");
							
					}
//					response.getWriter().println("202-Sucess:Experience Update Done sucessfully"+estatus);
					if (estatus.equalsIgnoreCase("202-Sucesstrue"))
					{
						response.getWriter().println("202-Sucess:Experience Update Done sucessfully" );
						response.setStatus(200);
					}
					else if (estatus.equalsIgnoreCase("DBerror"))
					{
					response.getWriter().println("Experience Update fail-Database error .kindly contact system admin" );
					response.setStatus(400);
					}
					else{
						 response.getWriter().println("message"+estatus);
                    	 response.setStatus(400);
					}
					
				}
				} catch (Exception e) {
					try {
						response.getWriter().println("Experience Skill updation failed-database connection Fail");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					e.getMessage();
					
					}

			}
	//Get json data and convert to object-Employee
	//public Employee getandparsejson(HttpServletRequest request, HttpServletResponse response,Employee emp) throws SQLException{
		public List<EmployeeB> processGivenData(HttpServletRequest request, HttpServletResponse response) throws SQLException{
		List<EmployeeB> empList = new ArrayList<EmployeeB>();
		try {
			
			String userId = (String)request.getSession().getAttribute("Username");//Authorization
			BufferedReader reader = request.getReader();
			StringBuilder sb = new StringBuilder();
			String line = reader.readLine();
			JSONObject obj = new JSONObject();
			JSONArray jJSONArray = new JSONArray();
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
				/*JsonReader readerj = Json.createReader(reader);
				JsonObject empObj = readerj.readObject();
	           
	            // read string data
	            System.out.println("New LOgic---------Emp Name: " + empObj.getString("EMPNO"));
	            // read json array
	            JsonArray arrObj = empObj.getJsonArray("SKILL");
	            System.out.println("\nSKILL:");
	            for(JsonValue value : arrObj){
	                System.out.println(value.toString());
	            }*/
				
				JSONParser parser = new JSONParser();
				jJSONArray = (JSONArray) parser.parse(jsondata);
				for(int i=0;i< jJSONArray.size();i++){
					
					EmployeeB emp = new EmployeeB();
					emp.setLoginUser(userId);
					System.out.println("get value" +jJSONArray.get(i));
					
					obj=(JSONObject)jJSONArray.get(i);
					//System.out.println("obj.values():: "+jJSONArray.);
					System.out.println("objEMPNO ---------"+obj.get("EMPNO"));
				
				String empno = String.valueOf(obj.get("EMPNO"));
				
					/******************************UPDATE EMPLOYEE TABLE******************************************/
							/*if(request.getParameter("CMD").equalsIgnoreCase("UPDATE"))
							
							{			
								
								System.out.println("jsonObject empNumber is -------" + obj.get("EMPNO"));
								System.out.println("jsonObject contact is -------" + obj.get("CONTACT"));
								//String empno = String.valueOf(obj.get("EMPNO"));
								String contact = String.valueOf(obj.get("CONTACT"));
								String city = String.valueOf(obj.get("CITY"));
								String email = String.valueOf(obj.get("EMAIL"));
								String pincode = String.valueOf(obj.get("PINCODE"));
										
								String degree = String.valueOf(obj.get("HIGH_DEGREE"));
								String cv = String.valueOf(obj.get("CV_ATTACHED"));
								String profile = String.valueOf(obj.get("PROFILE_ATTACHED"));
								String ismgr = String.valueOf(obj.get("IS_MGR"));
								
																
								if (empno != null && !empno.trim().isEmpty())
								{
									
									//Employee emp = new Employee();
									System.out.println("empno TEST "+empno);
									emp.setEmpNo(empno);
									emp.setContact(contact);
									emp.setCity(city);
									emp.setEmail(email);
									emp.setPincode(pincode);
									emp.setHighdegree(degree);
									emp.setCv_attached(cv);
									emp.setProfile_attached(profile);
									emp.setIs_mgr(ismgr);
									
									
									System.out.println("-----------EMP number--------- "+emp.getEmpNo());
								}
								else{
									response.getWriter().println(" getandparsejson-To update Employee table need all input data");
									
								}
							//return emp;
							}*/
							
							/******************************UPDATE SKILL of EMPLOYEE TABLE******************************************/
							if(request.getParameter("CMD").equalsIgnoreCase("SKILL_UPDATE"))
								
							{			
								/*response.getWriter().println("Json data get ,now SKIll of employees will update -" + obj.get("SKILL")
									+ "LEVL is " + obj.get("LEVL") +"IS_CERTIFIED is " +obj.get("IS_CERTIFIED"));*/
								
								String skill = String.valueOf(obj.get("SKILL"));
								
								List<String> test = new ArrayList<String>();
									/*if(obj.containsKey("SKILL"))
										{
										// Now we try to take the data from "SKILL" array
										//JSONArray jsonarr = (JSONArray) obj.get("SKILL");
										org.json.simple.JSONArray jsonArray= (org.json.simple.JSONArray) parser.parse(skill);
										Iterator j = jsonArray.iterator();
										while(j.hasNext()){
											test.add( (String)j.next());
											//String skilll = String.valueOf(obj.get("SKILL"));//String empno = String.valueOf(obj.get("EMPNO"));
											System.out.println("List of skill for employee "+ ""+test);
										}
										
										
										
										}*/
								String level = String.valueOf(obj.get("LEVL"));
								String iscertified = String.valueOf(obj.get("IS_CERTIFIED"));
								
								if (empno != null && !empno.trim().isEmpty())
								{
									
									//Employee emp = new Employee();
									System.out.println("SKILL_UPDATE" +empno);
									emp.setEmpNo(empno);
									emp.setSkill(skill);
									emp.setLevel(level);
									emp.setIscertified(iscertified);
								}
								else{
									response.getWriter().println(" getandparsejson-To update Employee table need employee number");
									//return emp;
								}
								//return emp;
							}
							
							/******************************UPDATE Experience of EMPLOYEE TABLE******************************************/
							else if(request.getParameter("CMD").equalsIgnoreCase("EXP_UPDATE"))
								
							{			
								/*response.getWriter().println("Json data get ,now SKIll of employees will update -" + obj.get("ORG_NAME")
									+ "PRJ_NAME is " + obj.get("PRJ_NAME") +"S_DATE is " +obj.get("S_DATE")+ "E_DATE is "+obj.get("E_DATE"));*/
								
								String orgName = String.valueOf(obj.get("ORG_NAME"));
								String prjName = String.valueOf(obj.get("PRJ_NAME"));
								String startDate = String.valueOf(obj.get("S_DATE"));
								String endDate = String.valueOf(obj.get("E_DATE"));
								
								if (empno != null && !empno.trim().isEmpty())
								{
									
									//Employee emp = new Employee();
									System.out.println("SKILL_UPDATE" +empno);
									emp.setEmpNo(empno);
									emp.setOrgName(orgName);
									emp.setProjectName(prjName);
									emp.setStartDate(startDate);
									emp.setEndDate(endDate);
									
								}
								else{
									response.getWriter().println(" getandparsejson-To update Employee table need employee number");
									//return emp;
								}
								//return emp;
							}
							
							empList.add(emp);
				}
				
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("emp list size:"+empList.size());
		return empList;
		//return emp;
		}
		
		//Get json data and convert to object-Employee
        public EmployeeB getandparsejson(HttpServletRequest request, HttpServletResponse response) throws SQLException{
               EmployeeB emp = new EmployeeB();
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
                                          /******************************Create Project******************************************/
                                   /*       if(request.getParameter("CMD").equalsIgnoreCase("PRJ_CREATE"))
                                                 
                                          {
                                          
                                          String startDate = String.valueOf(obj.get("START_DATE"));
                                          String endDate = String.valueOf(obj.get("END_DATE"));
                                          String posReq = String.valueOf(obj.get("POS_REQ"));
                                          String mrgNo = String.valueOf(obj.get("MRG_NO"));
                                          String prjCost = String.valueOf(obj.get("PRJ_COST"));
                                          String prjloc = String.valueOf(obj.get("PRJ_LOC"));
                                          String prjDes = String.valueOf(obj.get("PRJ_DES"));
                                          String clientId = String.valueOf(obj.get("CLIENT_ID"));
                                          
                                          proj.setStartDate(startDate);
                                          proj.setEndDate(endDate);
                                          proj.setPosRequest(posReq);
                                          proj.setMrgNumber(mrgNo);
                                          proj.setProjectcost(prjCost);
                                          proj.setProjectlocation(prjloc);
                                          proj.setProjectDes(prjDes);
                                          proj.setClientId(clientId);
                                          return proj;
                                          }*/
                                          
                                          String empno = String.valueOf(obj.get("EMPNO"));
                                          
                                          /******************************UPDATE EMPLOYEE TABLE******************************************/
                                                        if(request.getParameter("CMD").equalsIgnoreCase("UPDATE"))
                                                        
                                                        {                    
                                                            
                                                               //////////////
                                                               System.out.println("jsonObject empNumber is -------" + obj.get("EMPNO"));
                                                               System.out.println("jsonObject contact is -------" + obj.get("CONTACT"));
                                                               //String empno = String.valueOf(obj.get("EMPNO"));
                                                               String contact = String.valueOf(obj.get("CONTACT"));
                                                               String city = String.valueOf(obj.get("CITY"));
                                                               String email = String.valueOf(obj.get("EMAIL"));
                                                               String pincode = String.valueOf(obj.get("PINCODE"));
                                                                            
                                                               String degree = String.valueOf(obj.get("HIGH_DEGREE"));
                                                               String cv = String.valueOf(obj.get("CV_ATTACHED"));
                                                               String profile = String.valueOf(obj.get("PROFILE_ATTACHED"));
                                                               String ismgr = String.valueOf(obj.get("IS_MGR"));
                                                               
                                                               /*if (empno != null && contact != null && city != null && !empno.trim().isEmpty() && !contact.trim().isEmpty()
                                                                            && !city.trim().isEmpty()) {*/
                                                               
                                                               if (empno != null && !empno.trim().isEmpty())
                                                               {
                                                                     
                                                                     //Employee emp = new Employee();
                                                                     System.out.println("empno TEST "+empno);
                                                                     emp.setEmpNo(empno);
                                                                     emp.setContact(contact);
                                                                     emp.setCity(city);
                                                                     emp.setEmail(email);
                                                                     emp.setPincode(pincode);
                                                                      emp.setHighdegree(degree);
                                                                     emp.setCv_attached(cv);
                                                                      emp.setProfile_attached(profile);
                                                                     emp.setIs_mgr(ismgr);
                                                                     
                                                                     
                                                                     System.out.println("-----------EMP number--------- "+emp.getEmpNo());
                                                               }
                                                               else{
                                                                      LOGGER.info(" getandparsejson-To update Employee table need all input data");
                                                                     
                                                               }
                                                        return emp;
                                                        }
                                          
                                          
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
