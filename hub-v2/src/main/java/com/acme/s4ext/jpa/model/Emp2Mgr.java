package com.acme.s4ext.jpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.eclipse.persistence.annotations.ReadOnly;

/*
 * JPA Moddel definition for IoTDevice.
 * Details for IoTDevice can be found in /resources/sqlscripts/T_IOT_DEVICE.sql
 */
@Entity
@ReadOnly
@Table(schema = "\"_SYS_BIC\"", name =  "\"rbei.real.i1.data.transaction.views/RA_CV_CURR_PRJ\"")
@NamedQuery(name = Emp2Mgr.FIND_ALL, query = "select s from Emp2Mgr s")
public class Emp2Mgr {
	public static final String FIND_ALL = "Emp2Mgr.findAll";
	@Id
	@Column(name = "EMPNAME")
	 private String EMPNAME;
	@Column(name = "MGR_NO")
	 private String MGR_NO;
	@Column(name = "LOC")
	 private String LOC;
	@Column(name = "LEVL")
	 private String LEVL;
	@Column(name = "CITY")
	 private String CITY;
	@Column(name = "MANAGER_NAME")
	 private String MANAGER_NAME;
	@Column(name = "PRJ_NO")
	 private Integer PRJ_NO;
	@Column(name = "CLIENT_ID")
	 private String CLIENT_ID;
	@Column(name = "POS_REQ")
	 private String POS_REQ;
	@Column(name = "PRJ_LOC")
	 private String PRJ_LOC;
	@Column(name = "PRJ_STATUS")
	 private String PRJ_STATUS;
	@Column(name = "PRJ_DES")
	 private String PRJ_DES;
	@Column(name = "EMP_NO")
	 private String EMP_NO;
	@Column(name = "EMP_START_DATE_PRJ")
	 private String EMP_START_DATE_PRJ;
	@Column(name = "EMP_END_DATE_PRJ")
	 private String EMP_END_DATE_PRJ;
	@Column(name = "PRJ_END_DATE")
	 private String PRJ_END_DATE;
	@Column(name = "PRJ_START_DATE")
	 private String PRJ_START_DATE;
	@Column(name = "PRJ_MRG_NO")
	 private String PRJ_MRG_NO;
	@Column(name = "ALL_SKILL")
	 private String ALL_SKILL;
	@Column(name = "PROFILE_ATTACHED")
	 private String PROFILE_ATTACHED;
	@Column(name = "COST")
	 private Integer COST;
	@Column(name = "CC_COST_PROJECT")
	 private Integer CC_COST_PROJECT;
	@Column(name = "CC_PRJ_ALLOT_STATUS")
	 private Integer CC_PRJ_ALLOT_STATUS;
	@Column(name = "CC_MONTH_IN_PRJ")
	 private Float CC_MONTH_IN_PRJ;
	@Column(name = "PRJ_COST")
	 private Integer PRJ_COST;

	public String getEMPNAME() {
		  return EMPNAME;
		}
		public String getMGR_NO() {
		  return MGR_NO;
		}
		public String getLOC() {
		  return LOC;
		}
		public String getLEVL() {
		  return LEVL;
		}
		public String getCITY() {
		  return CITY;
		}
		public String getMANAGER_NAME() {
		  return MANAGER_NAME;
		}
		public Integer getPRJ_NO() {
		  return PRJ_NO;
		}
		public String getCLIENT_ID() {
		  return CLIENT_ID;
		}
		public String getPOS_REQ() {
		  return POS_REQ;
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
		public String getEMP_NO() {
		  return EMP_NO;
		}
		public String getEMP_START_DATE_PRJ() {
		  return EMP_START_DATE_PRJ;
		}
		public String getEMP_END_DATE_PRJ() {
		  return EMP_END_DATE_PRJ;
		}
		public String getPRJ_END_DATE() {
		  return PRJ_END_DATE;
		}
		public String getPRJ_START_DATE() {
		  return PRJ_START_DATE;
		}
		public String getPRJ_MRG_NO() {
		  return PRJ_MRG_NO;
		}
		public String getALL_SKILL() {
		  return ALL_SKILL;
		}
		public String getPROFILE_ATTACHED() {
		  return PROFILE_ATTACHED;
		}
		public Integer getCOST() {
		  return COST;
		}
		public Integer getCC_COST_PROJECT() {
		  return CC_COST_PROJECT;
		}
		public Integer getCC_PRJ_ALLOT_STATUS() {
		  return CC_PRJ_ALLOT_STATUS;
		}
		public Float getCC_MONTH_IN_PRJ() {
		  return CC_MONTH_IN_PRJ;
		}
		public Integer getPRJ_COST() {
		  return PRJ_COST;
		}


}
