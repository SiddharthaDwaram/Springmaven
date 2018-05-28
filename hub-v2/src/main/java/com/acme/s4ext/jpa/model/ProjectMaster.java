package com.acme.s4ext.jpa.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.ReadOnly;

import com.acme.s4ext.jpa.model.EmpSkillRef;

/*
 * JPA Model definition of Project Master.
 * Details for Project master 
 */
@Entity
@ReadOnly
@Table(schema = "\"_SYS_BIC\"", name = "\"rbei.real.i1.data.transaction.views/RA_CV_PRJK\"")
@NamedQuery(name = ProjectMaster.FIND_ALL, query = "select s from ProjectMaster s")
public class ProjectMaster {

	public static final String FIND_ALL = "ProjectMaster.findAll";

	@Id
	@Column(name = "PRJ_NO")
	private Integer PRJ_NO;
	@Column(name = "MANAGER_NAME")
	private String MANAGER_NAME;
	@Column(name = "CLIENT_TEXT")
	private String CLIENT_TEXT;
	@Column(name = "START_DATE")
	private String START_DATE;
	@Column(name = "END_DATE")
	private String END_DATE;
	@Column(name = "CLIENT_ID")
	private String CLIENT_ID;
	@Column(name = "POS_REQ")
	private Integer POS_REQ;
	@Column(name = "MGR_NO")
	private String MGR_NO;
	@Column(name = "PRJ_LOC")
	private String PRJ_LOC;
	@Column(name = "PRJ_STATUS")
	private String PRJ_STATUS;
	@Column(name = "PRJ_DES")
	private String PRJ_DES;
	@Column(name = "PRJ_COST")
	private Integer PRJ_COST;
	@Column(name = "CC_DURATION")
	private float CC_DURATION;



	// //Each sales order has ordered multiple equipment
	// //This allows to navigate to the equipment
//	@ManyToOne()
//	@JoinColumn(name = "PRJ_NO", updatable = false, insertable = false)
//	private CreateProject CreateProject;

	public Integer getPRJ_NO() {
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
		public Integer getPOS_REQ() {
		  return POS_REQ;
		 }
		public String getMANAGER_NAME() {
		  return MANAGER_NAME;
		 }
		public String getCLIENT_TEXT() {
		  return CLIENT_TEXT;
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
		public Integer getPRJ_COST() {
		  return PRJ_COST;
		 }
		public float getCC_DURATION() {
		  return CC_DURATION;
		 }
		public String getMGR_NO() {
		  return MGR_NO;
		 }
//		public CreateProject getCreateProject() {
//			return CreateProject;
//		}

}