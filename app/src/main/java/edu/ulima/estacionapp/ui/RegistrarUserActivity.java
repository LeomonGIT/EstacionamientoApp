package edu.ulima.estacionapp.ui;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import edu.ulima.estacionapp.R;

public class RegistrarUserActivity extends ActionBarActivity {

    TextView txtOnPressed,lblRegisterAuto;
    EditText register1,register2,register3,register4,register5,register6,register7,register8,register9;
    Switch switchUser;
    ActionBar bar;
    Button btnRegister;
    int USER_SELECTED=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_user);
        bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.user_cliente)));

        lblRegisterAuto=(TextView) findViewById(R.id.lblRegisterAuto);
        register1=(EditText) findViewById(R.id.register1);
        register2=(EditText) findViewById(R.id.register2);
        register3=(EditText) findViewById(R.id.register3);
        register4=(EditText) findViewById(R.id.register4);
        register5=(EditText) findViewById(R.id.register5);
        //register6=(EditText) findViewById(R.id.register6);
        register7=(EditText) findViewById(R.id.register7);
        register8=(EditText) findViewById(R.id.register8);
        register9=(EditText) findViewById(R.id.register9);
        btnRegister=(Button) findViewById(R.id.btnRegister);
        switchUser = (Switch) findViewById(R.id.switch1);
        txtOnPressed=(TextView) findViewById(R.id.txtCabeceraRegister);
        txtOnPressed.setText("DATOS PERSONALES");
        btnRegister.setBackground(new ColorDrawable(getResources().getColor(R.color.user_cliente)));
        switchUser.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    USER_SELECTED=1;
                    switchUser.setThumbDrawable(RegistrarUserActivity.this.getResources().getDrawable(R.drawable.switch_empresa));
                    txtOnPressed.setText("DATOS DE EMPRESA");
                    bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.user_business)));
                    btnRegister.setBackground(new ColorDrawable(getResources().getColor(R.color.user_business)));
                    registerForm(USER_SELECTED);
                } else {
                    USER_SELECTED=0;
                    switchUser.setThumbDrawable(RegistrarUserActivity.this.getResources().getDrawable(R.drawable.switch_cliente));
                    txtOnPressed.setText("DATOS PERSONALES");
                    bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.user_cliente)));
                    btnRegister.setBackground(new ColorDrawable(getResources().getColor(R.color.user_cliente)));
                    registerForm(USER_SELECTED);
                }
            }
        });
    }

    public void clickRegistrarUser(View view){
        Log.e("user_selected:",""+USER_SELECTED);
        registerParse(USER_SELECTED);
    }

    private void registerForm(int type){
        switch (type){

            case 0:
                register1.setHint(getString(R.string.hint_name));
                register2.setHint(getString(R.string.hint_username));
                register3.setHint(getString(R.string.hint_password));
                register4.setHint(getString(R.string.hint_cellphone));
                register5.setHint(getString(R.string.hint_dni));
                //register6.setHint(getString(R.string.hint_email));//quitar
                register7.setHint(getString(R.string.hint_placa_auto));
                register8.setHint(getString(R.string.hint_modelo_auto));//selector
                register9.setHint(getString(R.string.hint_color_auto));//selector
                register7.setVisibility(View.VISIBLE);
                register8.setVisibility(View.VISIBLE);
                register9.setVisibility(View.VISIBLE);
                lblRegisterAuto.setVisibility(View.VISIBLE);
                break;

            case 1:
                register1.setHint("Nombre Empresa");
                register2.setHint("Usuario");
                register3.setHint("Password");
                register4.setVisibility(View.GONE);
                register5.setHint("RUC ");
                //register6.setHint("Correo Empresa");//quitar
                register7.setHint("Direccion Sede Principal");
                register8.setHint("Logo (Opcional)");
                register9.setVisibility(View.GONE);
                lblRegisterAuto.setVisibility(View.GONE);
                break;
            default:
                break;

        }

    }
    private void registerParse(int type){
        Log.e("user_selected/type",""+USER_SELECTED+"/"+type);
        TextView user = (TextView) findViewById(R.id.register2);
        TextView pass = (TextView)findViewById(R.id.register3);
        ParseUser userParse = new ParseUser();
        userParse.setUsername(user.getText().toString());
        userParse.setPassword(pass.getText().toString());
        userParse.put("type",USER_SELECTED);
        userParse.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    Toast.makeText(RegistrarUserActivity.this, "Registrado!", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                } else {
                    Toast.makeText(RegistrarUserActivity.this,"Error!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        /*ParseObject userTable = new ParseObject("User");
        switch (type){
            case 0:
                userTable.put("username", user.getText().toString());
                userTable.put("password", pass.getText().toString());
                userTable.put("type", type);
                break;
            case 1:
                userTable.put("username", user.getText().toString());
                userTable.put("password", pass.getText().toString());
                userTable.put("type", type);
                break;
            default:
                break;
        }
        userTable.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e==null){
                    Toast.makeText(RegistrarUserActivity.this,"Registrado!",Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }
            }
        });*/
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
