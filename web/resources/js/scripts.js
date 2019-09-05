/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    $("#btn-menu").click(function () {
        //$("#main-menu").css({"display": "none"});
        $("#main-menu").toggleClass("main-menu-hide");
        //$("#main-menu").fadeOut("slow");
        $("#right-content").toggleClass("col-md-12");
    });
});
