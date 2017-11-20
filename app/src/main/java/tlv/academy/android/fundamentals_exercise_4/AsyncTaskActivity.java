package tlv.academy.android.fundamentals_exercise_4;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AsyncTaskActivity extends Activity implements IAsyncTaskEvents{

    private Button mBtnCreate;
    private Button mBtnStart;
    private Button mBtnCancel;
    private TextView mTxtValue;

    private ColorChangeAsyncTask mColorChangeAsyncTask;
    private View mRootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_async_task);

        mRootView = findViewById(R.id.root);

        mBtnCreate = findViewById(R.id.btnAsyncCreate);
        mBtnStart = findViewById(R.id.btnAsyncStart);
        mBtnCancel = findViewById(R.id.btnAsyncCancel);
        mTxtValue = findViewById(R.id.txtAsyncValue);

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
        Toast.makeText( this, getString(R.string.msg_preexecute), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPostExecute() {
        Toast.makeText( this, getString(R.string.msg_postexecute), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProgressUpdate(Integer aInteger) {
        mTxtValue.setText( String.valueOf( aInteger));

//        mRootView.setBackgroundColor(aInteger);
    }

    @Override
    public void onCancel() {
        Toast.makeText( this, getString(R.string.msg_oncancel), Toast.LENGTH_SHORT).show();
    }


    private void doAsyncTaskCreate(){
        mColorChangeAsyncTask = new ColorChangeAsyncTask( this);
    }

    private void doAsyncTaskStart(){
        Integer integerStart;
        Integer integerEnd;
//        Integer mColorStart;
//        Integer mColorEnd;

        if (mColorChangeAsyncTask == null) {
            Toast.makeText(this, "must click \"create\" task first", Toast.LENGTH_SHORT).show();
            return;
        }


        integerStart = 1;
        integerEnd = 100;

        mColorChangeAsyncTask.execute( integerStart, integerEnd);
//        mColorStart = Color.BLACK;
//        mColorEnd = Color.WHITE;

//        mColorChangeAsyncTask.execute( mColorStart, mColorEnd);
    }

    private void doAsyncTaskCancel(){
        mColorChangeAsyncTask.cancel( true);
    }
}
