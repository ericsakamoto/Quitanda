<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html ng-app="quitanda">
<head>
	<meta charset="UTF-8">
	<title>Quitanda - Incluir Compras</title>
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.3/angular.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script>
	var quitanda_app = angular.module('quitanda', []);
		
	quitanda_app.controller('IncluirCompra', function($scope, $http) {	
		
	    // Listar Tipos
		$http.get('http://${hostname}/type/listAll').
    		then(function(response) {
    			$scope.listaTipos = response.data.responseData;
    		});
	    
		// Listar Unidades de medida
	    $http.get('http://${hostname}/unit/listAll').
			then(function(response) {
				$scope.listaUnidades = response.data.responseData;
			});
	    
	    // Listar Produtos por Tipo
		$scope.listarProdutos = function() {
   			$http.get('http://${hostname}/product/get-by-type',{params:{tipo: $scope.tipos}}).
    		then(function(response) {
    			$scope.listaProdutos = response.data.responseData;
    		});
		};
		
	    // Inserir Compra
		$scope.inserirCompra = function() {
	    	
	    	// Validar se campos foram preenchidos
	    	if($scope.validarCampos()==false) {
	    		return;
	    	}
	    	
	    	// String produto, String tipo, String unidade, String quantidade, String preco, String data
			$http.get('http://${hostname}/purchase/create',{params:{produto: $scope.produtos,tipo: $scope.tipos,unidade: $scope.unidades,quantidade: $scope.quantidade,preco: $scope.preco,data: $scope.data}}).
    		then(function(response) {
				$scope.tipos="";
				$scope.produtos="";
				$scope.unidades="";
				$scope.quantidade="";
				$scope.preco="";
				document.getElementById("tipos").focus();
				alert(response.data.returnCode + " - " + response.data.responseMessage);
    		});
		};
		
		$scope.limparCampos = function() {
			$scope.unidades="";
			$scope.quantidade="";
		};
		
		$scope.validarCampos = function() {
			if($scope.data==null) {
				alert("Favor preencher a data");
				return false;
			}
			
			if($scope.tipos=="" || typeof $scope.tipos=='undefined') {
				alert("Favor preencher o tipo");
				return false;
			}
			
			if($scope.produtos=="" || typeof $scope.produtos=='undefined') {
				alert("Favor preencher o produto");
				return false;
			}
			
			if($scope.unidades=="" || typeof $scope.unidades=='undefined') {
				alert("Favor preencher a unidade");
				return false;
			}
			
			if($scope.quantidade=="" || typeof $scope.quantidade=='undefined') {
				alert("Favor preencher a quantidade");
				return false;
			}
			
			if($scope.preco=="" || typeof $scope.preco=='undefined') {
				alert("Favor preencher o preço");
				return false;
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
					<li><a href="/compras">Consultar Compras</a></li>
					<li class="active"><a href="/compras/incluir">Incluir Compras</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div ng-app="quitanda" ng-controller="IncluirCompra">
		<div class="row">
			<div class="col-sm-4"></div>
			<div class="col-sm-4"></div>
			<div class="col-sm-4"></div>
		</div>
		<div class="row">
			<div class="col-sm-1"></div>
		</div>
		<div class="row">
			<div class="col-sm-4"></div>
			<div class="col-sm-4">
				<form name="formCompras">
					<div class="form-group">
	  					<label for=""data"">Data:</label>
	  					<input type="date" class="form-control" id="data" ng-model="data"></input>
					</div>
					<div class="form-group">
	  					<label for=""data"">Tipo:</label>
	  					<select id="tipos" ng-model="tipos" 
	  										ng-change="listarProdutos()" 
	  										ng-options="tipo.nome as tipo.nome for tipo in listaTipos" 
	  										class="form-control">
    						<option value="">Selecione um tipo</option>
						</select>
					</div>
					<div class="form-group">
	  					<label for=""data"">Produto:</label>
	  					<select id="produtos" ng-model="produtos" 
	  											ng-options="produto.nome as produto.nome for produto in listaProdutos" 
	  											class="form-control">
    						<option value="">Selecione um produto</option>
						</select>
					</div>
					<div class="form-group">
	  					<label for=""data"">Unidade:</label>
	  					<select id="unidades" ng-model="unidades" 
	  											ng-options="unidade.nome as unidade.nome for unidade in listaUnidades" 
	  											class="form-control">
    						<option value="">Selecione uma unidade</option>
						</select>
					</div>
					<div class="form-group">
	  					<label for=""quantidade"">Quantidade:</label>
	  					<input id="quantidade" type="text" class="form-control" ng-model="quantidade" placeholder="0"/>
					</div>
					<div class="form-group">
	  					<label for=""data"">Preco:</label>
	  					<input type="number" class="form-control" id="preco" ng-model="preco" placeholder="00.00"/>
					</div>
					<div class="form-group">
						<button class=".btn-primary" ng-click="inserirCompra()">Inserir</button>
					</div>
				</form>
			</div>
			<div class="col-sm-4"></div>
		</div>
	</div>
</body>
</html>