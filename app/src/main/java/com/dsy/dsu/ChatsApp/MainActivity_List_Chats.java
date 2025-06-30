package com.dsy.dsu.ChatsApp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;


import com.dsy.dsu.BusinessLogicForApps.GetPublicID.GetttingPublicID;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;

import com.dsy.dsu.R;
import com.sous.backasync.launch.ModuleQuety;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MainActivity_List_Chats extends FragmentActivity {

    private  FragmentManager fragmentManager;
    private  FragmentTransaction fragmentTransaction;
    private String РежимЗапускаАктивтиЧата=new String();
   private Long ПолученыйIDДляЧата=0l;
    private String ПолученыйФИОIDДляЧата=new String();
    private Long ПолученыйUUIDДляЧатаПриУжеСуществуещйПперески=0l;
    private HashMap<String, Object> ХэщЗапусАктивтиИзФрагмента=null;
// TODO: 29.06.20
    final  private  String ИмяСлужбыСинхронизацииОдноразовая = "WorkManager Synchronizasiy_Data Disposable";
    private WorkInfo WorkInfoИнформацияОЗапущенойСлужбеОдноразовая;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main_history_chat);
            // TODO: 16.04.2025
            Log.d(getApplicationContext().getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()  );

            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                    | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                    | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


            МетодгенерируемВходящиеДанныеЧтоБЫПрередатьИхДаллеВдругиеАктивтиЛистЧат();
            // TODO: 27.04.2021 формируем внешний вид Чата через фрагменты
            // TODO: 22.12.2021  rEGISTA OENSIGMAL
            МетододеноразовойСлужбыСинхрониазции();
            fragmentManager=getSupportFragmentManager();
            fragmentTransaction=fragmentManager.beginTransaction();

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }



    private void МетодгенерируемВходящиеДанныеЧтоБЫПрередатьИхДаллеВдругиеАктивтиЛистЧат() {
        try{
            Intent ОбменДаннымиСДругимиАктивити = getIntent();
        ХэщЗапусАктивтиИзФрагмента = (HashMap<String, Object>)ОбменДаннымиСДругимиАктивити.getSerializableExtra("ОбменМеждуАктивти");
        // TODO: 21.12.2021
        if (ХэщЗапусАктивтиИзФрагмента!=null) {
            // TODO: 21.12.2021
            // TODO: 04.11.2021   ЗАПУСКАЕМ СИНХРОНИАХЦИИЮ  через ONESIGNAL
            Log.d(this.getClass().getName(), "ClassOneSingnalGenerator ХэщЗапусАктивтиИзФрагмента "+ХэщЗапусАктивтиИзФрагмента.keySet().toArray());
            ////
            Log.d(this.getClass().getName(), "   ХэщЗапусАктивтиИзФрагмента "+ХэщЗапусАктивтиИзФрагмента.values());

            РежимЗапускаАктивтиЧата=   ХэщЗапусАктивтиИзФрагмента.get("ЗапускАктивтиЧатИзФрагмента").toString();

            Log.d(this.getClass().getName(), "   РежимЗапускаАктивтиЧата "+РежимЗапускаАктивтиЧата);
            ////////////////////////ID приповтроном входет
            if (!РежимЗапускаАктивтиЧата.equalsIgnoreCase("Повторный Запск Фрагмента Контактов")) {
                //////
                if (РежимЗапускаАктивтиЧата.length()>0) {
                    ///
                    ПолученыйIDДляЧата= (Long) ХэщЗапусАктивтиИзФрагмента.get("ПолученыйIDДляЧата");
                    Log.d(this.getClass().getName(), "   ПолученыйIDДляЧата "+ПолученыйIDДляЧата);
                    ПолученыйФИОIDДляЧата= (String) ХэщЗапусАктивтиИзФрагмента.get("ПолученыйФИОIDДляЧата");
                    Log.d(this.getClass().getName(), "   ПолученыйФИОIDДляЧата "+ПолученыйФИОIDДляЧата);
                    ПолученыйUUIDДляЧатаПриУжеСуществуещйПперески=0l;
                    ПолученыйUUIDДляЧатаПриУжеСуществуещйПперески= (Long) ХэщЗапусАктивтиИзФрагмента.get("ПолученыйUUIDУжеСуществующийПерепискиПользоватлейДляЧата");
                    Log.d(this.getClass().getName(), "   ПолученыйUUIDДляЧатаПриУжеСуществуещйПперески "+ПолученыйUUIDДляЧатаПриУжеСуществуещйПперески);
                    // TODO: 27.12.2021
                    if(ПолученыйUUIDДляЧатаПриУжеСуществуещйПперески==null){
                        // TODO: 15.05.202
                        String Текущаятаблицы="chats";
                        ModuleQuety moduleQuety=new ModuleQuety(getApplicationContext());
                        Cursor Курсор_ПосикUUIDУжеСозданныйУжеЧатМеждуользователсиИЛНЕТ= moduleQuety.getModuleQuery(Текущаятаблицы," SELECT uuid_parent FROM "+Текущаятаблицы+"" +
                                " WHERE id_user   =  "+ПолученыйIDДляЧата   +" AND uuid_parent  IS NOT NULL " ,null);

                        Log.d(this.getClass().getName(), "\n"
                                + " время: " + new Date() + "\n+" +
                                " Класс в процессе... " + this.getClass().getName() + "\n" +
                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                + " Курсор_ПосикUUIDУжеСозданныйУжеЧатМеждуользователсиИЛНЕТ " +Курсор_ПосикUUIDУжеСозданныйУжеЧатМеждуользователсиИЛНЕТ);
                        // TODO: 21.12.2021  Если Есть Есть Хоть есть одна строчка значит пользоватми есть Чат и Создавать НЕ надо Еще Одну строчку
                        Log.d(this.getClass().getName(), "Курсор_ПосикUUIDУжеСозданныйУжеЧатМеждуользователсиИЛНЕТ.getCount() " + Курсор_ПосикUUIDУжеСозданныйУжеЧатМеждуользователсиИЛНЕТ.getCount());
                        // TODO: 21.12.2021  СОЗДАЕМ НОВОЕ СООБЩЕНИ  ИЗ ДВУХ ЧАСТЬЕЙ СОЗДАНИЕ В ДВУХ ТАБЛИЦАХ СТРОЧЕК CHATS AND DATA_CHATS ВТОРОЙ ХОД
                        if (Курсор_ПосикUUIDУжеСозданныйУжеЧатМеждуользователсиИЛНЕТ!=null) {
                            // TODO: 10.02.2022
                            if (Курсор_ПосикUUIDУжеСозданныйУжеЧатМеждуользователсиИЛНЕТ.getCount() > 0) {
                                // TODO: 22.12.2021
                                Курсор_ПосикUUIDУжеСозданныйУжеЧатМеждуользователсиИЛНЕТ.moveToFirst();
                                ПолученыйUUIDДляЧатаПриУжеСуществуещйПперески= Курсор_ПосикUUIDУжеСозданныйУжеЧатМеждуользователсиИЛНЕТ.getLong(0);
                                // TODO: 21.12.2021
                                Log.d(this.getClass().getName(), "Курсор_ПосикUUIDУжеСозданныйУжеЧатМеждуользователсиИЛНЕТ "  + Курсор_ПосикUUIDУжеСозданныйУжеЧатМеждуользователсиИЛНЕТ.getCount()+"\n"+
                                        "  ПолученыйUUIDДляЧатаПриУжеСуществуещйПперески " +ПолученыйUUIDДляЧатаПриУжеСуществуещйПперески);


                                // TODO: 27.12.2021 повтороеное присоение значению

                            }
                        }


                    }


                                if (ПолученыйUUIDДляЧатаПриУжеСуществуещйПперески==null){
                                    // TODO: 10.02.2022
                                    ПолученыйUUIDДляЧатаПриУжеСуществуещйПперески=0l;
                                }

                    Log.d(this.getClass().getName(), "   ПолученыйUUIDДляЧатаПриУжеСуществуещйПперески "+ПолученыйUUIDДляЧатаПриУжеСуществуещйПперески);

                }
            }

        }

    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }



    }






    //////////////////////////////////////////////////////////////МОДЕЛЬ MVC  ////////////////////////////////////////////////////////////////////////





    @Override
    protected void onStart() {
        super.onStart();
        //
        try{

            // TODO: 16.06.2021


        new MainActivity_List_Chats.MODEL(fragmentManager);

            // TODO: 16.06.2021 ЗАПУСКАЕМ НА АКТИВТИ ЧАТ КЛАСС VIEW КОТОРЫЙ ОТВЕЧАЕТ ЗА ОТОБРАЖЕНИЯ ИНФОРМАЦИИ И КЛИК

            new MainActivity_List_Chats.VIEW(fragmentManager);

            // TODO: 16.06.2021


            new MainActivity_List_Chats.CONTROLLER(fragmentManager);



            /////////////
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }


    }



















// TODO: 16.06.2021


    private class CONTROLLER {


        public CONTROLLER( FragmentManager fragmentManager ) {

            //
            try{

                
             
                /////////////
            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }




















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


        public MainAdapter(@NonNull @NotNull FragmentManager fm) {
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















// TODO: 16.06.2021

    private class MODEL{


        public MODEL( FragmentManager fragmentManager) {

            //
            try{







                /////////////
            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }





    }

// TODO: 16.06.2021


    private class VIEW {


        public VIEW(FragmentManager fragmentManager) {

            //
            try{




                Log.d(this.getClass().getName(), "   МетодОдинОтображаетьсяФрагмента(fragmentManager); РежимЗапускаАктивтиЧата"+РежимЗапускаАктивтиЧата);


                switch (РежимЗапускаАктивтиЧата){

                    case "Повторный Запск Фрагмента Контактов":

                        МетодОдинОтображаетьсяФрагментаПовторноеЗапускКонтактом(fragmentManager); ///ПОСЛЕ ВЫБОРА




                        Log.d(this.getClass().getName(), "   МетодОдинОтображаетьсяФрагмента(fragmentManager)  Повторный Запск Фрагмента Контактов ;"+РежимЗапускаАктивтиЧата);
                        //////////

                        break;
                    // TODO: 25.06.2021  запускаем код работает сразу два фрагмента









                    case "Повторный Запск Активти  Для Третьего Фрагмента":

                        МетодОдинОтображаетьсяФрагмента(fragmentManager); ///ПОСЛЕ ВЫБОРА




                        Log.d(this.getClass().getName(), "   МетодОдинОтображаетьсяФрагмента(fragmentManager);"+РежимЗапускаАктивтиЧата);
                        //////////

                        break;
                    // TODO: 25.06.2021  запускаем код работает сразу два фрагмента






                    default:

                        // TODO: 25.06.2021 ПЕРВЫЙ ЗАПУСК ФОРАГМЕНТОВ С СООБЩЕНИЯМИ


                        МетодОтображаетьсяДваФрагмента(fragmentManager);

                        Log.d(this.getClass().getName(), "     МетодОтображаетьсяДваФрагмента(fragmentManager);"+РежимЗапускаАктивтиЧата);

                        ///////
                        break;

                }

                // TODO: 05.07.2021






                /////////////
            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        // TODO: 25.06.2021  два фрагмента

        private void МетодОтображаетьсяДваФрагмента(FragmentManager fragmentManager) {

try{
    fragmentManager=getSupportFragmentManager();
    ///
    fragmentTransaction=fragmentManager.beginTransaction();


            Fragment_Messages_СообщенияЧата fragment_Messages_сообщенияЧата =new Fragment_Messages_СообщенияЧата();



            ///
            fragmentTransaction.add(R.id.Layoutmain_for_chats, fragment_Messages_сообщенияЧата);


    Log.d(this.getClass().getName(), " fragmentTransaction.isEmpty() " );


   // fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    // TODO: 17.11.2021

    Bundle bundleДляЧаста=new Bundle();
            ///
            //Put string

            ////
            //
            bundleДляЧаста.putString("ДаннеыДляФрагментаЧат","Создания Чата");


            //////
            // TODO: 22.06.2021  фрагменты

            ///todo публикум название таблицы или цифру его
    /////////////
} catch (Exception e) {
    e.printStackTrace();
    ///метод запись ошибок в таблицу
    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
    new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
}

        }

        // TODO: 25.06.2021 один фрагмент















        private void МетодОдинОтображаетьсяФрагмента(FragmentManager fragmentManager) {

  try{
            ///
            Bundle bundleДляФрагментаЧитатьПисатьПосылаемIDиФИО=new Bundle();
            ///

            // TODO: 29.06.2021 Получениый ID выбраного сотрудника передаем в фрагмент
      if (ПолученыйIDДляЧата>0) {

          bundleДляФрагментаЧитатьПисатьПосылаемIDиФИО.putLong("ПолученыйIDДляЧата", ПолученыйIDДляЧата);

          // TODO: 21.12.2021
          Log.d(this.getClass().getName(), " ПолученыйIDДляЧата  "+ПолученыйIDДляЧата + "ПолученыйФИОIDДляЧата " +ПолученыйФИОIDДляЧата);
      }


      if (ПолученыйФИОIDДляЧата!=null) {

          bundleДляФрагментаЧитатьПисатьПосылаемIDиФИО.putString("ПолученыйФИОIDДляЧата", ПолученыйФИОIDДляЧата);

          Log.d(this.getClass().getName(), " ПолученыйIDДляЧата  "+ПолученыйIDДляЧата + "ПолученыйФИОIDДляЧата " +ПолученыйФИОIDДляЧата);
      }

      //


      if (ПолученыйUUIDДляЧатаПриУжеСуществуещйПперески!=null  && ПолученыйUUIDДляЧатаПриУжеСуществуещйПперески>0) {

          // TODO: 26.12.2021
          bundleДляФрагментаЧитатьПисатьПосылаемIDиФИО.putLong("ПолученыйUUIDУжеСуществующийПерепискиПользоватлейДляЧата", ПолученыйUUIDДляЧатаПриУжеСуществуещйПперески);
      }

      Log.d(this.getClass().getName(), " ПолученыйUUIDУжеСуществующийПерепискиПользоватлейДляЧата  "+ПолученыйUUIDДляЧатаПриУжеСуществуещйПперески );

      //
      fragmentManager=getSupportFragmentManager();
      ///
      fragmentTransaction=fragmentManager.beginTransaction();


            Fragment_Writer_Read_ЧитатьПисатьЧата fragment_WriterRead_читатьПисатьЧата =new Fragment_Writer_Read_ЧитатьПисатьЧата();




            /////
            fragment_WriterRead_читатьПисатьЧата.setArguments(bundleДляФрагментаЧитатьПисатьПосылаемIDиФИО);

            ///
            fragmentTransaction.replace(R.id.Layoutmain_for_chats, fragment_WriterRead_читатьПисатьЧата);


      ///fragmentTransaction.addToBackStack(null);
            ///
            fragmentTransaction.commit();




            // TabLayoutИзЧата.getTabAt(1).setIcon(R.mipmap.icon_dsu1_emloeys_sonrudnik);     TabLayoutИзЧата.getTabAt(2).setTabLabelVisibility(TabLayout.TAB_LABEL_VISIBILITY_UNLABELED);


/*
                TabLayoutИзЧата.addTab(TabLayoutИзЧата.newTab().setText("Tab 1"));



                ///
                TabLayoutИзЧата.addTab(TabLayoutИзЧата.newTab().setText("Tab 2"));*/

/*
            TabLayoutИзЧата.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {



                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    Log.d(this.getClass().getName(), "ЗАПУСК СЛУЖБА  Синхронизация   " + new Date()+" tab " +tab.getText());


                    //


                    String КакойTab=tab.getText().toString();

                    switch (КакойTab)
                    {
                        case "Чат":
                            // your logic goes here!



                            Fragment_Messages_СообщенияЧата ФрагментRead_СообщенияЧата=new Fragment_Messages_СообщенияЧата();
                            ////
                            //  TabLayoutИзЧата.getTabAt(1).setText("ffffffff").setIcon();


                            ////
                            FragmentTransaction fTransФрагментRСообщенияЧата;
                            fTransФрагментRСообщенияЧата = fragmentManager.beginTransaction();
                            fTransФрагментRСообщенияЧата.replace(R.id.Relativemain_for_chats, ФрагментRead_СообщенияЧата); // fTrans.add(R.id.frgmCont, frag1);
                            fTransФрагментRСообщенияЧата.addToBackStack(null).commit();
                            ///
                            break;
*//*

                        // ...
                        case "Контакты":
                            // your logic goes here!

                            Fragment_Contacts_КонтактыЧата ФрагментКонтакты=new Fragment_Contacts_КонтактыЧата();
                            ////
                            //  TabLayoutИзЧата.getTabAt(1).setText("ffffffff").setIcon();


                            ////
                            FragmentTransaction fTransФрагментКонтакты;
                            fTransФрагментКонтакты = fragmentManager.beginTransaction();
                            fTransФрагментКонтакты.add(R.id.Relativemain_for_chats, ФрагментКонтакты); // fTrans.add(R.id.frgmCont, frag1);
                            fTransФрагментКонтакты.addToBackStack(null).commit();
                            ////
                            break;
*//*

                        // ...


                        //



                        // ...

                    }

                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {
                    Log.d(this.getClass().getName(), "ЗАПУСК СЛУЖБА  Синхронизация   " + new Date());





                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {
                    Log.d(this.getClass().getName(), "ЗАПУСК СЛУЖБА  Синхронизация   " + new Date());
                }

            });*/


            /////////////
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        }


        // TODO: 02.07.2021 метод повтоного запуска фрагмента контактов


























        // TODO: 25.06.2021 один фрагмент



        private void МетодОдинОтображаетьсяФрагментаПовторноеЗапускКонтактом(FragmentManager fragmentManager) {

            try{



            Bundle bundleДляЧаста=new Bundle();
            ///
            // TODO: 29.06.2021 Получениый ID выбраного сотрудника передаем в фрагмент
            bundleДляЧаста.putLong("ПолученыйIDДляЧата", ПолученыйIDДляЧата);


                Log.d(this.getClass().getName(), "   ПолученыйIDДляЧата "
                        +ПолученыйIDДляЧата);


                fragmentManager=getSupportFragmentManager();
                ///
                fragmentTransaction=fragmentManager.beginTransaction();


                
            Fragment_Contacts_КонтактыЧата fragment_Contacts_контактыЧата =new Fragment_Contacts_КонтактыЧата();
            ///
            fragmentTransaction.replace(R.id.Layoutmain_for_chats, fragment_Contacts_контактыЧата);

                // TODO: 19.02.2022

            //    fragmentTransaction.addToBackStack(null);
            ////
            fragmentTransaction.commit();




            // TabLayoutИзЧата.getTabAt(1).setIcon(R.mipmap.icon_dsu1_emloeys_sonrudnik);     TabLayoutИзЧата.getTabAt(2).setTabLabelVisibility(TabLayout.TAB_LABEL_VISIBILITY_UNLABELED);


/*
                TabLayoutИзЧата.addTab(TabLayoutИзЧата.newTab().setText("Tab 1"));



                ///
                TabLayoutИзЧата.addTab(TabLayoutИзЧата.newTab().setText("Tab 2"));*/

/*
            TabLayoutИзЧата.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {



                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    Log.d(this.getClass().getName(), "ЗАПУСК СЛУЖБА  Синхронизация   " + new Date()+" tab " +tab.getText());


                    //


                    String КакойTab=tab.getText().toString();

                    switch (КакойTab)
                    {
                        case "Чат":
                            // your logic goes here!



                            Fragment_Messages_СообщенияЧата ФрагментRead_СообщенияЧата=new Fragment_Messages_СообщенияЧата();
                            ////
                            //  TabLayoutИзЧата.getTabAt(1).setText("ffffffff").setIcon();


                            ////
                            FragmentTransaction fTransФрагментRСообщенияЧата;
                            fTransФрагментRСообщенияЧата = fragmentManager.beginTransaction();
                            fTransФрагментRСообщенияЧата.replace(R.id.Relativemain_for_chats, ФрагментRead_СообщенияЧата); // fTrans.add(R.id.frgmCont, frag1);
                            fTransФрагментRСообщенияЧата.addToBackStack(null).commit();
                            ///
                            break;
*//*

                        // ...
                        case "Контакты":
                            // your logic goes here!

                            Fragment_Contacts_КонтактыЧата ФрагментКонтакты=new Fragment_Contacts_КонтактыЧата();
                            ////
                            //  TabLayoutИзЧата.getTabAt(1).setText("ffffffff").setIcon();


                            ////
                            FragmentTransaction fTransФрагментКонтакты;
                            fTransФрагментКонтакты = fragmentManager.beginTransaction();
                            fTransФрагментКонтакты.add(R.id.Relativemain_for_chats, ФрагментКонтакты); // fTrans.add(R.id.frgmCont, frag1);
                            fTransФрагментКонтакты.addToBackStack(null).commit();
                            ////
                            break;
*//*

                        // ...


                        //



                        // ...

                    }

                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {
                    Log.d(this.getClass().getName(), "ЗАПУСК СЛУЖБА  Синхронизация   " + new Date());





                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {
                    Log.d(this.getClass().getName(), "ЗАПУСК СЛУЖБА  Синхронизация   " + new Date());
                }

            });*/

            /////////////
        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }


        }



    }

    // TODO: 29.06.2022






    private void МетододеноразовойСлужбыСинхрониазции() {
        // TODO: 28.12.2021

        try {
            // TODO: 28.12.2021  ЗАПУСК ОДНОРАЗОВОЙ СЛУЖБЫ
            Integer  ПубличныйIDДляОдноразовойСинхрониазции=   new GetttingPublicID().getttingPublicID(getApplicationContext());
            if (!WorkManager.getInstance(getApplicationContext()).getWorkInfosByTag(ИмяСлужбыСинхронизацииОдноразовая).get().isEmpty()) {
                WorkInfoИнформацияОЗапущенойСлужбеОдноразовая =
                        WorkManager.getInstance(getApplicationContext().getApplicationContext())
                                .getWorkInfosByTag(ИмяСлужбыСинхронизацииОдноразовая).get().get(0);
                if (WorkInfoИнформацияОЗапущенойСлужбеОдноразовая.getState().compareTo(WorkInfo.State.RUNNING) != 0) {
                    // TODO: 26.03.2023 launch Async

                    // TODO: 26.06.2022
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                }
            }
            Log.w(getApplicationContext().getClass().getName(), " ПЕРВЫЙ ЗАПУСК НА ФРАГМЕНТЕ ЧИТАТЬ И ПИСАТЬ ПубличныйIDДляОдноразовойСинхрониазции   "
                    + ПубличныйIDДляОдноразовойСинхрониазции + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
            ///


        }
    }


}