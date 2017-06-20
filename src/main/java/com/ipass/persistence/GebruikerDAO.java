package com.ipass.persistence;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.tutorial.eclipse.entity.Gebruiker;
public class GebruikerDAO extends BaseDAO {
	private AfspraakDAO afspraakDAO = new AfspraakDAO();
	
	
	public Gebruiker get(int id) {
		String query = "SELECT * FROM gebruiker WHERE id = " +id;
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			
			while (dbResultSet.next()) {
				
				String voornaam = dbResultSet.getString("voornaam");
				String achternaam = dbResultSet.getString("achternaam");
				String rol = dbResultSet.getString("rol");
				String wachtwoord = dbResultSet.getString("wachtwoord");
				
				return new Gebruiker(id, voornaam, achternaam, rol , wachtwoord);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return null;
	}
	
	public List<Gebruiker> findAll() {
		List<Gebruiker> gebruikers = new ArrayList<>();
		String query = "SELECT * FROM gebruiker";
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			
			while (dbResultSet.next()) {
				if (dbResultSet.getInt("id")== 1){
					System.out.println(dbResultSet.getInt("id"));
					continue;
					
				}else{
				int id = dbResultSet.getInt("id");
				String voornaam = dbResultSet.getString("voornaam");
				String achternaam = dbResultSet.getString("achternaam");
				String rol = dbResultSet.getString("rol");
				String wachtwoord = dbResultSet.getString("wachtwoord");
				
				gebruikers.add( new Gebruiker(id, voornaam, achternaam, rol , wachtwoord));
			}}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return gebruikers;
	}
	
	
	public boolean deleteGebruiker(Gebruiker gebruiker) {
		boolean result = false;
		boolean customerExists = get(gebruiker.getID()) != null;
		
		if (customerExists) {
			String query = "DELETE FROM gebruiker WHERE id = " + gebruiker.getID() ; 
					
			try (Connection con = getConnection()) {
				
				Statement stmt = con.createStatement();
				if (stmt.executeUpdate(query) == 1) { // 1 row updated!
					result = afspraakDAO.deleteByGebruiker(gebruiker.getID());
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
		
		return result;
	}
//	 public String findRoleForIDAndPassword (String username, String wachtwoord) {
//	 String role = null;
//	 int id = Integer.parseInt(username);
//	 String query = "SELECT role FROM gebruiker WHERE id="+id+" AND wachtwoord='"+wachtwoord+"'";
//
//	 try (Connection con = super.getConnection()) {
//
//	 PreparedStatement pstmt = con.prepareStatement(query);
//	 pstmt.setInt (1, id);
//	 pstmt.setString(2, wachtwoord);
//
//	 ResultSet rs = pstmt.executeQuery();
//	 if (rs.next())
//	 role = rs.getString("rol");
//
//	 } catch (SQLException sqle) {
//	 sqle.printStackTrace();
//	 }
//
//	 return role;
//	 }
//	
}
