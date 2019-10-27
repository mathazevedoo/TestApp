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
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LocationActivity extends AppCompatActivity {

    Dialog sucessDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        sucessDialog = new Dialog(this);
        EditText txtCidade = (EditText) findViewById(R.id.txtCidade);
        txtCidade.setFilters(new InputFilter[] {
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
        startActivity(new Intent(this,com.location.inoarb.StartActivity.class));
        finish();
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
