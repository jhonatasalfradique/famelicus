package br.ufrj.cos.famelicus;

        import android.app.Activity;
        import android.app.PendingIntent;
        import android.content.BroadcastReceiver;
        import android.content.Intent;
        import android.content.IntentSender;
        import android.location.Location;
        import android.os.Bundle;
        import android.support.v7.app.ActionBarActivity;
        import android.util.Log;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Button;
        import android.widget.Toast;

        import com.google.android.gms.common.ConnectionResult;
        import com.google.android.gms.common.GooglePlayServicesUtil;
        import com.google.android.gms.common.api.GoogleApiClient;
        import com.google.android.gms.common.api.Status;
        import com.google.android.gms.location.FusedLocationProviderApi;
        import com.google.android.gms.location.Geofence;
        import com.google.android.gms.location.LocationListener;
        import com.google.android.gms.location.LocationRequest;
        import com.google.android.gms.location.LocationServices;
        import com.google.android.gms.common.api.PendingResult;

        import java.util.ArrayList;


public class MainActivity extends Activity
        implements com.google.android.gms.location.LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static int REQUEST_CODE_RECOVER_PLAY_SERVICES = 200;

    private GoogleApiClient mClient;
    private Location mLastLocation;
    private LocationRequest mLocationRequest;
    PendingIntent pendingIntent;

    Button situacaoFila;
    Button colaborar;
    Aplicativo famelicus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createLocationRequest();
        // you can also add more APIs and scopes here
        buildGoogleAPI();
        mClient.connect();

        situacaoFila = (Button) findViewById(R.id.situacaoFila);
        colaborar = (Button) findViewById(R.id.colaborar);
        famelicus = new Aplicativo(this);
        //final String famelicusString = famelicus.generateString();
        //final String versao = Double.toString(famelicus.getVersaoBD());

        famelicus.LigarGPS();
        situacaoFila.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, SituacaoFila.class);
                //String jsonstring = famelicus.getProxy().createretornasituacao();
                //intent.putExtra("json", jsonstring);
                String famelicusString = famelicus.generateString();
                String versao = Double.toString(famelicus.getVersaoBD());
                intent.putExtra("listaPA", famelicusString);
                intent.putExtra("versao", versao);

                startActivity(intent);
            }
        });

        colaborar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, ColaborarQRCODE.class);
                String famelicusString = famelicus.generateString();
                String versao = Double.toString(famelicus.getVersaoBD());
                intent.putExtra("listaPA", famelicusString);
                intent.putExtra("versao", versao);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mClient.connect();
    }

    @Override
    protected void onStop() {
        LocationServices.GeofencingApi.removeGeofences(
                mClient,pendingIntent);

//        if (mClient != null) {
//            LocationServices.FusedLocationApi.removeLocationUpdates(mClient, pendingIntent);
//        }
        mClient.disconnect();
        super.onStop();
    }


    @Override
    public void onConnected(Bundle connectionHint) {
        // this callback will be invoked when all specified services are connected
        //startUpdates();

//        Intent mUpdatesIntent = new Intent(this, Servico.class);
        String famelicusString = famelicus.generateString();
        String versao = Double.toString(famelicus.getVersaoBD());
//        mUpdatesIntent.putExtra("listaPA", famelicusString);
//        mUpdatesIntent.putExtra("versao", versao);
//        //Log.d("versao", versao);
//        //mUpdatesIntent.addFlags()
//        PendingIntent pendingIntent = PendingIntent.getService(this, 0,
//                mUpdatesIntent,
//                PendingIntent.FLAG_UPDATE_CURRENT);
        //LocationServices.FusedLocationApi.requestLocationUpdates(mClient, mLocationRequest, pendingIntent);
//        Location location = LocationServices.FusedLocationApi.getLastLocation(mClient);
//        Log.d("coordenadas esoo", location.getLatitude() + " lng: " + location.getLongitude());
        ArrayList<Geofence> geofences = new ArrayList<Geofence>();
        for(PontoAlimentacao pa: famelicus.getListaPA()){
            geofences.add(new Geofence.Builder()
                    .setRequestId(Integer.toString(pa.getId()))
                    .setCircularRegion(pa.getLocalizacao().getLat(), pa.getLocalizacao().getLng(), 50).setExpirationDuration(Geofence.NEVER_EXPIRE)
                    .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER
                            | Geofence.GEOFENCE_TRANSITION_DWELL)
                    .setLoiteringDelay(10000)
                    .build());
        }
        pendingIntent = PendingIntent.getService(this, 0,
                new Intent(this, Servico.class).putExtra("listaPA", famelicusString).putExtra("versao", versao),
                PendingIntent.FLAG_UPDATE_CURRENT);
        LocationServices.GeofencingApi.addGeofences(
                mClient, geofences, pendingIntent);
    }

    @Override
    public void onLocationChanged(Location location) {
        // this callback is invoked when location updates
        String msg = "Updated Location: " +
                Double.toString(location.getLatitude()) + "," +
                Double.toString(location.getLongitude());
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionSuspended(int cause) {
        // this callback will be invoked when the client is disconnected
        // it might happen e.g. when Google Play service crashes
        // when this happens, all requests are canceled,
        // and you must wait for it to be connected again
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        // this callback will be invoked when the connection attempt fails

        if (connectionResult.hasResolution()) {
            // Google Play services can fix the issue
            // e.g. the user needs to enable it, updates to latest version
            // or the user needs to grant permissions to it
            try {
                connectionResult.startResolutionForResult(this, 0);
            } catch (IntentSender.SendIntentException e) {
                // it happens if the resolution intent has been canceled,
                // or is no longer able to execute the request
            }
        } else {
            // Google Play services has no idea how to fix the issue
        }
    }

    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000*10);
        mLocationRequest.setFastestInterval(1000*5);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    protected void startUpdates(){
        PendingResult<Status> pendingResult = LocationServices.FusedLocationApi.requestLocationUpdates(
                mClient, mLocationRequest, this);
    }
    protected void stopUpdates(){
        LocationServices.FusedLocationApi.removeLocationUpdates(
                mClient, this);
    }
//    protected void startBackgroundUpdates(){
//                LocationServices.FusedLocationApi.requestLocationUpdates(
//                mClient, mLocationRequest, mPendingIntent);
//    }
//    protected void stopBackgroundUpdates(){
//        LocationServices.FusedLocationApi. removeLocationUpdates(
//                mClient, mPendingIntent);
//    }

    public void buildGoogleAPI(){
        mClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }
}