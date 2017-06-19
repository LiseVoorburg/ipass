package com.tutorial.eclipse.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.tutorial.eclipse.entity.Afspraak;

public class AfspraakAanpassen {
   public static void main( String[ ] args ) {
      EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Eclipselink_JPA" );
      
      EntityManager entitymanager = emfactory.createEntityManager( );
      entitymanager.getTransaction( ).begin( );
      Afspraak afspraak = entitymanager.find( Afspraak.class, 7 );
      
      //before update
      System.out.println( afspraak);
      afspraak.setTijd( "11:00" );
      afspraak.setDatum( "27/07/2017" );
      afspraak.setSoort( "voetreflex 60 min" );
      entitymanager.getTransaction( ).commit( );
      
      //after update
      System.out.println( afspraak );
      entitymanager.close();
      emfactory.close();
   }}