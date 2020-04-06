<?php
	header('Content-type: application/json');
	require 'BASE/BDD.php';
	/*
		Respuesta:
		[
    	  {
        	  "usuario": "admin",
         	  "clave": "admin",
              "rol": "Administrador"
    	  }
		]


	*/



	$data = BDD::QUERY("SELECT u.id,u.usuario,u.clave,r.descripcion as rol from usuario u join rol r on u.rol_id = r.id_rol where u.estado= 'A'");
	if ($data) {

		$array = array("usuario" => $data);
		echo json_encode($array, JSON_NUMERIC_CHECK | JSON_UNESCAPED_UNICODE | JSON_PRESERVE_ZERO_FRACTION | JSON_PRETTY_PRINT);
	}else{
		echo json_encode("error", JSON_NUMERIC_CHECK | JSON_UNESCAPED_UNICODE | JSON_PRESERVE_ZERO_FRACTION | JSON_PRETTY_PRINT);
	}

 ?>