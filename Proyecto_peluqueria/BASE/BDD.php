<?php

class BDD
{
    private static $SERVIDOR = "localhost";
    private static $NAME_BASE = "peluqueria";
    private static $NAME_USER = "root";
    private static $PASS = "root";
    public $conexion;
    static public function CONECTAR()
    {
        $conexion = new mysqli(self::$SERVIDOR,self::$NAME_USER,self::$PASS, self::$NAME_BASE);
        if ($conexion->connect_errno) {
            echo "Fallo al conectar a MySQL: (" . $conexion->connect_errno . ") " . $conexion->connect_error;
        }
        return $conexion;
    }
    
    static public function CONSULTAR($tabla,$campo,$where = "")
    {
        $mysql = self::CONECTAR();
        if (!$tabla || !$campo )
        {
            return;
        }
        $sql = "";
        if ($where) $where = "where $where";

        $sql .= "SELECT $campo FROM $tabla $where ;";

        if(!$res = $mysql->query($sql))
        {
            echo "consulta: ".$sql." \n";
            echo "Erro en consulta: ".$mysql->errno ." \n";
            echo "Error: " . $mysql->error . "\n";
            exit;
        }else{
            if ($res->num_rows === 0)
            {
                return false;
            }else{
                $regs = $res->fetch_assoc();
                return $regs;
            }
        }
    }
    static public function  QUERY($read)
    {
        $mysql = self::CONECTAR();
        if (!$read)
        {
            return;
        }

        if(!$res = $mysql->query($read))
        {
            echo "consulta: ".$read." \n";
            echo "Erro en consulta: ".$mysql->errno ." \n";
            echo "Error: " . $mysql->error . "\n";
            exit;
        }else{
            if ($res->num_rows === 0)
            {
                $regs = array();
                return $regs;
            }else{
                $regs = array();
                while($row = $res->fetch_assoc())
                {
                    $regs[] = array_change_key_case($row, CASE_LOWER);
                }
                return $regs;
            }
        }
    }

    static public function INSERTAR_DESDE_ARRAY($tabla,$array= array(),$w ="")
    {
        $mysql = self::CONECTAR();
        if (!$tabla) return;
        if (empty($array))
        {
            return;
        }
        $strFlds = "";
        $delim = "";
        foreach ($array as $k => $v)
        {
            $k=strtolower($k);
            $strFlds = $strFlds . $delim . $k;
            $delim = ",";
        }
        $strVals = "";
        $delim = "";
        foreach ($array as $v)
        {
            $strVals = $strVals. $delim . "'".$v."'";
            $delim = ",";
        }
        $q = "INSERT INTO $tabla ($strFlds) VALUES ($strVals)";
        $res = mysqli_query($mysql,$q);
        $id = mysqli_insert_id($mysql);
        if ($res){
          return $id;
        }else{
            echo "Erro en consulta: ".$mysql->errno ." \n";
            echo "Error: " . $mysql->error . "\n";
            echo "insert $q \n";
            return false;
        }
    }

    static public function ELIMINAR_DATOS($tabla,$w)
    {
        $mysql = self::CONECTAR();
        if (!$tabla) return false;
        if (!$w) return false;
        else $w = "where $w";

        $q = "DELETE FROM $tabla $w;";
        $res = mysqli_query($mysql,$q);
        if ($res){
            return true;
        }else{
            return false;
        }
    }

    static public function ACTUALIZAR_DESDE_ARRAY($tabla,$campos,$w)
    {
        $mysql = self::CONECTAR();
        if (!$tabla) return false;
        if (!$w) return false;
        else $w = "where $w";
        $strFlds = "";
        $delim = "";
        foreach ($campos as $k => $v) {
            $k = strtoupper($k);
            $strFlds = $strFlds . $delim . $k . " = " ."'". $v."'" ;
            $delim = ",";
        }
        $q = "UPDATE $tabla SET $strFlds $w";
        $res = mysqli_query($mysql,$q);
        if ($res){
            return true;
        }else{
            echo "Erro en consulta: ".$mysql->errno ." \n";
            echo "Error: " . $mysql->error . "\n";
            echo "update $q \n";
            return false;
        }
    }

}