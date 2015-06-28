package edu.ulima.estacionapp.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import edu.ulima.estacionapp.Bean.Usuario;
import edu.ulima.estacionapp.R;
import edu.ulima.estacionapp.Servicios.UserController;
import edu.ulima.estacionapp.ui.main.MainActivity;


public class LoginActivity extends ActionBarActivity {

    ImageView iv;
    EditText txtUser;
    EditText txtPass;
    Button ingresar,btnToRegister;
    private String user, pass;
    private ProgressDialog pd = null;
    private UserController controlador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        controlador = UserController.getInstance();
        txtUser=(EditText) findViewById(R.id.user);
        txtPass=(EditText) findViewById(R.id.password);
        iv = (ImageView) findViewById(R.id.img_titulo);
        ingresar = (Button) findViewById(R.id.btnLogin);
        btnToRegister=(Button) findViewById(R.id.btnToRegister);

        final Animation anim = AnimationUtils.loadAnimation(this,
                R.anim.animacion_titulo);
        final Animation anim_text = AnimationUtils.loadAnimation(this,
                R.anim.animacion_input);
        final Animation anim_btn = AnimationUtils.loadAnimation(this,
                R.anim.animacion_button);

        iv.startAnimation(anim);
        txtUser.startAnimation(anim_text);
        txtPass.startAnimation(anim_text);
        ingresar.startAnimation(anim_btn);
        btnToRegister.startAnimation(anim_btn);
    }

    public void clickLogin(View v) {
        user = txtUser.getText().toString();
        pass = txtPass.getText().toString();
        pd = ProgressDialog.show(this, "Conectando al Servidor",
                "Espere unos segundos...", true, false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 200);
        if (user != null && pass != null) {
            Log.e("user/pass", user + "/" + pass);
            ParseUser.logInInBackground(user, pass, new LogInCallback() {
                @Override
                public void done(ParseUser parseUser, ParseException e) {
                    if (parseUser != null){
                        controlador.setUsuario(new Usuario(parseUser.getUsername(),parseUser.getEmail(),parseUser.getInt("type")));
                        Log.e("setearid user", parseUser.getObjectId().toString());
                        controlador.getUsuario().setId(parseUser.getObjectId().toString());
                        Log.e("verificar id user", controlador.getUsuario().getId());
                        login(controlador.getUsuario());
                    }else
                        Toast.makeText(LoginActivity.this,"Usuario y/o Clave no valido",Toast.LENGTH_LONG).show();
                    pd.dismiss();
                }
        });
        }
        else{
            Toast.makeText(this,"Campos no pueden ser vacios",Toast.LENGTH_SHORT).show();
            pd.dismiss();
        }
    }
    private void login(Usuario user){
        Intent i=null;

        switch (user.getType()){
            case 0:
                i = new Intent(LoginActivity.this, MainActivity.class);
                break;
            case 1:
                i = new Intent(LoginActivity.this, MainActivity.class);
                break;
            case 2:
                i = new Intent(LoginActivity.this, MainActivity.class);
                break;
            default:
                break;
        }
        startActivity(i);
    };

    public void clickToRegistrarUser(View view){
        Intent i = new Intent(LoginActivity.this,RegistrarUserActivity.class);
        startActivity(i);
    }
}
