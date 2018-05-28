package com.acme.s4ext.jpa.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.ReadOnly;

import com.acme.s4ext.jpa.model.EmpSkillRef;

/*
 * JPA Model definition of SalesOrder.
 * Details for SalesOrder can befound in /resources/sqlscripts/V_SALES_ORDER.sql
 */
@Entity
@ReadOnly
@Table(schema = "\"_SYS_BIC\"", name = "\"rbei.real.i1.data.transaction.views/RA_CV_EMPL_M\"")
@NamedQuery(name = EMPLOYEE.FIND_ALL, query = "select s from EMPLOYEE s")
public class EMPLOYEE {

	public static final String FIND_ALL = "EMPLOYEE.findAll";

	@Id
	@Column(name = "EMPNO")
	private String EMPNO;
	@Column(name = "EMPNAME")
	private String EMPNAME;
	@Column(name = "FIRST_NAME")
	private String FIRST_NAME;
	@Column(name = "CONTACT")
	private String CONTACT;
	@Column(name = "CITY")
	private String CITY;
	@Column(name = "EMAIL")
	private String EMAIL;
	@Column(name = "PINCODE")
	private String PINCODE;
	@Column(name = "SEX")
	private String SEX;
	@Column(name = "HIGH_DEGREE")
	private String HIGH_DEGREE;
	@Column(name = "EMP_TYP")
	private String EMP_TYP;
	@Column(name = "LEVL")
	private String LEVL;
	@Column(name = "MGR_NO")
	private String MGR_NO;
	@Column(name = "IS_MGR")
	private String IS_MGR;
	@Column(name = "LOC")
	private String LOC;
	@Column(name = "CV_ATTACHED")
	private String CV_ATTACHED;
	@Column(name = "HIRE_DT")
	private String HIRE_DT;
	@Column(name = "RES_DT")
	private String RES_DT;
	@Column(name = "EXIT_DT")
	private String EXIT_DT;
	@Column(name = "PROFILE_ATTACHED")
	private String PROFILE_ATTACHED;
	@Column(name = "MGR_NAME")
	private String MGR_NAME;


	// //Each sales order has ordered multiple equipment
	// //This allows to navigate to the equipment
	@OneToMany(mappedBy = "EMPLOYEE")
	@JoinColumn(name = "EMPNO", referencedColumnName = "EMPNO")
	private List<EmpSkillRef> EmpSkillRef;
	@OneToMany(mappedBy = "EMPLOYEE")
	@JoinColumn(name = "EMPNO", referencedColumnName = "EMPNO")
	private List<EmpPastExp> EmpPastExp;
	 
	public String getEMPNO() {
		  return EMPNO;
		 }
		public String getEMPNAME() {
		  return EMPNAME;
		 }
		public String getFIRST_NAME() {
		  return FIRST_NAME;
		 }
		public String getCONTACT() {
		  return CONTACT;
		 }
		public String getCITY() {
		  return CITY;
		 }
		public String getEMAIL() {
		  return EMAIL;
		 }
		public String getPINCODE() {
		  return PINCODE;
		 }
		public String getSEX() {
		  return SEX;
		 }
		public String getHIGH_DEGREE() {
		  return HIGH_DEGREE;
		 }
		public String getEMP_TYP() {
		  return EMP_TYP;
		 }
		public String getLEVL() {
		  return LEVL;
		 }
		public String getMGR_NO() {
		  return MGR_NO;
		 }
		public String getIS_MGR() {
		  return IS_MGR;
		 }
		public String getLOC() {
		  return LOC;
		 }
		public String getCV_ATTACHED() {
		  return CV_ATTACHED;
		 }
		public String getHIRE_DT() {
		  return HIRE_DT;
		 }
		public String getRES_DT() {
		  return RES_DT;
		 }
		public String getEXIT_DT() {
		  return EXIT_DT;
		 }
		public String getPROFILE_ATTACHED() {
		  return PROFILE_ATTACHED;
		 }
		public String getMGR_NAME() {
		  return MGR_NAME;
		 }
	public List<EmpPastExp> getEmpPastExp() {
		return EmpPastExp;
	}
	public List<EmpSkillRef> getEmpSkillRef() {
		return EmpSkillRef;
	}
}