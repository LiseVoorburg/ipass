package com.ipass.persistence;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.tutorial.eclipse.entity.Afspraak;
import com.tutorial.eclipse.entity.Gebruiker;




public class AfspraakDAO extends BaseDAO{
	
	
	private List<Afspraak> selectAfspraak(String query) {
		//maakt een lijst met strings aan de hand van de query die wordt toegevoeg door een andere funcite
		List<Afspraak> results = new ArrayList<Afspraak>();
	//maakt een nieuwe list aan voor een afspraak
		try (Connection con = super.getConnection()) {//de connectie vanuit de basedao
			Statement stmt = con.createStatement();//statment aanmaken om de query naar de database te sturen
			ResultSet dbResultSet = stmt.executeQuery(query);//het halen van de string uit de database
			
			while (dbResultSet.next()) {//voor iedere uitkomst van de database op de query
				int ID = dbResultSet.getInt("id");//het halen van de objecten uit de string
				String soort = dbResultSet.getString("soort");
				String tijd = dbResultSet.getString("tijd");
				String datum = dbResultSet.getString("datum");
				int gebruikersID = dbResultSet.getInt("gebruikersId");
				
				results.add(new Afspraak(ID, soort, tijd, datum, gebruikersID));//voegt alles toe aan de afspraak
			}
		} catch (SQLException sqle) {//het vangen van de sqleception
			sqle.printStackTrace();
		}
		
		return results;
	}
	public List<Afspraak> findAllAfspraak() {//haalt alle afspraken op uit de database
		return selectAfspraak("SELECT ID, soort, tijd, datum, gebruikersID FROM Afspraak");//geeft de query naar selectafspraak
	}
	public boolean delete(Afspraak afspraak) {
		//verwijderd een afspraak door middel van het id van de afspraak
		boolean result = false;
		boolean afspraakExists = findById(afspraak.getID()) != null;//kijkt of de afspraak bestaat
		
		if (afspraakExists) {
			String query = "DELETE FROM Afspraak WHERE id = '" +afspraak.getID() + "'"; //de query voor de database
					
			try (Connection con = super.getConnection()) {//de connectie vanuit de basedao
				
				Statement stmt = con.createStatement();//statment aanmaken om de query naar de database te sturen
				if (stmt.executeUpdate(query) == 1) { // 1 row updated! extra check zodat er echt maar 1 rij wordt verwijderd
					result = true;
				}
			} catch (SQLException sqle) {//het vangen van de sqleception
				sqle.printStackTrace();
			}
		}
		
		return result;
	}
	
	public boolean deleteByGebruiker(int id) {
		//het verwijderen van alle afspraken van een begruiker
		// is een gevolg vanuit de gebruikerdao, fuctie: deletegebruier()
		boolean result = false;
		boolean afExists = findById(id) != null;//de afspraak mag niet null zijn(moet dus bestaan)
		
		if (afExists) {
			String query = "DELETE FROM Afspraak WHERE gebruikersid = " +id; //de query voor de database
					
			try (Connection con = super.getConnection()) {//de connectie vanuit de basedao
				
				Statement stmt = con.createStatement();//statment aanmaken om de query naar de database te sturen
				if (stmt.executeUpdate(query) == 1) { // 1 row updated!
					result = true;
				}
			} catch (SQLException sqle) {//het vangen van de sqleception
				sqle.printStackTrace();		
							}	
						}
		return result;}

	public Afspraak findById(int ID) {
		// een afspraak vinden op id van de afspraak zelf
			  String query = "SELECT ID, soort, tijd, datum, gebruikersID FROM Afspraak WHERE id='"+ID+"'";
			  //de query voor de database
			  Afspraak afspraak = new Afspraak();
			  try (Connection con = super.getConnection()) {//de connectie vanuit de basedao
			    Statement stmt = con.createStatement();//statment aanmaken om de query naar de database te sturen
			    ResultSet dbResultSet = stmt.executeQuery(query);
			    
			    while (dbResultSet.next()) {//voor iedere uitkomst van de database op de query
			     String datum = dbResultSet.getString("datum");//het halen van de objecten uit de string
			     String soort = dbResultSet.getString("soort");
			     String tijd = dbResultSet.getString("tijd");
			     int gebruikersID = dbResultSet.getInt("gebruikersID");
			     afspraak.setDatum(datum );//het setten van de objecten
			     afspraak.setID(ID);
			     afspraak.setTijd(tijd);
			     afspraak.setSoort(soort);
			     afspraak.setGebruikersID(gebruikersID);
			    }
			    } catch (SQLException sqle) {//het vangen van de sqleception
			     sqle.printStackTrace();
			    }
			   return afspraak;
			 }
	
	
	
	 
	public Afspraak updateAfspraak(Afspraak afspraak) {
			//	het aanpassen van een afspraak via het id
		boolean afspraakExists = findById(afspraak.getID()) != null;
				
				if (afspraakExists) {
					String query = "Update Afspraak SET soort= '" + afspraak.getSoort() + "', tijd= '" + afspraak.getTijd() + "' , datum= '" +afspraak.getDatum() + "' WHERE id = " + afspraak.getID() ; 
					//de query voor de database
					System.out.println(query);
					try (Connection con = super.getConnection()) {//de connectie vanuit de basedao
						
						Statement stmt = con.createStatement();//statment aanmaken om de query naar de database te sturen
						if (stmt.executeUpdate(query) == 1) { // 1 row updated!
							
						}
					} catch (SQLException sqle) {//het vangen van de sqleception
						sqle.printStackTrace();		
									}	
								}
				return afspraak;
				}
	
	 
	 
	 public Afspraak createAfspraak(Afspraak afspraak) throws SQLException {//het vangen van de sqleception
		 //het maken van een afspraak via het id
		 System.out.println("doe het plzz");	
		    boolean result = false;
			
			
			
				String query = "INSERT INTO Afspraak( ID, soort, tijd, datum, gebruikersID) VALUES(nextval('af_id_aq')"+ ",'" + afspraak.getSoort() + "','" + afspraak.getTijd() + "','" + afspraak.getDatum() + "'," + afspraak.getGebruikersID() + ")";
				//de query voor de database 
					System.out.println(query);	
						try (Connection con = super.getConnection()) {//de connectie vanuit de basedao
						
						Statement stmt = con.createStatement();//statment aanmaken om de query naar de database te sturen
						 stmt.executeUpdate(query);//het uitvoer van de update in de database
						}
								
				return afspraak;
	 }
	
	 
	public List<Afspraak> getByGebruiker(Gebruiker gebruiker) {
		//het ophalen van alle afspraken van 1 gebruiker door middel van het gebruikersid
		List<Afspraak> results = new ArrayList<Afspraak>();
		
		try (Connection con = super.getConnection()) {//de connectie vanuit de basedao
			Statement stmt = con.createStatement();//statment aanmaken om de query naar de database te sturen
			String query = "SELECT * FROM afspraak WHERE gebruikersid = " +gebruiker.getID();//de query voor de database 
			ResultSet dbResultSet = stmt.executeQuery(query);
			
			while (dbResultSet.next()) {//voor iedere uitkomst van de database op de query
				int ID = dbResultSet.getInt("id");//het halen van de objecten uit de string
				String soort = dbResultSet.getString("soort");
				String tijd = dbResultSet.getString("tijd");
				String datum = dbResultSet.getString("datum");
				int gebruikersID = dbResultSet.getInt("gebruikersid");
				
				results.add(new Afspraak(ID, soort, tijd, datum, gebruikersID));//voegt alles toe aan de afspraak
			}
		} catch (SQLException sqle) {//het vangen van de sqleception
			sqle.printStackTrace();
		}
		
		return results;
	}
}

