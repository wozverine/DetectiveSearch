package com.glitch.detectivesearch.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.glitch.detectivesearch.R
import com.glitch.detectivesearch.databinding.ActivityMainBinding
/*import com.google.gson.Gson
import java.lang.reflect.Type
import java.util.Collections*/

class MainActivity : AppCompatActivity() {
	private lateinit var appBarConfiguration: AppBarConfiguration
	private lateinit var binding: ActivityMainBinding
	/*var start_btn: Button? = null
	var options_btn: Button? = null*/
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(R.layout.activity_main)


		setSupportActionBar(binding.toolbar)

		val navHostFragment =
			supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
		val navController = navHostFragment.navController

		appBarConfiguration = AppBarConfiguration(navController.graph)
		setupActionBarWithNavController(navController, appBarConfiguration)

		/*start_btn = findViewById<Button>(R.id.start_btn)
		options_btn = findViewById<Button>(R.id.options_btn)
		val CASE_COUNT = 10
		saveData(CASE_COUNT, "case_count")
		val PREFS_NAME = "MyPrefsFile"
		val settings: SharedPreferences = getSharedPreferences(PREFS_NAME, 0)
		if (settings.getBoolean("my_first_time", true)) {
			//the app is being launched for first time, do something
			Log.d("Comments", "First time")
			val arr = arrayOfNulls<String>(CASE_COUNT)
			val arr_evals = arrayOfNulls<String>(CASE_COUNT)
			arr[0] = "true"
			arr_evals[0] = "false"
			for (c in 1 until CASE_COUNT) {
				arr[c] = "false"
				arr_evals[c] = "false"
			}
			val list_cases = ArrayList<String>()
			Collections.addAll(list_cases, *arr)
			saveArrayList(list_cases, "cases_enabled")
			saveData(0, "mode")
			saveData(0, "easy")
			saveData(1, "photo")
			//saveData(CASE_COUNT,"case_count");
			val list_evals = ArrayList<String>()
			Collections.addAll(list_evals, *arr_evals)
			saveArrayList(list_evals, "evals_enabled")

			// record the fact that the app has been started at least once
			settings.edit().putBoolean("my_first_time", false).apply()
		}
		val cases = getArrayList("cases_enabled")
		val cases_enabled = arrayOfNulls<String>(cases.size)
		cases.toArray(cases_enabled)
		val evals = getArrayList("evals_enabled")
		val evals_enabled = arrayOfNulls<String>(evals.size)
		evals.toArray(evals_enabled)
		start_btn!!.setOnClickListener {
			val myIntent = Intent(this@MainActivity, CasesActivity::class.java)
			val string_array_bundle = Bundle()
			string_array_bundle.putStringArray("cases_enabled", cases_enabled)
			string_array_bundle.putStringArray("evals_enabled", evals_enabled)
			myIntent.putExtras(string_array_bundle)
			startActivity(myIntent)
		}
		options_btn!!.setOnClickListener {
			val myIntent = Intent(this@MainActivity, OptionsActivity::class.java)
			startActivity(myIntent)
		}
	}

	fun saveArrayList(list: ArrayList<String>?, key: String?) {
		val prefs: SharedPreferences =
			PreferenceManager.getDefaultSharedPreferences(this@MainActivity)
		val editor: SharedPreferences.Editor = prefs.edit()
		val gson = Gson()
		val json: String = gson.toJson(list)
		editor.putString(key, json)
		editor.apply()
	}

	fun getArrayList(key: String?): ArrayList<String> {
		val prefs: SharedPreferences =
			PreferenceManager.getDefaultSharedPreferences(this@MainActivity)
		val gson = Gson()
		val json: String = prefs.getString(key, null)
		val type: Type = object : TypeToken<ArrayList<String?>?>() {}.getType()
		return gson.fromJson(json, type)
	}

	fun saveData(TEXT: Int, KEY: String?) {
		val prefs: SharedPreferences =
			PreferenceManager.getDefaultSharedPreferences(this@MainActivity)
		val editor: SharedPreferences.Editor = prefs.edit()
		editor.putInt(KEY, TEXT)
		editor.apply()
	}

	fun loadData(KEY: String?): Int {
		val prefs: SharedPreferences =
			PreferenceManager.getDefaultSharedPreferences(this@MainActivity)
		return prefs.getInt(KEY, 0)
	}*/
	}
}