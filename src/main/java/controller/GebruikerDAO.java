package controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Gebruiker;

public class GebruikerDAO extends BaseDAO {// dit betekent dat de gebruiker de basedao verder uitbreid
	private AfspraakDAO afspraakDAO = new AfspraakDAO();//haalt de afspraakdao op

	public Gebruiker get(int id) {//get() haalt de gebruiker op uit de database door het id mee te geven
		String query = "SELECT * FROM gebruiker WHERE id = " + id;//de query voor de database

		try (Connection con = super.getConnection()) {//de connectie vanuit de basedao
			Statement stmt = con.createStatement();//statment aanmaken om de query naar de database te sturen
			ResultSet dbResultSet = stmt.executeQuery(query);//het halen van de string uit de database

			while (dbResultSet.next()) {//voor iedere uitkomst van de database op de query

				String voornaam = dbResultSet.getString("voornaam");//het halen van de objecten uit de string
				String achternaam = dbResultSet.getString("achternaam");
				String rol = dbResultSet.getString("rol");
				String wachtwoord = dbResultSet.getString("wachtwoord");

				return new Gebruiker(id, voornaam, achternaam, rol, wachtwoord);//het aanmaken van de gebruiker met de objecten
			}
		} catch (SQLException sqle) {//het vangen van de sqleception
			sqle.printStackTrace();
		}

		return null;
	}



	public List<Gebruiker> findAll() {// haalt alle gebruikers uit de database en stopt het in een list
		List<Gebruiker> gebruikers = new ArrayList<>();//maakt de list aan
		String query = "SELECT * FROM gebruiker";//de query voor de database
		try (Connection con = super.getConnection()) {//de connectie vanuit de basedao
			Statement stmt = con.createStatement();//statment aanmaken om de query naar de database te sturen
			ResultSet dbResultSet = stmt.executeQuery(query);//het halen van de string uit de database

			while (dbResultSet.next()) {//voor iedere uitkomst van de database op de query
				if (dbResultSet.getInt("id") == 1) {// als de eigenaar met id 1 wordt aangeroepen wordt er niet geprint
													//haar gegevens zijn namelijk niet zichtbaar voor iemand
					
					continue;

				}else if (dbResultSet.getInt("id") == 666) {// als de eigenaar met id 666 wordt aangeroepen wordt er niet geprint
					//haar gegevens zijn namelijk niet zichtbaar voor iemand
					
                     continue;

} else {
					int id = dbResultSet.getInt("id");//het halen van de objecten uit de string
					String voornaam = dbResultSet.getString("voornaam");
					String achternaam = dbResultSet.getString("achternaam");
					String rol = dbResultSet.getString("rol");
					String wachtwoord = dbResultSet.getString("wachtwoord");

					gebruikers.add(new Gebruiker(id, voornaam, achternaam, rol, wachtwoord));//het aanmaken van de gebruiker met de objecten
				
				}
			}
		} catch (SQLException sqle) {//het vangen van de sqleception
			sqle.printStackTrace();
		}

		return gebruikers;
	}

	public boolean deleteGebruiker(Gebruiker gebruiker) {//het deleten van een gebruiker door middel van het id
		boolean result = false;
		boolean customerExists = get(gebruiker.getID()) != null;//checken of de bebruiker wel echt bestaat en in de database zit

		if (customerExists) {
			String query = "DELETE FROM gebruiker WHERE id = " + gebruiker.getID();//de query voor de database

			try (Connection con = getConnection()) {//de connectie vanuit de basedao

				Statement stmt = con.createStatement();//statment aanmaken om de query naar de database te sturen
				if (stmt.executeUpdate(query) == 1) { // om zeker te zijn dat maar 1 id wordt verwijderd
					result = afspraakDAO.deleteByGebruiker(gebruiker.getID());
					//hier wordt de afspraakdao aangeroepen zodat alle afspraken van de gebruiker ook verwijderd worden
				}
			} catch (SQLException sqle) {//het vangen van de sqleception
				sqle.printStackTrace();
			}
		}

		return result;
	}
	//dit was vor de authentcation maar dit is mij niet gelukt om werkend te krijgen
	
	// public String findRoleForIDAndPassword (String username, String
	// wachtwoord) {
	// String role = null;
	// int id = Integer.parseInt(username);
	// String query = "SELECT role FROM gebruiker WHERE id="+id+" AND
	// wachtwoord='"+wachtwoord+"'";
	//
	// try (Connection con = super.getConnection()) {
	//
	// PreparedStatement pstmt = con.prepareStatement(query);
	// pstmt.setInt (1, id);
	// pstmt.setString(2, wachtwoord);
	//
	// ResultSet rs = pstmt.executeQuery();
	// if (rs.next())
	// role = rs.getString("rol");
	//
	// } catch (SQLException sqle) {
	// sqle.printStackTrace();
	// }
	//
	// return role;
	// }
	//
}
