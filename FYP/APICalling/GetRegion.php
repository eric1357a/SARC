<?php
// required headers
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");

include_once '../Config/Database.php';
include_once '../Model/Restaurant.php';

$database = new Database();
$db = $database->getConnection();

$restaurant = new Restaurant($db);
$stmt = $restaurant->getRegion();
$countNum = $stmt->rowCount();

if($countNum > 0){
 
    $region_arr = array();
    $region_arr["region_list"] = array();

    while ($row = $stmt->fetch(PDO::FETCH_ASSOC)){
        extract($row);
 
        $region_items = array(
            "region" => $region
        );
 
        array_push($region_arr["region_list"], $region_items);
    }
 
    echo json_encode($region_arr);
}else{
    echo json_encode(
        array("message" => "No region info found.")
    );
}

?>