<?php
    class Database{
        private $host = "localhost";
        private $db_name = "FYP_DB";
        private $db_user = "fyp";
        private $db_ps = "a12345678A";
        public $conn;

        public function getConnection(){
            $this->conn = null;

            try{
                $this->conn = new PDO("mysql:host=" . $this->host . ";dbname=" . $this->db_name, $this->db_user, $this->db_ps);
                $this->conn->exec("set names utf8");
            }catch(PDOException $exception){
                echo "Connection error: " . $exception->getMessage();
            }
            return $this->conn;
        }
    }
?>