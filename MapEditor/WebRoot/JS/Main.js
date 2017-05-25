

$(function () {
    var point = new BMap.Point(104.06, 30.67);
    Window.MyMap.centerAndZoom(point, 12); // 初始化地图,设置中心点坐标和地图级别。
    function f(result) {
        var cityName = result.name;
        Window.MyMap.setCenter(cityName);
    }
    var myCity = new BMap.LocalCity();
    myCity.get(f);
    Window.MyMap.enableScrollWheelZoom(); //启用滚轮放大缩小
    Window.MyMap.disableDragging();     //禁止拖拽
    setTimeout(function () {
        Window.MyMap.enableDragging();   //两秒后开启拖拽
        //map.enableInertialDragging();   //两秒后开启惯性拖拽
    }, 2000);
    // 添加定位控件
    var geolocationControl = new BMap.GeolocationControl();
    geolocationControl.addEventListener("locationSuccess", function (e) {
        // 定位成功事件
        var address = '';
        address += e.addressComponent.province;
        address += e.addressComponent.city;
        address += e.addressComponent.district;
        address += e.addressComponent.street;
        address += e.addressComponent.streetNumber;
        alert("当前定位地址为：" + address);
    });
    geolocationControl.addEventListener("locationError", function (e) {
        // 定位失败事件
        alert(e.message);
    });
    Window.MyMap.addControl(geolocationControl);

	$.post("/OurMap/API/Map/QueryOverlay"
		, function(data) {
			if (data.msg == "success") {
				var shpeCounts = data.ShapeSize;
				for (var i = 0; i < shpeCounts; i++) {
					var str = data["Shape" + i];
					var strs = new Array(); //定义一数组 
					strs = str.split("*"); //字符分割 
					var points = [];
					var average_x=0;
					var average_y=0;
					for (var j = 0; j < strs.length; j++) {
						var x = strs[j].split(",")[0];
						var y = strs[j].split(",")[1];
						points.push(new BMap.Point(x, y));
						average_x=average_x+parseFloat(x);
						average_y=average_y+parseFloat(y);
					}
					average_x=average_x/strs.length;
					average_y=average_y/strs.length;
					var polygon = new BMap.Polygon(points, {
						strokeColor : "blue",
						strokeWeight : 2,
						strokeOpacity : 0.5
					}); //创建多边形

					Window.MyMap.addOverlay(polygon); //增加多边形
					var str="公建："+data["gongjian" + i]+"<br />"+"医务："+data["yiwu" + i]+"<br />"+"肄业："+data["yiye" + i]+"<br />"+"社区："+data["shequ" + i]+"<br />"+"上门："+data["goHomeNums" + i]+"<br />"+"报名："+data["goSchoolNums" + i]
					showText(average_x,average_y,str);
				}
			} else {
				alert("数据错误");
			}
		});

	function showText(x,y, str) {
		var point= new BMap.Point(x,y);
		var opts = {
			position : point, // 指定文本标注所在的地理位置
			offset : new BMap.Size(-30, -60) //设置文本偏移量
		}
		var label = new BMap.Label(str, opts); // 创建文本标注对象
		label.setStyle({
			color : "red",
			fontSize : "20px",
			fillColor : "red", //填充颜色。当参数为空时，圆形将没有填充效果。
		});
		Window.MyMap.addOverlay(label);
	}
	/*
	//>关键字输入提示词条
	var ac = new BMap.Autocomplete( //建立一个自动完成的对象
    {
        "input": "suggestId",
        "location": Window.MyMap
    });
    function setPlace() {
        Window.MyMap.clearOverlays();    //清除地图上所有覆盖物
        function myFun() {
            var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
            Window.MyMap.centerAndZoom(pp, 18);
            Window.MyMap.addOverlay(new BMap.Marker(pp));    //添加标注
        }
        var local = new BMap.LocalSearch(Window.MyMap, { //智能搜索
            onSearchComplete: myFun
        });
        local.search(myValue);
    }
    ac.addEventListener("onhighlight", function (e) {  //鼠标放在下拉列表上的事件
        var str = "";
        var _value = e.fromitem.value;
        var value = "";
        if (e.fromitem.index > -1) {
            value = _value.province + _value.city + _value.district + _value.street + _value.business;
        }
        str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;

        value = "";
        if (e.toitem.index > -1) {
            _value = e.toitem.value;
            value = _value.province + _value.city + _value.district + _value.street + _value.business;
        }
        str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
        $("#searchResultPanel").html(str);
    });

    var myValue;
    ac.addEventListener("onconfirm", function (e) {    //鼠标点击下拉列表后的事件
        var _value = e.item.value;
        myValue = _value.province + _value.city + _value.district + _value.street + _value.business;
        $("#searchResultPanel").html("onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue);
        setPlace();
    });
*/

	$("#DrawPolygon").on("click", function() {
		draw(BMAP_DRAWING_POLYGON)
	});
	
	var overlays; //覆盖物  
	var overlaycomplete = function(e) {
		Window.MyMap.removeOverlay(overlays);
		overlays = e.overlay;
	};

	var styleOptions = {
		strokeColor : "red", //边线颜色。
		fillColor : "red", //填充颜色。当参数为空时，圆形将没有填充效果。
		strokeWeight : 3, //边线的宽度，以像素为单位。
		strokeOpacity : 0.8, //边线透明度，取值范围0 - 1。
		fillOpacity : 0.6, //填充的透明度，取值范围0 - 1。
		strokeStyle : 'solid' //边线的样式，solid或dashed。
	}

	//实例化鼠标绘制工具
	var drawingManager = new BMapLib.DrawingManager(Window.MyMap, {
		isOpen : false, //是否开启绘制模式
		polygonOptions : styleOptions, //多边形的样式
	});
	function draw(type) {
		drawingManager.open();
		drawingManager.setDrawingMode(type);
	}

	//添加鼠标绘制工具监听事件，用于获取绘制结果
	drawingManager.addEventListener('overlaycomplete', overlaycomplete);

	var str; //坐标集合
	function getPoint() {
		str = "";
		var overlay = overlays.getPath();
		for (var j = 0; j < overlay.length; j++) {
			var grid = overlay[j];
			str = str + grid.lng + "," + grid.lat + "*";
		}
	}
	$("#SavePolygon").on("click",function() {
		getPoint();
		sendDataTo();
	});
	function sendDataTo() {
		 var strs = prompt("请输入地区、公建、医务、 肄业、社区、上门数、报名数，请用'*'隔开", ""); //将输入的内容赋给变量 gohomeNums ，
		 while(strs==""||strs==null||strs.split('*').length!=7){
			 alert("你输入的数据不对、请重新输入！");
			 strs = prompt("请输入地区、公建、医务、 肄业、社区、上门数、报名数，请用'*'隔开", ""); //将输入的内容赋给变量 gohomeNums ，
		 } 
		 var a=strs.split('*');
		 str=str+"-";
		for (var i = 0; i < a.length; i++) {
			if ($.trim(a[i]).length == 0)
				if (i == 5 || i == 6)
					a[i] = 0;
				else
					a[i] = " ";
			i != 6 ? str = str + a[i] + "*" : str = str + a[i];
		}

		$.post("/OurMap/API/Map/SaveInfo"
			, {
				info : str
			}, function(data) {
				if (data.msg == "success") {
					alert("存储成功");
				} else {
					alert("存储失败");
				}
			});
	}
	$("#EditPolygon").on("click", function() {
		if (overlays != null) {
			var status = $(this).attr("data-status");
			if (status == "enable") {
				$(this).attr("data-status", "disable");
				$(this).text("停止编辑");
				$('#SavePolygon').hide();
			} else {
				$(this).attr("data-status", "enable");
				$(this).text("编辑多边形");
				$('#SavePolygon').show();
			}
			Editing(status);
		}
	});
	function Editing(status) {
		status == 'enable' ? overlays.enableEditing() : overlays.disableEditing();
	}
});