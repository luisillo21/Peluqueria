<?php
	/* Para Traer un personal especifico se usa esta url*/
	/*http://127.0.0.1/API/Proyecto_peluqueria/detalle_personal.php?cedula=0945789523*/
	require 'BASE/BDD.php';
	header('Content-type: application/json');
	error_reporting(E_ERROR | E_PARSE);
	date_default_timezone_set('GMT/UTC');
	
	$descripcion = $_GET["descripcion"];
	$precio = (float) $_GET["precio"];

	echo $descripcion;
	echo $precio;

	$sql = "INSERT INTO actividad (descripcion,precio) VALUES ('".$descripcion."', '".$precio."')";
	$conexion = BDD::CONECTAR();
	$resultado = mysqli_query($conexion,$sql);
	if ($resultado) {
		echo json_encode("bien", JSON_NUMERIC_CHECK | JSON_UNESCAPED_UNICODE | JSON_PRESERVE_ZERO_FRACTION | JSON_PRETTY_PRINT);
	}else{

		echo json_encode(mysql_errno(), JSON_NUMERIC_CHECK | JSON_UNESCAPED_UNICODE | JSON_PRESERVE_ZERO_FRACTION | JSON_PRETTY_PRINT);
	}
 ?>