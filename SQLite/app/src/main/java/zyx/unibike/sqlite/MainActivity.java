package zyx.unibike.sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    DataBaseAdapter dataBaseAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataBaseAdapter=new DataBaseAdapter(this);
    }
    //之后通过dataBaseAdapter对象调用增删改查语句
}
