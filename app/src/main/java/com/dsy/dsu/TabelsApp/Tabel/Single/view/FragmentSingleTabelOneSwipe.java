package com.dsy.dsu.TabelsApp.Tabel.Single.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.cursoradapter.widget.CursorAdapter;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.dsy.dsu.BusinessLogicForApps.CoreBinessLogic.CoreBinessLogics;
import com.dsy.dsu.BusinessLogicForApps.GetPublicID.GetttingPublicID;
import com.dsy.dsu.BusinessLogicForApps.UpdateCellsTabel.SubClassUpdatesCELL;
import com.dsy.dsu.BusinessLogicForApps.VersionCurentTable;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.BusinessLogicForApps.Dates.GetMainDateForApp;
import com.dsy.dsu.TabelsApp.Peoples.MainActivityListPeoples;
import com.dsy.dsu.R;
import com.dsy.dsu.TabelsApp.Tabel.Single.model.BunesslogicSingleTabel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textview.MaterialTextView;
import com.jakewharton.rxbinding4.widget.RxTextView;
import com.jakewharton.rxbinding4.widget.TextViewAfterTextChangeEvent;
import com.sous.backasync.launch.ModuleQuety;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.function.IntConsumer;
import java.util.function.ToIntFunction;
import java.util.stream.IntStream;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * AHilt simple {@link Fragment} subclass.
 * Use the {@link FragmentSingleTabelOneSwipe#newInstance} factory method to
 * create an instance of this fragment.
 */


@AndroidEntryPoint
public class FragmentSingleTabelOneSwipe extends Fragment {


    private MaterialTextView spinnerchasy,spinnermesazyear,spinnerdepartament;/////спинеры для создание табеля
    private    String Профессия;
    private  Integer DigitalNameCFO=0;
    private MaterialButton imageButtonbackotsingletabel;
    private  long CurrenrsСhildUUID =0l;
    private  long CurrenrsSelectFio =0l;
    private  long MainParentUUID =0l;

    private  String ФИО;
    private Message message;
    private      Message messageRows;
    private RecyclerView recycleviewsingletabel;
    private  SubClassSingleTabelRecycreView. MyRecycleViewAdapter myRecycleViewAdapter;
    private  SubClassSingleTabelRecycreView. MyViewHolder myViewHolder;
    private  Integer CurrentFragmentMaxItem =0;
    private  String FullNameCFO = "";
    private  Integer ГодТабелей = 0;
    private  String ИмесяцвИГодСразу = "";
    private  Integer МЕсяцТабелей=0;
    private InputMethodManager imm;
    // TODO: 12.10.2022  для одного сигг табеля сотрудника
    private  SubClassBisscessFragmentSingleTabel fragmentSingleTabel;
    private  SubClassSingleTabelRecycreView singleTabelRecycreView;
    private  Integer getlistpeoplesposition;
  private      Animation animation1;
  private      Animation animationFromRecyReview;
  private CopyOnWriteArrayList<Disposable>  disposableAfterTextChangeEvent=new CopyOnWriteArrayList<>();
  private      Cursor cursorSingleTabels;
  private      Handler handlerМетодForCurcorHandlerCallBack;
  private  MaterialTextView  materialTextViewfio,materialTextViewprofession;
    private LinkedHashMap< String,String> getWorkerDays =new LinkedHashMap<>();
    private LinkedHashMap< String,String> getHolidaysDays =new LinkedHashMap<>();

    // TODO: Rename and change types and number of parameters
    public static FragmentSingleTabelOneSwipe newInstance(@NonNull Bundle bundle_single_tabel_viewpagers ) {
        FragmentSingleTabelOneSwipe fragment = new FragmentSingleTabelOneSwipe();
        fragment.setArguments(bundle_single_tabel_viewpagers);
        System.out.printf("bundle_single_tabel_viewpagers "+bundle_single_tabel_viewpagers);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        try{
            super.onCreate(savedInstanceState);
            fragmentSingleTabel=new SubClassBisscessFragmentSingleTabel();
            fragmentSingleTabel.МетодGetmessage();
            // TODO: 29.03.2023  Метод RerecyView RerecyView RerecyView RerecyView RerecyView
            // TODO: 22.06.2023
            singleTabelRecycreView= new SubClassSingleTabelRecycreView( );

            fragmentSingleTabel.new SubClassBungleSingle().методGETДанныеИзДругихАктивити();

            singleTabelRecycreView.МетодForCurcorHandlerCallBack();



            // TODO: 16.04.2023  Выходыне Дни   празнечные ДНИ 
            metodGetsDaySetPrasnchih();

            // TODO: 16.11.2023
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+" cursorForViewPager.getPosition() ");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                    + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewSingletabek=null;
        try{
           // viewSingletabek= inflater.inflate(R.layout.fragment_single_tabel_viewpager2, container, false);
            viewSingletabek= inflater.inflate(R.layout.simple_forfragmentsingle, container, false);
            // TODO: 21.06.2023
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                    + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        // Inflate the layout for this fragment
        return  viewSingletabek; //TODO inflater.inflate(R.layout.activity_main__tabel_four_colums, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        try{
            super.onViewCreated(view, savedInstanceState);
            // TODO: 21.06.2023 КОДЕ
            fragmentSingleTabel.new SubClassNewDataSingleTabel().методВнешнийВидФрагмента(view);

            singleTabelRecycreView.metodAddCurcorRecyreview(cursorSingleTabels);

            singleTabelRecycreView.metodДизайнRecycreView();





            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                    + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        try{

            if (myRecycleViewAdapter!=null) {
                getlistpeoplesposition =myRecycleViewAdapter.cursor.getPosition();
            }

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ " GetPosition " + getlistpeoplesposition);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }

    }

    @Override
    public void onStart() {
        super.onStart();
        try{
            // TODO: 08.05.2025   главный курсор
            cursorSingleTabels =   singleTabelRecycreView.  new GetCursorSingleTabel().МетодSwipesКурсор();
            // TODO: 21.06.2023 Смещения Курсоора
            singleTabelRecycreView.МетодСлушательКурсора(cursorSingleTabels);
            singleTabelRecycreView.  методСчитаемЧасы(cursorSingleTabels );
            // TODO: 04.04.2023  ФИО
            new SubClassSingleTabelRecycreView().МетодЗаполняемФИОиПрофесиюRow(cursorSingleTabels);

            // TODO: 26.06.2023  созданнй CallBack
            singleTabelRecycreView.методДляSimpeCallbacks( );
            singleTabelRecycreView.МетодСлушательRecycleView();
            // TODO: 16.06.2023  перегрузка экрана


        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ " getlistpeoplesposition " +getlistpeoplesposition);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }


    @Override
    public void onResume() {
        super.onResume();
        // TODO: 16.11.2023
                try{
                    if (cursorSingleTabels !=null) {
                //singleTabelRecycreView.metodAddCurcorRecyreview(cursorForViewPager );
                  singleTabelRecycreView.  методRebootRecyreview(cursorSingleTabels);
                // TODO: 16.06.2023  перегрузка экрана


                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                        + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }

    }





    @Override
    public void onStop() {
        super.onStop();
        try{


            //metodReebotDataForFragmentSingleTabel();

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                    + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    private void metodReebotDataForFragmentSingleTabel() {
        try{
            if (myRecycleViewAdapter.cursor!=null) {
                singleTabelRecycreView.  методReeoBootCursorRecyreViewAlfterChangeProffesion();
                // TODO: 15.06.2023 перегрузка данныех
                myRecycleViewAdapter.notifyDataSetChanged();
                recycleviewsingletabel.getAdapter().notifyDataSetChanged();
                // TODO: 26.06.2023  перегрузка

                // TODO: 10.08.2023
            }
            if (disposableAfterTextChangeEvent!=null) {
                disposableAfterTextChangeEvent.forEach(new java.util.function.Consumer<Disposable>() {
                                                           @Override
                                                           public void accept(Disposable disposable) {
                                                               disposable.dispose();
                                                           }
                                                       }
                );
            }

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                    + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }











// TODO: 21.06.2023 Class Бизнес Код Для Фрагмента Single Tabel

    // TODO: 21.06.2023 Class Бизнес Код Для Фрагмента Single Tabel
    // TODO: 21.06.2023 Class Бизнес Код Для Фрагмента Single Tabel

    // TODO: 21.06.2023 Class Бизнес Код Для Фрагмента Single Tabel
    // TODO: 21.06.2023 Class Бизнес Код Для Фрагмента Single Tabel

    // TODO: 21.06.2023 Class Бизнес Код Для Фрагмента Single Tabel



    // TODO: 21.06.2023 Class Бизнес Код Для Фрагмента Single Tabel

    protected   Cursor МетодКурсорДляПрофессииБезФильтра() {
        Cursor КурсорТаблицаПрофесии =null;
        try{
            Bundle bundleПрофесии=new Bundle();
            bundleПрофесии.putString("СамЗапрос","  SELECT * FROM  prof WHERE uuid!=? ");
            bundleПрофесии.putStringArray("УсловияВыборки" ,new String[]{"0"});
            bundleПрофесии.putString("Таблица","prof");

            // TODO: 14.05.2025
            String Текущаятаблицы="prof";
            ModuleQuety moduleQuety=new ModuleQuety(getContext());
            КурсорТаблицаПрофесии=   moduleQuety.getModuleQuery(Текущаятаблицы,"   SELECT *  FROM   "+Текущаятаблицы+" AS D " +
                    "  WHERE   D.uuid='"+String.valueOf(0)+";" ,null);
            // TODO: 09.06.2025
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");


        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        return КурсорТаблицаПрофесии;
    }
    //TODO класс визуализации внешнего вида

    private void metodGetsDaySetPrasnchih()   {
        try{
            // TODO: 20.11.2023 Получение Празничных и Выходных Дней
            GetDayFromKalendary getDayFromKalendary=new GetDayFromKalendary();

            getWorkerDays = getDayFromKalendary.getWorkerDays();
            // TODO: 26.06.2023 Празничные Дни
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+" cursorForViewPager.getPosition() ");


            getHolidaysDays =getDayFromKalendary.getHolidaysDays();
            // TODO: 16.11.2023
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+" cursorForViewPager.getPosition() ");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                    + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    @SuppressLint("Range")
    class  SubClassBisscessFragmentSingleTabel{
        //TODO метод делает callback с ответом на экран
        private  void  МетодGetmessage(){
            try{
                message=new Handler(Looper.getMainLooper(), new Handler.Callback() {
                    @Override
                    public boolean handleMessage(@NonNull Message msg) {
                        try{
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(getContext()).recordnewerror(e.toString(),
                                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                        return true;
                    }
                }).obtainMessage();

                messageRows=Message.obtain(new Handler(Looper.myLooper()),()->{
                    try{
                        Bundle bundle=   messageRows.getData();
                        Log.i(this.getClass().getName(),  " Атоманически установкаОбновление ПО "+
                                Thread.currentThread().getStackTrace()[2].getMethodName()+
                                " время " +new Date().toLocaleString() + " bundle " +bundle );
                        Log.i(this.getClass().getName(), "bundle " +bundle);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(getContext()).recordnewerror(e.toString(),
                                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        //TODO хдесь мы запускаем метод создание и обработка самого табеля
        private void методСпинерМесяцы( ) {
            try{
                // TODO: 20.11.2023  заполение часов
                class SpinerMoth extends AddDataSpinersHasyAndMotch{
                    @Override
                    protected void metodAddDataSpinersHasyAndMotch(@NonNull MaterialTextView materialTextView, @NonNull String string) {
                        super.metodAddDataSpinersHasyAndMotch(materialTextView, string);
                    }
                }
                new SpinerMoth().metodAddDataSpinersHasyAndMotch(spinnermesazyear,ИмесяцвИГодСразу);
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
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

        private void МетодСпинерДепартамент( ) {
            // TODO: 20.11.2022 второй спинер департамент
            try {
                // TODO: 20.11.2023  заполение часов
                class SpinerДепартамент extends AddDataSpinersHasyAndMotch{
                    @Override
                    protected void metodAddDataSpinersHasyAndMotch(@NonNull MaterialTextView materialTextView, @NonNull String string) {
                        super.metodAddDataSpinersHasyAndMotch(materialTextView, string);
                    }
                }
                new SpinerДепартамент().metodAddDataSpinersHasyAndMotch(spinnerdepartament,FullNameCFO);

                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
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

        private void МетодОтработкиПоднятияКлавиатуры() {
            try{
                KeyboardVisibilityEvent.setEventListener(getActivity(), new KeyboardVisibilityEventListener() {
                    @Override
                    public void onVisibilityChanged(boolean isOpen) {
                        if (isOpen) {
                            Log.d(this.getClass().getName(), " FullNameCFO  "+FullNameCFO +"\n"+
                                    " FullNameCFO " + FullNameCFO);
                            методСпинерМесяцы( );
                        }else{
                            Log.d(this.getClass().getName(), " FullNameCFO  "+FullNameCFO +"\n"+
                                    " FullNameCFO " + FullNameCFO);
                            методСпинерМесяцы( );
                        }
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());

            }

        }

        private void МетодПриНАжатииНаКнопкуBACK() {
            try {
                imageButtonbackotsingletabel.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Log.d(this.getClass().getName(), "  onDestroyView");
                        методПереходаMainActivity_List_Peoples();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }


        }

        void методПереходаMainActivity_List_Peoples() {
            try{
                ////TODO ИНТРЕНТ КОТОРЫЙ СОЗДАЕТ НОВГО СОТРУДНИКА
                Intent Интент_ПереходаMainActivity_List_Peoples = new Intent();
                Интент_ПереходаMainActivity_List_Peoples.setClass(getContext(), MainActivityListPeoples.class);
                Bundle bundleИзMainActitivy_List_Tables=getArguments();
                Интент_ПереходаMainActivity_List_Peoples.putExtras(bundleИзMainActitivy_List_Tables);
                // TODO: 28.06.2023 clear
                myRecycleViewAdapter.cursor.close();
                recycleviewsingletabel.removeAllViewsInLayout();
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " FullNameCFO "+FullNameCFO+ " CurrenrsСhildUUID " +CurrenrsСhildUUID
                        + " ГодТабелей " +ГодТабелей +" МЕсяцТабелей " +МЕсяцТабелей   + " DigitalNameCFO "+DigitalNameCFO+
                        " CurrentFragmentMaxItem " + CurrentFragmentMaxItem + " ИмесяцвИГодСразу " +ИмесяцвИГодСразу);
                startActivity(Интент_ПереходаMainActivity_List_Peoples);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }

        }

        // TODO: 21.06.2023  перенесоный метод из Activity Single Tabel
        class SubClassNewDataSingleTabel{
            void методВнешнийВидФрагмента(@NonNull View view){
                try{
                    RelativeLayout getLayout_tabel_single=(RelativeLayout)view.findViewById(R.id.linearLayout_tabel_single);


                    spinnermesazyear = (MaterialTextView) getLayout_tabel_single. findViewById(R.id.spinnermesazyear_single);
                    spinnerchasy = (MaterialTextView) getLayout_tabel_single.findViewById(R.id.spinnerchasy_single);
                    spinnerdepartament = (MaterialTextView) getLayout_tabel_single.findViewById(R.id.spinnerdepartament_single);

                    ///TODO на данной КНОПКЕ МЫ МОЖЕМ ДОБАВИТЬ СОТРУДНИКА К ТАБЕЛЮ ИЛИ СОЗДАТЬ НОВОГО СОТРУДНИКА
                    imageButtonbackotsingletabel =(MaterialButton)getLayout_tabel_single. findViewById(R.id.imageButtonbackotsingletabel);

                    materialTextViewfio = (MaterialTextView)  getLayout_tabel_single.findViewById(R.id.materialTextViewfio);
                    materialTextViewprofession = (MaterialTextView)  getLayout_tabel_single.findViewById(R.id.materialTextViewprofession);

                    recycleviewsingletabel = (RecyclerView)  getLayout_tabel_single.findViewById(R.id.recycleviewsingletabel);
                    recycleviewsingletabel.scrollToPosition(View.FOCUS_UP);
                    recycleviewsingletabel.setNestedScrollingEnabled(false);
                    recycleviewsingletabel.setVerticalScrollBarEnabled(true);



                    fragmentSingleTabel.  методСпинерМесяцы( );
                    fragmentSingleTabel.    МетодСпинерДепартамент();
                    fragmentSingleTabel.    МетодОтработкиПоднятияКлавиатуры();
                    fragmentSingleTabel.    МетодПриНАжатииНаКнопкуBACK();


                    animation1 = AnimationUtils.loadAnimation(getContext(),R.anim.slide_singletable2);
                    animationFromRecyReview= AnimationUtils.loadAnimation(getContext(),R.anim.slide_in_row);
           /*     animationПрофессия300 = AnimationUtils.loadAnimation(getContext(),R.anim.slide_in_row2);
                animationVibr1 = AnimationUtils.loadAnimation(getContext(),R.anim.slide_singletable);
                animationVibr2 = AnimationUtils.loadAnimation(getContext(),R.anim.slide_singletable2);
                animationRows = AnimationUtils.loadAnimation(getContext(),R.anim.slide_in_row_scroll_for_singletabel);
                animationRich = AnimationUtils.loadAnimation(getContext(),R.anim.slide_in_swipe_r);//R.anim.slide_in_row)
                animationLesft = AnimationUtils.loadAnimation(getContext(),R.anim.slide_in_swipe_l);//R.anim.slide_in_row)R.anim.slide_in_row_newscanner1*/

                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  + " CurrentFragmentMaxItem " + CurrentFragmentMaxItem);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                            + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }
        }//TODO END   class SubClassNewDataSingleTabel

        class SubClassBungleSingle{
            private void методGETДанныеИзДругихАктивити() {
                try {
                    Bundle     bundleИзMainActitivy_List_Tables=(Bundle) getArguments();
                    // TODO: 10.04.2023
                    if (bundleИзMainActitivy_List_Tables!=null) {
                        // TODO: 23.06.2023  NEW
                        MainParentUUID=    bundleИзMainActitivy_List_Tables.getLong("MainParentUUID", 0l);
                        ГодТабелей=  bundleИзMainActitivy_List_Tables.getInt("ГодТабелей", 0);
                        МЕсяцТабелей=  bundleИзMainActitivy_List_Tables.getInt("МЕсяцТабелей",0);
                        DigitalNameCFO=   bundleИзMainActitivy_List_Tables.getInt("DigitalNameCFO", 0);
                        FullNameCFO=  bundleИзMainActitivy_List_Tables.getString("FullNameCFO", "").trim();
                        ИмесяцвИГодСразу= bundleИзMainActitivy_List_Tables.getString("ИмесяцвИГодСразу", "").trim();
                        CurrenrsСhildUUID= bundleИзMainActitivy_List_Tables.getLong("CurrenrsСhildUUID", 0l);
                        ФИО= bundleИзMainActitivy_List_Tables.getString("ФИО", "").trim();
                        CurrenrsSelectFio= bundleИзMainActitivy_List_Tables.getLong("CurrenrsSelectFio", 0l);
                        getlistpeoplesposition =  bundleИзMainActitivy_List_Tables.getInt("listpeoplesposition", 0);
                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                + " FullNameCFO "+FullNameCFO+ " CurrenrsСhildUUID " +CurrenrsСhildUUID
                                + " ГодТабелей " +ГодТабелей +" МЕсяцТабелей " +МЕсяцТабелей   + " DigitalNameCFO "+DigitalNameCFO+
                                " CurrentFragmentMaxItem " + CurrentFragmentMaxItem
                                + " ИмесяцвИГодСразу " +ИмесяцвИГодСразу
                                + "  cursorForViewPager " + cursorSingleTabels + " getPositionSingle " + getlistpeoplesposition);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }


            private void методGETДанныеRunTimeИзCursor(@NonNull Cursor cursor ) {
                try {
                    // TODO: 10.04.2023
                    if (cursor!=null) {
                        MainParentUUID=    cursor.getLong(cursor.getColumnIndex("uuid_tabel"));
                        CurrentFragmentMaxItem =     myRecycleViewAdapter.cursor.getPosition();
                        ГодТабелей=  cursor.getInt(cursor.getColumnIndex("year_tabels"));
                        МЕсяцТабелей= cursor.getInt(cursor.getColumnIndex("month_tabels"));
                        DigitalNameCFO=   cursor.getInt(cursor.getColumnIndex("cfo"));
                        CurrenrsСhildUUID= cursor.getLong(cursor.getColumnIndex("uuid"));
                        ФИО= cursor.getString(cursor.getColumnIndex("name"));
                        CurrenrsSelectFio= cursor.getLong(cursor.getColumnIndex("fio"));
                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                + " FullNameCFO "+FullNameCFO+ " CurrenrsСhildUUID " +CurrenrsСhildUUID
                                + " ГодТабелей " +ГодТабелей +" МЕсяцТабелей " +МЕсяцТабелей   + " DigitalNameCFO "+DigitalNameCFO+
                                " CurrentFragmentMaxItem " + CurrentFragmentMaxItem + " ИмесяцвИГодСразу " +ИмесяцвИГодСразу + "  cursor " +cursor);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }


        }
    }//TODO END  class  SubClassBisscessFragmentSingleTabel //TODO END  class  SubClassBisscessFragmentSingleTabel //TODO END  class  SubClassBisscessFragmentSingleTabel

    // TODO: 21.06.2023  --- 15.25   Пересенный Класс из Activity Singe Tabel НА Fragment Single Tabel
    public class SubClassSingleTabelRecycreView  {
        private     Cursor cursorForViewPager;

        public SubClassSingleTabelRecycreView() {
        }

        private void metodДизайнRecycreView() {
            try{
                DividerItemDecoration dividerItemDecorationVer=
                        new DividerItemDecoration(getActivity(), GridLayoutManager.VERTICAL);
                dividerItemDecorationVer.setDrawable(getContext().getDrawable(R.drawable.divider_for_order_transport2));///R.dimen.activity_horizontal_margin
                recycleviewsingletabel.addItemDecoration(dividerItemDecorationVer);
                GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 1,GridLayoutManager.VERTICAL,false);
                layoutManager.setSpanSizeLookup(new GridLayoutManager.DefaultSpanSizeLookup());

               /* DividerItemDecoration dividerItemDecorationVer=
                        new DividerItemDecoration(getActivity(),LinearLayoutManager.VERTICAL);
                dividerItemDecorationVer.setDrawable(getContext().getDrawable(R.drawable.divider_for_order_transport2));///R.dimen.activity_horizontal_margin
                recycleviewsingletabel.addItemDecoration(dividerItemDecorationVer);*/

              /*  GetDividerItemDecorationTabelSingle getDividerItemDecoration=new GetDividerItemDecorationTabelSingle(getContext(), LinearLayoutManager.HORIZONTAL);

               *//* DividerItemDecoration dividerItemDecorationHor=
                        new DividerItemDecoration(getActivity(), LinearLayoutManager.HORIZONTAL);*//*
            *//*    dividerItemDecorationHor.setDrawable(getContext().getDrawable(R.drawable.divider_for_order_transport2));///R.dimen.activity_horizontal_margin*//*
                recycleviewsingletabel.addItemDecoration(getDividerItemDecoration);*/
                // recycleviewsingletabel.addItemDecoration(new GetDividerItemDecorationTabelSingle(getContext()));

                //LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity() , LinearLayoutManager.VERTICAL, true);
                recycleviewsingletabel.setLayoutManager(layoutManager);
                recycleviewsingletabel.setItemAnimator(new DefaultItemAnimator());
                // TODO: 12.05.2023 Клаиатура
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+  "cursorForViewPager " + cursorForViewPager);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
        void методИницаллизацииКлавиаотурыЯчейка(@NonNull EditText editText){
            try{
                if (editText!=null) {
                    imm = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput( editText, InputMethodManager.SHOW_IMPLICIT);
                }
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+  "cursorForViewPager " + cursorForViewPager);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        // TODO: 15.06.2023 свайвы
        // TODO: 15.06.2023 свайвы













        // TODO: 15.06.2023  скоол левый внутри reryvreview
        private Cursor методScrollsRightRecyreView() {
            try {
                // TODO: 20.04.2023 Данные
                Cursor       cursorSwipeViewPager=   myRecycleViewAdapter.cursor;
                if (!cursorSwipeViewPager.isFirst()){
                    cursorSwipeViewPager.moveToPrevious();
                }
                // TODO: 11.05.2025
                myRecycleViewAdapter.  onAttachedToRecyclerView(recycleviewsingletabel);

                myRecycleViewAdapter.cursor= cursorSwipeViewPager;
                // TODO: 15.06.2023 перегрузка данныех
                myRecycleViewAdapter.notifyDataSetChanged();
                // TODO: 18.06.2023
                recycleviewsingletabel.getAdapter().notifyDataSetChanged();

                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+"CurrentFragmentMaxItem   " + CurrentFragmentMaxItem + " cursorForViewPager " + cursorForViewPager +
                        " posio " +myViewHolder.getLayoutPosition()  + " CurrenrsСhildUUID " +CurrenrsСhildUUID + " CurrenrsSelectFio " +CurrenrsSelectFio + "  ФИО " + ФИО
                        + " cursorSwipeViewPager " + cursorSwipeViewPager.getPosition());

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return   myRecycleViewAdapter.cursor;
        }




        // TODO: 15.06.2023  скоол левый внутри reryvreview
        private Cursor методScrollsLeftRecyreView() {
            try {
                Integer Позиция=        myRecycleViewAdapter.cursor.getPosition();
                // TODO: 20.04.2023 Данные
                Cursor       cursorSwipeViewPager=   myRecycleViewAdapter.cursor;
                if (!cursorSwipeViewPager.isLast()){
                    cursorSwipeViewPager.moveToNext();
                }


                // TODO: 11.05.2025
                myRecycleViewAdapter.  onAttachedToRecyclerView(recycleviewsingletabel);

                myRecycleViewAdapter.cursor= cursorSwipeViewPager;
                // TODO: 15.06.2023 перегрузка данныех
                myRecycleViewAdapter.notifyDataSetChanged();
                // TODO: 18.06.2023
                recycleviewsingletabel.getAdapter().notifyDataSetChanged();

                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+"CurrentFragmentMaxItem   " + CurrentFragmentMaxItem + " cursorForViewPager " + cursorForViewPager +
                        " posio " +myViewHolder.getLayoutPosition()  + " CurrenrsСhildUUID " +CurrenrsСhildUUID + " CurrenrsSelectFio " +CurrenrsSelectFio + "  ФИО " + ФИО
                        + " cursorSwipeViewPager " + cursorSwipeViewPager.getPosition());

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return   myRecycleViewAdapter.cursor;
        }




        // TODO: 04.03.2022 прозвомжность Заполения RecycleView
        void metodAddCurcorRecyreview(@NonNull Cursor cursorForViewPager) {
            try {
                // remove item from adapter
                myRecycleViewAdapter = new  MyRecycleViewAdapter(cursorForViewPager );
                myRecycleViewAdapter.notifyDataSetChanged();
                recycleviewsingletabel.setAdapter(myRecycleViewAdapter);
                recycleviewsingletabel.requestLayout();
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                        "cursorForViewPager  " + cursorForViewPager );
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        private void методЗакрываемКлавитатуру( ) {
            try{
                getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
                recycleviewsingletabel.clearFocus();
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        // TODO: 03.04.2023 перенесеный RecycreView
        private void МетодСлушательКурсора( @NonNull Cursor cursor) {
            // TODO: 15.10.2022  слушатиель для курсора
            try {
                cursor.registerDataSetObserver(new DataSetObserver() {
                    @Override
                    public void onChanged() {
                        super.onChanged();
                        Log.d(this.getClass().getName(), "recycleviewsingletabel   " + recycleviewsingletabel);
                    }

                    @Override
                    public void onInvalidated() {
                        super.onInvalidated();
                        Log.d(this.getClass().getName(), "recycleviewsingletabel   " + recycleviewsingletabel);
                    }
                });
                // TODO: 15.10.2022
                cursor.registerContentObserver(new ContentObserver(handlerМетодForCurcorHandlerCallBack) {
                    @Override
                    public boolean deliverSelfNotifications() {
                        Log.d(this.getClass().getName(), "recycleviewsingletabel   " + recycleviewsingletabel);
                        return super.deliverSelfNotifications();
                    }

                    @Override
                    public void onChange(boolean selfChange) {
                        Log.d(this.getClass().getName(), "recycleviewsingletabel   " + recycleviewsingletabel);
                        super.onChange(selfChange);
                    }

                    @Override
                    public void onChange(boolean selfChange, @Nullable Uri uri) {
                        Log.d(this.getClass().getName(), "recycleviewsingletabel   " + recycleviewsingletabel);
                        super.onChange(selfChange, uri);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        // TODO: 15.06.2023 свайвы
        private void методДляSimpeCallbacks(   ) {
            try {

                // TODO: 11.05.2023 SWIPE:
                ItemTouchHelper.SimpleCallback simpleItemTouchCallbackSwipe = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP,
                        ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {

                    @Override
                    public boolean isItemViewSwipeEnabled() {
                        return true;
                    }

                    @Override
                    public boolean isLongPressDragEnabled() {
                        return false;
                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                        try {

                            getLeftRecyreVieData getLeftAndRightRecyreVieData =new getLeftRecyreVieData();
                            // TODO: 17.06.2023 сама свайп
                            if(swipeDir == ItemTouchHelper.LEFT){
                                Log.i("Swipe direction : ","Left");
                                // TODO: 06.03.2025
                                // TODO: 16.06.2023
                               Cursor cursorSwipeViewPagerLeft=      singleTabelRecycreView.      методScrollsLeftRecyreView();
                                getLeftAndRightRecyreVieData.методПереРоденияRevireViewScroll(cursorSwipeViewPagerLeft);
                                методСчитаемЧасы(cursorSwipeViewPagerLeft,cursorSwipeViewPagerLeft.getPosition());
                                // TODO: 04.04.2023  ФИО
                                    МетодЗаполняемФИОиПрофесиюRow( cursorSwipeViewPagerLeft);
                            }
                            else if (swipeDir == ItemTouchHelper.RIGHT){
                                Log.i("Swipe direction : ","Right");
                                Cursor cursorSwipeViewPagerRight=      singleTabelRecycreView.      методScrollsRightRecyreView();
                                getLeftAndRightRecyreVieData.методПереРоденияRevireViewScroll(cursorSwipeViewPagerRight);
                                методСчитаемЧасы(cursorSwipeViewPagerRight,cursorSwipeViewPagerRight.getPosition());
                                // TODO: 04.04.2023  ФИО
                                   МетодЗаполняемФИОиПрофесиюRow( cursorSwipeViewPagerRight);
                            }


                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(getContext().getClass().getName(),
                                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(getContext()).recordnewerror(e.toString(),
                                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }

                    @SuppressLint("NewApi")
                    @Override
                    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
                                            @NonNull RecyclerView.ViewHolder viewHolder,
                                            float dX, float dY, int actionState, boolean isCurrentlyActive) {
                        try{

                            // TODO: 16.11.2023 офрмлаяем дизацн при SWIPE
                            методИзмененияЦветаSwipes(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

                            // TODO: 16.11.2023  после как переопределили вызыывем супер
                            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "PositionCustomer   "
                                    + " CurrenrsСhildUUID " + CurrenrsСhildUUID + " CurrenrsSelectFio " + CurrenrsSelectFio + "  ФИО " + ФИО);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(getContext().getClass().getName(),
                                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(getContext()).recordnewerror(e.toString(),
                                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }

                    private void методИзмененияЦветаSwipes(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
                                                           @NonNull RecyclerView.ViewHolder viewHolder,
                                                           float dX, float dY, int actionState, boolean isCurrentlyActive) {
                        try {
                            if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                                // TODO: 18.06.2023 первый вариант
                                RecyclerView.ViewHolder vh = (RecyclerView.ViewHolder) viewHolder;
                                View itemView = vh.itemView;

                                Paint p = new Paint();
                                if(dX > 0) {
                                    p.setColor(Color.parseColor("#E0E0E3"));//828080 /// FCFAFA //F2F8F8 887B7B
                                } else {
                                    p.setColor(Color.parseColor("#887B7B")); /// F2F8F8  E0E0E3

                                }
                                c.drawRect(itemView.getLeft(), itemView.getTop(), itemView.getRight(), itemView.getBottom(), p);

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(getContext().getClass().getName(),
                                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(getContext()).recordnewerror(e.toString(),
                                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }

                    @Override
                    public void onChildDrawOver(@NonNull Canvas c, @NonNull RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                        super.onChildDrawOver(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                    }

                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                          RecyclerView.ViewHolder target) {
                        final int fromPos = viewHolder.getAdapterPosition();
                        final int toPos = target.getAdapterPosition();
                        // move item in `fromPos` to `toPos` in adapter.
                        return true;// true if moved, false otherwise
                    }


                };
                ItemTouchHelper itemTouchHelperLEFT = new ItemTouchHelper(simpleItemTouchCallbackSwipe);
                itemTouchHelperLEFT.attachToRecyclerView(recycleviewsingletabel);
                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "recycleviewsingletabel   " + recycleviewsingletabel);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }


        }



        private void методRebootRecyreview(@NotNull   Cursor    cursorForRebootCursor) {
            try{
                // TODO: 21.06.2023 Смещения Курсоора
                myRecycleViewAdapter.cursor=cursorForRebootCursor;
                myRecycleViewAdapter.notifyDataSetChanged();
                RecyclerView.Adapter recyclerViewОбновление=         recycleviewsingletabel.getAdapter();
                recycleviewsingletabel.swapAdapter(recyclerViewОбновление,true);
                recycleviewsingletabel.getAdapter().notifyDataSetChanged();
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +" cursorForRebootCursor " +cursorForRebootCursor );
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }



        // TODO: 14.04.2023  слушатель Recycreviwe
        void МетодСлушательRecycleView() {  // TODO: 04.03.2022  класс в котором находяться слушатели
            try {
                myRecycleViewAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                    @Override
                    public void onChanged() {
                        super.onChanged();
                        try {
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                    + " cursorForViewPager "+ cursorForViewPager);
                            //TODO
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(getContext().getClass().getName(),
                                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(getContext()).recordnewerror(e.toString(),
                                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }

                    @Override
                    public void onItemRangeChanged(int positionStart, int itemCount) {
                        super.onItemRangeChanged(positionStart, itemCount);
                        // TODO: 05.03.2022  СТАТУС ЗНАЧКА С ДОПОЛНИТЕЛЬНЫЙ СТАТУСОМ
                        try {
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                    + " cursorForViewPager "+ cursorForViewPager);
                            //TODO
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(getContext().getClass().getName(),
                                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(getContext()).recordnewerror(e.toString(),
                                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }

                    @Override
                    public void onItemRangeChanged(int positionStart, int itemCount, @Nullable Object payload) {
                        super.onItemRangeChanged(positionStart, itemCount, payload);
                        try {
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(getContext().getClass().getName(),
                                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(getContext()).recordnewerror(e.toString(),
                                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }

                    @Override
                    public void onItemRangeInserted(int positionStart, int itemCount) {
                        super.onItemRangeInserted(positionStart, itemCount);
                        try {
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(getContext().getClass().getName(),
                                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(getContext()).recordnewerror(e.toString(),
                                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }

                    @Override
                    public void onItemRangeRemoved(int positionStart, int itemCount) {
                        super.onItemRangeRemoved(positionStart, itemCount);
                        try {
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                            //TODO
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(getContext().getClass().getName(),
                                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(getContext()).recordnewerror(e.toString(),
                                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }

                    @Override
                    public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
                        super.onItemRangeMoved(fromPosition, toPosition, itemCount);
                        try {
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(getContext().getClass().getName(),
                                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(getContext()).recordnewerror(e.toString(),
                                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        void  МетодForCurcorHandlerCallBack(){

            try{

                Handler.Callback callback = new Handler.Callback() {
                    @Override
                    public boolean handleMessage(@NonNull android.os.Message msg) {
                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                        return true;
                    }
                };
                handlerМетодForCurcorHandlerCallBack = new Handler(callback);

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        void методСчитаемЧасы(@NonNull Cursor cursor ) {
            try{
                //TODO ЗАПОЛЯНЕМ ПОЛУЧЕННЫЙ МЕСЯ Ц ПЛУС КОЛИЧЕСТВО ЧАСОВ СОТРУДНИКА КОНКРЕТНОГО
                Integer   ЧасыТекущегоСОтрудника = new BunesslogicSingleTabel(getContext()).getemployeeHoursCounting(cursor);
                Log.d(this.getClass().getName(), "  ЧасыТекущегоСОтрудника " + ЧасыТекущегоСОтрудника);
                 Integer getHortsCurrentpostion=     getlistpeoplesposition+1;
                String ЧасыТекущегоСотрудника="(" + ЧасыТекущегоСОтрудника + "/часы)" +
                        ""+"\n"+"("+getHortsCurrentpostion+")";

             // TODO: 20.11.2023  заполение часов

                class SpinerЧасы extends AddDataSpinersHasyAndMotch{
                    @Override
                    protected void metodAddDataSpinersHasyAndMotch(@NonNull MaterialTextView materialTextView, @NonNull String string) {
                        super.metodAddDataSpinersHasyAndMotch(materialTextView, string);
                    }
                }
                new SpinerЧасы().metodAddDataSpinersHasyAndMotch(spinnerchasy,ЧасыТекущегоСотрудника);


                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " +e + " Метод :"+Thread.currentThread().getStackTrace()[2].getMethodName()
                        + " Линия  :"+Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(),  this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }

        }

        void методСчитаемЧасы(@NonNull Cursor cursor ,@NonNull Integer gerRecyreViewPosion) {
            try{
                //TODO ЗАПОЛЯНЕМ ПОЛУЧЕННЫЙ МЕСЯ Ц ПЛУС КОЛИЧЕСТВО ЧАСОВ СОТРУДНИКА КОНКРЕТНОГО
                Integer   ЧасыТекущегоСОтрудника = new BunesslogicSingleTabel(getContext()).getemployeeHoursCounting(cursor);
                Log.d(this.getClass().getName(), "  ЧасыТекущегоСОтрудника " + ЧасыТекущегоСОтрудника);
                Integer getHortsCurrentpostion=     gerRecyreViewPosion+1;
                String ЧасыТекущегоСотрудника="(" + ЧасыТекущегоСОтрудника + "/часы)" +
                        ""+"\n"+"("+getHortsCurrentpostion+")";

                // TODO: 20.11.2023  заполение часов

                class SpinerЧасы extends AddDataSpinersHasyAndMotch{
                    @Override
                    protected void metodAddDataSpinersHasyAndMotch(@NonNull MaterialTextView materialTextView, @NonNull String string) {
                        super.metodAddDataSpinersHasyAndMotch(materialTextView, string);
                    }
                }
                new SpinerЧасы().metodAddDataSpinersHasyAndMotch(spinnerchasy,ЧасыТекущегоСотрудника);




                Log.d(CoreBinessLogics.class.getName()," RowNumber  " + " cursorForViewPager " +cursor.getPosition()
                        +"myViewHolder.getLayoutPosition()   "+myViewHolder.getLayoutPosition() +
                        "CurrentFragmentMaxItem  " + CurrentFragmentMaxItem);

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " +e + " Метод :"+Thread.currentThread().getStackTrace()[2].getMethodName()
                        + " Линия  :"+Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(),  this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }

        }

        // TODO: 08.11.2022 КЛИК ПО ФИО
        @SuppressLint("Range")
        private void МетодаКликаTableRowФИО() {
            try{
                materialTextViewprofession.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundleПереходДетализацию=(Bundle) v.getTag();
                        if (bundleПереходДетализацию != null) {
                            // TODO: 27.08.2021  ПОЛУЧЕНИЕ ДАННЫХ ОТ КЛАССА GRUD-ОПЕРАЦИИ
                            try{
                                TextView TextViewФИОДляУдаление = (TextView) v;
                                Log.d(this.getClass().getName(),   " TextViewФИОДляУдаление.getText() " + TextViewФИОДляУдаление.getText());
                                //////TODO ГЛАВНЫЙ КУРСОР ДЛЯ НЕПОСРЕДТСВЕНОГО ЗАГРУЗКИ СОТРУДНИКА
                                Bundle bundleTabelViewПрофессияФИО= (Bundle) TextViewФИОДляУдаление.getTag();

                                if (bundleTabelViewПрофессияФИО!=null) {
                                    // TODO: 14.05.2025
                                    String Текущаятаблицы="fio";
                                    ModuleQuety moduleQuety=new ModuleQuety(getContext());
                                    Cursor    КурсорТаблицаФИО=   moduleQuety.getModuleQuery(Текущаятаблицы,"   SELECT *  FROM   "+Текущаятаблицы+" AS D " +
                                            "  WHERE   D.uuid='"+String.valueOf(CurrenrsSelectFio)+";" ,null);
                                    // TODO: 09.06.2025
                                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");





                                    if (КурсорТаблицаФИО.getCount()>0) {
                                      String ФИОИнфо= Optional.ofNullable(КурсорТаблицаФИО.getString(КурсорТаблицаФИО.getColumnIndex("name"))).orElse("");
                                        String ДеньРОжденияИНФО= Optional.ofNullable(КурсорТаблицаФИО.getString(КурсорТаблицаФИО.getColumnIndex("BirthDate"))).orElse("");
                                        Long СНИЛСИНфо= КурсорТаблицаФИО.getLong(КурсорТаблицаФИО.getColumnIndex("snils"));
                                        String ПрофессияИзФИо= Optional.ofNullable(КурсорТаблицаФИО.getString(КурсорТаблицаФИО.getColumnIndex("prof"))).orElse("Должность");


                                        // TODO: 20.03.2023  ПОказываем Данные Для Обзора
                                        СообщениеИнформацияОСотруднике("Данные",  "ФИО: " +ФИОИнфо+
                                                "\n"+"День рождения: " +ДеньРОжденияИНФО+
                                                "\n"+"СНИЛС: " +СНИЛСИНфо+
                                                "\n" +"Должость: " + "("+bundleTabelViewПрофессияФИО.getString("Профессия").trim()+ " )");

                                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                                + " КурсорТаблицаФИО "+КурсорТаблицаФИО.getCount() );
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                        }
                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                + " v "+v );
                    }
                });
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        ///todo сообщение информация о ФИО
        @UiThread
        protected void СообщениеИнформацияОСотруднике(@NonNull String ШабкаДиалога,
                                                      @NonNull String СообщениеДиалога) {
            ///////СОЗДАЕМ ДИАЛОГ ДА ИЛИ НЕТ///////СОЗДАЕМ ДИАЛОГ ДА ИЛИ НЕТ
            try{
//////сам вид
               new MaterialAlertDialogBuilder(getActivity())
                        .setTitle(ШабкаДиалога)
                        .setMessage(СообщениеДиалога)
                        .setPositiveButton("Закрыть", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    materialTextViewprofession.setBackgroundColor(Color.WHITE);
                                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                            " materialTextViewprofession  " + materialTextViewprofession);
                                    dialog.dismiss();
                                    dialog.cancel();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    new RecordNewErros(getContext()).recordnewerror(e.toString(),
                                            this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                                }
                            }
                        })
                        .setNegativeButton("Изменить", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {


                                    materialTextViewprofession.setBackgroundColor(Color.WHITE);
                                    Cursor КурсорТаблицаПрофесии = МетодКурсорДляПрофессииБезФильтра();
                                    // TODO: 27.03.2023 Новый ПОсик
                                    new SubClassSearchProfessia().МетодСообщениеНовыйПоиска(getActivity(),КурсорТаблицаПрофесии ,message,"prof", CurrenrsСhildUUID);
                                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                            " materialTextViewprofession  " + materialTextViewprofession);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    new RecordNewErros(getContext()).recordnewerror(e.toString(),
                                            this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                                }
                            }

                        })
                        .setIcon(R.drawable.icon_dsu1_info_customer)
                        .setCancelable(true)
                        .show();
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        private void МетодПереходаНаМеткиТабеля(@NonNull EditText editTextЯчейка) {
            try {
                Intent intentПереХодНаМеткиТабеля = new Intent();
                intentПереХодНаМеткиТабеля.setClass(getContext(), MainActivity_Metki_Tabel.class);
                Bundle bundleToMainActitivyMetkiTabel= (Bundle) editTextЯчейка.getTag();
                bundleToMainActitivyMetkiTabel.putInt("listpeoplesposition",myRecycleViewAdapter.cursor.getPosition());

                if (bundleToMainActitivyMetkiTabel!=null) {
                    intentПереХодНаМеткиТабеля.putExtras(bundleToMainActitivyMetkiTabel);
                    message.getTarget().postDelayed(()->{
                        // TODO: 10.04.2023  переход ИЗ MAINaCTITyTabelSingle Peolpe
                        // TODO: 10.04.2023  ОТПРАВЛЯЕММ ПЕРЕМЕННЫЕ
                      startActivity(intentПереХодНаМеткиТабеля);
                    },100);
                }

                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " FullNameCFO " + FullNameCFO + " CurrenrsСhildUUID " + CurrenrsСhildUUID
                        + " ГодТабелей " + ГодТабелей + " МЕсяцТабелей " + МЕсяцТабелей + " DigitalNameCFO " + DigitalNameCFO +
                        " CurrentFragmentMaxItem " + CurrentFragmentMaxItem + " ИмесяцвИГодСразу " + ИмесяцвИГодСразу+ " editTextЯчейка " +editTextЯчейка);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        void методReeoBootCursorRecyreViewAlfterChangeProffesion() {
            try{
                if (myRecycleViewAdapter.cursor!=null) {
                    Cursor cursorПослеОбновлениеПрофесии=myRecycleViewAdapter.cursor;
                    Integer ПозицияКурсора= myRecycleViewAdapter.cursor.getPosition();
                    cursorПослеОбновлениеПрофесии  =   singleTabelRecycreView.  new GetCursorSingleTabel().МетодSwipesКурсор();
                    cursorПослеОбновлениеПрофесии.moveToPosition(ПозицияКурсора);
                    myRecycleViewAdapter.cursor=cursorПослеОбновлениеПрофесии;
                }
                Log.d(this.getClass().getName(),"\n" + " class " +
                        Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                        this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        // TODO: 28.02.2022 начало  MyViewHolderДляЧата
        protected class MyViewHolder extends RecyclerView.ViewHolder {
            private TableLayout tableLayout_single_tabel_one_swipe;
            private TableRow TableRow1Name,TableRow2Name ,TableRow3Name,TableRow4Name,TableRow5Name,TableRow6Name,TableRow7Name,TableRow8Name;
            private  TableRow TableData1Row,TableData2Row,TableData3Row,TableData4Row,TableData5Row,TableData6Row,TableData7Row,TableData8Row;
            private  CopyOnWriteArrayList<LinkedHashMap<TableRow,TableRow>> linkedHashMapsНазваниеиДанные =new CopyOnWriteArrayList<>();
            private    LinkedHashMap<TableRow,TableRow> linkedHashMapНазвание=new LinkedHashMap<>();
            public MyViewHolder(@NonNull View itemView ) {
                super(itemView);
                try {
                    // TODO: 04.04.2023 Метод Иинициализации RecycreVIEW ALL ELEMENT
                    методИнициализацииПеременыхRecureView(itemView);
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ " itemView "+itemView);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(getContext().getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(),
                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }
            private void методИнициализацииПеременыхRecureView(@NonNull View itemView) {
                try {
                    // TODO: 04.04.2023  Иниуциализация Комепонетов
                    tableLayout_single_tabel_one_swipe = itemView.findViewById(R.id.tableLayout_single_tabel_one_swipe);
                    if (tableLayout_single_tabel_one_swipe !=null) {
                        // TODO: 04.04.2023   NAME
                        TableRow1Name= (TableRow) tableLayout_single_tabel_one_swipe.findViewById(R.id.TableRow1Name);
                        TableRow2Name= (TableRow) tableLayout_single_tabel_one_swipe.findViewById(R.id.TableRow2Name);
                        TableRow3Name= (TableRow) tableLayout_single_tabel_one_swipe.findViewById(R.id.TableRow3Name);
                        TableRow4Name= (TableRow) tableLayout_single_tabel_one_swipe.findViewById(R.id.TableRow4Name);
                        TableRow5Name= (TableRow) tableLayout_single_tabel_one_swipe.findViewById(R.id.TableRow5Name);
                        TableRow6Name= (TableRow) tableLayout_single_tabel_one_swipe.findViewById(R.id.TableRow6Name);
                        TableRow7Name= (TableRow) tableLayout_single_tabel_one_swipe.findViewById(R.id.TableRow7Name);
                        TableRow8Name= (TableRow) tableLayout_single_tabel_one_swipe.findViewById(R.id.TableRow8Name);
                        // TODO: 26.06.2023
                        // TODO: 04.04.2023   Data
                        // TODO: 04.04.2023   Data
                        TableData1Row= (TableRow) tableLayout_single_tabel_one_swipe.findViewById(R.id.TableData1Row);
                        TableData2Row= (TableRow) tableLayout_single_tabel_one_swipe.findViewById(R.id.TableData2Row);
                        TableData3Row= (TableRow) tableLayout_single_tabel_one_swipe.findViewById(R.id.TableData3Row);
                        TableData4Row= (TableRow) tableLayout_single_tabel_one_swipe.findViewById(R.id.TableData4Row);
                        TableData5Row= (TableRow) tableLayout_single_tabel_one_swipe.findViewById(R.id.TableData5Row);
                        TableData6Row= (TableRow) tableLayout_single_tabel_one_swipe.findViewById(R.id.TableData6Row);
                        TableData7Row= (TableRow) tableLayout_single_tabel_one_swipe.findViewById(R.id.TableData7Row);
                        TableData8Row= (TableRow) tableLayout_single_tabel_one_swipe.findViewById(R.id.TableData8Row);
                        // TODO: 26.06.2023

                        if (TableData1Row!=null) {
                            linkedHashMapНазвание.put(TableRow1Name,TableData1Row);
                        }
                        if (TableData2Row!=null) {
                            linkedHashMapНазвание.put(TableRow2Name,TableData2Row);
                        }
                        if (TableData3Row!=null) {
                            linkedHashMapНазвание.put(TableRow3Name,TableData3Row);
                        }
                        if (TableData4Row!=null) {
                            linkedHashMapНазвание.put(TableRow4Name,TableData4Row);
                        }
                        if (TableData5Row!=null) {
                            linkedHashMapНазвание.put(TableRow5Name,TableData5Row);
                        }
                        if (TableData6Row!=null) {
                            linkedHashMapНазвание.put(TableRow6Name,TableData6Row);
                        }
                        if (TableData7Row!=null) {
                            linkedHashMapНазвание.put(TableRow7Name,TableData7Row);
                        }
                        if (TableData8Row!=null) {
                            linkedHashMapНазвание.put(TableRow8Name,TableData8Row);
                        }


                        // TODO: 26.06.2023Добавляем помопненты на экран
                        linkedHashMapsНазваниеиДанные.add(linkedHashMapНазвание);

                    }
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+" tableLayout_single_tabel_one_swipe   "
                            + tableLayout_single_tabel_one_swipe +
                            " tableLayout_single_tabel_one_swipe " + tableLayout_single_tabel_one_swipe);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(getContext().getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(),
                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }

        }

        @SuppressLint("Range")
        class MyRecycleViewAdapter extends RecyclerView.Adapter< MyViewHolder> {
            private Cursor cursor;
            public MyRecycleViewAdapter(@NotNull Cursor cursor) {
                try{
                    if (cursor!=null) {
                        this.cursor = cursor;
                    }
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ " cursor " +cursor );
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }

            @Override
            public void onBindViewHolder(@NonNull  MyViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull List<Object> payloads) {
                try {
                    // TODO: 23.06.2023
                    if (cursor!=null && cursor.getCount()>0 && !cursor.isClosed()) {
                        // TODO: 16.11.2023  Считаем Дни календаря
                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " cursorForViewPager " +cursor +
                                " getWorkerDays" + getWorkerDays + "getHolidaysDays  " +getHolidaysDays);






                        // TODO: 22.06.2023 loop Name ROW
                        getLoopNameRecycreViewSingleTable(holder, cursor);


                        messageRows.getTarget().post(()->{
                            // TODO: 22.06.2023 loop   Value
                            getLoopValueRecycreViewSingleTable(holder, cursor);
                        });

                        // TODO: 16.04.2023 Професии Професии Професии Професии
                        МетодаКликаTableRowФИО( );



                    }

                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " cursorForViewPager " +cursor +
                            " myViewHolder.getLayoutPosition() " +myViewHolder.getLayoutPosition());
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(getContext().getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(),
                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                super.onBindViewHolder(holder, position, payloads);
            }




            @Override
            public void setHasStableIds(boolean hasStableIds) {
                super.setHasStableIds(hasStableIds);
            }

            @Override
            public void onViewRecycled(@NonNull  MyViewHolder holder) {
                super.onViewRecycled(holder);
            }

            @Override
            public boolean onFailedToRecycleView(@NonNull  MyViewHolder holder) {
                return super.onFailedToRecycleView(holder);
            }

            @Override
            public void onViewAttachedToWindow(@NonNull  MyViewHolder holder) {
                super.onViewAttachedToWindow(holder);
            }

            @Override
            public void onViewDetachedFromWindow(@NonNull  MyViewHolder holder) {
                super.onViewDetachedFromWindow(holder);
            }

            @Override
            public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
                try{
                    recyclerView.removeAllViews();
                    recyclerView.getRecycledViewPool().clear();
                    super.onAttachedToRecyclerView(recyclerView);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(getContext().getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(),
                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }

            @Override
            public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
                super.onDetachedFromRecyclerView(recyclerView);
            }

            @Override
            public int getItemViewType(int position) {
                try {
                    Log.i(this.getClass().getName(), "   getItemViewType  position" + position);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(getContext().getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(),
                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                return super.getItemViewType(position);
            }

            @NonNull
            @Override
            @SuppressLint("Range")
            public  MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View viewSingleTabel = null;
                try {

                    if (cursor!=null) {
                        // TODO: 16.11.2023
                        if (cursor.getCount()>0) {
                 if (МЕсяцТабелей!=2) {
                     if (getWorkerDays.size()==31) {
                         viewSingleTabel = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_for_single_tabel_one_single_31day, parent, false);
                     } else {
                         if (getWorkerDays.size()==30) {
                             viewSingleTabel = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_for_single_tabel_one_single_30day, parent, false);
                         }
                     }



                     // TODO: 25.03.2025  только февряль
                            } else {
                     if (getWorkerDays.size()==28) {
                         viewSingleTabel = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_for_single_tabel_one_single_for_fefraly_28day, parent, false);
                     } else {
                         if (getWorkerDays.size()==29) {
                             viewSingleTabel = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_for_single_tabel_one_single_for_fefraly_29day, parent, false);
                         }
                     }
                 }
                            // TODO: 23.06.2023
                            Log.d(this.getClass().getName(),"\n" + " НЕт ДАнных class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+  "myViewHolder " +myViewHolder);
                        }else{
                            viewSingleTabel = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_isnull_single_tabel, parent, false);
                            // TODO: 23.06.2023
                            Log.d(this.getClass().getName(),"\n" + " НЕт ДАнных class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+  "myViewHolder " +myViewHolder);
                        }
                    }else {
                        viewSingleTabel = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_load_isnull_tabel, parent, false);
                    }

                    // TODO: 04.04.2023  Запускаем ПОлучений Вид View
                    myViewHolder = new  MyViewHolder(viewSingleTabel);
                    // TODO: 18.06.2023
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+  "myViewHolder " +myViewHolder+
                            myViewHolder.getLayoutPosition()+ myViewHolder.getAbsoluteAdapterPosition());

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(getContext().getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(),
                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                return myViewHolder;
            }


            @Override
            public void onBindViewHolder(@NonNull  MyViewHolder holder, int position) {
                try {
                    holder.itemView.startAnimation(animation1);
                    holder.itemView.refreshDrawableState();
                    holder.itemView.requestLayout();
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " cursorForViewPager " +cursor +
                            " myViewHolder.getLayoutPosition() " +myViewHolder.getLayoutPosition());
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(getContext().getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(),
                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }


            ///todo первый метод #1
            private void getLoopNameRecycreViewSingleTable(@NonNull  MyViewHolder holder, @NonNull Cursor cursor) {
                try {
// TODO: 26.06.2023 Цикл Крутим Нахвание и Данные Экрана
                    Flowable.fromIterable(holder.linkedHashMapsНазваниеиДанные)
                            .onBackpressureBuffer(1)
                            .doOnNext(new Consumer<LinkedHashMap<TableRow, TableRow>>() {
                                @Override
                                public void accept(LinkedHashMap<TableRow, TableRow> tableRowTableRowLinkedHashMap) throws Throwable {
                                    // TODO: 04.03.2025 NAME
                                    Flowable getName=     Flowable.fromIterable(tableRowTableRowLinkedHashMap.keySet())
                                            .onBackpressureBuffer(1)
                                            .doOnNext(new Consumer<TableRow>() {
                                                @Override
                                                public void accept(TableRow tableRowДанныеШабка) throws Throwable {
                                                    // TODO: 04.03.2025
                                                    // TODO: 04.04.2023   Name ROW
                                                    МетодЗаполняеШабкаTableRow(cursor  ,tableRowДанныеШабка);
                                                    // TODO: 26.06.2023  поднимае версию
                                                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

                                                }
                                            }) ;

                                    getName.subscribe();
                                    // TODO: 26.06.2023  поднимае версию
                                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                                }
                            }) .doOnComplete(new Action() {
                                @Override
                                public void run() throws Throwable {
                                    // TODO: 26.06.2023  поднимае версию
                                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                                }
                            }).subscribe();


                    // TODO: 26.06.2023
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(getContext().getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(),
                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }

            ///todo первый метод #2
            private void getLoopValueRecycreViewSingleTable(@NonNull  MyViewHolder holder, @NonNull Cursor cursor) {
                try {
// TODO: 26.06.2023 Цикл Крутим Нахвание и Данные Экрана
                    Flowable.fromIterable(holder.linkedHashMapsНазваниеиДанные)
                            .onBackpressureBuffer(1)
                            .doOnNext(new Consumer<LinkedHashMap<TableRow, TableRow>>() {
                                @Override
                                public void accept(LinkedHashMap<TableRow, TableRow> tableRowTableRowLinkedHashMap) throws Throwable {
                                    // TODO: 04.03.2025 NAME
                                    // TODO: 04.03.2025 VALUE
                                    Flowable getValue=  Flowable.fromIterable(tableRowTableRowLinkedHashMap.values())
                                            .onBackpressureBuffer(1)
                                            .doOnNext(new Consumer<TableRow>() {
                                                @Override
                                                public void accept(TableRow tableRowДанные) throws Throwable {
                                                    // TODO: 04.03.2025
                                                    // TODO: 04.04.2023   DATA ROW
                                                    МетодЗаполняемДаннымиTableRow(cursor  ,tableRowДанные);

                                                    // TODO: 26.06.2023  поднимае версию
                                                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

                                                }
                                            });

                                    getValue.subscribe();
                                    // TODO: 26.06.2023  поднимае версию
                                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                                }
                            }) .doOnComplete(new Action() {
                                @Override
                                public void run() throws Throwable {
                                    // TODO: 26.06.2023  поднимае версию
                                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                                }
                            }).subscribe();


                    // TODO: 26.06.2023
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(getContext().getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(),
                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }


            private void МетодЗаполняемДаннымиTableRow(@NonNull Cursor cursor, @NonNull  TableRow tableRowДанные) {
                try {
                    if (tableRowДанные!=null) {
                        // TODO: 06.04.2023  СОДЕРДИМОЕ ROW
                        Flowable.range(0,tableRowДанные.getChildCount())
                                .doOnNext(new Consumer<Integer>() {
                                    @Override
                                    public void accept(Integer ПозицияДняДаннымиTableRow) throws Throwable {
                                        EditText editTextRowКликПоДАнными = (EditText) tableRowДанные.getChildAt(ПозицияДняДаннымиTableRow);
                                        String ДнейСодержимое =            editTextRowКликПоДАнными.getTooltipText().toString();
                                        // TODO: 05.04.2023  ЗАПОЛЯНИЕМ ДНЯМИ ROW 1
                                        if (getWorkerDays.containsKey(ДнейСодержимое.trim())) {
                                            String ВыходныеИлиПразничные = getWorkerDays.get(ДнейСодержимое.trim());
                                            // TODO: 05.03.2025  fill to data
                                            методЗаполениеСодеримомRowData(editTextRowКликПоДАнными, cursor, ДнейСодержимое);

                                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                                    + " editTextRowКликПоДАнными " + editTextRowКликПоДАнными + " ДнейСодержимое " + ДнейСодержимое);


                                            // TODO: 05.03.2025  Disain
                                           fillInDesignfordata(  editTextRowКликПоДАнными,ВыходныеИлиПразничные);

                                            // TODO: 10.05.2023 Сохранение ДАнных Ячейки
                                            МетодаСохранениеДанныхЯчейкиRow(editTextRowКликПоДАнными);
                                            // TODO: 19.10.2022 Переход на Метки Табеля
                                            методПереходНаМеткиТАбедяcRow(editTextRowКликПоДАнными);
                                            // TODO: 05.04.2023 Иниуиализация Клавиаьтуры Поднятие для кажой Ячейки
                                            методИницаллизацииКлавиаотурыЯчейка(editTextRowКликПоДАнными);


                                            // TODO: 10.05.2023
                                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                                        }else {

                                            editTextRowКликПоДАнными.setVisibility(View.GONE);
                                            // TODO: 10.05.2023
                                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                                        }
                                    }
                                }).doOnError(new Consumer<Throwable>() {
                                    @Override
                                    public void accept(Throwable throwable) throws Throwable {
                                        throwable.printStackTrace();
                                        Log.e(getContext().getClass().getName(),
                                                "Ошибка " +  throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                        new RecordNewErros(getContext()).recordnewerror(throwable.toString(),
                                                this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    }
                                }).doOnComplete(new Action() {
                                    @Override
                                    public void run() throws Throwable {
                                        // TODO: 10.05.2023
                                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                                    }
                                }).blockingSubscribe();


                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " cursorForViewPager  " + cursor);
                        // TODO: 10.05.2023  Слушатель ПО ЯЧЕЙКАМ
                        // TODO: 19.10.2022
                    }
                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " cursorForViewPager  " + cursor);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(getContext().getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(),
                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }



            private void МетодЗаполняеШабкаTableRow(@NonNull Cursor cursor, @NonNull  TableRow tableRowНазвания) {
                try {
                    if (tableRowНазвания!=null) {
                        // TODO: 04.03.2025
                        Flowable.range(0,tableRowНазвания.getChildCount())
                                .onBackpressureBuffer()
                                .doOnNext(new io.reactivex.rxjava3.functions.Consumer<Integer>() {
                            @SuppressLint("NewApi")
                            @Override
                            public void accept(Integer ПозицияДняШабкаTableRow) throws Throwable {
                                // TODO: 04.03.2025
                                TextView textViewНазвание = (TextView) tableRowНазвания.getChildAt(ПозицияДняШабкаTableRow);
                                String ДнейНазвание =            textViewНазвание.getHint().toString();
                                // TODO: 05.04.2023  ЗАПОЛЯНИЕМ ДНЯМИ ROW 1
                                if (getWorkerDays.containsKey(ДнейНазвание.trim())) {
                                    String ВыходныеИлиПразничные=    getWorkerDays.get(ДнейНазвание.trim());
                                    ВыходныеИлиПразничные =          методЗаполениеНазванияRowData(textViewНазвание, ДнейНазвание);

                                    // TODO: 26.06.2023 Цветом Оформлем
                                    fillDatatemplatewithcolor(textViewНазвание,ВыходныеИлиПразничные);

                                }else {

                                    textViewНазвание.setVisibility(View.GONE);
                                    // TODO: 10.05.2023
                                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                                }
                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                        + " textViewНазвание " + textViewНазвание + " ДнейНазвание " + ДнейНазвание);
                                // TODO: 26.06.2023
                            }
                        }).doOnError(new io.reactivex.rxjava3.functions.Consumer<Throwable>() {
                                    @Override
                                    public void accept(Throwable throwable) throws Throwable {
                                        throwable.printStackTrace();
                                        Log.e(getContext().getClass().getName(),
                                                "Ошибка " +throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                        new RecordNewErros(getContext()).recordnewerror(throwable.toString(),
                                                this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    }
                                }).doOnComplete(new Action() {
                                    @Override
                                    public void run() throws Throwable {
                                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " cursorForViewPager  " + cursor);
                                    }
                                })
                                .blockingSubscribe();
                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " cursorForViewPager  " + cursor);
                        // TODO: 10.05.2023  Слушатель ПО ЯЧЕЙКАМ
                        // TODO: 19.10.2022
                    }


                    // TODO: 19.10.2022
                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " cursorForViewPager  " + cursor);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(getContext().getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(),
                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }
            private void методЗаполениеСодеримомRowData(@NonNull  EditText editTextRowКликПоДАнными,
                                                        @NonNull Cursor cursor,
                                                        @NonNull String НазваниеДляДень) {
                try {
                    Bundle dataRowData = null;
                    String День =  cursor.getString(cursor.getColumnIndex(НазваниеДляДень)) ;
                    if(День==null ||День.equalsIgnoreCase("0")) {
                        День=new String();
                    }
                    CurrenrsСhildUUID= Optional.ofNullable(cursor.getLong(cursor.getColumnIndex("uuid"))).orElse(0l);
                    dataRowData = new Bundle();
                    dataRowData.putString("ЗначениеДня", День);
                    dataRowData.putLong("uuid", CurrenrsСhildUUID);
                    dataRowData.putString("День", НазваниеДляДень);
                    // TODO: 13.04.2023  дополнительные
                    dataRowData.putLong("MainParentUUID", MainParentUUID);
                    dataRowData.putInt("listpeoplesposition",getlistpeoplesposition );
                    dataRowData.putInt("ГодТабелей",   ГодТабелей);
                    dataRowData.putInt("МЕсяцТабелей", МЕсяцТабелей);
                    dataRowData.putInt("DigitalNameCFO", DigitalNameCFO);
                    dataRowData.putString("FullNameCFO", FullNameCFO);
                    dataRowData.putString("ИмесяцвИГодСразу", ИмесяцвИГодСразу);
                    dataRowData.putLong("CurrenrsСhildUUID", CurrenrsСhildUUID);
                    dataRowData.putString("ФИО", ФИО);
                    dataRowData.putLong("CurrenrsSelectFio", CurrenrsSelectFio);
                    editTextRowКликПоДАнными.setVisibility(View.VISIBLE);
                    editTextRowКликПоДАнными.setTag(dataRowData);
                    editTextRowКликПоДАнными.setText(День.trim());
                    editTextRowКликПоДАнными.requestLayout();
                    // TODO: 07.06.2023
                    методИзменяемЦветСодержимоваЦифраИлиБуква(editTextRowКликПоДАнными, День);


                    // TODO: 19.10.2022
                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " НазваниеДляДень "
                            + НазваниеДляДень + "dataRowData " + dataRowData);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(getContext().getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(),
                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }

            private void методИзменяемЦветСодержимоваЦифраИлиБуква(@NonNull EditText editTextRowКликПоДАнными,@NonNull String День) {
                try {
                    if (День.length()>0) {
                        char ЕслиБуквывДнеСодержимое=     День.charAt(0);
                        if(Character.isLetter( ЕслиБуквывДнеСодержимое )){
                        editTextRowКликПоДАнными.setTextColor(Color.parseColor("#887B7B"));
                        editTextRowКликПоДАнными.setTextSize(19l);
                        }else {
                            editTextRowКликПоДАнными.setTextColor(Color.BLACK);
                            editTextRowКликПоДАнными.setTextSize(18l);
                        }
                    }
                    editTextRowКликПоДАнными.refreshDrawableState();
                    editTextRowКликПоДАнными.requestLayout();
                    // TODO: 19.10.2022
                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " НазваниеДляДень ");
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(getContext().getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(),
                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }

            private String методЗаполениеНазванияRowData(@NonNull  TextView TextViewRowКликПоНазваниям,String s) {
                String ВыходныеИлиПразничные = null;
                try {
                    // TODO: 11.04.2023 Ставим Дни
                    ВыходныеИлиПразничные=    getWorkerDays.get(s.trim());
                    TextViewRowКликПоНазваниям.setVisibility(View.VISIBLE);
                    TextViewRowКликПоНазваниям.setText( ВыходныеИлиПразничные);
                    TextViewRowКликПоНазваниям.requestLayout();
                    // TODO: 19.10.2022
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ " s.trim() " +s.trim() + "  getWorkerDays.get(s.trim()) "
                            + getWorkerDays.get(s.trim()));
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(getContext().getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(),
                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                return ВыходныеИлиПразничные;
            }






            // TODO: 07.06.2023 цвет
            private void fillDatatemplatewithcolor(@NonNull  TextView TextViewRowКликПоНазваниям, String ВыходныеИлиПразничные) {
                try {
                    // TODO: 11.04.2023 Ставим Дни Празничные
                    if ( getHolidaysDays.containsValue(ВыходныеИлиПразничные.trim())==true) {

                        if (TextViewRowКликПоНазваниям.isEnabled()) {
                            Drawable drawableup = getContext().getDrawable(R.drawable.style_for_chat4);
                            TextViewRowКликПоНазваниям.setBackground(drawableup);
                            TextViewRowКликПоНазваниям.setTextColor(Color.WHITE);
                            TextViewRowКликПоНазваниям.startAnimation(animation1);
                            TextViewRowКликПоНазваниям.refreshDrawableState();
                            TextViewRowКликПоНазваниям.requestLayout();

                        }
                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "  ВыходныеИлиПразничные "
                                + ВыходныеИлиПразничные);

                    }


                    // TODO: 19.10.2022
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+   "  ВыходныеИлиПразничные "
                            + ВыходныеИлиПразничные);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(getContext().getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(),
                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }


            private void fillInDesignfordata(@NonNull  EditText EditTextRowДанные, String ВыходныеИлиПразничные) {
                try {
                    // TODO: 11.04.2023 Ставим Дни Празничные
                    if ( getWorkerDays.containsValue(ВыходныеИлиПразничные.trim())==true) {

                        if (EditTextRowДанные.isEnabled()) {
                            EditTextRowДанные.startAnimation(animationFromRecyReview);
                            Drawable drawableup = getContext().getDrawable(R.drawable.style_for_chat7);
                            EditTextRowДанные.setBackground(drawableup);
                            EditTextRowДанные.refreshDrawableState();
                            EditTextRowДанные.requestLayout();
                        }
                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+   "  ВыходныеИлиПразничные "
                                + ВыходныеИлиПразничные);



                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+   "  ВыходныеИлиПразничные "
                                + ВыходныеИлиПразничные);
                    }


                    // TODO: 19.10.2022
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+   "  ВыходныеИлиПразничные "
                            + ВыходныеИлиПразничные);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(getContext().getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(),
                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }




























            // TODO: 08.11.2022 метод КЛИК ПО ДАННЫМ
            private void МетодаСохранениеДанныхЯчейкиRow(@NonNull   EditText editTextRowКликПоДАнными  ) {
                try{

                    if (editTextRowКликПоДАнными!=null) {


                                // TODO: 25.08.2023  тест код
                                disposableAfterTextChangeEvent.add(      RxTextView.afterTextChangeEvents( editTextRowКликПоДАнными)
                                        .skip(1)
                                        .debounce(500,TimeUnit.MILLISECONDS)///общее время event
                                        .filter(edit->edit.component1().getText().toString().length()<=2)
                                        .subscribeOn(Schedulers.single())
                                        .observeOn(Schedulers.single())
                                                .doOnError(new Consumer<Throwable>() {
                                                    @Override
                                                    public void accept(Throwable throwable) throws Throwable {
                                                        throwable.printStackTrace();
                                                        Log.e(getContext().getClass().getName(),
                                                                "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                                        new RecordNewErros(getContext()).recordnewerror(throwable.toString(),
                                                                this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                                                    }
                                                })
                                        .distinct().subscribe(new Consumer<TextViewAfterTextChangeEvent>() {
                                            @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
                                            @Override
                                            public void accept(TextViewAfterTextChangeEvent textViewAfterTextChangeEvent)
                                                    throws Throwable {
                                                // TODO: 05.05.2025
                                                try{

                                                if(textViewAfterTextChangeEvent.component1().isInputMethodTarget()){


                                                    // TODO: 28.03.2025
                                                    textViewAfterTextChangeEvent.getView().getHandler().post(()->{

                                                        String   НовоеЗначенияДня   =(String)
                                                                textViewAfterTextChangeEvent.component1().getText().toString();
                                                        НовоеЗначенияДня=   НовоеЗначенияДня.replaceAll("[^0-9]","").trim();


                                                          Long getNewValueCell=     Optional.ofNullable(НовоеЗначенияДня).stream()
                                                                .filter(f1->f1!=null)
                                                                /*  .filter(f3->f3.chars().allMatch( Character::isDigit ))*/
                                                                .mapToInt(new ToIntFunction<String>() {
                                                                    @Override
                                                                    public int applyAsInt(String value) {
                                                                        Integer getNewValue= 0;
                                                                        if (!value.isEmpty()) {
                                                                            getNewValue = Integer.valueOf(value.replaceAll("[^0-9]","").trim());
                                                                        }
                                                                        // TODO: 24.08.2023
                                                                        Log.d(this.getClass().getName(), "\n" + " class " +
                                                                                Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()
                                                                                + "\n"+" value " +value );
                                                                        return getNewValue;
                                                                    }
                                                                }).asLongStream().findAny().orElse(0l);


                                                        Integer     РезультатОбновлениеЯчейки=
                                                                методListerAfterSaveNewDay (editTextRowКликПоДАнными,getNewValueCell );


                                                        // TODO: 24.08.2023
                                                        Log.d(this.getClass().getName(), "\n" + " class " +
                                                                Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()
                                                                + "\n"+" РезультатОбновлениеЯчейки " +РезультатОбновлениеЯчейки );

                                                    });

                                                }
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                                Log.e(getContext().getClass().getName(),
                                                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                                                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                                            }
                                            }
                                        }));
                            }




                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(getContext().getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(),
                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }

            // TODO: 24.08.2023 метод слушатель с последующим запись
            Integer  методListerAfterSaveNewDay (@NonNull EditText editTextRowКликПоДАнными,@NonNull Long getNewValueCell){
                // TODO: 04.03.2025
                Integer РезультатОбновлениеЯчейки=0;
                try{
                        // TODO: 11.04.2023 Оперция Обновлнения ЯЧЕЕК
                        SubClassUpdatesCELL subClassUpdateSingletabel = new SubClassUpdatesCELL(getContext());
                        // TODO: 10.05.2023  ЗАВПИСЫАЕМ НОВЫЕ ДАННЫВЕ В БАЗУ
                          РезультатОбновлениеЯчейки = subClassUpdateSingletabel.МетодВалидацияЯчеекSaveCell(editTextRowКликПоДАнными,getNewValueCell);
                        // TODO: 10.05.2023 После операции Сохранение в Ячкейке
                        if (РезультатОбновлениеЯчейки > 0) {
                            // TODO: 06.07.2023 Считаем ЧАсы
                            методRefrefyGetDataRecycreView();
                            методСчитаемЧасы(myRecycleViewAdapter.cursor,myRecycleViewAdapter.cursor.getPosition());

                            new SubClassSingleTabelRecycreView().МетодЗаполняемФИОиПрофесиюRow(cursorSingleTabels);
                                 message.getTarget().postDelayed(()->{
                                     editTextRowКликПоДАнными.startAnimation(animation1);
                                 },50);

                            // TODO: 19.06.2023 код когда данные в ячейке не сохранились
                        } else {
                            методКогдаДанныеНеСохранились(editTextRowКликПоДАнными, getNewValueCell.toString());

                        }
                        // TODO: 24.04.2023  после обновление ячейки Считаем Часы
                        методИзменяемЦветСодержимоваЦифраИлиБуква((editTextRowКликПоДАнными), getNewValueCell.toString());

                    Log.d(this.getClass().getName(), "\n" + " class " +
                            Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " РезультатОбновлениеЯчейки"+РезультатОбновлениеЯчейки);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(getContext().getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(),
                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
      return  РезультатОбновлениеЯчейки;


            }



            private void методПереходНаМеткиТАбедяcRow(@NonNull EditText editTextRowКликПоДАнными) {
                try{
                    editTextRowКликПоДАнными.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            try{
                                // TODO: 19.10.2022  переход на метки табеля
                                МетодПереходаНаМеткиТабеля( (EditText) v);
                                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e(getContext().getClass().getName(),
                                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                            return true;
                        }
                    });
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(getContext().getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(),
                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }

            // TODO: 21.04.2023  записб данных в ячейку


            private void методRefrefyGetDataRecycreView() {
                try{
                    Integer Полощения=  myRecycleViewAdapter.cursor.getPosition();
                    Cursor     cursorForЧАсов=     myRecycleViewAdapter.cursor;
                    cursorForЧАсов=    new GetCursorSingleTabel().МетодSwipesКурсор();
                    cursorForЧАсов.moveToPosition(Полощения);
                    myRecycleViewAdapter.cursor=cursorForЧАсов;
                    Log.d(this.getClass().getName(), "\n" + "Start Update D1 class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }



            private void методКогдаДанныеНеСохранились(@NonNull EditText editTextRowКликПоДАнными,
                                                       @NonNull String После) {
                try{
                    Vibrator v2 = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
                    v2.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));

                    editTextRowКликПоДАнными.setError(После);
                    message.getTarget().postDelayed(()->{
                        editTextRowКликПоДАнными.setError(null);
                },5000);
                Log.d(this.getClass().getName(), "\n" + "Start Update D1 class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            }



            @Override
            public long getItemId(int position) {
                // TODO: 04.03.2022
                Log.i(this.getClass().getName(), "     getItemId holder.position " + position);
                return super.getItemId(position);
            }
            @Override
            public int getItemCount() {
                int КоличесвоСтрок=1;
                try {
                    ///КоличесвоСтрок =getWorkerDays.size();
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " КоличесвоСтрок "+КоличесвоСтрок);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(getContext().getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(),
                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                return КоличесвоСтрок;
            }
        }

        //TODO Перерождения Данных recycreView Отдельный Класс
        public   class getLeftRecyreVieData {
            void методПереРоденияRevireViewScroll (@NonNull     Cursor cursorSwipeViewPager) {
                try{
                    // TODO: 15.06.2023 Scroll Left RecyreView
                    singleTabelRecycreView. методЗакрываемКлавитатуру();
                    spinnerdepartament.setClickable(false);
                    spinnerdepartament.setFocusable(false);

                    Vibrator v2 = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
                    v2.vibrate(VibrationEffect.createOneShot(65, VibrationEffect.EFFECT_HEAVY_CLICK));
                        // TODO: 22.06.2023
                        fragmentSingleTabel.new SubClassBungleSingle().методGETДанныеRunTimeИзCursor(cursorSwipeViewPager );
                        // TODO: 16.06.2023  после переполуение данныз перегрузка экрана


                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+"CurrentFragmentMaxItem   " + CurrentFragmentMaxItem +
                                " oldScrollY ");

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(getContext().getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(),
                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }

            }

            // TODO: 30.11.2023 class for swile








        }

        class SubClassSearchProfessia {
            private  MaterialButton materialButtonЗакрываемПосик;
            private androidx.appcompat.widget.SearchView searchViewДляНовогоПоиска;
            private  AlertDialog      alertDialogНовыйПосик;
            private ListView listViewДляНовыйПосик;
            private    Cursor  КурсорТаблицаПрофесии;
            @UiThread
            private void МетодСообщениеНовыйПоиска(@NonNull Activity activity
                    ,@NonNull Cursor КурсорТаблицаПрофесии,
                                                   @NonNull Message message
                    ,@NonNull String ТаблицаПосика,
                                                   @NonNull Long РодительскийUUDТаблицыТабель) {
                try{

                    alertDialogНовыйПосик= new MaterialAlertDialogBuilder(activity){
                        @NonNull
                        @Override
                        public MaterialAlertDialogBuilder setView(View view) {
                            listViewДляНовыйПосик =    (ListView) view.findViewById(R.id.SearchViewList);
                            listViewДляНовыйПосик.setTextFilterEnabled(true);
                            searchViewДляНовогоПоиска=    (SearchView) view.findViewById(R.id.searchview_newscanner);
                            searchViewДляНовогоПоиска.setQueryHint("Поиск");
                            // TODO: 14.12.2022




                            TextView textViewСтрокаПосика = searchViewДляНовогоПоиска.findViewById(com.google.android.material.R.id.search_src_text);




                            textViewСтрокаПосика.setTextColor(Color.rgb(0,102,102));
                            textViewСтрокаПосика.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                            // TODO: 14.12.2022
                            materialButtonЗакрываемПосик =    (MaterialButton) view.findViewById(R.id.bottom_newscanner1);
                            materialButtonЗакрываемПосик.setText("Закрыть");
                            ///TODO ГЛАВНЫЙ АДАПТЕР чата
                            SimpleCursorAdapter simpleCursorAdapterЦФО= new SimpleCursorAdapter(getContext(),
                                    R.layout.simple_newspinner_dwonload_newfiltersearch, КурсорТаблицаПрофесии,
                                    new String[]{ "name","_id"},
                                    new int[]{android.R.id.text1,android.R.id.text1}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);//R.layout.simple_newspinner_dwonload_cfo2
                            SimpleCursorAdapter.ViewBinder БиндингДляНовогоПоиска = new SimpleCursorAdapter.ViewBinder(){
                                @Override
                                public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                                    switch (view.getId()) {
                                        case android.R.id.text1:
                                            Log.d(this.getClass().getName()," position");
                                            if (cursor.getCount()>0) {
                                                try{
                                                    Integer ИндексНазваниеЦФО = cursor.getColumnIndex("name");///user_update  --old/// uuid
                                                    String НазваниеПрофесии = cursor.getString(ИндексНазваниеЦФО);
                                                    // TODO: 13.12.2022  производим состыковку
                                                    Integer ИндексНазваниеПрофесииID = cursor.getColumnIndex("uuid");///user_update  --old/// uuid
                                                    Integer ПолучаемIDПрофессии = cursor.getInt(ИндексНазваниеПрофесииID);
                                                    if (ПолучаемIDПрофессии>0) {
                                                        Integer UUIDПрофессии = cursor.getColumnIndex("uuid");///user_update  --old/// uuid
                                                        Long UUIDПрофесиии = cursor.getLong(UUIDПрофессии);
                                                        Bundle bundle=new Bundle();
                                                        bundle.putInt("ПолучаемIDПрофессии",ПолучаемIDПрофессии);
                                                        bundle.putString("НазваниеПрофесии",НазваниеПрофесии);
                                                        bundle.putLong("UUIDПрофесиии",UUIDПрофесиии);
                                                        bundle.putLong("CurrenrsСhildUUID",РодительскийUUDТаблицыТабель);
                                                  /*  UUIDТекущегоВыбраногоСотрудника=      ГлавныйALLКурсорДанныеSwipes.getLong(ГлавныйALLКурсорДанныеSwipes.getColumnIndex("uuid"));
                                                    bundle.putLong("UUIDТекущегоВыбраногоСотрудника",UUIDТекущегоВыбраногоСотрудника);*/
                                                        ((MaterialTextView)view).setTag(bundle);
                                                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                                                + " bundle"+bundle );
                                                    }
                                                    // TODO: 20.01.2022
                                                    Log.d(this.getClass().getName()," НазваниеЦФО "+НазваниеПрофесии);
                                                    boolean ДлинаСтрокивСпиноре = НазваниеПрофесии.length() >40;
                                                    if (ДлинаСтрокивСпиноре) {
                                                        StringBuffer sb = new StringBuffer(НазваниеПрофесии.trim());
                                                        sb.insert(40, System.lineSeparator());
                                                        НазваниеПрофесии = sb.toString();
                                                        Log.d(getContext().getClass().getName(), " НазваниеПрофесии " + "--" + НазваниеПрофесии);/////
                                                    }
                                                    ((MaterialTextView)view).setText(НазваниеПрофесии);
                                                    // TODO: 29.03.2023
                                                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                                            + " НазваниеПрофесии"+НазваниеПрофесии);
                                                    // TODO: 13.12.2022 слушатель
                                                    ((MaterialTextView)view).setOnClickListener(new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View v) {
                                                            try{
                                                                Bundle bundle=(Bundle)   ((MaterialTextView)view).getTag();
                                                                String НазваниеПрофесии = null;
                                                                if (bundle!=null) {
                                                                    Integer ПолучаемIDПрофессии=      bundle.getInt("ПолучаемIDПрофессии",0);
                                                                    НазваниеПрофесии = bundle.getString("НазваниеПрофесии","");
                                                                    Long UUIDПрофесиии =   bundle.getLong("UUIDПрофесиии",0l);
                                                                    Long CurrenrsСhildUUID =   bundle.getLong("CurrenrsСhildUUID",0l);
                                                                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                                                            + " ПолучаемIDПрофессии "+ПолучаемIDПрофессии + " НазваниеЦФО " +НазваниеПрофесии + " UUIDПрофесиии " +UUIDПрофесиии+
                                                                            " CurrenrsСhildUUID " +РодительскийUUDТаблицыТабель);
                                                                    searchViewДляНовогоПоиска.setTag(bundle);
                                                                    searchViewДляНовогоПоиска.setQueryHint("");
                                                                    searchViewДляНовогоПоиска.setQuery(НазваниеПрофесии,true);
                                                                    ((MaterialTextView)view).setTag(bundle);
                                                                }


                                                                if (  searchViewДляНовогоПоиска.getQuery().toString().length()==0) {
                                                                    Snackbar.make(view, " Вы не выбрали  !!! "
                                                                            , Snackbar.LENGTH_LONG).show();
                                                                    ((MaterialTextView)view).setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                                                                    ((MaterialTextView)view).setTextColor(Color.GRAY);
                                                                    Log.d(this.getClass().getName()," bundle.keySet().size() "+bundle.keySet().size());
                                                                } else {
                                                                    ((MaterialTextView)view).setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                                                                    ((MaterialTextView)view).setTextColor(Color.BLACK);
                                                                    Log.d(this.getClass().getName()," bundle.keySet().size() "+bundle.keySet().size());

                                                                    if (  searchViewДляНовогоПоиска.getQuery().toString().length()>5 ) {
                                                                        searchViewДляНовогоПоиска.setQuery("",true);
                                                                        searchViewДляНовогоПоиска.refreshDrawableState();
                                                                        // TODO: 21.07.2023  меняем Професию
                                                                        Integer ПровйдерСменаПрофесии=
                                                                                МетодЗаписиСменыПрофесии( (SearchView)  searchViewДляНовогоПоиска,getActivity());



                                                                        if (ПровйдерСменаПрофесии>0) {
                                                                            // TODO: 27.05.2025
                                                                            new SetFioAndProfessionDisayn(0).методПерегрузкаВидаПрофесии(НазваниеПрофесии , materialTextViewprofession);

                                                                            onStart();
                                                                            onResume();

                                                                            alertDialogНовыйПосик.dismiss();
                                                                            alertDialogНовыйПосик.cancel();




                                                                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                                                                    + " ПровйдерСменаПрофесии "+ ПровйдерСменаПрофесии+"\n+");



                                                                        }else {
                                                                            Toast.makeText(getActivity(), "Профессия не сменилась !!! ", Toast.LENGTH_SHORT).show();
                                                                        }
                                                                        // TODO: 27.05.2025
                                                                    }
                                                                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                                                            + " listViewДляНовыйПосик "+ listViewДляНовыйПосик+"\n+"+
                                                                            " cursorДанные " + КурсорТаблицаПрофесии +"\n");

                                                                }

                                                                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                                                            } catch (Exception e) {
                                                                e.printStackTrace();
                                                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                                                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                                                                        this.getClass().getName(),
                                                                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                                            }
                                                        }

                                                    });
                                                    // TODO: 13.12.2022 филь
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                                    new RecordNewErros(getContext()).recordnewerror(e.toString(),
                                                            this.getClass().getName(),
                                                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                                }
                                                return true;
                                            } else {
                                                Log.d(this.getClass().getName()," position");
                                                return false;
                                            }
                                    }
                                    return false;
                                }
                            };
                            simpleCursorAdapterЦФО.setViewBinder(БиндингДляНовогоПоиска);
                            simpleCursorAdapterЦФО.notifyDataSetChanged();
                            listViewДляНовыйПосик.setAdapter(simpleCursorAdapterЦФО);
                            listViewДляНовыйПосик.startAnimation(animation1);
                            listViewДляНовыйПосик.setSelection(0);
                            listViewДляНовыйПосик.forceLayout();

                            // TODO: 13.12.2022  Поиск и его слушель
                            МетодПоискаФильтрНовыйПосик(searchViewДляНовогоПоиска,simpleCursorAdapterЦФО,message,listViewДляНовыйПосик,ТаблицаПосика);
                            // TODO: 13.12.2022 Закрыаем Посик Профессий
                            методЗакрываемПосикПровфесиий(materialButtonЗакрываемПосик);
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                    + " listViewДляНовыйПосик "+listViewДляНовыйПосик+"\n+"+
                                    " КурсорТаблицаПрофесии " +КурсорТаблицаПрофесии+"\n");
                            return super.setView(view);
                        }
                    }
                            .setTitle("Профессии")
                            .setCancelable(false)
                            .setIcon( R.drawable.icon_newscannertwo)
                            .setView(getLayoutInflater().inflate( R.layout.simple_for_new_spinner_searchview, null ))
                            .show();
                    WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                    layoutParams.copyFrom(   alertDialogНовыйПосик.getWindow().getAttributes());
                    layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                    layoutParams.height =WindowManager.LayoutParams.MATCH_PARENT;
                    layoutParams.gravity = Gravity.CENTER;
                    alertDialogНовыйПосик.getWindow().setAttributes(layoutParams);
                    alertDialogНовыйПосик.setTitle("Профессии "+"("+String.valueOf(КурсорТаблицаПрофесии.getCount()+")"));

                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " listViewДляНовыйПосик "+listViewДляНовыйПосик+"\n+");
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(),
                            this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }








            private void методЗакрываемПосикПровфесиий(@NonNull MaterialButton materialButtonЗакрываемПосик) {
                // TODO: 21.07.2023
                materialButtonЗакрываемПосик.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            Log.d(this.getClass().getName(), " position");
                            Log.d(this.getClass().getName(), "МетодСозданиеТабеля  v " + v);
                            // TODO: 28.03.2023 ЗАПИСЫВАЕМ НОВУЮ ПРОФЕССИЮ В БАЗУ
                            alertDialogНовыйПосик.dismiss();
                            alertDialogНовыйПосик.cancel();
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(getContext()).recordnewerror(e.toString(),
                                    this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }
                });
            }
            // TODO: 27.03.2023  ВТОРОЙ МЕТОД ПОИСККА ПО БАЗЕ

            private void МетодПоискаФильтрНовыйПосик(@NonNull  androidx.appcompat.widget.SearchView searchViewДляНовогоЦФО,
                                                     @NonNull SimpleCursorAdapter simpleCursorAdapterЦФО,
                                                     @NonNull Message message,
                                                     @NonNull ListView listViewДляНовыйПосик
                    ,@NonNull String ТаблицаПосика) {
                try{
                    searchViewДляНовогоЦФО.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                        @Override
                        public boolean onQueryTextSubmit(String query) {
                            Log.d(this.getClass().getName()," position");
                            return false;
                        }

                        @Override
                        public boolean onQueryTextChange(String newText) {
                            Log.d(this.getClass().getName()," position");
                            Filter filter= simpleCursorAdapterЦФО.getFilter();
                            filter.filter(newText);
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                    + " filter "+filter.toString() );
                            return true;
                        }
                    });

                    searchViewДляНовогоЦФО.setOnCloseListener(new SearchView.OnCloseListener() {
                        @Override
                        public boolean onClose() {
                            // TODO: 05.02.2025
                            try{
                                if (КурсорТаблицаПрофесии!=null) {
                                    КурсорТаблицаПрофесии=      МетодКурсорДляНовогоПосика( null);

                                    startswapCursor(simpleCursorAdapterЦФО,  listViewДляНовыйПосик,alertDialogНовыйПосик);
                                }

                                alertDialogНовыйПосик.cancel();

                                Log.d(this.getClass().getName(), "\n" + " class " +
                                        Thread.currentThread().getStackTrace()[2].getClassName()
                                        + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                        + "    КурсорТаблицаПрофесии " +КурсорТаблицаПрофесии);

                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                                        this.getClass().getName(),
                                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                                return false;
                        }
                    });




                    simpleCursorAdapterЦФО.setFilterQueryProvider(new FilterQueryProvider() {
                        @Override
                        public Cursor runQuery(CharSequence constraint) {
                            Log.d(this.getClass().getName()," constraint"  +constraint);
                            try{
                                КурсорТаблицаПрофесии=      МетодКурсорДляНовогоПосика(constraint.toString());
                                message.getTarget().post(()->{
                                    if (КурсорТаблицаПрофесии.getCount()>0 && constraint.length()>0) {
                                        // TODO: 05.02.2025
                                        startswapCursor(simpleCursorAdapterЦФО,  listViewДляНовыйПосик,alertDialogНовыйПосик);


                                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+" КурсорТаблицаПрофесии " +КурсорТаблицаПрофесии+"\n" +" constraint"  +constraint);

                                    }else {

                                            searchViewДляНовогоЦФО.setBackgroundColor(Color.RED);
                                        startswapCursor(simpleCursorAdapterЦФО,  listViewДляНовыйПосик,alertDialogНовыйПосик);
                                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+" КурсорТаблицаПрофесии " +КурсорТаблицаПрофесии+"\n" +" constraint"  +constraint);

                                            message.getTarget().postDelayed(() -> {
                                                searchViewДляНовогоЦФО.setBackgroundColor(Color.parseColor("#F2F5F5"));
                                                // TODO: 05.02.2025

                                                КурсорТаблицаПрофесии=      МетодКурсорДляПрофессииБезФильтра( );
                                                // TODO: 05.02.2025
                                                startswapCursor(simpleCursorAdapterЦФО,  listViewДляНовыйПосик,alertDialogНовыйПосик);

                                                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+" КурсорТаблицаПрофесии " +КурсорТаблицаПрофесии+"\n" +" constraint"  +constraint);
                                            }, 500);

                                    }


                                    simpleCursorAdapterЦФО.notifyDataSetChanged();
                                    searchViewДляНовогоЦФО.refreshDrawableState();
                                    listViewДляНовыйПосик.deferNotifyDataSetChanged();
                                    listViewДляНовыйПосик.refreshDrawableState();
                                    listViewДляНовыйПосик.forceLayout();
                                });
                                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+" КурсорТаблицаПрофесии " +КурсорТаблицаПрофесии+"\n" +" constraint"  +constraint);
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                                        this.getClass().getName(),
                                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                            return КурсорТаблицаПрофесии;
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(),
                            this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }





            private void startswapCursor(@NonNull SimpleCursorAdapter simpleCursorAdapterЦФО,@NonNull ListView listViewДляЦФО,@NonNull AlertDialog alertDialog) {
                try{
                    simpleCursorAdapterЦФО.swapCursor(КурсорТаблицаПрофесии);
                    simpleCursorAdapterЦФО.notifyDataSetChanged();
                    listViewДляЦФО.setSelection(0);
                    alertDialog.setTitle("ЦФО "+"("+String.valueOf(КурсорТаблицаПрофесии.getCount()+")"));
                    Log.d(this.getClass().getName(), "\n" + " class " +
                            Thread.currentThread().getStackTrace()[2].getClassName()
                            + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + "    cursorДанные " +КурсорТаблицаПрофесии);

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(),
                            this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }


            // TODO: 02.08.2022
            protected  Cursor МетодКурсорДляНовогоПосика(  @NotNull String Фильтр){
                Cursor КурсорТаблицаПрофесииLike = null;
                try{
                    // TODO: 09.10.2024 Public ID
                    Integer getPublicID = new GetttingPublicID().getttingPublicID(getContext());
                    // TODO: 09.06.2025
                    String Текущаятаблицы="prof";
                    ModuleQuety moduleQuety=new ModuleQuety(getContext());
                    КурсорТаблицаПрофесииLike=   moduleQuety.getModuleQuery(Текущаятаблицы,"   SELECT *  FROM   "+Текущаятаблицы+" AS D " +
                            "  WHERE D.name  LIKE ='%"+Фильтр+"%'+",null);
                    // TODO: 09.06.2025
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");


                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                return  КурсорТаблицаПрофесииLike;
            }
            // TODO: 12.04.2023 смена професси








            Integer МетодЗаписиСменыПрофесии(@NonNull View searchViewДляНовогоПоиска, @NonNull Context context){ //TODO метод записи СМЕНЫ ПРОФЕСИИ
                Integer ОбновлениеПрофесии=0;
                try{
                    String ТаблицаОбработки="data_tabels";
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " searchViewДляНовогоПоиска "+searchViewДляНовогоПоиска+ " ТаблицаОбработки "+ТаблицаОбработки);
                    Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabasecurrentoperations/" +ТаблицаОбработки + "");
                    Bundle bundleСменаПрофессии= (Bundle)  searchViewДляНовогоПоиска.getTag();

                    if (bundleСменаПрофессии!=null) {
                        ContentValues valuesСменаПрофесси=new ContentValues();
                        Integer ПолучаемIDПрофессии=      bundleСменаПрофессии.getInt("ПолучаемIDПрофессии",0);
                        valuesСменаПрофесси.put("prof",ПолучаемIDПрофессии);
                        Long ВерсияДанныхUp = new VersionCurentTable(getContext()).upVersionCurentTable(ТаблицаОбработки );
                        valuesСменаПрофесси.put("current_table",ВерсияДанныхUp);
                        String ДатаОбновления=     new GetMainDateForApp(getContext()).getMainDateForApp();
                        valuesСменаПрофесси.put("date_update",ДатаОбновления);
                        Long CurrenrsСhildUUID =   bundleСменаПрофессии.getLong("CurrenrsСhildUUID",0l);
                        ContentResolver contentResolver=context.getContentResolver();
                        ОбновлениеПрофесии=  contentResolver.update(uri, valuesСменаПрофесси,"uuid=?",new String[]{String.valueOf(CurrenrsСhildUUID)});
                    }


                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+" РЕЗУЛЬТАТ ОбновлениеПрофесии  " +  ОбновлениеПрофесии);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                return  ОбновлениеПрофесии;
            }






        }


        @SuppressLint("Range")
             String МетодЗаполняемФИОиПрофесиюRow(@NonNull  Cursor   cursor  ) {
                try {
                    // TODO: 16.04.2023  посик по Data_Tabels
                    Integer getProfessions = cursor.getInt(cursor.getColumnIndex("dt_prof"));
                    getProfessions=    Optional.ofNullable(getProfessions).orElse(0);
                    if(getProfessions==0){
                        // TODO: 16.04.2023  посик по ФИО
                        getProfessions  = cursor.getInt(cursor.getColumnIndex("fio_prof"));
                    }

                    // TODO: 09.06.2025
                    String Текущаятаблицы="prof";
                    ModuleQuety moduleQuety=new ModuleQuety(getContext());
                    Cursor    КурсорПрофессия=   moduleQuety.getModuleQuery(Текущаятаблицы,"   SELECT *  FROM   "+Текущаятаблицы+" AS D " +
                            " WHERE D.uuid='"+getProfessions.toString()+"'",null);
                    // TODO: 09.06.2025
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");




                    // TODO: 20.11.2023  уставналиваем проыесиию и ФИО

                    metodSetProfesfionSingleTabel(КурсорПрофессия);
                    metodSetFIOSingleTabel(ФИО);


                    Bundle bundleFio=new Bundle();
                    // TODO: 17.04.2023 Tag
                    bundleFio.putString("ФИО",ФИО);
                    bundleFio.putString("Профессия",Профессия);
                    materialTextViewprofession.setTag(bundleFio);
                   // TODO: 27.05.2025
                    КурсорПрофессия.close();

                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " cursorForViewPager  "
                            +cursor+ "materialTextViewprofession " + materialTextViewprofession);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(getContext().getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(),
                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                return  Профессия;
            }


            // TODO: 20.11.2023  устанвливаем фпросию
            @SuppressLint("Range")
            private void metodSetProfesfionSingleTabel(@NotNull Cursor КурсорПрофессия) {
                try{
                if (КурсорПрофессия.getCount()>0) {
                    Профессия = КурсорПрофессия.getString(КурсорПрофессия.getColumnIndex("name"));
                    // TODO: 20.11.2023
                    if (   Профессия.length()> 0 && !Профессия.trim().matches("(.*)Должность не заполнена(.*)")) {
                        new SetFioAndProfessionDisayn(0).методПерегрузкаВидаПрофесии("("+Профессия.trim()+")" ,
                                materialTextViewprofession);
                        // TODO: 20.11.2023  когда не професии
                    }else {
                        metodDontPresesssion();
                    }
                }else {
                    metodDontPresesssion();
                }
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " cursorForViewPager  "
                        + "Профессия " + Профессия);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            }


        private void metodSetFIOSingleTabel(@NotNull String ФИО) {
            try{
                        new SetFioAndProfessionDisayn(ФИО.length()).методПерегрузкаВидаПрофесии(ФИО.trim() , materialTextViewfio);
                        // TODO: 20.11.2023  когда не професии
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " cursorForViewPager  "
                        + "Профессия " + Профессия);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }


            private void metodDontPresesssion() {
                Профессия="нет должности";
                new SetFioAndProfessionDisayn(ФИО.length()).методПерегрузкаВидаПрофесии(ФИО.trim() , materialTextViewfio);
                new SetFioAndProfessionDisayn(0).методПерегрузкаВидаПрофесии("("+Профессия.trim()+")" , materialTextViewprofession);
            }


        protected     class GetCursorSingleTabel {
            Cursor          getCursorSingleTabel = null;
            protected Cursor МетодSwipesКурсор() {
                try{
                    if (MainParentUUID>0) {
                        // TODO: 09.06.2025
                        String Текущаятаблицы="viewtabel";
                        ModuleQuety moduleQuety=new ModuleQuety(getContext());
                        getCursorSingleTabel=   moduleQuety.getModuleQuery(Текущаятаблицы,"   SELECT *  FROM   "+Текущаятаблицы+" AS D " +
                                " WHERE D.uuid_tabel='"+String.valueOf(MainParentUUID)+"'    AND D.status_send !='Удаленная'   ORDER BY D.name ",null);
                        // TODO: 09.06.2025
                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                        // TODO: 08.05.2025
                        if ( myRecycleViewAdapter.cursor==null) {
                            getCursorSingleTabel.moveToPosition(getlistpeoplesposition);
                        }else {
                            getCursorSingleTabel.moveToPosition(myRecycleViewAdapter.cursor.getPosition());
                        }
                        // TODO: 13.04.2023 делаем смещение по курсору
                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "getlistpeoplesposition " +getlistpeoplesposition );
                    }else {
                        // TODO: 13.04.2023 делаем смещение по курсору
                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "DigitalNameCFO " +DigitalNameCFO );
                    }
                    // TODO: 13.04.2023 делаем смещение по курсору
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "getCursorSingleTabel " +getCursorSingleTabel );
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                return  getCursorSingleTabel;
            }

        }



    }//TODO КОНЕЦ КЛАССА визуального оформление Recycreview

    // TODO: 20.11.2023  кллас зааполенени данными двух снименров часы и Мксяц
    class AddDataSpinersHasyAndMotch{
        protected void metodAddDataSpinersHasyAndMotch(@NotNull MaterialTextView materialTextView
                , @NotNull String string) {
            try{
                materialTextView.setText(string.trim());
                materialTextView.setTypeface(Typeface.SANS_SERIF,Typeface.BOLD);//Typeface boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD);
                materialTextView.setTextColor(Color.BLACK);
                materialTextView.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
                materialTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);
                materialTextView.requestLayout();
                materialTextView.refreshDrawableState();
                materialTextView.setClickable(false);
                materialTextView.setFocusable(false);
                Log.d(this.getClass().getName(), " spinnerchasy  " +spinnerchasy);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " +e + " Метод :"+Thread.currentThread().getStackTrace()[2].getMethodName()
                        + " Линия  :"+Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(),  this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
    }

    //TODO форматирование ФИО и или Проыесии
    class SetFioAndProfessionDisayn{
        Integer ДлинаПодчерникания=0;

        public SetFioAndProfessionDisayn(Integer длинаПодчерникания) {
            ДлинаПодчерникания = длинаПодчерникания;
        }

        protected void методПерегрузкаВидаПрофесии(@NonNull String Профессия,@NotNull MaterialTextView materialTextView) {
            try {

            SpannableString ss=new SpannableString(Профессия);
            ss.setSpan(new UnderlineSpan(), ДлинаПодчерникания, Профессия.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            materialTextView.setText(ss);
                materialTextView.refreshDrawableState();
                materialTextView.requestLayout();

                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "  Профессия " +Профессия +  " ФИО " +ФИО);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }


    }

//TODO класс получение Каленалря ДНЕЙ и ПРАЗНИКОВ
    class GetDayFromKalendary{
    // TODO: 03.04.2023 Создание  Дней Недели Вс, Пон, Ср,Черт
    private  LinkedHashMap< String,String> getWorkerDays() throws ParseException,RuntimeException {
        LinkedHashMap< String,String> linkedHashMapВыходные=new LinkedHashMap<>();
        try {
            Integer ПолученоеКоличествоДнейНаКонкретныйМЕсяц=МетодПолучениеСколькоДнейВКонкретномМесяце(ГодТабелей,    МЕсяцТабелей );
            IntStream.iterate(1, i -> i + 1).limit(ПолученоеКоличествоДнейНаКонкретныйМЕсяц ).parallel().forEachOrdered(new IntConsumer() {
                @Override
                public void accept(int ИндексДней) {
                    SimpleDateFormat СозданияВычисляемВыходные = null;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        СозданияВычисляемВыходные = new SimpleDateFormat("yyyy-MM-dd", new Locale("ru"));
                    } else {
                        СозданияВычисляемВыходные = new SimpleDateFormat("yyyy-MM-dd", new Locale("ru"));
                    }
                    Date ДатаПосикаВыходныеДней = null;

                    try {
                        ДатаПосикаВыходныеДней = СозданияВычисляемВыходные.parse(ГодТабелей + "-" + МЕсяцТабелей + "-" + ИндексДней);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }

                    String РезультатДатыДляКонктетногоТабеляТолькоЗанвание = new SimpleDateFormat("EEE", new Locale("ru")).format(ДатаПосикаВыходныеДней);
                    Integer РезультатДатыДляКонктетногоТабеляТольокЧисло = Integer.parseInt(new SimpleDateFormat("dd", new Locale("ru")).format(ДатаПосикаВыходныеДней));
                    StringBuffer БуферРезультатСокращенноВставкиВТабель = new StringBuffer();
                    БуферРезультатСокращенноВставкиВТабель.append(РезультатДатыДляКонктетногоТабеляТолькоЗанвание).append(" ,").append(РезультатДатыДляКонктетногоТабеляТольокЧисло);
                    String СокращенныйДниМесяцаВТабеле = БуферРезультатСокращенноВставкиВТабель.substring(0, 1).toUpperCase()
                            + БуферРезультатСокращенноВставкиВТабель.substring(1, БуферРезультатСокращенноВставкиВТабель.length()).toLowerCase();


                    // TODO: 26.06.2023  ТОЛЬКО  ВЫХОДНЫЕ
                    Integer ИндексДнейФинал = (Integer) ИндексДней;
                    ///linkedHashMapДни.put("d" + ИндексДнейФинал.toString().trim(), СокращенныйДниМесяцаВТабеле.trim());
                    linkedHashMapВыходные.put("d"+ИндексДнейФинал.toString().trim(), СокращенныйДниМесяцаВТабеле.trim());

                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " linkedHashMapВыходные " + linkedHashMapВыходные);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  linkedHashMapВыходные;
    }

    // TODO: 03.04.2023 Создание  ПРАЗНИЧНЫЕ ДНИ
    private  LinkedHashMap< String,String> getHolidaysDays() throws ParseException,RuntimeException {
        LinkedHashMap< String,String> linkedHashMapПраздничныеДни =new LinkedHashMap<>();
        try {
            Integer ПолученоеКоличествоДнейНаКонкретныйМЕсяц=МетодПолучениеСколькоДнейВКонкретномМесяце(ГодТабелей,    МЕсяцТабелей );
            IntStream.iterate(1, i -> i + 1).limit(ПолученоеКоличествоДнейНаКонкретныйМЕсяц ).parallel().forEachOrdered(new IntConsumer() {
                @Override
                public void accept(int ИндексДней) {
                    SimpleDateFormat СозданияВычисляемВыходные = null;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        СозданияВычисляемВыходные = new SimpleDateFormat("yyyy-MM-dd", new Locale("ru"));
                    } else {
                        СозданияВычисляемВыходные = new SimpleDateFormat("yyyy-MM-dd", new Locale("ru"));
                    }
                    Date ДатаПосикаВыходныеДней = null;

                    try {
                        ДатаПосикаВыходныеДней = СозданияВычисляемВыходные.parse(ГодТабелей + "-" + МЕсяцТабелей + "-" + ИндексДней);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }

                    String РезультатДатыДляКонктетногоТабеляТолькоЗанвание = new SimpleDateFormat("EEE", new Locale("ru")).format(ДатаПосикаВыходныеДней);
                    Integer РезультатДатыДляКонктетногоТабеляТольокЧисло = Integer.parseInt(new SimpleDateFormat("dd", new Locale("ru")).format(ДатаПосикаВыходныеДней));
                    StringBuffer БуферРезультатСокращенноВставкиВТабель = new StringBuffer();
                    БуферРезультатСокращенноВставкиВТабель.append(РезультатДатыДляКонктетногоТабеляТолькоЗанвание).append(" ,").append(РезультатДатыДляКонктетногоТабеляТольокЧисло);
                    String СокращенныйДниМесяцаВТабеле = БуферРезультатСокращенноВставкиВТабель.substring(0, 1).toUpperCase()
                            + БуферРезультатСокращенноВставкиВТабель.substring(1, БуферРезультатСокращенноВставкиВТабель.length()).toLowerCase();

                    // TODO: 11.04.2023 празничные
                    Integer ИндексДнейФинал = (Integer) ИндексДней;
                    ///linkedHashMapДни.put("d" + ИндексДнейФинал.toString().trim(), СокращенныйДниМесяцаВТабеле.trim());

                    Integer integerСубота=   СокращенныйДниМесяцаВТабеле.trim().indexOf("Сб");
                    Integer integerВоск=   СокращенныйДниМесяцаВТабеле.trim().indexOf("Вс");

                    if (integerСубота==0  || integerВоск==0){
                        linkedHashMapПраздничныеДни.put("d"+ИндексДнейФинал.toString().trim(), СокращенныйДниМесяцаВТабеле.trim());
                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                + " integerСубота " + integerСубота
                                + " integerВоск " + integerВоск);
                    }
                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                + " linkedHashMapПраздничныеДни " + linkedHashMapПраздничныеДни
                                + " МЕсяцТабелей " + МЕсяцТабелей);


                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " linkedHashMapПраздничныеДни " + linkedHashMapПраздничныеДни);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  linkedHashMapПраздничныеДни;
    }
    /**
     *
     */
    private  int МетодПолучениеСколькоДнейВКонкретномМесяце(int Год,int Месяц) {
        int КоличествоДнейНаВыбраныйМесяц=0;
        try{
            Date date = null;
            int КонктетныйМесяцВВидеЦифры;
            Calendar cal;
            cal = Calendar.getInstance();
            System.out.println(cal.get(Calendar.MONTH));
            // Create a calendar object and set year and month
            Calendar mycal = new GregorianCalendar(Год, Месяц, 0);
            // Get the number of days in that month
           // КоличествоДнейНаВыбраныйМесяц = mycal.getActualMaximum(Calendar.DAY_OF_MONTH); // 28

            КоличествоДнейНаВыбраныйМесяц = YearMonth.of(Год, Месяц).lengthOfMonth();
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " КоличествоДнейНаВыбраныйМесяц " + КоличествоДнейНаВыбраныйМесяц);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        return  КоличествоДнейНаВыбраныйМесяц;
    }

    // TODO: 20.11.2023 END KLASS get DAY
}



// TODO: 30.11.2023  класс SWIPE


}