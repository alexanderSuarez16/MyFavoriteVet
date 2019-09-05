
$('#graph-bars').hide();
 
$('#lines').on('click', function (e) {
    $('#bars').removeClass('active');
    $('#graph-bars').fadeOut();
    $(this).addClass('active');
    $('#graph-lines').fadeIn();
    e.preventDefault();
});
 
$('#bars').on('click', function (e) {
    $('#lines').removeClass('active');
    $('#graph-lines').fadeOut();
    $(this).addClass('active');
    $('#graph-bars').fadeIn().removeClass('hidden');
    e.preventDefault();
});


.yAxis .tickLabel:first-child,
.yAxis .tickLabel:last-child { display: none; }