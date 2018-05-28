package com.acme.s4ext.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.ReadOnly;

/*
 * JPA Model definition for Equipment2Device
 * Details for Equipment2Device are defined in /resources/sqlscripts/T_EQUIPMENT2DEVICE.sql
 */
//@Entity
//@Table(schema = "ACME", name = "T_EQUIPMENT2DEVICE")
@Entity
@ReadOnly
@Table(schema = "\"_SYS_BIC\"", name = "\"rbei.real.i1.data.transaction.views/RA_CV_EMPL_E\"")
@NamedQuery(name = EmpPastExp.FIND_ALL, query = "select e from EmpPastExp e ")
public class EmpPastExp implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String FIND_ALL = "EmpPastExp.findAll";

	@Id
	@Column(name = "EMPNO")
	private String EMPNO;

	@Column(name = "E_DATE")
	private String E_DATE;

	@Column(name = "ORG_NAME")
	private String ORG_NAME;

	@Column(name = "PRJ_NAME")
	private String PRJ_NAME;
	
	@Column(name = "S_DATE")
	private String S_DATE;
	
	@Column(name = "COUNT_TOTAL")
	private String COUNT_TOTAL;

	//This will allow to navigate to the details for a specific equipment
	@ManyToOne()
	@JoinColumn(name = "EMPNO", updatable = false, insertable = false)
	private EMPLOYEE EMPLOYEE;
	
	@ManyToOne()
	@JoinColumn(name = "EMPNO", updatable = false, insertable = false)
	private AllEmployee AllEmployee;

	public EmpPastExp() {
	}

	public String getEMPNO() {
		return this.EMPNO;
	}
	public String getE_DATE() {
		return this.E_DATE;
	}
	
	public String getORG_NAME() {
		return this.ORG_NAME;
	}
	public String getPRJ_NAME() {
		return this.PRJ_NAME;
	}
	public String getS_DATE() {
		return this.S_DATE;
	}

	public String getCOUNT_TOTAL() {
		return this.COUNT_TOTAL;
	}

	
//	public void setEquipmentKey(String equipmentKey) {
//		this.equipmentKey = equipmentKey;
//	}

//	public String getGDevice() {
//		return this.gDevice;
//	}
//
//	public void setGDevice(String gDevice) {
//		this.gDevice = gDevice;
//	}
//
//	public String getGDeviceName() {
//		return this.gDeviceName;
//	}
//
//	public void setGDeviceName(String gDeviceName) {
//		this.gDeviceName = gDeviceName;
//	}

	public EMPLOYEE getEMPLOYEE() {
		return EMPLOYEE;
	}
	
	public AllEmployee getAllEmployee() {
		return AllEmployee;
	}
}