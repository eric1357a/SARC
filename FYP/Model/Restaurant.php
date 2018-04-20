<?php
    class Restaurant{
        private $conn;
        private $table_name = "Restaurant";

        public $id;
        public $name;
        public $address;
        public $price;
        public $type1;
        public $type2;
        public $type3;
		public $type4;
		public $region;
		public $avg_price;
		public $simp_type;
		public $Districts;
		public $lon;
		public $lat;

        public function __construct($db){
            $this->conn = $db;
        }

        function getAllRestaurantInfo(){
            $query = "SELECT * FROM Restaurant ORDER BY id ASC ";
            // prepare query statement
            $stmt = $this->conn->prepare($query);

            // execute query
            $stmt->execute();

            return $stmt;
        }
        
		function getOneInfo(){
 
    		// query to read single record
    		$query = "SELECT * FROM " . $this->table_name . " WHERE id = ? LIMIT 0,1";
 
    		// prepare query statement
    		$stmt = $this->conn->prepare( $query );
 
    		// bind id of product to be updated
    		$stmt->bindParam(1, $this->id);
 
    		// execute query
    		$stmt->execute();
 
    		// get retrieved row
    		$row = $stmt->fetch(PDO::FETCH_ASSOC);
 
   			// set values to object properties
    		$this->id = $row['id'];
    		$this->name = $row['name'];
    		$this->address = $row['address'];
    		$this->price = $row['price'];
    		$this->type1 = $row['type1'];
    		$this->type2 = $row['type2'];
    		$this->type3 = $row['type3'];
			$this->type4 = $row['type4'];
			$this->region = $row['region'];
			$this->avg_price = $row['avg_price'];
			$this->simp_type = $row['simp_type'];
			$this->Districts = $row['Districts'];
			$this->lon = $row['lon'];
			$this->lat = $row['lat'];
		}
    }
?>