
package dao;

import exceptions.SchoolException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Student;

/**
 *
 */
public class SchoolDAOImpl implements SchoolDAO {

	private Connection connection;
	
	// Método para conectar con la bbdd
	@Override
	public void connect() throws SQLException {
		// Define connection parameters
		String url = "jdbc:mysql://localhost:3306/school";
		String user = "root";
		String pass = "";
		this.connection = DriverManager.getConnection(url, user, pass);
	}

	public Student getStudent(int studentId) {
		Student student = null;
		// prepare query
		String query = "select * from student where code = ? ";
		
		try (PreparedStatement ps = connection.prepareStatement(query)) { 
			// set id to search for
			ps.setInt(1,studentId);
		  	//System.out.println(ps.toString());
	        try (ResultSet rs = ps.executeQuery()) {
	        	if (rs.next()) {
	        		student =  new Student(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5));            		            				
	        	}
	        }
	    } catch (SQLException e) {
			// in case error in SQL
			e.printStackTrace();
		}
		return student;
	}

	/**
	 *
	 */
	@Override
	public ArrayList<Student> getAllStudents() throws SQLException {
		ArrayList<Student> students = new ArrayList<>();
		String select = "select * from student";
		Statement st = connection.createStatement();
		// Run query and read the result
		ResultSet rs = st.executeQuery(select);
		// for each result a new student is created
		while (rs.next()) {
			int code = rs.getInt("code");
			String name = rs.getString("name");
			String surname = rs.getString("surname");
			int age = rs.getInt("age");
			String gender = rs.getString("gender");
			Student s = new Student(code, name, surname, age, gender);
			students.add(s);
		}
		rs.close();
		st.close();
		return students;
	}

	@Override
	public boolean isStudent(Student s) throws SQLException {
		boolean exist = false;
		String select = "select * from student where code = " + s.getCode();
		Statement st = connection.createStatement();
		// run query and get result
		ResultSet rs = st.executeQuery(select);
		// check if result has data
		if (rs.next()) {
			exist = true;
		}
		// close resources
		rs.close();
		st.close();
		return exist;
	}

	@Override
	public void insertStudent(Student s) throws SQLException, SchoolException {
		if (isStudent(s)) {
			throw new SchoolException("Ya existe un alumno con ese código");
		}
		String insert = "insert into student values (?, ?, ?, ?, ?)";
		PreparedStatement ps = connection.prepareStatement(insert);
		// assign values to each parameter
		ps.setInt(1, s.getCode());
		ps.setString(2, s.getName());
		ps.setString(3, s.getSurname());
		ps.setInt(4, s.getAge());
		ps.setString(5, s.getGender());
		// run query
		ps.executeUpdate();
		// close resources
		ps.close();
	}

	@Override
	public void disconnect() throws SQLException {
		if (connection != null) {
			connection.close();
		}
	}

}
