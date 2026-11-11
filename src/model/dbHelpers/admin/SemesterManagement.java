package model.dbHelpers.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import model.beans.admin.Exam_Semesters;
import model.beans.admin.Grade_Semester_Details;
import model.beans.admin.Grade_Semester_Terms;
import model.beans.admin.Grade_Semesters;
import model.beans.admin.Semester_Exam_Details;
import model.beans.admin.Year_Semester_Details;
import model.beans.admin.Year_Semesters;
import model.beans.admin.Year_Terms;
import model.dbHelpers.DbConnector;

public class SemesterManagement {
	public int createYearSemester(Year_Semester_Details year_Semester_Details, HttpServletRequest request){
		String query =  "INSERT INTO Year_Semesters (Semester_id, Semester_Name, School_Year_id) VALUES (Null,?, ?)";
		String sUpdateYearTermSemester=null;
		int updateQuery = 0;
		
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			 Statement statementYearTerms = connection.createStatement();){
			
			pstatement.setString(1, year_Semester_Details.getSemester_Name());
			pstatement.setInt(2, year_Semester_Details.getSchool_Year_id());
			updateQuery = pstatement.executeUpdate();
	        
			ResultSet resultSemester_id = pstatement.getGeneratedKeys();
			int semester_id=0;
			if(resultSemester_id.next()) 
			{
				semester_id = resultSemester_id.getInt(1);
			}

			//////////////////////////////Retrieve all Terms related to this Year ////////////////
	    	String sYearTerms = "select * from Year_Terms where School_Year_id='"+year_Semester_Details.getSchool_Year_id()+"' and Semester_id is null";
	    	ResultSet resultYearTerms = statementYearTerms.executeQuery(sYearTerms);
	    	//////////////////////////////////////////////////////////////////////////////////////////
			////////////////Update Terms with the Semester id //////////////////////////
	    	while (resultYearTerms.next())
	    	{
		    	String Year_Term_id = request.getParameter("Year_Term_id"+resultYearTerms.getString("Term_id"));
	    		
	    		if (Year_Term_id!=null)
	    		{
	    			sUpdateYearTermSemester = "update Year_Terms set semester_id = "+semester_id+" where term_id='"+Year_Term_id+"'";			
	    			PreparedStatement pstatementUpdateYearTermSemester = connection.prepareStatement(sUpdateYearTermSemester);
	    			//pstatementUpdateYearTermSemester.setInt(1, semester_id);
	    			updateQuery = pstatementUpdateYearTermSemester.executeUpdate(sUpdateYearTermSemester);
	    			pstatementUpdateYearTermSemester.close();
	    		}
	    	}
			////////////////////////////////////////////////////////////////////////////
	       
		}  catch (MySQLIntegrityConstraintViolationException ee) {
			// TODO Auto-generated catch block
			if(ee.toString().contains("Semester_Name_Unique"))
				updateQuery= -1;	
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	
	public int updateYearSemester(Year_Semester_Details year_Semester_Details, int semester_id, HttpServletRequest request){
		String query = "update Year_Semesters set Semester_Name=? where Semester_id="+semester_id;
		int updateQuery = 0;
		
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);

				Statement statement = connection.createStatement()){
			
			pstatement.setString(1, year_Semester_Details.getSemester_Name());
	        updateQuery = pstatement.executeUpdate();
	        
			//////////////////////////////Retrieve all Terms related to this year ////////////////
	    	String sYearTerms = "select * from Year_Terms where school_year_id='"+year_Semester_Details.getSchool_Year_id()+"' and (Semester_id="+semester_id+" or Semester_id is null)";
	    	ResultSet resultYearTerms = statement.executeQuery(sYearTerms);
	    	//////////////////////////////////////////////////////////////////////////////////////////
			////////////////Update Terms with the Semester id //////////////////////////
	    	while (resultYearTerms.next())
	    	{
		    	String Year_Term_id = request.getParameter("Year_Term_id"+resultYearTerms.getString("Term_id"));
	    		String sUpdateYearTermSemester=null;
	    		PreparedStatement pstatementUpdateYearTermSemester=null;
	    		if (Year_Term_id!=null)
	    		{
	    			sUpdateYearTermSemester = "update Year_Terms set semester_id="+semester_id+" where term_id="+Year_Term_id;	
					pstatementUpdateYearTermSemester = connection.prepareStatement(sUpdateYearTermSemester);
					//pstatementUpdateYearTermSemester.setInt(1, semester_id);
					updateQuery = pstatementUpdateYearTermSemester.executeUpdate(sUpdateYearTermSemester);
	    		}
	    		else
	    		{
	    			sUpdateYearTermSemester = "update Year_Terms set semester_id=NULL where term_id="+request.getParameter("hiddenYear_Term_id"+resultYearTerms.getString("Term_id"));			
	    			pstatementUpdateYearTermSemester = connection.prepareStatement(sUpdateYearTermSemester);
	    			updateQuery = pstatementUpdateYearTermSemester.executeUpdate(sUpdateYearTermSemester);
	    		}
	    		pstatementUpdateYearTermSemester.close();
	    	}
			////////////////////////////////////////////////////////////////////////////
	       
		}catch (MySQLIntegrityConstraintViolationException ee) {
			// TODO Auto-generated catch block
			if(ee.toString().contains("Semester_Name_Unique"))
				updateQuery= -1;	
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	
	public int deleteYearSemester(int semester_id){
		String query = "Delete from Year_Semesters where Semester_id="+semester_id;
		int updateQuery = 0;
		
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);){
	
			updateQuery = pstatement.executeUpdate();
	
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	
	public Year_Semesters getYearSemesterList(int school_year_id){
		String query = "select * from Year_Semesters where school_year_id="+school_year_id;
		Year_Semesters Year_Semesters = new Year_Semesters(); 
		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);
		   	ArrayList<Integer> semester_id = new ArrayList<Integer>();
	    	ArrayList<String> semester_Name = new ArrayList<String>();
	    	while(result.next()) {
	    		semester_id.add(result.getInt("semester_id"));

	    		semester_Name.add(result.getString("semester_Name"));	    		
	        } 
	    	Year_Semesters.setSemester_id(semester_id);
	    	Year_Semesters.setSemester_Name(semester_Name);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Year_Semesters;	
	}	
	
	public Year_Semester_Details getYearSemester(int semester_id){
	String query = "select * from Year_Semesters where Semester_id="+semester_id;
	Year_Semester_Details year_Semester_Details = new Year_Semester_Details(); 
	
	try (Connection connection = DbConnector.connectToDb();
		 Statement statement = connection.createStatement();){
					
		ResultSet result = statement.executeQuery(query);
		while(result.next())
		{
			year_Semester_Details.setSemester_id(semester_id);
			year_Semester_Details.setSemester_Name(result.getString("Semester_Name"));
			year_Semester_Details.setSchool_Year_id(result.getInt("School_Year_id"));
		}

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return year_Semester_Details;
}
	
	public Year_Terms getYearSemesterTerms(int school_Year_id, int semester_id){
		String query = "select * from Year_Terms where school_Year_id="+school_Year_id+" and (Semester_id="+semester_id+" or Semester_id is null)";
		Year_Terms Year_Terms = new Year_Terms(); 
		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
	    	ResultSet result = statement.executeQuery(query);

	    	ArrayList<Integer> year_Term_id = new ArrayList<Integer>();
	    	ArrayList<String> year_Term_Name = new ArrayList<String>();
	    	ArrayList<String> term_Semester_id = new ArrayList<String>();
	    	while(result.next()) 
	    	{
	    		year_Term_id.add(result.getInt("Term_id"));	
	    		year_Term_Name.add(result.getString("Term_Name"));	
	    		term_Semester_id.add(result.getString("Semester_id"));
	        } 
	    	Year_Terms.setYear_Term_id(year_Term_id);
	    	Year_Terms.setYear_Term_Name(year_Term_Name);
	    	Year_Terms.setTerm_Semester_id(term_Semester_id);

	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Year_Terms;	
	}
	
	public int createGradeSemester(Grade_Semester_Details grade_Semester_Details, HttpServletRequest request){
		String query =  "INSERT INTO Grade_Semesters (Semester_id, Semester_Name, School_Year_id, Grade_id) VALUES (Null,?, ?, ?)";
		String sUpdateGradeTermSemester=null;
		int updateQuery = 0;
		
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			 Statement statementGradeTerms = connection.createStatement();){
			
			pstatement.setString(1, grade_Semester_Details.getSemester_Name());
			pstatement.setInt(2, grade_Semester_Details.getSchool_Year_id());
			pstatement.setInt(3, grade_Semester_Details.getGrade_id());
			updateQuery = pstatement.executeUpdate();
	        
			ResultSet resultSemester_id = pstatement.getGeneratedKeys();
			int semester_id=0;
			if(resultSemester_id.next()) 
			{
				semester_id = resultSemester_id.getInt(1);
			}

			//////////////////////////////Retrieve all Terms related to this Year ////////////////
	    	String sGradeTerms = "select * from Grade_Terms where School_Year_id='"+grade_Semester_Details.getSchool_Year_id()+" and Grade_id="+grade_Semester_Details.getGrade_id()+"' and Semester_id is null";
	    	ResultSet resultGradeTerms = statementGradeTerms.executeQuery(sGradeTerms);
	    	//////////////////////////////////////////////////////////////////////////////////////////
			////////////////Update Terms with the Semester id //////////////////////////
	    	while (resultGradeTerms.next())
	    	{
		    	String Grade_Term_id = request.getParameter("Grade_Term_id"+resultGradeTerms.getString("Grade_Term_id"));
	    		
	    		if (Grade_Term_id!=null)
	    		{
	    			sUpdateGradeTermSemester = "update Grade_Terms set semester_id = "+semester_id+" where Grade_Term_id='"+Grade_Term_id+"'";			
	    			PreparedStatement pstatementUpdateGradeTermSemester = connection.prepareStatement(sUpdateGradeTermSemester);
	    			//pstatementUpdateYearTermSemester.setInt(1, semester_id);
	    			updateQuery = pstatementUpdateGradeTermSemester.executeUpdate(sUpdateGradeTermSemester);
	    			pstatementUpdateGradeTermSemester.close();
	    		}
	    	}
			////////////////////////////////////////////////////////////////////////////
	       
		}catch (MySQLIntegrityConstraintViolationException ee) {
			// TODO Auto-generated catch block
			if(ee.toString().contains("Year_Term_Semester_Unique"))
				updateQuery=-1;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	public int updateGradeSemester(Grade_Semester_Details grade_Semester_Details, int semester_id, HttpServletRequest request){
		String query = "update Grade_Semesters set Semester_Name=? where Semester_id="+semester_id;
		int updateQuery = 0;
		
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);

				Statement statement = connection.createStatement()){
			
			pstatement.setString(1, grade_Semester_Details.getSemester_Name());
	        updateQuery = pstatement.executeUpdate();
	        
			//////////////////////////////Retrieve all Terms related to this year ////////////////
	    	String sGradeTerms = "select * from Grade_Terms where school_year_id='"+grade_Semester_Details.getSchool_Year_id()+" and Grade_id="+grade_Semester_Details.getGrade_id()+"' and (Semester_id="+semester_id+" or Semester_id is null)";
	    	ResultSet resultGradeTerms = statement.executeQuery(sGradeTerms);
	    	//////////////////////////////////////////////////////////////////////////////////////////
			////////////////Update Terms with the Semester id //////////////////////////
	    	while (resultGradeTerms.next())
	    	{
		    	String Grade_Term_id = request.getParameter("Grade_Term_id"+resultGradeTerms.getString("Grade_Term_id"));
		    	String hiddenGrade_Term_id= request.getParameter("hiddenGrade_Term_id"+resultGradeTerms.getString("Grade_Term_id"));
	    		String sUpdateGradeTermSemester=null;
	    		PreparedStatement pstatementUpdateGradeTermSemester=null;
	    		if (Grade_Term_id!=null)
	    		{
	    			sUpdateGradeTermSemester = "update Grade_Terms set semester_id="+semester_id+" where Grade_Term_id="+Grade_Term_id;	
					pstatementUpdateGradeTermSemester = connection.prepareStatement(sUpdateGradeTermSemester);
					//pstatementUpdateYearTermSemester.setInt(1, semester_id);
					updateQuery = pstatementUpdateGradeTermSemester.executeUpdate(sUpdateGradeTermSemester);
	    		}
	    		else
	    		{
	    			if(hiddenGrade_Term_id!=null)
	    			{
		    			sUpdateGradeTermSemester = "update Grade_Terms set semester_id=NULL where Grade_Term_id="+hiddenGrade_Term_id;			
		    			pstatementUpdateGradeTermSemester = connection.prepareStatement(sUpdateGradeTermSemester);
		    			updateQuery = pstatementUpdateGradeTermSemester.executeUpdate(sUpdateGradeTermSemester);
	    			}
	    		}
	    		if(pstatementUpdateGradeTermSemester!=null)
	    		{
	    			pstatementUpdateGradeTermSemester.close();
	    		}
	    	}
			////////////////////////////////////////////////////////////////////////////
	       
		}catch (MySQLIntegrityConstraintViolationException ee) {
			// TODO Auto-generated catch block
			if(ee.toString().contains("Year_Term_Semester_Unique"))
				updateQuery=-1;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	
	public int deleteGradeSemester(int semester_id){
		String query = "Delete from Grade_Semesters where Semester_id="+semester_id;
		int updateQuery = 0;
		
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);){
	
			updateQuery = pstatement.executeUpdate();
	
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	
	public Grade_Semesters getGradeSemesterList(int school_year_id, int grade_id){
		String query = "select * from Grade_Semesters where school_year_id="+school_year_id+" and Grade_id="+grade_id;
		Grade_Semesters Grade_Semesters = new Grade_Semesters(); 
		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
						
			ResultSet result = statement.executeQuery(query);
		   	ArrayList<Integer> semester_id = new ArrayList<Integer>();
	    	ArrayList<String> semester_Name = new ArrayList<String>();
	    	while(result.next()) {
	    		semester_id.add(result.getInt("semester_id"));

	    		semester_Name.add(result.getString("semester_Name"));	    		
	        } 
	    	Grade_Semesters.setSemester_id(semester_id);
	    	Grade_Semesters.setSemester_Name(semester_Name);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Grade_Semesters;	
	}	
	
	public Grade_Semester_Details getGradeSemester(int semester_id){
	String query = "select * from Grade_Semesters where Semester_id="+semester_id;
	Grade_Semester_Details grade_Semester_Details = new Grade_Semester_Details(); 
	
	try (Connection connection = DbConnector.connectToDb();
		 Statement statement = connection.createStatement();
			Statement statementSemesterHasGradedExams = connection.createStatement();){
					
		ResultSet result = statement.executeQuery(query);
		while(result.next())
		{
			grade_Semester_Details.setSemester_id(semester_id);
			grade_Semester_Details.setSemester_Name(result.getString("Semester_Name"));
			grade_Semester_Details.setSchool_Year_id(result.getInt("School_Year_id"));
			grade_Semester_Details.setGrade_id(result.getInt("Grade_id"));
			
    		////////////////////////Check if the Semester has graded exams i.e Students already graded for exams in that semester/////////////
    		String sSemesterHasGradedExams = "select Student_Grade from student_semesterExam where exam_id in (select exam_id from Course_SemesterExam where Grade_Semester_id="+semester_id+")";
    		ResultSet resultSemesterHasGradedExams = statementSemesterHasGradedExams.executeQuery(sSemesterHasGradedExams);
    		if(resultSemesterHasGradedExams.isBeforeFirst())
    		{
    			grade_Semester_Details.setHasGradedExams(true);
    		}
    		else
    		{
    			grade_Semester_Details.setHasGradedExams(false);
    		}
		}

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return grade_Semester_Details;
}
	
	public Grade_Semester_Terms getGradeSemesterTerms(int school_Year_id, int grade_id, int semester_id){
		String query = "select * from Grade_Terms where school_Year_id="+school_Year_id+" and Grade_id="+grade_id+" and (Semester_id="+semester_id+" or Semester_id is null)";
		Grade_Semester_Terms Grade_Semester_Terms = new Grade_Semester_Terms(); 
		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statement = connection.createStatement();){
	    	ResultSet result = statement.executeQuery(query);

	    	ArrayList<Integer> grade_Term_id = new ArrayList<Integer>();
	    	ArrayList<String> grade_Term_Name = new ArrayList<String>();
	    	ArrayList<String> term_Semester_id = new ArrayList<String>();
	    	while(result.next()) 
	    	{
	    		grade_Term_id.add(result.getInt("Grade_Term_id"));	
	    		grade_Term_Name.add(result.getString("Term_Name"));	
	    		term_Semester_id.add(result.getString("Semester_id"));
	        } 
	    	Grade_Semester_Terms.setGrade_Term_id(grade_Term_id);
	    	Grade_Semester_Terms.setGrade_Term_Name(grade_Term_Name);
	    	Grade_Semester_Terms.setTerm_Semester_id(term_Semester_id);

	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Grade_Semester_Terms;	
	}
	
	
	public Exam_Semesters getExamSemesterList(int school_year_id, int grade_id){
		Exam_Semesters Exam_Semesters = new Exam_Semesters(); 
		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statementExamSemesters = connection.createStatement();
			 Statement statementGradeTerms = connection.createStatement();){
			
			String semester_Source = null;
			String sExamSemesters = "select * from Grade_Semesters where school_year_id="+school_year_id+" and Grade_id="+grade_id;						
			ResultSet resultExamSemesters = statementExamSemesters.executeQuery(sExamSemesters);
			semester_Source="Grade";
			if(!resultExamSemesters.isBeforeFirst())
		   	{
				///////First check if this Grade has terms or not////////////////////////
				String sGradeTerms = "select * from Grade_Terms where school_year_id="+school_year_id+" and Grade_id="+grade_id;						
				ResultSet resultGradeTerms = statementGradeTerms.executeQuery(sGradeTerms);
		   		////////////////////////////////////////////////////////////////////////
		   		if(!resultGradeTerms.isBeforeFirst())
		   		{
					sExamSemesters = "select * from Year_Semesters where school_year_id="+school_year_id;						
					resultExamSemesters = statementExamSemesters.executeQuery(sExamSemesters);
					semester_Source="Year";
		   		}
		   	}
			
			ArrayList<Integer> semester_id = new ArrayList<Integer>();
	    	ArrayList<String> semester_Name = new ArrayList<String>();
	    	while(resultExamSemesters.next()) {
	    		semester_id.add(resultExamSemesters.getInt("semester_id"));

	    		semester_Name.add(resultExamSemesters.getString("semester_Name"));	    		
	        } 
	    	Exam_Semesters.setSemester_id(semester_id);
	    	Exam_Semesters.setSemester_Name(semester_Name);
	    	Exam_Semesters.setSemester_Source(semester_Source);
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Exam_Semesters;	
	}
	
	public int createSemesterExam(Semester_Exam_Details semester_Exam_Details){
		String semester_Source = semester_Exam_Details.getSemester_Source();
		String query = null;
		if(semester_Source.equals("Grade"))
		{
			query =  "INSERT INTO Course_SemesterExam (Exam_id, Course_id, Grade_Semester_id, Exam_Grade, Exam_Contribution) VALUES (Null,?, ?, ?, ?)";
		}
		else
		{
			query =  "INSERT INTO Course_SemesterExam (Exam_id, Course_id, Year_Semester_id, Exam_Grade, Exam_Contribution) VALUES (Null,?, ?, ?, ?)";
		}		 
		int updateQuery = 0;
		
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);){
			
			pstatement.setInt(1, semester_Exam_Details.getCourse_id());
			pstatement.setInt(2, semester_Exam_Details.getSemester_id());
			pstatement.setInt(3, semester_Exam_Details.getExam_Grade());
			pstatement.setInt(4, semester_Exam_Details.getExam_Contribution());
			updateQuery = pstatement.executeUpdate();
  
		}catch (MySQLIntegrityConstraintViolationException ee) {
			// TODO Auto-generated catch block
			if(ee.toString().contains("Unique_GSemester_Course") || ee.toString().contains("Unique_YSemester_Course"))
				updateQuery = -1;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	
	public int updateSemesterExam(Semester_Exam_Details semester_Exam_Details, int exam_id){
		String semester_Source = semester_Exam_Details.getSemester_Source();
		String query = null;
		if(semester_Source.equals("Grade"))
		{
			query =  "update Course_SemesterExam set Course_id=?, Grade_Semester_id=?, Exam_Grade=?, Exam_Contribution=?, Year_Semester_id=NULL  where exam_id="+exam_id;
		}
		else
		{
			query =  "update Course_SemesterExam set Course_id=?, Year_Semester_id=?, Exam_Grade=?, Exam_Contribution=?, Grade_Semester_id=NULL where exam_id="+exam_id;
		}		 
		int updateQuery = 0;
		
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);
				Statement statementOldExamGrade = connection.createStatement();
				Statement statementStudentsGrading = connection.createStatement();){
			
			String sOldExamGrade = "select Exam_Grade from Course_SemesterExam where exam_id="+exam_id;
			ResultSet resultOldExamGrade = statementOldExamGrade.executeQuery(sOldExamGrade);
			int oldExamGrade = -1;
			while(resultOldExamGrade.next())
			{
				oldExamGrade = resultOldExamGrade.getInt("Exam_Grade");
			}
			pstatement.setInt(1, semester_Exam_Details.getCourse_id());
			pstatement.setInt(2, semester_Exam_Details.getSemester_id());
			pstatement.setInt(3, semester_Exam_Details.getExam_Grade());
			pstatement.setInt(4, semester_Exam_Details.getExam_Contribution());
			updateQuery = pstatement.executeUpdate();
			if(updateQuery!=0 && semester_Exam_Details.getExam_Grade()!=oldExamGrade)
			{
				double updateRatio = (double) semester_Exam_Details.getExam_Grade() / (double) oldExamGrade;				
				String sStudentsGrading = "select * from Student_SemesterExam where Exam_id="+exam_id;
				ResultSet resultStudentsGrading = statementStudentsGrading.executeQuery(sStudentsGrading);
				int student_id=0;
				PreparedStatement pstatementUpdateGrading=null;
				while(resultStudentsGrading.next())
				{
					student_id = resultStudentsGrading.getInt("Student_id");
					int old_Student_Exam_Grade = resultStudentsGrading.getInt("Student_Grade");
					int new_Student_Exam_Grade = (int)(Math.round(updateRatio * old_Student_Exam_Grade));				
					String sUpdateGrading = "update Student_SemesterExam set Student_Grade=? where Student_id="+student_id+" and Exam_id="+exam_id;
					pstatementUpdateGrading = connection.prepareStatement(sUpdateGrading);
					pstatementUpdateGrading.setInt(1, new_Student_Exam_Grade);
					pstatementUpdateGrading.executeUpdate();
				}
				if(pstatementUpdateGrading!=null)
				{
					pstatementUpdateGrading.close();
				}				
			}
		}catch (MySQLIntegrityConstraintViolationException ee) {
			// TODO Auto-generated catch block
			if(ee.toString().contains("Unique_GSemester_Course") || ee.toString().contains("Unique_YSemester_Course"))
				updateQuery = -1;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	
	public int deleteSemesterExam(int exam_id){
		String query = "Delete from Course_SemesterExam where exam_id="+exam_id;
		int updateQuery = 0;
		
		try (Connection connection = DbConnector.connectToDb();
			 PreparedStatement pstatement = connection.prepareStatement(query);){
	
			updateQuery = pstatement.executeUpdate();
	
	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	
	public Semester_Exam_Details getSemesterExam(int course_id, int semester_id, String semester_Source){
		Semester_Exam_Details Semester_Exam_Details = new Semester_Exam_Details(); 
		
		try (Connection connection = DbConnector.connectToDb();
			 Statement statementSemesterExam = connection.createStatement();
			 Statement statementSemesterTerms = connection.createStatement();
			 Statement statementExamHasGrades = connection.createStatement();){

			String sSemesterTerms = null;
			if(semester_Source!=null && semester_Source.equals("Grade"))
			{
				sSemesterTerms = "select * from Grade_Terms where Semester_id="+semester_id;	
			}
			else
			{
				sSemesterTerms = "select * from Year_Terms where Semester_id="+semester_id;	
			}					
			ResultSet resultSemesterTerms = statementSemesterTerms.executeQuery(sSemesterTerms);
			if(!resultSemesterTerms.isBeforeFirst())
			{
				Semester_Exam_Details.setHasTerms(false);
			}
			else
			{
				Semester_Exam_Details.setHasTerms(true);
				String sSemesterExam = null;
				if(semester_Source!=null && semester_Source.equals("Grade"))
				{
					sSemesterExam = "select * from Course_SemesterExam where Course_id="+course_id+" and Grade_Semester_id="+semester_id;	
				}
				else
				{
					sSemesterExam = "select * from Course_SemesterExam where Course_id="+course_id+" and Year_Semester_id="+semester_id;	
				}					
				ResultSet resultSemesterExam = statementSemesterExam.executeQuery(sSemesterExam);

		    	while(resultSemesterExam.next())
		    	{
		    		Semester_Exam_Details.setExam_id(resultSemesterExam.getInt("Exam_id"));
		    		Semester_Exam_Details.setCourse_id(course_id);
		    		Semester_Exam_Details.setSemester_id(semester_id); 		
		    		Semester_Exam_Details.setExam_Grade(resultSemesterExam.getInt("Exam_Grade"));
		    		Semester_Exam_Details.setExam_Contribution(resultSemesterExam.getInt("Exam_Contribution"));
		    		
		    		////////////////////////////Check if the exam has grades i.e Students already graded for that exam/////////////
		    		String sExamHasGrades = "select Student_Grade from student_semesterExam where exam_id="+resultSemesterExam.getInt("Exam_id");
		    		ResultSet resultExamHasGrades = statementExamHasGrades.executeQuery(sExamHasGrades);
		    		if(resultExamHasGrades.isBeforeFirst())
		    		{
		    			Semester_Exam_Details.setHasGrades(true);
		    		}
		    		else
		    		{
		    			Semester_Exam_Details.setHasGrades(false);
		    		}
		        } 
			}


	       
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Semester_Exam_Details;	
	}
}
