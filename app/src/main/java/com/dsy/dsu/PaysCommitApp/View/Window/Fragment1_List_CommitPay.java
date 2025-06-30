package com.dsy.dsu.PaysCommitApp.View.Window;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.dsy.dsu.BusinessLogicForApps.GetPublicID.QualifierPublicID;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.BusinessLogicForApps.Hilt.Adress1cPays.QualifierCommintgPays;
import com.dsy.dsu.BusinessLogicForApps.Hilt.Adress1cPays.QualifierCommintgPays2;
import com.dsy.dsu.BusinessLogicForApps.Hilt.Adress1cPays.QualifierCommintgPays4;

import com.dsy.dsu.PaysCommitApp.Model.BI_RecyreView.Bl_CommintigPay;
import com.dsy.dsu.PaysCommitApp.Model.BI_RecyreView.LiveData.GetLiveDataForrecyreViewPay;
import com.dsy.dsu.PaysCommitApp.Model.BinderService1cCommitPay;
import com.dsy.dsu.PaysCommitApp.Model.EventBusPays.MessageEvensBusPays;
import com.dsy.dsu.PaysCommitApp.View.RecyreViewIsNull.MyRecycleViewIsNullAdapterPay;
import com.dsy.dsu.R;
import com.dsy.dsu.BusinessLogicForApps.Services.ServiceForApprove;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.textview.MaterialTextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class Fragment1_List_CommitPay extends Fragment   {

    // TODO: 10.03.2022  согласования фрагмент
    private Bl_CommintigPay bl_commintigPay;
    private JsonNode jsonNode1сСогласованияAllRows;
    private FragmentManager fragmentManagerДляЗадачи;
    private FragmentTransaction fragmentTransactionляЗадачи;
    private MaterialTextView mTVhandlercommingpay;
   /// private Integer ПубличныйidPay;
    private MaterialCardView materialCardViewmain_commitpay;
    private Fragment fragment_ТекущийФрагментСогласованиеСписок;

    private BottomNavigationView bottomNavigationViewParent;
    @SuppressLint("RestrictedApi")
    private BottomNavigationItemView bottomNavigationBack;
    @SuppressLint("RestrictedApi")
    private BottomNavigationItemView bottomNavigationAsync;
    @SuppressLint("RestrictedApi")
    private BottomNavigationItemView bottomNavigationSearch;

    private ProgressBar progressBarCommitPay;

    private  Boolean  РезультатИзмененияСтатусаСогласованияОтказаИлиУспешноеСогласования;
    private ServiceForApprove.LocalBinderДляСогласования binderСогласования1C;

    private Boolean ФлагПрошлаХотябыОднаПопыткаПолучитьДанные=false;
   private  Animation animationДляСогласования;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private LifecycleOwner lifecycleOwner;
    private Animation animation1;
    private  Handler handler;
    private BinderService1cCommitPay binderService1cCommitPay;
    private   MainActivity_CommitPay   mainActivity_commitPayInterface;
    private androidx.appcompat.widget.SearchView searchview_commitpay;
    private     RelativeLayout relativeLayout_recyreview;
     private        Intent intentsendJsonNodeToService= null;

     @Inject
     @QualifierCommintgPays
     StringBuilder getHiltCommintgPays;

    @Inject
    @QualifierCommintgPays2
    StringBuilder getHiltCommintgPays3;
    @Inject
    @QualifierCommintgPays4
    StringBuilder getHiltCommintgPays4;





  /*  private  MutableLiveData<Intent> jsonNodeMutableLiveDataPayCommintg = new MutableLiveData<>();*/

       @Inject
    MutableLiveData<Intent> getHiltMutableLiveDataPay;


    @Inject
    ObjectMapper getHiltJaksonObjectMapper;
    @Inject
    @QualifierPublicID
    Integer getHiltPublicId;



    private RecyclerView recycleviewcommitpays;


    private View viewCore;

    public MyRecycleViewIsNullAdapterPay myRecycleViewIsNullAdapter;

    @Inject
    GetLiveDataForrecyreViewPay getLiveDataForrecyreViewPay;


    @Inject
    MutableLiveData<Intent> getHiltMutableLiveDataPayForRecyreView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
        Bundle data=      getArguments();
            fragmentManager = getActivity(). getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            lifecycleOwner=this;





            setObservableLiveData();

            // TODO: 15.08.2023
            Log.d(this.getClass().getName(),"\n" + " class "
                    + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getActivity()).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        ///
    }
    }



    @SuppressLint("RestrictedApi")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            recycleviewcommitpays = view.findViewById(R.id.recycleviewcommitpays);
            mTVhandlercommingpay = view.findViewById(R.id.mTVhandlercommingpay);
            fragmentManagerДляЗадачи = getActivity().getSupportFragmentManager();
            materialCardViewmain_commitpay = view.findViewById(R.id.materialCardViewmain_commitpay);
                searchview_commitpay =  view.findViewById(R.id.searchview_commitpay);
              //  searchview_commitpay.setVisibility(View.INVISIBLE);
                searchview_commitpay.setEnabled(false);
                searchview_commitpay.setIconifiedByDefault(true); //iconify the widget
                searchview_commitpay.setSubmitButtonEnabled(true);
            bottomNavigationViewParent = view.findViewById(R.id.bottomnavigationActivicommit_search);
            bottomNavigationViewParent.setLabelVisibilityMode(NavigationBarView.LABEL_VISIBILITY_UNLABELED);
            bottomNavigationBack = bottomNavigationViewParent.findViewById(R.id.bottomNavigationBack);
            bottomNavigationBack.setTitle("Выйти");
            bottomNavigationAsync = bottomNavigationViewParent.findViewById(R.id.bottomNavigationAsync);
            bottomNavigationAsync.setTitle("Обновить");
            bottomNavigationSearch = bottomNavigationViewParent.findViewById(R.id.bottomNavigationSearch);
            bottomNavigationSearch.setTitle("Поиск");
            bottomNavigationSearch.setEnabled(false);
            bottomNavigationSearch.setClickable(false);

            progressBarCommitPay = view.findViewById(R.id.prograessbarcommitpaydown); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА
            relativeLayout_recyreview= view.findViewById(R.id.relativeLayout_recyreview);
            bottomNavigationViewParent.getOrCreateBadge(R.id.id_commitasync).setVisible(true);//.getOrCreateBadge(R.id.id_taskHome).setVisible(true);

             ///animationДляСогласования= AnimationUtils.loadAnimation(getContext(),R.anim.slide_in_row);
            // animationДляСогласования= AnimationUtils.loadAnimation(getContext(),R.anim.slide_in_row_vibrator1);
             animationДляСогласования= AnimationUtils.loadAnimation(getContext(),R.anim.slide_in_row);//R.anim.layout_animal_commit
            animation1 = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_row_tabellist);
           // animation2 = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_scrolls);


            binderСогласования1C=   ((MainActivity_CommitPay)getActivity()).binderСогласования1C;

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getActivity()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


    // TODO: 12.03.2022
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        try{
// TODO: 14.03.2022
          /// viewДляПервойКнопкиСогласованиея = inflater.inflate(R.layout.activity_main_fragment1_for_commipay, container, false);
            viewCore = inflater.inflate(R.layout.activity_main_fragment1_for_commipay_search, container, false);
            // TODO: 12.03.2022
            // TODO: 17.04.2023 LOG
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()
                    + "\n" + " viewCore " +viewCore);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getActivity()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return viewCore;
    }


    @Override
    public void onStart() {
        super.onStart();
        try{
            EventBus.getDefault().register(this);
            //todo метод  Запуск  RECYCLEVIEW IS NULL
            bl_commintigPay=new Bl_CommintigPay(getActivity(),getContext() ,
                    viewCore, getHiltJaksonObjectMapper,getHiltPublicId,
                    lifecycleOwner, recycleviewcommitpays,
                    myRecycleViewIsNullAdapter,animation1, mTVhandlercommingpay,
                    jsonNode1сСогласованияAllRows,
                    bottomNavigationViewParent,
                    bottomNavigationBack,
                    bottomNavigationAsync,
                    bottomNavigationSearch,
                    getHiltMutableLiveDataPay,
                    searchview_commitpay,
                    getHiltCommintgPays.toString(),
                    getLiveDataForrecyreViewPay,
                    getHiltMutableLiveDataPayForRecyreView );


            // TODO: 12.01.2024  Первоночальные вотростипенные методы



            bl_commintigPay.InitRecyreReview( );

            // TODO: 16.01.2024   RecyreView Is Null
            bl_commintigPay.InitMyAdapterRecyreViewIsNull( true );


            bl_commintigPay.   metodSetNameCommitHeaders(  );
            bl_commintigPay.successNavigatorButtonIconRow( jsonNode1сСогласованияAllRows);


            // TODO: 17.01.2024 init butttons
            bl_commintigPay.eventBackCommmitPays( );
            bl_commintigPay.eVentsAsycBottunCommintgPay( );


// TODO: 15.01.2024  Запуск Получение Данных

            bl_commintigPay.metodGetDataOt1cCommitPay( );














            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + "getHiltPublicId " + getHiltPublicId + "jsonNode1сСогласованияAllRows " + jsonNode1сСогласованияAllRows);


        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getActivity()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }



    @Override
    public void onPause() {
        super.onPause();

        // TODO: 19.01.2024 выключаем обозреватель для получение данных
       //  getHiltMutableLiveDataPay.removeObservers(lifecycleOwner);

        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
    }

    @Override
    public void onStop() {
        super.onStop();
        try{
            EventBus.getDefault().unregister(this);
          //  bl_commintigPay.методRebootDisaynRecyreViewonStopOrAsync(jsonNode1сСогласованияAllRows );

// TODO: 19.01.2024 выключаем обозреватель для получение данных
           /// getHiltMutableLiveDataPay.removeObservers(lifecycleOwner);

         /*   Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());*/

        } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getActivity()).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }

    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        try{
// TODO: 19.01.2024 выключаем обозреватель для получение данных
             // getHiltMutableLiveDataPay.removeObservers(lifecycleOwner);

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + "getHiltPublicId " + getHiltPublicId + "binderСогласования1C " + binderСогласования1C);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getActivity()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        try{

        outState.putBinder("binderСогласования1C", binderСогласования1C);

            super.onSaveInstanceState(outState);

        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                + "getHiltPublicId " + getHiltPublicId + "binderСогласования1C " + binderСогласования1C);

    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getActivity()).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }
// TODO: 09.11.2023  Код Внутри Фрагмента Pay Commiting







    private void setObservableLiveData() {
        try{

            if (!getHiltMutableLiveDataPay.hasObservers()) {
                // TODO: 16.01.2024
                getHiltMutableLiveDataPay.observe(lifecycleOwner, new Observer<Intent>() {
                    @Override
                    public void onChanged(Intent intent) {
                        try{

                         switch (intent.getAction())  {

                             case          "CallBackAflerGetData1cCommitPay":
                                 // TODO: 16.01.2024  пришли данные
                                 Bundle bundleFirst =      intent.getExtras();
                                  jsonNode1сСогласованияAllRows=     (JsonNode)      bundleFirst.getSerializable("jsonNode1сСогласования");

                                 // TODO: 16.01.2024  когда данных нет
                                 if (jsonNode1сСогласованияAllRows==null || jsonNode1сСогласованияAllRows.size()==0 ) {

                                     // TODO: 16.01.2024  ЗАПУСКАЕМ ДАННЙ КОД КОГДА ДАННЫХ НЕ ПРИШЛО оТ 1с
                                     getDataISAdapterIsNullDontCallBack(   );

                                     // TODO: 16.01.2024 выключаем Програсс БАр
                                     prograssBarVisibleDont();


                                     // TODO: 16.01.2024    Данные есть
                                 }else {
                                     // TODO: 16.01.2024  Запускаем код когда данные уже есть пригшли успешно от 1с Сограсования
                                     getDataSucceessOt1cCallBack();


                                     // TODO: 16.01.2024 выключаем Програсс БАр
                                     prograssBarVisibleDont();
                                     
                                     Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                             " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                             " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()
                                             + "\n"+ " jsonNode1сСогласованияAllRows "+jsonNode1сСогласованияAllRows);
                                     
                                 }

                                 // TODO: 16.01.2024 перегрузка дизайна
                                 bl_commintigPay.   rebootDizaynRecyreView();



                                 Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                         " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                         " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()
                                         + "\n"+ " jsonNode1сСогласованияAllRows "+jsonNode1сСогласованияAllRows);
                                 break;







                             // TODO: 16.01.2024  Повтороый SECOND получение данных с Кнопки
                             case         "SecondCallBackAflerGetData1c":
                                 // TODO: 16.01.2024  пришли данные
                                 Bundle bundleSecond =      intent.getExtras();
                                 jsonNode1сСогласованияAllRows=     (JsonNode)      bundleSecond.getSerializable("jsonNode1сСогласования");
                                 // TODO: 16.01.2024  когда данных нет
                                 if (jsonNode1сСогласованияAllRows!=null   ) {
                                     if ( jsonNode1сСогласованияAllRows.size()>0) {

                                         bl_commintigPay.myRecycleViewAdapterReebotgetAdapter(jsonNode1сСогласованияAllRows );

                                         // TODO: 16.01.2024 перегрузка дизайна

                                         // TODO: 16.01.2024 перегрузка дизайна
                                         bl_commintigPay.   rebootDizaynRecyreView();
                                     }
                                 }

                                 // TODO: 16.01.2024 выключаем Програсс БАр
                                 prograssBarVisibleDont();


                                 Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                         " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                         " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()
                                         + "\n"+ " jsonNode1сСогласованияAllRows "+jsonNode1сСогласованияAllRows);
                             break;




                             // TODO: 16.01.2024  Повтороый SECOND получение данных с Кнопки
                             case "PrinyditelnaySecondCallBackAflerGetData1c":
                                 // TODO: 16.01.2024  пришли данные
                                 Bundle bundleSecondPrinediltel =      intent.getExtras();
                                 jsonNode1сСогласованияAllRows=     (JsonNode)      bundleSecondPrinediltel.getSerializable("jsonNode1сСогласования");
                                 // TODO: 16.01.2024  когда данных нет
                                 if (jsonNode1сСогласованияAllRows!=null    ) {
                                     if (jsonNode1сСогласованияAllRows.size()>0) {
                                         // TODO: 25.03.2024  метод перезаполяем данные которые пришли

                                         getDataSucceessOt1cCallBack();
                                         // TODO: 16.01.2024 перегрузка дизайна
                                         // TODO: 16.01.2024 перегрузка дизайна
                                         bl_commintigPay.   rebootDizaynRecyreView();
                                     }
                                 }

                                 // TODO: 16.01.2024 выключаем Програсс БАр
                                 prograssBarVisibleDont();



                                 Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                         " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                         " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()
                                         + "\n"+ " jsonNode1сСогласованияAllRows "+jsonNode1сСогласованияAllRows);
                                 break;





                         }

                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
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


            }

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
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



    // TODO: 25.03.2024  метод перезаполяем данные которые пришли
    private void getDataSucceessOt1cCallBack() {
        
        try{

            if (jsonNode1сСогласованияAllRows!=null && binderСогласования1C!=null) {
                // TODO: 16.01.2024   RecyreView Is Worker
                bl_commintigPay.InitMyAdapterRecyreViewWorker(jsonNode1сСогласованияAllRows,
                        binderСогласования1C,getHiltCommintgPays.toString(),lifecycleOwner);

                bl_commintigPay.successNavigatorButtonIconRow(jsonNode1сСогласованияAllRows );

                bl_commintigPay. setEnableSearchMechi(jsonNode1сСогласованияAllRows,bottomNavigationViewParent);


                bl_commintigPay.  eVentsAsycBottunCommintgPay();

                bl_commintigPay.  eVentSearchViewCommingPay(jsonNode1сСогласованияAllRows);




                // TODO: 16.01.2024 перегрузка дизайна
                // TODO: 16.01.2024 перегрузка дизайна
                bl_commintigPay.   rebootDizaynRecyreView();


            }


            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
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

    private void prograssBarVisibleDont() {
        try{
        progressBarCommitPay.setVisibility(View.GONE);


        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
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




    private void getDataISAdapterIsNullDontCallBack() {
        try{


            bl_commintigPay. методRebootRecyreviewDontJsonNULL();

        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
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






    // TODO: 23.01.2024 EventBus for Async
    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
    public void EventMessageEvensBusItemConut(MessageEvensBusPays.MessageEvensBusCommintPay messageEvensBusCommintPay){
        try{

            Log.d(this.getClass().getName(),"\n"
                    + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()+
                    " messageEvensBusCommintPay " +messageEvensBusCommintPay.mess);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }































}






























