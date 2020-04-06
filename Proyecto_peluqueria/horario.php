<?php
	header('Content-type: application/json');
	require 'BASE/BDD.php';
	$data = BDD::QUERY('SELECT * from horario');
	if ($data) {
		echo json_encode($data, JSON_NUMERIC_CHECK | JSON_UNESCAPED_UNICODE | JSON_PRESERVE_ZERO_FRACTION | JSON_PRETTY_PRINT);	
	}else{
		echo json_encode("No existen personas registradas", JSON_NUMERIC_CHECK | JSON_UNESCAPED_UNICODE | JSON_PRESERVE_ZERO_FRACTION | JSON_PRETTY_PRINT);
	}
	
 ?>