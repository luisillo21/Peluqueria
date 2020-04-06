<?php
	header('Content-Type: application/json');
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

$usuario = $_GET['usuario'];

$query = "SELECT id, usuario,clave,id_rol,usuario.estado,rol.descripcion,rol_id from usuario,rol where rol_id = id_rol and usuario='".$usuario."';";


function disconnectDB($conexion){
    $close = mysqli_close($conexion);
        if($close){
           // echo 'La desconexion de la base de datos se ha hecho satisfactoriamente';
        }else{
            echo 'Ha sucedido un error inexperado en la desconexion de la base de datos';
        }   
    return $close;
}

function getArraySQL($sql){
    $conexion = BDD::CONECTAR();
    if(!$result = mysqli_query($conexion, $sql)) die(); 
    $rawdata = array();
    $i=0;
    while($row = mysqli_fetch_assoc($result))
   	 	{
        	$rawdata[$i] = $row;
        	$i++;
    	}
    disconnectDB($conexion); 
    return $rawdata;
}

$lst_usuario = getArraySQL($query);


	if ($lst_usuario) {			
		foreach ($lst_usuario as $v) {
			$array = array("id"=>$v["id"],"usuario" => $v["usuario"],"clave"=>$v["clave"],"estado"=>$v["estado"],"rol_id"=>$v["rol_id"] ]);

		}


		echo json_encode($array, JSON_NUMERIC_CHECK | JSON_UNESCAPED_UNICODE | JSON_PRESERVE_ZERO_FRACTION | JSON_PRETTY_PRINT);	
	}else{
		echo json_encode("error", JSON_NUMERIC_CHECK | JSON_UNESCAPED_UNICODE | JSON_PRESERVE_ZERO_FRACTION | JSON_PRETTY_PRINT);
	}

 ?>