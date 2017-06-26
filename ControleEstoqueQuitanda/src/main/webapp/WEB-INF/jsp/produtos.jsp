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
	
	quitanda_app.controller('Produtos', function($scope, $http, $modal) {
	
		// Listar Produtos
		$http.get('http://${hostname}/type/listAll').
    		then(function(response) {
    			$scope.listaTipos = response.data.responseData;
    		});

	    // Listar Produtos por Tipo
		$scope.buscarProdutosPorTipo = function() {
		
	    	// Validar se campos foram preenchidos
	    	if($scope.validarCampos()==false) {
	    		return;
	    	}		
		
			// Buscar produtos 
   			$http.get('http://${hostname}/product/get-by-type',{params:{tipo: $scope.tipos}}).
    		then(function(response) {
    			$scope.produtos = response.data;
    		});
		};
		
		// Validar campos
		$scope.validarCampos = function() {

			if($scope.tipos=="" || typeof $scope.tipos=='undefined') {
				alert("Favor preencher o tipo");
				return false;
			}
		};
		
		// Alterar produto
		$scope.open = function(id) {
		
			// Buscar produto 
   			$http.get('http://${hostname}/product/get-by-id',{params:{id: id}}).
    		then(function(response) {
    			$scope.nome = response.data.produto.nome;
    			$scope.tipo = response.data.produto.tipo;
    			$scope.unidade = response.data.produto.unidade;
    			$scope.unidade = response.data.produto.id;

				$scope.open = function () {
			    	var modalInstance = $modal.open({
						templateUrl : 'modal_produtos',
						controller : modalController,
						resolve : {
							items : function() {
								return $scope.items;
							},
							key : function() {
								return key;
							}
						}
					});
				};
			});
		};	
		
	};
		
	var modalController = function ($scope, $modalInstance, items, key) {
		alert("The key is " + key);
		$scope.items = items;
		$scope.selected = {
			item: $scope.items[0]
		};

	  	$scope.ok = function () {
	    	$modalInstance.close($scope.selected.item);
	  	};
	
	  	$scope.cancel = function () {
	    	$modalInstance.dismiss('cancel');
	  	};
	};
	</script>
</head>

<body>
	<!-- *** MENU *** -->
	<nav class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">${applicationName}</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="/compras">Consultar Compras</a></li>
					<li><a href="/compras/incluir">Incluir Compras</a></li>
					<li class="active"><a href="/produtos">Consultar Produtos</a></li>					
				</ul>
			</div>
		</div>
	</nav>
	<!-- *** MENU *** -->
	<div class="container">
		<div class="row">
			<!-- div class="col-sm-4"></div -->
			<div class="col-sm-12">
				<div ng-controller="Produtos">
				    <div class="container">
					  <form class="form-search">
						<div class="form-group">
	  						<label for=""data"">Tipo:</label>
	  						<select id="tipos" ng-model="tipos" 
	  											ng-options="tipo.nome as tipo.nome for tipo in listaTipos" 
	  											class="form-control">
    							<option value="">Selecione um tipo</option>
							</select>
					    	<button class="btn btn-md btn-primary btn-block" ng-click="buscarProdutosPorTipo()">Buscar Produtos</button>
					  </form>
					</div>
					<br>
					<table class="table table-striped">
						<tr>
							<th>NOME</th>
							<th>TIPO</th>
							<th>UNIDADE</th>
							<th></th>
						</tr>
						<tr ng-repeat="produto in produtos.responseData">
							<td aling="center">{{ produto.nome }}</td>
							<td aling="center">{{ produto.tipo }}</td>
							<td aling="center">{{ produto.unidade }}</td>
							<td aling="center"><button class="btn" data-toggle="modal" data-target="#modalProduto" ng-click="editarProduto(produto.id)">Editar</button></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
	<script type="text/ng-template" id="modal_produtos">
        <div class="modal-header">
            <h3>I'm a modal!</h3>
        </div>
        <div class="modal-body">
            <ul>
                <li ng-repeat="item in items">
                    <a ng-click="selected.item = item">{{ item }}</a>
                </li>
            </ul>
            Selected: <b>{{ selected.item }}</b>
        </div>
        <div class="modal-footer">
            <button class="btn btn-primary" ng-click="ok()">OK</button>
            <button class="btn btn-warning" ng-click="cancel()">Cancel</button>
        </div>

	<div class="modal fade" id="modalProduto" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Editar Produto</h4>
				</div>
				<div class="modal-body">
					<p>Produto: {{ produto_editar.nome }}</p>
					<form class="form-search">
						<div class="form-group">
	  						<label for=""nome"">Nome:</label>
	  						<input id="nome" type="text" class="form-control" ng-model="nome"/>
						</div>
						<div class="form-group">
	  						<label for=""nome"">Unidade:</label>
	  						<input id="nome" type="text" class="form-control" ng-model="unidade"/>
						</div>
						<div class="form-group">
	  						<label for=""nome"">Tipo:</label>
	  						<input id="nome" type="text" class="form-control" ng-model="tipo"/>
						</div>
						<div class="form-group">
	  						<label for=""nome"">id:</label>
	  						<input id="nome" type="text" class="form-control" ng-model="id"/>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-md btn-primary" data-dismiss="modal">Salvar</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
				</div>
			</div>
		</div>
	</div>
    </script>
</body>
</html>