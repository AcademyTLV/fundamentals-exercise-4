package tlv.academy.android.fundamentals_exercise_4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

  private static final String TAG = "MainActivity";

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    findViewById(R.id.btnOpenActivityAsyncTask).setOnClickListener(this);
    findViewById(R.id.btnOpenActivityThreadHandler).setOnClickListener(this);
  }

  @Override public void onClick(View v) {
    switch (v.getId()) {
      case R.id.btnOpenActivityAsyncTask:
        Log.i(TAG, "onClick: opening AsyncTaskImplementation");
        startActivity(new Intent(v.getContext(), AsyncTaskActivity.class));
        break;
      case R.id.btnOpenActivityThreadHandler:
        Log.i(TAG, "onClick: opening Hander Implementation");
        startActivity(new Intent(v.getContext(), HandlerActivity.class));
        break;
    }
  }
}
