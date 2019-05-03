<?php

	if($_SERVER['REQUEST_METHOD']=='POST'){

		//Mendapatkan Nilai Variable
		$nama = $_POST['nama'];
		$warna = $_POST['warna'];
		$stok = $_POST['stok'];
		$jenis = $_POST['jenis'];	

		//Pembuatan Syntax SQL
		$sql = "INSERT INTO tb_pegawai (nama,warna,stok,jenis) VALUES ('$nama','$warna','$stok','$jenis')";

		//Import File Koneksi database
		require_once('koneksi.php');

		//Eksekusi Query database
		if(mysqli_query($con,$sql)){
			echo 'Berhasil Menambahkan Data';
		}else{
			echo 'Gagal Menambahkan Data';
		}

		mysqli_close($con);
	}
?>
