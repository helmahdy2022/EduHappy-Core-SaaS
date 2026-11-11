package model.beans.account;

import java.io.Serializable;
import java.util.Date;

import model.beans.parent.Child;

public class Account_Details implements Serializable{

private static final long serialVersionUID = 1L;

int Account_id;
int Employee_id;
int payroll_id;
int department_id;
String designation;
String joining_Date;
double initial_Basic=-1;
String Organization;
int country_id;
String city;
int PO_Box;
String Full_Address;
String Gender;
String tel;
String Mobile;
String Birth_Date;
int Activated;
Date Trial_Date;
int Trial_Days;
String First_Name;
String Last_Name;
int Admin_id;
int Account_Owner;
String Email;
String Password;
int Teacher_id;
int Student_id;
int Parent_id;
int Subscribed;
Date Expiry_Date;
int No_Of_Students;
int No_Of_Created_Students;
String Agreement_id;
int FrequencyInterval;
int Last_Payment;
int Amount_Per_Cycle;
String Last_Payment_Date;
int Days_Consumed;
int Cost_Till_Today;
int Remaining_Amount;
boolean AgreementPending;
boolean AgreementCancelling;
String Agreement_State;
int TrialDays_Left;
String FileName;
boolean Trial_Valid;
boolean Subscription_Valid;
String Subscription_Expiry;
String Trial_Expiry;
int School_id;
int Student_First_Login;
int Parent_First_Login;
int Teacher_First_Login;
boolean Student_Promoted;
String Year_Name;
Child Child;
boolean has_saved_token;


public int getAccount_id() {
	return Account_id;
}
public void setAccount_id(int account_id) {
	Account_id = account_id;
}

/**
 * @return the employee_id
 */
public int getEmployee_id() {
	return Employee_id;
}
/**
 * @param employee_id the employee_id to set
 */
public void setEmployee_id(int employee_id) {
	Employee_id = employee_id;
}
/**
 * @return the payroll_id
 */
public int getPayroll_id() {
	return payroll_id;
}
/**
 * @param payroll_id the payroll_id to set
 */
public void setPayroll_id(int payroll_id) {
	this.payroll_id = payroll_id;
}
/**
 * @return the department_id
 */
public int getDepartment_id() {
	return department_id;
}
/**
 * @param department_id the department_id to set
 */
public void setDepartment_id(int department_id) {
	this.department_id = department_id;
}
/**
 * @return the designation
 */
public String getDesignation() {
	return designation;
}
/**
 * @param designation the designation to set
 */
public void setDesignation(String designation) {
	this.designation = designation;
}
/**
 * @return the joining_Date
 */
public String getJoining_Date() {
	return joining_Date;
}
/**
 * @param joining_Date the joining_Date to set
 */
public void setJoining_Date(String joining_Date) {
	this.joining_Date = joining_Date;
}
/**
 * @return the initial_Basic
 */
public double getInitial_Basic() {
	return initial_Basic;
}
/**
 * @param initial_Basic the initial_Basic to set
 */
public void setInitial_Basic(double initial_Basic) {
	this.initial_Basic = initial_Basic;
}
public String getOrganization() {
	return Organization;
}
public void setOrganization(String organization) {
	Organization = organization;
}


/**
 * @return the country_id
 */
public int getCountry_id() {
	return country_id;
}
/**
 * @param country_id the country_id to set
 */
public void setCountry_id(int country_id) {
	this.country_id = country_id;
}
/**
 * @return the city
 */
public String getCity() {
	return city;
}
/**
 * @param city the city to set
 */
public void setCity(String city) {
	this.city = city;
}

/**
 * @return the pO_Box
 */
public int getPO_Box() {
	return PO_Box;
}
/**
 * @param pO_Box the pO_Box to set
 */
public void setPO_Box(int pO_Box) {
	PO_Box = pO_Box;
}
/**
 * @return the full_Address
 */
public String getFull_Address() {
	return Full_Address;
}
/**
 * @param full_Address the full_Address to set
 */
public void setFull_Address(String full_Address) {
	Full_Address = full_Address;
}
/**
 * @return the gender
 */
public String getGender() {
	return Gender;
}
/**
 * @param gender the gender to set
 */
public void setGender(String gender) {
	Gender = gender;
}

/**
 * @return the tel
 */
public String getTel() {
	return tel;
}
/**
 * @param tel the tel to set
 */
public void setTel(String tel) {
	this.tel = tel;
}

/**
 * @return the mobile
 */
public String getMobile() {
	return Mobile;
}
/**
 * @param mobile the mobile to set
 */
public void setMobile(String mobile) {
	Mobile = mobile;
}

/**
 * @return the birth_Date
 */
public String getBirth_Date() {
	return Birth_Date;
}
/**
 * @param birth_Date the birth_Date to set
 */
public void setBirth_Date(String birth_Date) {
	Birth_Date = birth_Date;
}
public int getActivated() {
	return Activated;
}
public void setActivated(int activated) {
	Activated = activated;
}
public Date getTrial_Date() {
	return Trial_Date;
}
public void setTrial_Date(Date trial_Date) {
	Trial_Date = trial_Date;
}
public int getTrial_Days() {
	return Trial_Days;
}
public void setTrial_Days(int trial_Days) {
	Trial_Days = trial_Days;
}
public String getFirst_Name() {
	return First_Name;
}
public void setFirst_Name(String first_Name) {
	First_Name = first_Name;
}
public String getLast_Name() {
	return Last_Name;
}
public void setLast_Name(String last_Name) {
	Last_Name = last_Name;
}
public int getAdmin_id() {
	return Admin_id;
}
public void setAdmin_id(int admin_id) {
	Admin_id = admin_id;
}
public int getAccount_Owner() {
	return Account_Owner;
}
public void setAccount_Owner(int account_Owner) {
	Account_Owner = account_Owner;
}
public String getEmail() {
	return Email;
}
public void setEmail(String email) {
	Email = email;
}
public String getPassword() {
	return Password;
}
public void setPassword(String password) {
	Password = password;
}

/**
 * @return the teacher_id
 */
public int getTeacher_id() {
	return Teacher_id;
}
/**
 * @param teacher_id the teacher_id to set
 */
public void setTeacher_id(int teacher_id) {
	Teacher_id = teacher_id;
}
/**
 * @return the student_id
 */
public int getStudent_id() {
	return Student_id;
}
/**
 * @param student_id the student_id to set
 */
public void setStudent_id(int student_id) {
	Student_id = student_id;
}


public int getParent_id() {
	return Parent_id;
}
public void setParent_id(int parent_id) {
	Parent_id = parent_id;
}
/**
 * @return the subscribed
 */
public int getSubscribed() {
	return Subscribed;
}
/**
 * @param subscribed the subscribed to set
 */
public void setSubscribed(int subscribed) {
	Subscribed = subscribed;
}
/**
 * @return the expiry_Date
 */
public Date getExpiry_Date() {
	return Expiry_Date;
}
/**
 * @param expiry_Date the expiry_Date to set
 */
public void setExpiry_Date(Date expiry_Date) {
	Expiry_Date = expiry_Date;
}
/**
 * @return the no_Of_Students
 */
public int getNo_Of_Students() {
	return No_Of_Students;
}
/**
 * @param no_Of_Students the no_Of_Students to set
 */
public void setNo_Of_Students(int no_Of_Students) {
	No_Of_Students = no_Of_Students;
}

/**
 * @return the no_Of_Created_Students
 */
public int getNo_Of_Created_Students() {
	return No_Of_Created_Students;
}
/**
 * @param no_Of_Created_Students the no_Of_Created_Students to set
 */
public void setNo_Of_Created_Students(int no_Of_Created_Students) {
	No_Of_Created_Students = no_Of_Created_Students;
}


/**
 * @return the agreement_id
 */
public String getAgreement_id() {
	return Agreement_id;
}
/**
 * @param agreement_id the agreement_id to set
 */
public void setAgreement_id(String agreement_id) {
	Agreement_id = agreement_id;
}


/**
 * @return the frequencyInterval
 */
public int getFrequencyInterval() {
	return FrequencyInterval;
}
/**
 * @param frequencyInterval the frequencyInterval to set
 */
public void setFrequencyInterval(int frequencyInterval) {
	FrequencyInterval = frequencyInterval;
}


/**
 * @return the last_Payment
 */
public int getLast_Payment() {
	return Last_Payment;
}
/**
 * @param last_Payment the last_Payment to set
 */
public void setLast_Payment(int last_Payment) {
	Last_Payment = last_Payment;
}


/**
 * @return the amount_Per_Cycle
 */
public int getAmount_Per_Cycle() {
	return Amount_Per_Cycle;
}
/**
 * @param amount_Per_Cycle the amount_Per_Cycle to set
 */
public void setAmount_Per_Cycle(int amount_Per_Cycle) {
	Amount_Per_Cycle = amount_Per_Cycle;
}
/**
 * @return the last_Payment_Date
 */
public String getLast_Payment_Date() {
	return Last_Payment_Date;
}
/**
 * @param last_Payment_Date the last_Payment_Date to set
 */
public void setLast_Payment_Date(String last_Payment_Date) {
	Last_Payment_Date = last_Payment_Date;
}

/**
 * @return the days_Consumed
 */
public int getDays_Consumed() {
	return Days_Consumed;
}
/**
 * @param days_Consumed the days_Consumed to set
 */
public void setDays_Consumed(int days_Consumed) {
	Days_Consumed = days_Consumed;
}


/**
 * @return the cost_Till_Today
 */
public int getCost_Till_Today() {
	return Cost_Till_Today;
}
/**
 * @param cost_Till_Today the cost_Till_Today to set
 */
public void setCost_Till_Today(int cost_Till_Today) {
	Cost_Till_Today = cost_Till_Today;
}

/**
 * @return the remaining_Amount
 */
public int getRemaining_Amount() {
	return Remaining_Amount;
}
/**
 * @param remaining_Amount the remaining_Amount to set
 */
public void setRemaining_Amount(int remaining_Amount) {
	Remaining_Amount = remaining_Amount;
}


/**
 * @return the agreementPending
 */
public boolean isAgreementPending() {
	return AgreementPending;
}
/**
 * @param agreementPending the agreementPending to set
 */
public void setAgreementPending(boolean agreementPending) {
	AgreementPending = agreementPending;
}


/**
 * @return the agreementCancelling
 */
public boolean isAgreementCancelling() {
	return AgreementCancelling;
}
/**
 * @param agreementCancelling the agreementCancelling to set
 */
public void setAgreementCancelling(boolean agreementCancelling) {
	AgreementCancelling = agreementCancelling;
}
/**
 * @return the agreement_State
 */
public String getAgreement_State() {
	return Agreement_State;
}
/**
 * @param agreement_State the agreement_State to set
 */
public void setAgreement_State(String agreement_State) {
	Agreement_State = agreement_State;
}


/**
 * @return the trialDays_Left
 */
public int getTrialDays_Left() {
	return TrialDays_Left;
}
/**
 * @param trialDays_Left the trialDays_Left to set
 */
public void setTrialDays_Left(int trialDays_Left) {
	TrialDays_Left = trialDays_Left;
}
/**
 * @return the fileName
 */
public String getFileName() {
	return FileName;
}
/**
 * @param fileName the fileName to set
 */
public void setFileName(String fileName) {
	FileName = fileName;
}

/**
 * @return the trial_Valid
 */
public boolean isTrial_Valid() {
	return Trial_Valid;
}
/**
 * @param trial_Valid the trial_Valid to set
 */
public void setTrial_Valid(boolean trial_Valid) {
	Trial_Valid = trial_Valid;
}

/**
 * @return the subscription_Valid
 */
public boolean isSubscription_Valid() {
	return Subscription_Valid;
}
/**
 * @param subscription_Valid the subscription_Valid to set
 */
public void setSubscription_Valid(boolean subscription_Valid) {
	Subscription_Valid = subscription_Valid;
}


/**
 * @return the subscription_Expiry
 */
public String getSubscription_Expiry() {
	return Subscription_Expiry;
}
/**
 * @param subscription_Expiry the subscription_Expiry to set
 */
public void setSubscription_Expiry(String subscription_Expiry) {
	Subscription_Expiry = subscription_Expiry;
}
/**
 * @return the trial_Expiry
 */
public String getTrial_Expiry() {
	return Trial_Expiry;
}
/**
 * @param trial_Expiry the trial_Expiry to set
 */
public void setTrial_Expiry(String trial_Expiry) {
	Trial_Expiry = trial_Expiry;
}


/**
 * @return the school_id
 */
public int getSchool_id() {
	return School_id;
}
/**
 * @param school_id the school_id to set
 */
public void setSchool_id(int school_id) {
	School_id = school_id;
}
/**
 * @return the student_First_Login
 */
public int getStudent_First_Login() {
	return Student_First_Login;
}
/**
 * @param student_First_Login the student_First_Login to set
 */
public void setStudent_First_Login(int student_First_Login) {
	Student_First_Login = student_First_Login;
}



public int getParent_First_Login() {
	return Parent_First_Login;
}
public void setParent_First_Login(int parent_First_Login) {
	Parent_First_Login = parent_First_Login;
}
/**
 * @return the teacher_First_Login
 */
public int getTeacher_First_Login() {
	return Teacher_First_Login;
}
/**
 * @param teacher_First_Login the teacher_First_Login to set
 */
public void setTeacher_First_Login(int teacher_First_Login) {
	Teacher_First_Login = teacher_First_Login;
}
/**
 * @return the student_Promoted
 */
public boolean isStudent_Promoted() {
	return Student_Promoted;
}
/**
 * @param student_Promoted the student_Promoted to set
 */
public void setStudent_Promoted(boolean student_Promoted) {
	Student_Promoted = student_Promoted;
}
/**
 * @return the year_Name
 */
public String getYear_Name() {
	return Year_Name;
}
/**
 * @param year_Name the year_Name to set
 */
public void setYear_Name(String year_Name) {
	Year_Name = year_Name;
}
public Child getChild() {
	return Child;
}
public void setChild(Child child) {
	Child = child;
}
public boolean isHas_saved_token() {
	return has_saved_token;
}
public void setHas_saved_token(boolean has_saved_token) {
	this.has_saved_token = has_saved_token;
}

}
