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


/*
 * JPA Model definition of SalesOrder.
 * Details for SalesOrder can befound in /resources/sqlscripts/V_SALES_ORDER.sql
 */
@Entity
@ReadOnly
@Table(schema = "\"RB_SCH_REAL\"", name = "\"rbei.real.i1.data.master.attributes::RA_DDL_ATTRIBUTES.T_RA_PRJK\"")
@NamedQuery(name = CreateProject.FIND_ALL, query = "select s from CreateProject s")
public class CreateProject {

	public static final String FIND_ALL = "CreateProject.findAll";

	@Id
	@Column(name = "PRJ_NO")
	private String PRJ_NO;
	@Column(name = "CLIENT_ID")
	private String CLIENT_ID;
	@Column(name = "START_DATE")
	private String START_DATE;
	@Column(name = "END_DATE")
	private String END_DATE;
	@Column(name = "POS_REQ")
	private String POS_REQ;
	@Column(name = "MRG_NO")
	private String MRG_NO;
	@Column(name = "PRJ_COST")
	private String PRJ_COST;
	@Column(name = "PRJ_LOC")
	private String PRJ_LOC;
	@Column(name = "PRJ_STATUS")
	private String PRJ_STATUS;
	@Column(name = "PRJ_DES")
	private String PRJ_DES;
	

	// //Each sales order has ordered multiple equipment
	// //This allows to navigate to the equipment
	@OneToMany(mappedBy = "CreateProject")
	@JoinColumn(name = "PRJ_NO", referencedColumnName = "PRJ_NO")
	private List<Project> Project;
//	@OneToMany(mappedBy = "EMPLOYEE")
//	@JoinColumn(name = "EMPNO", referencedColumnName = "EMPNO")
//	private List<EmpPastExp> EmpPastExp;
	 
	public String getPRJ_NO() {
		  return PRJ_NO;
		 }
		public String getCLIENT_ID() {
		  return CLIENT_ID;
		 }
		public String getSTART_DATE() {
		  return START_DATE;
		 }
		public String getEND_DATE() {
		  return END_DATE;
		 }
		public String getPOS_REQ() {
		  return POS_REQ;
		 }
		public String getMRG_NO() {
		  return MRG_NO;
		 }
		public String getPRJ_COST() {
		  return PRJ_COST;
		 }
		public String getPRJ_LOC() {
		  return PRJ_LOC;
		 }
		public String getPRJ_STATUS() {
		  return PRJ_STATUS;
		 }
		public String getPRJ_DES() {
		  return PRJ_DES;
		 }
		
	public List<Project> getProject() {
		return Project;
	}
//	public List<EmpSkillRef> getEmpSkillRef() {
//		return EmpSkillRef;
//	}
}