<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html ng-app="quitanda">
<head>
	<meta charset="UTF-8">
	<title>Quitanda - Consultar Compras</title>
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.3/angular.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link href="../css/quitanda.css" rel="stylesheet" />
	<script>
	var quitanda_app = angular.module('quitanda', []);
	quitanda_app.controller('Compras', function($scope, $http) {
		$scope.buscarComprasPorData = function() {
			$http.get('http://${hostname}/purchase/get-by-date',{params:{data: $scope.data}}).
	    		then(function(response) {
	    			$scope.compras = response.data;
	    		});
		}; 
		
		$scope.excluirCompra = function(id,produto) {
			if(confirm('Deseja excluir a compra ' + id + ' - ' + produto + '?')) {
	   			$http.get('http://${hostname}/purchase/delete',{params:{id: id}}).
	    		then(function(response) {
	    			alert(response.data.responseMessage);
	    			$scope.buscarComprasPorData();
	    		});
			}
		};
	});	
	</script>
</head>

<body>
	<nav class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">${applicationName}</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="/compras">Consultar Compras</a></li>
					<li><a href="/compras/incluir">Incluir Compras</a></li>
					<li><a href="/produtos">Consultar Produtos</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container">
		<div class="row">
			<!-- div class="col-sm-4"></div -->
			<div class="col-sm-12">
				<div ng-controller="Compras">
				    <div class="container">
					  <form class="form-search">
					    <label for="data" class="sr-only">Data:</label>
					    <input type="date" class="form-control" id="data" ng-model="data" required autofocus/>
					    <button class="btn btn-lg btn-primary btn-block" ng-click="buscarComprasPorData()">Buscar</button>
					  </form>
					</div>
					<br>
					<table class="table table-striped">
						<tr ng-repeat="compra in compras.responseData">
							<td aling="center">{{ compra.produto }}</td>
							<td aling="center">{{ compra.tipo }}</td>
							<td aling="center">{{ compra.quantidade }}</td>
							<td aling="center">{{ compra.unidade }}</td>
							<td aling="center">{{ compra.preco | currency}}</td>
							<td aling="center"><button class=".btn-primary" ng-click="excluirCompra(compra.id,compra.produto)">Excluir</button></td>
						</tr>
					</table>
					<div>Total: {{ compras.soma | currency}}</div>
				</div>
			</div>
			<!--  div class="col-sm-4"></div -->
		</div>
	</div>
</body>
</html>