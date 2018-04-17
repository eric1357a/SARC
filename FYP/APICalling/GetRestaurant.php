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
            "id" => $restaurant_id,
            "name" => $restaurant_name,
            "description" => html_entity_decode($restaurant_info),
            "latitude" => $restaurant_latitude,
            "longitude" => $restaurant_longitude,
            "comment" => html_entity_decode($restaurant_comment),
            "imgSrc" => $imgSrc
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