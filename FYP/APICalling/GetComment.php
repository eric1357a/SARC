<?php
// required headers
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");

include_once '../Config/Database.php';
include_once '../Model/Comment.php';

$database = new Database();
$db = $database->getConnection();

$comment = new Comment($db);
$stmt = $comment->getAllComment();
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

?>