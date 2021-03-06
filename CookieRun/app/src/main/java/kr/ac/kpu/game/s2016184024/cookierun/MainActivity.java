package kr.ac.kpu.game.s2016184024.cookierun;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import kr.ac.kpu.game.s2016184024.cookierun.framework.view.GameView;
import kr.ac.kpu.game.s2016184024.cookierun.game.MainGame;

public class MainActivity extends AppCompatActivity {
    private MainGame mainGame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mainGame = new MainGame();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    protected void onPause() {
        super.onPause();
        GameView.view.pauseGame();
    }
}