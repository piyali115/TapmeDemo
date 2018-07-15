package in.demo.tapmedemo.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import in.demo.tapmedemo.R;


public class ViewPagerAdapter extends PagerAdapter {



    public LayoutInflater inflater;
    private Context context;
    ArrayList<String> list= null;



    public ViewPagerAdapter(Context context, ArrayList<String> list) {

        this.context=context;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==(LinearLayout)object;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {

        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.viewpager_swipe,container,false);
        ImageView img=(ImageView)view.findViewById(R.id.image_swipe);

                Picasso.with(context)
                        .load(list.get(position))
                        .resize(800, 500)
                        .into(img);





        ((ViewPager)container).addView(view);
        return view;


    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((LinearLayout) object);
    }
}
