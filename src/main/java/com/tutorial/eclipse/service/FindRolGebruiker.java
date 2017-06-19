package com.tutorial.eclipse.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import com.tutorial.eclipse.entity.Gebruiker;

public class FindRolGebruiker {
   
	public static void main( String[ ] args ) {
   
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Eclipselink_JPA" );
		EntityManager entitymanager = emfactory.createEntityManager();
	    Gebruiker gebruiker= entitymanager.find( Gebruiker.class, 1 );
		//Query q = entitymanager.createQuery("from Gebruiker e", Gebruiker.class);
	      

	  
	         System.out.print("\t Gebruiker rol :" + gebruiker.getRol( ));
	       
	      }}

