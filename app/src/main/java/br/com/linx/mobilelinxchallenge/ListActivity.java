package br.com.linx.mobilelinxchallenge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import br.com.linx.mobilelinxchallenge.adapter.ListProductsAdapter;
import br.com.linx.mobilelinxchallenge.dao.ProductsDAO;
import br.com.linx.mobilelinxchallenge.model.Products;
import br.com.linx.mobilelinxchallenge.utils.RenderImage;

/**
 * Created by Wenderson Roberto on 23/05/2017.
 */

/**Classe Activity Principal, onde é adaptado o list para visualização, e onde é trabalhado o cache da imagem
 * @author Wenderson Roberto
 * @version 1.0
 */
public class ListActivity extends AppCompatActivity {

    private RequestQueue rq;
    private ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        final Products[] products = ProductsDAO.getInstance( getApplicationContext() ).getProductsArray();
        rq = Volley.newRequestQueue(ListActivity.this);

        //renderizando a imagem juntamente com o cache para uso junto ao adapter
        imageLoader = new RenderImage().CacheImageLoader(rq);

        ListProductsAdapter adapter = new ListProductsAdapter(this, R.layout.list_item_fragment, products, imageLoader);
        ListView listaView = (ListView) findViewById(R.id.lista);

        listaView.setAdapter(adapter);

        //click para obter as informações e levar para outra Acvity mostrar na view as informações
        listaView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                bundle.putString("imagem", products[i].getImage());
                bundle.putString("link", products[i].getLink());
                bundle.putString("name", products[i].getName());
                bundle.putString("last_price", products[i].getLast_price());
                bundle.putString("price", products[i].getPrice());
                bundle.putString("rating", products[i].getRating());
                bundle.putString("desc", products[i].getDesc());
                Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
