function deleteAfspraak() {
	
	//het deleten van een afspraak door de gebruiker en door sandra
	var uri = "/notafirstapp/restservices/afspraak/delete/"
			+ $("#afspraakid").val();
//uri bevat de url van de rest service
	$.ajax(uri, {
		type : "delete", //geeft het soort rest aan 
	        success: function(response) {//geeft het succes door uit de rest
	            $("#response").text("Customer deleted!");
	        },
	        error: function(response) {// geeft de error door uit de rest
	            $("#response").text("Could not delete customer!");
	        }

		
	});
	alert("afspraak verwijderd");
};

function deleteGebruiker() {
	//het deleten van een gebruiker door de beheerder
	console.log("doe het!!");
	var uri = "/notafirstapp/restservices/gebruikers/deleteg/" + $("#gebruikerid").val();
	//uri bevat de url van de rest service
	$.ajax(uri, {
		type : "delete"//geeft het soort rest aan 
		
	});
	window.alert("gebruiker verwijderd");
};

function updateAfspraak() {
	var tijd = $('#tijd').val();
	var soort = $('#soort').val();
	var gebruikersid = $('#gebruikersid').val(); // het ophalen van de values uit de invul velden van de html pagina
	var datum = $('#datum').val();
	var id = $('#id').val();

 var uri = "/notafirstapp/restservices/afspraak/update/" + id;
//uri bevat de url van de rest service
		                		$.ajax(uri, {
		                			type : "put",//geeft het soort rest aan 
		                			data : {// dit is de data die wordt geset in de rest
		                				"tijd" : tijd,
		                				"soort" : soort,
		                				"datum" : datum
		                		            }
		                					});
		                		window.alert("afspraak aangepast");
	};

function newAfspraak() {
	var uri = "/notafirstapp/restservices/afspraak/createAfpsraak/";
	//uri bevat de url van de rest service
	var tijd = $('#tijd').val();// het ophalen van de values uit de invul velden van de html pagina
	var soort = $('#soort').val();
	var gebruikersid = sessionStorage.getItem("id");
	var datum = $('#datum').val();
	
	console.log(tijd, soort,  datum, gebruikersid);
	$.ajax(uri, {
		type : "post",//geeft het soort rest aan 
		data : {// dit is de data die wordt geset in de rest
			"tijd" : tijd,
			"soort" : soort,
			"gebruikersid" : gebruikersid,
			"datum" : datum
		}

	});
	alert("afspraak aangemaakt");
};
function newAfspraakSan() {
	var uri = "/notafirstapp/restservices/afspraak/createAfpsraak/";
	//uri bevat de url van de rest service
	var tijd = $('#tijd').val();
	var soort = $('#soort').val();// het ophalen van de values uit de invul velden van de html pagina
	var gebruikersid = $('#gebruikersid').val();
	var datum = $('#datum').val();
	
	console.log(tijd, soort, id, datum, gebruikersid);
	$.ajax(uri, {
		type : "post",//geeft het soort rest aan 
		data : {// dit is de data die wordt geset in de rest
			"tijd" : tijd,
			"soort" : soort,
			"gebruikersid" : gebruikersid,
			"datum" : datum
		}

	});
	alert("afspraak aangemaakt");
};
function showall() {

	id = sessionStorage.getItem("id");//hier wordt het id uit de sessionstorage gehaald 
									//zodat hij gebruikt kan worden bij het ophalen van de data
    
	$.getJSON("/notafirstapp/restservices/afspraak/" + id, function(data) {
		$.each(data, function(i, afspraak) {
			//de getjason haalt de url op van de rest  service
			console.log(i);

			$("#alles").append(// laat alles zien in de daarvoor gemaakte table bij de html paginas
					"<tr><td>" + afspraak.datum + "</td><td>" + afspraak.tijd// "</td><td>" zorgt ervoor dat alles netjes uitgelijnd is
							+ "</td><td>" + afspraak.gebruikersid + "</td><td>"
							+ afspraak.id + "</td><td>" + afspraak.soort
							+ "</td></tr>");

		});
	});
}
showall();
function showechtall() {

	$.getJSON("/notafirstapp/restservices/afspraak/",
			function(data) {
		//de getjason haalt de url op van de rest  service
				$.each(data, function(i, afspraak) {

					console.log(i);

					$("#echtalles").append(// laat alles zien in de daarvoor gemaakte table bij de html paginas
							"<tr><td>" + afspraak.datum + "</td><td>"// "</td><td>" zorgt ervoor dat alles netjes uitgelijnd is
									+ afspraak.tijd + "</td><td>"
									+ afspraak.gebruikersid + "</td><td>"
									+ afspraak.id + "</td><td>"
									+ afspraak.soort + "</td></tr>");

				});
			});
}
showechtall();
function showallgb() {

	$.getJSON("/notafirstapp/restservices/gebruikers", function(data) {
		//de getjason haalt de url op van de rest  service
		$.each(data, function(i, g) {

			console.log(i);
			
			$("#allesgb").append(// laat alles zien in de daarvoor gemaakte table bij de html paginas
					
					   
					"<tr><td>" + g.voornaam + "</td><td>" + g.achternaam// "</td><td>" zorgt ervoor dat alles netjes uitgelijnd is
							+ "</td><td>" + g.id + "</td></tr>");

		});
	});
}
showallgb();

function inlog() {
	 id = $("#inid").val();
	 ww= $("#wachtwoord").val();// het ophalen van de values uit de invul velden van de html pagina
	 
	 $.getJSON("/notafirstapp/restservices/gebruikers/gb/" + id , function(data) {
		//de getjason haalt de url op van de rest  service
			$.each(data, function(i, g) {
           if (ww == g.wachtwoord){
     sessionStorage.setItem("id" , id);// zet het id in de sessionstorage hiermee kan alleen de ingelogde zijn eigen afspraken zien.
	 window.alert(sessionStorage.getItem("id"));
	 //hier wordt er naar het id gekeken wie de gebruiker is van de site sommige hebben andere rechten dan andere.
	 //hieronder wordt weergegeven wie met welke rechten welke paginas mag zien.
	 if ($("#inid").val() == 1){window.location ="http://localhost:8089/notafirstapp/startscherms.html"};
	 if ($("#inid").val() != 1){window.location ="http://localhost:8089/notafirstapp/starscherm.html"};
	 if ($("#inid").val() == 666){window.location ="http://localhost:8089/notafirstapp/beheerderscherm.html"};
           }else {
        	  window.alert("Uw wachtwoord of id is onjuist," +
        	  		"probeer opnieuw!") 
        	   location.reload();}
        		 });
			
	 
	 });
			};
function show(){// laat het gebruikers id zien in de console met welke er is ingelogd
console.log(sessionStorage.getItem("id"));	// voor extra verduidelijking voor het testen van de aplicatie
};

