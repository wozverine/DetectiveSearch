package com.glitch.detectivesearch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.glitch.detectivesearch.R;
import com.glitch.detectivesearch.cases.CasesAdapter;
import com.glitch.detectivesearch.cases.CasesInfo;
import com.glitch.detectivesearch.evalquestions.E1Activity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Random;

public class CasesActivity extends AppCompatActivity implements ListItemClickListener {
    private CasesAdapter cAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cases);
        //Toolbar toolbar = findViewById(R.id .toolbar_detail);
        //setSupportActionBar(toolbar);
        final int CASE_COUNT=loadData("case_count");

        ArrayList<String> enabled_cases_list=getArrayList("cases_enabled");
        String[] enabled_string=new String[enabled_cases_list.size()];
        enabled_cases_list.toArray(enabled_string);

        ArrayList<String> enabled_evals_list=getArrayList("evals_enabled");
        String[] enabled_evals_string=new String[enabled_evals_list.size()];
        enabled_evals_list.toArray(enabled_evals_string);

        CasesInfo[] info=new CasesInfo[CASE_COUNT*2];
        int count_i=0;
        for (int x=0;x<CASE_COUNT*2;x++){
            info[x]=namecases(""+(count_i+1),enabled_string[count_i]);
            info[x+1]=nameevals(""+(count_i+1),enabled_evals_string[count_i]);
            count_i++;
            x++;
        }


        /*ArrayList<String> cases_enabled_arrylst=getArrayList("cases_enabled");
        String[] cases_enabled=new String[cases_enabled_arrylst.size()];
        cases_enabled_arrylst.toArray(cases_enabled);

        CasesInfo[] list_string=new CasesInfo[CASE_COUNT];
        for (int x=0;x<CASE_COUNT;x++){
            list_string[x]=namecases(""+(x+1),cases_enabled[x]);
        }*/

        //CasesInfo[] list_string = {namecases("1"),namecases("2"),namecases("3"),namecases("4"),
        //      namecases("5"),namecases("6"),namecases("7")};

        RecyclerView case_recyle = (RecyclerView) findViewById(R.id.recyc_cases);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        case_recyle.setLayoutManager(mLayoutManager);
        case_recyle.setHasFixedSize(true);

        cAdapter=new CasesAdapter(info.length,info,this);
        //cAdapter=new CasesAdapter(list_string.length,list_string,this);
        //eAdapter=new EvalAdapter(list_string.length,list_string_eval,this);
        case_recyle.setAdapter(cAdapter);
    }

    public CasesInfo namecases(String x,String enabled){
        int draw=0;
        if(enabled.equals("false")){
            draw=R.drawable.file_false;
        }
        if(enabled.equals("true")){
            draw=R.drawable.file;
        }
        if(enabled.equals("done")){
            draw=R.drawable.file_done;
        }
        CasesInfo sampleInfo = new CasesInfo("Case "+x, draw,enabled,true);
        return sampleInfo;
    }

    public CasesInfo nameevals(String x,String enabled){
        int draw=0;
        if(enabled.equals("false")){
            draw=R.drawable.file_false;
        }
        if(enabled.equals("true")){
            draw=R.drawable.file;
        }
        if(enabled.equals("done")){
            draw=R.drawable.file_done;
        }
        CasesInfo sampleInfo = new CasesInfo("Evaluation "+x, draw,enabled,false);
        return sampleInfo;
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        CasesInfo[] cInfos = cAdapter.getdata();
        CasesInfo singleItemInfo = cInfos[clickedItemIndex];
        String en=singleItemInfo.getEnabled();
        boolean isCase=singleItemInfo.isCase();
        if(isCase){
            if(en.equals("true")){
                Resources res = getResources();
                String[] countries_arr = res.getStringArray(R.array.countries_array);
                String []selected=new String[3];
                String []wrong=new String[9];
                Integer[] selected_numbers =random_n(countries_arr.length);
                for (int x =0;x<3;x++){
                    selected[x]=countries_arr[selected_numbers[x]];
                }
                for (int x =3;x<11;x++){
                    wrong[x-3]=countries_arr[selected_numbers[x]];
                }
                //System.out.println("selected"+Arrays.toString(selected));
                Bundle string_array_bundle = new Bundle();
                Bundle int_bundle = new Bundle();
                string_array_bundle.putStringArray("country_array",selected);
                string_array_bundle.putStringArray("wrong_array",wrong);

                int_bundle.putInt("case_number",clickedItemIndex/2+1);
                Intent intent = new Intent(CasesActivity.this, StoryActivity.class);
                intent.putExtras(string_array_bundle);
                intent.putExtras(int_bundle);
                startActivity(intent);
                CasesActivity.this.finish();
            }if(en.equals("done")){
                Toast.makeText(CasesActivity.this, "You already solved this case!", Toast.LENGTH_SHORT).show();
            }if(en.equals("false")){
                Toast.makeText(CasesActivity.this, "You haven't unlocked this case yet!", Toast.LENGTH_SHORT).show();
            }
        }else {
            if(en.equals("true")){
                Bundle int_bundle = new Bundle();
                int_bundle.putInt("case_number",clickedItemIndex/2+1);
                Intent intent = new Intent(CasesActivity.this, E1Activity.class);
                intent.putExtras(int_bundle);
                startActivity(intent);
                CasesActivity.this.finish();
            }
            if(en.equals("done")){
                Toast.makeText(CasesActivity.this, "You already completed this evaluation!", Toast.LENGTH_SHORT).show();
            }if(en.equals("false")){
                Toast.makeText(CasesActivity.this, "You haven't unlocked this evaluation yet!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public Integer[] random_n(int x){
        Random random = new Random();
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        while (arrayList.size() < 11) { // how many numbers u need
            int a = random.nextInt(x);
            if (!arrayList.contains(a)) {
                arrayList.add(a);
            }
        }
        return arrayList.toArray(new Integer[0]);
    }
    public ArrayList<String> getArrayList(String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(CasesActivity.this);
        Gson gson = new Gson();
        String json = prefs.getString(key, null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        return gson.fromJson(json, type);
    }
    public int loadData(String KEY) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(CasesActivity.this);
        int text = prefs.getInt(KEY, 0);
        return text;
    }
}