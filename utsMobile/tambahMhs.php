<?php

if ($_SERVER['REQUEST_METHOD'] == 'POST') {

	//Mendapatkan Nilai Variable
	$kode = $_POST['Kode_mhs'];
	$nama = $_POST['Nama_mhs'];
	$tanggal = $_POST['Tgl_lhr'];
	$jeniskel = $_POST['Jns_kel'];
	$agama = $_POST['Agama'];
	$goldar = $_POST['Gol_darah'];
	$status = $_POST['Status_mhs'];
	$kota =  $_POST['Kota'];

	//Pembuatan Syntax SQL
	$sql = "INSERT INTO mahasiswa (Kode_mhs, Nama_mhs, Tgl_lhr, Jns_kel, Agama, Gol_darah, Status_mhs, Kota)  VALUES ('$kode','$nama','$tanggal','$jeniskel','$agama','$goldar','$status','$kota')";

	//Import File Koneksi database

	require_once('koneksi.php');

	//Eksekusi Query database
	if (mysqli_query($con, $sql)) {
		echo 'Berhasil Menambahkan data';
	} else {
		echo 'Gagal Menambahkan data';
	}
	mysqli_close($con);
}
