package hamood.malak.malakproject1.MyUI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import hamood.malak.malakproject1.MyData.MyTaskAdAdapter;
import hamood.malak.malakproject1.MyUtils.MyTask;
import hamood.malak.malakproject1.R;

public class MainActivity extends AppCompatActivity {

    private ImageButton addbtn;
    //a.1 after building the array adapter
    private ListView listview1;
    //a.2 adapter
    MyTaskAdAdapter taskAdapter;

    //a.1 after building the array adapter
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addbtn=findViewById(R.id.addbtn);

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this, AddTasky.class);
                startActivity(i);
            }
        });
        //a.3
        listview1=findViewById(R.id.listview1);
        //a.4
        taskAdapter=new MyTaskAdAdapter(getBaseContext(),R.layout.item_tasl_view);
        //a.5
        listview1.setAdapter(taskAdapter);
    }
    //a.6
    public void downloadFromFireBase() {
        //where we saved before we need to connect to download
        //1.
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        //2.
        DatabaseReference reference = database.getReference();
        //3. user id
        
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String uid = auth.getCurrentUser().getUid();
        //4. My Object Key
        //reference.child("AllTasks").child(uid).addValueEventListener(new ValueEventListener()
        reference.child("AllTasks").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                taskAdapter.clear();
                for (DataSnapshot child: dataSnapshot.getChildren()){
                    MyTask task=child.getValue(MyTask.class);
                    taskAdapter.add(task);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this,"Download Error",Toast.LENGTH_SHORT).show();

            }
        });
    }
}
