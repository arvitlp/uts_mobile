<?php
require_once('koneksi.php');

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
	//MEndapatkan Nilai Dari Variable
	$kode = $_POST['Kode_mhs'];
	$nama = $_POST['Nama_mhs'];
	$tanggal = $_POST['Tgl_lhr'];
	$jeniskel = $_POST['Jns_kel'];
	$agama = $_POST['Agama'];
	$goldar = $_POST['Gol_darah'];
	$status = $_POST['Status_mhs'];
	$kota = $_POST['Kota'];

	//import file koneksi database


	//Membuat SQL Query
	$sql = "UPDATE tb_mahasiswa SET Nama_mhs = '$nama', Tgl_lhr = '$tanggal', Jns_Kel = '$jeniskel', Agama = '$agama', Gol_darah = '$goldar', Status_mhs = '$status', Kota = '$kota' WHERE kode = '$kode'";


	//Meng-update Database
	if (mysqli_query($con, $sql)) {
		echo 'Berhasil Update Data Mahasiswa';
	} else {
		echo 'Gagal Update Data Mahasiswa';
	}
	mysqli_close($con);
}
