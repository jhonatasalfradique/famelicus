package br.ufrj.cos.famelicus;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

import java.util.Timer;
import java.util.TimerTask;

public class Servico extends Service{

	private Aplicativo aplicativo;
    Timer timer;
    public Servico(Aplicativo app){
        aplicativo = app;
    }

	public void ChamarAplicativo() {
        final Handler handler = new Handler();
        timer = new Timer();

        final Runnable r = new Runnable()
        {
            public void run()
            {
                aplicativo.ChecarVisibilidade();
                run();
            }
        };

        handler.postDelayed(r, 1000);


	}

	public void ChamarAtualizacao() {
        int versao = aplicativo.getProxy().pedirVersaoBD();
        aplicativo.VerificarVersaoBD(versao);
	}

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
