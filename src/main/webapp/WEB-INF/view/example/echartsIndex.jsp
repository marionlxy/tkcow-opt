<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<%@ include file="/include.jsp"%>
<title>Echarts展示</title>
</head>

<body>
    
	<!--Step:1 Prepare a dom for ECharts which (must) has size (width & hight)-->
	<!--Step:1 为ECharts准备一个具备大小（宽高）的Dom-->
	<div id="main" style="height: 500px; border: 1px solid #ccc; padding: 10px;"></div>
	<div id="mainMap" style="height: 500px; border: 1px solid #ccc; padding: 10px;"></div>
	<div id="heatMap" style="height: 500px; border: 1px solid #ccc; padding: 10px;"></div>
	<div id="Pm25Map" style="height: 500px; border: 1px solid #ccc; padding: 10px;"></div>
	<div id="gaugeMap" style="height: 400px; border: 1px solid #ccc; padding: 10px;"></div>
	<div id="treeMap" style="height: 600px; border: 1px solid #ccc; padding: 10px;"></div>
	<div id="dymcMap" style="height: 500px; border: 1px solid #ccc; padding: 10px;"></div>
	<!--Step:2 Import echarts.js-->
	<!--Step:2 引入echarts.js-->
	<script type="text/javascript" src="<%=basePath%>/static/js/echarts/echarts.js"></script>
	<script type="text/javascript" src="<%=basePath%>/static/js/echarts/chart/bar.js"></script>
	<script type="text/javascript" src="<%=basePath%>/static/js/echarts/chart/chord.js"></script>
	<script type="text/javascript" src="<%=basePath%>/static/js/echarts/chart/eventRiver.js"></script>
	<script type="text/javascript" src="<%=basePath%>/static/js/echarts/chart/force.js"></script>
	<script type="text/javascript" src="<%=basePath%>/static/js/echarts/chart/funnel.js"></script>
	<script type="text/javascript" src="<%=basePath%>/static/js/echarts/chart/gauge.js"></script>
	<script type="text/javascript" src="<%=basePath%>/static/js/echarts/chart/heatmap.js"></script>
	<script type="text/javascript" src="<%=basePath%>/static/js/echarts/chart/k.js"></script>
	<script type="text/javascript" src="<%=basePath%>/static/js/echarts/chart/line.js"></script>
	<script type="text/javascript" src="<%=basePath%>/static/js/echarts/chart/map.js"></script>
	<script type="text/javascript" src="<%=basePath%>/static/js/echarts/chart/pie.js"></script>
	<script type="text/javascript" src="<%=basePath%>/static/js/echarts/chart/radar.js"></script>
	<script type="text/javascript" src="<%=basePath%>/static/js/echarts/chart/scatter.js"></script>
	<script type="text/javascript" src="<%=basePath%>/static/js/echarts/chart/tree.js"></script>
	<script type="text/javascript" src="<%=basePath%>/static/js/echarts/chart/treemap.js"></script>
	<script type="text/javascript" src="<%=basePath%>/static/js/echarts/chart/venn.js"></script>
	<script type="text/javascript" src="<%=basePath%>/static/js/echarts/chart/wordCloud.js"></script>

	<script type="text/javascript">
    // Step:3 conifg ECharts's path, link to echarts.js from current page.
    // Step:3 为模块加载器配置echarts的路径，从当前页面链接到echarts.js，定义所需图表路径
    require.config({
        paths: {
            echarts: './js'
        }
    });
    
    // Step:4 require echarts and use it in the callback.
    // Step:4 动态加载echarts然后在回调函数中开始使用，注意保持按需加载结构定义图表路径
    require(
        [
            'echarts',
            'echarts/chart/bar',
        	'echarts/chart/chord',
        	'echarts/chart/eventRiver',
        	'echarts/chart/force',
        	'echarts/chart/funnel',
        	'echarts/chart/gauge',
        	'echarts/chart/heatmap',
        	'echarts/chart/k',
        	'echarts/chart/line',
        	'echarts/chart/map',
        	'echarts/chart/pie',
        	'echarts/chart/radar',
        	'echarts/chart/scatter',
        	'echarts/chart/tree',
        	'echarts/chart/treemap',
        	'echarts/chart/venn',
        	'echarts/chart/wordCloud'
        ],
        function (ec) {
            //--- 折柱 ---
            var myChart = ec.init(document.getElementById('main'));
            myChart.setOption({
                tooltip : {
                    trigger: 'axis'
                },
                legend: {
                    data:['蒸发量','降水量']
                },
                toolbox: {
                    show : true,
                    feature : {
                        mark : {show: true},
                        dataView : {show: true, readOnly: false},
                        magicType : {show: true, type: ['line', 'bar']},
                        restore : {show: true},
                        saveAsImage : {show: true}
                    }
                },
                calculable : true,
                xAxis : [
                    {
                        type : 'category',
                        data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
                    }
                ],
                yAxis : [
                    {
                        type : 'value',
                        splitArea : {show : true}
                    }
                ],
                series : [
                    {
                        name:'蒸发量',
                        type:'bar',
                        data:[2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3]
                    },
                    {
                        name:'降水量',
                        type:'bar',
                        data:[2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3]
                    }
                ]
            });
            
            // --- 地图 ---
            var myChart2 = ec.init(document.getElementById('mainMap'));
            myChart2.setOption({
                tooltip : {
                    trigger: 'item',
                    formatter: '{b}'
                },
                series : [
                    {
                        name: '中国',
                        type: 'map',
                        mapType: 'china',
                        selectedMode : 'multiple',
                        itemStyle:{
                            normal:{label:{show:true}},
                            emphasis:{label:{show:true}}
                        },
                        data:[
                            {name:'广东',selected:true}
                        ]
                    }
                ]
            });
            
            // --- 热图 ---
            var myChart3 = ec.init(document.getElementById('heatMap'));
            myChart3.setOption({
            	    title : {
            	        text: '人物关系：乔布斯',
            	        subtext: '数据',
            	        x:'right',
            	        y:'bottom'
            	    },
            	    tooltip : {
            	        trigger: 'item',
            	        formatter: '{a} : {b}'
            	    },
            	    toolbox: {
            	        show : true,
            	        feature : {
            	            restore : {show: true},
            	            magicType: {show: true, type: ['force', 'chord']},
            	            saveAsImage : {show: true}
            	        }
            	    },
            	    legend: {
            	        x: 'left',
            	        data:['家人','朋友']
            	    },
            	    series : [
            	        {
            	            type:'force',
            	            name : "人物关系",
            	            ribbonType: false,
            	            categories : [
            	                {
            	                    name: '人物'
            	                },
            	                {
            	                    name: '家人',
            	                    symbol: 'diamond'
            	                },
            	                {
            	                    name:'朋友'
            	                }
            	            ],
            	            itemStyle: {
            	                normal: {
            	                    label: {
            	                        show: true,
            	                        textStyle: {
            	                            color: '#333'
            	                        }
            	                    },
            	                    nodeStyle : {
            	                        brushType : 'both',
            	                        borderColor : 'rgba(255,215,0,0.4)',
            	                        borderWidth : 1
            	                    }
            	                },
            	                emphasis: {
            	                    label: {
            	                        show: false
            	                        // textStyle: null      // 默认使用全局文本样式，详见TEXTSTYLE
            	                    },
            	                    nodeStyle : {
            	                        //r: 30
            	                    },
            	                    linkStyle : {}
            	                }
            	            },
            	            minRadius : 15,
            	            maxRadius : 25,
            	            gravity: 1.1,
            	            scaling: 1.2,
            	            draggable: false,
            	            linkSymbol: 'arrow',
            	            steps: 10,
            	            coolDown: 0.9,
            	            //preventOverlap: true,
            	            nodes:[
            	                {
            	                    category:0, name: '乔布斯', value : 10,
            	                    //symbol: 'image://http://www.damndigital.com/wp-content/uploads/2010/12/steve-jobs.jpg',
            	                    symbol: 'image://<%=basePath%>/static/images/echarts/steve-jobs.jpg',
            	                    symbolSize: [60, 35],
            	                    draggable: true,
            	                    itemStyle: {
            	                        normal: {
            	                            label: {
            	                                position: 'right',
            	                                textStyle: {
            	                                    color: 'black'
            	                                }
            	                            }
            	                        }
            	                    }
            	                },
            	                {category:1, name: '丽萨-乔布斯',value : 2},
            	                {category:1, name: '保罗-乔布斯',value : 3},
            	                {category:1, name: '克拉拉-乔布斯',value : 3},
            	                {category:1, name: '劳伦-鲍威尔',value : 7},
            	                {category:2, name: '史蒂夫-沃兹尼艾克',value : 5},
            	                {category:2, name: '奥巴马',value : 8},
            	                {category:2, name: '比尔-盖茨',value : 9},
            	                {category:2, name: '乔纳森-艾夫',value : 4},
            	                {category:2, name: '蒂姆-库克',value : 4},
            	                {category:2, name: '龙-韦恩',value : 1},
            	            ],
            	            links : [
            	                {source : '丽萨-乔布斯', target : '乔布斯', weight : 1, name: '女儿', itemStyle: {
            	                    normal: {
            	                        width: 1.5,
            	                        color: 'red'
            	                    }
            	                }},
            	                {source : '乔布斯', target : '丽萨-乔布斯', weight : 1, name: '父亲', itemStyle: {
            	                    normal: { color: 'red' }
            	                }},
            	                {source : '保罗-乔布斯', target : '乔布斯', weight : 2, name: '父亲'},
            	                {source : '克拉拉-乔布斯', target : '乔布斯', weight : 1, name: '母亲'},
            	                {source : '劳伦-鲍威尔', target : '乔布斯', weight : 2},
            	                {source : '史蒂夫-沃兹尼艾克', target : '乔布斯', weight : 3, name: '合伙人'},
            	                {source : '奥巴马', target : '乔布斯', weight : 1},
            	                {source : '比尔-盖茨', target : '乔布斯', weight : 6, name: '竞争对手'},
            	                {source : '乔纳森-艾夫', target : '乔布斯', weight : 1, name: '爱将'},
            	                {source : '蒂姆-库克', target : '乔布斯', weight : 1},
            	                {source : '龙-韦恩', target : '乔布斯', weight : 1},
            	                {source : '克拉拉-乔布斯', target : '保罗-乔布斯', weight : 1},
            	                {source : '奥巴马', target : '保罗-乔布斯', weight : 1},
            	                {source : '奥巴马', target : '克拉拉-乔布斯', weight : 1},
            	                {source : '奥巴马', target : '劳伦-鲍威尔', weight : 1},
            	                {source : '奥巴马', target : '史蒂夫-沃兹尼艾克', weight : 1},
            	                {source : '比尔-盖茨', target : '奥巴马', weight : 6},
            	                {source : '比尔-盖茨', target : '克拉拉-乔布斯', weight : 1},
            	                {source : '蒂姆-库克', target : '奥巴马', weight : 1}
            	            ]
            	        }
            	    ]
            	});
            
            
         	// --- 热图 ---
            var myChart4 = ec.init(document.getElementById('Pm25Map'));
            myChart4.setOption({
            	    title : {
            	        text: '全国主要城市空气质量（pm2.5）',
            	        subtext: 'data from PM25.in',
            	        sublink: 'http://www.pm25.in',
            	        x:'center'
            	    },
            	    tooltip : {
            	        trigger: 'item'
            	    },
            	    legend: {
            	        orient: 'vertical',
            	        x:'left',
            	        data:['pm2.5']
            	    },
            	    dataRange: {
            	        min : 0,
            	        max : 500,
            	        calculable : true,
            	        color: ['maroon','purple','red','orange','yellow','lightgreen']
            	    },
            	    toolbox: {
            	        show : true,
            	        orient : 'vertical',
            	        x: 'right',
            	        y: 'center',
            	        feature : {
            	            mark : {show: true},
            	            dataView : {show: true, readOnly: false},
            	            restore : {show: true},
            	            saveAsImage : {show: true}
            	        }
            	    },
            	    series : [
            	        {
            	            name: 'pm2.5',
            	            type: 'map',
            	            mapType: 'china',
            	            hoverable: false,
            	            roam:true,
            	            data : [],
            	            markPoint : {
            	                symbolSize: 5,       // 标注大小，半宽（半径）参数，当图形为方向或菱形则总宽度为symbolSize * 2
            	                itemStyle: {
            	                    normal: {
            	                        borderColor: '#87cefa',
            	                        borderWidth: 1,            // 标注边线线宽，单位px，默认为1
            	                        label: {
            	                            show: false
            	                        }
            	                    },
            	                    emphasis: {
            	                        borderColor: '#1e90ff',
            	                        borderWidth: 5,
            	                        label: {
            	                            show: false
            	                        }
            	                    }
            	                },
            	                data : [
            	                    {name: "海门", value: 9},
            	                    {name: "鄂尔多斯", value: 12},
            	                    {name: "招远", value: 12},
            	                    {name: "舟山", value: 12},
            	                    {name: "齐齐哈尔", value: 14},
            	                    {name: "盐城", value: 15},
            	                    {name: "赤峰", value: 16},
            	                    {name: "青岛", value: 18},
            	                    {name: "乳山", value: 18},
            	                    {name: "金昌", value: 19},
            	                    {name: "泉州", value: 21},
            	                    {name: "莱西", value: 21},
            	                    {name: "日照", value: 21},
            	                    {name: "胶南", value: 22},
            	                    {name: "南通", value: 23},
            	                    {name: "拉萨", value: 24},
            	                    {name: "云浮", value: 24},
            	                    {name: "梅州", value: 25},
            	                    {name: "文登", value: 25},
            	                    {name: "上海", value: 25},
            	                    {name: "攀枝花", value: 25},
            	                    {name: "威海", value: 25},
            	                    {name: "承德", value: 25},
            	                    {name: "厦门", value: 26},
            	                    {name: "汕尾", value: 26},
            	                    {name: "潮州", value: 26},
            	                    {name: "丹东", value: 27},
            	                    {name: "太仓", value: 27},
            	                    {name: "曲靖", value: 27},
            	                    {name: "烟台", value: 28},
            	                    {name: "福州", value: 29},
            	                    {name: "瓦房店", value: 30},
            	                    {name: "即墨", value: 30},
            	                    {name: "抚顺", value: 31},
            	                    {name: "玉溪", value: 31},
            	                    {name: "张家口", value: 31},
            	                    {name: "阳泉", value: 31},
            	                    {name: "莱州", value: 32},
            	                    {name: "湖州", value: 32},
            	                    {name: "汕头", value: 32},
            	                    {name: "昆山", value: 33},
            	                    {name: "宁波", value: 33},
            	                    {name: "湛江", value: 33},
            	                    {name: "揭阳", value: 34},
            	                    {name: "荣成", value: 34},
            	                    {name: "连云港", value: 35},
            	                    {name: "葫芦岛", value: 35},
            	                    {name: "常熟", value: 36},
            	                    {name: "东莞", value: 36},
            	                    {name: "河源", value: 36},
            	                    {name: "淮安", value: 36},
            	                    {name: "泰州", value: 36},
            	                    {name: "南宁", value: 37},
            	                    {name: "营口", value: 37},
            	                    {name: "惠州", value: 37},
            	                    {name: "江阴", value: 37},
            	                    {name: "蓬莱", value: 37},
            	                    {name: "韶关", value: 38},
            	                    {name: "嘉峪关", value: 38},
            	                    {name: "广州", value: 38},
            	                    {name: "延安", value: 38},
            	                    {name: "太原", value: 39},
            	                    {name: "清远", value: 39},
            	                    {name: "中山", value: 39},
            	                    {name: "昆明", value: 39},
            	                    {name: "寿光", value: 40},
            	                    {name: "盘锦", value: 40},
            	                    {name: "长治", value: 41},
            	                    {name: "深圳", value: 41},
            	                    {name: "珠海", value: 42},
            	                    {name: "宿迁", value: 43},
            	                    {name: "咸阳", value: 43},
            	                    {name: "铜川", value: 44},
            	                    {name: "平度", value: 44},
            	                    {name: "佛山", value: 44},
            	                    {name: "海口", value: 44},
            	                    {name: "江门", value: 45},
            	                    {name: "章丘", value: 45},
            	                    {name: "肇庆", value: 46},
            	                    {name: "大连", value: 47},
            	                    {name: "临汾", value: 47},
            	                    {name: "吴江", value: 47},
            	                    {name: "石嘴山", value: 49},
            	                    {name: "沈阳", value: 50},
            	                    {name: "苏州", value: 50},
            	                    {name: "茂名", value: 50},
            	                    {name: "嘉兴", value: 51},
            	                    {name: "长春", value: 51},
            	                    {name: "胶州", value: 52},
            	                    {name: "银川", value: 52},
            	                    {name: "张家港", value: 52},
            	                    {name: "三门峡", value: 53},
            	                    {name: "锦州", value: 54},
            	                    {name: "南昌", value: 54},
            	                    {name: "柳州", value: 54},
            	                    {name: "三亚", value: 54},
            	                    {name: "自贡", value: 56},
            	                    {name: "吉林", value: 56},
            	                    {name: "阳江", value: 57},
            	                    {name: "泸州", value: 57},
            	                    {name: "西宁", value: 57},
            	                    {name: "宜宾", value: 58},
            	                    {name: "呼和浩特", value: 58},
            	                    {name: "成都", value: 58},
            	                    {name: "大同", value: 58},
            	                    {name: "镇江", value: 59},
            	                    {name: "桂林", value: 59},
            	                    {name: "张家界", value: 59},
            	                    {name: "宜兴", value: 59},
            	                    {name: "北海", value: 60},
            	                    {name: "西安", value: 61},
            	                    {name: "金坛", value: 62},
            	                    {name: "东营", value: 62},
            	                    {name: "牡丹江", value: 63},
            	                    {name: "遵义", value: 63},
            	                    {name: "绍兴", value: 63},
            	                    {name: "扬州", value: 64},
            	                    {name: "常州", value: 64},
            	                    {name: "潍坊", value: 65},
            	                    {name: "重庆", value: 66},
            	                    {name: "台州", value: 67},
            	                    {name: "南京", value: 67},
            	                    {name: "滨州", value: 70},
            	                    {name: "贵阳", value: 71},
            	                    {name: "无锡", value: 71},
            	                    {name: "本溪", value: 71},
            	                    {name: "克拉玛依", value: 72},
            	                    {name: "渭南", value: 72},
            	                    {name: "马鞍山", value: 72},
            	                    {name: "宝鸡", value: 72},
            	                    {name: "焦作", value: 75},
            	                    {name: "句容", value: 75},
            	                    {name: "北京", value: 79},
            	                    {name: "徐州", value: 79},
            	                    {name: "衡水", value: 80},
            	                    {name: "包头", value: 80},
            	                    {name: "绵阳", value: 80},
            	                    {name: "乌鲁木齐", value: 84},
            	                    {name: "枣庄", value: 84},
            	                    {name: "杭州", value: 84},
            	                    {name: "淄博", value: 85},
            	                    {name: "鞍山", value: 86},
            	                    {name: "溧阳", value: 86},
            	                    {name: "库尔勒", value: 86},
            	                    {name: "安阳", value: 90},
            	                    {name: "开封", value: 90},
            	                    {name: "济南", value: 92},
            	                    {name: "德阳", value: 93},
            	                    {name: "温州", value: 95},
            	                    {name: "九江", value: 96},
            	                    {name: "邯郸", value: 98},
            	                    {name: "临安", value: 99},
            	                    {name: "兰州", value: 99},
            	                    {name: "沧州", value: 100},
            	                    {name: "临沂", value: 103},
            	                    {name: "南充", value: 104},
            	                    {name: "天津", value: 105},
            	                    {name: "富阳", value: 106},
            	                    {name: "泰安", value: 112},
            	                    {name: "诸暨", value: 112},
            	                    {name: "郑州", value: 113},
            	                    {name: "哈尔滨", value: 114},
            	                    {name: "聊城", value: 116},
            	                    {name: "芜湖", value: 117},
            	                    {name: "唐山", value: 119},
            	                    {name: "平顶山", value: 119},
            	                    {name: "邢台", value: 119},
            	                    {name: "德州", value: 120},
            	                    {name: "济宁", value: 120},
            	                    {name: "荆州", value: 127},
            	                    {name: "宜昌", value: 130},
            	                    {name: "义乌", value: 132},
            	                    {name: "丽水", value: 133},
            	                    {name: "洛阳", value: 134},
            	                    {name: "秦皇岛", value: 136},
            	                    {name: "株洲", value: 143},
            	                    {name: "石家庄", value: 147},
            	                    {name: "莱芜", value: 148},
            	                    {name: "常德", value: 152},
            	                    {name: "保定", value: 153},
            	                    {name: "湘潭", value: 154},
            	                    {name: "金华", value: 157},
            	                    {name: "岳阳", value: 169},
            	                    {name: "长沙", value: 175},
            	                    {name: "衢州", value: 177},
            	                    {name: "廊坊", value: 193},
            	                    {name: "菏泽", value: 194},
            	                    {name: "合肥", value: 229},
            	                    {name: "武汉", value: 273},
            	                    {name: "大庆", value: 279}
            	                ]
            	            },
            	            geoCoord: {
            	                "海门":[121.15,31.89],
            	                "鄂尔多斯":[109.781327,39.608266],
            	                "招远":[120.38,37.35],
            	                "舟山":[122.207216,29.985295],
            	                "齐齐哈尔":[123.97,47.33],
            	                "盐城":[120.13,33.38],
            	                "赤峰":[118.87,42.28],
            	                "青岛":[120.33,36.07],
            	                "乳山":[121.52,36.89],
            	                "金昌":[102.188043,38.520089],
            	                "泉州":[118.58,24.93],
            	                "莱西":[120.53,36.86],
            	                "日照":[119.46,35.42],
            	                "胶南":[119.97,35.88],
            	                "南通":[121.05,32.08],
            	                "拉萨":[91.11,29.97],
            	                "云浮":[112.02,22.93],
            	                "梅州":[116.1,24.55],
            	                "文登":[122.05,37.2],
            	                "上海":[121.48,31.22],
            	                "攀枝花":[101.718637,26.582347],
            	                "威海":[122.1,37.5],
            	                "承德":[117.93,40.97],
            	                "厦门":[118.1,24.46],
            	                "汕尾":[115.375279,22.786211],
            	                "潮州":[116.63,23.68],
            	                "丹东":[124.37,40.13],
            	                "太仓":[121.1,31.45],
            	                "曲靖":[103.79,25.51],
            	                "烟台":[121.39,37.52],
            	                "福州":[119.3,26.08],
            	                "瓦房店":[121.979603,39.627114],
            	                "即墨":[120.45,36.38],
            	                "抚顺":[123.97,41.97],
            	                "玉溪":[102.52,24.35],
            	                "张家口":[114.87,40.82],
            	                "阳泉":[113.57,37.85],
            	                "莱州":[119.942327,37.177017],
            	                "湖州":[120.1,30.86],
            	                "汕头":[116.69,23.39],
            	                "昆山":[120.95,31.39],
            	                "宁波":[121.56,29.86],
            	                "湛江":[110.359377,21.270708],
            	                "揭阳":[116.35,23.55],
            	                "荣成":[122.41,37.16],
            	                "连云港":[119.16,34.59],
            	                "葫芦岛":[120.836932,40.711052],
            	                "常熟":[120.74,31.64],
            	                "东莞":[113.75,23.04],
            	                "河源":[114.68,23.73],
            	                "淮安":[119.15,33.5],
            	                "泰州":[119.9,32.49],
            	                "南宁":[108.33,22.84],
            	                "营口":[122.18,40.65],
            	                "惠州":[114.4,23.09],
            	                "江阴":[120.26,31.91],
            	                "蓬莱":[120.75,37.8],
            	                "韶关":[113.62,24.84],
            	                "嘉峪关":[98.289152,39.77313],
            	                "广州":[113.23,23.16],
            	                "延安":[109.47,36.6],
            	                "太原":[112.53,37.87],
            	                "清远":[113.01,23.7],
            	                "中山":[113.38,22.52],
            	                "昆明":[102.73,25.04],
            	                "寿光":[118.73,36.86],
            	                "盘锦":[122.070714,41.119997],
            	                "长治":[113.08,36.18],
            	                "深圳":[114.07,22.62],
            	                "珠海":[113.52,22.3],
            	                "宿迁":[118.3,33.96],
            	                "咸阳":[108.72,34.36],
            	                "铜川":[109.11,35.09],
            	                "平度":[119.97,36.77],
            	                "佛山":[113.11,23.05],
            	                "海口":[110.35,20.02],
            	                "江门":[113.06,22.61],
            	                "章丘":[117.53,36.72],
            	                "肇庆":[112.44,23.05],
            	                "大连":[121.62,38.92],
            	                "临汾":[111.5,36.08],
            	                "吴江":[120.63,31.16],
            	                "石嘴山":[106.39,39.04],
            	                "沈阳":[123.38,41.8],
            	                "苏州":[120.62,31.32],
            	                "茂名":[110.88,21.68],
            	                "嘉兴":[120.76,30.77],
            	                "长春":[125.35,43.88],
            	                "胶州":[120.03336,36.264622],
            	                "银川":[106.27,38.47],
            	                "张家港":[120.555821,31.875428],
            	                "三门峡":[111.19,34.76],
            	                "锦州":[121.15,41.13],
            	                "南昌":[115.89,28.68],
            	                "柳州":[109.4,24.33],
            	                "三亚":[109.511909,18.252847],
            	                "自贡":[104.778442,29.33903],
            	                "吉林":[126.57,43.87],
            	                "阳江":[111.95,21.85],
            	                "泸州":[105.39,28.91],
            	                "西宁":[101.74,36.56],
            	                "宜宾":[104.56,29.77],
            	                "呼和浩特":[111.65,40.82],
            	                "成都":[104.06,30.67],
            	                "大同":[113.3,40.12],
            	                "镇江":[119.44,32.2],
            	                "桂林":[110.28,25.29],
            	                "张家界":[110.479191,29.117096],
            	                "宜兴":[119.82,31.36],
            	                "北海":[109.12,21.49],
            	                "西安":[108.95,34.27],
            	                "金坛":[119.56,31.74],
            	                "东营":[118.49,37.46],
            	                "牡丹江":[129.58,44.6],
            	                "遵义":[106.9,27.7],
            	                "绍兴":[120.58,30.01],
            	                "扬州":[119.42,32.39],
            	                "常州":[119.95,31.79],
            	                "潍坊":[119.1,36.62],
            	                "重庆":[106.54,29.59],
            	                "台州":[121.420757,28.656386],
            	                "南京":[118.78,32.04],
            	                "滨州":[118.03,37.36],
            	                "贵阳":[106.71,26.57],
            	                "无锡":[120.29,31.59],
            	                "本溪":[123.73,41.3],
            	                "克拉玛依":[84.77,45.59],
            	                "渭南":[109.5,34.52],
            	                "马鞍山":[118.48,31.56],
            	                "宝鸡":[107.15,34.38],
            	                "焦作":[113.21,35.24],
            	                "句容":[119.16,31.95],
            	                "北京":[116.46,39.92],
            	                "徐州":[117.2,34.26],
            	                "衡水":[115.72,37.72],
            	                "包头":[110,40.58],
            	                "绵阳":[104.73,31.48],
            	                "乌鲁木齐":[87.68,43.77],
            	                "枣庄":[117.57,34.86],
            	                "杭州":[120.19,30.26],
            	                "淄博":[118.05,36.78],
            	                "鞍山":[122.85,41.12],
            	                "溧阳":[119.48,31.43],
            	                "库尔勒":[86.06,41.68],
            	                "安阳":[114.35,36.1],
            	                "开封":[114.35,34.79],
            	                "济南":[117,36.65],
            	                "德阳":[104.37,31.13],
            	                "温州":[120.65,28.01],
            	                "九江":[115.97,29.71],
            	                "邯郸":[114.47,36.6],
            	                "临安":[119.72,30.23],
            	                "兰州":[103.73,36.03],
            	                "沧州":[116.83,38.33],
            	                "临沂":[118.35,35.05],
            	                "南充":[106.110698,30.837793],
            	                "天津":[117.2,39.13],
            	                "富阳":[119.95,30.07],
            	                "泰安":[117.13,36.18],
            	                "诸暨":[120.23,29.71],
            	                "郑州":[113.65,34.76],
            	                "哈尔滨":[126.63,45.75],
            	                "聊城":[115.97,36.45],
            	                "芜湖":[118.38,31.33],
            	                "唐山":[118.02,39.63],
            	                "平顶山":[113.29,33.75],
            	                "邢台":[114.48,37.05],
            	                "德州":[116.29,37.45],
            	                "济宁":[116.59,35.38],
            	                "荆州":[112.239741,30.335165],
            	                "宜昌":[111.3,30.7],
            	                "义乌":[120.06,29.32],
            	                "丽水":[119.92,28.45],
            	                "洛阳":[112.44,34.7],
            	                "秦皇岛":[119.57,39.95],
            	                "株洲":[113.16,27.83],
            	                "石家庄":[114.48,38.03],
            	                "莱芜":[117.67,36.19],
            	                "常德":[111.69,29.05],
            	                "保定":[115.48,38.85],
            	                "湘潭":[112.91,27.87],
            	                "金华":[119.64,29.12],
            	                "岳阳":[113.09,29.37],
            	                "长沙":[113,28.21],
            	                "衢州":[118.88,28.97],
            	                "廊坊":[116.7,39.53],
            	                "菏泽":[115.480656,35.23375],
            	                "合肥":[117.27,31.86],
            	                "武汉":[114.31,30.52],
            	                "大庆":[125.03,46.58]
            	            }
            	        },
            	        {
            	            name: 'Top5',
            	            type: 'map',
            	            mapType: 'china',
            	            data:[],
            	            markPoint : {
            	                symbol:'emptyCircle',
            	                symbolSize : function (v){
            	                    return 10 + v/100
            	                },
            	                effect : {
            	                    show: true,
            	                    shadowBlur : 0
            	                },
            	                itemStyle:{
            	                    normal:{
            	                        label:{show:false}
            	                    }
            	                },
            	                data : [
            	                    {name: "廊坊", value: 193},
            	                    {name: "菏泽", value: 194},
            	                    {name: "合肥", value: 229},
            	                    {name: "武汉", value: 273},
            	                    {name: "大庆", value: 279}
            	                ]
            	            }
            	        }
            	    ]
            	});
           
            var timeTicket;
            // --- 仪表图 ---
            var myChart4 = ec.init(document.getElementById('gaugeMap'));
            myChart4.setOption({
            	    backgroundColor: '#1b1b1b',
            	    tooltip : {
            	        formatter: "{a} <br/>{c} {b}"
            	    },
            	    toolbox: {
            	        show : true,
            	        feature : {
            	            mark : {show: true},
            	            restore : {show: true},
            	            saveAsImage : {show: true}
            	        }
            	    },
            	    series : [
            	        {
            	            name:'速度',
            	            type:'gauge',
            	            min:0,
            	            max:220,
            	            splitNumber:11,
            	            axisLine: {            // 坐标轴线
            	                lineStyle: {       // 属性lineStyle控制线条样式
            	                    color: [[0.09, 'lime'],[0.82, '#1e90ff'],[1, '#ff4500']],
            	                    width: 3,
            	                    shadowColor : '#fff', //默认透明
            	                    shadowBlur: 10
            	                }
            	            },
            	            axisLabel: {            // 坐标轴小标记
            	                textStyle: {       // 属性lineStyle控制线条样式
            	                    fontWeight: 'bolder',
            	                    color: '#fff',
            	                    shadowColor : '#fff', //默认透明
            	                    shadowBlur: 10
            	                }
            	            },
            	            axisTick: {            // 坐标轴小标记
            	                length :15,        // 属性length控制线长
            	                lineStyle: {       // 属性lineStyle控制线条样式
            	                    color: 'auto',
            	                    shadowColor : '#fff', //默认透明
            	                    shadowBlur: 10
            	                }
            	            },
            	            splitLine: {           // 分隔线
            	                length :25,         // 属性length控制线长
            	                lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
            	                    width:3,
            	                    color: '#fff',
            	                    shadowColor : '#fff', //默认透明
            	                    shadowBlur: 10
            	                }
            	            },
            	            pointer: {           // 分隔线
            	                shadowColor : '#fff', //默认透明
            	                shadowBlur: 5
            	            },
            	            title : {
            	                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
            	                    fontWeight: 'bolder',
            	                    fontSize: 20,
            	                    fontStyle: 'italic',
            	                    color: '#fff',
            	                    shadowColor : '#fff', //默认透明
            	                    shadowBlur: 10
            	                }
            	            },
            	            detail : {
            	                backgroundColor: 'rgba(30,144,255,0.8)',
            	                borderWidth: 1,
            	                borderColor: '#fff',
            	                shadowColor : '#fff', //默认透明
            	                shadowBlur: 5,
            	                offsetCenter: [0, '50%'],       // x, y，单位px
            	                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
            	                    fontWeight: 'bolder',
            	                    color: '#fff'
            	                }
            	            },
            	            data:[{value: 40, name: 'km/h'}]
            	        },
            	        {
            	            name:'转速',
            	            type:'gauge',
            	            center : ['25%', '55%'],    // 默认全局居中
            	            radius : '50%',
            	            min:0,
            	            max:7,
            	            endAngle:45,
            	            splitNumber:7,
            	            axisLine: {            // 坐标轴线
            	                lineStyle: {       // 属性lineStyle控制线条样式
            	                    color: [[0.29, 'lime'],[0.86, '#1e90ff'],[1, '#ff4500']],
            	                    width: 2,
            	                    shadowColor : '#fff', //默认透明
            	                    shadowBlur: 10
            	                }
            	            },
            	            axisLabel: {            // 坐标轴小标记
            	                textStyle: {       // 属性lineStyle控制线条样式
            	                    fontWeight: 'bolder',
            	                    color: '#fff',
            	                    shadowColor : '#fff', //默认透明
            	                    shadowBlur: 10
            	                }
            	            },
            	            axisTick: {            // 坐标轴小标记
            	                length :12,        // 属性length控制线长
            	                lineStyle: {       // 属性lineStyle控制线条样式
            	                    color: 'auto',
            	                    shadowColor : '#fff', //默认透明
            	                    shadowBlur: 10
            	                }
            	            },
            	            splitLine: {           // 分隔线
            	                length :20,         // 属性length控制线长
            	                lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
            	                    width:3,
            	                    color: '#fff',
            	                    shadowColor : '#fff', //默认透明
            	                    shadowBlur: 10
            	                }
            	            },
            	            pointer: {
            	                width:5,
            	                shadowColor : '#fff', //默认透明
            	                shadowBlur: 5
            	            },
            	            title : {
            	                offsetCenter: [0, '-30%'],       // x, y，单位px
            	                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
            	                    fontWeight: 'bolder',
            	                    fontStyle: 'italic',
            	                    color: '#fff',
            	                    shadowColor : '#fff', //默认透明
            	                    shadowBlur: 10
            	                }
            	            },
            	            detail : {
            	                //backgroundColor: 'rgba(30,144,255,0.8)',
            	               // borderWidth: 1,
            	                borderColor: '#fff',
            	                shadowColor : '#fff', //默认透明
            	                shadowBlur: 5,
            	                width: 80,
            	                height:30,
            	                offsetCenter: [25, '20%'],       // x, y，单位px
            	                textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
            	                    fontWeight: 'bolder',
            	                    color: '#fff'
            	                }
            	            },
            	            data:[{value: 1.5, name: 'x1000 r/min'}]
            	        },
            	        {
            	            name:'油表',
            	            type:'gauge',
            	            center : ['75%', '50%'],    // 默认全局居中
            	            radius : '50%',
            	            min:0,
            	            max:2,
            	            startAngle:135,
            	            endAngle:45,
            	            splitNumber:2,
            	            axisLine: {            // 坐标轴线
            	                lineStyle: {       // 属性lineStyle控制线条样式
            	                    color: [[0.2, 'lime'],[0.8, '#1e90ff'],[1, '#ff4500']],
            	                    width: 2,
            	                    shadowColor : '#fff', //默认透明
            	                    shadowBlur: 10
            	                }
            	            },
            	            axisTick: {            // 坐标轴小标记
            	                length :12,        // 属性length控制线长
            	                lineStyle: {       // 属性lineStyle控制线条样式
            	                    color: 'auto',
            	                    shadowColor : '#fff', //默认透明
            	                    shadowBlur: 10
            	                }
            	            },
            	            axisLabel: {
            	                textStyle: {       // 属性lineStyle控制线条样式
            	                    fontWeight: 'bolder',
            	                    color: '#fff',
            	                    shadowColor : '#fff', //默认透明
            	                    shadowBlur: 10
            	                },
            	                formatter:function(v){
            	                    switch (v + '') {
            	                        case '0' : return 'E';
            	                        case '1' : return 'Gas';
            	                        case '2' : return 'F';
            	                    }
            	                }
            	            },
            	            splitLine: {           // 分隔线
            	                length :15,         // 属性length控制线长
            	                lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
            	                    width:3,
            	                    color: '#fff',
            	                    shadowColor : '#fff', //默认透明
            	                    shadowBlur: 10
            	                }
            	            },
            	            pointer: {
            	                width:2,
            	                shadowColor : '#fff', //默认透明
            	                shadowBlur: 5
            	            },
            	            title : {
            	                show: false
            	            },
            	            detail : {
            	                show: false
            	            },
            	            data:[{value: 0.5, name: 'gas'}]
            	        },
            	        {
            	            name:'水表',
            	            type:'gauge',
            	            center : ['75%', '50%'],    // 默认全局居中
            	            radius : '50%',
            	            min:0,
            	            max:2,
            	            startAngle:315,
            	            endAngle:225,
            	            splitNumber:2,
            	            axisLine: {            // 坐标轴线
            	                lineStyle: {       // 属性lineStyle控制线条样式
            	                    color: [[0.2, 'lime'],[0.8, '#1e90ff'],[1, '#ff4500']],
            	                    width: 2,
            	                    shadowColor : '#fff', //默认透明
            	                    shadowBlur: 10
            	                }
            	            },
            	            axisTick: {            // 坐标轴小标记
            	                show: false
            	            },
            	            axisLabel: {
            	                textStyle: {       // 属性lineStyle控制线条样式
            	                    fontWeight: 'bolder',
            	                    color: '#fff',
            	                    shadowColor : '#fff', //默认透明
            	                    shadowBlur: 10
            	                },
            	                formatter:function(v){
            	                    switch (v + '') {
            	                        case '0' : return 'H';
            	                        case '1' : return 'Water';
            	                        case '2' : return 'C';
            	                    }
            	                }
            	            },
            	            splitLine: {           // 分隔线
            	                length :15,         // 属性length控制线长
            	                lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
            	                    width:3,
            	                    color: '#fff',
            	                    shadowColor : '#fff', //默认透明
            	                    shadowBlur: 10
            	                }
            	            },
            	            pointer: {
            	                width:2,
            	                shadowColor : '#fff', //默认透明
            	                shadowBlur: 5
            	            },
            	            title : {
            	                show: false
            	            },
            	            detail : {
            	                show: false
            	            },
            	            data:[{value: 0.5, name: 'gas'}]
            	        }
            	    ]
            	});

            	clearInterval(timeTicket);
            	timeTicket = setInterval(function (){
            	    option.series[0].data[0].value = (Math.random()*100).toFixed(2) - 0;
            	    option.series[1].data[0].value = (Math.random()*7).toFixed(2) - 0;
            	    option.series[2].data[0].value = (Math.random()*2).toFixed(2) - 0;
            	    option.series[3].data[0].value = (Math.random()*2).toFixed(2) - 0;
            	    myChart.setOption(option,true);
            	},2000)
            	                    
            
            	
            	//--- 折柱 ---
            var myChart = ec.init(document.getElementById('treeMap'));
            myChart.setOption({
            	    title : {
            	        text: '冰桶挑战'
            	    },
            	    toolbox: {
            	        show : true,
            	        feature : {
            	            mark : {show: true},
            	            dataView : {show: true, readOnly: false},
            	            restore : {show: true},
            	            saveAsImage : {show: true}
            	        }
            	    },
            	    series : [
            	        {
            	            name:'树图',
            	            type:'tree',
            	            orient: 'horizontal',  // vertical horizontal
            	            rootLocation: {x: 100,y: 230}, // 根节点位置  {x: 100, y: 'center'}
            	            nodePadding: 8,
            	            layerPadding: 200,
            	            hoverable: false,
            	            roam: true,
            	            symbolSize: 6,
            	            itemStyle: {
            	                normal: {
            	                    color: '#4883b4',
            	                    label: {
            	                        show: true,
            	                        position: 'right',
            	                        formatter: "{b}",
            	                        textStyle: {
            	                            color: '#000',
            	                            fontSize: 5
            	                        }
            	                    },
            	                    lineStyle: {
            	                        color: '#ccc',
            	                        type: 'curve' // 'curve'|'broken'|'solid'|'dotted'|'dashed'

            	                    }
            	                },
            	                emphasis: {
            	                    color: '#4883b4',
            	                    label: {
            	                        show: false
            	                    },
            	                    borderWidth: 0
            	                }
            	            },
            	            
            	            data: [
            	                {"name":"冰桶挑战","children":[{"name":"刘作虎","children":[{"name":"周鸿祎","children":[{"name":"马化腾"},{"name":"徐小平","children":[{"name":"牛文文","children":[{"name":"姚劲波","children":[{"name":"蔡文胜"},{"name":"蔡明"},{"name":"汪小菲"}]},{"name":"杨守彬","children":[{"name":"所有的创业者"},{"name":"所有的投资人"},{"name":"所有的创业服务机构"}]},{"name":"蒲易"}]},{"name":"罗振宇","children":[{"name":"罗辑思维25000名会员"}]},{"name":"黄西"}]},{"name":"黄章"}]},{"name":"罗永浩"},{"name":"刘江峰","children":[{"name":"何刚","children":[{"name":"谢清江"},{"name":"王翔"},{"name":"艾伟"}]},{"name":"王煜磊"}]}]},{"name":"雷军","children":[{"name":"刘德华","children":[{"name":"朗朗"},{"name":"苏桦伟"},{"name":"周杰伦","children":[{"name":"方文山","children":[{"name":"九把刀"},{"name":"柯有伦"}]},{"name":"五月天","children":[{"name":"谢金燕","children":[{"name":"赵慧仙"},{"name":"张菲","children":[{"name":"小S"}]},{"name":"郭富城"}]},{"name":"张震"},{"name":"金城武"}]}]}]},{"name":"李彦宏","children":[{"name":"俞敏洪"},{"name":"潘石屹","children":[{"name":"任志强"}]},{"name":"田亮","children":[{"name":"王岳伦"},{"name":"小沈阳"},{"name":"李小鹏"}]}]},{"name":"郭台铭","children":[{"name":"孙正义","children":[{"name":"宫坂学"}]},{"name":"谢晓亮"},{"name":"林志玲"}]}]},{"name":"古永锵","children":[{"name":"马云"},{"name":"王长田","children":[{"name":"邓超","children":[{"name":"俞白眉","children":[{"name":"姚晨","children":[{"name":"吴秀波"},{"name":"吴彦祖"},{"name":"孙红雷"}]},{"name":"朱芳雨","children":[{"name":"王仕鹏","children":[{"name":"易建联"}]}]},{"name":"梁超"}]}]},{"name":"刘亦菲"},{"name":"刘同"}]}]},{"name":"朱挺","children":[{"name":"张耀坤","children":[{"name":"姜宁"},{"name":"唐淼"}]},{"name":"周海滨","children":[{"name":"汪嵩","children":[{"name":"蔡贇"},{"name":"李易峰"},{"name":"王弢 "}]},{"name":"邵佳一"},{"name":"高迪","children":[{"name":"莫雷诺"},{"name":"恩里克"},{"name":"保罗"}]}]},{"name":"阎小闯"}]},{"name":"郑璐","children":[{"name":"于嘉","children":[{"name":"董成鹏","children":[{"name":"伊一","children":[{"name":"许嵩"},{"name":"付辛博"},{"name":"洪辰"}]},{"name":"王祖蓝","children":[{"name":"王菀之"},{"name":"李亚男","children":[{"name":"宋熙年"},{"name":"钟嘉欣"},{"name":"陈美诗"}]},{"name":"贾玲"}]},{"name":"白客","children":[{"name":"盛宇","children":[{"name":"邪童 "},{"name":"杜海涛"},{"name":"汪涵"}]},{"name":"派克特"},{"name":"谢帝","children":[{"name":"C-BLOCK小胖"},{"name":"范元成"},{"name":"隋凯","children":[{"name":"高以翔"},{"name":"马楚成"}]}]}]}]},{"name":"易建联、李艾、江映蓉"}]},{"name":"Kevin Han"}]},{"name":"舒德伟","children":[{"name":"姚明"},{"name":"NBA中国全体员工"}]},{"name":"叶丙成","children":[{"name":"翟本乔"},{"name":"嵇晓华","children":[{"name":"王思聪","children":[{"name":"易振兴","children":[{"name":"徐磊"},{"name":"佟大为","children":[{"name":"孟非","children":[{"name":"郭德纲","children":[{"name":"于谦"}]},{"name":"黄健翔","children":[{"name":"张琳芃","children":[{"name":"黄博文"},{"name":"李帅"}]},{"name":"郜林","children":[{"name":"刘建宏"},{"name":"李玮峰"}]}]}]},{"name":"陈坤"},{"name":"AKB48"}]},{"name":"吴欣鸿","children":[{"name":"贾乃亮"},{"name":"李小璐"},{"name":"angelababy"}]}]},{"name":"林更新","children":[{"name":"赵又廷"},{"name":"佟丽娅"},{"name":"AngelaBaby"}]},{"name":"刘军"}]},{"name":"魏坤琳","children":[{"name":"迟毓凯"},{"name":"李淼"},{"name":"姜振宇"}]},{"name":"刘成城","children":[{"name":"张颖"},{"name":"王自如","children":[{"name":"刘翔"},{"name":"吴海"},{"name":"傅盛"}]},{"name":"汪峰"}]}]}]},{"name":"萧上农","children":[{"name":"林之晨","children":[{"name":"柯文哲","children":[{"name":"赵少康"},{"name":"魏德圣"},{"name":"郭子乾"}]},{"name":"连胜文","children":[{"name":"郝龙斌","children":[{"name":"卢贝松"},{"name":"胡志强"},{"name":"邱文达"}]},{"name":"朱立伦"},{"name":"吴思华"}]},{"name":"管中闵","children":[{"name":"杜紫军"},{"name":"陈保基"},{"name":"杨泮池"}]}]},{"name":"陈素兰","children":[{"name":"颜漏有","children":[{"name":"詹宏志","children":[{"name":"钮承泽"},{"name":"李宗盛"},{"name":"何飞鹏"}]},{"name":"陈清祥","children":[{"name":"黄日灿"},{"name":"黄胜华"},{"name":"吴升奇"}]}]}]},{"name":"蔡牧民"}]},{"name":"林书豪","children":[{"name":"兰德里-菲尔兹"},{"name":"帕森斯"}]},{"name":"王猛","children":[{"name":"杨毅"},{"name":"柯凡"},{"name":"StephonMarbury"}]},{"name":"叶璇","children":[{"name":"李晨"},{"name":"苏芒","children":[{"name":"黄晓明","children":[{"name":"范冰冰"},{"name":"李冰冰","children":[{"name":"王中军"},{"name":"新浪娱乐"}]}]}]},{"name":"陈欧"}]},{"name":"章子怡","children":[{"name":"TFBoys","children":[{"name":"尚格云顿"}]},{"name":"韩庚","children":[{"name":"迈克尔·贝"},{"name":"何炅"},{"name":"那英"}]},{"name":"苏菲玛索"}]},{"name":"张靓颖","children":[{"name":"张杰","children":[{"name":"快乐家族"},{"name":"李宇春"},{"name":"萧敬腾"}]},{"name":"王铮亮","children":[{"name":"天天兄弟"},{"name":"武艺","children":[{"name":"卓文萱","children":[{"name":"廖俊杰","children":[{"name":"连晨翔","children":[{"name":"马振桓"},{"name":"萧煌奇"},{"name":"诗安"}]}]}]},{"name":"阿纬"},{"name":"洪卓立"}]},{"name":"DJ小强"}]},{"name":"Timbaland"}]},{"name":"邓紫棋","children":[{"name":"李蕴","children":[{"name":"何超莲","children":[{"name":"吴克群","children":[{"name":" 何猷啟"},{"name":"陈泽杉"}]},{"name":"卡提娜"},{"name":"jw_amusic "}]},{"name":"诗雅"},{"name":"陈静"}]},{"name":"蔡卓妍","children":[{"name":"钟欣桐","children":[{"name":"乔振宇","children":[{"name":"马天宇"},{"name":"陈伟霆","children":[{"name":"霍汶希"}]},{"name":"高伟光"}]},{"name":" 周汤豪"},{"name":"黃鴻升"}]},{"name":"谢娜"},{"name":"詹瑞文"}]},{"name":"茜拉","children":[{"name":"EXO-M"},{"name":"巫启贤"}]}]},{"name":"卫诗雅","children":[{"name":"吴君如"},{"name":"邹凯光"},{"name":"钟舒漫"}]},{"name":"容祖儿","children":[{"name":"梁家辉"},{"name":"黄伟文"}]},{"name":"蔡一智","children":[{"name":"陈奕迅","children":[{"name":"谢霆锋","children":[{"name":"桂纶镁","children":[{"name":"舒淇"},{"name":"张惠妹"},{"name":"孙燕姿"}]},{"name":"林丹"},{"name":"李云迪"}]},{"name":"范晓萱"},{"name":"张一白","children":[{"name":"彭于晏","children":[{"name":"林超贤"}]},{"name":"魏晨","children":[{"name":"秦凯","children":[{"name":"孙杨","children":[{"name":"张学友"},{"name":"华少"}]},{"name":"吴敏霞"},{"name":"陈一冰"}]}]},{"name":"张嘉佳"}]}]},{"name":"葛民辉"},{"name":"郑伊健","children":[{"name":"陈小春"},{"name":"谢天华"}]}]},{"name":"林俊杰","children":[{"name":"王力宏"},{"name":"蔡依林"}]},{"name":"徐峥","children":[{"name":"李连杰"},{"name":"韩寒"},{"name":"赵薇"}]},{"name":"刘循子墨","children":[{"name":"薛之谦"},{"name":"杨姗姗"}]},{"name":"王自健","children":[{"name":"郑凯"},{"name":"刘江"},{"name":"刘涛"}]},{"name":"罗震环","children":[{"name":"邹凯","children":[{"name":"许嵩 "},{"name":"张成龙"},{"name":"邹市明 "}]},{"name":"林琪雪","children":[{"name":"禹景曦","children":[{"name":"张翔玲","children":[{"name":"PLU小米"},{"name":"高地平"}]},{"name":"戴士","children":[{"name":"杨丰智"},{"name":"李鑫"},{"name":"卢本伟","children":[{"name":"孙亚龙"},{"name":"瞿申图"},{"name":"朱永权"}]}]},{"name":"裴乐","children":[{"name":"沈伟荣","children":[{"name":"金亦波"},{"name":"卞正伟"}]},{"name":"李君"}]}]},{"name":"孔连顺","children":[{"name":"老湿","children":[{"name":"至尊玉"},{"name":"马俊"},{"name":"颜土豆avi"}]},{"name":"小爱"},{"name":"马诗歌","children":[{"name":"张本煜"}]}]},{"name":"孙博文","children":[{"name":"陈剑书"},{"name":"陈琦栋"},{"name":"滕林季"}]}]},{"name":"沈建宏","children":[{"name":"陈奕","children":[{"name":"炎亚纶"},{"name":"张榕容"},{"name":"刘希平"}]},{"name":"何润东","children":[{"name":"俞永福","children":[{"name":"曹国伟"},{"name":"余承东"},{"name":"金池","children":[{"name":"曹格"},{"name":"魏雪漫"},{"name":"曾一鸣 "}]}]},{"name":"郭品超"},{"name":"霍建华 "}]},{"name":"张根硕"}]}]},{"name":"涂松岩","children":[{"name":"海清"},{"name":"张韵艺"},{"name":"王媛可"}]},{"name":"陈嘉上","children":[{"name":"包贝尔","children":[{"name":"陈赫"},{"name":"杨子姗"}]}]},{"name":"留几手","children":[{"name":"夏河"},{"name":"陆琪","children":[{"name":"贝志诚"},{"name":"孙杰"}]},{"name":"张辛苑","children":[{"name":"黄轩"},{"name":"古川雄辉"},{"name":"蒋劲夫"}]}]},{"name":"郑希怡","children":[{"name":"古巨基","children":[{"name":"崔始源","children":[{"name":"朴正洙"},{"name":"金希澈"}]},{"name":"黄子华"}]}]},{"name":"宁浩","children":[{"name":"徐铮"},{"name":"黄渤"},{"name":"雷佳音"}]},{"name":"鈕承澤","children":[{"name":"陈意涵","children":[{"name":"张钧甯"},{"name":"陈柏霖","children":[{"name":"冯绍峰"},{"name":"高华阳","children":[{"name":"王志鹏"},{"name":"李东霖"},{"name":"夏青"}]}]},{"name":"池珍熙"}]},{"name":"阮经天"}]},{"name":"周显扬","children":[{"name":"王珞丹"},{"name":"井柏然"},{"name":"张晋"}]},{"name":"徐熙娣","children":[{"name":"蔡康永"}]},{"name":"刘俊纬","children":[{"name":"杨奇煜","children":[{"name":"曾志伟"},{"name":"张艾亚","children":[{"name":"房思瑜"}]}]},{"name":"林峰"}]},{"name":"周汤豪","children":[{"name":"庄濠全","children":[{"name":"罗志祥"},{"name":"簡愷樂"}]},{"name":"林暐恒"},{"name":"王雪娥","children":[{"name":"洪炜宁"}]}]},{"name":"杨颖","children":[{"name":"倪妮"}]},{"name":"董子健","children":[{"name":"郭京飞","children":[{"name":"袁咏仪"},{"name":"钱芳"}]},{"name":"陆毅"},{"name":"关锦鹏"}]}]}
            	            ]
            	        }
            	    ]
            	});

            
        	//--- 动态图 ---
            var dymcChart = ec.init(document.getElementById('dymcMap'));
            dymcChart.setOption({
            	    title : {
            	        text: '动态数据',
            	        subtext: '纯属虚构'
            	    },
            	    tooltip : {
            	        trigger: 'axis'
            	    },
            	    legend: {
            	        data:['最新成交价', '预购队列']
            	    },
            	    toolbox: {
            	        show : true,
            	        feature : {
            	            mark : {show: true},
            	            dataView : {show: true, readOnly: false},
            	            magicType : {show: true, type: ['line', 'bar']},
            	            restore : {show: true},
            	            saveAsImage : {show: true}
            	        }
            	    },
            	    dataZoom : {
            	        show : false,
            	        start : 0,
            	        end : 100
            	    },
            	    xAxis : [
            	        {
            	            type : 'category',
            	            boundaryGap : true,
            	            data : (function (){
            	                var now = new Date();
            	                var res = [];
            	                var len = 10;
            	                while (len--) {
            	                    res.unshift(now.toLocaleTimeString().replace(/^\D*/,''));
            	                    now = new Date(now - 2000);
            	                }
            	                return res;
            	            })()
            	        },
            	        {
            	            type : 'category',
            	            boundaryGap : true,
            	            data : (function (){
            	                var res = [];
            	                var len = 10;
            	                while (len--) {
            	                    res.push(len + 1);
            	                }
            	                return res;
            	            })()
            	        }
            	    ],
            	    yAxis : [
            	        {
            	            type : 'value',
            	            scale: true,
            	            name : '价格',
            	            boundaryGap: [0.2, 0.2]
            	        },
            	        {
            	            type : 'value',
            	            scale: true,
            	            name : '预购量',
            	            boundaryGap: [0.2, 0.2]
            	        }
            	    ],
            	    series : [
            	        {
            	            name:'预购队列',
            	            type:'bar',
            	            xAxisIndex: 1,
            	            yAxisIndex: 1,
            	            data:(function (){
            	                var res = [];
            	                var len = 10;
            	                while (len--) {
            	                    res.push(Math.round(Math.random() * 1000));
            	                }
            	                return res;
            	            })()
            	        },
            	        {
            	            name:'最新成交价',
            	            type:'line',
            	            data:(function (){
            	                var res = [];
            	                var len = 10;
            	                while (len--) {
            	                    res.push((Math.random()*10 + 5).toFixed(1) - 0);
            	                }
            	                return res;
            	            })()
            	        }
            	    ]
            	});
            	var lastData = 11;
            	var axisData;
            	clearInterval(timeTicket);
            	timeTicket = setInterval(function (){
            	    lastData += Math.random() * ((Math.round(Math.random() * 10) % 2) == 0 ? 1 : -1);
            	    lastData = lastData.toFixed(1) - 0;
            	    axisData = (new Date()).toLocaleTimeString().replace(/^\D*/,'');
            	    
            	    // 动态数据接口 addData
            	    dymcChart.addData([
            	        [
            	            0,        // 系列索引
            	            Math.round(Math.random() * 1000), // 新增数据
            	            true,     // 新增数据是否从队列头部插入
            	            false     // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
            	        ],
            	        [
            	            1,        // 系列索引
            	            lastData, // 新增数据
            	            false,    // 新增数据是否从队列头部插入
            	            false,    // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
            	            axisData  // 坐标轴标签
            	        ]
            	    ]);
            	}, 2100);
            	                    
            
            
            

        }
    );
    
    
    

    </script>
</body>
</html>