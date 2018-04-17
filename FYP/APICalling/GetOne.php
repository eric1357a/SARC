<?php
// required headers
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Headers: access");
header("Access-Control-Allow-Methods: GET");
header("Access-Control-Allow-Credentials: true");
header('Content-Type: application/json');

include_once '../Config/Database.php';
include_once '../Model/Restaurant.php';

$database = new Database();
$db = $database->getConnection();

$restaurant = new Restaurant($db);

$restaurant->id = isset($_GET['id']) ? $_GET['id'] : die();

$restaurant->getOneInfo();

$restaurant_item = array(
            "id" => $restaurant->restaurant_id,
            "name" => $restaurant->restaurant_name,
            "description" => html_entity_decode($restaurant->restaurant_info),
            "latitude" => $restaurant->restaurant_latitude,
            "longitude" => $restaurant->restaurant_longitude,
            "comment" => html_entity_decode($restaurant->restaurant_comment),
            "imgSrc" => $restaurant->restaurant_img
);
print_r(json_encode($restaurant_item));
?>