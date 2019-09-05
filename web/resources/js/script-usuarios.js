
var contador = 1;
var usuarios = new Array();

for (var i = 1; i < 6; i++) {
    var objG = {
        id: 0 + i,
        mascota: "pacha",
        raza: "",
        genero: "Hembra",
        cliente: "Juan",
        apellido: "Perez",
        gmail: "Juan@gmail.co",
        direccion: "Bogota",
        nutricion: "Excelente",
        estilo: "Agradable",
         entorno: "Comparte con otras mascotas su entorno",
          estado: "Excelente",
           analgesicos: "Se les recomiendan los siguientes analgesicos: etc"

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
        id: $("#form-nu #txt-id-u").val().trim(),
        mascota: $("#txt-mascota-u").val().trim(),
        raza: $("#txt-raza-u").val().trim(),
        genero: $("#txt-genero-u").val().trim(),
        cliente: $("#txt-cliente-u").val().trim(),
        apellido: $("#txt-apellido-u").val().trim(),
        gmail: $("#txt-gmail-u").val().trim(),
        direccion: $("#txt-direccion-u").val().trim(),
        nutricion: $("#txt-nutricion-u").val().trim(),
        estilo: $("#txt-estilo-u").val().trim(),
        entorno: $("#txt-entorno-u").val().trim(),
        estado: $("#txt-estado-u").val().trim(),
        analgesicos: $("#txt-analgesico-u").val().trim()
         

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
                "<td>" + obj.mascota + "</td>" +
                "<td>" + obj.raza + "</td>" +
                "<td>" + obj.genero + "</td>" +
                 "<td>" + obj.cliente + "</td>" +
                 "<td>" + obj.apellido + "</td>" +
                 "<td>" + obj.gmail + "</td>" +
                  "<td>" + obj.direccion + "</td>" +
                   "<td>" + obj.nutricion + "</td>" +
                    "<td>" + obj.estilo + "</td>" +
                    "<td>" + obj.entorno + "</td>" +
                    "<td>" + obj.estado + "</td>" +
                    "<td>" + obj.analgesicos + "</td>" +

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
        var rta = confirm("¿Está seguro de eliminar a " + usuario.mascota + " " + usuario.ra + "?");
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
        htmlModal = htmlModal.replace("#mascota#", usuario.mascota);
        htmlModal = htmlModal.replace("#raza#", usuario.raza);
        htmlModal = htmlModal.replace("#genero#", usuario.genero);
        htmlModal = htmlModal.replace("#cliente#", usuario.cliente);
        htmlModal = htmlModal.replace("#apellido#", usuario.apellido);
        htmlModal = htmlModal.replace("#gmail#", usuario.gmail);
        htmlModal = htmlModal.replace("#direccion#", usuario.direccion);
         htmlModal = htmlModal.replace("#nutricion#", usuario.nutricion);
          htmlModal = htmlModal.replace("#estilo#", usuario.estilo);
         htmlModal = htmlModal.replace("#entorno#", usuario.entorno);
         htmlModal = htmlModal.replace("#estado#", usuario.estado);
         htmlModal = htmlModal.replace("#analgesicos#", usuario.analgesicos);
        //alert(htmlModal);
        $("#modalDetalle .modal-body").html(htmlModal);

    });
}


