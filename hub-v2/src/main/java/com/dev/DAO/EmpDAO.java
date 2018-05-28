package com.dev.DAO;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.acme.s4ext.jpa.dbsetup.DatabaseEntitySetup;
import com.dev.bean.EmployeeB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.DriverManager;
//import java.lang.*;

public class EmpDAO {

	// ---------JDBC driver name and database URL------------
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:sap://bmh1078076:30515/";// ?autocommit=false",
																// "SAPHAL_ADMIN",
																// "Bosch@1234"
	public static final String DATA_SOURCE_NAME = "java:comp/env/jdbc/DefaultDB";
	private static final Logger LOGGER = LoggerFactory.getLogger(EmpDAO.class);
	// ----------Database credentials---------------
	Connection connection = null;
	// connection.setAutoCommit(false);
	PreparedStatement pstmt = null;

	/*--------------------SQL Statement----------------*/
	String isManagerStatement = "SELECT IS_MGR from \"RB_SCH_REAL\".\"rbei.real.i1.data.master.attributes::RA_DDL_ATTRIBUTES.T_RA_EMPL_M\" WHERE EMPNO = ?";
	String getEMP = "SELECT SESSION_USER \"session user\" FROM DUMMY;";
	String updateEMPstatement = "UPDATE \"RB_SCH_REAL\".\"rbei.real.i1.data.master.attributes::RA_DDL_ATTRIBUTES.T_RA_EMPL_M\" SET CONTACT =?, CITY =?,EMAIL=?, PINCODE = ?,HIGH_DEGREE = ?, CV_ATTACHED = ?, PROFILE_ATTACHED = ? WHERE EMPNO =? ";
	String sdeleteStatement = "DELETE from \"RB_SCH_REAL\".\"rbei.real.i1.data.master.attributes::RA_DDL_ATTRIBUTES.T_RA_EMPL_S\" where EMPNO = ?";
	String edeleteStatement = "DELETE from \"RB_SCH_REAL\".\"rbei.real.i1.data.master.attributes::RA_DDL_ATTRIBUTES.T_RA_EMPL_E\" where EMPNO = ?";
	String SkillUpdateStatement = "INSERT INTO \"RB_SCH_REAL\".\"rbei.real.i1.data.master.attributes::RA_DDL_ATTRIBUTES.T_RA_EMPL_S\" VALUES (?,?,?,?)";
	String experienceUpdateStatement = "INSERT INTO \"RB_SCH_REAL\".\"rbei.real.i1.data.master.attributes::RA_DDL_ATTRIBUTES.T_RA_EMPL_E\" VALUES (?,?,?,?,?)";

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

	public String UpdateEmp(EmployeeB employee) throws SQLException {
		String estatus = "";
		// Connection connection = null;
		try {

			System.out.println("Calling EMPDAO to update employee" + employee.getEmpNo());

			if (isempDBConnection()) {

				// pstmt = connection.prepareStatement(isManagerStatement);
				// pstmt.setString(1,log_user);// LoginUserId
				String log_user = getDBuser();
				LOGGER.info("User in UpdateEmp:" + log_user);
				System.out.println("EmpNo:" + employee.getEmpNo()+"---log_user:"+log_user);
				if (log_user.equals(employee.getEmpNo())) {
					System.out.println("EmpNo:" + employee.getEmpNo()+"---log_user:"+log_user);
					PreparedStatement update = connection.prepareStatement(updateEMPstatement);
					update.setString(1, employee.getContact());
					update.setString(2, employee.getCity());
					update.setString(3, employee.getEmail());
					update.setString(4, employee.getPincode());
					update.setString(5, employee.getHighdegree());
					update.setString(6, employee.getCv_attached());
					update.setString(7, employee.getProfile_attached());
					update.setString(8, employee.getEmpNo());

					int i = update.executeUpdate();
					LOGGER.info("Employee executeupdate");
					if (i <= 0) {
						LOGGER.info("Employee updation failed");
						estatus = "DBerror";
						// return status;

					} else {
						// commit employee database
						LOGGER.info("Employee update successfully...");
						estatus = "202-Sucess";
						connection.setAutoCommit(false);
						connection.commit();

					}
			

				}
				System.out.println("estatus is " + estatus);
				LOGGER.info("estatus is " + estatus);
				return estatus;
			
			} else {
				estatus = "DBerror";
				return estatus;
			}
		} catch (Exception e) {
			// Handle errors for Class.forName
			// e.printStackTrace();
			estatus = e.getMessage();
			return estatus;
		} finally {
			// Clean-up environment

			pstmt.close();
			connection.close();

		}
		// return estatus;
	}

	// Method for connecting to JAVA -DB
	public boolean isempDBConnection() throws SQLException {
		// Connection connection = null;
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
			LOGGER.info("Connection success");
			dbStatus = true;

			// String dbuser = getDBuser();

		} catch (Exception e) {
			LOGGER.info("DBconnection fail");
			e.printStackTrace();
			return dbStatus;
		}
		return dbStatus;
	}
	// SKILL Update

	public String UpdateSkill(EmployeeB employee, boolean is_deleted) throws SQLException {
		String estatus = "";
		// boolean is_deleted = false;
		// Connection connection = null;
		try {

			System.out.println("Calling EMPDAO to update SKILL of employee" + employee.getEmpNo());
			// System.out.println("is_deleted is " +is_deleted);
			LOGGER.info("Update skill started");

			if (isempDBConnection()) {

				/*
				 * pstmt = connection.prepareStatement(isManagerStatement);
				 * pstmt.setString(1, employee.getEmpNo());// emp id ResultSet
				 * rs = pstmt.executeQuery(); if (rs.next() == false) {
				 * System.out.println("Login employee is not a manager"); }
				 */
				if (employee.getSkill() != null) {
					if (!is_deleted) {
						pstmt = connection.prepareStatement(sdeleteStatement);
						pstmt.setString(1, employee.getEmpNo());
						is_deleted = true;

						int i = pstmt.executeUpdate();
					}
					pstmt = connection.prepareStatement(SkillUpdateStatement);
					pstmt.setString(1, employee.getEmpNo());
					pstmt.setString(2, employee.getSkill());
					pstmt.setString(3, employee.getLevel());
					pstmt.setString(4, employee.getIscertified());
					int j = pstmt.executeUpdate();
					if (j > 0) {
						estatus = "202-Sucess" + is_deleted;
						LOGGER.info("Employee Skill set update successfully..." + estatus);
						connection.setAutoCommit(false);
						connection.commit();

					}
					// }

					/*
					 * pstmt =
					 * connection.prepareStatement(SkillUpdateStatement);
					 * pstmt.setString(1, employee.getEmpNo());
					 * pstmt.setString(2, employee.getSkill());
					 * pstmt.setString(3, employee.getLevel());
					 * pstmt.setString(4, employee.getIscertified()); int j =
					 * pstmt.executeUpdate(); if (j > 0) { connection.commit();
					 * System.out.
					 * println("Employee Skill set update successfully...");
					 * estatus = "202-Sucess"; }
					 */

				}

				// }
			} else {
				estatus = "DBerror";
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

	/********************
	 * UPDATE Experience of Employee
	 ***********************************/
	public String UpdateExperience(EmployeeB employee, boolean is_deleted) throws SQLException {
		String estatus = "";
		// Connection connection = null;
		try {

			System.out.println("Calling EMPDAO to update EXPERIENCE of employee" + employee.getEmpNo());

			if (isempDBConnection()) {
				System.out.println("UpdateExperience Connection done");
				// System.out.println("Experience EMP is ");
				if (!is_deleted) {
					pstmt = connection.prepareStatement(edeleteStatement);
					LOGGER.info("Experience pre1");
					pstmt.setString(1, employee.getEmpNo());
					// LOGGER.info("Experience pre 2");
					int i = pstmt.executeUpdate();
					LOGGER.info("Experience deleted done");
					is_deleted = true;
				}
				// if (i > 0) {
				/*
				 * connection.commit(); System.out.
				 * println("Employee experience data deleted successfully...");
				 * estatus = "202-Sucess";
				 */

				if (employee.getOrgName() != null) {
					System.out.println("Experience DAO will update now-----------------");
					pstmt = connection.prepareStatement(experienceUpdateStatement);
					pstmt.setString(1, employee.getEmpNo());
					pstmt.setString(2, employee.getOrgName());
					pstmt.setString(3, employee.getProjectName());
					pstmt.setString(4, employee.getStartDate());
					pstmt.setString(5, employee.getEndDate());
					int j = pstmt.executeUpdate();
					if (j > 0) {
						// estatus = "202-Sucess";
						estatus = "202-Sucess" + is_deleted;
						connection.setAutoCommit(false);
						connection.commit();
						System.out.println("Employee Experience set update successfully...");
						LOGGER.info("Employee Experience set update successfully...");

					}

					System.out.println("Experience sestatus" + estatus);
					// return estatus;
				}

			} else {
				estatus = "DBerror";
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
