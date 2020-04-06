<?php
	/* Para Traer un personal especifico se usa esta url*/
	/*http://127.0.0.1/API/Proyecto_peluqueria/detalle_personal.php?cedula=0945789523*/
	header('Content-type: application/json');
	require 'BASE/BDD.php';
	$id = $_GET['activividad'];
	$data = BDD::QUERY("SELECT * from actividad WHERE id_actividad = $id");
	if ($data) {
		echo json_encode($data, JSON_NUMERIC_CHECK | JSON_UNESCAPED_UNICODE | JSON_PRESERVE_ZERO_FRACTION | JSON_PRETTY_PRINT);
	}else{
		echo "Cedula no registrada en el sistema";
	}
 ?>