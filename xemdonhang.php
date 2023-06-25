<?php
include "connect.php";
$iduser = $_POST['iduser'];
if ($iduser==0) {
	// code...
	// nếu iduser ==0 thì lấy tất cả các user
	$query = " SELECT * FROM `donhang` ORDER BY id DESC ";
}else{
	//lấy id user trong bảng đơn hàng
	$query = 'SELECT * FROM `donhang` WHERE `iduser` = '.$iduser.' ORDER BY id DESC ';
}
//
$data =mysqli_query($conn, $query);
$result = array();
while ($row = mysqli_fetch_assoc($data)) {
	// lấy các hàng có iddonhang = id trong mảng row
	$truyvan = 'SELECT * FROM `chitietdonhang` INNER JOIN sanphammoi ON chitietdonhang.idsp = sanphammoi.id WHERE chitietdonhang.iddonhang =' .$row['id'];
	$data1 = mysqli_query($conn, $truyvan);
	$item = array();
	while($row1 = mysqli_fetch_assoc($data1)) {
		$item[] = $row1;
	}

	$row['item'] = $item;
	$result[]=($row);
	
	}
	if(!empty($result)) {
		$arr = [
			'success' => true,
			'message' => "thanh cong",
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
