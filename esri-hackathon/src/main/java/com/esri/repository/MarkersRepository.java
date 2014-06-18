package com.esri.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import com.esri.model.Marker;



/**
 * 
 * Repository class for Bookings CRUD
 */
public class MarkersRepository {




	public ArrayList<Marker> getAllMarkers() throws Exception {

	
		String sql = "SELECT * FROM tbl_markers";
			
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		ArrayList<Marker> markersList = new ArrayList<Marker>();

		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			conn = DbConnect.getConnection(DBType.MYSQL);
			stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(); 
			
			while (rs.next()) {
				Marker marker = new Marker();

				marker.setMarkerId(rs.getInt("marker_id"));
				marker.setLatitude(rs.getString("latitude"));
				marker.setLongitude(rs.getString("longitude"));
				marker.setTitle(rs.getString("title"));
				marker.setMainText(rs.getString("main_body"));

				markersList.add(marker);
			}			
			
		}
		catch(Exception e){
			System.err.println("In getAllMarkers() catch " + e);
			return null;
		}
		finally {
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			if(conn != null) conn.close();
		} 
		return markersList;
	}
	


	public int createMarker(Marker marker) throws Exception {

		int newId = 0;
		String sql = "INSERT into tbl_markers ( latitude, longitude, title, main_body ) " +
												  "VALUES (?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			conn = DbConnect.getConnection(DBType.MYSQL);
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			stmt.setString(1, marker.getLatitude());
			stmt.setString(2, marker.getLongitude());
			stmt.setString(3, marker.getTitle());
			stmt.setString(4, marker.getMainText());
		
			int wasSuccess = stmt.executeUpdate();
			
			if (wasSuccess == 1) {
				rs = stmt.getGeneratedKeys();
				rs.next();
				newId = rs.getInt(1);
				return newId;
			} 
			else {
				System.out.println("No rows affected");
				return newId;
			}
		}
		catch (SQLException e) {
			System.out.println("Create catch " + e);
			return newId;
		}
		finally {
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			if(conn != null) conn.close();
		}	
	}


	

	/**
	 *  @returns boolean to rest api of success status of delete
	 */
	public boolean deleteBooking(int markerId) throws Exception {
		
		String sql = "DELETE FROM tbl_markers WHERE marker_id = ?";
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			conn = DbConnect.getConnection(DBType.MYSQL);
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, markerId);
			int affectedRow = stmt.executeUpdate();
			
			if (affectedRow == 1) {
				return true;
			} 
			else {
				return false;
			}
		}
		catch(SQLException e) {
			System.err.println("Delete marker catch " + e);
			return false;
		}	
		finally {
			if(stmt != null) stmt.close();
			if(conn != null) conn.close();
		}
	}




}
