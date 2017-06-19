package com.tutorial.eclipse.service;

	import java.util.List;

import javax.persistence.EntityManager;
	import javax.persistence.EntityManagerFactory;
	import javax.persistence.Persistence;
import javax.persistence.Query;

import com.tutorial.eclipse.entity.Afspraak;
import com.tutorial.eclipse.entity.Gebruiker;

	public class DeleteGebruiker {
	   public static void main( String[ ] args ) {
	   
	      EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Eclipselink_JPA" );
	      EntityManager entitymanager = emfactory.createEntityManager( );
	      entitymanager.getTransaction( ).begin( );
	      Query query = entitymanager.createQuery( "Select e " + " from Afspraak e, Gebruiker a " + "WHERE a.ID = e.gebruikersID " + " AND a.ID=  5" );
	      @SuppressWarnings("unchecked")
		List<Afspraak> list = query.getResultList( );
	      
	      for( Afspraak e:list ){
	      
	      entitymanager.remove( e );
	      entitymanager.getTransaction( ).commit( );
	      
	   }
	      Query query1 = entitymanager.createQuery( "Select a " + " from Gebruiker a " + " WHERE a.ID=  5" );
	      @SuppressWarnings("unchecked")
		List<Gebruiker> list1 = query1.getResultList( );
	      
	      for( Gebruiker a:list1 ){
	      
	      entitymanager.remove( a );
	      entitymanager.getTransaction( ).commit( );
	      entitymanager.close( );
	      emfactory.close( );
	   
	   
	   }}}