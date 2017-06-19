package com.tutorial.eclipse.service;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import com.tutorial.eclipse.entity.Afspraak;


public class AllAfsGebruiker{
   @SuppressWarnings("unchecked")
public static void main( String[ ] args ) {
   
      EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Eclipselink_JPA" );
      EntityManager entitymanager = emfactory.createEntityManager();
     
      
      //Between
      Query query = entitymanager.createQuery( "Select e " + "from Afspraak e, Gebruiker  a " + "WHERE a.ID = e.gebruikersID" + " AND a.ID=  5" );


       List<Afspraak> list = query.getResultList( );
      
      for( Afspraak e:list ){
         System.out.print("Gebruiker ID :" + e.getID( ));
         System.out.println("\t  G_ID :" + e.getGebruikersID( ));
         System.out.println("\t  Datum :" + e.getDatum( ));
         System.out.println("\t  Tijd :" + e.getTijd( ));
         System.out.println("\t  Soort :" + e.getSoort( ));
      }}}