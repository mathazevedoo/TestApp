package com.location.inoarb;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;

import java.util.Scanner;

public class LocationActivity extends AppCompatActivity {

    Dialog sucessDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        sucessDialog = new Dialog(this);
        EditText txtCidade = (EditText) findViewById(R.id.txtCidade);
        EditText txtCpf = (EditText) findViewById(R.id.txtCpf);

    EditText txtNome = (EditText) findViewById(R.id.txtNome);
        txtNome.setFilters(new InputFilter[] {
        new InputFilter() {
            @Override
            public CharSequence filter(CharSequence cs, int start,
                                       int end, Spanned spanned, int dStart, int dEnd) {
                // TODO Auto-generated method stub
                if(cs.equals("")){ // for backspace
                    return cs;
                }
                if(cs.toString().matches("[a-zA-Z ]+")){
                    return cs;
                }
                return "";
            }
        }
    });
}
   //chamada de activity inicial e encerrar classe atual.
    public void proxlayout(){
        Intent intent = new Intent(this,com.location.inoarb.StartActivity.class);
        ActivityOptionsCompat activityOptionCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.mover_direita);
        ActivityCompat.startActivity(this, intent, activityOptionCompat.toBundle());
        //startActivity(new Intent(this,com.location.inoarb.StartActivity.class));
        finish();
    }

    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.mover_esquerda, R.anim.fade_out);
    }

    public void ShowPopup (View v){
        ImageView imgClose;
        Button btnConfirmado;
        sucessDialog.setContentView(R.layout.epic_popup_positive);
        sucessDialog.setContentView(R.layout.epic_popup_positive);
        sucessDialog.setContentView(R.layout.epic_popup_positive);
        imgClose = (ImageView) sucessDialog.findViewById(R.id.imgClose);
        btnConfirmado = (Button) sucessDialog.findViewById(R.id.btnOk);
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sucessDialog.dismiss();
                proxlayout();
            }
        });
        sucessDialog.show();
        btnConfirmado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sucessDialog.dismiss();
                proxlayout();
            }
        });
        sucessDialog.show();


    }
}
