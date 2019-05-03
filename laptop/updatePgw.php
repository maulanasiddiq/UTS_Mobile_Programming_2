<?php

	if($_SERVER['REQUEST_METHOD']=='POST'){
		//MEndapatkan Nilai Dari Variable
		$id = $_POST['id'];
		$nama = $_POST['nama'];
		$warna = $_POST['warna'];
		$stok = $_POST['stok'];
		$jenis = $_POST['jenis'];

		//import file koneksi database
		require_once('koneksi.php');

		//Membuat SQL Query
		$sql = "UPDATE tb_pegawai SET nama = '$nama', warna = '$warna', stok = '$stok', jenis = '$jenis' WHERE id = $id;";

		//Meng-update Database
		if(mysqli_query($con,$sql)){
			echo 'Berhasil Update Data';
		}else{
			echo 'Gagal Update Data';
		}

		mysqli_close($con);
	}
?>
