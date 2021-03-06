package tlv.academy.android.fundamentals_exercise_4.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import tlv.academy.android.fundamentals_exercise_4.counterasynctask.CounterAsyncTask;
import tlv.academy.android.fundamentals_exercise_4.counterasynctask.IAsyncTaskEvents;
import tlv.academy.android.fundamentals_exercise_4.R;

public class AsyncTaskActivity extends Activity implements IAsyncTaskEvents, View.OnClickListener {

    private static final String KEY_STRING = "KEY_STRING";
    private Button mBtnCreate;
    private Button mBtnStart;
    private Button mBtnCancel;
    private TextView mTxtValue;

    private CounterAsyncTask mAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        mBtnCreate = findViewById(R.id.btnAsyncCreate);
        mBtnStart = findViewById(R.id.btnAsyncStart);
        mBtnCancel = findViewById(R.id.btnAsyncCancel);
        mTxtValue = findViewById(R.id.txtAsyncValue);

        mBtnCreate.setOnClickListener(this);
        mBtnStart.setOnClickListener(this);
        mBtnCancel.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAsyncCreate:
                doAsyncTaskCreate();
                break;
            case R.id.btnAsyncStart:
                doAsyncTaskStart();
                break;
            case R.id.btnAsyncCancel:
                doAsyncTaskCancel();
                break;
        }
    }

    private void doAsyncTaskCreate() {
        Toast.makeText(this, getString(R.string.msg_oncreate), Toast.LENGTH_SHORT).show();
        mAsyncTask = new CounterAsyncTask(this);
    }


    private void doAsyncTaskStart() {
        if ((mAsyncTask == null) || (mAsyncTask.isCancelled())) {
            Toast.makeText(this, R.string.msg_should_create_task, Toast.LENGTH_SHORT).show();
        } else {
        Toast.makeText(this, getString(R.string.msg_onstart), Toast.LENGTH_SHORT).show();
            mAsyncTask.execute(1, 10);
        }
    }

    private void doAsyncTaskCancel() {
        mAsyncTask.cancel(true);
    }


    /***
     // IAsyncTaskEvent's methods - start:
     ***/

    @Override
    public void onPreExecute() {
        Toast.makeText(this, getString(R.string.msg_preexecute), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPostExecute() {
        Toast.makeText(this, getString(R.string.msg_postexecute), Toast.LENGTH_SHORT).show();
        mTxtValue.setText(R.string.done);
    }

    @Override
    public void onProgressUpdate(Integer integer) {
        mTxtValue.setText(String.valueOf(integer));
    }

    @Override
    public void onCancel() {
        Toast.makeText(this, getString(R.string.msg_oncancel), Toast.LENGTH_SHORT).show();
    }

    /***
     //  IAsyncTaskEvent's methods - end
     ***/


    @Override
    protected void onDestroy() {
        if (mAsyncTask != null) {
            mAsyncTask.cancel(false);
            mAsyncTask = null;
        }
        super.onDestroy();
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, AsyncTaskActivity.class));
    }
}
