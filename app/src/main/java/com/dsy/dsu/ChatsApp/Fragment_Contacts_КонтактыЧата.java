package com.dsy.dsu.ChatsApp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.dsy.dsu.BusinessLogicForApps.JbossAdress.JbossContext;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;

import com.dsy.dsu.R;
import com.sous.backasync.launch.ModuleQuety;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class Fragment_Contacts_КонтактыЧата extends Fragment    {

    // TODO: Rename parameter arguments, choose names that match

    View viewДляКонтактов =null;

    ListView ЛистВьюДляКонтактыЧата=null;

    Cursor КурсорДанныеДляКонтактовЧата=null;

    private Long ПолученыйIDДляЧата;


    Integer   ПубличноеIDПолученныйИзСервлетаДляUUID=0;

    // TODO: 12.10.2021  Ссылка Менеджер Потоков

    JbossContext Class_Engine_SQLГдеНаходитьсяМенеджерПотоков =null;





    @Override
    public void onStop() {
        super.onStop();

        Log.d(this.getClass().getName(), "  onStop");
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Log.d(getContext().getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() );
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        ///


    }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        try{

            viewДляКонтактов= inflater.inflate(R.layout.fragment2_layout, container, false);// viewДляКонтактов= inflater.inflate(R.layout.fragment2_layout, container, false);
            ////
            ЛистВьюДляКонтактыЧата = (ListView) viewДляКонтактов.findViewById(R.id.list);
            Class_Engine_SQLГдеНаходитьсяМенеджерПотоков =new JbossContext(getActivity());
            // TODO ////////////////////////////МОДЕЛЬ MVC ////////////////////////////////////////////////////////////////////////////////////////////////
            new Fragment_Contacts_КонтактыЧата. MODEL(getActivity()).   МетодЗагрузкиДанныхДляФрагентаКонтакты();
            new Fragment_Contacts_КонтактыЧата.VIEW(getActivity());
            new Fragment_Contacts_КонтактыЧата. CONTROLLER(getActivity());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
          new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            ///


        }
        return  viewДляКонтактов;
    }








    // TODO: 18.06.2021  класс вью
      private class VIEW {

        public VIEW(Activity  activity) {
            SimpleCursorAdapter АдаптерДляКонтактовЧата=null;
            try{
                Log.d(this.getClass().getName(), "  VIEW   " );

                if (КурсорДанныеДляКонтактовЧата.getCount()>0) {
                    /////////
                    Log.d(this.getClass().getName(), "  КурсорДанныеДляСообщенийЧата   "  + КурсорДанныеДляКонтактовЧата.getCount());
                }
                        /////
                АдаптерДляКонтактовЧата =
                        new SimpleCursorAdapter(getContext(),
                                R.layout.simple_for_chats_contact, КурсорДанныеДляКонтактовЧата,
                                new String[]{"name"},
                                new int[]{android.R.id.text1}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
                SimpleCursorAdapter.ViewBinder БиндингДляКонтактовЧата = new SimpleCursorAdapter.ViewBinder() {
                    @Override
                    public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                        if (view.getId()==android.R.id.text1) {
                            МетодЗаполенияДаннымиАдаптераВоВрагментеКонтактыДляЧта(view, cursor);
                            // TODO: 22.06.2021 добавляем значёк
                            МетодОформельнияВизульаноговАдаптереSimpleВоФрагментеКонтакты((TextView) view);
                            /////TODO
                            return true;
                        }
                        return false;
                    }

                    private void МетодЗаполенияДаннымиАдаптераВоВрагментеКонтактыДляЧта(View view, Cursor cursor) {
                        try{
                        Log.d(this.getClass().getName()," ClassActitytyClassActityty  view.getId() "+ view.getId());
                        // TODO: 22.06.2021 заполняем данными фрагмент
                        int ГдеUUID= cursor.getColumnIndex("_id");
                        // TODO: 29.04.2021
                        long ПолученныйUUID=0l;
                        ПолученныйUUID = cursor.getLong(ГдеUUID);
                        Log.d(  this.getClass().getName(), " метод посика уже существующего сотрудника в базе андройжа ПолученныйUUID[0] "
                                +ПолученныйUUID);
                        // TODO: 29.04.2021 Вписываем UUID для конкретного сотрудника
                        if (ПолученныйUUID >0) {
                            // TODO: 29.04.2021 ПрисваемваемКАЖДОМУ СОТРУДНИКУ ID
                            ((TextView) view).setTag(String.valueOf(ПолученныйUUID));
                            Log.d(  this.getClass().getName(), "  ПолученныйUUID iew.getTag() "
                                    +view.getTag());
                        }

                        // TODO: 22.06.2021 заполняем данными фрагмент
                        int ГдеФИО= cursor.getColumnIndex("name");
                        /////////////////
                        String   ПолученныйФИО = cursor.getString(ГдеФИО);
                        Log.d(  this.getClass().getName(), " метод посика уже существующего сотрудника в базе андройжа ПолученныйФИО"
                                +ПолученныйФИО);
                        // TODO: 29.04.2021 Вписываем UUID для конкретного сотрудника
                        if (ПолученныйФИО!=null && ПолученныйФИО.length()>0   &&  ПолученныйФИО.trim().matches("[^t,s,v].*")) {
                            // TODO: 29.04.2021 ПрисваемваемКАЖДОМУ СОТРУДНИКУ ID
                            StringBuffer БуферФИОДляЧатаКонтакты=new StringBuffer(ПолученныйФИО.trim());
                            // TODO: 24.06.2021  устанавливаем значения ФИО внутри SimpleCursorAdapter

                            ((TextView) view).setText("    " +БуферФИОДляЧатаКонтакты.toString());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }


                    }

                    private void МетодОформельнияВизульаноговАдаптереSimpleВоФрагментеКонтакты(TextView view) {
                        Drawable icon = null;
                        icon = getResources().getDrawable(R.drawable.icon_dsu1_for_fragment1_chat2);
                        icon.setBounds(10, 0, 90, 85);
                        view.setPadding(5, 20,40, 20);
                        view.setCompoundDrawables(icon, null, null, null);
                        view.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                        // TODO: 22.06.2021 выравниваем тексвид
                        view.setBackgroundResource(R.drawable.style_for_chat_for_fragmaent_contact);
                    }
                };
                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
              new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());

            }
        }
    }


















    final   private class CONTROLLER implements AdapterView.OnItemClickListener  {

        private  String ПолученыйФИОIDДляЧата;

        /////
        public CONTROLLER(Activity  activity) {
            try{

                Log.d(this.getClass().getName(), "  CONTROLLER  ");

                ЛистВьюДляКонтактыЧата.setOnItemClickListener(this);
                ///
                viewДляКонтактов.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Log.d(this.getClass().getName(), " ЛистВьюДляКонтактыЧата.setOnItemClickListener  final   private class CONTROLLER implements  " + viewДляКонтактов.getId());
                    }
                });
                //  Курсор_ДляЗагрузкиСотрудников.close();


           /*     LinkedTransferQueue linkedTransferQueue = new LinkedTransferQueue<>();
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    Iterator iterator= linkedTransferQueue.parallelStream().iterator();
                 linkedTransferQueue.parallelStream().forEach((qu)-> {

                     linkedTransferQueue.put(qu);


                 });


                }*/

            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
              new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                ///


            }
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            try{
                Log.d(this.getClass().getName(), " onItemClick MyWork_Update_ОбновлениеПО СЛУЖБА  " + view.getId());

////todo новое оформельение когда выбираем пользвоателя с кем хотим вести переписку

                ///
                TextView textViewВытаскиваемIDДляЧатаНАкогоНажали=  view.findViewById(android.R.id.text1);

                //



                textViewВытаскиваемIDДляЧатаНАкогоНажали.setBackgroundResource(R.drawable.style_for_chat_for_fragmaent_contact_for_click);

               //view.setBackgroundResource(R.drawable.style_for_chat_for_fragmaent_contact_for_click);


                ПолученыйIDДляЧата=0l;


 ///////////////////////////////TODO получения  ID
                ///////////////////////////////TODO получения  ID
                Long ПолученныйНАКогоКликнулиUUID=Long.parseLong(textViewВытаскиваемIDДляЧатаНАкогоНажали.getTag().toString());

                ПолученыйIDДляЧата=ПолученныйНАКогоКликнулиUUID;

                Log.d(this.getClass().getName(), "  textView.getTag() "+ textViewВытаскиваемIDДляЧатаНАкогоНажали.getTag()  + " ПолученыйIDДляЧата " +ПолученыйIDДляЧата+
                        "  ПолученныйНАКогоКликнулиUUID " +ПолученныйНАКогоКликнулиUUID);


                ///
                ПолученыйФИОIDДляЧата=new String();
                //
                ПолученыйФИОIDДляЧата=textViewВытаскиваемIDДляЧатаНАкогоНажали.getText().toString().trim();

                Log.d(this.getClass().getName(),  " ПолученыйФИОIDДляЧата " +ПолученыйФИОIDДляЧата);



                ///

                View textViewParent=  parent.getChildAt(position);

                //

                ///
                Log.d(this.getClass().getName(), " textViewParent  " + textViewParent);

                ///

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        /*Toast.makeText(getActivity(),
                                "Фрагмент НОМер 1  "   + " textView.getTag() "+ textViewВытаскиваемIDДляЧатаНАкогоНажали.getTag()   , Toast.LENGTH_LONG).show();*/
                        //

                        ////
                        Log.d(this.getClass().getName(), "  textView.getTag() "+ textViewВытаскиваемIDДляЧатаНАкогоНажали.getTag() );

                    }
                });



                // TODO: 24.06.2021 вызов Третьего Фрагмента При Клике по Сотруднику





 /*               Fragment_Writer_Read_ЧитатьПисатьЧата Фрагмент_Read_WriteЧата =new Fragment_Writer_Read_ЧитатьПисатьЧата();
                ////
              //  TabLayoutИзЧата.getTabAt(1).setText("ffffffff").setIcon();

                // Inflate the layout for this fragment
*/



                Intent intentЗапускЧатаИзФрагмента=new Intent();

                //////
                // intentЗапускЧата.setClass(getApplicationContext(), MainActivity_history_chat_test.class);

                intentЗапускЧатаИзФрагмента.setClass(getActivity(), MainActivity_List_Chats.class);

                HashMap<String,Object> ХэщЗапусАктивтиИзФрагмента=new HashMap<>();
                //
                ХэщЗапусАктивтиИзФрагмента.put("ЗапускАктивтиЧатИзФрагмента","Повторный Запск Активти  Для Третьего Фрагмента");







                if (ПолученыйIDДляЧата>0) {
                    // TODO: 29.06.2021 второй парамент передают ID
                    ХэщЗапусАктивтиИзФрагмента.put("ПолученыйIDДляЧата",ПолученыйIDДляЧата);
                }

                ///

                // TODO: 30.06.2021 ПолучаемКто написла чеоез ЗАПРОС


                    // TODO: 29.06.2021 второй парамент передают ID
                if (ПолученыйФИОIDДляЧата.length()>0) {
                    //////
                    ХэщЗапусАктивтиИзФрагмента.put("ПолученыйФИОIDДляЧата",ПолученыйФИОIDДляЧата);
                }


                //
                intentЗапускЧатаИзФрагмента.putExtra("ОбменМеждуАктивти",ХэщЗапусАктивтиИзФрагмента);


                intentЗапускЧатаИзФрагмента.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(intentЗапускЧатаИзФрагмента);

                // TODO: 09.02.2022
               // getActivity().getFragmentManager().beginTransaction().remove(null).commit();



                //////

            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
              new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                ///


            }
        }
    }





    final   private class MODEL {
        /////
        public MODEL(Activity activity) {
            try{

                Log.d(this.getClass().getName(), "  MODEL  ");


               // МетодЗагрузкиДанныхДляФрагентаКонтакты();


            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
              new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                ///


            }


        }

        // TODO: 28.09.2021
        protected void МетодЗагрузкиДанныхДляФрагентаКонтакты() {
            // TODO: 20.05.2025
            try{
                // TODO: 15.05.202
                String Текущаятаблицы="chat_users";
                ModuleQuety moduleQuety=new ModuleQuety(getContext());
                Cursor    Курсор_ВычисляемПУбличныйID= moduleQuety.getModuleQuery(Текущаятаблицы," SELECT *  FROM "+Текущаятаблицы+" AS D" +
                        "  WHERE D.id_user='"+ПубличноеIDПолученныйИзСервлетаДляUUID+"' AND  D.message IS NOT NULL "+
                        "   ORDER BY D.status_write, D.date_update DESC ,D.id DESC " ,null);

                Log.d(this.getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                        + " Курсор_ВычисляемПУбличныйID " +Курсор_ВычисляемПУбличныйID);


                if(Курсор_ВычисляемПУбличныйID.getCount()>0){
                    Курсор_ВычисляемПУбличныйID.moveToFirst();
                    ПубличноеIDПолученныйИзСервлетаДляUUID=Курсор_ВычисляемПУбличныйID.getInt(0);
                }
                Log.d(this.getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                        + " ПубличноеIDПолученныйИзСервлетаДляUUID " +ПубличноеIDПолученныйИзСервлетаДляUUID);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
          new RecordNewErros(getActivity()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        } }

    }











    private class MainAdapter extends FragmentPagerAdapter {

        ArrayList<String> stringArrayList=new ArrayList<>();
        //
        List<Fragment> fragmentList=new ArrayList<>();


        /**
         *
         */
        public void addFragment(Fragment fragment,String title){
            ////add title

            stringArrayList.add(title);
            ///
            fragmentList.add(fragment);

        }


        public MainAdapter(@NonNull @NotNull FragmentManager fm, FragmentActivity activity) {
            super(fm);
        }

        @NonNull
        @NotNull
        @Override
        public Fragment getItem(int position) {
            /**
             * return fragment position
             */
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Nullable
        @org.jetbrains.annotations.Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            /**
             * Return array list position
             */
            return stringArrayList.get(position);
        }
    }

}