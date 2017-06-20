package com.tutorial.eclipse.entity;

public class Afspraak{

    private int ID;
	private String soort;
    private String tijd;
    private String datum;
    private int gebruikersID;

    
    public Afspraak(int ID, String soort, String tijd, String datum, int gebruikersID){
     super ();
     this.ID=ID;
     this.soort=soort;
     this.tijd=tijd;
     this.datum=datum;
    this.gebruikersID= gebruikersID;
    }
    
    
    public Afspraak( ) {
        super();
     }
    
    public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getSoort() {
		return soort;
	}

	public void setSoort(String soort) {
		this.soort = soort;
	}

	public String getTijd() {
		return tijd;
	}

	public void setTijd(String tijd) {
		this.tijd = tijd;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public int getGebruikersID() {
		return gebruikersID;
	}

	public void setGebruikersID(int gebruikersID) {
		this.gebruikersID = gebruikersID;
	}

	  @Override
	   public String toString() {
	      return "Afspraak [ID=" + ID + ", SOORT=" + soort + ", TIJD=" + tijd  + ", DATUM=" + datum  + "GEBRUIKERSID=" + gebruikersID  +"]";
	   }


	
    }
    
    
     