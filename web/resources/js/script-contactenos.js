
function valida(f) {
  var ok = true;
  var msg = "Debes escribir algo en los campos:\n";

    if(f.elements[0].value == "")
  {
    msg += "Correo electronico\n";
    ok = false;
  }


  if(f.elements["jugador2"].value == "")
  {
    msg += "Nombres y apellidos\n";
    ok = false;
  }

  if(f.elements["jugador3"].value == "")
  {
    msg += "Telefono\n";
    ok = false;
  }

  if(f.jugador4.value == "")
  {
    msg += "Comentario y sugerencias\n";
    ok = false;
  }

  if(ok == false)
    alert(msg);
  return ok;

}



 