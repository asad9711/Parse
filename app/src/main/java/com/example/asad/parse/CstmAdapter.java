package com.example.asad.parse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import java.net.URL;

/**
 * Created by Asad on 01-03-2016.
 */
public class CstmAdapter extends BaseAdapter {
    private static LayoutInflater inflater = null;
    //    ImageView imageView;
//  Context context;
//    Bitmap image;
//    ImageView imageView;
    String[] title;
    Context c;
    URL[] url;

//    MainActivity.Holder holder;
    public CstmAdapter(Context cstmcontext, MainActivity.Holder holder) {
//        View rowView;
//        this.image = holder.image;
//        this.imageView=holder.imageView;
//        context = context;

        this.url=holder.imageurl;
        this.c=cstmcontext;
        this.title=holder.title;
        inflater = (LayoutInflater) cstmcontext.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public View getView(int position, View convertView, ViewGroup parent) {  // returns the View object for each row.
        View rowView;
        rowView = inflater.inflate(R.layout.each_row, null);

        ImageView imgView = (ImageView) rowView.findViewById(R.id.imageView1);
        TextView textView= (TextView) rowView.findViewById(R.id.textview);
        textView.setText(title[position]);
//        Drawable ob = new BitmapDrawable(Resources.getSystem(), image);
////        imgView.setBackgroundDrawable(ob);
//        Picasso.with(this)
//                .load("http://api.androidhive.info/music/images/adele.png")//loading single image
////              .placeholder(R.drawable.audi) // optional
////              .error(R.drawable.audi)         // optional
//                .into(imgView);

        Picasso.with(c)
                .load(String.valueOf(url[position]))//loading single image
//              .placeholder(R.drawable.audi) // optional
//              .error(R.drawable.audi)         // optional
                .into(imgView);
        return rowView;
    }
//    public Bitmap getImage()
//    {
//        return Bitmap.createScaledBitmap(image, 100, 100, true);
//    }

    @Override
    public int getCount() {
        return url.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
