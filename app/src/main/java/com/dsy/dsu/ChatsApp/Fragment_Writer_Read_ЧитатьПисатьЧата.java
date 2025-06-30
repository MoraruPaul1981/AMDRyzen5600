package com.dsy.dsu.ChatsApp;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.dsy.dsu.BusinessLogicForApps.CoreBinessLogic.CoreBinessLogics;
import com.dsy.dsu.BusinessLogicForApps.GetPublicID.GetttingPublicID;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;

import com.dsy.dsu.BusinessLogicForApps.SubClassDiffentChats;

import com.dsy.dsu.BusinessLogicForApps.JbossAdress.JbossContext;
import com.dsy.dsu.BusinessLogicForApps.Services.ServiceForChatten;
import com.dsy.dsu.R;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textview.MaterialTextView;


import org.jetbrains.annotations.NotNull;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;

import javax.crypto.NoSuchPaddingException;


public class Fragment_Writer_Read_ЧитатьПисатьЧата extends Fragment {

    final  protected String ИмяСлужбыСинхронизацииОдноразовая = "WorkManager Synchronizasiy_Data Disposable";
    final protected String ИмяСлужбыWorkmanagerДляВсехЗадачОбщаяДопольнительная = "WorkManager Synchronizasiy_Data";
    protected View viewДляСообщенийЧата = null;
    protected TextView textViewФрагментЧитатьПисатьДляЧата;
    protected FloatingActionButton floatingActionButtonВФагментеReadandWrite;
    protected EditText editTextТелоНаписаногоСообщенияДругимСотрудникам;
    protected Activity ActivityДляСинхронизацииОбмена = null;
    protected String ПолученыйФИОIDДляЧата = new String();
    protected CoreBinessLogics modelДляФрагментаДляОперацииЗаписиНовгоСтатусаПрочитанного;
    protected JbossContext Class_Engine_SQLГдеНаходитьсяМенеджерПотоков = null;
    protected LinkedBlockingQueue<String> ЛистЗапускаемТолькоТаблицыЧатаВСинхронизации = new LinkedBlockingQueue();
    protected WorkInfo WorkInfoИнформацияОЗапущенойСлужбеОдноразовая;
    protected Integer ПубличныйIDДляФрагмента = 0;
    protected Cursor КурсорДанныеДлязаписиичтнияЧата = null;
    protected Long ПолученыйIDДляЧата = 0l;
    protected Long ПолученыйУжеСуществующийUUIDИзПерепискиДляЧата = 0l;

    protected Handler handlerФрагментЧитатьПисатьЧАТ;
    protected RecordNewErros recordNewErros;
    protected   ProgressBar progressBarДляЧатаЧитатьПисать;
    protected RecyclerView recyclerViewДляЧата;
    protected MyRecycleViewAdapterДляЧата myRecycleViewAdapterДляЧата;
    protected MyViewHolderДляЧата myViewHolderДляЧата;
    protected   ConstraintLayout constraintLayoutДляЧата;
    // TODO: 15.07.2022
    protected ServiceForChatten СсылкаНаСлужбуЧата;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            // TODO: 16.04.2025
            Log.d(getContext().getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        Log.d(this.getClass().getName(), " " + " viewДляСообщений" + viewДляСообщенийЧата);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        recordNewErros.recordnewerror(e.toString(),
                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }

    // TODO: 14.07.2022  начинаеться код ЧАТА
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        try {
            viewДляСообщенийЧата = inflater.inflate(R.layout.fragment3_layout, container, false);
            Log.d(this.getClass().getName(), " " + " viewДляСообщений" + viewДляСообщенийЧата);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            recordNewErros.recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return viewДляСообщенийЧата;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try{
            ActivityДляСинхронизацииОбмена = getActivity();
            constraintLayoutДляЧата = (ConstraintLayout) view.findViewById(R.id.Read_WriteЧатаmain_for_chats);
            // TODO: 28.02.2022
            recyclerViewДляЧата = (RecyclerView) view.findViewById(R.id.recycleview_for_chat_read_write);
            recyclerViewДляЧата.setVisibility(View.VISIBLE);
            floatingActionButtonВФагментеReadandWrite = (FloatingActionButton) viewДляСообщенийЧата.findViewById(R.id.floatingActionButtonВФагментеReadandWrite);
            textViewФрагментЧитатьПисатьДляЧата = (TextView) viewДляСообщенийЧата.findViewById(R.id.textViewФрагментЧитатьПисатьДляЧата);
            editTextТелоНаписаногоСообщенияДругимСотрудникам = (EditText) viewДляСообщенийЧата.findViewById(R.id.editTextТелоНаписаногоСообщенияДругимСотрудникам);
            floatingActionButtonВФагментеReadandWrite = (FloatingActionButton) viewДляСообщенийЧата.findViewById(R.id.floatingActionButtonВФагментеReadandWrite);
            textViewФрагментЧитатьПисатьДляЧата = (TextView) viewДляСообщенийЧата.findViewById(R.id.textViewФрагментЧитатьПисатьДляЧата);
            ЛистЗапускаемТолькоТаблицыЧатаВСинхронизации.add("chats");
            ЛистЗапускаемТолькоТаблицыЧатаВСинхронизации.add("data_chat");
            editTextТелоНаписаногоСообщенияДругимСотрудникам.setBackgroundResource(R.drawable.style_for_chat_for_fragmaent_read_wirte_new_messages);
            progressBarДляЧатаЧитатьПисать= (ProgressBar) viewДляСообщенийЧата.findViewById(R.id.prograessbaк_for_chatswrite);


            Log.d(this.getClass().getName(), " " + " recyclerViewДляЧата" + recyclerViewДляЧата);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            recordNewErros.recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }



    @SuppressLint("SuspiciousIndentation")
    @Override
    public void onStart() {
        super.onStart();
        try {
            recordNewErros =new RecordNewErros(getContext());

            Class_Engine_SQLГдеНаходитьсяМенеджерПотоков = new JbossContext(getContext());
            modelДляФрагментаДляОперацииЗаписиНовгоСтатусаПрочитанного = new CoreBinessLogics(getContext());



            ПолученыйIDДляЧата = getArguments().getLong("ПолученыйIDДляЧата", 0);
            ПолученыйФИОIDДляЧата = new String();
            ПолученыйФИОIDДляЧата = getArguments().getString("ПолученыйФИОIDДляЧата", "");
            ПубличныйIDДляФрагмента =   new GetttingPublicID().getttingPublicID(getContext());

            ПолученыйУжеСуществующийUUIDИзПерепискиДляЧата =
                    getArguments().getLong("ПолученыйUUIDУжеСуществующийПерепискиПользоватлейДляЧата", 0);


            // TODO: 15.07.2022 запускам методы 

           МетодПодключенияКСлужбеБиндингом();

            // TODO: 15.07.2022  ПОЛУЧАЕМ ДАННЫЕ ДЛЯ ЧАТА
           
        КурсорДанныеДлязаписиичтнияЧата=    СсылкаНаСлужбуЧата.МетодГенерацияКурсораДляЧата(ПолученыйУжеСуществующийUUIDИзПерепискиДляЧата,getContext());
        
            Log.d(this.getClass().getName(), " " + "    КурсорДанныеДлязаписиичтнияЧата" + КурсорДанныеДлязаписиичтнияЧата);
            
            МетодКруглаяКнопкаНаФрагментеЧитатьПисать();

            Log.d(this.getClass().getName(), " " + "    ПолученыйУжеСуществующийUUIDИзПерепискиДляЧата" + ПолученыйУжеСуществующийUUIDИзПерепискиДляЧата);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            recordNewErros.recordnewerror(e.toString(),
                    this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        try{

            // TODO: 14.07.2022  иниилизируем RecyclerView Для ЧАТА   ЗАПУСКАЕТЬСЯ ПОСЛДНИМ
            МетодИнициализацииrecyclerViewДляЧата();

            Log.d(this.getClass().getName(), " " + "     public void onStart() {" + recyclerViewДляЧата);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            recordNewErros.recordnewerror(e.toString(),
                    this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


    // TODO: 15.07.2022  код для самого ФРАГМЕНТА

    void МетодИнициализацииrecyclerViewДляЧата() {

        try{
            // TODO: 14.03.202
            LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            // TODO: 28.02.2022 создаем наш первый RecyclerView
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                    recyclerViewДляЧата.getContext(),
                    DividerItemDecoration.HORIZONTAL);
            recyclerViewДляЧата.removeItemDecoration(dividerItemDecoration);
            recyclerViewДляЧата.addItemDecoration(dividerItemDecoration);
            recyclerViewДляЧата.setLayoutManager(linearLayoutManager);//TODO new LinearLayoutManager(getContext()) // TODO: 28.02.2022 создаем наш первый RecyclerView recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            // TODO: 28.02.2022
            myRecycleViewAdapterДляЧата = new MyRecycleViewAdapterДляЧата(КурсорДанныеДлязаписиичтнияЧата);
            // TODO: 04.03.2022
            recyclerViewДляЧата.setAdapter(myRecycleViewAdapterДляЧата);
            // TODO: 04.03.2022
            recyclerViewДляЧата.getAdapter().notifyDataSetChanged();
            // TODO: 04.03.2022
            recyclerViewДляЧата.getAdapter().notifyItemChanged(0);
            recyclerViewДляЧата.requestLayout();
            recyclerViewДляЧата.forceLayout();
            constraintLayoutДляЧата.requestLayout();
            constraintLayoutДляЧата.forceLayout();

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            recordNewErros.recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }



    }



    //TODO метод делает callback с ответом на экран
    void  МетодИнициализациHandlerCallBack(){

        try{

            Handler.Callback callback = new Handler.Callback() {
                @Override
                public boolean handleMessage(@NonNull android.os.Message msg) {
                    Log.d(this.getClass().getName(), " " +
                            " SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1   ПозицияЭлментаVIewCardДополнительно  СтатусПрочтеаУжеЗадачаИлиНет " +
                            msg + " msg.getWhen() " + msg.what);
                    progressBarДляЧатаЧитатьПисать.setVisibility(View.VISIBLE);

                    if (КурсорДанныеДлязаписиичтнияЧата!=null) {
                        КурсорДанныеДлязаписиичтнияЧата.requery();
                    }
                    if (recyclerViewДляЧата!=null) {

                        recyclerViewДляЧата.getAdapter().notifyDataSetChanged();
                        // TODO: 04.03.2022
                        recyclerViewДляЧата.getAdapter().notifyItemChanged(0);
                        recyclerViewДляЧата.requestLayout();
                        recyclerViewДляЧата.forceLayout();
                    }
                    if (constraintLayoutДляЧата!=null) {
                        constraintLayoutДляЧата.requestLayout();
                        constraintLayoutДляЧата.forceLayout();
                    }

                    // TODO: 19.02.2022
                    Bundle bundle=      msg.getData();
                    String ОперацияДанныВЧате=bundle.getString("ОперациЯПрошлаЧат","");
                    msg.getTarget().postDelayed(()->{
                        editTextТелоНаписаногоСообщенияДругимСотрудникам.setHint("Напишите сообщение  ►►");
                        progressBarДляЧатаЧитатьПисать.setVisibility(View.INVISIBLE);
                    },1000);
                    editTextТелоНаписаногоСообщенияДругимСотрудникам.setHint(ОперацияДанныВЧате);


                    msg.getTarget().removeMessages(1);
                    return true;
                }
            };
            handlerФрагментЧитатьПисатьЧАТ = new Handler(callback);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            recordNewErros.recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }



    void МетодЗапускаСинхроницииОдноразовая() throws ExecutionException, InterruptedException {
        try {
            Observer    observerОдноразовая = new Observer<List<WorkInfo>>() {
                @Override
                public void onChanged(List<WorkInfo> workInfosОдноразовая) {
                    workInfosОдноразовая.stream()
                            .filter(СтастусWorkMangerДляФрагментаЧитатьИПисать ->
                                    СтастусWorkMangerДляФрагментаЧитатьИПисать.getState().compareTo(WorkInfo.State.SUCCEEDED) == 0)
                            .forEachOrdered((СтастусWorkMangerДляФрагментаЧитатьИПисать) -> {
                                try {

                                    Long  CallBaskОтWorkManagerОдноразового = СтастусWorkMangerДляФрагментаЧитатьИПисать
                                            .getOutputData().getLong("ОтветПослеВыполения_MyWork_Async_Синхронизация_Одноразовая", 0l);
                                    Message message = new Message();
                                    Bundle bundle=new Bundle();
                                    if (CallBaskОтWorkManagerОдноразового > 0) {
                                        bundle.putString("ОперациЯПрошлаЧат","Успешный обмен !!!");
                                        message.setData(bundle);
                                    }else
                                    {
                                        bundle.putString("ОперациЯПрошлаЧат","В процессе...");
                                        message.setData(bundle);
                                    }
// TODO: 29.06.2022
                                    handlerФрагментЧитатьПисатьЧАТ.sendMessage(message);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Log.e(this.getClass().getName(), "Ошибка  Фрагмент Читать и Писать   observerОдноразоваяДляWORKMANAGER = new Observer<List<WorkInfo>>() {" +
                                            " МетодЗапускаСинхрониазцииПоРАсписаниювНезависимостиОтВставкиНовгоСообщения  " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    recordNewErros.recordnewerror(e.toString(), this.getClass().getName(),
                                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                }
                            });
                }
            };
            WorkManager.getInstance(getContext()).getWorkInfosByTagLiveData(ИмяСлужбыСинхронизацииОдноразовая).removeObserver(observerОдноразовая);
            WorkManager.getInstance(getContext()).getWorkInfosByTagLiveData(ИмяСлужбыСинхронизацииОдноразовая).observeForever(observerОдноразовая);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            recordNewErros.recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    void МетодЗапускаСинхронизацииОбщая() throws ExecutionException, InterruptedException {
        try {
            Observer    observerОБЩАЯ = new Observer<List<WorkInfo>>() {
                @Override
                public void onChanged(List<WorkInfo> workInfosОБЩАЯ) {
                    workInfosОБЩАЯ.stream()
                            .filter(СтастусWorkMangerДляФрагментаЧитатьИПисать ->
                                    СтастусWorkMangerДляФрагментаЧитатьИПисать.getState().compareTo(WorkInfo.State.RUNNING) != 0)
                            .forEachOrdered((СтастусWorkMangerДляФрагментаЧитатьИПисать) -> {
                                try {
                                    Integer  CallBaskОтWorkManagerОБЩАЯ = СтастусWorkMangerДляФрагментаЧитатьИПисать
                                            .getOutputData().getInt("ReturnPublicAsyncWorkMananger",0);
                                    Message message = new Message();
                                    Bundle bundle = new Bundle();
                                    bundle.putString("ОперациЯПрошлаЧат", "В процессе...");
                                    message.setData(bundle);
                                    handlerФрагментЧитатьПисатьЧАТ.sendMessage(message);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Log.e(this.getClass().getName(), "Ошибка  Фрагмент Читать и Писать   observerОдноразоваяДляWORKMANAGER = new Observer<List<WorkInfo>>() {" +
                                            " МетодЗапускаСинхрониазцииПоРАсписаниювНезависимостиОтВставкиНовгоСообщения  " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    recordNewErros.recordnewerror(e.toString(), this.getClass().getName(),
                                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                }
                            });
                }
            };
            WorkManager.getInstance(getContext()).getWorkInfosByTagLiveData(ИмяСлужбыWorkmanagerДляВсехЗадачОбщаяДопольнительная).removeObserver(observerОБЩАЯ);
            WorkManager.getInstance(getContext()).getWorkInfosByTagLiveData(ИмяСлужбыWorkmanagerДляВсехЗадачОбщаяДопольнительная).observeForever(observerОБЩАЯ);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            recordNewErros.recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


    void   МетодПодключенияКСлужбеБиндингом(){
        try {
            // TODO: 16.06.2022  тест код
            СсылкаНаСлужбуЧата=new ServiceForChatten();

            Intent intentЗапускДжоп = new Intent(getContext(), ServiceForChatten.class);
            //  ServiceForChatten.enqueueWork(getContext(), intentЗапускДжоп);
            getContext(). bindService(intentЗапускДжоп, connectionДляЧата, Context.BIND_AUTO_CREATE);
            // int num = service_smenaStatusMessageChat.getRandomNumber();

            Log.i( getContext().getClass().getName(),
                    "    protected void onHandleWork(@NonNull Intent intent) { "
                            + new Date()+ " intentЗапускДжоп " +intentЗапускДжоп+"\n"+
                            " Thread.currentThread().getName()  "
                            +Thread.currentThread().getName()+ " service_smenaStatusMessageChat " +СсылкаНаСлужбуЧата);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            recordNewErros.recordnewerror(e.toString(),
                    getContext().getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }




    private ServiceConnection connectionДляЧата = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            try{
                // We've bound to LocalService, cast the IBinder and get LocalService instance
                ServiceForChatten.LocalBinderДляЧата binder = (ServiceForChatten.LocalBinderДляЧата) service;
                СсылкаНаСлужбуЧата = binder.getService();
                Log.i(getContext().getClass().getName(), "    onServiceConnected  СсылкаНаСлужбуЧата" +СсылкаНаСлужбуЧата);

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                recordNewErros.recordnewerror(e.toString(),
                        this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            try{
                Log.i(getContext().getClass().getName(), "    onServiceDisconnected  СсылкаНаСлужбуЧата" +СсылкаНаСлужбуЧата);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                recordNewErros.recordnewerror(e.toString(),
                        getContext().getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
    };

// TODO: 15.07.2022 инициализация Круглой Зеленой Кнопки Создание Нового сообщение или запуск холостой синхронизации
    void МетодКруглаяКнопкаНаФрагментеЧитатьПисать() {

        try {
            floatingActionButtonВФагментеReadandWrite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Log.w(getContext().getClass().getName(), "  запускам метод РЕАКЦИЯ на нажаите кнопки  МетодКруглаяКнопкаНаФрагментеЧитатьПисать() ");

                    Vibrator v2 = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
                    v2.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.EFFECT_HEAVY_CLICK));
                    try {
                        floatingActionButtonВФагментеReadandWrite.setImageResource(R.drawable.icon_dsu1_chat_messgae_sync_up);

                        if (editTextТелоНаписаногоСообщенияДругимСотрудникам.getText().toString() != null &&
                                editTextТелоНаписаногоСообщенияДругимСотрудникам.getText().toString().length() > 0) {

                            progressBarДляЧатаЧитатьПисать.setVisibility(View.VISIBLE);

                            МетодВизуальногОформелнияКнопкиПриНАжатии(v);


                            // TODO: 01.08.2022
                            if (!WorkManager.getInstance(getContext()).getWorkInfosByTag(ИмяСлужбыСинхронизацииОдноразовая).get().isEmpty()) {
                                WorkInfoИнформацияОЗапущенойСлужбеОдноразовая =
                                        WorkManager.getInstance(getContext().getApplicationContext()).getWorkInfosByTag(ИмяСлужбыСинхронизацииОдноразовая).get().get(0);
                                if (WorkInfoИнформацияОЗапущенойСлужбеОдноразовая.getState().compareTo(WorkInfo.State.RUNNING) == 0) {

                                    WorkInfoИнформацияОЗапущенойСлужбеОдноразовая =
                                            WorkManager.getInstance(getContext().getApplicationContext()).getWorkInfosByTag(ИмяСлужбыСинхронизацииОдноразовая).get().get(0);
                                    progressBarДляЧатаЧитатьПисать.setVisibility(View.VISIBLE);
                                }
                            }

                            // TODO: 15.07.2022  Само Создание Нового Сообщения
                            Integer      РезультатЗапускаСозданииНовгоСообщения = МетодСозданииНовогоСообщениявЧате();
                            if (РезультатЗапускаСозданииНовгоСообщения > 0) {

                                editTextТелоНаписаногоСообщенияДругимСотрудникам.setText("");
                                Message message = new Message();
                                Bundle bundle=new Bundle();
                                bundle.putString("ОперациЯПрошлаЧат","Создано новое сообщения !!!");
                                message.setData(bundle);
                                handlerФрагментЧитатьПисатьЧАТ.sendMessage(message);
                                // TODO: 01.08.2022
                                if (!WorkManager.getInstance(getContext()).getWorkInfosByTag(ИмяСлужбыСинхронизацииОдноразовая).get().isEmpty()) {
                                    WorkInfoИнформацияОЗапущенойСлужбеОдноразовая =
                                            WorkManager.getInstance(getContext().getApplicationContext()).getWorkInfosByTag(ИмяСлужбыСинхронизацииОдноразовая).get().get(0);
                                    if (WorkInfoИнформацияОЗапущенойСлужбеОдноразовая.getState().compareTo(WorkInfo.State.RUNNING) != 0) {


                                        // TODO: 26.06.2022
                                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                                + " ПубличныйIDДляФрагмента "+ПубличныйIDДляФрагмента );

                                        progressBarДляЧатаЧитатьПисать.setVisibility(View.VISIBLE);
                                    }
                                }
                            } else {
                                Toast.makeText(getContext(), "Новое сообщение не создалось, попробуйте еще раз !!!", Toast.LENGTH_SHORT).show();
                            }




                            // TODO: 30.06.2022 ПУСТАЯ СИХРОНИЗАЦИЯ БЕЗ ДАННЫХ !!!!!! ЧАТ СТРОЧКА СООБЩЕНИЯ ПУСТА
                        } else {

                            progressBarДляЧатаЧитатьПисать.setVisibility(View.VISIBLE);
                            МетодВизуальногОформелнияКнопкиПриНАжатии(v);
                            // TODO: 01.08.2022
                            if (!WorkManager.getInstance(getContext()).getWorkInfosByTag(ИмяСлужбыСинхронизацииОдноразовая).get().isEmpty()) {
                                WorkInfoИнформацияОЗапущенойСлужбеОдноразовая =
                                        WorkManager.getInstance(getContext().getApplicationContext()).getWorkInfosByTag(ИмяСлужбыСинхронизацииОдноразовая).get().get(0);
                                if (WorkInfoИнформацияОЗапущенойСлужбеОдноразовая.getState().compareTo(WorkInfo.State.RUNNING) != 0) {


                                    // TODO: 26.06.2022
                                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                            + " ПубличныйIDДляФрагмента "+ПубличныйIDДляФрагмента );
                                    progressBarДляЧатаЧитатьПисать.setVisibility(View.VISIBLE);
                                }
                            }
                            //new VIEW(getContext()).МетодДляЧатаПоказываемВизуальноОтправкуИИлиПолучениеДанных("Выполянется...", 4000);
                            Message message = new Message();
                            Bundle bundle=new Bundle();
                            bundle.putString("ОперациЯПрошлаЧат","В процессе...");
                            message.setData(bundle);
                            handlerФрагментЧитатьПисатьЧАТ.sendMessage(message);

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        recordNewErros.recordnewerror(e.toString(),
                                this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            recordNewErros.recordnewerror(e.toString(),
                    this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
    protected Integer МетодСозданииНовогоСообщениявЧате()   {
        Integer РезультатВставки_ОбоихОперацийТОлькоДляДочернейТаблицыИлиДЛяОбеихИДочернейИРолдительской = 0;
        try {
            if (ПолученыйУжеСуществующийUUIDИзПерепискиДляЧата == 0) {

                ПолученыйУжеСуществующийUUIDИзПерепискиДляЧата
                        = new SubClassDiffentChats()
                        .МетодПовторноПроверетНеПовилосьЛиМеждеУчаникамиперепискиПубличныйUUID(getContext(),
                                ПолученыйIDДляЧата,
                                ПубличныйIDДляФрагмента);
            }


        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            recordNewErros.recordnewerror(e.toString(),
                    this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return РезультатВставки_ОбоихОперацийТОлькоДляДочернейТаблицыИлиДЛяОбеихИДочернейИРолдительской;
    }

    // TODO: 15.07.2022  визуальное оформление при нажатии на кнопку

    private void МетодВизуальногОформелнияКнопкиПриНАжатии( View viewДляСообщенийЧата) {

        try {
            InputMethodManager imm = (InputMethodManager) viewДляСообщенийЧата.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(viewДляСообщенийЧата.getWindowToken(), 0);
            floatingActionButtonВФагментеReadandWrite.postDelayed(new Runnable() {
                @Override
                public void run() {
                    floatingActionButtonВФагментеReadandWrite.setImageResource(R.drawable.icon_dsu1_singlescroll_forfart);
                    floatingActionButtonВФагментеReadandWrite.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            floatingActionButtonВФагментеReadandWrite.setImageResource(R.drawable.icon_dsu1_chat_messgae_sync_up);
                        }
                    }, 2500);
                }
            }, 1000);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            recordNewErros.recordnewerror(e.toString(),
                    this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    // TODO: 15.07.2022 конец пользовательского кода


    // TODO: 15.07.2022--13.50  НАЧАЛО САМОГО ГЛАВНОГО КЛАССА RecyclerView.ViewHolder   // TODO: 15.07.2022--13.50  НАЧАЛО САМОГО ГЛАВНОГО КЛАССА RecyclerView.ViewHolder
    // TODO: 15.07.2022--13.50  НАЧАЛО САМОГО ГЛАВНОГО КЛАССА RecyclerView.ViewHolder
    // TODO: 15.07.2022--13.50  НАЧАЛО САМОГО ГЛАВНОГО КЛАССА RecyclerView.ViewHolder
    private   class MyViewHolderДляЧата extends RecyclerView.ViewHolder {// TODO: 28.02.2022 начало  MyViewHolderДляЧата

        // TODO: 28.02.2022 ТОЛЬКО LINE
        MaterialTextView textView1ТипМатериаловЛиния, textView2ЛимитЛиния, textView3НоменклатураЛиния, textView4ЗакупленоРазрешенияЛиния, textView5ПривезеноРазрешенияЛиния, textView6ВесоваяЛиния;
        // TODO: 28.02.2022 ТОЛЬКО KEY
        MaterialTextView textView1ТипМатериаловКлюч, textView2ЛимитКлюч, textView3НоменклатураКлюч, textView4ЗакупленоРазрешенияКлюч, textView5ПривезеноРазрешенияКлюч, textView6ВесоваяКлюч, textView7ОстатокКлюч;
        // TODO: 28.02.2022 ТОЛЬКО VALUE
        MaterialTextView textView1ТипМатериалов, textView2Лимит, textView3Номенклатура, textView4ЗакупленоРазрешения, textView5ПривезеноРазрешения, textView6Весовая, textView7Остаток;
        // TODO: 13.03.2022
        MaterialCardView materialCardView_Shipment_of_materials;
        // TODO: 05.07.2022
        LinearLayout LinearLayout_Shipment_of_materials;

        // TODO: 02.03.2022
        MyViewHolderДляЧата(@NonNull View itemView) {
            super(itemView);
            // TODO: 02.03.2022
            МетодИнициализацииКомпонетовЗаданияCardView(itemView);
            // TODO: 01.03.2022
            Log.d(this.getClass().getName(), "  private class MyViewHolderДляЧата extends RecyclerView.ViewHolder  itemView   " + itemView);
        }

        // TODO: 14.03.2022
        private void МетодИнициализацииКомпонетовЗаданияCardView(@NonNull View itemView) {
            // TODO: 28.02.2022
            try {
                // TODO: 01.03.2022 Инициализации компонтов
                Log.d(this.getClass().getName(),
                        " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1 itemView   " + itemView);

                // TODO: 02.03.2022  ТОЛЬКО САМИ ЛИНИЯ ВНУТРИ CARDVIEW
                textView1ТипМатериаловЛиния= (MaterialTextView) itemView.findViewById(R.id.bertlinekey01);
                textView2ЛимитЛиния= (MaterialTextView) itemView.findViewById(R.id.bertlinekey02);
                textView3НоменклатураЛиния= (MaterialTextView) itemView.findViewById(R.id.bertlinekey03);
                textView4ЗакупленоРазрешенияЛиния= (MaterialTextView) itemView.findViewById(R.id.bertlinekey04);
                textView5ПривезеноРазрешенияЛиния= (MaterialTextView) itemView.findViewById(R.id.bertlinekey05);
                textView6ВесоваяЛиния= (MaterialTextView) itemView.findViewById(R.id.bertlinekey06);



                // TODO: 02.03.2022  ТОЛЬКО САМИ КЛЮЧИ ВНУТРИ CARDVIEW
                textView1ТипМатериаловКлюч= (MaterialTextView) itemView.findViewById(R.id.key1);
                textView2ЛимитКлюч= (MaterialTextView) itemView.findViewById(R.id.key2);
                textView3НоменклатураКлюч= (MaterialTextView) itemView.findViewById(R.id.key3);
                textView4ЗакупленоРазрешенияКлюч= (MaterialTextView) itemView.findViewById(R.id.key4);
                textView5ПривезеноРазрешенияКлюч= (MaterialTextView) itemView.findViewById(R.id.key5);
                textView6ВесоваяКлюч= (MaterialTextView) itemView.findViewById(R.id.key6);
                textView7ОстатокКлюч= (MaterialTextView) itemView.findViewById(R.id.key7);


                // TODO: 02.03.2022  ТОЛЬКО САМИ ДАННЫЕ ВНУТРИ CARDVIEW
                textView1ТипМатериалов = (MaterialTextView) itemView.findViewById(R.id.value1);
                textView2Лимит = (MaterialTextView) itemView.findViewById(R.id.value2);
                textView3Номенклатура = (MaterialTextView) itemView.findViewById(R.id.value3);
                textView4ЗакупленоРазрешения = (MaterialTextView) itemView.findViewById(R.id.value4);
                textView5ПривезеноРазрешения = (MaterialTextView) itemView.findViewById(R.id.value5);
                textView6Весовая = (MaterialTextView) itemView.findViewById(R.id.value6);
                textView7Остаток= (MaterialTextView) itemView.findViewById(R.id.value7);
                materialCardView_Shipment_of_materials = (MaterialCardView) itemView.findViewById(R.id.cardvie_shipment_of_materials);
                LinearLayout_Shipment_of_materials    = (LinearLayout) itemView.findViewById(R.id.simple_shipment_of_materials);
                Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1 materialCardView   " + materialCardView_Shipment_of_materials);
                ///////
            } catch (Exception e) {
                //  Block of code to handle errors
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                // TODO: 01.09.2021 метод вызова
                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            /////
        }
    } // TODO: 28.02.2022 конец  MyViewHolderДляЧата


// TODO: 08.07.2022 МЕТОД ВТОРОЙ ОБОБЩЕННЫЙ ДЛЯ УЖЕ ПОЛУЧЕНИЕ САМОГО JSON  ОТ 1С

    class MyRecycleViewAdapterДляЧата extends RecyclerView.Adapter<MyViewHolderДляЧата> {
        // TODO: 04.03.2022
     private     Cursor ГлавныйКурсорЧатаСообщения;
        // TODO: 15.03.2022
        public MyRecycleViewAdapterДляЧата(@NotNull Cursor ГлавныйКурсорЧатаСообщения) {
            // TODO: 04.03.2022
            this.ГлавныйКурсорЧатаСообщения = ГлавныйКурсорЧатаСообщения;
            // TODO: 29.03.2022

        }


        @Override
        public void onBindViewHolder(@NonNull MyViewHolderДляЧата holder, int position, @NonNull List<Object> payloads) {
            // TODO: 30.03.2022
            Log.i(this.getClass().getName(), "   onBindViewHolder  position" + position);
            // TODO: 02.03.2022 тут РАЗДАЕМ ДАННЫЕ RECYCLERBIEW
            try {
                //    recyclerViewДляЧата.getRecycledViewPool().clear();
                // TODO: 30.03.2022
                if (ГлавныйКурсорЧатаСообщения.getCount()>0) {
                    // TODO: 30.03.2022
                    ГлавныйКурсорЧатаСообщения.moveToFirst();
                    // TODO: 30.03.2022
                    ГлавныйКурсорЧатаСообщения.moveToPosition(position);
                    Log.d(this.getClass().getName(), " ГлавныйКурсорЧатаСообщения   " + ГлавныйКурсорЧатаСообщения + " position " +position);
                }
                // TODO: 30.03.2022
                recyclerViewДляЧата.requestLayout();
                // TODO: 30.03.2022
                recyclerViewДляЧата.forceLayout();
            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
            }
            super.onBindViewHolder(holder, position, payloads);
        }

        @Override
        public void setHasStableIds(boolean hasStableIds) {
            super.setHasStableIds(hasStableIds);
        }

        @Override
        public void onViewRecycled(@NonNull MyViewHolderДляЧата holder) {
            super.onViewRecycled(holder);
        }

        @Override
        public boolean onFailedToRecycleView(@NonNull MyViewHolderДляЧата holder) {
            return super.onFailedToRecycleView(holder);
        }

        @Override
        public void onViewAttachedToWindow(@NonNull MyViewHolderДляЧата holder) {
            super.onViewAttachedToWindow(holder);
        }

        @Override
        public void onViewDetachedFromWindow(@NonNull MyViewHolderДляЧата holder) {
            super.onViewDetachedFromWindow(holder);
        }

        @Override
        public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {

            recyclerView.removeAllViews();

            recyclerView.getRecycledViewPool().clear();
            super.onAttachedToRecyclerView(recyclerView);
        }

        @Override
        public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
            super.onDetachedFromRecyclerView(recyclerView);
        }

        @Override
        public int getItemViewType(int position) {
            Log.i(this.getClass().getName(), "      holder.textView1  position " + position);
            try {
                // TODO: 30.03.2022
                Log.i(this.getClass().getName(), "   getItemViewType  position" + position);
                // TODO: 30.03.2022

            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
            }
            return super.getItemViewType(position);
        }


        @NonNull
        @Override
        public MyViewHolderДляЧата onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // TODO: 10.03.2022
            View viewГлавныйВидДляRecyclleViewДляЧАТА = null;
            try {
                if (ГлавныйКурсорЧатаСообщения.getCount()>0) {
                    // TODO: 28.02.2022
                    viewГлавныйВидДляRecyclleViewДляЧАТА =
                            LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_for_chats_cardview41, parent, false);//todo old simple_for_takst_cardview1
                    // TODO: 05.03.2022
                    Log.i(this.getClass().getName(), "   viewГлавныйВидДляRecyclleViewДляЧАТА" + viewГлавныйВидДляRecyclleViewДляЧАТА +
                            " ГлавныйКурсорЧатаСообщения.getCount() " + ГлавныйКурсорЧатаСообщения.getCount());

                    // TODO: 15.07.2022  когда данных нет

                }else{
                    // TODO: 08.07.2022 первый этап уже показываем только список с ЦФО
                    viewГлавныйВидДляRecyclleViewДляЧАТА =
                            LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_for_chats_cardview5_empty, parent, false);//todo old simple_for_takst_cardview1
                    // viewГлавныйВидДляRecyclleViewДляОтгрузкиМатерилов = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_for_shipment_of_materials_cardview1, parent, false);//todo old simple_for_takst_cardview1
                    // TODO: 05.03.2022
                    Log.i(this.getClass().getName(), "   viewГлавныйВидДляRecyclleViewДляЧАТА" + viewГлавныйВидДляRecyclleViewДляЧАТА+
                            "  ГлавныйКурсорЧатаСообщения.getCount() " + ГлавныйКурсорЧатаСообщения.getCount());

                }
                // TODO: 22.03.2022
                myViewHolderДляЧата = new MyViewHolderДляЧата(viewГлавныйВидДляRecyclleViewДляЧАТА);
                // TODO: 01.03.2022
                Log.i(this.getClass().getName(), "   myViewHolderДляЧата" + myViewHolderДляЧата+ "  viewГлавныйВидДляRecyclleViewДляЧАТА " +viewГлавныйВидДляRecyclleViewДляЧАТА);
// TODO: 01.03.2022
            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
            }
            return myViewHolderДляЧата;

        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolderДляЧата holder, int position) {
            // TODO: 28.02.2022 привазяваем данные из колекции пряме на наш recycreview
            try {
                // TODO: 01.03.2022
                Log.i(this.getClass().getName(), "   создание согласования"
                        + myViewHolderДляЧата
                        + " КурсорДанныеДлязаписиичтнияЧата " + КурсорДанныеДлязаписиичтнияЧата);
                // TODO: 14.03.2022  метод первый номер документ #1
                МетодБиндингаТипМатериала(holder);
                // TODO:14.03.2022  метод первый номер документ #2
                МетодБиндингаПривезеноРазрешения(holder);
                // TODO: 14.03.2022  метод первый номер документ #4
                МетодБиндингаЗакупленоРазрешения(holder);
                // TODO: 14.03.2022  метод первый номер документ #5
                МетодБиндингаСозданиеЛимита(holder);
                // TODO: 14.03.2022  метод создание номелклутры #3
                МетодБиндингаСозданиеНоменклатура(holder);
                // TODO: 09.03.2022 код для клика по Spinner  ЛИМИТ материалоов
                // TODO: 14.03.2022  метод первый номер документ #7
                МетодБиндингаСозданиеНомелклатура(holder);
                // TODO: 14.03.2022  метод первый номер документ #3
                МетодБиндингаСозданиеОстаток(holder);
                // TODO: 07.07.2022 код скрывем внешний вид ключ внутри Cardview
                МетодБиндингаСозданиеВыключаетКлючиCardView(holder);

            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
            }
        }











        ///todo первый метод #1

        private void МетодБиндингаТипМатериала(@NonNull MyViewHolderДляЧата holder) {
            try {
                // TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1
                if (КурсорДанныеДлязаписиичтнияЧата!=null && holder.textView1ТипМатериалов!=null ) {
                    //TODO
                    String ТипМатериала = КурсорДанныеДлязаписиичтнияЧата.getString(0).trim();
                    // TODO: 02.03.2022
                    Log.i(this.getClass().getName(), "  Ndoc  ПерваяСтрочкаЗнТипМатериалаачения " + ТипМатериала);
                    // TODO: 28.02.2022
                    holder.textView1ТипМатериалов.setText(ТипМатериала);
                }
                // TODO: 28.02.2022*/
            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
            }

        }

        ///todo первый метод #2

        private void МетодБиндингаСозданиеЛимита(@NonNull MyViewHolderДляЧата holder) {
            try {
                // TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1
                if (КурсорДанныеДлязаписиичтнияЧата!=null &&  holder.  textView2Лимит!=null ) {
                    Integer Лимит = КурсорДанныеДлязаписиичтнияЧата.getInt(0);
                    // TODO: 02.03.2022
                    Log.i(this.getClass().getName(), "  amount Лимит " + Лимит);
                    // TODO: 28.02.2022
                    holder.  textView2Лимит.setText(String.valueOf(Лимит));
                }
                // TODO: 28.02.2022*/
            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
            }

        }

        ///todo первый метод #3

        private void МетодБиндингаСозданиеНоменклатура(@NonNull MyViewHolderДляЧата holder) {
            try {
                // TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1
                if (КурсорДанныеДлязаписиичтнияЧата!=null &&  holder.    textView3Номенклатура!=null ) {
                    String Номенклатура = КурсорДанныеДлязаписиичтнияЧата.getString(0);
                    // TODO: 02.03.2022
                    Log.i(this.getClass().getName(), "  nomenclatura Номенклатура " + Номенклатура);
                    // TODO: 28.02.2022
                    holder.    textView3Номенклатура.setText(Номенклатура);
                }
                // TODO: 28.02.2022*/
            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
            }

        }


        ///todo первый метод #4

        private void МетодБиндингаЗакупленоРазрешения(@NonNull MyViewHolderДляЧата holder) {
            try {
                // TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1
                if (КурсорДанныеДлязаписиичтнияЧата!=null &&  holder.textView4ЗакупленоРазрешения!=null ) {
                    //TODO
                    Integer ЗакупленоеРазрешения = КурсорДанныеДлязаписиичтнияЧата.getInt(0);
                    Log.i(this.getClass().getName(), " ЗакупленоеРазрешения" + ЗакупленоеРазрешения);
                    // TODO: 28.02.2022
                    holder.textView4ЗакупленоРазрешения.setText(String.valueOf(ЗакупленоеРазрешения));
                }
                // TODO: 28.02.2022*/
            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
            }

        }




        ///todo первый метод #4

        private void МетодБиндингаПривезеноРазрешения(@NonNull MyViewHolderДляЧата holder) {
            try {
                // TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1
                if (holder. textView5ПривезеноРазрешения!=null && КурсорДанныеДлязаписиичтнияЧата!=null ) {
                    //todo
                    Integer ПривезеноРазрешения = КурсорДанныеДлязаписиичтнияЧата.getInt(0);
                    Log.i(this.getClass().getName(), " ПривезеноРазрешения " + ПривезеноРазрешения);
                    // TODO: 28.02.2022
                    holder. textView5ПривезеноРазрешения.setText(String.valueOf(ПривезеноРазрешения));
                }
                // TODO: 28.02.2022*/
            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
            }

        }


        ///todo первый метод #7

        private void МетодБиндингаСозданиеНомелклатура(@NonNull MyViewHolderДляЧата holder) {
            try {
                // TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1
                if (КурсорДанныеДлязаписиичтнияЧата!=null && holder.    textView6Весовая !=null ) {
                    Double ПерваяСтрочкаЗначения = КурсорДанныеДлязаписиичтнияЧата.getDouble(0);
                    Log.i(this.getClass().getName(), "  nomenclature ПерваяСтрочкаЗначения " + ПерваяСтрочкаЗначения);
                    // TODO: 28.02.2022
                    holder.    textView6Весовая .setText(String.valueOf(ПерваяСтрочкаЗначения));
                }
                // TODO: 28.02.2022*/
            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
            }

        }
        // TODO: 14.03.2022  Успешное Согласования





        private void МетодБиндингаСозданиеОстаток(@NonNull MyViewHolderДляЧата holder) {
            try {
                // TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1
                if (КурсорДанныеДлязаписиичтнияЧата!=null &&   holder. textView7Остаток!=null ) {
                    //TODO
                    Double Остаток = КурсорДанныеДлязаписиичтнияЧата.getDouble(0);
                    Log.i(this.getClass().getName(), " Остаток " + Остаток);
                    // TODO: 28.02.2022
                    holder. textView7Остаток.setText(String.valueOf(Остаток));
                }
                // TODO: 28.02.2022*/
            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
            }

        }

        ///todo первый метод #5
        private void МетодБиндингаСозданиеВыключаетКлючиCardView(@NonNull MyViewHolderДляЧата holder) {
            try {
                // TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1// TODO: 02.03.2022#1
                if (КурсорДанныеДлязаписиичтнияЧата!=null  ) {
                    //TODO
                    Log.i(this.getClass().getName(), " Скрываем Внешний вид ключей в CardView  holder.getAdapterPosition() "  +holder.getAdapterPosition()+"\n"+
                            " holder.getLayoutPosition() " +holder.getLayoutPosition() );
                    LinearLayout.LayoutParams layoutParams=null;
                    if (holder. materialCardView_Shipment_of_materials!=null) {
                        // TODO: 13.07.2022
                        layoutParams = (LinearLayout.LayoutParams) holder.materialCardView_Shipment_of_materials.getLayoutParams();
                    }

                    if (holder.getAdapterPosition()>0){
                        // TODO: 13.07.2022 hide key
                        holder.textView1ТипМатериаловКлюч.setVisibility(View.GONE);
                        holder. textView2ЛимитКлюч.setVisibility(View.GONE);
                        holder.textView3НоменклатураКлюч.setVisibility(View.GONE);
                        holder.    textView4ЗакупленоРазрешенияКлюч.setVisibility(View.GONE);
                        holder. textView5ПривезеноРазрешенияКлюч.setVisibility(View.GONE);
                        holder. textView6ВесоваяКлюч.setVisibility(View.GONE);
                        holder.  textView7ОстатокКлюч.setVisibility(View.GONE);
                        // TODO: 13.07.2022 hide line
                        holder. textView1ТипМатериаловЛиния.setVisibility(View.GONE);
                        holder. textView2ЛимитЛиния.setVisibility(View.GONE);
                        holder. textView3НоменклатураЛиния.setVisibility(View.GONE);
                        holder. textView4ЗакупленоРазрешенияЛиния.setVisibility(View.GONE);
                        holder.textView5ПривезеноРазрешенияЛиния.setVisibility(View.GONE);
                        holder. textView6ВесоваяЛиния.setVisibility(View.GONE);
                        // TODO: 13.07.2022 hide matiealcardview
                               /*    ViewGroup.LayoutParams layoutParams =(ViewGroup.LayoutParams)  holder. materialCardView_Shipment_of_materials.getLayoutParams();
                                    layoutParams.height = 220;*/
                        layoutParams.topMargin=20;
                        layoutParams.height = 150;
                    }
                    if (holder. materialCardView_Shipment_of_materials!=null) {
                        holder. materialCardView_Shipment_of_materials.setLayoutParams(layoutParams);
                    }
                }
                // TODO: 28.02.2022*/
            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
            }

        }

        // TODO: 29.03.2022  слутаеть смены статуса

        @Override
        public long getItemId(int position) {
            // TODO: 04.03.2022
            Log.i(this.getClass().getName(), "     getItemId holder.position " + position);
            return super.getItemId(position);

        }

        @Override
        public int getItemCount() {
            // TODO: 02.03.2022
            int КоличесвоСтрок = 0;
            try{
                if (ГлавныйКурсорЧатаСообщения.getCount()>0) {
                    КоличесвоСтрок = ГлавныйКурсорЧатаСообщения.getCount();
                    Log.d(this.getClass().getName(), "ARRAYJSONПолучениеДанныхОТ1СВторойШагRecycleViewAdapter " + ГлавныйКурсорЧатаСообщения
                            + " КоличесвоСтрок " +КоличесвоСтрок);
                } else {
                    КоличесвоСтрок=1;
                    Log.d(this.getClass().getName(), "ARRAYJSONПолучениеДанныхОТ1СВторойШагRecycleViewAdapter " + ГлавныйКурсорЧатаСообщения
                            + " холостой ход КоличесвоСтрок " +КоличесвоСтрок);
                }

                // TODO: 28.02.2022*/
            } catch (Exception e) {
                e.printStackTrace();
                ///метод запись ошибок в таблицу
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                //   mNotificationManagerДляЧАТА.cancel(1);///.cancelAll();
            }
            // TODO: 28.02.2022
            return КоличесвоСтрок ;
        }


        // TODO: 15.07.2022  МНЕНА СТАТУСА ТЕКУЩЕГО СООБЩЕНИЯ НА ПРОЧИТАНОЕ
        Boolean МетодКоторыйМеняетСтатусНаПрочитанныйТекущуюЗапись( @NonNull TextView view
                ,@NonNull Long ЗамоЗначениеТекущегоСообщения)
                throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
            //TODO
            Boolean ПолученноеСтатусПрочитанноСообщениеТекущееИлиНетНольСтоитИЛиЦифраОДИН = false;
            try {

                Integer РезультатВставкиНовогоФлагаЧтоТекущееСообщенеиОтДруговоПользоватлеяБылоПрочитанно = 0;
                final String ТекущаяТаблицыОбработкиПриИзмененияСтатусаПрочнетие = "data_chat";


                // TODO: 15.07.2022 действие первое получаени статус сообщение прочитано оно или нет

  /*              ПолученноеСтатусПрочитанноСообщениеТекущееИлиНетНольСтоитИЛиЦифраОДИН =
                        МетодПолучаемНаТекущуюЗаписьПрочитанноеСообщениеИлиНЕТ(ЗамоЗначениеТекущегоСообщения);*/

                Log.d(this.getClass().getName(), "  ПолученноеСтатусПрочитанноСообщениеТекущееИлиНетНольСтоитИЛиЦифраОДИН "
                        + ПолученноеСтатусПрочитанноСообщениеТекущееИлиНетНольСтоитИЛиЦифраОДИН);


                if (ПолученноеСтатусПрочитанноСообщениеТекущееИлиНетНольСтоитИЛиЦифраОДИН == true) {
                    Intent intentСменаСтатусаСообщениеТекущего = new Intent();
                    Bundle bundleДляБиндингаСлудбыСменыСтатуса = new Bundle();
                    bundleДляБиндингаСлудбыСменыСтатуса.putString("ЗамоЗначениеТекущегоСообщения", "uuid");
                    bundleДляБиндингаСлудбыСменыСтатуса.putLong("РезультатВставкиНовогоФлагаЧтоТекущееСообщенеиОтДруговоПользоватлеяБылоПрочитанно",
                            ЗамоЗначениеТекущегоСообщения);
                    intentСменаСтатусаСообщениеТекущего.putExtras(bundleДляБиндингаСлудбыСменыСтатуса);
                    РезультатВставкиНовогоФлагаЧтоТекущееСообщенеиОтДруговоПользоватлеяБылоПрочитанно = СсылкаНаСлужбуЧата
                            .МетодВнутриБиндингСлужбыСменаСтатусаВЧатеПрочитаноИлиНет(intentСменаСтатусаСообщениеТекущего, getContext());
                    Log.d(this.getClass().getName(), "  СсылкаНаСлужбуЧата"
                            + СсылкаНаСлужбуЧата +
                            " РезультатВставкиНовогоФлагаЧтоТекущееСообщенеиОтДруговоПользоватлеяБылоПрочитанно "
                            + РезультатВставкиНовогоФлагаЧтоТекущееСообщенеиОтДруговоПользоватлеяБылоПрочитанно);
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                recordNewErros.recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return ПолученноеСтатусПрочитанноСообщениеТекущееИлиНетНольСтоитИЛиЦифраОДИН;

        }
    }//TODO  конец обьявленого RecycreView для чата
    //TODO  конец обьявленого RecycreView для чата
}








// TODO: 15.07.2022  OLD CODE ДЛЯ ПЕРЕНОСА ИЛИ RECYCREVIEW ИЛИ В СЛУЖБУ



































































    // TODO: 15.07.2022 КОНЕЦ  кода бизнес логики чата



// TODO: 15.07.2022  главный код RECYCREVIEW
