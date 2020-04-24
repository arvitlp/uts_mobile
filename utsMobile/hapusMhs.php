<?php


//Mendapatkan Nilai ID
$kode = @$_GET['kode'];

//Import File Koneksi Database
require_once('koneksi.php');

//Membuat SQL Query
$sql = "DELETE FROM tb_mahasiswa WHERE Kode_mhs='$kode'";


//Menghapus Nilai pada Database
if (mysqli_query($con, $sql)) {
    echo json_encode(array('message' => 'Berhasil Menghapus data Mahasiswa.'));
} else {
    echo json_encode(array('message' => 'Gagal Menghapus data Mahasiswa.'));
}

mysqli_close($con);
