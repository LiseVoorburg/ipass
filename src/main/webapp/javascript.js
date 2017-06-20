function deleteAfspraak() {
	console.log("doe het!!");
	var uri = "/notafirstapp/restservices/gebruikers/delete/"
			+ $("#afspraakid").val();

	$.ajax(uri, {
		type : "delete",
		success : function(response) {
			$("#response").text("Afspraak deleted!");
		},
		error : function(response) {
			$("#response").text("Could not delete Afspraak!");
		}
	});
};

function deleteGebruiker() {
	console.log("doe het!!");
	var uri = "/notafirstapp/restservices/gebruikers/deleteg/" + $("#gebruikerid").val();

	$.ajax(uri, {
		type : "delete",
		success : function(response) {
			$("#response").text("Gebruiker deleted!");
		},
		error : function(response) {
			$("#response").text("Could not delete customer!");
		}
	});
};

function updateAfspraak() {
	var uri = "/notafirstapp/restservices/gebruikers/update/" + $("#id").val();
	var tijd = $('#tijd').val();
	var soort = $('#soort').val();
	var gebruikersid = $('#gebruikersid').val();
	var datum = $('#datum').val();
	$.ajax(uri, {
		type : "put",
		data : {
			"tijd" : tijd,
			"soort" : soort,
			"datum" : datum
		},
		success : function(response) {
			$("#response").text("Customer saved!");
		},
		error : function(response) {
			$("#response").text("Could not update customer!");
		}
	});
};

function newAfspraak() {
	var uri = "/notafirstapp/restservices/gebruikers/createAfpsraak/";
	var tijd = $('#tijd').val();
	var soort = $('#soort').val();
	var gebruikersid = sessionStorage.getItem("id");
	var datum = $('#datum').val();
	var id = $('#id').val();
	console.log(tijd, soort, id, datum);
	$.ajax(uri, {
		type : "post",
		data : {
			"tijd" : tijd,
			"soort" : soort,
			"id" : id,
			"datum" : datum
		},
		success : function(response) {
			$("#response").text("Customer saved!");
		},
		error : function(response) {
			$("#response").text("Could not update customer!");
		}

	});
	alert("afspraak aangemaakt");
};
function newAfspraakSan() {
	var uri = "/notafirstapp/restservices/gebruikers/createAfpsraak/";
	var tijd = $('#tijd').val();
	var soort = $('#soort').val();
	var gebruikersid = $('#gebruikersid').val();
	var datum = $('#datum').val();
	
	console.log(tijd, soort, id, datum);
	$.ajax(uri, {
		type : "post",
		data : {
			"tijd" : tijd,
			"soort" : soort,
			"gebruikersid" : gebruikersid,
			"datum" : datum
		},
		success : function(response) {
			$("#response").text("Customer saved!");
		},
		error : function(response) {
			$("#response").text("Could not update customer!");
		}

	});
	alert("afspraak aangemaakt");
};
function showall() {

	ids = sessionStorage.getItem("id");
    
	$.getJSON("/notafirstapp/restservices/gebruikers/" + ids, function(data) {

		$.each(data, function(i, afspraak) {

			console.log(i);

			$("#alles").append(
					"<tr><td>" + afspraak.datum + "</td><td>" + afspraak.tijd
							+ "</td><td>" + afspraak.gebruikersid + "</td><td>"
							+ afspraak.id + "</td><td>" + afspraak.soort
							+ "</td></tr>");

		});
	});
}
showall();
function showechtall() {

	$.getJSON("/notafirstapp/restservices/gebruikers/afspraak/",
			function(data) {

				$.each(data, function(i, afspraak) {

					console.log(i);

					$("#echtalles").append(
							"<tr><td>" + afspraak.datum + "</td><td>"
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

		$.each(data, function(i, g) {

			console.log(i);
			
			$("#allesgb").append(
					
					   
					"<tr><td>" + g.voornaam + "</td><td>" + g.achternaam
							+ "</td><td>" + g.id + "</td></tr>");

		});
	});
}
showallgb();

function inlog() {
	id = $("#inid").val();
	ww= $("#wachtwoord").val();
	
	$.getJSON("/notafirstapp/restservices/gebruikers/gb/" + id  );
	
	sessionStorage.setItem("id" , id);
	window.alert(sessionStorage.getItem("id"));
	if ($("#inid").val() == 1){window.location ="http://localhost:8089/notafirstapp/startscherms.html"};
	if ($("#inid").val() != 1){window.location ="http://localhost:8089/notafirstapp/starscherm.html"};
	if ($("#inid").val() == "beheerder"){window.location ="http://localhost:8089/notafirstapp/beheerderscherm.html"};
};


