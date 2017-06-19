package com.tutorial.eclipse.service;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import com.tutorial.eclipse.entity.Afspraak;

public class FindAllAfspraak {
   @SuppressWarnings("unchecked")
public static void main( String[ ] args ) {
   
      EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Eclipselink_JPA" );
      EntityManager entitymanager = emfactory.createEntityManager();
      Query q = entitymanager.createQuery("from Afspraak e", Afspraak.class);
      

       List<Afspraak> list = q.getResultList( );
      
      for( Afspraak e:list ){
         System.out.print("Gebruiker ID :" + e.getID( ));
         System.out.println("\t  G_ID :" + e.getGebruikersID( ));
         System.out.println("\t  Datum :" + e.getDatum( ));
         System.out.println("\t  Tijd :" + e.getTijd( ));
         System.out.println("\t  Soort :" + e.getSoort( ));
      }}}