package android.com.phisicsapp.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.com.phisicsapp.R;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;


public class FragmentDirection extends Fragment {


    private static final int LAYOUT = R.layout.fragment_direction;

    private ListView lv;
    private View view;
    private TextView textView;
    private Toolbar toolbar;
    private ArrayList<String> array_sort;
    private ArrayList<Integer> image_sort;

    private int[] listview_images = {R.drawable.zemlakov, R.drawable.manuilov,
            R.drawable.cvetanskii, R.drawable.fomin, R.drawable.fain,
            R.drawable.kovalenko, R.drawable.malishevskii, R.drawable.sinavskii,
            R.drawable.bogatin, R.drawable.kiroi, R.drawable.bugaev,
            R.drawable.chebotaev, R.drawable.zorgano, R.drawable.soldatov,
            R.drawable.razumnai};


    // ЧТО_ТО С ЭТИМ НАДО СДЕЛАТЬ ЕПТААААААА
    private String[] listview_names = {"Земляков Вячеслав Викторович \n" + "ИО декана физического факультета \n" + "E-mail: vvzemlyakov@sfedu.ru ",
            "Мануилов Михаил Борисович \n" + "3аместитель декана по науке \n" + "E-mail: m_manuilov@sfedu.ru",
            "Цветянский Александр Леонидович \n" + "3аместитель декана по соц. вопросам \n" + "E-mail: altsvetyansky@sfedu.ru",
            "Фомин Георгий Викторович \n" + "3аместитель декана факультета физики по информатизации \n" + "E-mail: fomin@rsu.ru ",
            "Файн Марина Борисовна \n" + "Старший преподаватель \n" + "E-mail: mbfain@sfedu.ru ",
            "Коваленко Елена Викторовна \n" + "Cпециалист по учебно-методической работе \n" + "E-mail: kovalenko1970@sfedu.ru ",
            "Малышевский Вячеслав Сергеевич \n" + "Профессор кафедры теоретической и вычислительной физики физического факультета \n",
            "Синявский Геннадий Петрович \n" + "Председатель диссертационного совета \n" + "E-mail: sinyavsky@sfedu.ru ",
            "Богатин Александр Соломонович \n" + "3аведующий кафедрой общей физики \n" + "E-mail: asbbogatin@sfedu.ru",
            "Кирой Валерий Николаевич \n" + "3аведующий кафедрой биофизики и биокибернетики\n" + "E-mail: kiroy@sfedu.ru",
            "Бугаев Лусеген Арменакович \n" + "Член диссертационного совета \n" + "E-mail:  bugaev@sfedu.ru",
            "Чеботарев Геннадий Дмитриевич \n" + "Кафедpа квантовой pадиофизики - Профессор \n" + "E-mail: gdchebotarev@sfedu.ru",
            "Заргано Геннадий Филиппович \n" + "Ученый секретарь диссертационного совета \n" + "E-mail: gfzargano@sfedu.ru",
            "Солдатов Александр Владимирович \n" + "Член диссертационного совета\n" + "E-mail: soldatov@sfedu.ru ",
            "Разумная Анна Григорьевна \n" + "ИО заведующей кафедрой нанотехнологий \n" + "E-mail: agrazumnaya@sfedu.ru"

    };








    public static FragmentDirection newInstance() {
        FragmentDirection fragment = new FragmentDirection();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);
        lv =(ListView) view.findViewById(android.R.id.list);

            lv = (ListView) view.findViewById(android.R.id.list);
            array_sort = new ArrayList<String>(Arrays.asList(listview_names));
            image_sort = new ArrayList<Integer>();
            for (int index = 0; index < listview_images.length; index++) {
                image_sort.add(listview_images[index]);
            }
            lv.setAdapter(new FragmentDirection.bsAdapter(getActivity()));

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> arg0,
                                        View arg1, int position, long arg3) {
                    Toast.makeText(getActivity().getApplicationContext(), array_sort.get(position),
                            Toast.LENGTH_SHORT).show();
                }
            });


        return view;
    }

    public class bsAdapter extends BaseAdapter {
        Activity cntx;

        public bsAdapter(Activity context) {
            // TODO Auto-generated constructor stub
            this.cntx = context;
        }

        public int getCount() {
            // TODO Auto-generated method stub
            return array_sort.size();
        }

        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return array_sort.get(position);
        }

        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return array_sort.size();
        }

        public View getView(final int position, View convertView, ViewGroup parent) {
            View row = null;

            LayoutInflater inflater = cntx.getLayoutInflater();
            row = inflater.inflate(R.layout.fragment_direction_list, null);

            TextView tv = (TextView) row.findViewById(R.id.title);
            ImageView im = (ImageView) row.findViewById(R.id.imageView);

            tv.setText(array_sort.get(position));
            im.setImageDrawable(getResources().getDrawable(image_sort.get(position)));

            return row;
        }
    }

}
