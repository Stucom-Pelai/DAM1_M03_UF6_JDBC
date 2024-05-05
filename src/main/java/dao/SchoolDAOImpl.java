
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
 * @author Maria del Mar
 */
public class SchoolDAOImpl implements SchoolDAO {

	private Connection connection;
	
	/**
	 *
	 */
	@Override
	public ArrayList<Student> getAllStudents() throws SQLException {
		ArrayList<Student> students = new ArrayList<>();
		String select = "select * from student";
		Statement st = connection.createStatement();
		// Ejecutamos la consulta y recogemos resultado
		ResultSet rs = st.executeQuery(select);
		// Recorremos el resultado y vamos leyendo datos de los alumnos
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
	public void insertStudent(Student s) throws SQLException, SchoolException {
		if (isStudent(s)) {
			throw new SchoolException("Ya existe un alumno con ese código");
		}
		String insert = "insert into student values (?, ?, ?, ?, ?)";
		PreparedStatement ps = connection.prepareStatement(insert);
		// Definimos los parámetros ?
		ps.setInt(1, s.getCode());
		ps.setString(2, s.getName());
		ps.setString(3, s.getSurname());
		ps.setInt(4, s.getAge());
		ps.setString(5, s.getGender());
		// Ejecutamos la consulta
		ps.executeUpdate();
		// cierro recursos
		ps.close();
	}

	@Override
	public boolean isStudent(Student s) throws SQLException {
		boolean exist = false;
		String select = "select * from student where code = " + s.getCode();
		Statement st = connection.createStatement();
		// ejecutamos consulta recogiendo el resultado
		ResultSet rs = st.executeQuery(select);
		// Comprobamos si hay resultado de la consulta
		if (rs.next()) {
			exist = true;
		}
		// cerramos recursos
		rs.close();
		st.close();
		return exist;
	}

	// Método para conectar con la bbdd
	@Override
	public void connect() throws SQLException {
		// Define connection parameters
		String url = "jdbc:mysql://localhost:3306/school";
		String user = "root";
		String pass = "";
		this.connection = DriverManager.getConnection(url, user, pass);
	}

	@Override
	public void disconnect() throws SQLException {
		if (connection != null) {
			connection.close();
		}
	}

}
