package com.dev.DAO;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dev.bean.EmpCreateB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.DriverManager;
//import java.lang.*;

public class CreateEDAO {

	// ---------JDBC driver name and database URL------------
	
	public static final String DATA_SOURCE_NAME = "java:comp/env/jdbc/DefaultDB";
	
	Connection connection = null;
	PreparedStatement pstmt = null;

	private static final Logger LOGGER = LoggerFactory.getLogger(CreateEDAO.class);
	/*--------------------SQL Statement----------------*/
	String isManagerStatement = "SELECT IS_MGR from \"RB_SCH_REAL\".\"rbei.real.i1.data.master.attributes::RA_DDL_ATTRIBUTES.T_RA_EMPL_M\" WHERE EMPNO = ?";
	String getEMP = "SELECT SESSION_USER \"session user\" FROM DUMMY;";
	String getempstatement = "SELECT count(*) as count from \"RB_SCH_REAL\".\"rbei.real.i1.data.master.attributes::RA_DDL_ATTRIBUTES.T_RA_EMPL_M\" WHERE EMPNO = ?";
	String updateEMPstatement = "UPDATE \"RB_SCH_REAL\".\"rbei.real.i1.data.master.attributes::RA_DDL_ATTRIBUTES.T_RA_EMPL_M\" SET EMPNAME = ?,FIRST_NAME = ?, CONTACT = ?,CITY = ?,EMAIL = ?,PINCODE = ?,SEX = ?,HIGH_DEGREE = ?,EMP_TYP = ?,LEVL = ?,MGR_NO = ?,IS_MGR = ?,LOC = ?,HIRE_DT = ?, RES_DT = ?,EXIT_DT = ?, CV_ATTACHED =? ,PROFILE_ATTACHED = ?  WHERE EMPNO = ?";
	String insertEMPstatement="INSERT INTO \"RB_SCH_REAL\".\"rbei.real.i1.data.master.attributes::RA_DDL_ATTRIBUTES.T_RA_EMPL_M\" (EMPNO,EMPNAME,FIRST_NAME,CONTACT,CITY,EMAIL,PINCODE,SEX,HIGH_DEGREE,EMP_TYP,LEVL,MGR_NO,IS_MGR,LOC,CV_ATTACHED,HIRE_DT,PROFILE_ATTACHED) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	/*----------------------------------------------------*/
	
	
	// Method to read the DB user
	public String getDBuser() throws SQLException {
		// read the DB user
		String currdbuser = "";
		try {
			pstmt = connection.prepareStatement(getEMP);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				currdbuser = rs.getString("session user");
				LOGGER.info("Logged in DB user:" + currdbuser);
			}
			return currdbuser;
		} catch (Exception e) {

			LOGGER.info(e.getMessage());
			return currdbuser;
		}
	}
	public String InsertEmp(EmpCreateB employee) throws SQLException{
		String estatus="";
		
		//Connection connection = null;
		try {

			System.out.println("Calling CreateEMPDAO to Insert employee" + employee.getEmpNo());

			if (isempDBConnection()) {
				
				pstmt = connection.prepareStatement(getempstatement);
				pstmt.setString(1, employee.getEmpNo());// EmpUserId
				ResultSet rs = pstmt.executeQuery();
				
				if(rs.next()){
					Integer count= rs.getInt("COUNT");
					if(count==0){
						
						PreparedStatement update = connection.prepareStatement(insertEMPstatement);
						//EMPNO,EMPNAME,FIRST_NAME,CONTACT,CITY,EMAIL,PINCODE,SEX,HIGH_DEGREE,EMP_TYP,LEVL,MGR_NO,IS_MGR,LOC,CV_ATTACHED,HIRE_DT,PROFILE_ATTACHED
						update.setString(1,employee.getEmpNo());
						update.setString(2,employee.getEmpName());
						update.setString(3,employee.getFirstname());
						update.setString(4, employee.getContact());
						update.setString(5, employee.getCity());
						update.setString(6, employee.getEmail());
						update.setString(7, employee.getPincode());
						update.setString(8, employee.getSex());
						update.setString(9, employee.getHighdegree());
						update.setString(10, employee.getEmpType());
						update.setString(11, employee.getLevel());
						update.setString(12, employee.getMgrNo());
						update.setString(13, employee.getIs_mgr());
						update.setString(14, employee.getLoc());
						update.setString(15, employee.getCv_attached());
						update.setString(16, employee.getHdate());
						update.setString(17, employee.getProfile_attached());
						

						int i = update.executeUpdate();
						System.out.println("Employee executeupdate");
						if (i < 0) {
							System.out.println("employee creation failed");
							estatus="DBerror";
							// return status;

						} else {
							// commit employee database
							System.out.println("Employee created successfully...");
							estatus = "200-Sucess";
							connection.setAutoCommit(false);
							connection.commit();


					}
						
					}
				}
				else{
					estatus="Employee already exists";
					return estatus;
				}
					
			} else {
				estatus="DBerror";
				return estatus;
			}
			return estatus;
		}  catch (Exception e) {
			e.printStackTrace();
			 estatus = e.getMessage();
			return estatus;
		} finally {
			// Clean-up environment

			pstmt.close();
			connection.close();

		}
		//return estatus;
	}

	public boolean isempDBConnection() throws SQLException {
		//Connection connection = null;
		boolean dbStatus = false;
		System.out.println("success========");
		/**************************
		 * Datasource logic
		 ***********************************/

		// Connection connection;
		try {
			InitialContext ctx = new InitialContext();
			DataSource dataSource = (DataSource) ctx.lookup(DATA_SOURCE_NAME);
			connection = dataSource.getConnection();
			System.out.println("Connection success");
			dbStatus = true;
		} catch (Exception e) {
			System.out.println("DBconnection fail");
			e.printStackTrace();
			return dbStatus;
		}
		return dbStatus;
	}
	// SKILL Update

	public String UpdateEmp(EmpCreateB employee) throws SQLException {
		String estatus="";
		//boolean is_deleted = false;
		//Connection connection = null;
		try {

			System.out.println("EMP NO:----- FROM JSON" + employee.getEmpNo());
		

			if (isempDBConnection()) {
				String log_user = getDBuser();
				System.out.println("User:"+log_user);
				pstmt = connection.prepareStatement(isManagerStatement);
				pstmt.setString(1, log_user);		
				ResultSet rs = pstmt.executeQuery();
				System.out.println("HR User ID" +log_user);
				if(rs.next()){
					String ismgr= rs.getString("IS_MGR");
					if(ismgr.equals("3"))
					{
						System.out.println("Employee Number======="+employee.getEmpNo());
						System.out.println("Employee Number======="+employee.getEmpName());
						PreparedStatement update = connection.prepareStatement(updateEMPstatement);
						// EMPNAME = ?,FIRST_NAME = ?, CONTACT = ?,CITY = ?,EMAIL = ?,PINCODE = ?,SEX = ?,HIGH_DEGREE = ?,EMP_TYP = ?,LEVL = ?,MGR_NO = ?,IS_MGR = ?,LOC = ?,HIRE_DT = ?, RES_DT = ?,EXIT_DT = ?, CV_ATTACHED =? ,PROFILE_ATTACHED = ?  WHERE EMPNO = ?";
						
						update.setString(1,employee.getEmpName());
						update.setString(2,employee.getFirstname());
						update.setString(3, employee.getContact());
						update.setString(4, employee.getCity());
						update.setString(5, employee.getEmail());
						update.setString(6, employee.getPincode());
						update.setString(7, employee.getSex());
						update.setString(8, employee.getHighdegree());
						update.setString(9, employee.getEmpType());
						update.setString(10, employee.getLevel());
						update.setString(11, employee.getMgrNo());
						update.setString(12, employee.getIs_mgr());
						update.setString(13, employee.getLoc());
						update.setString(14, employee.getHdate());
						update.setString(15, employee.getResDate());
						update.setString(16, employee.getExitdate());
						update.setString(17, employee.getCv_attached());
						update.setString(18, employee.getProfile_attached());
						
						update.setString(19,employee.getEmpNo());
						
						
						int i = update.executeUpdate();
						System.out.println("Employee executeupdate");
						if (i < 0) {
							System.out.println("employee updatation failed");
							estatus="DBerror";
							

						} else {
							// commit employee database
							System.out.println("Employee updation successfully done...");
							estatus = "200-Sucess";
							connection.setAutoCommit(false);
							connection.commit();


					}
					}
				}
				else{
					System.out.println("Employee is not authorized to update Employee");
					estatus="notauthorized";
					return estatus;
				}
				
			//}
			}
			else{
				estatus="DBerror";
				return estatus;
			}
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
			estatus = se.getMessage();
			return estatus;
		}
		return estatus;

	}
	

}
