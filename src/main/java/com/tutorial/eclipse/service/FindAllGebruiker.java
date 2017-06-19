package com.tutorial.eclipse.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import com.tutorial.eclipse.entity.Gebruiker;

public class FindAllGebruiker {
   @SuppressWarnings("unchecked")
public static void main( String[ ] args ) {
   
      EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Eclipselink_JPA" );
      EntityManager entitymanager = emfactory.createEntityManager();
      Query q = entitymanager.createQuery("from Gebruiker e", Gebruiker.class);
      

       List<Gebruiker> list = q.getResultList( );
      
      for( Gebruiker e:list ){
         System.out.print("Gebruiker ID :" + e.getID( ));
         System.out.println("\t  Voornaam :" + e.getVoornaam( ));
         System.out.println("\t  Achternaam :" + e.getAchternaam( ));
      }}}