package model.dbHelpers.teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.beans.admin.Students;
import model.beans.teacher.Student_List;
import model.dbHelpers.DbConnector;
import model.dbHelpers.admin.StudentManagement;

public class GradingManagement {
	public Student_List getGrading(int assignment_id, int class_id, int course_id, int group_id){
		Student_List Student_List = new Student_List();
		try (Connection connection = DbConnector.connectToDb();
				Statement statementElective = connection.createStatement();
				Statement statementStudents = connection.createStatement();
				Statement statementStudentGrade = connection.createStatement();){			
			StudentManagement studentManagement = new StudentManagement();
			Students students = new Students();
			students = studentManagement.getStudentsByClassCourseGroup(class_id, course_id, group_id);
			ArrayList<Integer> studensList = students.getStudent_id();   				
			/////////////////////////////////////////////////////////////////////////////////////				
			ArrayList<ArrayList<String>> StudentList = new ArrayList<ArrayList<String>>();

			int i=0;
			for (int student_id : studensList)
			{
				String sStudentGrade = "select Assignment_Grade, Remarks from student_assignment_grade where Assignment_id="+assignment_id+" and Student_id="+student_id;
				ResultSet resultStudentGrade = statementStudentGrade.executeQuery(sStudentGrade);
				String Student_Grade = null;
				String Remarks = null;
				while (resultStudentGrade.next())
				{
					Student_Grade = resultStudentGrade.getString("Assignment_Grade");
					Remarks = resultStudentGrade.getString("Remarks");
		
				}
				StudentList.add(new ArrayList<String>());
				StudentList.get(i).add(0,null);
				StudentList.get(i).set(0, String.valueOf(student_id));
				StudentList.get(i).add(1,null);
				StudentList.get(i).set(1, students.getFirst_Name().get(i));
				StudentList.get(i).add(2,null);
				StudentList.get(i).set(2, students.getLast_Name().get(i));
				StudentList.get(i).add(3,null);
				StudentList.get(i).set(3, Student_Grade);
				StudentList.get(i).add(4,null);
				StudentList.get(i).set(4, String.valueOf(students.getClass_id().get(i)));
				StudentList.get(i).add(5,null);
				StudentList.get(i).set(5, Remarks);
				i=i+1;
			}
			Student_List.setStudentList(StudentList);
			Student_List.setAssignment_id(assignment_id);
			Student_List.setAccount_id(students.getAccount_id());
			Student_List.setSchool_id(students.getSchool_id());
			Student_List.setFileName(students.getFileName());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Student_List;
	}
	
	public int getStudentGrading(int assignment_id, int student_id){
		int studentGrading = -1;
		String query = "select Assignment_Grade from Student_Assignment_Grade where Student_id="+student_id+" and Assignment_id="+assignment_id;
		try (Connection connection = DbConnector.connectToDb();
				Statement statement = connection.createStatement();){

			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next())
			{
				studentGrading = resultSet.getInt("Assignment_Grade");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return studentGrading;
	}
	public int createGrading(int assignment_id, int student_id, int assignment_Grade, String remarks){
		int updateQuery = 0;
		String query = "INSERT INTO Student_Assignment_Grade (Student_id, Assignment_id, Assignment_Grade, Remarks) VALUES (?, ?, ?, ?)";	
		try (Connection connection = DbConnector.connectToDb();
				PreparedStatement pstatement = connection.prepareStatement(query);){	
			pstatement.setInt(1, student_id);
			pstatement.setInt(2, assignment_id);
			pstatement.setInt(3, assignment_Grade);
			pstatement.setString(4, remarks);	
			updateQuery = pstatement.executeUpdate();
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	
	public int updateGrading(int assignment_id, int student_id, int assignment_Grade, String remarks){
		int updateQuery = 0;
		String query = null;
		if(assignment_Grade!=-1)
			query = "update Student_Assignment_Grade set  Remarks=?, Assignment_Grade=? where Student_id="+student_id+" and Assignment_id="+assignment_id;
		else
			query = "update Student_Assignment_Grade set  Remarks=? where Student_id="+student_id+" and Assignment_id="+assignment_id;
		try (Connection connection = DbConnector.connectToDb();
				PreparedStatement pstatement = connection.prepareStatement(query);){
			pstatement.setString(1, remarks);
			if(assignment_Grade!=-1)
				pstatement.setInt(2, assignment_Grade);	
			updateQuery = pstatement.executeUpdate();
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	
	public int deleteGrading(int assignment_id, int student_id){
		int updateQuery = 0;
		String query = "Delete from Student_Assignment_Grade where Student_id="+student_id+" and Assignment_id="+assignment_id;
		try (Connection connection = DbConnector.connectToDb();
				PreparedStatement pstatement = connection.prepareStatement(query);){

			updateQuery = pstatement.executeUpdate();	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	
	
	public int createSemesterGrading(int semester_Exam_id, int student_id, int student_Grade){
		int updateQuery = 0;
		String query = "INSERT INTO Student_SemesterExam (Student_id, Exam_id, Student_Grade) VALUES (?, ?, ?)";	
		try (Connection connection = DbConnector.connectToDb();
				PreparedStatement pstatement = connection.prepareStatement(query);){	
			pstatement.setInt(1, student_id);
			pstatement.setInt(2, semester_Exam_id);
			pstatement.setInt(3, student_Grade);	
			updateQuery = pstatement.executeUpdate();
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	
	public int updateSemesterGrading(int semester_Exam_id, int student_id, int student_Grade){
		int updateQuery = 0;
		String query = "update Student_SemesterExam set Student_Grade=? where Student_id="+student_id+" and Exam_id="+semester_Exam_id;
		try (Connection connection = DbConnector.connectToDb();
				PreparedStatement pstatement = connection.prepareStatement(query);){
			pstatement.setInt(1, student_Grade);	
			updateQuery = pstatement.executeUpdate();
	    	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	
	public int deleteSemesterGrading(int semester_Exam_id, int student_id){
		int updateQuery = 0;
		String query = "Delete from Student_SemesterExam where Student_id="+student_id+" and Exam_id="+semester_Exam_id;
		try (Connection connection = DbConnector.connectToDb();
				PreparedStatement pstatement = connection.prepareStatement(query);){

			updateQuery = pstatement.executeUpdate();	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return updateQuery;
	}
	
	public int getStudentSemesterGrading(int semester_Exam_id, int student_id){
		int studentGrading = -1;
		String query = "select Student_Grade from Student_SemesterExam where Student_id="+student_id+" and Exam_id="+semester_Exam_id;
		try (Connection connection = DbConnector.connectToDb();
				Statement statement = connection.createStatement();){

			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next())
			{
				studentGrading = resultSet.getInt("Student_Grade");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return studentGrading;
	}
	public Student_List getSemesterGrading(int semester_Exam_id, int class_id, int course_id, int group_id){
		Student_List Student_List = new Student_List();
		try (Connection connection = DbConnector.connectToDb();
				Statement statementElective = connection.createStatement();
				Statement statementStudents = connection.createStatement();
				Statement statementExamGrade = connection.createStatement();
				Statement statementStudentGrade = connection.createStatement();){

			StudentManagement studentManagement = new StudentManagement();
			Students students = new Students();
			students = studentManagement.getStudentsByClassCourseGroup(class_id, course_id, group_id);
			ArrayList<Integer> studensList = students.getStudent_id(); 
			/////////////////////////////////////////////////////////////////////////////////////			
			ArrayList<ArrayList<String>> StudentList = new ArrayList<ArrayList<String>>();

			int i=0;
			for (int student_id : studensList)
			{
				String sStudentGrade = "select Student_Grade from student_semesterExam where Exam_id="+semester_Exam_id+" and Student_id="+student_id;
				ResultSet resultStudentGrade = statementStudentGrade.executeQuery(sStudentGrade);
				String Student_Grade = null;
				while (resultStudentGrade.next())
				{
					Student_Grade = resultStudentGrade.getString("Student_Grade");
		
				}
				StudentList.add(new ArrayList<String>());
				StudentList.get(i).add(0,null);
				StudentList.get(i).set(0, String.valueOf(student_id));
				StudentList.get(i).add(1,null);
				StudentList.get(i).set(1, students.getFirst_Name().get(i));
				StudentList.get(i).add(2,null);
				StudentList.get(i).set(2, students.getLast_Name().get(i));
				StudentList.get(i).add(3,null);
				StudentList.get(i).set(3, Student_Grade);
				StudentList.get(i).add(4,null);
				StudentList.get(i).set(4, String.valueOf(students.getClass_id().get(i)));
				i=i+1;
			}
			/////////////Get Semester Exam Grade //////////////////////////////////
			String sExamGrade = "select Exam_Grade from Course_semesterExam where exam_id="+semester_Exam_id;
			ResultSet resultExamGrade = statementExamGrade.executeQuery(sExamGrade);
			int semester_Exam_Grade=-1;
			while(resultExamGrade.next())
			{
				semester_Exam_Grade = resultExamGrade.getInt("Exam_Grade");
			}
			////////////////////////////////////////////////
			Student_List.setStudentList(StudentList);
			Student_List.setSemester_Exam_id(semester_Exam_id);
			if(semester_Exam_Grade!=-1)
			{
				Student_List.setSemester_Exam_Grade(semester_Exam_Grade);
			}
			Student_List.setAccount_id(students.getAccount_id());
			Student_List.setSchool_id(students.getSchool_id());
			Student_List.setFileName(students.getFileName());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Student_List;
	}	
}
