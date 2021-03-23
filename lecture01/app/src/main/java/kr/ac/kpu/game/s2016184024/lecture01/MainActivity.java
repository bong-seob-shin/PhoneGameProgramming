package kr.ac.kpu.game.s2016184024.lecture01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private TextView mainTextView;
    private ImageView mainImageView;
    private ImageButton prevImageButton;
    private ImageButton nextImageButton;

    private int imageIndex = 1;
    private int[] imageNames = {R.mipmap.cat1, R.mipmap.cat2, R.mipmap.cat3, R.mipmap.cat4,R.mipmap.cat5};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainTextView = findViewById(R.id.mainTextView);
        mainTextView.setText("1/5");

        mainImageView = findViewById(R.id.mainImageView);

        prevImageButton = findViewById(R.id.prevButton);
        prevImageButton.setEnabled(false);
        nextImageButton = findViewById(R.id.nextButton);

    }

    public void onButtonPrev(View view) {
        if(imageIndex>1)
        {
            imageIndex--;
            nextImageButton.setEnabled(true);
        }
        if(imageIndex == 1){
            prevImageButton.setEnabled(false);
        }

        mainTextView.setText(imageIndex + "/5");
        mainImageView.setImageResource(imageNames[imageIndex-1]);

    }

    public void onButtonNext(View view) {
        if(imageIndex<5)
        {
            imageIndex++;
            prevImageButton.setEnabled(true);

        }
        if(imageIndex == 5){
            nextImageButton.setEnabled(false);
        }
        mainTextView.setText(imageIndex + "/5");
        mainImageView.setImageResource(imageNames[imageIndex-1]);

    }


}
