package com.ipass.persistence;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.tutorial.eclipse.entity.Afspraak;
import com.tutorial.eclipse.entity.Gebruiker;




public class AfspraakDAO extends BaseDAO{
	
	
	private List<Afspraak> selectAfspraak(String query) {
		List<Afspraak> results = new ArrayList<Afspraak>();
	
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			
			while (dbResultSet.next()) {
				int ID = dbResultSet.getInt("id");
				String soort = dbResultSet.getString("soort");
				String tijd = dbResultSet.getString("tijd");
				String datum = dbResultSet.getString("datum");
				int gebruikersID = dbResultSet.getInt("gebruikersId");
				
				results.add(new Afspraak(ID, soort, tijd, datum, gebruikersID));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return results;
	}
	public boolean delete(Afspraak afspraak) {
		boolean result = false;
		boolean addressExists = findById(afspraak.getID()) != null;
		
		if (addressExists) {
			String query = "DELETE FROM Afspraak WHERE id = '" +afspraak.getID() + "'"; 
					
			try (Connection con = super.getConnection()) {
				
				Statement stmt = con.createStatement();
				if (stmt.executeUpdate(query) == 1) { // 1 row updated!
					result = true;
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
		
		return result;
	}
	
	public boolean deleteByGebruiker(int id) {
		boolean result = false;
		boolean addressExists = findById(id) != null;
		
		if (addressExists) {
			String query = "DELETE FROM Afspraak WHERE gebruikersid = " +id; 
					
			try (Connection con = super.getConnection()) {
				
				Statement stmt = con.createStatement();
				if (stmt.executeUpdate(query) == 1) { // 1 row updated!
					result = true;
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();		
							}	
						}
		return result;}

	public Afspraak findById(int ID) {
		
			  String query = "SELECT ID, soort, tijd, datum, gebruikersID FROM Afspraak WHERE id='"+ID+"'";
			  Afspraak afspraak = new Afspraak();
			  try (Connection con = super.getConnection()) {
			    Statement stmt = con.createStatement();
			    ResultSet dbResultSet = stmt.executeQuery(query);
			    
			    while (dbResultSet.next()) {
			     String datum = dbResultSet.getString("datum ");
			     String soort = dbResultSet.getString("soort");
			     String tijd = dbResultSet.getString("tijd");
			     int gebruikersID = dbResultSet.getInt("gebruikersID");
			     afspraak.setDatum(datum );
			     afspraak.setID(ID);
			     afspraak.setTijd(tijd);
			     afspraak.setSoort(soort);
			     afspraak.setGebruikersID(gebruikersID);
			    }
			    } catch (SQLException sqle) {
			     sqle.printStackTrace();
			    }
			   return afspraak;
			 }
	
	
	public List<Afspraak> findAllAfspraak() {
		return selectAfspraak("SELECT ID, soort, tijd, datum, gebruikersID FROM Afspraak");
	}
	 
	public Afspraak updateAfspraak(Afspraak afspraak) throws SQLException {
				boolean afspraakExists = findById(afspraak.getID()) != null;
				
				if (afspraakExists) {
					String query = "Update Afspraak SET soort= '" + afspraak.getSoort() + "', tijd= '" + afspraak.getTijd() + "' , datum= '" +afspraak.getDatum() + "' WHERE id = " + afspraak.getID() ; 
					System.out.println(query);
					try (Connection con = super.getConnection()) {
						
						Statement stmt = con.createStatement();
						if (stmt.executeUpdate(query) == 1) { // 1 row updated!
							
						}
					} catch (SQLException sqle) {
						sqle.printStackTrace();		
									}	
								}
				return afspraak;
				}
	
	 
	 
	 public Afspraak createAfspraak(Afspraak afspraak) throws SQLException {
		 System.out.println("doe het plzz");	
		    boolean result = false;
			
			
			
				String query = "INSERT INTO Afspraak( ID, soort, tijd, datum, gebruikersID) VALUES(nextval('af_id_aq')"+ ",'" + afspraak.getSoort() + "','" + afspraak.getTijd() + "','" + afspraak.getDatum() + "'," + afspraak.getGebruikersID() + ")"; 
					System.out.println(query);	
						try (Connection con = super.getConnection()) {
						
						Statement stmt = con.createStatement();
						 stmt.executeUpdate(query);
						}
								
				return afspraak;
	 }
	
	 
	public List<Afspraak> getByGebruiker(Gebruiker gebruiker) {
		List<Afspraak> results = new ArrayList<Afspraak>();
		
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			String query = "SELECT * FROM afspraak WHERE gebruikersid = " +gebruiker.getID();
			ResultSet dbResultSet = stmt.executeQuery(query);
			
			while (dbResultSet.next()) {
				int ID = dbResultSet.getInt("id");
				String soort = dbResultSet.getString("soort");
				String tijd = dbResultSet.getString("tijd");
				String datum = dbResultSet.getString("datum");
				int gebruikersID = dbResultSet.getInt("gebruikersid");
				
				results.add(new Afspraak(ID, soort, tijd, datum, gebruikersID));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return results;
	}
}

