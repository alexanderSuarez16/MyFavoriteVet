
var contador = 1;
var usuarios = new Array();

for (var i = 0; i < 7; i++) {
    var objG = {
        id: 1 + i,
        Nombres: "juan",
        apellidos: "perez",
        telefono: "23424324",
        email: "juan@gmail.com",
        direccion: "bogota",
        fechanacimiento: "10/12/1999",
        ciudad: "bogota",
        documento: "cedula",
        numerodocumento: "231233123"

    };
    usuarios.push(objG);
    
}



//usuarios[usuarios.length] = objG;

$(document).ready(function () {
    $("#btn-nuevo-usuario").on("click", function () {

        //var idUsuario = document.getElementById("txt-id-u").value;
        //var idUsuario = $("#form-nu #txt-id-u").val();
        if (validarNuevoUsuario()) {
            addUsuario();
            actualizarTabla();
            contador++;
            $('#modalNuevoUsuario').modal('hide');
            document.getElementById("form-nu").reset();
        }
    });
    actualizarTabla();
});
function leerDatosNuevoUsuario() {
    var obj = {
        id: $("#form-nu #txt-iddueño-u").val().trim(),
        nombres: $("#txt-nombremascota-u").val().trim(),
        apellidos: $("#txt-sexo-u").val().trim(),
        telefono: $("#txt-raza-u").val().trim(),
        email: $("#txt-fechanacimiento-u").val().trim(),

    direccion: $("#txt-unidadpeso-u").val().trim(),
    fechanacimiento: $("#txt-peso-u").val().trim(),
         ciudad: $("#txt-peso-u").val().trim(),
        documento: $("#txt-peso-u").val().trim(),
         numerodocumento: $("#txt-peso-u").val().trim(),
         

    };
    return obj;
}
function addUsuario() {
    var obj = leerDatosNuevoUsuario();
    usuarios.push(obj);
}

function actualizarTabla() {
    $("#tbl-usuarios>tbody").html("");
    for (var i = 0; i < usuarios.length; i++) {
        var obj = usuarios[i];
        var html = "" +
                "<tr>" +
                "<td>" + obj.id + "</td>" +
                "<td>" + obj.nombres + "</td>" +
                 "<td>" + obj.apellidos + "</td>" +
                 "<td>" + obj.telefono + "</td>" +
                 "<td>" + obj.email + "</td>" +
                  "<td>" + obj.direccion+ "</td>" +
                   "<td>" + obj.fechanacimiento + "</td>" +
                     "<td>" + obj.fechanacimiento+ "</td>" +
                       "<td>" + obj.ciudad+ "</td>" +
                         "<td>" + obj.documento+ "</td>" +
                           "<td>" + obj.numerodocumento+ "</td>" +
                  

                "<td>" +
                "<a id=\"btn-ver-" + i + "\" class=\"btn btn-success\"  data-toggle=\"modal\" data-target=\"#modalDetalle\"><i class=\"fa fa-eye\"></i></a>" +
               
                "<a id=\"btn-eliminar-" + i + "\" class=\"btn btn-danger\"><i class=\"fa fa-trash\"></i></a>" +
                "</td>" +
                "</tr>";
        $("#tbl-usuarios>tbody").append(html);
        addEvtEliminar(i);
        addEvtVer(i);
    }
}

function addEvtEliminar(i) {
    $("#btn-eliminar-" + i).click(function () {
        var usuario = usuarios[i];
        //var ii = $(this).attr("id").replace("btn-eliminar-", "");
        var rta = confirm("¿Está seguro de eliminar a " + usuario.nombres + " " + usuario.apellidos + "?");
        if (rta) {
            usuarios.splice(i, 1);
            actualizarTabla();
        }
        //alert(ii);
    });
}

function addEvtVer(i) {
    $("#btn-ver-" + i).click(function () {
        var usuario = usuarios[i];
        var htmlModal = $("#modalDetalle .modal-body").html();
        //alert(htmlModal);
        htmlModal = htmlModal.replace("#id#", usuario.id);
        htmlModal = htmlModal.replace("#nombres#", usuario.nombres);
        htmlModal = htmlModal.replace("#apellidos#", usuario.apellidos);
        htmlModal = htmlModal.replace("#telefono#", usuario.telefono);
        htmlModal = htmlModal.replace("#fechanacimiento#", usuario.fechanacimiento);
    
        htmlModal = htmlModal.replace("#ciudad#", usuario.ciudad);
         htmlModal = htmlModal.replace("#documento#", usuario.documento);
         htmlModal = htmlModal.replace("#numerodocumento#", usuario.numerodocumento);
         
        //alert(htmlModal);
        $("#modalDetalle .modal-body").html(htmlModal);

    });
}


function getMensajeValidacion(mensaje) {
    var html = '<div class="alert alert-danger">' + mensaje +
            '<button type="button" class="close" data-dismiss="alert" aria-label="Close">' +
            '<span aria-hidden="true">&times;</span>' +
            '</button>' +
            '</div>';
    return html;
}

function validarNuevoUsuario() {
    var vali = true;
    var nuevoUsuario = leerDatosNuevoUsuario();
    var ape = nuevoUsuario.apellidos.split(" ");
    removerAlerts();
    //alert(nuevoUsuario.id);
    if (nuevoUsuario.id === null || nuevoUsuario.id.length === 0
            || nuevoUsuario.id === "") {
        var mensajeHtml = getMensajeValidacion("El campo id no puede estar vacio");
        $("#txt-id-u").parent(".form-group").append(mensajeHtml);

        //alert("El campo id no puede estar vacio");
        vali = false;
    } if (isNaN(nuevoUsuario.id)) {
        var mensajeHtml = getMensajeValidacion("El campo id solo acepta números");
        $("#txt-iddueño-u").parent(".form-group").append(mensajeHtml);
        //alert("El campo id solo acepta números");
        vali = false;
    } if(nuevoUsuario.apellidos === null || nuevoUsuario.apellidos.length === 0
            || nuevoUsuario.apellidos === ""){
        var mensajeHtml = getMensajeValidacion("El campo no puede estar vacio.");
        $("#txt-nombremascota-u").parent(".form-group").append(mensajeHtml);
        vali = false;
    } if(ape.length > 2){
        var mensajeHtml = getMensajeValidacion("Solo se aceptan máximo 2 palabras.");
        $("#txt-unidadpeso-u").parent(".form-group").append(mensajeHtml);
        vali = false;
    }
    return vali;
}

function removerAlerts(){
    $("#form-nu .alert").remove();
}
