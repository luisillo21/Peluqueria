<?php
	/* Para Traer un personal especifico se usa esta url*/
	/*http://127.0.0.1/API/Proyecto_peluqueria/detalle_personal.php?cedula=0945789523*/
	header('Content-type: application/json');
	require 'BASE/BDD.php';
	error_reporting(E_ERROR | E_PARSE);
	date_default_timezone_set('GMT/UTC');
	
	$fecha = $_POST["fecha"];
	$nueva_fecha = date("Y-m-d", strtotime($fecha));
	
	$hora_inicio = $_POST['hora_inicio'];
	$inicio =date('H:i:s',$hora_inicio);

	$hora_fin = $_POST['hora_fin'];
	$fin = date('H:i:s',$hora_fin);

	$sql = "INSERT INTO horario (fecha,hora_inicio,hora_fin) VALUES ('".$nueva_fecha."', '".$inicio."', '".$fin."')";
	$conexion = BDD::CONECTAR();
	$resultado = mysqli_query($conexion,$sql);
	if ($resultado) {
		echo json_encode($data, JSON_NUMERIC_CHECK | JSON_UNESCAPED_UNICODE | JSON_PRESERVE_ZERO_FRACTION | JSON_PRETTY_PRINT);
	}else{

		echo json_encode(mysql_errno(), JSON_NUMERIC_CHECK | JSON_UNESCAPED_UNICODE | JSON_PRESERVE_ZERO_FRACTION | JSON_PRETTY_PRINT);
	}
 ?>