package hamood.malak.malakproject1.MyUI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import hamood.malak.malakproject1.MyUtils.MyTask;
import hamood.malak.malakproject1.MyUtils.MyValidations;
import hamood.malak.malakproject1.R;

public class AddTasky extends AppCompatActivity {

    private Button btnsave,btnupload,btndatepicker;
    private ImageButton imageButton;
    private SeekBar sbimp, sbnec;
    private EditText etSubject,etTitle,dueDate;
    private TextView tvNeccesary,tvImportant;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tasky);
        //3. find view by id
        etTitle=findViewById(R.id.etTitle);
        etSubject=findViewById(R.id.etSubject);
        sbimp =findViewById(R.id.sbimp);
        sbnec =findViewById(R.id.sbnec);
        tvNeccesary=findViewById(R.id.tvNeccesary);
        btndatepicker=findViewById(R.id.btndatepicker);
        imageButton=findViewById(R.id.imageButton);
        btnupload=findViewById(R.id.btnupload);
        tvImportant=findViewById(R.id.tvImportant);
        //4 listners
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyValidations();
            }
        });




    }
    public void validateForm()
    {
        String title=etTitle.getText().toString();
        String subject=etSubject.getText().toString();
        int nec= sbnec.getProgress();
        int imp= sbimp.getProgress();
        boolean isOk= false;
        if (title.length()==0){
            isOk=false;
            etTitle.setError("at least one char");
        }
        if (isOk){
            //6 save on firebase database
            //6.1 :bulid your data object
            MyTask myTask=new MyTask();
            myTask.setTitle(title);
            myTask.setSub(subject);
            myTask.setNeccessary(nec);
            myTask.setImportant(imp);
            //6.2
            saveTask(myTask);

        }
        //6.2 actual storing
    }
    private void saveTask(MyTask myTask) {
        //1
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        //2
        DatabaseReference reference=database.getReference();
        //3.user id
        FirebaseAuth auth= FirebaseAuth.getInstance();
        String uid=auth.getCurrentUser().getUid();
        //4 my object key
        String key=reference.child("AllTasks").push().getKey();
        //5 update your object
        myTask.setOwner(uid);
        myTask.setKey(key);
        //6 actual storing
        reference.child("AllTasks").child(uid).child(key).setValue(myTask).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(AddTasky.this,"add successful", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {
                    Toast.makeText(AddTasky.this,"add failed"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    task.getException().printStackTrace();
                }
            }
        });
    }
}
