<?php
//
// $con = new mysqli("localhost", "root", "7878", "test");
//
// $userID = $_POST["userID"];
//
// $query = "select * from USER where userID = " . $userID;
// $result = $con->query($query);
// $row = $result->fetch_assoc();
//
// $row['userID'] = $userID;
// $row['userName'] = $userName;
// $row['userAge'] = $userAge;
$con = mysqli_connect("localhost", "root", "7878", "test");
$userID = $_POST["userID"];

$result = mysqli_query($con, "select * from USER" );
$response = array();

while ($row = mysqli_fetch_array($result)) {
	array_push($response, array("userID"=>$row[0], "userPassword"=>$row[1], "userName"=>$row[2], "userAge"=>$row[3]));
}
	echo json_encode(array("response"=>$response));

?>
