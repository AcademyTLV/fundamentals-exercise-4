package tlv.academy.android.fundamentals_exercise_4;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AsyncTaskActivity extends Activity implements IAsyncTaskEvents{

    private Button mBtnCreate;
    private Button mBtnStart;
    private Button mBtnCancel;

    private ColorChangeAsyncTask mColorChangeAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_async_task);

        mBtnCreate = findViewById(R.id.btnAsyncCreate);
        mBtnStart = findViewById(R.id.btnAsyncStart);
        mBtnCancel = findViewById(R.id.btnAsyncCancel);

        mBtnCreate.setOnClickListener( OnClickCreate);
        mBtnStart.setOnClickListener( OnClickStart);
        mBtnCancel.setOnClickListener( OnClickCancel);
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

    @Override
    public void onPreExecute() {

    }

    @Override
    public void onPostExecute() {

    }

    @Override
    public void onProgressUpdate(Integer aInteger) {

    }


    private void doAsyncTaskCreate(){
        mColorChangeAsyncTask = new ColorChangeAsyncTask( this);
    }

    private void doAsyncTaskStart(){
        Integer mColorStart;
        Integer mColorEnd;

        mColorStart = Color.WHITE;
        mColorEnd = Color.BLACK;

        mColorChangeAsyncTask.execute( mColorStart, mColorEnd);
    }

    private void doAsyncTaskCancel(){
        mColorChangeAsyncTask.cancel( true);
    }
}
