package tlv.academy.android.fundamentals_exercise_4.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import tlv.academy.android.fundamentals_exercise_4.R;
import tlv.academy.android.fundamentals_exercise_4.counterasynctask.IAsyncTaskEvents;
import tlv.academy.android.fundamentals_exercise_4.simpleasynctask.MySimpleAsyncTask;

public class ThreadsActivity extends AppCompatActivity implements View.OnClickListener, IAsyncTaskEvents {
    private static final String TAG = "ThreadsActivity";


    private Button mBtnCreate;
    private Button mBtnStart;
    private Button mBtnCancel;
    private TextView mTxtValue;

    private MySimpleAsyncTask mAsyncTask;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);
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
        mAsyncTask = new MyCounterAsynTaskImpl(this);
    }


    private void doAsyncTaskStart() {
        if ((mAsyncTask == null) || (mAsyncTask.isCancelled())) {
            Toast.makeText(this, R.string.msg_should_create_task, Toast.LENGTH_SHORT).show();
        } else {
            mAsyncTask.execute();
        }
    }

    private void doAsyncTaskCancel() {
        mAsyncTask.cancel();
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
            mAsyncTask.cancel();
            mAsyncTask = null;
        }
        super.onDestroy();
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, ThreadsActivity.class));
    }
}



