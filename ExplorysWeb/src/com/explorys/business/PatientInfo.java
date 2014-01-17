package com.explorys.business;

import java.lang.reflect.Array;

public class PatientInfo {
	
	private String language;
	private String state;
	private String status;
	private String recordDate;
	private String intraOrgId;
	private String lastName;
	private String firstName;
	private String middleName;
	private String otherName;
	private String stdStatus;
	private String gender;
	private String stdGender;
	private String stdLanguage;
	private Array race;
	private String stdRace;
	private String religion;
	private String stdReligion;
	private String birthDate;
	private String deathDate;
	private String stdDeathDate;
	private boolean isDeceased;
	private boolean isOptedIn;
	private boolean isOptedOut;
	private String consentDate;
	private String emailAddress;
	private String preferredContactMethod;
	private String pcpProviderId;
	private boolean isMilitaryVeteran;
	private boolean isTestPatient;
	private boolean isDeletedFromSource;
	private String postalCode;
	private Array ethnicity;
	private Array stdEthnicity;
	private Array insurance;
	private String stdInsurance;
	private boolean stdOptedIn;
	private boolean stdOptedOut;
	private boolean update;
	private String sid;
	private String ehrSystemId;
	private String ehrSystemIdQualifier;
	private String recordId;
	private int dataGridDateUpdated;
	private Array recordStatus;
	private Array sufficiencyFailures;
	private Array minReqsFailures;
	private String sourceSystemStatus;
	private String ignoreStatus;
	
	
	
	public void setState(String state) {
		this.state = state;
	}
	public String getState() {
		return state;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getLanguage() {
		return language;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus() {
		return status;
	}
	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}
	public String getRecordDate() {
		return recordDate;
	}
	public void setIntraOrgId(String intraOrgId) {
		this.intraOrgId = intraOrgId;
	}
	public String getIntraOrgId() {
		return intraOrgId;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setOtherName(String otherName) {
		this.otherName = otherName;
	}
	public String getOtherName() {
		return otherName;
	}
	public void setStdStatus(String stdStatus) {
		this.stdStatus = stdStatus;
	}
	public String getStdStatus() {
		return stdStatus;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getGender() {
		return gender;
	}
	public void setStdGender(String stdGender) {
		this.stdGender = stdGender;
	}
	public String getStdGender() {
		return stdGender;
	}
	public void setStdLanguage(String stdLanguage) {
		this.stdLanguage = stdLanguage;
	}
	public String getStdLanguage() {
		return stdLanguage;
	}
	public void setRace(Array race) {
		this.race = race;
	}
	public Array getRace() {
		return race;
	}
	public void setStdRace(String stdRace) {
		this.stdRace = stdRace;
	}
	public String getStdRace() {
		return stdRace;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	public String getReligion() {
		return religion;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setDeathDate(String deathDate) {
		this.deathDate = deathDate;
	}
	public String getDeathDate() {
		return deathDate;
	}
	public void setDeceased(boolean isDeceased) {
		this.isDeceased = isDeceased;
	}
	public boolean isDeceased() {
		return isDeceased;
	}
	public void setOptedIn(boolean isOptedIn) {
		this.isOptedIn = isOptedIn;
	}
	public boolean isOptedIn() {
		return isOptedIn;
	}
	public void setOptedOut(boolean isOptedOut) {
		this.isOptedOut = isOptedOut;
	}
	public boolean isOptedOut() {
		return isOptedOut;
	}
	public void setConsentDate(String consentDate) {
		this.consentDate = consentDate;
	}
	public String getConsentDate() {
		return consentDate;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setPreferredContactMethod(String preferredContactMethod) {
		this.preferredContactMethod = preferredContactMethod;
	}
	public String getPreferredContactMethod() {
		return preferredContactMethod;
	}
	public void setPcpProviderId(String pcpProviderId) {
		this.pcpProviderId = pcpProviderId;
	}
	public String getPcpProviderId() {
		return pcpProviderId;
	}
	public void setMilitaryVeteran(boolean isMilitaryVeteran) {
		this.isMilitaryVeteran = isMilitaryVeteran;
	}
	public boolean isMilitaryVeteran() {
		return isMilitaryVeteran;
	}
	public void setTestPatient(boolean isTestPatient) {
		this.isTestPatient = isTestPatient;
	}
	public boolean isTestPatient() {
		return isTestPatient;
	}
	public void setDeletedFromSource(boolean isDeletedFromSource) {
		this.isDeletedFromSource = isDeletedFromSource;
	}
	public boolean isDeletedFromSource() {
		return isDeletedFromSource;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setEthnicity(Array ethnicity) {
		this.ethnicity = ethnicity;
	}
	public Array getEthnicity() {
		return ethnicity;
	}
	public void setInsurance(Array insurance) {
		this.insurance = insurance;
	}
	public Array getInsurance() {
		return insurance;
	}
	public void setUpdate(boolean update) {
		this.update = update;
	}
	public boolean isUpdate() {
		return update;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getSid() {
		return sid;
	}
	public void setEhrSystemId(String ehrSystemId) {
		this.ehrSystemId = ehrSystemId;
	}
	public String getEhrSystemId() {
		return ehrSystemId;
	}
	public void setEhrSystemIdQualifier(String ehrSystemIdQualifier) {
		this.ehrSystemIdQualifier = ehrSystemIdQualifier;
	}
	public String getEhrSystemIdQualifier() {
		return ehrSystemIdQualifier;
	}
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	public String getRecordId() {
		return recordId;
	}
	public void setDataGridDateUpdated(int dataGridDateUpdated) {
		this.dataGridDateUpdated = dataGridDateUpdated;
	}
	public int getDataGridDateUpdated() {
		return dataGridDateUpdated;
	}
	public void setRecordStatus(Array recordStatus) {
		this.recordStatus = recordStatus;
	}
	public Array getRecordStatus() {
		return recordStatus;
	}
	public void setSufficiencyFailures(Array sufficiencyFailures) {
		this.sufficiencyFailures = sufficiencyFailures;
	}
	public Array getSufficiencyFailures() {
		return sufficiencyFailures;
	}
	public void setMinReqsFailures(Array minReqsFailures) {
		this.minReqsFailures = minReqsFailures;
	}
	public Array getMinReqsFailures() {
		return minReqsFailures;
	}
	public void setSourceSystemStatus(String sourceSystemStatus) {
		this.sourceSystemStatus = sourceSystemStatus;
	}
	public String getSourceSystemStatus() {
		return sourceSystemStatus;
	}
	public void setIgnoreStatus(String ignoreStatus) {
		this.ignoreStatus = ignoreStatus;
	}
	public String getIgnoreStatus() {
		return ignoreStatus;
	}
	
	
}
