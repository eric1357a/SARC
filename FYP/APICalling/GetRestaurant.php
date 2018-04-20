<?php
// required headers
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");

include_once '../Config/Database.php';
include_once '../Model/Restaurant.php';

$database = new Database();
$db = $database->getConnection();

$restaurant = new Restaurant($db);
$stmt = $restaurant->getAllRestaurantInfo();
$countNum = $stmt->rowCount();

if($countNum > 0){
 
    $restaurants_arr = array();
    $restaurants_arr["restaturant_list"] = array();

    while ($row = $stmt->fetch(PDO::FETCH_ASSOC)){
        extract($row);
 
        $restaurant_items = array(
            "id" => $id,
            "name" => $name,
            "address" => html_entity_decode($address),
            "price" => $price,
            "type1" => $type1,
			"type2" => $type2,
			"type3" => $type3,
			"type4" => $type4,
			"region" => $region,
			"avg_price" => $avg_price,
            "simp_type" => html_entity_decode($restaurant_comment),
			"Districts" => $Districts,
			"lon" => $lon,
            "lat" => $lat
        );
 
        array_push($restaurants_arr["restaturant_list"], $restaurant_items);
    }
 
    echo json_encode($restaurants_arr);
}else{
    echo json_encode(
        array("message" => "No Restaurants info found.")
    );
}

?>