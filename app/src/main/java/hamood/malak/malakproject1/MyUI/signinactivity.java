package hamood.malak.malakproject1.MyUI;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import hamood.malak.malakproject1.MyUtils.MyValidations;
import hamood.malak.malakproject1.R;
//1 xml

public class signinactivity extends MainActivity
{
    //2
    private Button login,signup;
    private EditText Password,Email;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signupactivity); //3

        Email=findViewById(R.id.Email);
        Password=findViewById(R.id.Password);
        login=findViewById(R.id.login);
        signup=findViewById(R.id.signup);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateForm();

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(signinactivity.this,signupactivity.class);
                startActivity(i);
            }
        });
    }
    //5
    private void validateForm()
    {
        String password=Password.getText().toString();
        String email=Email.getText().toString();
        boolean isOk=true;
        if(email.length()<5 || email.indexOf('@')==0 || email.indexOf('@')>=email.length()-2 ||
                email.indexOf('.')==0 || email.indexOf('.')>=email.length()-1 || email.lastIndexOf('.')<email.indexOf('@'))
        {
            isOk=false;
            Email.setError("wrong Gmail syntax");
        }
        MyValidations myValidations=new MyValidations();
        if (myValidations.validatePasword(password)==false) {
            isOk = false;
            Password.setError("Invalid Password");
        }
         if ((isOk)){
             signIn(email,password);
         }


    }

    private void signIn(String email, String password) {
        FirebaseAuth auth=FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Intent i=new Intent(signinactivity.this,MainActivity.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(signinactivity.this,"FAILED",Toast.LENGTH_SHORT).show();
                    Email.setError(task.getException().getMessage());
                }
            }
        });
    }

}
