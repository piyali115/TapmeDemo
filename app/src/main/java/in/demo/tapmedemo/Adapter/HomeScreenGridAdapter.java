package in.demo.tapmedemo.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import in.demo.tapmedemo.Model.OfferList;
import in.demo.tapmedemo.R;


/**
 * Created by piyali on 27/1/17.
 */
public class HomeScreenGridAdapter extends ArrayAdapter {

    ArrayList<OfferList> list;
    Context mContext;
    int resourceID;

    public HomeScreenGridAdapter(Context mContext, int resourceID, ArrayList<OfferList> list)
    {
        super(mContext, resourceID,list);
        this.mContext = mContext;
        this.resourceID = resourceID;
        this.list = list;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = convertView;

         ViewHolder holder;

        if (convertView==null){

            LayoutInflater inflater =  (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(resourceID, null);
            holder = new ViewHolder();

            holder.image_child = (ImageView) view.findViewById(R.id.iv_child_gridview);
           // holder.subcat_name_child_listview_home_screen = (TextView) view.findViewById(R.id.subcat_name_child_listview_home_screen);


            view.setTag(holder);
        }

        else
            holder = (ViewHolder) view.getTag();


        if (!list.get(position).getOfferArtwork().get2x().equalsIgnoreCase("")){
            Picasso.with(mContext)
                    .load(list.get(position).getOfferArtwork().get2x())
                    .resize(550,400)
                    .into(holder.image_child);

        }




        return view;
    }

    public static class ViewHolder
    {

        public ImageView image_child;

    }



}
