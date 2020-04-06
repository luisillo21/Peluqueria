<?php 
	header('Content-Type: application/json');
	require 'BASE/BDD.php';
	$lista = $_POST["personal"];

	$consulta = BDD::QUERY("select * from personal where estado='A'");

	foreach ($v as $lista) {
		foreach ($c as $consulta) {
			
		}
	}




 ?>