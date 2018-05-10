// La URL raiz para los servicios RESTful
var rootURL = "rest/hotel";

var hotelActual;

// Recuperar la lista de hoteles cuando la aplicacion inicia 
findAll();

// Ocultar el botón de eliminar al iniciarse la aplicacion
$('#btnDelete').hide();

// Registrar los listeners
$('.btnBusqueda').click(function() {
	//$('#campoBusqueda').trigger('keyup');
	search($('#campoBusqueda').val());
	return false;
});

// Lanzar la operacion de busqueda al presionar 'Return' en el campo de busqueda
$('#campoBusqueda').keypress(function(e){
	if(e.which == 13) {
		search($('#campoBusqueda').val());
		e.preventDefault();
		return false;
    }
});

$('.new').click(function() {
	newHotel();
	return false;
});

$('#btnSave').click(function() {
	if ($('#hotelId').val() == '')
		addHotel();
	else
		updateHotel();
	return false;
});

$('#btnDelete').click(function() {
	deleteHotel();
	return false;
});

$('#listaHotel').delegate('a', 'click', function() {
	findById($(this).data('identity'));
});

$("img").error(function(){
  $(this).attr("src", "pics/generic.jpg");

});

function search(searchKey) {
	if (searchKey == '') 
		findAll();
	else
		findByName(searchKey);
}

function newHotel() {
	$('#btnDelete').hide();
	hotelActual = { imagen: 'generic.jpg'};
	renderDetails(hotelActual); // Mostrar formulario vacio
	$('#nombre').focus();
}

function findAll() {
	console.log('findAll');
	$.ajax({
		type: 'GET',
		url: rootURL,
		dataType: "json", // tipo de la respuesta
		success: renderList
	});
}

function findByName(searchKey) {
	console.log('findByName: ' + searchKey);
	$.ajax({
		type: 'GET',
		url: rootURL + '/search/' + searchKey,
		dataType: "json",
		success: renderList 
	});
}

function findById(id) {
	console.log('findById: ' + id);
	$.ajax({
		type: 'GET',
		url: rootURL + '/' + id,
		dataType: "json",
		success: function(data){
			$('#btnDelete').show();
			console.log('findById success: ' + data.name);
			hotelActual = data;
			renderDetails(hotelActual);
		}
	});
}

function addHotel() {
	console.log('addHotel');
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: rootURL,
		dataType: "json",
		data: formToJSON(),
		success: function(data, textStatus, jqXHR){
			findAll();
			alert('Hotel creado correctamente');
			$('#btnDelete').show();
			$('#hotelId').val(data.id);
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('Error en addHotel: ' + textStatus);
		}
	});
}

function updateHotel() {
	console.log('updateHotel');
	$.ajax({
		type: 'PUT',
		contentType: 'application/json',
		url: rootURL + '/' + $('#hotelId').val(),
		dataType: "json",
		data: formToJSON(),
		success: function(data, textStatus, jqXHR){			
			alert('Hotel actualizado correctamente');
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('Error en updateHotel: ' + textStatus);
		}
	});
}

function deleteHotel() {
	console.log('deleteHotel');
	$.ajax({
		type: 'DELETE',
		url: rootURL + '/' + $('#hotelId').val(),
		success: function(data, textStatus, jqXHR){
			findAll();
			renderDetails({});
			alert('Hotel eliminado correctamente');
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('Error en deleteHotel');
		}
	});
}

function renderList(data) {
	// JAX-RS serializa una lista vacia como null, y una coleccion de un único elemento en lugar de un array de uno)
	var list = 
		data == null ? 
				[] : 
					(data instanceof Array ? data : [data]);

	$('#listaHotel li').remove();
	$.each(list, function(index, hotel) {
		$('#listaHotel').append('<li><a href="#" data-identity="' + hotel.id + '">'+hotel.nombre+'</a></li>');
	});
}

function renderDetails(hotel) {
	$('#hotelId').val(hotel.id);
	$('#nombre').val(hotel.nombre);
	$('#precio').val(hotel.precio);
	$('#ubicacion').val(hotel.ubicacion);
	$('#pais').val(hotel.pais);
	$('#estrellas').val(hotel.estrellas);
	$('#descripcion').val(hotel.descripcion);
	$('#imagen').attr('src', 'pics/' + hotel.imagen);
}

// Funcion de ayuda para serializar todos los campos del formulario a strings de JSON
function formToJSON() {
	var hotelId = $('#hotelId').val();
	return JSON.stringify({
		"id": hotelId == "" ? null : hotelId, 
		"nombre": $('#nombre').val(), 
		"precio": $('#precio').val(),
		"ubicacion": $('#ubicacion').val(),
		"pais": $('#pais').val(),
		"estrellas": $('#estrellas').val(),
		"descripcion": $('#descripcion').val(),
		"imagen": hotelActual.imagen
		});
}
