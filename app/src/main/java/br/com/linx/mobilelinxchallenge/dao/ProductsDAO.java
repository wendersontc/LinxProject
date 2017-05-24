package br.com.linx.mobilelinxchallenge.dao;

import android.content.Context;
import com.google.gson.Gson;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import br.com.linx.mobilelinxchallenge.model.Products;

/**
 * Created by Wenderson Roberto on 23/05/2017.
 */

/** Classe Dao para trabalhar as informações dos produtos, é onde é obtido os dados do arquivo json
 * @author Wenderson Roberto
 * @version 1.0
 */
public class ProductsDAO {
    Context context;
    String json = null;
    Products products = null;
    JSONObject objectJson;
    String stringJson;

    private static ProductsDAO mInstance = null;

    /**
     * Construtor
     * @param ctx
     */
    private ProductsDAO(Context ctx) {
        context = ctx;
        try {
            InputStream is = context.getAssets().open("challenge.json");

            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();

        }
    }

    public static ProductsDAO getInstance(Context ctx) {
        if (mInstance == null)
            mInstance = new ProductsDAO(ctx);

        return mInstance;
    }

    /**
     * Metodo para transformar os dados do json em array de Products
     * @return products1
     */
    public Products[] getProductsArray(){
        Gson gson = new Gson();

        try {
            objectJson = new JSONObject(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            stringJson = objectJson.getString("result");
            objectJson = new JSONObject(stringJson);
            stringJson = objectJson.getString("products");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Products[] products1 = gson.fromJson(stringJson,Products[].class);

        return products1;
    }
}
