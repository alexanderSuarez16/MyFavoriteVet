
var contador = 1;
var usuarios = new Array();

for (var i = 0; i < 3; i++) {
    var objG = {
        id: 1 + i,
        servicio: "peluqueria",
        fecha: "10/17/2017"
        

    };
    usuarios.push(objG);
    
}


for (var i = 0; i < 3; i++) {
    var objG = {
        id: 1 + i,
        Servicio: "baño",
        fecha: "10/17/2017"
        

    };
    usuarios.push(objG);
    
}

for (var i = 0; i < 3; i++) {
    var objG = {
        id: 1 + i,
        Servicio: "estetica",
        fecha: "10/17/2017"
        

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
        servicio: $("#txt-servicio-u").val().trim(),
        fecha: $("#txt-fecha-u").val().trim(),
       

    
         

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
                "<td>" + obj.Servicio + "</td>" +
                 "<td>" + obj.fecha + "</td>" +
                 
                  

                "<td>" +
                "<a id=\"btn-ver-" + i + "\" class=\"btn btn-success\"  data-toggle=\"modal\" data-target=\"#modalDetalle\"><i class=\"fa fa-eye\"></i></a>" +
               
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
        htmlModal = htmlModal.replace("#servicio#", usuario.servicio);
        htmlModal = htmlModal.replace("#fecha#", usuario.fecha);
        
         
        //alert(htmlModal);
        $("#modalDetalle .modal-body").html(htmlModal);

    });
}


