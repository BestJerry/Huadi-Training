$(function() {
	//加载地址
	$.getJSON('https://restapi.amap.com/v3/config/district?keywords=中国&subdistrict=1&key=e3da30f48254d815699bc01b68d42309', function(data) {
		var address2 = data.districts[0].districts;
		for (var i = 0; i < address2.length; i++) {
			$("#province").append("<option>" + address2[i].name + "</option>");
			//console.log(address[i].name);
		}
	});
	$.getJSON('https://restapi.amap.com/v3/config/district?keywords=河南省&subdistrict=1&key=e3da30f48254d815699bc01b68d42309', function(data) {
		var address3 = data.districts[0].districts;
		for (var i = 0; i < address3.length; i++) {
			$("#city").append("<option>" + address3[i].name + "</option>");
			//console.log(address[i].name);
		}
	});
	$.getJSON('https://restapi.amap.com/v3/config/district?keywords=濮阳市&subdistrict=1&key=e3da30f48254d815699bc01b68d42309', function(data) {
		var address4 = data.districts[0].districts;
		for (var i = 0; i < address4.length; i++) {
			$("#district").append("<option>" + address4[i].name + "</option>");
			//console.log(address[i].name);
		}
	});
	$("#province").change(function() {
		console.log($("#province").val());
		$.getJSON("https://restapi.amap.com/v3/config/district?keywords=" + $("#province").val() + "&subdistrict=1&key=e3da30f48254d815699bc01b68d42309", function(data) {
			var address5 = data.districts[0].districts;
			//删除子节点
			$("#city").empty();
			for (var i = 0; i < address5.length; i++) {
				$("#city").append("<option>" + address5[i].name + "</option>");
				//console.log(address5[i].name);
			}
			$.getJSON("https://restapi.amap.com/v3/config/district?keywords=" + $("#city").val() + "&subdistrict=1&key=e3da30f48254d815699bc01b68d42309", function(data) {
				var address6 = data.districts[0].districts;
				//删除子节点
				$("#district").empty();
				for (var i = 0; i < address6.length; i++) {
					$("#district").append("<option>" + address6[i].name + "</option>");
					//console.log(address6[i].name);
				}
			});
		});
	});
	$("#city").change(function() {
		$.getJSON("https://restapi.amap.com/v3/config/district?keywords=" + $("#city").val() + "&subdistrict=1&key=e3da30f48254d815699bc01b68d42309", function(data) {
			var address7 = data.districts[0].districts;
			//删除子节点
			$("#district").empty();
			for (var i = 0; i < address7.length; i++) {
				$("#district").append("<option>" + address7[i].name + "</option>");
				//console.log(address6[i].name);
			}
		});
	});
	//比例
	var proportion = new Array("10%", "20%", "30%", "40%", "50%", "60%", "70%", "80%", "90%", "100%");
	for (var i = 1; i <= proportion.length; i++) {
		$("#con_intergral").append("<option value=\"" + (i * 0.1).toFixed(1) + "\">" + proportion[i - 1] + "</option>");
		$("#discount").append("<option value=\"" + (i * 0.1).toFixed(1) + "\">" + proportion[i - 1] + "</option>");
	}
	//验证手机号
	var reg_phone = /^[1][3,4,5,6,7,8][0-9]{9}$/; //手机号正则表达式
	var reg_mobile = /^0[0-9]{2,3}-?[0-9]{7,8}$/; //家庭电话正则表达式
	//清空警示
	$("#bus_phone").focus(function() {
		$("#bus_phoneWarning").html("");
	});
	$("#con_tel").focus(function() {
		$("#con_telWarning").html("");
	});
	$("#con_mobile").focus(function() {
		$("#con_mobileWarning").html("");
	});
	//企业电话验证
	$("#bus_phone").blur(function() {
		var bus_phone = $("#bus_phone").val();
		//alert(bus_phone);
		if (bus_phone == "") {
			$("#bus_phoneWarning").html("企业电话不能为空！");
		} else if (!reg_phone.test(bus_phone) && !reg_mobile.test(bus_phone)) {
			$("#bus_phoneWarning").html("企业电话格式不正确！");
		}
	});
	//联系人电话或手机号验证
	$("#con_tel").blur(function() {
		var con_tel = $("#con_tel").val();
		//alert(con_tel);
		if (con_tel != "") {
			if (!reg_mobile.test(con_tel)) {
				$("#con_telWarning").html("电话格式不正确！");
			}
		}
	});
	$("#con_mobile").blur(function() {
		var con_mobile = $("#con_mobile").val();
		//alert(con_mobile);
		if (con_mobile != "") {
			if (!reg_phone.test(con_mobile)) {
				$("#con_mobileWarning").html("手机号格式不正确！");
			}
		}
	});
	//邮政编码验证
	var reg_postcode = /^[1-9][0-9]{5}$/;
	$("#bus_postcode").focus(function() {
		$("#bus_postcodeWarning").html("");
	});
	$("#bus_postcode").blur(function() {
		var bus_postcode = $("#bus_postcode").val();
		//alert(con_mobile);
		if (bus_postcode != "") {
			if (!reg_postcode.test(bus_postcode)) {
				$("#bus_postcodeWarning").html("邮政编码格式不正确！");
			}
		}
	});
	//emial验证
	var reg_email = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
	$("#con_email").focus(function() {
		$("#con_emailWarning").html("");
	});
	$("#con_email").blur(function() {
		var con_email = $("#con_email").val();
		//alert(con_email);
		if (con_email == "") {
			$("#con_emailWarning").html("Email不能为空！");
		} else if (!reg_email.test(con_email)) {
			$("#con_emailWarning").html("Email格式不正确！");
		}
	});
	//确定加盟
	$("#bus_name").focus(function() {
		$("#bus_nameWarning").html("");
	});
	$("#bus_name").blur(function() {
		if ($("#bus_name").val() == "") {
			$("#bus_nameWarning").html("企业名称不能为空！");
		}
	});
	$("#detail").focus(function() {
		$("#bus_addWarning").html("");
	});
	$("#detail").blur(function() {
		if ($("#detail").val() == "") {
			$("#bus_addWarning").html("详细地址不能为空！");
		}
	});
	$("#con_name").focus(function() {
		$("#con_nameWarning").html("");
	});
	$("#con_name").blur(function() {
		if ($("#con_name").val() == "") {
			$("#con_nameWarning").html("姓名不能为空！");
		}
	});
	$("#verificationCode").focus(function() {
		$("#verificationCodeWarning").html("");
	});
	$("#verificationCode").blur(function() {
		if ($("#verificationCode").val() == "") {
			$("#verificationCodeWarning").html("验证码不能为空！");
		}
	});
	$(":radio").focus(function() {
		$("#con_titleWarning").html("");
	});
	$(":radio").blur(function() {
		if ($("input[type='radio']:checked").val() == undefined) {
			$("#con_titleWarning").html("请选择称谓！");
		}
	});
	$("#con_position").focus(function() {
		$("#con_positionWarning").html("");
	});
	$("#con_position").blur(function() {
		if ($("#con_position").val() == "") {
			$("#con_positionWarning").html("职务不能为空！");
		}
	});
	//登录信息
	$("#sure2").click(function() {
		window.location.href = "../login/login.html";
	});
	$("#cancel2").click(function() {
		window.location.href = "../homePage/homePage.html";
	});
	$("#sure").click(function() {
		var flag = 0;
		//企业类型
		var bus_type = $("#bus_type").val();
		//企业名称
		var bus_name = $("#bus_name").val();
		if (bus_name == "") {
			$("#bus_nameWarning").html("企业名称不能为空！");
			flag = 1;
		}
		//企业地址
		var bus_add = $("#province").val() + $("#city").val() + $("#district").val() + $("#detail").val();
		if ($("#detail").val() == "") {
			$("#bus_addWarning").html("详细地址不能为空！");
			flag = 1;
		}
		//企业电话
		var bus_phone = $("#bus_phone").val();
		if (bus_phone == "") {
			$("#bus_phoneWarning").html("企业电话不能为空！");
			flag = 1;
		}
		//企业邮编
		var bus_postcode = $("#bus_postcode").val();
		//星级
		var bus_star = $("#bus_star").val();
		//消费积分比例
		var con_intergral = $("#con_intergral").val();
		//会员折扣比
		var discount = $("#discount").val();
		//商家资料
		var con_title = $("input[type='radio']:checked").val();
		if (con_title == undefined) {
			$("#con_titleWarning").html("请选择称谓！");
			flag = 1;
		}
		//alert(con_title);
		var con_name = $("#con_name").val();
		if (con_name == "") {
			$("#con_nameWarning").html("姓名不能为空！");
			flag = 1;
		}
		var con_position = $("#con_position").val();
		if (con_position == "") {
			$("#con_positionWarning").html("职务不能为空！");
			flag = 1;
		}
		var con_tel = $("#con_tel").val();
		var con_mobile = $("#con_mobile").val();
		var con_fax = $("#con_fax").val();
		var con_email = $("#con_email").val();
		if (con_email == "") {
			flag = 1;
			$("#con_emailWarning").html("Email不能为空！");
		}

		var verificationCode = $("#verificationCode").val();
		if (verificationCode == "") {
			flag = 1;
			$("#verificationCodeWarning").html("验证码不能为空！");
		}
		if (flag == 0) {
			//alert("yes");
			var data = "bus_type=" + bus_type + "&bus_name=" + bus_name + "&bus_add=" + bus_add + "&bus_phone=" + bus_phone + "&bus_postcode=" + bus_postcode + "&bus_star=" + bus_star + "&con_intergral=" + con_intergral + "&discount=" + discount;
			data += "&con_title=" + con_title + "&con_name=" + con_name + "&con_position=" + con_position + "&con_tel=" + con_tel + "&con_mobile=" + con_mobile + "&con_fax=" + con_fax + "&con_email=" + con_email + "&verificationCode=" + verificationCode;
			//alert(data);
			$.post("../register", {
					bus_type: bus_type,
					bus_name: bus_name,
					bus_add: bus_add,
					bus_phone: bus_phone,
					bus_postcode: bus_postcode,
					bus_star: bus_star,
					con_intergral: con_intergral,
					discount: discount,
					con_title: con_title,
					con_name: con_name,
					con_position: con_position,
					con_tel: con_tel,
					con_mobile: con_mobile,
					con_fax: con_fax,
					con_email: con_email,
					verificationCode: verificationCode
				},
				function(data, status) {
					console.log(data);
					if (data.status == "0") {
						//登录信息
						console.log(data);
						$("#loginAccount").val(data.data.acc_name);
						$("#loginPassword").val(data.data.acc_psd);
						var loginInformation = document.getElementById('loginInformation');
						loginInformation.showModal();
					} else if (data.message == "验证码错误") {
						//alert(data.message);
						$("#verificationCodeWarning").html("验证码错误");
					} else {
						alert(data.message);
					}
				}
			);
			/*$.getJSON("../register?" + data, function(data) {
				if (data.status == "0") {
					//登录信息
					console.log(data);
					$("#loginAccount").val(data.data.acc_name);
					$("#loginPassword").val(data.data.acc_psd);
					var loginInformation = document.getElementById('loginInformation');
					loginInformation.showModal();
				} else {
					alert(data.message);
				}
			});*/
		}
	});
});