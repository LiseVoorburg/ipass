package com.tutorial.eclipse.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.tutorial.eclipse.entity.Afspraak;

public class AfspraakMaken {

   public static void main( String[ ] args ) {
   
      EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Eclipselink_JPA" );
      
      EntityManager entitymanager = emfactory.createEntityManager( );
      entitymanager.getTransaction( ).begin( );

      Afspraak afspraak = new Afspraak( ); 
      afspraak.setID( 7 );
      afspraak.setGebruikersID( 5 );
      afspraak.setDatum( "24/06/2017" );
      afspraak.setTijd( "8:00" );
      afspraak.setSoort( "voetreflex 30 min" );
      entitymanager.persist( afspraak );
      entitymanager.getTransaction( ).commit( );

      entitymanager.close( );
      emfactory.close( );
   }}