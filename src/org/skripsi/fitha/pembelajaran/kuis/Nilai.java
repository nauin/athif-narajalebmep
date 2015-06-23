package org.skripsi.fitha.pembelajaran.kuis;

import org.skripsi.fitha.pembelajaran.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Nilai extends Activity {
	private TextView tvhasil1;
	private TextView tvhasil2;
	private TextView tvhasil3;
	private TextView tvhasil4;
	private TextView tvhasil5;
	private TextView tvhasil6;
	private TextView tvhasil7;
	private TextView tvTotalNilai;
	private Button btnKembali;
	private Button btnKunciJawaban;
	
	private double totalnilai = 0; 
	private int jumlahbenar = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nilai);
		
		tvhasil1 = (TextView) findViewById(R.id.tvhasil1);
		tvhasil2 = (TextView) findViewById(R.id.tvhasil2);
		tvhasil3 = (TextView) findViewById(R.id.tvhasil3);
		tvhasil4 = (TextView) findViewById(R.id.tvhasil4);
		tvhasil5 = (TextView) findViewById(R.id.tvhasil5);
		tvhasil6 = (TextView) findViewById(R.id.tvhasil6);
		tvhasil7 = (TextView) findViewById(R.id.tvhasil7);
		tvTotalNilai = (TextView) findViewById(R.id.tvtotallnilai);
		btnKembali = (Button) findViewById(R.id.btnkembali);
		btnKunciJawaban = (Button) findViewById(R.id.btncekkuncijawaban);
		
		Intent intent = getIntent();
		int hasil1 = intent.getIntExtra("hasil1", 0);
		tvhasil1.setText(hasil1+"");
		int hasil2 = intent.getIntExtra("hasil2", 0);
		tvhasil2.setText(hasil2+"");
		int hasil3 = intent.getIntExtra("hasil3", 0);
		tvhasil3.setText(hasil3+"");
		int hasil4 = intent.getIntExtra("hasil4", 0);
		tvhasil4.setText(hasil4+"");
		int hasil5 = intent.getIntExtra("hasil5", 0);
		tvhasil5.setText(hasil5+"");
		int hasil6 = intent.getIntExtra("hasil6", 0);
		tvhasil6.setText(hasil6+"");
		int jumlahsoal = intent.getIntExtra("jumlahsoal", 0);
		jumlahbenar = hasil1 + hasil2 + hasil3 + hasil4 + hasil5 + hasil6;
		totalnilai = ((double)jumlahbenar/40) * 100;
		tvhasil7.setText(totalnilai+"");
	}
	

}
