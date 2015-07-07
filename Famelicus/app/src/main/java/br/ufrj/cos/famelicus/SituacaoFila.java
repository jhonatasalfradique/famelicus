package br.ufrj.cos.famelicus;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class SituacaoFila extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_situacao_fila);

        String json = getIntent().getStringExtra("json");

        Aplicativo famelicus = new Aplicativo();
        famelicus.setListaPAString(json);
        //
        // Log.d("listdata", listData.toString());
        final ListView listView = (ListView) findViewById(android.R.id.list);
        listView.setAdapter(new PAListAdapter(this,famelicus.getListaPA()));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_situacao_fila, menu);
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


//class StableArrayAdapter extends ArrayAdapter<String> {
//
//    HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();
//
//    public StableArrayAdapter(Context context, int textViewResourceId,
//                              List<String> objects) {
//        super(context, textViewResourceId, objects);
//        for (int i = 0; i < objects.size(); ++i) {
//            mIdMap.put(objects.get(i), i);
//        }
//    }
//
//    @Override
//    public long getItemId(int position) {
//        String item = getItem(position);
//        return mIdMap.get(item);
//    }
//
//    @Override
//    public boolean hasStableIds() {
//        return true;
//    }
//
//}


