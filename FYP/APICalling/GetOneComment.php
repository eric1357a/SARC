<?php
// required headers
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Headers: access");
header("Access-Control-Allow-Methods: GET");
header("Access-Control-Allow-Credentials: true");
header('Content-Type: application/json');

include_once '../Config/Database.php';
include_once '../Model/Comment.php';

$database = new Database();
$db = $database->getConnection();

$comment = new Comment($db);

$comment->id = isset($_GET['id']) ? $_GET['id'] : die();

$stmt = $comment->getOneResInfo();
$countNum = $stmt->rowCount();

if($countNum > 0){
 
    $comment_arr = array();
    $comment_arr["comment_list"] = array();

    while ($row = $stmt->fetch(PDO::FETCH_ASSOC)){
        extract($row);
 
        $comment_items = array(
            "comment_id" => $comment_id,
            "content" => $comment_content,
            "restaurant_id" => $restaurant_id,
            "post_date" => $post_date
        );
 
        array_push($comment_arr["comment_list"], $comment_items);
    }
 
    echo json_encode($comment_arr);
}else{
    echo json_encode(
        array("message" => "No Comment found.")
    );
}



/*
$comment->getOneResInfo();

$comment_items = array(
            "comment_id" => $comment->comment_id,
            "content" => $comment->comment_content,
            "restaurant_id" => $comment->restaurant_id,
            "post_date" => $comment->post_date
        );
print_r(json_encode($comment_items));
*/
?>