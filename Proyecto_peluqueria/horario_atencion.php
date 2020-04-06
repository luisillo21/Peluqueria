<?php
	header('Content-type: application/json');
	require 'BASE/BDD.php';
	$data = BDD::QUERY('SELECT nombre,cedula,descripcion as actividad,precio,fecha,hora_inicio,hora_fin from horarios_atencion,personal,horario,actividad
			where personal = id_personal and actividad = id_actividad and horario = id_horario');
	if ($data) {
		echo json_encode($data, JSON_NUMERIC_CHECK | JSON_UNESCAPED_UNICODE | JSON_PRESERVE_ZERO_FRACTION | JSON_PRETTY_PRINT);
	}else{
		echo json_encode("$data", JSON_NUMERIC_CHECK | JSON_UNESCAPED_UNICODE | JSON_PRESERVE_ZERO_FRACTION | JSON_PRETTY_PRINT);
	}
 ?>