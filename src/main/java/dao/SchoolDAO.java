package dao;

import java.sql.SQLException;
import java.util.ArrayList;

import exceptions.SchoolException;
import model.Student;

public interface SchoolDAO {

	public void connect() throws SQLException;

	public void disconnect() throws SQLException;

	public ArrayList<Student> getAllStudents() throws SQLException;

	public void insertStudent(Student s) throws SQLException, SchoolException;
	
	public boolean isStudent(Student s) throws SQLException;

}
