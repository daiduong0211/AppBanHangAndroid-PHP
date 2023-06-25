<?php
include "connect.php";
$loai = $_POST['loai'];

$query = ' SELECT * FROM  `sanphammoi` WHERE `loai`='.$loai.' ';
$data = mysqli_query($conn, $query);
$result = array();
while ($row = mysqli_fetch_assoc($data)) {
	$result[] = ($row);
	// code...
}
if(!empty($result)) {
	$arr = [
		'success' => true,
		'message' => "thanhcong",
		'result' => $result
	];
}else{
		$arr = [
		'success' => false,
		'message' => "khong thanh cong",
		'result' => $result
	];
}

print_r(json_encode($arr));
?>