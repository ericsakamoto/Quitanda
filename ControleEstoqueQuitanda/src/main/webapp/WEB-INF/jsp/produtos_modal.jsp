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
	
	</script>
</head>
<body>
	<!-- Modal -->
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
</body>
</html>