package tlv.academy.android.fundamentals_exercise_4.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import tlv.academy.android.fundamentals_exercise_4.R;

public class  MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnOpenActivityAsyncTask).setOnClickListener(this);
        findViewById(R.id.btnOpenActivityThreadHandler).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOpenActivityAsyncTask:
                Log.i(TAG, "onClick: opening AsyncTaskImplementation");
                AsyncTaskActivity.start(v.getContext());
                break;
            case R.id.btnOpenActivityThreadHandler:
                Log.i(TAG, "onClick: opening Hander Implementation");
                ThreadsActivity.start(v.getContext());
                break;
        }
    }
}
