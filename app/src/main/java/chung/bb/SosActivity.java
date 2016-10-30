package chung.bb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;

public class SosActivity extends AppCompatActivity {

    private TextView desc;
    private Button physical;
    private Button verbal;
    private Button cyber;
    private Button relation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        desc = (TextView)findViewById(R.id.desc);
        desc.setText("Remember you are not alone. I need help with...");
        physical = (Button)findViewById(R.id.btn_physical);
        verbal = (Button)findViewById(R.id.btn_verbal);
        cyber = (Button)findViewById(R.id.btn_cyber);
        relation = (Button)findViewById(R.id.btn_relation);

    }

}
