package com.zobus.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zobus.auth.Authendicate;
import com.zobus.dbmanager.DatabaseConnectionManager;
import com.zobus.model.UsersModel;

import org.mindrot.jbcrypt.BCrypt;

public class UsersDAO {

	public static UsersModel authendicateUserWithToken(String token) {
		Connection connection = null;
		PreparedStatement stm = null;
		ResultSet result = null;
		UsersModel user = new UsersModel();

		String query = "select * from users where (login_token = ?) ;";

		try {
			connection = DatabaseConnectionManager.getConnection();
			stm = connection.prepareStatement(query);
			stm.setString(1, token);

			result = stm.executeQuery();
			if (!result.next()) {
				user = null;
			} else {
				user.setPassword(result.getString("password"));
				user.setUserId(result.getInt("user_id"));
				user.setName(result.getString("name"));
				user.setMailId(result.getString("mail_id"));
				user.setPhone(result.getString("phone"));
				user.setLoginToken(result.getString("login_token"));
			}

		} catch (

		SQLException e) {
			System.out.println(e + "\n error message from Users DAO");
			user = null;
		} finally {
			// Close resources in reverse order of opening
			try {
				if (result != null)
					result.close();
				if (stm != null)
					stm.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;

	}

	/*
	 * @param usarname
	 * 
	 * @param password
	 * 
	 * @return if the username and password is correction mean return UsersModel
	 * else null
	 */
	public static UsersModel authendicateUser(String username, String password) throws SQLException {
		Connection connection = null;
		PreparedStatement stm = null;
		ResultSet result = null;
		UsersModel user = new UsersModel();

		String query = "select * " + "from " + "users " + "where (phone=? or name = ?) ;";
		try {
			connection = DatabaseConnectionManager.getConnection();
			stm = connection.prepareStatement(query);
			stm.setString(1, username);
			stm.setString(2, username);

			result = stm.executeQuery();
			if (!result.next()) {
				user = null;
			} else {
				user.setPassword(result.getString("password"));
				if (!BCrypt.checkpw(password, user.getPassword())) {
					user = null;
				} else {
					user.setUserId(result.getInt("user_id"));
					user.setName(result.getString("name"));
					user.setMailId(result.getString("mail_id"));
					user.setPhone(result.getString("phone"));
					user.setLoginToken(result.getString("login_token"));
					if (user.getLoginToken() == null) {
						String token = Authendicate.generateLoginToken();
						user.setLoginToken(token);
						UsersDAO.uploadLoginToken(user.getUserId(), token);
					}
				}
			}

		} catch (SQLException e) {
			System.out.println(e + "\n error message from Users DAO");
			user = null;
		} finally {
			// Close resources in reverse order of opening
			try {
				if (result != null)
					result.close();
				if (stm != null)
					stm.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}

	/*
	 * @param user_id
	 * 
	 * @param login_token
	 * 
	 * @return boolean values if the token setted successfully in the databse else
	 * false
	 */
	private static boolean uploadLoginToken(int user_id, String login_token) {
		Connection connection = null;
		PreparedStatement stm = null;
		ResultSet result = null;
		boolean status = false;

		String query = "UPDATE users  " + "SET  " + "    login_token = ? " + "WHERE " + "    user_id = ?;";
		try {
			stm = connection.prepareStatement(query);
			stm.setString(1, login_token);
			stm.setInt(2, user_id);

			status = stm.execute();

		} catch (Exception e) {
			System.out.println(e + "\n error message from Users DAO");
		} finally {
			// Close resources in reverse order of opening
			try {
				if (result != null)
					result.close();
				if (stm != null)
					stm.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return status;
	}
}
