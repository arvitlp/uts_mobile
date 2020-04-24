<?php


$kode = $_GET['kode'];

//Importing database
require_once('koneksi.php');

$sql = "SELECT * FROM mahasiswa WHERE Kode_mhs=$kode";

//Mendapatkan Hasil
$r = mysqli_query($con, $sql);

//Memasukkan Hasil Kedalam Array
$result = array();
$row = mysqli_fetch_array($r);
array_push($result, array(
	"Kode_mhs" => $row['Kode_mhs'],
	"Nama_mhs" => $row['Nama_mhs'],
	"Tanggal" => $row['Tgl_lhr'],
	"Jns_kel" => $row['Jns_kel'],
	"Agama" => $row['Agama'],
	"Gol_darah" => $row['Gol_darah'],
	"Status_mhs" => $row['Status_mhs'],
	"Kota" => $row['Kota']
));

//Menampilkan dalam format JSON
echo json_encode(array('result' => $result));

mysqli_close($con);
