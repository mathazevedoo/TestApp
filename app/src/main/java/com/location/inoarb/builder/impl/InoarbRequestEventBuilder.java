package com.location.inoarb.builder.impl;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.location.inoarb.R;
import com.location.inoarb.builder.Builder;
import com.location.inoarb.dto.InoarbRequestEventDTO;
import com.location.inoarb.indicator.InoarbEventIndicator;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public final class InoarbRequestEventBuilder implements Builder<InoarbRequestEventDTO, AppCompatActivity> {

    Bitmap bitmap;
    TextView txt;
    @Override
    public InoarbRequestEventDTO build(AppCompatActivity appCompatActivity) {


        final InoarbRequestEventDTO inoarbRequestEventDTO = new InoarbRequestEventDTO();

        final EditText txNome = (EditText) appCompatActivity.findViewById(R.id.txtNome);
        final EditText txCpf = (EditText) appCompatActivity.findViewById(R.id.txtCpf);
        final EditText txEndereco = (EditText) appCompatActivity.findViewById(R.id.txtEndereco);
        final EditText txCidade = (EditText) appCompatActivity.findViewById(R.id.txtCidade);
        final EditText txCep = (EditText) appCompatActivity.findViewById(R.id.txtCep);
        final EditText txTelefone = (EditText) appCompatActivity.findViewById(R.id.txtTelefone);

        /*
        final ImageView imageView = (ImageView) appCompatActivity.findViewById(R.id.image);
        bitmap = BitmapFactory.decodeResource(Resources.getSystem(),R.id.image);

        new AsyncTask<Void, Void, String>(){
            @Override
            protected String doInBackground(Void... voids){
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
                byte[] b = baos.toByteArray();

                String encodeImage = Base64.encodeToString(b,Base64.DEFAULT);


                return encodeImage;
            }

            @Override
            protected void onPostExecute(String s){
                txt.setText(s);
            }
        }.execute();
        */

        final Date agora = new Date();
        final SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        final String txProcessamentoData = sdf.format(agora);

        final Random rnd = new Random();
        final String simpleImagePath = "/tmp/" + String.valueOf(rnd.nextInt(512)) + ".png";

        inoarbRequestEventDTO.setName(txNome.getText().toString());
        inoarbRequestEventDTO.setDocument(txCpf.getText().toString());
        inoarbRequestEventDTO.setAddress(txEndereco.getText().toString());
        inoarbRequestEventDTO.setCity(txCidade.getText().toString());
        inoarbRequestEventDTO.setZip(txCep.getText().toString());
        inoarbRequestEventDTO.setPhone(txTelefone.getText().toString());
        inoarbRequestEventDTO.setEventDate(txProcessamentoData);
        inoarbRequestEventDTO.setUpdateDate(txProcessamentoData);
        inoarbRequestEventDTO.setEventType(InoarbEventIndicator.DEFAULT_EVENT.toString());
        inoarbRequestEventDTO.setImagePath(simpleImagePath);

        return inoarbRequestEventDTO;
    }
}
