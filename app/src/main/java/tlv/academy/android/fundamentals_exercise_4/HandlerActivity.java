package tlv.academy.android.fundamentals_exercise_4;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import tlv.academy.android.fundamentals_exercise_4.simpleasynctask.MySimpleAsyncTask;

public class HandlerActivity extends AppCompatActivity implements View.OnClickListener {
  private static final String TAG = "HandlerActivity";

  private static final int START_COLOR = Color.BLACK;
  public static final int END_COLOR = Color.WHITE;
  private static final String CURRENT_COLOR_KEY = "current_key";

  MySimpleAsyncTask mColorChangingTask;
  View mRootView;
  int currentColor;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.handler_activity_layout);
    mRootView = findViewById(R.id.root);
    findViewById(R.id.btnStartTask).setOnClickListener(this);
    findViewById(R.id.btnEndTask).setOnClickListener(this);
    if (savedInstanceState != null) {
      currentColor = savedInstanceState.getInt(CURRENT_COLOR_KEY, START_COLOR);
      mRootView.setBackgroundColor(currentColor);
    }
    initMyTask();
  }

  private void initMyTask() {
    mColorChangingTask = new MySimpleAsyncTask() {
      @Override protected void onPreExecute() {
        Log.d(TAG, "onPreExecute() called");
        mRootView.setBackgroundColor(START_COLOR);
        currentColor = START_COLOR;
      }

      @Override protected void doInBackground() {
        Log.d(TAG, "doInBackground() called");
        while (currentColor < END_COLOR) {
          if (isInterrupted()) {
            return;
          }
          SystemClock.sleep(200);
          currentColor += 20;
          publishProgress();
        }
      }

      @Override protected void onPostExecute() {
        Log.d(TAG, "onPostExecute() called");
      }

      @Override protected void onProgress() {
        super.onProgress();
        Log.d(TAG, "onProgress: ");
        mRootView.setBackgroundColor(currentColor);
      }
    };
  }

  @Override public void onClick(View v) {
    switch (v.getId()) {
      case R.id.btnEndTask:
        mColorChangingTask.cancel();
        break;
      case R.id.btnStartTask:
        mColorChangingTask.execute();
        break;
    }
  }

  @Override protected void onSaveInstanceState(Bundle outState) {
    mColorChangingTask.cancel();
    outState.putInt(CURRENT_COLOR_KEY, currentColor);
    super.onSaveInstanceState(outState);
  }

}
