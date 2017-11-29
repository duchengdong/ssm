<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>员工列表</title>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<!-- 引入jQuery、bootstrap -->
<script type="text/javascript"
	src="${APP_PATH}/static/js/jquery-3.2.1.min.js"></script>

<link rel="stylesheet"
	href="${APP_PATH}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css"></link>
<script
	src="${APP_PATH}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

</head>
<body>
	<div class="container">
		<!-- 标题 -->
		<div class="row">
			<div class="col-md-12">
				<h1>SSM-CRUD</h1>
			</div>
		</div>
		<!-- 按钮：新增、删除-->
		<div class="row">
			<div class="col-md-4 col-md-offset-8">
				<button class="btn btn-primary">新增</button>
				<button class="btn btn-danger">删除</button>
			</div>

		</div>
		<!-- 显示表格数据-->
		<div class="row">
			<div class="col-md-12">
				<table class="table table-striped" id="emps_table">
					<thead>
						<tr>
							<th>#</th>
							<th>empName</th>
							<th>gender</th>
							<th>email</th>
							<th>department</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
					</tbody>




				</table>
			</div>
		</div>
		<!-- 分页信息 -->
		<div class="row">
			<div class="col-md-6" id="page_info_area"></div>
			<div class="col-md-6" id="page_nav_area"></div>
		</div>
	</div>


	<script type="text/javascript">
		//页面加载完成后，获取数据
		$(function() {
			to_page(1);
		});

		function to_page(pn) {
			$.ajax({
				url : "${APP_PATH}/emps",
				data : "pn=" + pn,
				type : "GET",
				success : function(result) {
					//1、解析并显示员工数据
					build_emps_table(result);
					build_page_info(result);
					build_page_nav(result);

				}
			});
		}

		function build_emps_table(result) {
			$("#emps_table tbody").empty();
			var emps = result.extend.pageInfo.list;
			$.each(emps, function(index, item) {
				var empIdTd = $("<td></td>").append(item.empId);
				var empNameTd = $("<td></td>").append(item.empName);
				var gender = item.gender == "m" ? "男" : "女";
				var genderTd = $("<td></td>").append(gender);
				var emailTd = $("<td></td>").append(item.email);
				var deptNameTd = $("<td></td>")
						.append(item.department.deptName);
				var editBtn = $("<button></button>").addClass(
						"btn btn-primary btn-sm").append(
						$("<span></span>").addClass(
								"glyphicon glyphicon-pencil")).append("编辑");
				var delBtn = $("<button></button>").addClass(
						"btn btn-danger btn-sm").append(
						$("<span></span>")
								.addClass("glyphicon glyphicon-trash")).append(
						"删除");
				var functionTd = $("<td></td>").append(editBtn).append(" ")
						.append(delBtn);

				$("<tr></tr>").append(empIdTd).append(empNameTd).append(
						genderTd).append(emailTd).append(deptNameTd).append(
						functionTd).appendTo("#emps_table tbody");

			});
		}
		function build_page_info(result) {
			$("#page_info_area").empty();
			$("#page_info_area").append(
					"当前第" + result.extend.pageInfo.pageNum + "页，总"
							+ result.extend.pageInfo.pages + "页，总"
							+ result.extend.pageInfo.total + "条记录");
		}
		function build_page_nav(result) {
			$("#page_nav_area").empty();
			var ul = $("<ul></ul>").addClass("pagination");
			//构建元素
			var firstPageLi = $("<li></li>").append(
					$("<a></a>").append("首页").attr("href", "#"));

			var prePageLi = $("<li></li>").append(
					$("<a></a>").append("&laquo;"));
			if (result.extend.pageInfo.hasPreviousPage == false) {
				firstPageLi.addClass("disabled");
				prePageLi.addClass("disabled");
			}else{
				firstPageLi.click(function() {
					to_page(1);
				});
				prePageLi.click(function() {
					to_page(result.extend.pageInfo.pageNum - 1);
				});
			}
			var nextPageLi = $("<li></li>").append(
					$("<a></a>").append("&raquo;"));
			var lastPageLi = $("<li></li>").append(
					$("<a></a>").append("末页").attr("href", "#"));
			if (result.extend.pageInfo.hasNextPage == false) {
				nextPageLi.addClass("disabled");
				lastPageLi.addClass("disabled");
			}else{
				
				lastPageLi.click(function() {
					to_page(result.extend.pageInfo.pages);
				});
				nextPageLi.click(function() {
					to_page(result.extend.pageInfo.pageNum + 1);
				});
			}
		

			ul.append(firstPageLi).append(prePageLi);
			$.each(result.extend.pageInfo.navigatepageNums, function(index,
					item) {

				var li = $("<li></li>").append($("<a></a>").append(item));
				if (result.extend.pageInfo.pageNum == item) {
					li.addClass("active");
				}
				li.click(function() {
					to_page(item);
				});
				ul.append(li);
			});

			ul.append(nextPageLi).append(lastPageLi);
			$("<nav></nav>").append(ul).appendTo("#page_nav_area");

		}
	</script>
</body>
</html>