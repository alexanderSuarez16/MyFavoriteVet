function validateMail(idMail)
{
  //aqui cree un objeto
  object=document.getElementById(idMail);
  valueForm=object.value;
 
  // aqui va como tiene que ser el correo
  var patron=/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,4})+$/;
  if(valueForm.search(patron)==0)
  {
    //este va ser el color para el email correcto
    object.style.color="#000";
    return;
  }
  //este va hacer ser el email para el correo incorrecto
  object.style.color="#f00";
}
