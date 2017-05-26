$(function() {
	$(".editInput").addClass("txtstyle").attr("readonly","readonly");
	
	var oldstr=newstr="";
	$(".Edit").on("click", function() {
		var value = $(this).text();
		if (value == "开始编辑") {
			$(this).text("保存");
			$(this).parent().siblings().children(".editInput").removeAttr("readonly").addClass("form-control").removeClass("txtstyle");
			oldstr = $(this).parent().siblings(".td_name").children().val() + "*" + $(this).parent().siblings(".td_gongjian").children().val() + "*" + $(this).parent().siblings(".td_yiwu").children().val() + "*" + $(this).parent().siblings(".td_yiye").children().val() + "*" + $(this).parent().siblings(".td_shequ").children().val() + "*" + $(this).parent().siblings(".td_home").children().val() + "*" + $(this).parent().siblings(".td_school").children().val();
		} else {
			$(this).text("开始编辑");
			$(this).parent().siblings().children(".editInput").attr("readonly", "readonly").addClass("txtstyle").removeClass("form-control");
			str = $(this).parent().siblings(".td_name").children().val() + "*" + $(this).parent().siblings(".td_gongjian").children().val() + "*" + $(this).parent().siblings(".td_yiwu").children().val() + "*" + $(this).parent().siblings(".td_yiye").children().val() + "*" + $(this).parent().siblings(".td_shequ").children().val() + "*" + $(this).parent().siblings(".td_home").children().val() + "*" + $(this).parent().siblings(".td_school").children().val();
			
			 var a=str.split('*');
			for (var i = 0; i < a.length; i++) {
				if ($.trim(a[i]).length == 0)
					a[i] = " ";
				i != 6 ? newstr = newstr + a[i] + "*" : newstr = newstr + a[i];
			}
			if (oldstr != newstr)
				modifyData(oldstr, newstr);
			else
				alert("你未作任何改变");
		}
	});

	$(".Delete").on("click",function(){
	var	str=$(this).parent().prev().prev().prev().prev().children().val()+"*"+$(this).parent().prev().prev().prev().children().val()+"*"+$(this).parent().prev().prev().children().val();
	 var msg = "您真的确定要删除吗？\n\n请确认！"; 
	 if (confirm(msg)==true){ 
		 deleteData(str,$(this));
	 }
	});
	function modifyData(oldstr,newstr) {
		$.post("/MapEditor/API/Map/Modify",{
			oldstr:oldstr,
			newstr:newstr
		},function(data){
			if (data.msg == "success") {
				alert("修改成功");
			} else {
				alert("修改失败");
			}
		});
	}
	
	function deleteData(str,t) {
		$.post("/MapEditor/API/Map/Delete",{
			str:str
		},function(data){
			if (data.msg == "success") {
				alert("修改成功");
				 t.parent().parent().html("");
			} else {
				alert("修改失败");
			}
		});
	}
});