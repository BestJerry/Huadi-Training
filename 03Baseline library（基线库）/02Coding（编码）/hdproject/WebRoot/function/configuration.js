$(function() {
	var type2 = 0;
	var type3 = 0;
	var type4 = 0;
	//鼠标移进的样式
	$(".configuration").mouseover(function() {
		$(".configuration").css({
			"border-left": "none"
		});
	});
	//鼠标移走的样式
	$(".configuration").mouseout(function() {
		$(".configuration").css({
			"border-left": "3px solid #009688"
		});
	});
	//加载账号
	var strName = localStorage.getItem("keyName");
	$("#account").html(strName);
	//加载数据
	$.getJSON("../getParameter", function(data) {
		$("#consumption").val(data.data.con_intergral * 100 + "%");
		$("#members").val(data.data.discount * 100 + "%");
	});
	$.getJSON("../getPower", function(data) {
		if (data.data.type2 == 1) {
			$("#type2").attr("checked", "checked");
			type2 = 1;
		}
		if (data.data.type3 == 1) {
			$("#type3").attr("checked", "checked");
			type3 = 1;
		}
		if (data.data.type4 == 1) {
			$("#type4").attr("checked", "checked");
			type4 = 1;
		}
	});
	//是否启用
	$("input[type='checkbox']").change(function() {
		var isType = $(this).attr("id");
		var isChecked = $(this).attr("checked");
		if (isChecked) {
			if (isType == "type2") {
				type2 = 0;
			} else if (isType == "type3") {
				type3 = 0;
			} else {
				type4 = 0;
			}
			$("#" + isType).removeAttr("checked");
		} else {
			if (isType == "type2") {
				type2 = 1;
			} else if (isType == "type3") {
				type3 = 1;
			} else {
				type4 = 1;
			}
			$("#" + isType).attr("checked", "checked");
		}
	});
	//修改
	$("#modify").click(function() {
		$("#consumption").removeAttr("disabled");
		$("#members").removeAttr("disabled");
		$("#type2").removeAttr("disabled");
		$("#type3").removeAttr("disabled");
		$("#type4").removeAttr("disabled");
	});
	//保存
	$("#save").click(function() {
		var con_intergral = (parseInt($("#consumption").val()) / 100).toFixed(1);
		var discount = (parseInt($("#members").val()) / 100).toFixed(1);
		//alert(con_intergral);
		//alert(discount);
		//alert(type2);
		//alert(type3);
		//alert(type4);
		$.post('../updateParameter', {
				con_intergral: con_intergral,
				discount: discount
			},
			function(data, status) {
				console.log(data);
			});
		$.post('../updatePower', {
			type2: type2,
			type3: type3,
			type4: type4
		}, function(data, status) {
			console.log(data);
		});
		$("#consumption").attr("disabled", true);
		$("#members").attr("disabled", true);
		$("#type2").attr("disabled", true);
		$("#type3").attr("disabled", true);
		$("#type4").attr("disabled", true);
	});
});