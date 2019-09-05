function showTooltip(x, y, contents) {
    $('&lt;div id=&quot;tooltip&quot;&gt;' + contents + '&lt;/div&gt;')
        .css({
        top: y - 16,
        left: x + 20
    }).appendTo('body').fadeIn();
}
 
var previousPoint = null;
 
$('#graph-lines, #graph-bars').bind('plothover', function (event, pos, item) {
    if (item) {
        if (previousPoint != item.dataIndex) {
            previousPoint = item.dataIndex;
            $('#tooltip').remove();
            var x = item.datapoint[0],
                y = item.datapoint[1];
                showTooltip(item.pageX, item.pageY, y + ' visitors at ' + x + '.00h');
        }
    } else {
        $('#tooltip').remove();
        previousPoint = null;
    }
});
