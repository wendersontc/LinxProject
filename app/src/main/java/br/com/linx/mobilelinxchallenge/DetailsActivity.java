package br.com.linx.mobilelinxchallenge;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import br.com.linx.mobilelinxchallenge.utils.RenderImage;

/**
 * Created by Wenderson Roberto on 25/05/2017.
 */

/**Classe Activity para exibir detalhes do produto e botao que contém o link para exibição do produto na web
 * @author Wenderson Roberto
 * @version 1.0
 */
public class DetailsActivity extends AppCompatActivity{
    ImageView image;
    TextView details_price,details_last,details_name,details_desc;
    FloatingActionButton details_link;
    Bitmap bitmap;
    private RequestQueue rq;
    private ImageLoader imageLoader;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        initializeComponents();


        final Bundle extras = getIntent().getExtras();
        final String imagem = (String) extras.getSerializable("imagem");

        rq = Volley.newRequestQueue(DetailsActivity.this);
        imageLoader = new RenderImage().CacheImageLoader(rq);
        imageLoader.get(imagem,imageLoader.getImageListener(image,R.mipmap.ic_launcher,R.mipmap.cupcake));

        details_name.setText((String) extras.getSerializable("name"));
        details_last.setText((String) extras.getSerializable("last_price"));
        details_price.setText("Por " + (String) extras.getSerializable("price"));
        details_desc.setText((String) extras.getSerializable("desc"));
        Typeface face = Typeface.createFromAsset(getAssets(),
                "fonts/roboto.ttf");
        details_last.setPaintFlags(details_last.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        details_name.setTypeface(face);
        details_last.setTypeface(face);
        details_price.setTypeface(face);
        details_desc.setTypeface(face);

        //redireciona usuario para navegador através do link
        details_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse((String) extras.getSerializable("link"));
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

    }

    /** Método para iniciar componentes de view */
    public void initializeComponents(){
        image = (ImageView) findViewById(R.id.detais_image_view);
        details_last = (TextView) findViewById(R.id.details_last_view);
        details_price = (TextView) findViewById(R.id.details_price_view);
        details_name =  (TextView) findViewById(R.id.detais_name_view);
        details_desc =  (TextView) findViewById(R.id.details_desc_view);
        details_link = (FloatingActionButton) findViewById(R.id.details_link_button);
    }
}
