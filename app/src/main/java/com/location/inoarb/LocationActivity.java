package com.location.inoarb;

        import android.app.Dialog;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.Toast;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.core.app.ActivityCompat;
        import androidx.core.app.ActivityOptionsCompat;

        import com.google.gson.Gson;
        import com.location.inoarb.builder.Builder;
        import com.location.inoarb.builder.impl.InoarbRequestEventBuilder;
        import com.location.inoarb.client.SimpleHttpClient;
        import com.location.inoarb.dto.InoarbRequestEventDTO;
        import com.location.inoarb.indicator.UrlResourceIndicator;

public class LocationActivity extends AppCompatActivity {

    private Dialog sucessDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        sucessDialog = new Dialog(this);

        try {

            // Toast.makeText(this, "Start!", Toast.LENGTH_LONG).show();

        }catch(Exception ex){

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }

        EditText txtCidade = (EditText) findViewById(R.id.txtCidade);

    }

    public void bdok(View view){

        final Builder<InoarbRequestEventDTO, AppCompatActivity> builder =
                new InoarbRequestEventBuilder();

        final InoarbRequestEventDTO inoarbRequestEventDTO =
                builder.build(LocationActivity.this);


        final Gson gson = new Gson();
        final String content = gson.toJson(inoarbRequestEventDTO);

        //Toast.makeText(LocationActivity.this, content, Toast.LENGTH_LONG).show();
        final SimpleHttpClient simpleHttpClient = new SimpleHttpClient();
        //final //String response =
        simpleHttpClient.<String>executeRequestConcurrently(content, UrlResourceIndicator.REQUEST_EVENT.toString(), "PUT")
                .start();

        Button btnConfirmado;
        sucessDialog.setContentView(R.layout.epic_popup_positive);
        btnConfirmado = (Button) sucessDialog.findViewById(R.id.btnOk);
        btnConfirmado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sucessDialog.dismiss();
                proxlayout();
            }
        });
        sucessDialog.show();
        // Toast.makeText(LocationActivity.this, "", Toast.LENGTH_LONG).show();
    }

    //chamada de activity inicial e encerrar classe atual.
    private void proxlayout() {
        Intent intent = new Intent(this,com.location.inoarb.StartActivity.class);
        ActivityOptionsCompat activityOptionCompat = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(), R.anim.fade_in, R.anim.mover_direita);
        ActivityCompat.startActivity(this, intent, activityOptionCompat.toBundle());
        finish();
    }

}
