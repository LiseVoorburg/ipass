package com.tutorial.eclipse.entity;

import java.util.ArrayList;
import java.util.List;


public class Gebruiker{
	
	    private int ID;
		private String voornaam;
	    private String achternaam;
	    private String rol;
	    private String wachtwoord;
	    private List<Afspraak> afspraken = new ArrayList<>();
	    
	    public Gebruiker(int ID, String voornaam, String achternaam, String rol, String wachtwoord){
	        super ();
	        this.ID=ID;
	        this.voornaam=voornaam;
	        this.achternaam=achternaam;
	        this.rol=rol;
	        this.wachtwoord=wachtwoord;
	       }
	       
	       
	       public Gebruiker( ) {
	           super();
	        }


		public int getID() {
			return ID;
		}


		public void setID(int iD) {
			ID = iD;
		}


		public String getVoornaam() {
			return voornaam;
		}


		public void setVoornaam(String voornaam) {
			this.voornaam = voornaam;
		}


		public String getAchternaam() {
			return achternaam;
		}


		public void setAchternaam(String achternaam) {
			this.achternaam = achternaam;
		}


		public String getRol() {
			return rol;
		}


		public void setRol(String rol) {
			this.rol = rol;
		}


		public String getWachtwoord() {
			return wachtwoord;
		}


		public void setWachtwoord(String wachtwoord) {
			this.wachtwoord = wachtwoord;
		}
		 public List<Afspraak> getAfspraken() {
			return afspraken;
		}


		public void setAfspraken(List<Afspraak> afspraken) {
			this.afspraken = afspraken;
		}


		@Override
		   public String toString() {
		      return "Afspraak [ID=" + ID + ", VOORNAAM=" + voornaam + ", ACHTERNAAM=" + achternaam  + ", ROL=" + rol  + "WACHTWOORD=" + wachtwoord  +"]";
		   }
}
