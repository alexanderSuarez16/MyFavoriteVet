/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var contador = 1;
var usuarios = new Array();

for (var i = 0; i < 2; i++) {
    var objG = {
        iddueño: 12 + i,
        nombremascota: "pacha",
        sexo: "hembra",
        raza: "perro comun",
        fechanacimiento: "23/02/2007",
        unidadpeso: "klg",
        peso: 10

    };
    usuarios.push(objG);
    
}

for (var i = 0; i < 2; i++) {
    var objG = {
        iddueño: 123 + i,
        nombremascota: "polo",
        sexo: "hembra",
        raza: "perro comun",
        fechanacimiento: "23/02/2007",
        unidadpeso: "klg",
        peso: 10

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
        iddueño: $("#form-nu #txt-iddueño-u").val().trim(),
        nombremascota: $("#txt-nombremascota-u").val().trim(),
        sexo: $("#txt-sexo-u").val().trim(),
          raza: $("#txt-raza-u").val().trim(),
        fechanacimiento: $("#txt-fechanacimiento-u").val().trim(),

        unidadpeso: $("#txt-unidadpeso-u").val().trim(),
        peso: $("#txt-peso-u").val().trim(),
        
         

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
                "<td>" + obj.iddueño + "</td>" +
                "<td>" + obj.nombremascota + "</td>" +
                 "<td>" + obj.sexo + "</td>" +
                 "<td>" + obj.raza + "</td>" +
                 "<td>" + obj.fechanacimiento + "</td>" +
                  "<td>" + obj.unidadpeso+ "</td>" +
                   "<td>" + obj.peso + "</td>" +
                  

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
        var rta = confirm("¿Está seguro de eliminar a " + usuario.nombremascota + " " + usuario.sexo + "?");
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
        htmlModal = htmlModal.replace("#iddueño#", usuario.iddueño);
        htmlModal = htmlModal.replace("#nombremascota#", usuario.nombremascota);
        htmlModal = htmlModal.replace("#sexo#", usuario.sexo);
        htmlModal = htmlModal.replace("#raza#", usuario.raza);
        htmlModal = htmlModal.replace("#fechanacimiento#", usuario.fechanacimiento);
    
        htmlModal = htmlModal.replace("#unidadpeso#", usuario.unidadpeso);
         htmlModal = htmlModal.replace("#peso#", usuario.peso);
         
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
