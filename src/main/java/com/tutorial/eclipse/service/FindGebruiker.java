package com.tutorial.eclipse.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.tutorial.eclipse.entity.Gebruiker;

public class FindGebruiker {
   public static void main( String[ ] args ) {
   
      EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Eclipselink_JPA" );
      EntityManager entitymanager = emfactory.createEntityManager();
      Gebruiker gebruiker = entitymanager.find( Gebruiker.class, 1 );

      System.out.println("gebruiker id = " + gebruiker.getID( ));
      System.out.println("gebruiker voornaam = " + gebruiker.getVoornaam( ));
      System.out.println("gebruiker achternaam = " + gebruiker.getAchternaam( ));
      System.out.println("gebruiker rol = " + gebruiker.getRol( ));
      System.out.println("gebruiker wachtwoord = " + gebruiker.getWachtwoord( ));
   }}