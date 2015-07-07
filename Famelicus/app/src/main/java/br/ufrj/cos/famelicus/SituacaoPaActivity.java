package br.ufrj.cos.famelicus;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;


public class SituacaoPaActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_situacao_pa);
//        String json = getIntent().getStringExtra("json");
//        Gson gson = new Gson();
//        JsonParser parser = new JsonParser();
//        JsonElement jObj = parser.parse(json).getAsJsonObject().get("values");
//        JsonArray jArray = jObj.getAsJsonArray();
//
//
//        ArrayList<PontoDeAlimentacao> listData = new ArrayList();
//        for(JsonElement obj: jArray){
//            PontoDeAlimentacao pa = gson.fromJson(obj, PontoDeAlimentacao.class);
//            listData.add(pa);
//        }
//        final ListView listView = (ListView) findViewById(android.R.id.list);
//        listView.setAdapter(new PAListAdapter(this,listData));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_situacao_pa, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
