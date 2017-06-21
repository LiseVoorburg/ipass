package nl.hu.v1wac.notafirstapp.webservices;

import java.sql.SQLException;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ipass.persistence.AfspraakDAO;
import com.ipass.persistence.GebruikerDAO;
import com.tutorial.eclipse.entity.Afspraak;
import com.tutorial.eclipse.entity.Gebruiker;

@Path("/gebruikers")//geeft het path waarmee er een link gemaakt kan worden naar het javascript
public class GebruikerResource {

	@GET // geeft aan wat voor soort rest service er wordt gebruikt
	@Produces("application/json")//geeft aan wat de rest maakt
	public String getGebruikers() {
		JsonArrayBuilder jab = Json.createArrayBuilder();//bouwt een array doormiddel van json
		GebruikerDAO gdao = new GebruikerDAO();//roept de DAO aan
		List<Gebruiker> gebruikers = gdao.findAll();//maakt een list van gebruikers met de DAO funcite findall()

		for (Gebruiker g : gebruikers) {// kijkt of gebruiker gelijk is als alle gebruikers in de dao
			JsonObjectBuilder job = Json.createObjectBuilder();// maakt een object builder zodat je die in de array kan zetten

			job.add("id", g.getID());// hier wordt het object gevuld
			job.add("voornaam", g.getVoornaam());
			job.add("achternaam", g.getAchternaam());
			job.add("rol", g.getRol());
			job.add("wachtwoord", g.getWachtwoord());
			jab.add(job);// hier stop je het object in de array
		}
		JsonArray array = jab.build();// hier maak je de array af
		return array.toString();// hier zet je de array in een string

	}
	

	

	@Path("/gb/{id}")//geeft het path waarmee er een link gemaakt kan worden naar het javascript
	@GET// geeft aan wat voor soort rest service er wordt gebruikt
	@Produces("application/json")//geeft aan wat de rest maakt
	public String getGebruikerbyID(@PathParam("id") int id) {//pathparam zorgt dat er een id meegegevan kan worden in de url
		JsonArrayBuilder jab = Json.createArrayBuilder();
		GebruikerDAO gdao = new GebruikerDAO();//roept de DAO aan
		Gebruiker g = gdao.get(id);//haalt de gebruiker uit de DAO via het id met de get() functie

		JsonObjectBuilder job = Json.createObjectBuilder();// maakt een object builder zodat je die in de array kan zetten

		job.add("id", g.getID());// hier wordt het object gevuld
		job.add("voornaam", g.getVoornaam());
		job.add("achternaam", g.getAchternaam());
		job.add("rol", g.getRol());
		job.add("wachtwoord", g.getWachtwoord());
		jab.add(job);// hier stop je het object in de array
		

		JsonArray array = jab.build();// hier maak je de array af
		return array.toString();// hier zet je de array in een string

	}

	

	@DELETE// geeft aan wat voor soort rest service er wordt gebruikt
	@Path("/deleteg/{gID}")//geeft het path waarmee er een link gemaakt kan worden naar het javascript

	public Response deleteGebruiker(@PathParam("gID") int gebruikersid) {//pathparam zorgt dat er een id meegegevan kan worden in de url

		GebruikerDAO gDAO = new GebruikerDAO();//roept de DAO aan
		Gebruiker gebruiker = gDAO.get(gebruikersid);//haalt de gebruiker uit de DAO via het gebruikersid met de get() functie

		for (Gebruiker g : gDAO.findAll())//kijkt of het id in de lijst staat van alle gebruikers via de DAO met functie findall()
			if (g.getID() == gebruikersid) {
				gebruiker = g;
			}
		if (gebruiker == null) {//wanneer de gebruiker niet bestaat geeft hij een response af
			return Response.status(Response.Status.NOT_FOUND).build();//geeft een error response af naar het javascript
		} else {
			gDAO.deleteGebruiker(gebruiker);
			return Response.ok().build();// dit geeft een succes response af naar het javascript

		}
	}

	


}