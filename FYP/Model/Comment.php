<?php
    class Comment{
        private $conn;
        private $table_name = "Restaurant_comment";

        public $comment_id;
        public $comment_content;
        public $restaurant_id;
        public $post_date;

        public function __construct($db){
            $this->conn = $db;
        }

        function getAllComment(){
            $query = "SELECT * FROM " . $this->table_name . " ORDER BY post_date ASC";
            // prepare query statement
            $stmt = $this->conn->prepare($query);

            // execute query
            $stmt->execute();

            return $stmt;
        }
        
		function getOneResInfo(){
			$query = "SELECT * FROM " . $this->table_name . " WHERE restaurant_id = ? ORDER BY post_date ASC";
            // prepare query statement
            $stmt = $this->conn->prepare($query);
			// bind id of product to be updated
    		$stmt->bindParam(1, $this->id);
            // execute query
            $stmt->execute();

            return $stmt;
 /*
    		// query to read single restaurant record
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
    		$this->comment_id = $row['comment_id'];
    		$this->comment_content = $row['comment_content'];
    		$this->restaurant_id = $row['restaurant_id'];
			$this->post_date = $row['post_date'];
			*/
		}
		function insertComment(){
			 $query = "INSERT INTO
                " . $this->table_name . "
            SET
                comment_content=:comment_content, restaurant_id=:restaurant_id, post_date=:post_date";
 
    // prepare query
    $stmt = $this->conn->prepare($query);
 
    // sanitize
    $this->comment_content=htmlspecialchars(strip_tags($this->comment_content));
    $this->restaurant_id=htmlspecialchars(strip_tags($this->restaurant_id));
    $this->post_date=htmlspecialchars(strip_tags($this->post_date));
 
    // bind values
    $stmt->bindParam(":comment_content", $this->comment_content);
    $stmt->bindParam(":restaurant_id", $this->restaurant_id);
    $stmt->bindParam(":post_date", $this->post_date);
 
    // execute query
    if($stmt->execute()){
        return true;
    }
 
    return false;
		}
    }
?>