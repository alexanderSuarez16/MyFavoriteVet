

$(function () {
    $("#login").submit(function () {
        var email = $("#login #login").val();
        var pass = $("#login #txt-password").val();
        if(email === "administrador@gmail.com" && pass === "123"){
            $("#login").attr("action","Empleado/Empleado.xhtml");
            return true;
        }

        else if(email === "veterinario@gmail.com" && pass === "123"){
            $("#login").attr("action","v-eterinario.html");
            return true;

        }
        else if(email === "empleado@gmail.com" && pass === "123"){
            $("#login").attr("action","e-mpleado.html");
            return true;
        }
        else if(email === "cliente@gmail.com" && pass === "123"){
            $("#login").attr("action","u-suario.html");
            return true;
        }
        else
        alert("Los datos que a ingresado no son los correctos por favor vuelva a intentarlo");
        return false;
    });
});

function pruebaemail(valor) {
                re = /^([\da-z_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/
                if (!re.exec(valor)) {
                    alert('El correo ingresado no es valido');
                    return true;
                } else
                    alert('Exito nos colocaremos en  contacto con usted el algun momento');

            }



