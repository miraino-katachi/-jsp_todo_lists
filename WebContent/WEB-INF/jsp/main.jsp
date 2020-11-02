<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>TODOリスト</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
	<style>
		.isCompleted {
			text-decoration: double line-through;
		}
	</style>
</head>
<body>
	<div class="container-md">
		<div class="row my-3">
			<div class="col-md">
				<h1>TODOリスト</h1>
				<form action="./Main" method="post">
					<div class="form-row">
						<div class="col">
							<input type="date" name="expirationDate" value="<c:out value="${today}" />" class="form-control">
						</div>
						<div class="col-6">
							<input type="text" name="todoItem" class="form-control">
						</div>
						<div class="col">
							<input type="submit" value="登録" class="btn btn-primary">
						</div>
					</div>
				</form>
			</div>
		</div>
		<div class="row my-3">
			<div class="col-md">
				<div class="table-responsive">
					<table class="table">
						<tr>
							<th>期限日</th>
							<th>項目</th>
							<th>登録日/更新日</th>
							<th></th>
						</tr>
						<c:forEach var="list" items="${todoList}">
						<tr
							<c:if test="${list.expirationDate <= today}">class="text-danger"</c:if>
						>
							<td class="text-nowrap
								<c:if test="${list.isCompleted==1}"> isCompleted</c:if>
							">
								<%-- java.sql.Date型の値をフォーマットする --%>
								<fmt:formatDate var="date" value="${list.expirationDate}" pattern="yyyy年MM月dd日"/>
								<c:out value="${date}" /><br>
							</td>
							<td
								<c:if test="${list.isCompleted==1}">class="isCompleted"</c:if>
							>
								<c:out value="${list.todoItem}" /></td>
							<td>
								<%-- java.sql.Timestamp型の値をフォーマットする --%>
								<fmt:formatDate var="createDateTime" value="${list.createDateTime}" pattern="yyyy/MM/dd HH:mm:ss"/>
								<small><c:out value="${createDateTime}" /></small><br>
								<fmt:formatDate var="updateDateTime" value="${list.updateDateTime}" pattern="yyyy年MM月dd日HH時mm分ss秒"/>
								<small><c:out value="${updateDateTime}" /></small>
							</td>
							<td class="text-nowrap text-right">
								<form action="./Update" method="post" class="inline-form">
									<input type="hidden" name="id" value="<c:out value="${list.id}" />">
									<div class="custom-control custom-radio custom-control-inline">
  										<input type="radio" id="isNotCompleted<c:out value="${list.id}" />" name="isCompleted" value="0" class="custom-control-input"<c:if test="${list.isCompleted==0}"> checked</c:if>>
  										<label class="custom-control-label" for="isNotCompleted<c:out value="${list.id}" />">未完了</label>
									</div>
									<div class="custom-control custom-radio custom-control-inline">
  										<input type="radio" id="isCompleted<c:out value="${list.id}" />" name="isCompleted" value="1" class="custom-control-input"<c:if test="${list.isCompleted==1}"> checked</c:if>>
  										<label class="custom-control-label" for="isCompleted<c:out value="${list.id}" />">完了</label>
									</div>
									<div class="custom-control custom-checkbox custom-control-inline">
  										<input type="checkbox" id="isDeleted<c:out value="${list.id}" />" name="isDeleted" class="custom-control-input">
  										<label class="custom-control-label" for="isDeleted<c:out value="${list.id}" />">削除</label>
									</div>
									<input type="submit" value="実行" class="btn btn-outline-primary">
								</form>
							</td>
						</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>