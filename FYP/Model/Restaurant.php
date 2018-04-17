<?php
    class Restaurant{
        private $conn;
        private $table_name = "Restaurant";

        public $restaurant_id;
        public $restaurant_name;
        public $restaurant_info;
        public $restaurant_longitude;
        public $restaurant_latitude;
        public $restaurant_comment;
        public $imgSrc;

        public function __construct($db){
            $this->conn = $db;
        }

        function getAllRestaurantInfo(){
            $query = "SELECT * FROM " . $this->table_name . " ORDER BY restaurant_id DESC";
            // prepare query statement
            $stmt = $this->conn->prepare($query);

            // execute query
            $stmt->execute();

            return $stmt;
        }
        
		function getOneInfo(){
 
    		// query to read single record
    		$query = "SELECT * FROM " . $this->table_name . " WHERE restaurant_id = ? LIMIT 0,1";
 
    		// prepare query statement
    		$stmt = $this->conn->prepare( $query );
 
    		// bind id of product to be updated
    		$stmt->bindParam(1, $this->id);
 
    		// execute query
    		$stmt->execute();
 
    		// get retrieved row
    		$row = $stmt->fetch(PDO::FETCH_ASSOC);
 
   			// set values to object properties
    		$this->restaurant_id = $row['restaurant_id'];
    		$this->restaurant_name = $row['restaurant_name'];
    		$this->restaurant_info = $row['restaurant_info'];
    		$this->restaurant_latitude = $row['restaurant_latitude'];
    		$this->restaurant_longitude = $row['restaurant_longitude'];
    		$this->restaurant_comment = $row['restaurant_comment'];
    		$this->restaurant_img = $row['imgSrc'];
		}
    }
?>