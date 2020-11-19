package hamood.malak.malakproject1.MyUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.internal.api.FirebaseNoSignedInUserException;

import hamood.malak.malakproject1.MyUI.MainActivity;
import hamood.malak.malakproject1.MyUtils.MyValidations;
import hamood.malak.malakproject1.R;


public class signupactivity extends MainActivity {
    //1 XML design
    //2
    private EditText editTextTextEmailAddress,text,editPhone;
    private TextView password1,firstname,lastname;
    private Button save;
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.signupactivity);
        //3
        editTextTextEmailAddress=findViewById(R.id.editTextTextEmailAddress);
        text=findViewById(R.id.text);
        editPhone=findViewById(R.id.editPhone);
        password1=findViewById(R.id.password1);
        firstname=findViewById(R.id.firstname);
        lastname=findViewById(R.id.lastname);
        save=findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//class without name


            }
        });
    }

    /**
     *המתודה בודקת תקינות טופם ההרשמה ואם תקין מבצעת הרשמה\The method checks the correctness of the registration top and whether it is correct performs the registration
     */
    private void checkForm()
    {
        String phone=editPhone.getText().toString();
        String Gmail=editTextTextEmailAddress.getText().toString();
        String password=password1.getText().toString();
        String FirstName=firstname.getText().toString();
        String LastName=lastname.getText().toString();

        boolean isOk= true;


        if(FirstName.length()< 2)
        {
            isOk=false;
            firstname.setError("at least two letters");
        }
        if(LastName.length()< 2)
        {
            isOk=false;
            lastname.setError("at least two letters");
        }
        if(Gmail.length()<5 || Gmail.indexOf('@')==0 || Gmail.indexOf('@')>=Gmail.length()-2 ||
                Gmail.indexOf('.')==0 || Gmail.indexOf('.')>=Gmail.length()-1 || Gmail.lastIndexOf('.')<Gmail.indexOf('@'))
        {
            isOk=false;
            editTextTextEmailAddress.setError("wrong Gmail syntax");
        }
        if(password.equals(password1)==false){
            isOk=false;
            password1.setError("Password must ne the same");
        }
        else
        {
            MyValidations myValidations=new MyValidations();
            if (myValidations.validatePasword(password)==false){
                isOk= false;
                password1.setError("Invalid Password");
            }
        }
        if(isOk)// isOk = true
        {
            // todo: create account and return to sign in screen/ close this screen
            createNewAccount(Gmail,FirstName,LastName,password,phone);
    }

    }

    /**
     *
     * @param gmail
     * @param firstName
     * @param lastName
     * @param password
     * @param phone
     */

    private void createNewAccount(final String gmail, String firstName, String lastName, String password, String phone)
    {
        // 1
        FirebaseAuth auth=FirebaseAuth.getInstance(); //// אחראית על רישום וכניסת משתמשים
        //  מאזין לאירוע הרשמה בפיירביים 2
        OnCompleteListener<AuthResult> listener=new OnCompleteListener<AuthResult>() {
            // responce
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())// ההרשמה הצליחה כמו שנדרש
                {
                    Toast.makeText(signupactivity.this,"Succefuly signing in", Toast.LENGTH_SHORT).show();

                    //next screen or close this screen
                    finish();// close this screen
                    //next screen
                    Intent i=new Intent(getBaseContext(),MainActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(signupactivity.this,"Signing up, Failed: "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    editTextTextEmailAddress.setError(" Signing up, Failded: "+ task.getException().getMessage());
                }
            }
        };
        //3
        auth.createUserWithEmailAndPassword(gmail,password).addOnCompleteListener(listener);
    }


}
