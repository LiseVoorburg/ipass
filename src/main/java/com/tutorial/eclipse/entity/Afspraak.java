package com.tutorial.eclipse.entity;

public class Afspraak{
	// gebruiker pojo
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
    // dit zijn de setter en getter voor id
	public void setID(int iD) {
		ID = iD;
	}

	public String getSoort() {
		return soort;
	}
	 // dit zijn de setter en getter voor soort
	public void setSoort(String soort) {
		this.soort = soort;
	}

	public String getTijd() {
		return tijd;
	}
	 // dit zijn de setter en getter voor tijd
	public void setTijd(String tijd) {
		this.tijd = tijd;
	}

	public String getDatum() {
		return datum;
	}
	 // dit zijn de setter en getter voor datum
	public void setDatum(String datum) {
		this.datum = datum;
	}

	public int getGebruikersID() {
		return gebruikersID;
	}
	 // dit zijn de setter en getter voor gebruikersid
	public void setGebruikersID(int gebruikersID) {
		this.gebruikersID = gebruikersID;
	}

	  @Override
	   public String toString() {// dit is de string die elke keer gevuld wordt in de rest
	      return "Afspraak [ID=" + ID + ", SOORT=" + soort + ", TIJD=" + tijd  + ", DATUM=" + datum  + "GEBRUIKERSID=" + gebruikersID  +"]";
	   }


	
    }
    
    
     