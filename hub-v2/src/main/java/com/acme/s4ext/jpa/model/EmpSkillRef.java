package com.acme.s4ext.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.ReadOnly;

/*
 * JPA Model definition for EmpSkillRef.
 * Details for EmpSkillRef are defined in /resources/sqlscripts/V_EmpSkillRef.sql
 */
@Entity
@ReadOnly
@Table(schema = "\"_SYS_BIC\"", name = "\"rbei.real.i1.data.transaction.views/RA_CV_EMPL_S\"")
@NamedQuery(name = EmpSkillRef.FIND_ALL, query = "select e from EmpSkillRef e ")
public class EmpSkillRef implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String FIND_ALL = "EmpSkillRef.findAll";

	
	 
	@Id
	@Column(name = "EMPNO")
	private String EMPNO;

	@Column(name = "IS_CERTIFIED")
	private String IS_CERTIFIED;

	@Column(name = "LEVL")
	private String LEVL;

	@Column(name = "SKILL")
	private String SKILL;



	//EmpSkillRef originates from a sales order. 
	//This will allow to navigate from the EmpSkillRef to the sales order with which it was ordered.
	@ManyToOne()
	@JoinColumn(name = "EMPNO", updatable = false, insertable = false)
	private EMPLOYEE EMPLOYEE;
	@ManyToOne()
	@JoinColumn(name = "EMPNO", updatable = false, insertable = false)
	private AllEmployee AllEmployee;

	public EmpSkillRef() {
	}

	public String getEMPNO() {
		return this.EMPNO;
	}

	public String getIS_CERTIFIED() {
		return this.IS_CERTIFIED;
	}

	public String getLEVL() {
		return LEVL;
	}

	public Object getSKILL() {
		return this.SKILL;
	}


	public EMPLOYEE getEMPLOYEE() {
		return EMPLOYEE;
	}
	
	public AllEmployee getAllEmployee() {
		return AllEmployee;
	}

}