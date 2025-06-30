package com.dsy.dsu.MaterialsShipmentApp;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.R;

import org.jetbrains.annotations.NotNull;


public class MainActivity_shipment_of_materials extends FragmentActivity implements  SubInterface_forОтргузкаМатериалов {
    // TODO: 28.02.2022
    private Activity activity;
    private FragmentManager fragmentManagerДляОтгрузкаМатреиалов;
    private FragmentTransaction fragmentTransactionляОтгрузкаМатриалов;
    private Fragment fragment_дляОтгрузкаМатериаловПерваяКнопка;
    private RecyclerView RecyclerViewИзФрагмента;
    // TODO: 18.07.2022
    protected   static    StringBuffer БуферРезультатПолучениеДанныхОт1СВторойЭтап=new StringBuffer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            /*   setContentView(R.layout.activity_main_fragment1_for_tasks);//R.layout.activity_main_history_chat  //TODO old R.layout.activity_main_history_tasks*/
            setContentView(R.layout.activity_main_fisrt_for_shipment_of_materials);//R.layout.activity_main_history_chat  //TODO old R.layout.activity_main_history_tasks
            // TODO: 27.04.2021 формируем внешний вид Чата через фрагменты
            activity = this;
            // TODO: 28.02.2022
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
          //  setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                    | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                    | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

// TODO: 03.03.2022  ЗАПУСКАЕМ БИЗНЕС ЛОГИКУ НА АКТИВТИ ДЛЯ ФРАГМЕНТОВ ДЛЯ ЗАДАНИЯ
            // TODO: 01.03.2022*/
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
    // TODO: 14.07.2022  после поворота экран пытаемся его востановить
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(this.getClass().getName(), "onRestoreInstanceState savedInstan" +savedInstanceState);
        try{
/*
            Bundle bundleПослеRestart=new Bundle();


            Integer IDВыбраногоЦФОДляЗагрузкиЛимитаМатерилов
                    =Optional.ofNullable(savedInstanceState.getInt("ПослеRestartIDВыбраногоЦФОДляЗагрузкиЛимитаМатерилов")).map(Integer::new).orElse(0) ;

        Log.d(this.getClass().getName(), "IDВыбраногоЦФОДляЗагрузкиЛимитаМатерилов"+IDВыбраногоЦФОДляЗагрузкиЛимитаМатерилов);

            if (IDВыбраногоЦФОДляЗагрузкиЛимитаМатерилов>0) {
                bundleПослеRestart.putInt("ПослеRestartIDВыбраногоЦФОДляЗагрузкиЛимитаМатерилов", IDВыбраногоЦФОДляЗагрузкиЛимитаМатерилов);


                // TODO: 14.07.2022 само название сфо
                String СамоНазваниЦФОПослеRestart = savedInstanceState.getString("ПослеRestartСамоНазваниЦФОПослеRestart");
                // TODO: 14.07.2022
                Log.d(this.getClass().getName(), "СамоНазваниЦФОПослеRestart" + СамоНазваниЦФОПослеRestart);
                // TODO: 14.07.2022
                bundleПослеRestart.putString("ПослеRestartСамоНазваниЦФОПослеRestart", СамоНазваниЦФОПослеRestart);

                Log.d(this.getClass().getName(), "БуферРезультатПолучениеДанныхОт1СВторойЭтап" + БуферРезультатПолучениеДанныхОт1СВторойЭтап);

                // TODO: 14.07.2022
                bundleПослеRestart.putSerializable("БуферРезультатПолучениеДанныхОт1СВторойЭтап", (Serializable) БуферРезультатПолучениеДанныхОт1СВторойЭтап);


            }
*/

// TODO: 18.07.2022  последная команда отправки на фрагмент
            fragment_дляОтгрузкаМатериаловПерваяКнопка.setArguments(savedInstanceState);
            

    } catch (Exception e) {
        e.printStackTrace();
        ///метод запись ошибок в таблицу
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }


    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(this.getClass().getName(), "onSaveInstanceState");
        try{
       new SubClass_GenetarorOldValuePovorot_ГенерацияДанныхДоПоворотаЭкрана().
               МетодЗапоеленияДаннымиПриВозвратеПослеПоворотаЭкрана(outState,fragment_дляОтгрузкаМатериаловПерваяКнопка);

        } catch (Exception e) {
        e.printStackTrace();
        ///метод запись ошибок в таблицу
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }

    protected void onRestart() {
        super.onRestart();
        Log.d(this.getClass().getName(), "onRestart");
    }







    @Override
    protected void onStart() {
        super.onStart();
try{
    МетодЗапускФрагментаОтгрузкаМатериалов();
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
// TODO: 10.03.2022

    private void МетодЗапускФрагментаОтгрузкаМатериалов() {

        try{
            Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1   ");

         fragmentManagerДляОтгрузкаМатреиалов = getSupportFragmentManager();
            ///
            fragmentTransactionляОтгрузкаМатриалов = fragmentManagerДляОтгрузкаМатреиалов.beginTransaction();

            // TODO: 22.12.2021  запускам втнутерий класс по созданию бизнес логики для даннго активти

            Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1  fragmentTransactionляСогласование  " + fragmentTransactionляОтгрузкаМатриалов);

            Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1 imageView   ");

            // TODO: 09.03.2022
            fragment_дляОтгрузкаМатериаловПерваяКнопка = new Fragment1_List_Shipment_of_Materials();
            ///
            fragmentTransactionляОтгрузкаМатриалов.replace(R.id.activity_main_fisrt_shipment_of_materials, fragment_дляОтгрузкаМатериаловПерваяКнопка).commit();//.layout.activity_for_fragemtb_history_tasks

            // TODO: 10.03.2022
            fragmentTransactionляОтгрузкаМатриалов.show(fragment_дляОтгрузкаМатериаловПерваяКнопка);

            Log.d(this.getClass().getName(), " fragmentTransactionляСогласование " + fragmentTransactionляОтгрузкаМатриалов);

            //TODO

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

    @Override
    public StringBuffer МеханизмПолучениеданныхИзФрагмента(@NotNull  StringBuffer stringBuffer) {
        Log.d(this.getClass().getName(), " stringBuffer " + stringBuffer);
        if (stringBuffer!=null){
            if (stringBuffer.length()>0) {
                БуферРезультатПолучениеДанныхОт1СВторойЭтап      =new StringBuffer();
                БуферРезультатПолучениеДанныхОт1СВторойЭтап.append(stringBuffer);
            }
        }
        return БуферРезультатПолучениеДанныхОт1СВторойЭтап;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        Log.d(this.getClass().getName(), " hasCapture " + hasCapture);
        super.onPointerCaptureChanged(hasCapture);
    }
}















