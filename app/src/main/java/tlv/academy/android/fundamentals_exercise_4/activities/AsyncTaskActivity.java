package tlv.academy.android.fundamentals_exercise_4.activities;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import tlv.academy.android.fundamentals_exercise_4.R;

public class AsyncTaskActivity extends Activity {

    private Context mContext;
    private Button mBtnCreate;
    private Button mBtnStart;
    private Button mBtnCancel;
    private TextView mTxtValue;

    private InsideCounterAsyncTask mCounterAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;

        setContentView(R.layout.activity_async_task);

        mBtnCreate = findViewById(R.id.btnAsyncCreate);
        mBtnStart = findViewById(R.id.btnAsyncStart);
        mBtnCancel = findViewById(R.id.btnAsyncCancel);
        mTxtValue = findViewById(R.id.txtAsyncValue);

        mBtnCreate.setOnClickListener(OnClickCreate);
        mBtnStart.setOnClickListener(OnClickStart);
        mBtnCancel.setOnClickListener(OnClickCancel);
    }

    private View.OnClickListener OnClickCreate = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            doAsyncTaskCreate();
        }
    };

    private View.OnClickListener OnClickStart = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            doAsyncTaskStart();
        }
    };

    private View.OnClickListener OnClickCancel = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            doAsyncTaskCancel();
        }
    };


    private void doAsyncTaskCreate() {
        mCounterAsyncTask = new InsideCounterAsyncTask();
    }

    private void doAsyncTaskStart() {
        Integer integerStart;
        Integer integerEnd;

        if (mCounterAsyncTask == null) {
            Toast.makeText(this, "must click \"create\" task first", Toast.LENGTH_SHORT).show();
            return;
        }

        integerStart = 1;
        integerEnd = 100;

        mCounterAsyncTask.execute(integerStart, integerEnd);
    }

    private void doAsyncTaskCancel() {
        if (mCounterAsyncTask != null) {
            mCounterAsyncTask.cancel(true);
        }
    }

    class InsideCounterAsyncTask extends AsyncTask<Integer, Integer, Integer> {


        private InsideCounterAsyncTask() {
        }

        @Override
        protected Integer doInBackground(Integer... aIntegers) {

            int iStart;
            int iEnd;
            if (aIntegers.length == 2) {
                iStart = aIntegers[0];
                iEnd = aIntegers[1];
            } else {
                iStart = 0;
                iEnd = 100;
            }

            for (int iStep = iStart; iStep < iEnd; iStep++) {
                SystemClock.sleep(250);
                publishProgress(iStep);
            }

            return iEnd;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(mContext, mContext.getString(R.string.msg_preexecute), Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(Integer aInteger) {
            super.onPostExecute(aInteger);
            Toast.makeText(mContext, mContext.getString(R.string.msg_postexecute), Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            mTxtValue.setText(String.valueOf(values));
        }

        @Override
        protected void onCancelled(Integer aInteger) {
            super.onCancelled(aInteger);
            Toast.makeText(mContext, mContext.getString(R.string.msg_oncancel), Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            Toast.makeText(mContext, mContext.getString(R.string.msg_oncancel), Toast.LENGTH_SHORT).show();
        }
    }

}
