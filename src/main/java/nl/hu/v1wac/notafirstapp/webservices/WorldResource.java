package nl.hu.v1wac.notafirstapp.webservices;

import javax.json.Json;
import javax.json.*;
import javax.ws.rs.DELETE;
import javax.ws.rs.*;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.Key;
import java.util.Calendar;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;


import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;


import java.io.InputStream;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;

import com.ipass.persistence.AfspraakDAO;
import com.ipass.persistence.GebruikerDAO;
import com.tutorial.eclipse.entity.Afspraak;
import com.tutorial.eclipse.entity.Gebruiker;



@Path("/gebruikers")
public class WorldResource {

	@GET
	@Produces("application/json")
	public String getGebruikers() {
		JsonArrayBuilder jab = Json.createArrayBuilder();
		GebruikerDAO gdao = new GebruikerDAO();
		List<Gebruiker> gebruikers = gdao.findAll();

		for (Gebruiker g : gebruikers) {
			JsonObjectBuilder job = Json.createObjectBuilder();

			job.add("id", g.getID());
			job.add("voornaam", g.getVoornaam());
			job.add("achternaam", g.getAchternaam());
			job.add("rol", g.getRol());
			job.add("wachtwoord", g.getWachtwoord());
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();

	}

	@Path("/gb/{id}")
	@GET
	@Produces("application/json")
	public String getGebruikerbyID(@PathParam("id") int id) {
		JsonArrayBuilder jab = Json.createArrayBuilder();
		GebruikerDAO gdao = new GebruikerDAO();
		Gebruiker g = gdao.get(id);

		JsonObjectBuilder job = Json.createObjectBuilder();

		job.add("id", g.getID());
		job.add("voornaam", g.getVoornaam());
		job.add("achternaam", g.getAchternaam());
		job.add("rol", g.getRol());
		job.add("wachtwoord", g.getWachtwoord());
		jab.add(job);
		

		JsonArray array = jab.build();
		return array.toString();

	}

	@Path("/afspraak")
	@GET
	@Produces("application/json")
	public String getAfspraaken() {
		JsonArrayBuilder jab = Json.createArrayBuilder();
		AfspraakDAO adao = new AfspraakDAO();
		List<Afspraak> afspraken = adao.findAllAfspraak();

		for (Afspraak a : afspraken) {
			JsonObjectBuilder job = Json.createObjectBuilder();

			job.add("id", a.getID());
			job.add("soort", a.getSoort());
			job.add("tijd", a.getTijd());
			job.add("datum", a.getDatum());
			job.add("gebruikersid", a.getGebruikersID());
			jab.add(job);
		}

		JsonArray array = jab.build();
		return array.toString();
	}

	@Path("/{id}")
	@GET
	@Produces("application/json")
	public String getAfspraakbyGebruiker(@PathParam("id") int id) {
		{
			JsonArrayBuilder jab = Json.createArrayBuilder();
			GebruikerDAO gdao = new GebruikerDAO();
			Gebruiker gebruiker = gdao.get(id);
			AfspraakDAO adao = new AfspraakDAO();
			List<Afspraak> afspraken = adao.getByGebruiker(gebruiker);

			for (Afspraak a : afspraken) {

				JsonObjectBuilder job = Json.createObjectBuilder();

				job.add("id", a.getID());
				job.add("soort", a.getSoort());
				job.add("tijd", a.getTijd());
				job.add("datum", a.getDatum());
				job.add("gebruikersid", a.getGebruikersID());
				jab.add(job);
			}

			JsonArray array = jab.build();
			return array.toString();
		}
	}

	@DELETE
	@Path("/delete/{afspraakid}")

	public Response deleteAfspraak(@PathParam("afspraakid") int afspraakid) {

		AfspraakDAO aDAO = new AfspraakDAO();
		Afspraak afspraak = aDAO.findById(afspraakid);

		for (Afspraak a : aDAO.findAllAfspraak())
			if (a.getID() == afspraakid) {
				afspraak = a;
			}

		if (afspraak == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			aDAO.delete(afspraak);
			return Response.ok().build();
		}

	}

	@DELETE
	@Path("/deleteg/{gID}")

	public Response deleteGebruiker(@PathParam("gID") int gebruikersid) {

		GebruikerDAO gDAO = new GebruikerDAO();
		Gebruiker gebruiker = gDAO.get(gebruikersid);

		for (Gebruiker g : gDAO.findAll())
			if (g.getID() == gebruikersid) {
				gebruiker = g;
			}

		if (gebruiker == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			gDAO.deleteGebruiker(gebruiker);
			return Response.ok().build();

		}
	}

	@PUT
	@Path("update/{id}")
	@Produces("application/json")
	public String updateAfspraakbyID(@PathParam("id") int id, @FormParam("soort") String soort,
			@FormParam("tijd") String tijd, @FormParam("datum") String datum) throws SQLException {
		JsonObjectBuilder job = Json.createObjectBuilder();
		AfspraakDAO aDAO = new AfspraakDAO();
		Afspraak a = new Afspraak();
		a.setID(id);
		a.setDatum(datum);
		a.setSoort(soort);
		a.setTijd(tijd);
		aDAO.updateAfspraak(a);

		job.add("id", id);
		job.add("soort", soort);
		job.add("tijd", tijd);
		job.add("datum", datum);

		return job.build().toString();
	}

	@POST
	@Path("/createAfpsraak")
	@Produces("application/json")
	public String createAfspraak(@FormParam("soort") String soort, @FormParam("tijd") String tijd,
			@FormParam("datum") String datum, @FormParam("gebruikersid") int gebruikersid) throws SQLException {

		JsonObjectBuilder job = Json.createObjectBuilder();
		AfspraakDAO aDAO = new AfspraakDAO();
		Afspraak aa = new Afspraak();

		aa.setDatum(datum);
		aa.setGebruikersID(gebruikersid);
		aa.setSoort(soort);
		aa.setTijd(tijd);

		job.add("soort", soort);
		job.add("tijd", tijd);
		job.add("datum", datum);
		job.add("gebruikersid", gebruikersid);
		aDAO.createAfspraak(aa);
		return job.build().toString();
	}
	


}