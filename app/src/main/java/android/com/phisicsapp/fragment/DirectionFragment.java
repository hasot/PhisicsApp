package android.com.phisicsapp.fragment;

import android.com.phisicsapp.R;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Arrays;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/**
 * Created by root on 19.11.16.
 */
public class DirectionFragment  extends Fragment {

    private static final int LAYOUT = R.layout.fragment_direction;


    private String[] listview_names = {"India","Bangladesh", "China","Indonesia" };

    private int[] listview_images   = {R.drawable.as ,R.drawable.aa,R.drawable.ww,R.drawable.dd};

    private ListView lv;

    private ArrayList<String> array_sort;
    private ArrayList<Integer> image_sort;


    public static DirectionFragment getInstance() {
        Bundle args = new Bundle();
        DirectionFragment fragment = new DirectionFragment();
        fragment.setArguments(args);

        return fragment;
    }


    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);

        lv =(ListView) view.findViewById(android.R.id.list);

        array_sort=new ArrayList<String> (Arrays.asList(listview_names));
        image_sort=new ArrayList<Integer>();
        for (int index = 0; index < listview_images.length; index++)
        {
            image_sort.add(listview_images[index]);
        }
        lv.setAdapter(new bsAdapter(getActivity()));

        lv.setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0,
                                    View arg1, int position, long arg3)
            {
                Toast.makeText(getActivity().getApplicationContext(), array_sort.get(position),
                        Toast.LENGTH_SHORT).show();
            }
        });

        

        return view;
    }

    public class bsAdapter extends BaseAdapter
    {
        Activity cntx;
        public bsAdapter(Activity context)
        {
            // TODO Auto-generated constructor stub
            this.cntx=context;
        }
        public int getCount()
        {
            // TODO Auto-generated method stub
            return array_sort.size();
        }
        public Object getItem(int position)
        {
            // TODO Auto-generated method stub
            return array_sort.get(position);
        }
        public long getItemId(int position)
        {
            // TODO Auto-generated method stub
            return array_sort.size();
        }
        public View getView(final int position, View convertView, ViewGroup parent)
        {
            View row=null;

            LayoutInflater inflater=cntx.getLayoutInflater();
            row=inflater.inflate(R.layout.fragment_direction_list, null);

            TextView   tv = (TextView) row.findViewById(R.id.title);
            ImageView im = (ImageView) row.findViewById(R.id.imageView);

            tv.setText(array_sort.get(position));
            im.setImageDrawable(getResources().getDrawable(image_sort.get(position)));

            return row;
        }
    }

}


