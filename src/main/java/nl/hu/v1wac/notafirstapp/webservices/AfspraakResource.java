package nl.hu.v1wac.notafirstapp.webservices;

import java.sql.SQLException;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.ipass.persistence.AfspraakDAO;
import com.ipass.persistence.GebruikerDAO;
import com.tutorial.eclipse.entity.Afspraak;
import com.tutorial.eclipse.entity.Gebruiker;
@Path("/afspraak")//geeft het path waarmee er een link gemaakt kan worden naar het javascript
public class AfspraakResource {

	@GET// geeft aan wat voor soort rest service er wordt gebruikt
	@Produces("application/json")//geeft aan wat de rest maakt
	public String getAfspraaken() {
		//hier worden alle afspraken opgehaald
		JsonArrayBuilder jab = Json.createArrayBuilder();//bouwt een array doormiddel van json
		AfspraakDAO adao = new AfspraakDAO();//roept de DAO aan
		List<Afspraak> afspraken = adao.findAllAfspraak();//maakt een list van afspraken met de DAO funcite findallafspraken()

		for (Afspraak a : afspraken) {
			JsonObjectBuilder job = Json.createObjectBuilder();// maakt een object builder zodat je die in de array kan zetten

			job.add("id", a.getID());// hier wordt het object gevuld
			job.add("soort", a.getSoort());
			job.add("tijd", a.getTijd());
			job.add("datum", a.getDatum());
			job.add("gebruikersid", a.getGebruikersID());
			jab.add(job);// hier stop je het object in de array
		}

		JsonArray array = jab.build();// hier maak je de array af
		return array.toString();// hier zet je de array in een string
	}

	@Path("/{id}")//geeft het path waarmee er een link gemaakt kan worden naar het javascript
	@GET// geeft aan wat voor soort rest service er wordt gebruikt
	@Produces("application/json")//geeft aan wat de rest maakt
	public String getAfspraakbyGebruiker(@PathParam("id") int id) {
		// hier worden de afspraken opgehaald van een gebruiker door middel van het gebruikersid
		//pathparam zorgt dat er een id meegegevan kan worden in de url
		{
			JsonArrayBuilder jab = Json.createArrayBuilder();//bouwt een array doormiddel van json
			GebruikerDAO gdao = new GebruikerDAO();//roept de DAO aan
			Gebruiker gebruiker = gdao.get(id);
			AfspraakDAO adao = new AfspraakDAO();//roept de DAO aan
			List<Afspraak> afspraken = adao.getByGebruiker(gebruiker);

			for (Afspraak a : afspraken) {

				JsonObjectBuilder job = Json.createObjectBuilder();
				// maakt een object builder zodat je die in de array kan zetten
				job.add("id", a.getID());// hier wordt het object gevuld
				job.add("soort", a.getSoort());
				job.add("tijd", a.getTijd());
				job.add("datum", a.getDatum());
				job.add("gebruikersid", a.getGebruikersID());
				jab.add(job);// hier stop je het object in de array
			}

			JsonArray array = jab.build();// hier maak je de array af
			return array.toString();// hier zet je de array in een string
		}
	}

	@DELETE// geeft aan wat voor soort rest service er wordt gebruikt
	@Path("/delete/{afspraakid}")//geeft het path waarmee er een link gemaakt kan worden naar het javascript

	public Response deleteAfspraak(@PathParam("afspraakid") int afspraakid) {
		//hier wordt een afspraak verwijderd door middel van het afspraakid
		//pathparam zorgt dat er een id meegegevan kan worden in de url
		
		AfspraakDAO aDAO = new AfspraakDAO();//roept de DAO aan
		Afspraak afspraak = aDAO.findById(afspraakid);//pakt de afspraak met het gegeven id doormiddel van de functie findbyid()

		for (Afspraak a : aDAO.findAllAfspraak())
			if (a.getID() == afspraakid) {
				afspraak = a;
			}

		if (afspraak == null) {//wanneer de afspraak niet bestaat geeft hij een response af
			return Response.status(Response.Status.NOT_FOUND).build();//geeft een error response af naar het javascript
		} else {
			aDAO.delete(afspraak);
			return Response.ok().build();//dit geeft een succes response af naar het javascript

		}

	}
	@PUT// geeft aan wat voor soort rest service er wordt gebruikt
	@Path("update/{id}")//geeft het path waarmee er een link gemaakt kan worden naar het javascript
	@Produces("application/json")//geeft aan wat de rest maakt
	public String updateAfspraakbyID(@PathParam("id") int id, @FormParam("soort") String soort,
			@FormParam("tijd") String tijd, @FormParam("datum") String datum) throws SQLException {
		//hier wordt een afspraak aangepast doormiddel van het id
		//pathparam zorgt dat er een id meegegevan kan worden in de url
		//formparam haalt de gegevens op uit het javascript zodat het geset kan worden
		JsonObjectBuilder job = Json.createObjectBuilder();
		// maakt een object builder 
		AfspraakDAO aDAO = new AfspraakDAO();//roept de DAO aan
		Afspraak a = new Afspraak();//hier wordt een nieuwe afspraak aangemaakt
		a.setID(id);//hier wordt geset
		a.setDatum(datum);
		a.setSoort(soort);
		a.setTijd(tijd);
		aDAO.updateAfspraak(a);//hier wordt de nieuwe afspraak in de dao gevoegd

		job.add("id", id);// hier wordt het object gevuld
		job.add("soort", soort);
		job.add("tijd", tijd);
		job.add("datum", datum);

		return job.build().toString();//hier wordt het opject in een string gezet
	}

	@POST// geeft aan wat voor soort rest service er wordt gebruikt
	@Path("/createAfpsraak")//geeft het path waarmee er een link gemaakt kan worden naar het javascript
	@Produces("application/json")//geeft aan wat de rest maakt
	public String createAfspraak(@FormParam("soort") String soort, @FormParam("tijd") String tijd,
			@FormParam("datum") String datum, @FormParam("gebruikersid") int gebruikersid) throws SQLException {
		//formparam haalt de gegevens op uit het javascript zodat het geset kan worden
		JsonObjectBuilder job = Json.createObjectBuilder();
		// maakt een object builder 
		AfspraakDAO aDAO = new AfspraakDAO();//roept de DAO aan
		Afspraak aa = new Afspraak();//hier wordt een nieuwe afspraak aangemaakt

		aa.setDatum(datum);//hier wordt geset
		aa.setGebruikersID(gebruikersid);
		aa.setSoort(soort);
		aa.setTijd(tijd);

		job.add("soort", soort);// hier wordt het object gevuld
		job.add("tijd", tijd);
		job.add("datum", datum);
		job.add("gebruikersid", gebruikersid);
		aDAO.createAfspraak(aa);//hier wordt de afspraak in de dao gevoegd
		return job.build().toString();//hier wordt het opject in een string gezet
	}
	
}
