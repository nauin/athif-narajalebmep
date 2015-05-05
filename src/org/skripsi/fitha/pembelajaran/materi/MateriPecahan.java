package org.skripsi.fitha.pembelajaran.materi;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.skripsi.fitha.pembelajaran.R;
import org.skripsi.fitha.pembelajaran.parser.JSONParser;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
// import android.widget.TextView;
import android.widget.SimpleAdapter;

public class MateriPecahan extends Activity implements OnClickListener,
		OnItemClickListener {
	private final String TAG = getClass().getSimpleName();
	
	private String subJudul;
	private String anakSubBab;
	private String isiMateri;

	private Button btnMateriPecahanKembali;
	private Button btnMateriPecahanSelanjutnya;
	// private TextView txtViewJudul;
	// private TextView txtViewMateri;
	private ListView lvViewMateri;

	private ProgressDialog mProgressDialog;

	private JSONArray materi = null;

	private static final String LINK_URL = "http://pejuangcinta.nazuka.net/jsonpecahan.php";
	private static final String ARRAY_SUB_JUDUL = "sub_judul";
	private static final String ARRAY_ANAK_SUB_BAB = "anak_subbab";
	private static final String ARRAY_ISI_MATERI = "isi_materi";
	private static final String ARRAY_MATERI = "materi";

	private ArrayList<HashMap<String, String>> daftarMateri;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_materi_pecahan);

		daftarMateri = new ArrayList<HashMap<String, String>>();

		btnMateriPecahanKembali = (Button) findViewById(R.id.btnMateriPecahanKembali);
		btnMateriPecahanKembali.setOnClickListener(this);

		btnMateriPecahanSelanjutnya = (Button) findViewById(R.id.btnMateriPecahanSelanjutnya);
		btnMateriPecahanSelanjutnya.setOnClickListener(this);

		lvViewMateri = (ListView) findViewById(R.id.lvMateriPecahan);
		lvViewMateri.setOnItemClickListener(this);

		// txtViewMateri = (TextView) findViewById(R.id.txtViewMateriPecahan);
		// txtViewJudul = (TextView) findViewById(R.id.txtViewJudul);

		// this.testJson();
		new GetMateriPecahan().execute();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnMateriPecahanKembali:
			MateriPecahan.this.finish();
			break;
		// case R.id.btnMateriPecahanSelanjutnya:
			// break;
		default:
			break;
		}
	}

	@SuppressLint("NewApi")
	private class GetMateriPecahan extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			mProgressDialog = new ProgressDialog(MateriPecahan.this);
			mProgressDialog.setMessage("Memuat Materi Pecahan. Silahkan Tunggu!");
			mProgressDialog.setIndeterminate(false);
			mProgressDialog.setCancelable(true);
			mProgressDialog.show();
		}

		@Override
		protected String doInBackground(String... params) {
			try {
				JSONParser jsonParser = new JSONParser();
				JSONObject json = jsonParser.ambilJSonUrl(LINK_URL);

				materi = json.getJSONArray(ARRAY_MATERI);

				for (int i = 0; i < materi.length(); i++) {
					JSONObject arrayJson = materi.getJSONObject(i);
					subJudul = arrayJson.getString(ARRAY_SUB_JUDUL);
					anakSubBab = arrayJson.getString(ARRAY_ANAK_SUB_BAB);
					isiMateri = arrayJson.getString(ARRAY_ISI_MATERI);

					HashMap<String, String> map = new HashMap<String, String>();
					map.put(ARRAY_SUB_JUDUL, subJudul);
					map.put(ARRAY_ANAK_SUB_BAB, anakSubBab);
					map.put(ARRAY_ISI_MATERI, isiMateri);
					daftarMateri.add(map);

					Log.d(TAG, "sub judul: " + subJudul + ", anak sub bab: "
							+ anakSubBab + ", isi materi: " + isiMateri);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			mProgressDialog.dismiss();
			runOnUiThread(new Runnable() {

				@Override
				public void run() {
					ListAdapter adapter = new SimpleAdapter(
							MateriPecahan.this, daftarMateri,
							R.layout.list_item, new String[] {
									ARRAY_ANAK_SUB_BAB, ARRAY_SUB_JUDUL },
							new int[] { R.id.sub_judul, R.id.materi, });

					lvViewMateri.setAdapter(adapter);
				
					// txtViewJudul.setText(subJudul + "");
					// txtViewMateri.setText(anakSubBab + "\n" + isiMateri + "\n");
				}
			});
		}

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		HashMap<String, String> map = daftarMateri.get(position);
		Intent mIntent = new Intent(getApplicationContext(), IsiMateri.class);
		mIntent.putExtra(ARRAY_ISI_MATERI, map.get(ARRAY_ISI_MATERI));
		startActivity(mIntent);

	}
}
