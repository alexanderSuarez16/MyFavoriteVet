// Lines
$.plot($('#graph-lines'), graphData, {
    series: {
        points: {
            show: true,
            radius: 5
        },
        lines: {
            show: true
        },
        shadowSize: 0
    },
    grid: {
        color: '#646464',
        borderColor: 'transparent',
        borderWidth: 20,
        hoverable: true
    },
    xaxis: {
        tickColor: 'transparent',
        tickDecimals: 2
    },
    yaxis: {
        tickSize: 1000
    }
});
 
// Bars
$.plot($('#graph-bars'), graphData, {
    series: {
        bars: {
            show: true,
            barWidth: .9,
            align: 'center'
        },
        shadowSize: 0
    },
    grid: {
        color: '#646464',
        borderColor: 'transparent',
        borderWidth: 20,
        hoverable: true
    },
    xaxis: {
        tickColor: 'transparent',
        tickDecimals: 2
    },
    yaxis: {
        tickSize: 1000
    }
});

