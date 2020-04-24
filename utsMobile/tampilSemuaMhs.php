<?php

//Import File Koneksi Database
require_once('koneksi.php');

//Membuat SQL Query
$sql = "SELECT * FROM mahasiswa";

//Mendapatkan Hasil
$r = mysqli_query($con, $sql);

//Membuat Array Kosong
$result = array();

while ($row = mysqli_fetch_array($r)) {

	//Memasukkan Nama dan ID kedalam Array Kosong yang telah dibuat
	array_push($result, array(
		"Kode_mhs" => $row['Kode_mhs'],
		"Nama_mhs" => $row['Nama_mhs'],
		"Tgl_lhr" => $row['Tgl_lhr'],
		"Jns_kel" => $row['Jns_kel'],
		"Agama" => $row['Agama'],
		"Gol_darah" => $row['Gol_darah'],
		"Status" => $row['Status_mhs'],
		"Kota" => $row['Kota']
	));
}

//Menampilkan Array dalam Format JSON
echo json_encode(array('result' => $result));

mysqli_close($con);
