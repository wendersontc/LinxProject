package br.com.linx.mobilelinxchallenge.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;

import br.com.linx.mobilelinxchallenge.R;
import br.com.linx.mobilelinxchallenge.model.Products;


/**
 * Created by Wenderson Roberto on 23/05/17.
 */

/**Classe Adapter para exibir a listagem de produtos com seus respectivos itens, padrão ViewHolder
 * @author Wenderson Roberto
 * @version 1.0
 */
public class ListProductsAdapter extends ArrayAdapter<Products> {
    private Products [] mData;
    private Context mContext;
    private int mLayoutResourceId;
    private ImageLoader imageloader;

    public ListProductsAdapter(Context context, int resource, Products[] data, ImageLoader il) {
        super(context, resource, data);
        mContext = context;
        mData = data;
        imageloader = il;
        mLayoutResourceId = resource;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ListProductsAdapter.ViewHolderItem viewHolder;

        if(convertView == null){

            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            convertView = inflater.inflate(mLayoutResourceId, parent, false);

            viewHolder = new ListProductsAdapter.ViewHolderItem();
            viewHolder.textViewItem = (TextView) convertView.findViewById(R.id.textViewItem);
            viewHolder.textViewItem2 = (TextView) convertView.findViewById(R.id.textViewItem2);
            viewHolder.textViewItem3 = (TextView) convertView.findViewById(R.id.textViewItem3);
            viewHolder.imageViewItem = (ImageView) convertView.findViewById(R.id.image_view);

            convertView.setTag(viewHolder);

        }else{
            viewHolder = (ListProductsAdapter.ViewHolderItem) convertView.getTag();
        }

        String item = mData[position].getName();
        String item2 = mData[position].getLast_price();
        String item3 = mData[position].getPrice();

        //imagem para uso através do imageloader com o cache ativo
        imageloader.get(mData[position].getImage(),imageloader.getImageListener(viewHolder.imageViewItem,R.mipmap.ic_launcher,R.mipmap.cupcake));

        if(item != null) {
            viewHolder.textViewItem.setText(item);
            viewHolder.textViewItem2.setText(item2);

            viewHolder.textViewItem2.setPaintFlags(viewHolder.textViewItem2.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            viewHolder.textViewItem3.setText("Por " + item3);
            Typeface face = Typeface.createFromAsset(mContext.getAssets(),
                    "fonts/roboto.ttf");
            viewHolder.textViewItem.setTypeface(face);
            viewHolder.textViewItem2.setTypeface(face);
            viewHolder.textViewItem3.setTypeface(face);
            convertView.setTag(viewHolder);
        }

        return convertView;
    }

    /**
     * Classe View Holder com seus componentes que serão usados na view
     */
    static class ViewHolderItem {
        TextView textViewItem;
        TextView textViewItem2;
        TextView textViewItem3;
        ImageView imageViewItem;
    }
}
