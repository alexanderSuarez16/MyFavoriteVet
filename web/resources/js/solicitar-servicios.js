
var contador = 0;
var usuarios = new Array();

for (var i = 0; i < 1; i++) {
    var objG = {
        id: 0 + 1 + i,
        baño: "Servicio de baño para caninos y felinos",
        peluqueria: "Servicio de peluqueria para caninos y felinos",
        estetica: "Servicio de estetica para caninos y felinos"
    };
    usuarios.push(objG);
}


usuarios[usuarios.length] = objG;

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
        baño: $("#txt-nombre-u").val().trim(),
        peluqueria: $("#txt-fecha-u").val().trim(),
        estetica: $("#txt-dueño-u").val().trim()
        
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
                "<td>" + obj.baño + "</td>" +
                 "<td>" + obj.peluqueria + "</td>" +
                "<td>" + obj.estetica + "</td>" +
              
                "<td>" +
                "<a id=\"btn-ver-" + i + "\" class=\"btn btn-success\"  data-toggle=\"modal\" data-target=\"#modalDetalle\"><i class=\"fa fa-check-circle\"></i></a>" +
               
                "</td>" +
                "</tr>";
        $("#tbl-usuarios>tbody").append(html);
       
        addEvtVer(i);
    }
}


function addEvtVer(i) {
    $("#btn-ver-" + i).click(function () {
        var usuario = usuarios[i];
        var htmlModal = $("#modalDetalle .modal-body").html();
        //alert(htmlModal);
        htmlModal = htmlModal.replace("#baño#", usuario.baño);
          htmlModal = htmlModal.replace("#peluqueria#", usuario.peluqueria);
        htmlModal = htmlModal.replace("#estetica#", usuario.estetica);
     
        //alert(htmlModal);
        $("#modalDetalle .modal-body").html(htmlModal);

    });
}


