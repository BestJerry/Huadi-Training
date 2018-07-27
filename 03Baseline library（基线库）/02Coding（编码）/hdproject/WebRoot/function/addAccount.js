$(function() {
	//加载账号
	var strName = localStorage.getItem("keyName");
	$("#account").html(strName);
	var addInformation = document.getElementById('addInformation');
	//加载子账号
	function loadData(url) {
		$.getJSON(url, function(data) { //ajax iframe
			console.log(data);
			var arr = [];
			arr = data.data;
			console.log(arr);
			if (arr.length > 0) {
				$("tr.odd").hide();
			}
			for (var i = 0; i < arr.length; i++) {
				var str = "";
				str += "<tr'>";
				str += "<td>" + arr[i].acc_name + "</td>";
				//判断类型
				if (arr[i].account_type == "1") {
					str += "<td>" + "管理员" + "</td>";
				} else if (arr[i].account_type == "2") {
					str += "<td>" + "前台" + "</td>";
				} else if (arr[i].account_type == "3") {
					str += "<td>" + "客户经理" + "</td>";
				} else if (arr[i].account_type == "4") {
					str += "<td>" + "经理" + "</td>";
				}
				//是否启用
				if (arr[i].isStart == true) {
					str += "<td>" + "<div class='toggle col-md-8'><label><input class='checked' type='checkbox' checked id='" + arr[i].acc_id + "'><span class='button-indecator'/></span></label></div>" + "</td>";
				} else {
					str += "<td>" + "<div class='toggle col-md-8'><label><input class='checked' type='checkbox' id='" + arr[i].acc_id + "'><span class='button-indecator'  /></span></label></div>" + "</td>";
				}
				str += "<td>" + "<div class='toggle col-md-8'><button class='initPassword btn-primary' value='" + arr[i].acc_id + "' id='" + arr[i].acc_name + "'>初始化</button></div>" + "</td>";
				str += "</tr>";
				$("#data").append(str);
			}
			//是否初始化密码
			$(".initPassword").unbind().bind("click", function() {
				var acc_id = $(this).attr("value");
				var acc_name = $(this).attr("id");
				//console.log(acc_id);
				var isSure = document.getElementById('isSure');
				isSure.showModal();
				$("#cancelInit").click(function() {
					isSure.close();
				});
				$("#sureInit").click(function() {
					isSure.close();
					$("#" + acc_name).html("已初始化");
					$("#" + acc_name).attr("disabled", true);
					$.post('../initSubaccount', {
						acc_id: acc_id
					}, function(data, status) {
						console.log(data);
					});
				});
			});
			//是否启用
			$(".checked").change(function() {
				var acc_id = $(this).attr("id");
				var isChecked = $(this).attr("checked");
				var isStart;
				if (isChecked) {
					isStart = 0;
					$("#" + acc_id).removeAttr("checked");
				} else {
					isStart = 1;
					$("#" + acc_id).attr("checked", "checked");
				}
				$.post('../updateSubaccountStatus', {
						acc_id: acc_id,
						isStart: isStart
					},
					function(data, status) {
						console.log(data);
					});
			});
		});
	}
	loadData('../getAccount');
	//添加信息
	/*var addAccount = document.getElementById('addAccount');
	addAccount.addEventListener("click", function() {
		addInformation.showModal();
	}, false);*/
	//显示几行
	$("select.input-sm").change(function() {
		alert($("select.input-sm").val());
		loadData('../getAccount?countEachPage=' + parseInt($(".input-sm").val()));
	});
	//模糊搜索
	$("input[type='search']").change(function() {
		var str = $("input[type='search']").val();
		console.log(str);
		loadData('../getAccount?account_name=' + str);
	});
	//添加信息
	$("#addAccount").click(function() {
		addInformation.showModal();
	});
	//关闭对话框
	/*var cancel = document.getElementById('cancel');
	cancel.addEventListener("click", function() {
		addInformation.close();
	});*/
	$("#cancel").click(function() {
		addInformation.close();
	});
	//鼠标移进的样式
	$(".account").mouseover(function() {
		$(".account").css({
			"border-left": "none"
		});
	});
	//鼠标移走的样式
	$(".account").mouseout(function() {
		$(".account").css({
			"border-left": "3px solid #009688"
		});
	});
	//退出账号
	$("#quitSystem").click(function() {
		//alert("1");
		$.getJSON('../logout', function(data) {});
	});
	//关闭登录信息
	$("#sure2").click(function() {
		var loginInformation = document.getElementById('loginInformation');
		loginInformation.close();
		window.location.reload();
	});
	//添加子账号
	$("#sure").click(function() {
		var account_type = $("#account_type").val();
		var isStart;
		if ($("#isStart").is(":checked")) {
			isStart = 1;
		} else {
			isStart = 0;
		}
		$.post('../addSubaccount', {
			account_type: account_type,
			isStart: isStart
		}, function(data, status) {
			$("#isStart").attr("checked", false);
			console.log(data);
			if (data.status == "0") {
				addInformation.close();
				$("#loginAccount").val(data.data.acc_name);
				$("#loginPassword").val(data.data.acc_psd);
				var loginInformation = document.getElementById('loginInformation');
				loginInformation.showModal();
			}
		});
	});
});