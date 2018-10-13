package top.golabe.circleprogressview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import top.golabe.library.CircleProgressView;

public class MainActivity extends AppCompatActivity {
    private CircleProgressView mCircleProgressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCircleProgressView=findViewById(R.id.circleProgressView);


    }


}
