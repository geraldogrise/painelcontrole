<div id="barraTopo">
<div class="migalha">
<span>Dashboard</span>
</div>
</div>
<div class="boxGrid" style="overflow:hidden">
<table width="100%" style="min-width:400px" cellspacing="0" cellpadding="0" border="0" class="tabelaSistema">
<tr>
<td  align="center"><div id="container-speed" style="height: 125px;"></div></td>
<td colspan="2"><div id="svaPreTime" style="min-width: 150px;  margin: 0 auto; height:130px"></div></td>
<td  align="center"><div id="container-speed2" style="height: 125px;"></div></td>
<td colspan="2"><div id="bffPreTime" style="min-width: 150px;  margin: 0 auto; height:130px"></div></td>

</tr>

<tr>
<th class="thBg2" colspan="3" style="height:15px">BFM</th>
<th  colspan="3" style="height:15px">BFF</th>
</tr>


<tr>
<th width="16%"  style="height:15px">ARQUIVOS PRÉ</th>
<th width="16%"  style="height:15px">ARQUIVOS PÓS</th>
<th width="16%"  style="height:15px">ARQUIVO FIXA</th>
<th width="16%" class="thBg2"  style="height:15px">ARQUIVOS PRÉ</th>
<th width="16%" class="thBg2" style="height:15px">ARQUIVOS PÓS</th>
<th width="16%" class="thBg2"  style="height:15px">ARQUIVO FIXA</th>
</tr>

<tr>
<td height="100" align="center">
<div style="overflow:hidden; height:140px;">
<div class="pizza" id="svaPre" style="width: 150px;"></div></div>
<strong style="display:block; text-align:center; font-size:11px">Total de Arquivos: 129</strong>
</td>
<td height="100" align="center">
<div style="overflow:hidden; height:140px;">
<div class="pizza" id="svaPos" style="width: 150px;"></div></div>
<strong style="display:block; text-align:center; font-size:11px">Total de Arquivos: 129</strong>
</td>
<td height="100" align="center">
<div style="overflow:hidden; height:140px;"><div class="pizza" id="svaFixo" style="width: 150px;"></div></div>
<strong style="display:block; text-align:center; font-size:11px">Total de Arquivos: 129</strong>
</td>

<td height="100" align="center">
<div style="overflow:hidden; height:140px;"><div class="pizza" id="svaPre2" style="width: 150px;"></div></div>
<strong style="display:block; text-align:center; font-size:11px">Total de Arquivos: 129</strong>
</td>
<td height="100" align="center">
<div style="overflow:hidden; height:140px;"><div class="pizza" id="svaPos2" style="width: 150px;"></div></div>
<strong style="display:block; text-align:center; font-size:11px">Total de Arquivos: 129</strong>
</td>
<td height="100" align="center">
<div style="overflow:hidden; height:140px;"><div class="pizza" id="svaFixo2" style="width: 150px;"></div></div>
<strong style="display:block; text-align:center; font-size:11px">Total de Arquivos: 129</strong>
</td>

</tr>


<tr>
<th align="center"  style="height:15px">REGISTROS PRÉ</th>
<th align="center" style="height:15px">REGISTROS PÓS</th>
<th align="center"  style="height:15px">REGISTROS FIXA</th>
<th align="center" class="thBg2" style="height:15px">REGISTROS PRÉ</th>
<th align="center" class="thBg2"  style="height:15px">REGISTROS PÓS</th>
<th align="center" class="thBg2"  style="height:15px">REGISTROS FIXA</th>
</tr>
<tr>
<td height="100" align="center">
<div style="overflow:hidden; height:140px;"><div class="pizza" id="svaPreRe" style="width: 150px;"></div></div>
<strong style="display:block; text-align:center; font-size:11px">Total de Arquivos: 90</strong>
</td>
<td height="100" align="center">
<div style="overflow:hidden; height:140px;"><div class="pizza" id="svaPosRe" style="width: 150px;"></div></div>
<strong style="display:block; text-align:center; font-size:11px">Total de Arquivos: 90</strong>
</td>
<td height="100" align="center">
<div style="overflow:hidden; height:140px;"><div class="pizza" id="svaFixoRe" style="width: 150px;"></div></div>
<strong style="display:block; text-align:center; font-size:11px">Total de Arquivos: 90</strong>
</td>

<td height="100" align="center">
<div style="overflow:hidden; height:140px;"><div class="pizza" id="svaPreRe2" style="width: 150px;"></div></div>
<strong style="display:block; text-align:center; font-size:11px">Total de Arquivos: 90</strong>
</td>
<td height="100" align="center">
<div style="overflow:hidden; height:140px;"><div class="pizza" id="svaPosRe2" style="width: 150px;"></div></div>
<strong style="display:block; text-align:center; font-size:11px">Total de Arquivos: 90</strong>
</td>
<td height="100" align="center">
<div style="overflow:hidden; height:140px;"><div class="pizza" id="svaFixoRe2" style="width: 150px;"></div></div>
<strong style="display:block; text-align:center; font-size:11px">Total de Arquivos: 90</strong>
</td>

</tr>

</table>



</div>

<script type="text/javascript">
var moeda = {
		/**
		* retiraFormatacao
		* Remove a formatação de uma string de moeda e retorna um float
		* @param {Object} num
		*/
		desformatar: function(num){
		num = num.replace(".","");
		num = num.replace(",",".");
		return parseFloat(num);
		},
		/**
		* formatar
		* Deixar um valor float no formato monetário
		* @param {Object} num
		*/
		formatar: function(num){
		x = 0;
		if(num<0){
		num = Math.abs(num);
		x = 1;
		}
		if(isNaN(num)) num = "0";
		cents = Math.floor((num*100+0.5)%100);
		num = Math.floor((num*100+0.5)/100).toString();
		if(cents < 10) cents = "0" + cents;
		for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)
		num = num.substring(0,num.length-(4*i+3))+'.'
		+num.substring(num.length-(4*i+3));
		ret = num;
		if (x == 1 && ret!='0,00') ret = ' – ' + ret;return ret;
		},

		/**
		* arredondar
		* @abstract Arredonda um valor quebrado para duas casas
		* decimais.
		* @param {Object} num
		*/
		arredondar: function(num){
		return Math.round(num*Math.pow(10,2))/Math.pow(10,2);
		}};








$(function () {
    $('#svaPre').highcharts({
		 colors: [
        {
        radialGradient: { cx: 0.5, cy: 0.3, r: 0.7 },
        stops: [
            [0, '#4F81BD'],
            [1, Highcharts.Color('#4F81BD').brighten(-0.3).get('hex')] // darken
        ]
    }, 
        {
        radialGradient: { cx: 0.5, cy: 0.3, r: 0.7 },
        stops: [
            [0, '#C0504D'],
            [1, Highcharts.Color('#C0504D').brighten(-0.3).get('hex')] // darken
        ]
    }, 
        {
        radialGradient: { cx: 0.5, cy: 0.3, r: 0.7 },
        stops: [
            [0, '#9BBB59'],
            [1, Highcharts.Color('#9BBB59').brighten(-0.3).get('hex')] // darken
        ]
    },
	
	],
		 
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
			spacingTop: -240
        },
        title: {
            text: 'Processamento BFM'
        },
        tooltip: {
    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%<br>Qtda. Arquivos:<b>{point.y}</b></b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {               		distance: -30,
                    enabled: false,
					color: 'white',
                    format: '<b style="font-size:8px">{point.percentage:.1f}%</b>',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                }
            }
        },
        series: [{
            type: 'pie',
            name: 'ARQUIVOS PRÉ',
           		point: {
				events: {					
						click: function(e) {						
					    if(e.point.url)
						mudaPagina(e.point.url);
						e.preventDefault();
					}
				}
			},
			
			
             data: [
        {name: 'Processados', y: 87, url: 'monitoramento_lotes.html'},
        {name: 'Erro',  y: 10, url: 'monitoramento_lotes.html'},
        {name: 'Não Processados',  y: 32, url: 'monitoramento_lotes.html'}
    ]
        }]
    });

    $('#svaPos').highcharts({
		 colors: [
        {
        radialGradient: { cx: 0.5, cy: 0.3, r: 0.7 },
        stops: [
            [0, '#4F81BD'],
            [1, Highcharts.Color('#4F81BD').brighten(-0.3).get('hex')] // darken
        ]
    }, 
        {
        radialGradient: { cx: 0.5, cy: 0.3, r: 0.7 },
        stops: [
            [0, '#C0504D'],
            [1, Highcharts.Color('#C0504D').brighten(-0.3).get('hex')] // darken
        ]
    }, 
        {
        radialGradient: { cx: 0.5, cy: 0.3, r: 0.7 },
        stops: [
            [0, '#9BBB59'],
            [1, Highcharts.Color('#9BBB59').brighten(-0.3).get('hex')] // darken
        ]
    },
	
	],
		 
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
			spacingTop: -240
        },
        title: {
            text: 'Processamento BFM'
        },
        tooltip: {
    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%<br>Qtda. Arquivos:<b>{point.y}</b></b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {               		distance: -30,
                    enabled: false,
					color: 'white',
                    format: '<b style="font-size:8px">{point.percentage:.1f}%</b>',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                }
            }
        },
        series: [{
            type: 'pie',
            name: 'ARQUIVOS PRÉ',
			
					point: {
				events: {					
						click: function(e) {						
					    if(e.point.url)
						mudaPagina(e.point.url);
						e.preventDefault();
					}
				}
			},
			
			
             data: [
           {name: 'Processados', y: 87, url: 'monitoramento_lotes.html'},
        {name: 'Erro',  y: 10, url: 'monitoramento_lotes.html'},
        {name: 'Não Processados',  y: 32, url: 'monitoramento_lotes.html'}
    ]
        }]
    });
	
	
  $('#svaFixo').highcharts({
		 colors: [
        {
        radialGradient: { cx: 0.5, cy: 0.3, r: 0.7 },
        stops: [
            [0, '#4F81BD'],
            [1, Highcharts.Color('#4F81BD').brighten(-0.3).get('hex')] // darken
        ]
    }, 
        {
        radialGradient: { cx: 0.5, cy: 0.3, r: 0.7 },
        stops: [
            [0, '#C0504D'],
            [1, Highcharts.Color('#C0504D').brighten(-0.3).get('hex')] // darken
        ]
    }, 
        {
        radialGradient: { cx: 0.5, cy: 0.3, r: 0.7 },
        stops: [
            [0, '#9BBB59'],
            [1, Highcharts.Color('#9BBB59').brighten(-0.3).get('hex')] // darken
        ]
    },
	
	],
		 
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
			spacingTop: -240
        },
        title: {
            text: 'Processamento BFF'
        },
        tooltip: {
    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%<br>Qtda. Arquivos:<b>{point.y}</b></b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {               		distance: -30,
                    enabled: false,
					color: 'white',
                    format: '<b style="font-size:8px">{point.percentage:.1f}%</b>',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                }
            }
        },
        series: [{
            type: 'pie',
            name: 'ARQUIVOS PRÉ',
           point: {
				events: {					
						click: function(e) {						
					    if(e.point.url)
						mudaPagina(e.point.url);
						e.preventDefault();
					}
				}
			},
			
			
             data: [
           {name: 'Processados', y: 87, url: 'monitoramento_lotes.html'},
        {name: 'Erro',  y: 10, url: 'monitoramento_lotes.html'},
        {name: 'Não Processados',  y: 32, url: 'monitoramento_lotes.html'}
		
    ]
        }]
    });


 $('#svaPre2').highcharts({
		 colors: [
        {
        radialGradient: { cx: 0.5, cy: 0.3, r: 0.7 },
        stops: [
            [0, '#4F81BD'],
            [1, Highcharts.Color('#4F81BD').brighten(-0.3).get('hex')] // darken
        ]
    }, 
        {
        radialGradient: { cx: 0.5, cy: 0.3, r: 0.7 },
        stops: [
            [0, '#C0504D'],
            [1, Highcharts.Color('#C0504D').brighten(-0.3).get('hex')] // darken
        ]
    }, 
        {
        radialGradient: { cx: 0.5, cy: 0.3, r: 0.7 },
        stops: [
            [0, '#9BBB59'],
            [1, Highcharts.Color('#9BBB59').brighten(-0.3).get('hex')] // darken
        ]
    },
	
	],
		 
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
			spacingTop: -240
        },
        title: {
            text: 'Processamento BFM'
        },
        tooltip: {
    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%<br>Qtda. Arquivos:<b>{point.y}</b></b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {               		distance: -30,
                    enabled: false,
					color: 'white',
                    format: '<b style="font-size:8px">{point.percentage:.1f}%</b>',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                }
            }
        },
        series: [{
            type: 'pie',
            name: 'ARQUIVOS PRÉ',
            point: {
				events: {					
						click: function(e) {						
					    if(e.point.url)
						mudaPagina(e.point.url);
						e.preventDefault();
					}
				}
			},
			
			
             data: [
           {name: 'Processados', y: 87, url: 'monitoramento_lotes.html'},
        {name: 'Erro',  y: 10, url: 'monitoramento_lotes.html'},
        {name: 'Não Processados',  y: 32, url: 'monitoramento_lotes.html'}
    ]
        }]
    });

    $('#svaPos2').highcharts({
		 colors: [
        {
        radialGradient: { cx: 0.5, cy: 0.3, r: 0.7 },
        stops: [
            [0, '#4F81BD'],
            [1, Highcharts.Color('#4F81BD').brighten(-0.3).get('hex')] // darken
        ]
    }, 
        {
        radialGradient: { cx: 0.5, cy: 0.3, r: 0.7 },
        stops: [
            [0, '#C0504D'],
            [1, Highcharts.Color('#C0504D').brighten(-0.3).get('hex')] // darken
        ]
    }, 
        {
        radialGradient: { cx: 0.5, cy: 0.3, r: 0.7 },
        stops: [
            [0, '#9BBB59'],
            [1, Highcharts.Color('#9BBB59').brighten(-0.3).get('hex')] // darken
        ]
    },
	
	],
		 
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
			spacingTop: -240
        },
        title: {
            text: 'Processamento BFM'
        },
        tooltip: {
    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%<br>Qtda. Arquivos:<b>{point.y}</b></b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {               		distance: -30,
                    enabled: false,
					color: 'white',
                    format: '<b style="font-size:8px">{point.percentage:.1f}%</b>',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                }
            }
        },
        series: [{
            type: 'pie',
            name: 'ARQUIVOS PRÉ',
            point: {
				events: {					
						click: function(e) {						
					    if(e.point.url)
						mudaPagina(e.point.url);
						e.preventDefault();
					}
				}
			},
			
			
             data: [
           {name: 'Processados', y: 87, url: 'monitoramento_lotes.html'},
        {name: 'Erro',  y: 10, url: 'monitoramento_lotes.html'},
        {name: 'Não Processados',  y: 32, url: 'monitoramento_lotes.html'}
    ]
        }]
    });
	
	
  $('#svaFixo2').highcharts({
		 colors: [
        {
        radialGradient: { cx: 0.5, cy: 0.3, r: 0.7 },
        stops: [
            [0, '#4F81BD'],
            [1, Highcharts.Color('#4F81BD').brighten(-0.3).get('hex')] // darken
        ]
    }, 
        {
        radialGradient: { cx: 0.5, cy: 0.3, r: 0.7 },
        stops: [
            [0, '#C0504D'],
            [1, Highcharts.Color('#C0504D').brighten(-0.3).get('hex')] // darken
        ]
    }, 
        {
        radialGradient: { cx: 0.5, cy: 0.3, r: 0.7 },
        stops: [
            [0, '#9BBB59'],
            [1, Highcharts.Color('#9BBB59').brighten(-0.3).get('hex')] // darken
        ]
    },
	
	],
		 
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
			spacingTop: -240
        },
        title: {
            text: 'Processamento BFF'
        },
        tooltip: {
    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%<br>Qtda. Arquivos:<b>{point.y}</b></b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {               		distance: -30,
                    enabled: false,
					color: 'white',
                    format: '<b style="font-size:8px">{point.percentage:.1f}%</b>',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                }
            }
        },
        series: [{
            type: 'pie',
            name: 'ARQUIVOS PRÉ',
           point: {
				events: {					
						click: function(e) {						
					    if(e.point.url)
						mudaPagina(e.point.url);
						e.preventDefault();
					}
				}
			},
			
			
             data: [
           {name: 'Processados', y: 87, url: 'monitoramento_lotes.html'},
        {name: 'Erro',  y: 10, url: 'monitoramento_lotes.html'},
        {name: 'Não Processados',  y: 32, url: 'monitoramento_lotes.html'}
    ]
        }]
    });

	
	
//REGISTROS



$('#svaPreRe').highcharts({
		 colors: [
        {
        radialGradient: { cx: 0.5, cy: 0.3, r: 0.7 },
        stops: [
            [0, '#4F81BD'],
            [1, Highcharts.Color('#4F81BD').brighten(-0.3).get('hex')] // darken
        ]
    }, 
        {
        radialGradient: { cx: 0.5, cy: 0.3, r: 0.7 },
        stops: [
            [0, '#C0504D'],
            [1, Highcharts.Color('#C0504D').brighten(-0.3).get('hex')] // darken
        ]
    }, 
        {
        radialGradient: { cx: 0.5, cy: 0.3, r: 0.7 },
        stops: [
            [0, '#f1ae00'],
            [1, Highcharts.Color('#f1ae00').brighten(-0.3).get('hex')] // darken
        ]
    },
	
	],
		 
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
			spacingTop: -240
        },
        title: {
            text: 'Processamento BFM'
        },
        tooltip: {
    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%<br>Qtda. Arquivos:<b>{point.y}</b></b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {               		distance: -30,
                    enabled: false,
					color: 'white',
                    format: '<b style="font-size:8px">{point.percentage:.1f}%</b>',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                }
            }
        },
        series: [{
            type: 'pie',
            name: 'ARQUIVOS PRÉ',
            point: {
				events: {					
						click: function(e) {						
					    if(e.point.url)
						mudaPagina(e.point.url);
						e.preventDefault();
					}
				}
			},
			
			
             data: [
			 
           {name: 'Processados', y:60, url: 'monitoramento_lotes.html'},
        {name: 'Erro',  y: 10, url: 'monitoramento_lotes.html'},
		{name: 'Em Processamento', y:30/*, url: '#'*/},
    ]
        }]
    });

    $('#svaPosRe').highcharts({
		 colors: [
        {
        radialGradient: { cx: 0.5, cy: 0.3, r: 0.7 },
        stops: [
            [0, '#4F81BD'],
            [1, Highcharts.Color('#4F81BD').brighten(-0.3).get('hex')] // darken
        ]
    }, 
        {
        radialGradient: { cx: 0.5, cy: 0.3, r: 0.7 },
        stops: [
            [0, '#C0504D'],
            [1, Highcharts.Color('#C0504D').brighten(-0.3).get('hex')] // darken
        ]
    }, 
        {
        radialGradient: { cx: 0.5, cy: 0.3, r: 0.7 },
        stops: [
            [0, '#f1ae00'],
            [1, Highcharts.Color('#f1ae00').brighten(-0.3).get('hex')] // darken
        ]
    },
	
	],
		 
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
			spacingTop: -240
        },
        title: {
            text: 'Processamento BFM'
        },
        tooltip: {
    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%<br>Qtda. Arquivos:<b>{point.y}</b></b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {               		distance: -30,
                    enabled: false,
					color: 'white',
                    format: '<b style="font-size:8px">{point.percentage:.1f}%</b>',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                }
            }
        },
        series: [{
            type: 'pie',
            name: 'ARQUIVOS PRÉ',
           point: {
				events: {					
						click: function(e) {						
					    if(e.point.url)
						mudaPagina(e.point.url);
						e.preventDefault();
					}
				}
			},
			
			
             data: [
           {name: 'Processados', y:60, url: 'monitoramento_lotes.html'},
        {name: 'Erro',  y: 10, url: 'monitoramento_lotes.html'},
		{name: 'Em Processamento', y:30/*, url: '#'*/},
    ]
        }]
    });

	
  $('#svaFixoRe').highcharts({
		 colors: [
        {
        radialGradient: { cx: 0.5, cy: 0.3, r: 0.7 },
        stops: [
            [0, '#4F81BD'],
            [1, Highcharts.Color('#4F81BD').brighten(-0.3).get('hex')] // darken
        ]
    }, 
        {
        radialGradient: { cx: 0.5, cy: 0.3, r: 0.7 },
        stops: [
            [0, '#C0504D'],
            [1, Highcharts.Color('#C0504D').brighten(-0.3).get('hex')] // darken
        ]
    }, 
        {
        radialGradient: { cx: 0.5, cy: 0.3, r: 0.7 },
        stops: [
            [0, '#f1ae00'],
            [1, Highcharts.Color('#f1ae00').brighten(-0.3).get('hex')] // darken
        ]
    },
	
	],
		 
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
			spacingTop: -240
        },
        title: {
            text: 'Processamento BFF'
        },
        tooltip: {
    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%<br>Qtda. Arquivos:<b>{point.y}</b></b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {               		distance: -30,
                    enabled: false,
					color: 'white',
                    format: '<b style="font-size:8px">{point.percentage:.1f}%</b>',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                }
            }
        },
        series: [{
            type: 'pie',
            name: 'ARQUIVOS PRÉ',
           point: {
				events: {					
						click: function(e) {						
					    if(e.point.url)
						mudaPagina(e.point.url);
						e.preventDefault();
					}
				}
			},
			
			
             data: [
           {name: 'Processados', y:60, url: 'monitoramento_lotes.html'},
        {name: 'Erro',  y: 10, url: 'monitoramento_lotes.html'},
		{name: 'Em Processamento', y:30/*, url: '#'*/},
    ]
        }]
    });



 $('#svaPreRe2').highcharts({
		 colors: [
        {
        radialGradient: { cx: 0.5, cy: 0.3, r: 0.7 },
        stops: [
            [0, '#4F81BD'],
            [1, Highcharts.Color('#4F81BD').brighten(-0.3).get('hex')] // darken
        ]
    }, 
        {
        radialGradient: { cx: 0.5, cy: 0.3, r: 0.7 },
        stops: [
            [0, '#C0504D'],
            [1, Highcharts.Color('#C0504D').brighten(-0.3).get('hex')] // darken
        ]
    }, 
        {
        radialGradient: { cx: 0.5, cy: 0.3, r: 0.7 },
        stops: [
            [0, '#f1ae00'],
            [1, Highcharts.Color('#f1ae00').brighten(-0.3).get('hex')] // darken
        ]
    },
	
	],
		 
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
			spacingTop: -240
        },
        title: {
            text: 'Processamento BFM'
        },
        tooltip: {
    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%<br>Qtda. Arquivos:<b>{point.y}</b></b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {               		distance: -30,
                    enabled: false,
					color: 'white',
                    format: '<b style="font-size:8px">{point.percentage:.1f}%</b>',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                }
            }
        },
        series: [{
            type: 'pie',
            name: 'ARQUIVOS PRÉ',
           point: {
				events: {					
						click: function(e) {						
					    if(e.point.url)
						mudaPagina(e.point.url);
						e.preventDefault();
					}
				}
			},
			
			
             data: [
           {name: 'Processados', y:60, url: 'monitoramento_lotes.html'},
        {name: 'Erro',  y: 10, url: 'monitoramento_lotes.html'},
		{name: 'Em Processamento', y:30/*, url: '#'*/},
    ]
        }]
    });


    $('#svaPosRe2').highcharts({
		 colors: [
        {
        radialGradient: { cx: 0.5, cy: 0.3, r: 0.7 },
        stops: [
            [0, '#4F81BD'],
            [1, Highcharts.Color('#4F81BD').brighten(-0.3).get('hex')] // darken
        ]
    }, 
        {
        radialGradient: { cx: 0.5, cy: 0.3, r: 0.7 },
        stops: [
            [0, '#C0504D'],
            [1, Highcharts.Color('#C0504D').brighten(-0.3).get('hex')] // darken
        ]
    }, 
        {
        radialGradient: { cx: 0.5, cy: 0.3, r: 0.7 },
        stops: [
            [0, '#f1ae00'],
            [1, Highcharts.Color('#f1ae00').brighten(-0.3).get('hex')] // darken
        ]
    },
	
	],
		 
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
			spacingTop: -240
        },
        title: {
            text: 'Processamento BFM'
        },
        tooltip: {
    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%<br>Qtda. Arquivos:<b>{point.y}</b></b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {               		distance: -30,
                    enabled: false,
					color: 'white',
                    format: '<b style="font-size:8px">{point.percentage:.1f}%</b>',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                }
            }
        },
        series: [{
            type: 'pie',
            name: 'ARQUIVOS PRÉ',
            point: {
				events: {					
						click: function(e) {						
					    if(e.point.url)
						mudaPagina(e.point.url);
						e.preventDefault();
					}
				}
			},
			
			
             data: [
           {name: 'Processados', y:40, url: 'monitoramento_lotes.html'},
        {name: 'Erro',  y: 10, url: 'monitoramento_lotes.html'},
		{name: 'Em Processamento', y:50/*, url: '#'*/},
    ]
        }]
    });

	
  $('#svaFixoRe2').highcharts({
		 colors: [
        {
        radialGradient: { cx: 0.5, cy: 0.3, r: 0.7 },
        stops: [
            [0, '#4F81BD'],
            [1, Highcharts.Color('#4F81BD').brighten(-0.3).get('hex')] // darken
        ]
    }, 
        {
        radialGradient: { cx: 0.5, cy: 0.3, r: 0.7 },
        stops: [
            [0, '#C0504D'],
            [1, Highcharts.Color('#C0504D').brighten(-0.3).get('hex')] // darken
        ]
    }, 
        {
        radialGradient: { cx: 0.5, cy: 0.3, r: 0.7 },
        stops: [
            [0, '#f1ae00'],
            [1, Highcharts.Color('#f1ae00').brighten(-0.3).get('hex')] // darken
        ]
    },
	
	],
		 
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
			spacingTop: -240
        },
        title: {
            text: 'Processamento BFF'
        },
        tooltip: {
    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%<br>Qtda. Arquivos:<b>{point.y}</b></b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {               		distance: -30,
                    enabled: false,
					color: 'white',
                    format: '<b style="font-size:8px">{point.percentage:.1f}%</b>',
                    style: {
                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                    }
                }
            }
        },
        series: [{
            type: 'pie',
            name: 'ARQUIVOS PRÉ',
           point: {
				events: {					
						click: function(e) {						
					    if(e.point.url)
						mudaPagina(e.point.url);
						e.preventDefault();
					}
				}
			},
			
			
             data: [
           {name: 'Processados', y:80, url: 'monitoramento_lotes.html'},
        {name: 'Erro',  y: 10, url: 'monitoramento_lotes.html'},
		{name: 'Em Processamento', y:10/*, url: '#'*/},
    ]
        }]
    });
});

//REGISTROS
$(function () {
        Highcharts.setOptions({
            global: {
                useUTC: false
            }
        });
        var chart;
        $('#svaPreTime').highcharts({
            chart: {
                type: 'spline',
                animation: Highcharts.svg, // don't animate in old IE
                marginRight: 10,
                events: {
                    load: function() {
    
                        // set up the updating of the chart each second
                        var series = this.series[0];
                        setInterval(function() {
                            var x = (new Date()).getTime(), // current time
                                y =100000+ Math.ceil(Math.random() * 300000)
								if(y<0)y=0;
                            series.addPoint([x, y], true, true);
                        }, 1000);
                    }
                }
            },
            title: {
                text: ''
            },
            xAxis: {
                type: 'datetime',
                tickPixelInterval: 150
            },
            yAxis: {
                title: {
                    text: 'Registros'
                },
				
				labels: {
            formatter: function() {
					return moeda.formatar(this.value);			
		
            }
	    },
				
				
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            tooltip: {
                formatter: function() {
                        return '<b>'+ this.series.name +'</b><br/>'+
                        Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) +'<br/>'+
                        Highcharts.numberFormat(this.y, 2);
                }
            },
            legend: {
                enabled: false
            },
            exporting: {
                enabled: false
            },
            series: [{
                name: 'Random data',
                data: (function() {
                    // generate an array of random data
                    var data = [],
                        time = (new Date()).getTime(),
                        i;
    
                    for (i = -5; i <= 0; i++) {
						if(i<0)
						{
                        data.push({
                            x: time + i * 1000,
                            y: 100000+ Math.ceil(Math.random() * 200000)
                        });
						}else
						{
							 data.push({
                            x: time + i * 1000,
                            y: 450000
                        });
						}
                    }
                    return data;
                })()
            }]
        });
    });




//REGISTROS
$(function () {
        Highcharts.setOptions({
            global: {
                useUTC: false
            }
        });
        var chart;
        $('#bffPreTime').highcharts({
            chart: {
                type: 'spline',
                animation: Highcharts.svg, // don't animate in old IE
                marginRight: 10,
                events: {
                    load: function() {
    
                        // set up the updating of the chart each second
                        var series = this.series[0];
                        setInterval(function() {
                            var x = (new Date()).getTime(), // current time
                                y =100000+ Math.ceil(Math.random() * 300000);
								if(y<0)y=0;
                            series.addPoint([x, y], true, true);
                        }, 1000);
                    }
                }
            },
            title: {
                text: ''
            },
            xAxis: {
                type: 'datetime',
                tickPixelInterval: 150
            },
            yAxis: {
                title: {
                    text: 'Registros'
                },
				
				labels: {
            formatter: function() {
					return moeda.formatar(this.value);			
		
            }
	    },
				
				
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            tooltip: {
                formatter: function() {
                        return '<b>'+ this.series.name +'</b><br/>'+
                        Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) +'<br/>'+
                        Highcharts.numberFormat(this.y, 2);
                }
            },
            legend: {
                enabled: false
            },
            exporting: {
                enabled: false
            },
            series: [{
                name: 'Random data',
                data: (function() {
                    // generate an array of random data
                    var data = [],
                        time = (new Date()).getTime(),
                        i;
    
                    for (i = -5; i <= 0; i++) {
						if(i<0)
						{
                        data.push({
                            x: time + i * 1000,
                            y: 100000+ Math.ceil(Math.random() * 200000)
                        });
						}else
						{
							 data.push({
                            x: time + i * 1000,
                            y: 450000
                        });
						}
                    }
                    return data;
                })()
            }]
        });
    });

// RODA
$(function () {
	
    var gaugeOptions = {
	
	    chart: {
	        type: 'solidgauge'
	    },
	    
	    title: null,
	    
	    pane: {
	    	center: ['50%', '85%'],
	    	size: '140%',
	        startAngle: -90,
	        endAngle: 90,
            background: {
                backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || '#EEE',
                innerRadius: '60%',
                outerRadius: '100%',
                shape: 'arc'
            }
	    },

	    tooltip: {
	    	enabled: false
	    },
	       
	    // the value axis
	    yAxis: {
			stops: [
				[0.1, '#DF5353'], // green
	        	[0.5, '#DDDF0D'], // yellow
	        	[0.9, '#55BF3B'] // red
			],
			lineWidth: 0,
            minorTickInterval: null,
            tickPixelInterval: 400,
            tickWidth: 0,
	        title: {
                y: -50
	        },
            labels: {
                y: 16
            }        
	    },
        
        plotOptions: {
            solidgauge: {
                dataLabels: {
                    y: -30,
                    borderWidth: 0,
                    useHTML: true
                }
            }
        }
    };
    
    // The speed gauge
    $('#container-speed').highcharts(Highcharts.merge(gaugeOptions, {
        yAxis: {
	        min: 0,
	        max: 500.000,
	        title: {
	            text: 'Registros / Hora - ICFBF/BFM'
	        },
             labels: {
            formatter: function() {
				if(this.value>0)
				{
                 return Highcharts.numberFormat(this.value, 3);
				}
				else
				{
				 return 0;	
				}
            }
            }
	    },

	    credits: {
	    	enabled: false
	    },
	
	    series: [{
	        name: 'Speed',
	        data: [436.365],
	        dataLabels: {
	        	format: '<div style="text-align:center"><span style="font-size:15px;color:' + 
                    ((Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black') + '">{y:.3f}</span><br/>' + 
                   	'<span style="font-size:12px;color:silver">Registros/h</span></div>'
	        },
	        tooltip: {
	            valueSuffix: 'Registros/h'
	        }
	    }]
	
	}));
	
	
$('#container-speed2').highcharts(Highcharts.merge(gaugeOptions, {
        yAxis: {
	        min: 0,
	        max: 500.000,
	        title: {
	            text: 'Registros / Hora - ICFBF/BFF '
	        },
             labels: {
            formatter: function() {

				if(this.value>0)
				{
                 return Highcharts.numberFormat(this.value, 3);
				}
				else
				{
				 return 0;	
				}


            }
            }
	    },

	    credits: {
	    	enabled: false
	    },
	
	    series: [{
	        name: 'Speed',
	        data: [436.365],
	        dataLabels: {
	        	format: '<div style="text-align:center"><span style="font-size:15px;color:' + 
                    ((Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black') + '">{y:.3f}</span><br/>' + 
                   	'<span style="font-size:12px;color:silver">Registros/h</span></div>'
	        },
	        tooltip: {
	            valueSuffix: 'Registros/h'
	        }
	    }]
	
	}));
    
                              
    // Bring life to the dials
    setInterval(function () {
    	// Speed
        var chart = $('#container-speed').highcharts();
        if (chart) {
            var point = chart.series[0].points[0],
                newVal,
                inc =  Math.ceil(Math.random() * 500000);
            
            newVal =inc;
            if (newVal < 0) {
                newVal = 0;
            }
			
		    if (newVal > 500000) {
                newVal = 500000;
            }
            newVal=Number(moeda.formatar(newVal));
            point.update(newVal);
        }


    }, 2000);  
	
	
 setInterval(function () {
    	// Speed
        var chart = $('#container-speed2').highcharts();
        if (chart) {
            var point = chart.series[0].points[0],
                newVal,
                inc =  Math.ceil(Math.random() * 500000);
            
            newVal =inc;
            if (newVal < 0) {
                newVal = 0;
            }
			
		    if (newVal > 500000) {
                newVal = 500000;
            }
            newVal=Number(moeda.formatar(newVal));
            point.update(newVal);
        }


    }, 2000);  
    
	
});

</script>

<style>
.highcharts-title{ font-size:11px!important}
.pizza text[text-anchor]{ display:none!important}
.pizza text.highcharts-title{ display:none!important}
text[text-anchor="end"]{ visibility:hidden!important}
g text[text-anchor="end"]{ visibility:visible!important}

.highcharts-yaxis-title{ font-size:11px}


.highcharts-data-labels span{ font-size:10px!important}

</style>