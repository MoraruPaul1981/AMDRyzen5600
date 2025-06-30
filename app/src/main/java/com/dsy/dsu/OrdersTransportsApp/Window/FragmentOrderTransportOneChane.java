package com.dsy.dsu.OrdersTransportsApp.Window;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentCallbacks;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.loader.content.AsyncTaskLoader;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.SimpleCursorAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.dsy.dsu.BusinessLogicForApps.GetPublicID.GetttingPublicID;
import com.dsy.dsu.CoreApp.Apps.DashboardCoreApp.Model.bl_launchFragmentSettingsandDashbord.LaunchActivityDashboard;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.OrdersTransportsApp.Background.ServiceOrserTransportService;
import com.dsy.dsu.R;
import com.dsy.dsu.BusinessLogicForApps.Services.ServiceUpdatesPO;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textview.MaterialTextView;
import com.sous.backasync.launch.ModuleQuety;


import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Consumer;


// TODO: 29.09.2022 фрагмент для получение материалов
public class FragmentOrderTransportOneChane extends Fragment {
    LinearLayout linearLayout_orders_transport;
    long start;
    long startДляОбноразвовной;
    private Integer ПубличныйID;
    private BottomNavigationView BottomNavigationOrderTransport;
    @SuppressLint("RestrictedApi")
    private BottomNavigationItemView bottomNavigationItemViewвыход;
    @SuppressLint("RestrictedApi")
    private BottomNavigationItemView bottomNavigationItemView2создать;
    @SuppressLint("RestrictedApi")
    private BottomNavigationItemView bottomNavigationItemView3обновить;
    @SuppressLint("RestrictedApi")
    private BottomNavigationItemView bottomNavigationItemView4История;
    private ProgressBar progressBarСканирование;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment fragmentNewЗаказТранспорта;
    private TextView TextViewHadler;
    private ServiceOrserTransportService.LocalBinderOrderTransport localBinderOrderTransport;
    private ServiceConnection serviceConnection;
    private Message message;
    ///private  Cursor cursorGroupByParent;
    private RecyclerView recyclerView_OrderTransport;

    private SubClassOrdersTransport subClassOrdersTransport;
    private Animation animationvibr1;
    private Animation animationvibrRow;
    private LifecycleOwner lifecycleOwnerОдноразовая;
    private LifecycleOwner lifecycleOwnerОбщая;

    private SimpleCursorAdapter АдаптерЗаказыТарнпорта;
    private MaterialTextView textViewHadler;
    private String ИмяСлужбыСинхронизациОдноразовая = "WorkManager Synchronizasiy_Data Disposable";
    private String ИмяСлужбыСинхронизацииОбщая = "WorkManager Synchronizasiy_Data";
    private SubClassOrdersTransport.SubClassAdapters getsubClassAdapterMyRecyclerview;

    private SubClassOrdersTransport.SubClassAdapters.SubClassAdapterMyRecyclerview.MyRecycleViewAdapter myRecycleViewAdapter;
    private SubClassOrdersTransport.SubClassAdapters.SubClassAdapterMyRecyclerview.MyViewHolder myViewHolder;

    private ScrollView scrollview_OrderTransport;
    private LinearLayoutManager linearLayoutManager;

    private Cursor cursor;
    private AsyncTaskLoader asyncTaskLoader;
    private ServiceUpdatesPO.localBinderОбновлениеПО localBinderОбновлениеПО;//TODO новаЯ

    private LifecycleOwner lifecycleOwner;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            // TODO: 27.04.2023  Запускаем Заказ Транпорта

            fragmentManager = getActivity().getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();

            lifecycleOwner = this;


            Bundle bundleBoundOrserTran = (Bundle) getArguments();
            if (bundleBoundOrserTran != null) {
                localBinderOrderTransport = (ServiceOrserTransportService.LocalBinderOrderTransport) bundleBoundOrserTran.getBinder("binder");
            }
            // TODO: 29.05.2023  new Классов
            subClassOrdersTransport = new SubClassOrdersTransport(getActivity());
            getsubClassAdapterMyRecyclerview = subClassOrdersTransport.new SubClassAdapters();
            lifecycleOwnerОдноразовая = this;
            lifecycleOwnerОбщая = this;
            // TODO: 04.05.2023
            ПубличныйID = new GetttingPublicID().getttingPublicID(getContext());


            subClassOrdersTransport.МетодHandlerCallBack();


            Log.d(getContext().getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + "  localBinderOrderTransport " + localBinderOrderTransport);
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

    @SuppressLint({"WrongConstant", "RestrictedApi", "WrongViewCast"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        try {
            view = inflater.inflate(R.layout.simple_for_root_orders_transport, container, false);
            // view= inflater.inflate(R.layout.list_item_progressing_ordertransport, container, false);
            /// view= inflater.inflate(R.layout.list_item_progressing_ordertransport, container, false);
            linearLayout_orders_transport = (LinearLayout) view.findViewById(R.id.linearLayout_orders_transport);
            // TODO: 01.05.2023  Кнопки
            BottomNavigationOrderTransport = (BottomNavigationView) view.findViewById(R.id.BottomNavigationOrderTransport);
            bottomNavigationItemViewвыход = BottomNavigationOrderTransport.findViewById(R.id.id_lback);
            bottomNavigationItemView2создать = BottomNavigationOrderTransport.findViewById(R.id.id_create);
            bottomNavigationItemView3обновить = BottomNavigationOrderTransport.findViewById(R.id.id_async);
            bottomNavigationItemView4История = BottomNavigationOrderTransport.findViewById(R.id.id_story);

            BottomNavigationOrderTransport.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_UNLABELED);

            bottomNavigationItemViewвыход.setIconSize(50);
            bottomNavigationItemView2создать.setIconSize(50);
            bottomNavigationItemView3обновить.setIconSize(50);
            bottomNavigationItemView4История.setIconSize(55);


            bottomNavigationItemViewвыход.setVisibility(View.VISIBLE);
            bottomNavigationItemView2создать.setVisibility(View.VISIBLE);
            bottomNavigationItemView3обновить.setVisibility(View.VISIBLE);
            bottomNavigationItemView4История.setVisibility(View.VISIBLE);

            progressBarСканирование = (ProgressBar) view.findViewById(R.id.ProgressBar);
            progressBarСканирование.setVisibility(View.VISIBLE);
            // TODO: 01.05.2023
            recyclerView_OrderTransport = (RecyclerView) view.findViewById(R.id.recyclerView_OrderTransport);
            TextViewHadler = (TextView) view.findViewById(R.id.TextViewHadler);
            animationvibr1 = AnimationUtils.loadAnimation(getContext(), R.anim.slide_singletable2);//
            animationvibrRow = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_row_tabellist);//
            //  animationvibrRow = AnimationUtils.loadAnimation(getContext(),R.anim.slide_in_row);//
            textViewHadler = (MaterialTextView) view.findViewById(R.id.TextViewHadler);
            scrollview_OrderTransport = (ScrollView) view.findViewById(R.id.scrollview_OrderTransport);
            // TODO: 11.05.2023 горизонтальеный Сколлл
            Log.d(getContext().getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + " view " + view);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(getContext().getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).recordnewerror(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        try {
            super.onViewCreated(view, savedInstanceState);
            start = Calendar.getInstance().getTimeInMillis();
            startДляОбноразвовной = Calendar.getInstance().getTimeInMillis();
            //todo запуск методов в фрагменте
            // TODO: 23.05.2023 Биндинг
            subClassOrdersTransport.МетодВыходНаAppBack();
            // TODO: 04.05.2023 Анимация
            //todo recyrview
            subClassOrdersTransport.МетодИнициализацииRecycreView();
            subClassOrdersTransport.методАнимацииGridView();
            getsubClassAdapterMyRecyclerview.new SubClassAdapterMyRecyclerview(getContext()).методЗаполенияRecycleView(cursor);
            /*    getsubClassAdapterMyRecyclerview.  МетодБиндингOrderTransportGetCursor();*/
            Log.d(this.getClass().getName(), "\n" + " class " +
                    Thread.currentThread().getStackTrace()[2].getClassName()
                    + "\n" +
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
    public void onStop() {
        super.onStop();
        try {
            if (myRecycleViewAdapter.cursor != null) {
                myRecycleViewAdapter.cursor.requery();
                myRecycleViewAdapter.notifyDataSetChanged();
                recyclerView_OrderTransport.getAdapter().notifyDataSetChanged();
            }


            Log.d(this.getClass().getName(), "\n" + " class " +
                    Thread.currentThread().getStackTrace()[2].getClassName()
                    + "\n" +
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
    public void onDestroyView() {
        super.onDestroyView();
        try {
            recyclerView_OrderTransport.removeAllViewsInLayout();
            ///WorkManager.getInstance(getContext()).cancelUniqueWork(ИмяСлужбыСинхронизациОдноразовая);
            WorkManager.getInstance(getContext()).getWorkInfosByTagLiveData(ИмяСлужбыСинхронизацииОбщая).removeObservers(lifecycleOwnerОбщая);
            WorkManager.getInstance(getContext()).getWorkInfosByTagLiveData(ИмяСлужбыСинхронизациОдноразовая).removeObservers(lifecycleOwnerОдноразовая);
            Log.d(this.getClass().getName(), "\n" + " class " +
                    Thread.currentThread().getStackTrace()[2].getClassName()
                    + "\n" +
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
    public void onStart() {
        super.onStart();
        try {
            if (cursor != null) {
                getsubClassAdapterMyRecyclerview.new SubClassAdapterMyRecyclerview(getContext()).методЗаполенияRecycleView(cursor);
                subClassOrdersTransport.МетодСлушательRecycleView();
                subClassOrdersTransport.МетодСлушательКурсора();
                subClassOrdersTransport.МетодКпопкиЗначков();
                subClassOrdersTransport.методИсторияFragment();
                subClassOrdersTransport.МетодПерегрузкаRecyceView();
                // TODO: 12.05.2023 слушатель
                subClassOrdersTransport.методСлушателяWorkManagerОбщая(lifecycleOwnerОбщая);
            } else {
                subClassOrdersTransport.МетодКпопкиЗначков();
            }
            Log.d(this.getClass().getName(), "\n" + " class " +
                    Thread.currentThread().getStackTrace()[2].getClassName()
                    + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "  cursor " + cursor);
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


    // TODO: 27.04.2023  новый код Заказ Транспорта
    class SubClassOrdersTransport {
        private Activity activity;

        public SubClassOrdersTransport(Activity activity) {
            this.activity = activity;
        }

        // TODO: 28.04.2023
        private void методАнимацииGridView() {
            try {
                recyclerView_OrderTransport.startAnimation(animationvibrRow);
                Log.d(this.getClass().getName(), "\n" + " class " +
                        Thread.currentThread().getStackTrace()[2].getClassName()
                        + "\n" +
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

        private void МетодИнициализацииRecycreView() {
            try {
                recyclerView_OrderTransport.setVisibility(View.VISIBLE);
                DividerItemDecoration dividerItemDecorationHor =
                        new DividerItemDecoration(activity, LinearLayoutManager.HORIZONTAL);
                dividerItemDecorationHor.setDrawable(getContext().getDrawable(R.drawable.divider_for_order_transport1));///R.dimen.activity_horizontal_margin
                DividerItemDecoration dividerItemDecorationVer =
                        new DividerItemDecoration(activity, LinearLayoutManager.VERTICAL);
                //recyclerView_OrderTransport.addItemDecoration(dividerItemDecorationHor);
                //recyclerView_OrderTransport.addItemDecoration(dividerItemDecorationVer);
                recyclerView_OrderTransport.setHasFixedSize(false);
                recyclerView_OrderTransport.setItemAnimator(new DefaultItemAnimator());
              /*  SnapHelper snapHelper = new LinearSnapHelper();
                snapHelper.attachToRecyclerView(recyclerView_OrderTransport);*/
                linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                linearLayoutManager.setSmoothScrollbarEnabled(true);
                recyclerView_OrderTransport.setLayoutManager(linearLayoutManager);
                // TODO: 30.05.2023
                recyclerView_OrderTransport.scrollToPosition(0);
                scrollview_OrderTransport.pageScroll(View.FOCUS_UP);
                Log.d(this.getClass().getName(), "\n" + " class " +
                        Thread.currentThread().getStackTrace()[2].getClassName()
                        + "\n" +
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

        // TODO: 14.06.2023 клик по recycreview


        private void МетодСлушательRecycleView() {
            try {

                myRecycleViewAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                    @Override
                    public void onChanged() {
                        super.onChanged();
                        Log.d(this.getClass().getName(), "\n" + " class " +
                                Thread.currentThread().getStackTrace()[2].getClassName()
                                + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                    }

                    @Override
                    public void onItemRangeChanged(int positionStart, int itemCount) {
                        super.onItemRangeChanged(positionStart, itemCount);
                        Log.d(this.getClass().getName(), "\n" + " class " +
                                Thread.currentThread().getStackTrace()[2].getClassName()
                                + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                    }

                    @Override
                    public void onItemRangeChanged(int positionStart, int itemCount, @Nullable Object payload) {
                        super.onItemRangeChanged(positionStart, itemCount, payload);
                        Log.d(this.getClass().getName(), "\n" + " class " +
                                Thread.currentThread().getStackTrace()[2].getClassName()
                                + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                    }

                    @Override
                    public void onItemRangeInserted(int positionStart, int itemCount) {
                        super.onItemRangeInserted(positionStart, itemCount);
                        Log.d(this.getClass().getName(), "\n" + " class " +
                                Thread.currentThread().getStackTrace()[2].getClassName()
                                + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                    }

                    @Override
                    public void onItemRangeRemoved(int positionStart, int itemCount) {
                        super.onItemRangeRemoved(positionStart, itemCount);
                        Log.d(this.getClass().getName(), "\n" + " class " +
                                Thread.currentThread().getStackTrace()[2].getClassName()
                                + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                    }

                    @Override
                    public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
                        super.onItemRangeMoved(fromPosition, toPosition, itemCount);
                        Log.d(this.getClass().getName(), "\n" + " class " +
                                Thread.currentThread().getStackTrace()[2].getClassName()
                                + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                    }

                    @Override
                    public void onStateRestorationPolicyChanged() {
                        super.onStateRestorationPolicyChanged();
                        Log.d(this.getClass().getName(), "\n" + " class " +
                                Thread.currentThread().getStackTrace()[2].getClassName()
                                + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                    }
                });
                Log.d(this.getClass().getName(), "\n" + " class " +
                        Thread.currentThread().getStackTrace()[2].getClassName()
                        + "\n" +
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


        // TODO: 28.04.2023
        void МетодHandlerCallBack() {
            try {
                message = Message.obtain(new Handler(Looper.myLooper()), () -> {
                    Bundle bundle = message.getData();
                    Log.i(this.getClass().getName(), " Атоманически установкаОбновление ПО " +
                            Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " время " + new Date().toLocaleString() + " message " + message);
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
        // TODO: 28.04.2023

        private void МетодВыходНаAppBack() {
            try {
                bottomNavigationItemViewвыход.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            МетодЗапускаАнимацииКнопок(v);//todo только анимауия

                            методBackActivityOrderTranport();
                            Log.i(this.getClass().getName(), " Атоманически установкаОбновление ПО " +
                                    Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " время " + new Date().toLocaleString() + " message " + message);
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
                bottomNavigationItemView2создать.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            МетодЗапускаАнимацииКнопок(v);
                            message.getTarget().postDelayed(() -> {
                                методNewOrderTransport();
                            }, 500);
                            Log.d(this.getClass().getName(), "  v  " + v);
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
                bottomNavigationItemView3обновить.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            message.getTarget().post(() -> {
                                // TODO: 12.05.2023 слушатель
                                subClassOrdersTransport.
                                        методСлушателяWorkManagerОдноразовая(lifecycleOwnerОдноразовая);
                                progressBarСканирование.setVisibility(View.VISIBLE);
                                МетодЗапускаАнимацииКнопок(v);

                                Integer ПубличныйIDДляФрагмента = new GetttingPublicID().getttingPublicID(getContext());
                                // TODO: 16.11.2022  запуск синхронизации однорозовая


                            });
                            Log.d(this.getClass().getName(), "\n" + " class FaceAPp " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
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

        private void методBackActivityOrderTranport() {
            try {
                ///todo код которыц возврящет предыдущий актвитики кнопка back

                LaunchActivityDashboard launchActivityDashboard = new LaunchActivityDashboard(fragmentManager, getContext());
                // TODO: 27.03.2024 в зависомсти кто вызвает
                launchActivityDashboard.launchADashboardFragment();


                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");




   /*            // TODO Запусукаем Фргамент НАстройки  dashbord
               DashboardFragmentSettings dashboardFragmentSettings = DashboardFragmentSettings.newInstance();
               Bundle data=new Bundle();
               dashboardFragmentSettings.setArguments(data);
               fragmentTransaction.remove(dashboardFragmentSettings);
               String fragmentNewImageNameaddToBackStack=   dashboardFragmentSettings.getClass().getName();
               fragmentTransaction.addToBackStack(fragmentNewImageNameaddToBackStack);
               Fragment FragmentУжеЕСтьИлиНЕт=     fragmentManager.findFragmentByTag(fragmentNewImageNameaddToBackStack);
               if (FragmentУжеЕСтьИлиНЕт==null) {
                   dashboardFragmentSettings.show(fragmentManager, "DashboardFragmentSettings");
                   // TODO: 01.08.2023

               }
*/


                Log.i(this.getClass().getName(), " Атоманически установкаОбновление ПО " +
                        Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " время " + new Date().toLocaleString() + " message " + message);
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

        // TODO: 28.04.2023
        // TODO: 18.10.2021  слушатель  work manager #1
        @SuppressLint("FragmentLiveDataObserve")
        void методСлушателяWorkManagerОдноразовая(@NonNull LifecycleOwner lifecycleOwnerSingle) {
// TODO: 11.05.2021 ЗПУСКАЕМ СЛУЖБУ через брдкастер синхронизхации и уведомления
            try {
                lifecycleOwnerSingle.getLifecycle().addObserver(new LifecycleEventObserver() {
                    @Override
                    public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
                        source.getLifecycle().getCurrentState();
                        event.getTargetState().name();
                    }
                });


                WorkManager.getInstance(getContext()).getWorkInfosByTagLiveData(ИмяСлужбыСинхронизациОдноразовая)
                        .observe(lifecycleOwnerSingle, new Observer<List<WorkInfo>>() {
                            @Override
                            public void onChanged(List<WorkInfo> workInfos) {
                                try {
                                    workInfos.forEach(new Consumer<WorkInfo>() {
                                        @Override
                                        public void accept(WorkInfo workInfoSingle) {
                                            if (workInfoSingle.getState().compareTo(WorkInfo.State.SUCCEEDED) == 0) {
                                                Integer CallBaskОтWorkManager =
                                                        workInfoSingle.getOutputData().getInt("ReturnSingleAsyncWork", 0);
                                                long end = Calendar.getInstance().getTimeInMillis();
                                                long РазницаВоврмени = end - startДляОбноразвовной;
                                                if (РазницаВоврмени > 10000) {
                                                    if (CallBaskОтWorkManager > 0) {
                                                        методGetCursorReboot();
                                                        // TODO: 12.05.2023
                                                        WorkManager.getInstance(getContext())
                                                                .getWorkInfosByTagLiveData(ИмяСлужбыСинхронизациОдноразовая).removeObservers(lifecycleOwnerSingle);
                                                        // TODO: 23.05.2023
                                                    }
                                                }
                                            }
                                            if (workInfoSingle.getState().compareTo(WorkInfo.State.RUNNING) != 0) {
                                                // TODO: 23.05.2023  програсс бар
                                                методГазимПрогрессаБар();
                                            }
                                        }
                                    });


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
                        });

                // TODO: 29.09.2021  конец синхрониазции по раписанию
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        // TODO: 18.10.2021  слушатель  work manager #1
        @SuppressLint("FragmentLiveDataObserve")
        void методСлушателяWorkManagerОбщая(@NonNull LifecycleOwner lifecycleOwnerОбщая) {
// TODO: 11.05.2021 ЗПУСКАЕМ СЛУЖБУ через брдкастер синхронизхации и уведомления
            try {
                lifecycleOwnerОбщая.getLifecycle().addObserver(new LifecycleEventObserver() {
                    @Override
                    public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
                        source.getLifecycle().getCurrentState();
                        event.getTargetState().name();
                    }
                });

                WorkManager.getInstance(getContext()).getWorkInfosByTagLiveData(ИмяСлужбыСинхронизацииОбщая)
                        .observe(lifecycleOwnerОбщая, new Observer<List<WorkInfo>>() {
                            @Override
                            public void onChanged(List<WorkInfo> workInfosОбщая) {
                                try {
                                    workInfosОбщая.forEach(new Consumer<WorkInfo>() {
                                        @Override
                                        public void accept(WorkInfo workInfoОбщая) {
                                            if (workInfoОбщая.getState().compareTo(WorkInfo.State.SUCCEEDED) == 0
                                                    || workInfoОбщая.getState().compareTo(WorkInfo.State.ENQUEUED) == 0) {
                                                Integer CallBaskОтWorkManager =
                                                        workInfoОбщая.
                                                                getOutputData().getInt("ReturnSingleAsyncWork", 0);
                                                long end = Calendar.getInstance().getTimeInMillis();
                                                long РазницаВоврмени = end - startДляОбноразвовной;
                                                if (РазницаВоврмени > 25000) {
                                                    методGetCursorReboot();
                                      /*      // TODO: 23.05.2023  экран
                                            WorkManager.getInstance(getContext()).getWorkInfosByTagLiveData(ИмяСлужбыСинхронизацииОбщая).removeObservers(lifecycleOwnerОбщая);*/
                                                }
                                            }
                                            if (workInfoОбщая.getState().compareTo(WorkInfo.State.RUNNING) != 0) {
                                                // TODO: 23.05.2023  програсс бар
                                                методГазимПрогрессаБар();
                                            }
                                        }

                                    });


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
                        });
                // TODO: 29.09.2021  конец синхрониазции по раписанию
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        private void методГазимПрогрессаБар() {
            try {
                message.getTarget().postDelayed(() -> {
                    progressBarСканирование.setVisibility(View.INVISIBLE);
                }, 500);
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

        private void МетодСлушательКурсора() {
            // TODO: 15.10.2022  слушатиель для курсора
            try {
                // TODO: 29.05.2023
                Cursor cursor = myRecycleViewAdapter.cursor;
                if (cursor != null && cursor.isClosed() == false) {
                    cursor.registerDataSetObserver(new DataSetObserver() {
                        @Override
                        public void onChanged() {
                            super.onChanged();
                            Log.d(this.getClass().getName(), "cursor   " + cursor);
                        }

                        @Override
                        public void onInvalidated() {
                            super.onInvalidated();
                            Log.d(this.getClass().getName(), "cursor   " + cursor);
                        }
                    });
                    // TODO: 15.10.2022
                    cursor.registerContentObserver(new ContentObserver(message.getTarget()) {
                        @Override
                        public boolean deliverSelfNotifications() {
                            Log.d(this.getClass().getName(), "cursor   " + cursor);
                            return super.deliverSelfNotifications();
                        }

                        @Override
                        public void onChange(boolean selfChange) {
                            Log.d(this.getClass().getName(), "cursor   " + cursor);
                            super.onChange(selfChange);
                        }

                        @Override
                        public void onChange(boolean selfChange, @Nullable Uri uri) {
                            Log.d(this.getClass().getName(), "cursor   " + cursor);
                            super.onChange(selfChange, uri);
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                        Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
        // TODO: 28.04.2023

        private void МетодПерегрузкаRecyceView() {
            try {
                recyclerView_OrderTransport.requestLayout();
                recyclerView_OrderTransport.refreshDrawableState();
                BottomNavigationOrderTransport.refreshDrawableState();
                BottomNavigationOrderTransport.requestLayout();
                Log.d(getContext().getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
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


        protected void методNewOrderTransport() {
            try {
                fragmentManager.clearBackStack(null);
                fragmentTransaction = fragmentManager.beginTransaction();
                //  fragmentTransaction.addToBackStack(null);
                fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                fragmentNewЗаказТранспорта = new FragmentNewOrderTransport();
                Bundle bundleNewOrderTransport = new Bundle();
                bundleNewOrderTransport.putBinder("binder", (ServiceOrserTransportService.LocalBinderOrderTransport) localBinderOrderTransport);
                bundleNewOrderTransport.putInt("isalive", 1);
                fragmentNewЗаказТранспорта.setArguments(bundleNewOrderTransport);
                fragmentTransaction.remove(fragmentManager.getFragments().get(0));
                fragmentTransaction.replace(R.id.linearLayout_root_activity_main, fragmentNewЗаказТранспорта).setReorderingAllowed(true).commit();//.layout.activity_for_fragemtb_history_tasks
                fragmentTransaction.show(fragmentNewЗаказТранспорта);
                linearLayout_orders_transport.refreshDrawableState();
                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " localBinderOrderTransport " + localBinderOrderTransport);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                        + Thread.currentThread().getStackTrace()[2].getMethodName().toString() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).
                        recordnewerror(e.toString(),
                                this.getClass().getName().toString(),
                                Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        // TODO: 02.08.2022
        private Cursor методGetCursorGROUPBYBounds() {
            Cursor cursorGroupByParent = null;
            try {
                LinkedHashMap<String, String> linkedHashMapДеньМесяцГод;
                if (localBinderOrderTransport != null) {
                    linkedHashMapДеньМесяцГод = localBinderOrderTransport.методGetТриЗначениеГодМесяцДень();
                    // TODO: 04.05.2023  получаем первоночальыне Данные  #1
                    String Текущаятаблицы = "view_ordertransport";
                    ModuleQuety moduleQuety = new ModuleQuety(getContext());
                    cursorGroupByParent = moduleQuety.getModuleQuery(Текущаятаблицы,
                            " SELECT DISTINCT  _id, dateorders, status,\" +\n" +
                                    "                            \"  strftime('%Y', dateorders)  AS Year, strftime('%m', dateorders)  AS Month,\" +\n" +
                                    "                            \" strftime('%d', dateorders)  AS Day, COUNT(*) AS getcounts\" +\n" +
                                    "                            \"  FROM  " + Текущаятаблицы + "  WHERE dateorders  IS NOT NULL " +
                                    " AND    date(dateorders) >=" +
                                    " date('" + linkedHashMapДеньМесяцГод.get("Год") + "-" +
                                    linkedHashMapДеньМесяцГод.get("Месяц") + "-"
                                    + linkedHashMapДеньМесяцГод.get("День") + "') AND status!='5'  " +
                                    " GROUP BY strftime('%Y', dateorders)  ," +
                                    " strftime('%m', dateorders)   ," +
                                    " strftime('%d', dateorders) ," +
                                    " dateorders   HAVING        (COUNT(*) > 0)   " +
                                    " ORDER by  strftime('%Y', dateorders) DESC , " +
                                    "strftime('%m', dateorders) DESC  ,strftime('%d', dateorders) DESC ", null);

                    // TODO: 04.05.2023  перегружаем экран
                    Log.d(getContext().getClass().getName(), "\n"
                            + " время: " + new Date() + "\n+" +
                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                            + "   localBinderOrderTransport.isBinderAlive()" + localBinderOrderTransport.isBinderAlive()
                            + " cursorGroupByParent " + cursorGroupByParent);
                }
                // TODO: 04.05.2023  перегружаем экран
                Log.d(getContext().getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                        + " cursorGroupByParent " + cursorGroupByParent);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                        + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return cursorGroupByParent;
        }

        void методGetCursorReboot() {
            try {
                if (myRecycleViewAdapter.cursor != null) {
                    Cursor cursorПереполучаемДанные = myRecycleViewAdapter.cursor; //      методGetCursorBounds();
                    cursorПереполучаемДанные = методGetCursorGROUPBYBounds(); //      методGetCursorBounds();
                    myRecycleViewAdapter.cursor = cursorПереполучаемДанные;
                    myRecycleViewAdapter.notifyDataSetChanged();
                    // TODO: 23.05.2023  даннеы
                    RecyclerView.Adapter recyclerView = recyclerView_OrderTransport.getAdapter();
                    recyclerView_OrderTransport.swapAdapter(recyclerView, true);
                    recyclerView_OrderTransport.getAdapter().notifyDataSetChanged();

                    // TODO: 10.06.2023
                    МетодПерегрузкаRecyceView();
                }
                Log.d(this.getClass().getName(), "\n" + " class " +
                        Thread.currentThread().getStackTrace()[2].getClassName()
                        + "\n" +
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


        // TODO: 28.04.2023  ALL Adapters

        private void методЗаполенияЗаказаТранспорта(@NonNull Bundle bundleOrderTransport,
                                                    @NonNull MaterialTextView materialTextView,
                                                    @NonNull Object ЗначениеВставки) {
            try {
                if (materialTextView != null) {
                    if (ЗначениеВставки == null) {
                        materialTextView.setHint("не заполнено");
                        materialTextView.setText("");
                    } else {
                        materialTextView.setText(ЗначениеВставки.toString());
                    }
                    materialTextView.setTag(bundleOrderTransport);
                }
                Log.d(getContext().getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                        + "  bundleOrderTransport " + bundleOrderTransport);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        // TODO: 05.05.2023  треьтий этап
        @SuppressLint("Range")
        Bundle методGosNomersДанныеBungle(@NonNull Cursor cursor) {
            Bundle bundleOrderTransport = new Bundle();
            try {
                Long uuid = Optional.ofNullable(cursor.getLong(cursor.getColumnIndex("uuid"))).orElse(0l);
                String name = Optional.ofNullable(cursor.getString(cursor.getColumnIndex("name"))).orElse("нет");
                String dateorders = Optional.ofNullable(cursor.getString(cursor.getColumnIndex("dateorders"))).orElse("нет");
                Integer _id = Optional.ofNullable(cursor.getInt(cursor.getColumnIndex("_id"))).orElse(0);
                String number_order = Optional.ofNullable(cursor.getString(cursor.getColumnIndex("number_order"))).orElse("нет");
                Integer user_update = Optional.ofNullable(cursor.getInt(cursor.getColumnIndex("user_update"))).orElse(0);
                String cfo = Optional.ofNullable(cursor.getString(cursor.getColumnIndex("cfo"))).orElse("нет");
                Integer status = Optional.ofNullable(cursor.getInt(cursor.getColumnIndex("status"))).orElse(0);
                String gosmomer = Optional.ofNullable(cursor.getString(cursor.getColumnIndex("gosmomer"))).orElse("нет");
                // TODO: 12.05.2023 ПАРСИНГА  Dates
                dateorders = методПарсингаДатыЗаказа(dateorders);
                // TODO: 12.05.2023 ПАРСИНГА  СТАТУСА
                String Статус = методПарсингаСтатуса(status);
                // TODO: 18.04.2023 Данные Заказы Трансопрта
                bundleOrderTransport.putLong("uuid", uuid);
                bundleOrderTransport.putInt("position", cursor.getPosition());
                bundleOrderTransport.putString("name", name.trim());
                bundleOrderTransport.putString("dateorders", dateorders.trim());
                bundleOrderTransport.putInt("_id", _id);
                bundleOrderTransport.putString("number_order", number_order.trim());
                bundleOrderTransport.putInt("user_update", user_update);
                bundleOrderTransport.putString("cfo", cfo.trim());
                bundleOrderTransport.putString("status", Статус.trim());
                bundleOrderTransport.putString("fullname", gosmomer.trim());
                // TODO: 12.05.2023 ДАННЫЕ
                Log.d(getContext().getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                        + "  cursor " + cursor + "bundleOrderTransport " + bundleOrderTransport);

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());

            }
            return bundleOrderTransport;
        }

        // TODO: 05.05.2023  первый этап
        @SuppressLint("Range")
        Bundle методGroupByДанныеBungle(@NonNull Cursor cursor) {
            Bundle bundleGrpuopByOrder = new Bundle();
            try {
                Integer Year = Optional.ofNullable(cursor.getInt(cursor.getColumnIndex("Year"))).orElse(0);
                Integer Month = Optional.ofNullable(cursor.getInt(cursor.getColumnIndex("Month"))).orElse(0);
                @SuppressLint("Range") Integer Day = Optional.ofNullable(cursor.getInt(cursor.getColumnIndex("Day"))).orElse(0);
                Integer getcounts = Optional.ofNullable(cursor.getInt(cursor.getColumnIndex("getcounts"))).orElse(0);
                String dateorders = Optional.ofNullable(cursor.getString(cursor.getColumnIndex("dateorders"))).orElse("");
                // TODO: 25.05.2023 Дата
                String dateordersForCFO = dateorders;

                dateorders = методПарсингаДатыЗаказа(dateorders);
                // TODO: 18.04.2023 Данные Заказы Трансопрта
                bundleGrpuopByOrder.putInt("Year", Year);
                bundleGrpuopByOrder.putInt("position", cursor.getPosition());
                bundleGrpuopByOrder.putInt("Month", Month);
                bundleGrpuopByOrder.putInt("Day", Day);
                bundleGrpuopByOrder.putInt("getcounts", getcounts);
                bundleGrpuopByOrder.putString("dateorders", dateorders.trim());
                bundleGrpuopByOrder.putString("dateordersForCFO", dateordersForCFO.trim());
                // TODO: 12.05.2023 ДАННЫЕ
                Log.d(getContext().getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                        + "  cursor " + cursor + "bundleGrpuopByOrder " + bundleGrpuopByOrder + " dateordersForCFO " + dateordersForCFO);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                        Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());

            }
            return bundleGrpuopByOrder;
        }

        @NonNull
        private String методПарсингаДатыЗаказа(@NonNull String DateOrder) throws ParseException {
            try {
                // SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yyyy", new Locale("ru"));
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", new Locale("ru"));
                Date breamy = simpleDateFormat.parse(DateOrder);
                DateOrder = simpleDateFormat.format(breamy);


                Calendar myCal = new GregorianCalendar();
                myCal.setTime(breamy);
                DateOrder = new SimpleDateFormat("dd,MMM yyyy").format(myCal.getTime());

                Log.d(getContext().getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                        + "  DateOrder " + DateOrder);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());

            }
            return DateOrder;
        }

        private String методПарсингаСтатуса(Integer Status) throws ParseException {
            String Статус = null;
            try {
                switch (Status) {
                    case 0:
                        Статус = "Созданно";
                        break;
                    case 1:
                        Статус = "В работе";
                        break;
                    case 2:
                        Статус = "Закончено";
                        break;
                    case 3:
                        Статус = "Отмена";
                        break;
                }

                Log.d(getContext().getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                        + "  Status " + Status);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return Статус;
        }

        // TODO: 12.05.2023
        private void МетодЗапускаАнимацииКнопок(View v) {
            v.animate().rotationX(-40l);
            message.getTarget().postDelayed(() -> {
                v.animate().rotationX(0);
            }, 300);
        }

        private void МетодКпопкиЗначков() {
            try {
                if (myRecycleViewAdapter.cursor != null && myRecycleViewAdapter.cursor.getCount() > 0 && myRecycleViewAdapter.cursor.isClosed() == false) {
                    Log.d(this.getClass().getName(), "  myRecycleViewAdapter.cursor" + myRecycleViewAdapter.cursor.getCount());
                    BottomNavigationOrderTransport.getOrCreateBadge(R.id.id_async).setNumber(myRecycleViewAdapter.cursor.getCount());//.getOrCreateBadge(R.id.id_taskHome).setVisible(true);
                    BottomNavigationOrderTransport.getOrCreateBadge(R.id.id_async).setBackgroundColor(Color.parseColor("#15958A"));
                } else {
                    BottomNavigationOrderTransport.getOrCreateBadge(R.id.id_async).setNumber(0);//.getOrCreateBadge(R.id.id_taskHome).setVisible(true);
                    BottomNavigationOrderTransport.getOrCreateBadge(R.id.id_async).setBackgroundColor(Color.RED);
                }
                //TODO
                Log.d(getContext().getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + " myRecycleViewAdapter.cursor " + myRecycleViewAdapter.cursor);
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

        // TODO: 02.07.2023 история
        private void методИсторияFragment() {
            try {
                bottomNavigationItemView4История.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        fragmentManager.clearBackStack(null);
                        fragmentTransaction = fragmentManager.beginTransaction();
                        //  fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        fragmentNewЗаказТранспорта = new FragmentOrderTransportOneChaneStory();
                        Bundle bundleNewOrderTransport = new Bundle();
                        bundleNewOrderTransport.putBinder("binder", (ServiceOrserTransportService.LocalBinderOrderTransport) localBinderOrderTransport);
                        bundleNewOrderTransport.putInt("isalive", 1);
                        fragmentNewЗаказТранспорта.setArguments(bundleNewOrderTransport);
                        fragmentTransaction.remove(fragmentManager.getFragments().get(0));
                        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                        fragmentTransaction.replace(R.id.linearLayout_root_activity_main, fragmentNewЗаказТранспорта).setReorderingAllowed(true).commit();//.layout.activity_for_fragemtb_history_tasks
                        fragmentTransaction.show(fragmentNewЗаказТранспорта);
                        linearLayout_orders_transport.refreshDrawableState();
                        Log.d(getContext().getClass().getName(), "\n"
                                + " время: " + new Date() + "\n+" +
                                " Класс в процессе... " + this.getClass().getName() + "\n" +
                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + " myRecycleViewAdapter.cursor " + myRecycleViewAdapter.cursor);
                    }
                });
                //TODO
                Log.d(getContext().getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + " myRecycleViewAdapter.cursor " + myRecycleViewAdapter.cursor);
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

        class SubClassAdapters {
            public void МетодБиндингOrderTransportGetCursor() {
                try {
                    Intent intentЗапускOrserTransportService = new Intent(getContext(), ServiceOrserTransportService.class);
                    intentЗапускOrserTransportService.setAction("intentЗапускOrserTransportService");
                    serviceConnection = new ServiceConnection() {
                        @Override
                        public void onServiceConnected(ComponentName name, IBinder service) {
                            try {
                                if (service.isBinderAlive()) {
                                    localBinderOrderTransport = (ServiceOrserTransportService.LocalBinderOrderTransport) service;
                                    // TODO: 23.05.2023  даннеы
                                    asyncTaskLoader = new AsyncTaskLoader(getContext()) {
                                        @Override
                                        protected void onStartLoading() {
                                            super.onStartLoading();

                                            Log.d(getContext().getClass().getName(), "\n"
                                                    + " время: " + new Date() + "\n+" +
                                                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                                    + "   localBinderOrderTransport.isBinderAlive()" + localBinderOrderTransport.isBinderAlive() +
                                                    " localBinderOrderTransport " + localBinderOrderTransport
                                                    + "   localBinderOrderTransport.isBinderAlive()" + localBinderOrderTransport.isBinderAlive());
                                        }

                                        @Override
                                        public void forceLoad() {
                                            super.forceLoad();
                                            Log.d(getContext().getClass().getName(), "\n"
                                                    + " время: " + new Date() + "\n+" +
                                                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                                    + "   localBinderOrderTransport.isBinderAlive()" + localBinderOrderTransport.isBinderAlive() +
                                                    " localBinderOrderTransport " + localBinderOrderTransport
                                                    + "   localBinderOrderTransport.isBinderAlive()" + localBinderOrderTransport.isBinderAlive());
                                        }

                                        @Override
                                        public void commitContentChanged() {
                                            super.commitContentChanged();
                                            // TODO: 17.07.2023
                                            if (cursor != null) {
                                                // TODO: 23.05.2023  экран
                                                onStart();
                                            }

                                            Log.d(getContext().getClass().getName(), "\n"
                                                    + " время: " + new Date() + "\n+" +
                                                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                                    + "   localBinderOrderTransport.isBinderAlive()" + localBinderOrderTransport.isBinderAlive() +
                                                    " localBinderOrderTransport " + localBinderOrderTransport
                                                    + "   localBinderOrderTransport.isBinderAlive()" + localBinderOrderTransport.isBinderAlive());
                                        }

                                        @Nullable
                                        @Override
                                        public Object loadInBackground() {
                                            try {
                                                getContext().getMainExecutor().execute(() -> {
                                                    cursor = subClassOrdersTransport.методGetCursorGROUPBYBounds(); //      методGetCursorBounds();
                                                    Log.d(getContext().getClass().getName(), "\n"
                                                            + " время: " + new Date() + "\n+" +
                                                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                                                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                                            + "   localBinderOrderTransport.isBinderAlive()" + localBinderOrderTransport.isBinderAlive() +
                                                            " localBinderOrderTransport " + localBinderOrderTransport
                                                            + "   localBinderOrderTransport.isBinderAlive()" + localBinderOrderTransport.isBinderAlive());
                                                });

                                            } catch (Exception e) {
                                                e.printStackTrace();
                                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                                                        Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                                                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                                            }
                                            return cursor;
                                        }
                                    };
                                    if (!asyncTaskLoader.isStarted()) {
                                        asyncTaskLoader.startLoading();
                                        asyncTaskLoader.forceLoad();
                                    }

                                    Log.d(getContext().getClass().getName(), "\n"
                                            + " время: " + new Date() + "\n+" +
                                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                            + "   localBinderOrderTransport.isBinderAlive()" + localBinderOrderTransport.isBinderAlive() +
                                            " localBinderOrderTransport " + localBinderOrderTransport
                                            + "   localBinderOrderTransport.isBinderAlive()" + localBinderOrderTransport.isBinderAlive());
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                                        Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                        }

                        @Override
                        public void onServiceDisconnected(ComponentName name) {
                            try {
                                localBinderOrderTransport = null;
                                Log.d(getContext().getClass().getName(), "\n"
                                        + " время: " + new Date() + "\n+" +
                                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                                        Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                                        Thread.currentThread().getStackTrace()[2].getMethodName(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                                // TODO: 11.05.2021 запись ошибок

                            }
                        }
                    };
                    Boolean isBound = getContext().bindService(intentЗапускOrserTransportService, serviceConnection, Context.BIND_AUTO_CREATE);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(),
                            this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }

            public void МетодБиндингOrderTransportGetCursorTwo() {
                try {
                    Intent intentЗапускOrserTransportService = new Intent(getContext(), ServiceOrserTransportService.class);
                    intentЗапускOrserTransportService.setAction("intentЗапускOrserTransportService");
                    serviceConnection = new ServiceConnection() {
                        @Override
                        public void onServiceConnected(ComponentName name, IBinder service) {
                            try {
                                if (service.isBinderAlive()) {
                                    localBinderOrderTransport = (ServiceOrserTransportService.LocalBinderOrderTransport) service;
                                    // TODO: 23.05.2023  даннеы

                                    getContext().getMainExecutor().execute(() -> {
                                        cursor = subClassOrdersTransport.методGetCursorGROUPBYBounds(); //      методGetCursorBounds();
                                        Log.d(getContext().getClass().getName(), "\n"
                                                + " время: " + new Date() + "\n+" +
                                                " Класс в процессе... " + this.getClass().getName() + "\n" +
                                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                                + "   localBinderOrderTransport.isBinderAlive()" + localBinderOrderTransport.isBinderAlive() +
                                                " localBinderOrderTransport " + localBinderOrderTransport
                                                + "   localBinderOrderTransport.isBinderAlive()" + localBinderOrderTransport.isBinderAlive());
                                    });

                                    if (cursor != null) {
                                        // TODO: 23.05.2023  экран
                                        onStart();
                                    }

                                    Log.d(getContext().getClass().getName(), "\n"
                                            + " время: " + new Date() + "\n+" +
                                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                            + "   localBinderOrderTransport.isBinderAlive()" + localBinderOrderTransport.isBinderAlive() +
                                            " localBinderOrderTransport " + localBinderOrderTransport
                                            + "   localBinderOrderTransport.isBinderAlive()" + localBinderOrderTransport.isBinderAlive());
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                                        Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                                        this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                        }

                        @Override
                        public void onServiceDisconnected(ComponentName name) {
                            try {
                                localBinderOrderTransport = null;
                                Log.d(getContext().getClass().getName(), "\n"
                                        + " время: " + new Date() + "\n+" +
                                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
                                        Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                                        Thread.currentThread().getStackTrace()[2].getMethodName(),
                                        Thread.currentThread().getStackTrace()[2].getLineNumber());
                                // TODO: 11.05.2021 запись ошибок

                            }
                        }
                    };
                    Boolean isBound = getContext().bindService(intentЗапускOrserTransportService, serviceConnection, Context.BIND_AUTO_CREATE);
                    getContext().registerComponentCallbacks(new ComponentCallbacks() {
                        @Override
                        public void onConfigurationChanged(@NonNull Configuration newConfig) {
                            Log.d(getContext().getClass().getName(), "\n"
                                    + " время: " + new Date() + "\n+" +
                                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + "localBinderOrderTransport " +
                                    localBinderOrderTransport);
                        }

                        @Override
                        public void onLowMemory() {
                            ;
                            Log.d(getContext().getClass().getName(), "\n"
                                    + " время: " + new Date() + "\n+" +
                                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(),
                            this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }
            // TODO: 25.05.2023 Adapter Simple

            // TODO: 09.06.2023 Метод Клика Для Удаления  Строка
            private void методКликаПоДочернейRow(@NonNull TableRow tableRowДочерная) {
                try {
                    tableRowДочерная.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            try {
                                // TODO: 14.06.2023
                                v.setBackgroundColor(Color.GRAY);
                                message.getTarget().postDelayed(() -> {
                                    v.setBackgroundColor(Color.WHITE);
                                }, 1000);

                                Bundle bundleУдалениСтрочки = (Bundle) v.getTag();
                                Long UUIDДляУдалениеRow = bundleУдалениСтрочки.getLong("SuccessAddRow");
                                Integer Successid_Status = bundleУдалениСтрочки.getInt("Successid_Status");
                                String Successid_Name = bundleУдалениСтрочки.getString("Successid_Name").trim();
                                if (Successid_Status == 0) {
                                    // TODO: 14.06.2023 удалание
                                    // TODO: 15.06.2023 Удаление Строчки Вфыбраной ЗАКАЗА
                                    Snackbar snackbar = Snackbar.make(v, "Удаление заказа ", Snackbar.LENGTH_INDEFINITE).addCallback(new Snackbar.Callback() {


                                        @Override
                                        public void onShown(Snackbar sb) {
                                            super.onShown(sb);
                                            Log.d(getContext().getClass().getName(), "\n"
                                                    + " время: " + new Date() + "\n+" +
                                                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                                    + " Successid_Status " + Successid_Status);
                                        }

                                        @Override
                                        public void onDismissed(Snackbar transientBottomBar, int event) {
                                            super.onDismissed(transientBottomBar, event);
                                            Log.d(getContext().getClass().getName(), "\n"
                                                    + " время: " + new Date() + "\n+" +
                                                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                                    + " Successid_Status " + Successid_Status);
                                        }
                                    });
                                    /*                     Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();*/
                                    snackbar.setActionTextColor(Color.WHITE)
                                            .setTextColor(Color.GRAY)
                                            .setDuration(4000);
                                    View view = snackbar.getView();
                                    TextView textView = (TextView) view.findViewById(R.id.snackbar_text);
                                    textView.setTextColor(Color.parseColor("#F0FFFF"));
                                    textView.setText(Successid_Name);
                                    // TODO: 15.06.2023
                                    TextView viewСохранеие = (TextView) view.findViewById(R.id.snackbar_action);
                                    viewСохранеие.setTextColor(Color.parseColor("#FFFFFF"));
                                    // TODO: 15.06.2023  Кнопа Действие
                                    snackbar.setAction("Удалить заказ ?", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            snackbar.dismiss();
                                            message.getTarget().post(() -> {
                                                // TODO: 15.06.2023 удаление Заказа ROW
                                                Integer РезультатаУдалениеRow = localBinderOrderTransport.методВиндингУдалениеЗаказа(UUIDДляУдалениеRow);
                                                if (РезультатаУдалениеRow > 0) {
                                                    v.startAnimation(animationvibr1);
                                                    tableRowДочерная.setVisibility(View.GONE);
                                                    message.getTarget().postDelayed(() -> {
                                                        методGetCursorReboot();
                                                    }, 150);
                                                }
                                                Log.d(getContext().getClass().getName(), "\n"
                                                        + " время: " + new Date() + "\n+" +
                                                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                                                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                                        + " Successid_Status " + Successid_Status + "РезультатаУдалениеRow " + РезультатаУдалениеRow);
                                            });

                                            Log.d(getContext().getClass().getName(), "\n"
                                                    + " время: " + new Date() + "\n+" +
                                                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                                    + " Successid_Status " + Successid_Status);
                                        }
                                    }).isShown();

                                    snackbar.show();
                                    Log.d(getContext().getClass().getName(), "\n"
                                            + " время: " + new Date() + "\n+" +
                                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                            + " Successid_Status " + Successid_Status);
                                } else {
                                    Snackbar snackbar = Snackbar.make(v, "Заказ нельзя удалить !!!", Snackbar.LENGTH_LONG).setAction("Action", null);
                                    snackbar.show();
                                    Log.d(getContext().getClass().getName(), "\n"
                                            + " время: " + new Date() + "\n+" +
                                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                            + " bundleУдалениСтрочки " + bundleУдалениСтрочки);

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
                            return true;
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

            // TODO: 01.06.2023  выравниваем дизайн линию
            private void методВыравнивемЛиниюДизайнВСтавлнойRow(@NonNull TableRow tableRowДочернаяУжеВсатвновое
                    , @NonNull TableLayout tableLayoutРодительская) {
                try {
                    // TODO: 26.05.2023  Элемент Для Шабки
                    MaterialTextView materialTextViewДанныеAddRowГосНомер = tableRowДочернаяУжеВсатвновое.findViewById(R.id.ot_value_ordertype_gos_nomer);
                    // TODO: 26.05.2023  Элемент Для Шабки
                    MaterialTextView materialTextViewДанныеAddRowВидТс = tableRowДочернаяУжеВсатвновое.findViewById(R.id.ot_value_ordertype_ts);

                    materialTextViewДанныеAddRowГосНомер.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        @Override
                        public void onGlobalLayout() {
                            materialTextViewДанныеAddRowГосНомер.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                            int heightГосНомер = materialTextViewДанныеAddRowГосНомер.getHeight();
                            int heightТипТС = materialTextViewДанныеAddRowВидТс.getHeight();
                            if (heightГосНомер > heightТипТС) {
                                materialTextViewДанныеAddRowВидТс.setHeight(heightГосНомер);
                            } else {
                                if (heightТипТС > heightГосНомер) {
                                    materialTextViewДанныеAddRowГосНомер.setHeight(heightТипТС);
                                }
                            }
                            tableLayoutРодительская.requestLayout();
                            tableLayoutРодительская.refreshDrawableState();
                        }
                    });


                    Log.d(getContext().getClass().getName(), "\n"
                            + " время: " + new Date() + "\n+" +
                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + " tableRowДочернаяУжеВсатвновое " + tableRowДочернаяУжеВсатвновое);
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

            // TODO: 30.05.2023 Получение Get получение Дочерненго Элемента
            @SuppressLint("Range")
            @NonNull
            private TableRow методGettableДочернаяForCFO(@NonNull Cursor cursorListCFO, @NonNull View convertViewДочерния) {
                TableRow tableRowДочерная = null;
                try {
                    TableLayout tableLayoutДочернная = (TableLayout) convertViewДочерния.findViewById(R.id.tablelayout_singleotrow_cfo);
                    // TODO: 26.05.2023  цикл может сказать главный идем по СЦО #1
                    tableRowДочерная = (TableRow) tableLayoutДочернная.findViewById(R.id.tableRowChildOtCFO);
                    if (tableRowДочерная.getParent() != null) {
                        /*       ((ViewGroup)tableRowДочерная.getParent()).recomputeViewAttributes(tableRowДочерная); // <- fix
                         *//*     ((ViewGroup)tableRowДочерная.getParent()).removeViewInLayout(tableRowДочерная); // <- fix*/
                        ((ViewGroup) tableRowДочерная.getParent()).removeView(tableRowДочерная); // <- fix
                    }
                    if (cursorListCFO != null && cursorListCFO.getCount() > 0) {
                         Long SuccessAddRow = cursorListCFO.getLong(cursorListCFO.getColumnIndex("uuid"));
                        Long Successid_cfo = cursorListCFO.getLong(cursorListCFO.getColumnIndex("uuid_cfo"));
                        String Successname_cfo = cursorListCFO.getString(cursorListCFO.getColumnIndex("cfo")).trim();
                        Bundle bundleДочерний = new Bundle();
                        bundleДочерний.putLong("SuccessAddRow", SuccessAddRow);
                        bundleДочерний.putLong("Successid_cfo", Successid_cfo);
                        bundleДочерний.putString("Successname_cfo", Successname_cfo);
                        tableRowДочерная.setTag(bundleДочерний);
                        Log.d(getContext().getClass().getName(), "\n"
                                + " время: " + new Date() + "\n+" +
                                " Класс в процессе... " + this.getClass().getName() + "\n" +
                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + "  SuccessAddRow " + SuccessAddRow);
                    }
                    Log.d(getContext().getClass().getName(), "\n"
                            + " время: " + new Date() + "\n+" +
                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(getContext().getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(),
                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                return tableRowДочерная;
            }

            // TODO: 30.05.2023 Получение Get получение Дочерненго Элемента
            @SuppressLint("Range")
            @NonNull
            private TableRow методGettableДочернаяForTypeTC(@NonNull Cursor cursorgetTypeTSAndGosNomers) {
                TableRow tableRowДочерная = null;
                try {
                    View convertViewДочерния = LayoutInflater.from(getContext()).inflate(R.layout.fragment_order_trasport_for_single_row_type_tc,
                            null, false);
                    TableLayout tableLayoutДочернная = (TableLayout) convertViewДочерния.findViewById(R.id.tablelayout_singleotrow_type_tc);
                    // TODO: 26.05.2023  цикл может сказать главный идем по СЦО #1
                    tableRowДочерная = (TableRow) tableLayoutДочернная.findViewById(R.id.tableRowChildOt_typets);
                    if (tableRowДочерная.getParent() != null) {
                        /*       ((ViewGroup)tableRowДочерная.getParent()).recomputeViewAttributes(tableRowДочерная); // <- fix
                         *//*     ((ViewGroup)tableRowДочерная.getParent()).removeViewInLayout(tableRowДочерная); // <- fix*/
                        ((ViewGroup) tableRowДочерная.getParent()).removeView(tableRowДочерная); // <- fix
                    }
                    if (cursorgetTypeTSAndGosNomers != null && cursorgetTypeTSAndGosNomers.getCount() > 0) {
                        Long SuccessAddRow = cursorgetTypeTSAndGosNomers.getLong(cursorgetTypeTSAndGosNomers.getColumnIndex("uuid"));
                        Long Successid_uuid_track = cursorgetTypeTSAndGosNomers.getLong(cursorgetTypeTSAndGosNomers.getColumnIndex("uuid_vid_tc"));
                        Long Successid_Gosnomer = cursorgetTypeTSAndGosNomers.getLong(cursorgetTypeTSAndGosNomers.getColumnIndex("uuid_track"));
                        Integer Successid_Status = cursorgetTypeTSAndGosNomers.getInt(cursorgetTypeTSAndGosNomers.getColumnIndex("status"));
                        String Successid_Name = cursorgetTypeTSAndGosNomers.getString(cursorgetTypeTSAndGosNomers.getColumnIndex("name"));
                        Bundle bundleДочерний = new Bundle();
                        bundleДочерний.putLong("SuccessAddRow", SuccessAddRow);
                        bundleДочерний.putLong("Successid_uuid_track", Successid_uuid_track);
                        bundleДочерний.putLong("Successid_Gosnomer", Successid_Gosnomer);
                        bundleДочерний.putInt("Successid_Status", Successid_Status);
                        bundleДочерний.putString("Successid_Name", Successid_Name);
                        tableRowДочерная.setTag(bundleДочерний);
                        Log.d(getContext().getClass().getName(), "\n"
                                + " время: " + new Date() + "\n+" +
                                " Класс в процессе... " + this.getClass().getName() + "\n" +
                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + "  SuccessAddRow " + SuccessAddRow);
                    }
                    Log.d(getContext().getClass().getName(), "\n"
                            + " время: " + new Date() + "\n+" +
                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(getContext().getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(),
                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                return tableRowДочерная;
            }

            @NonNull
            private TableRow методGettableДочернаяForTypeTCUP(@NonNull Cursor cursorgetTypeTSAndGosNomers) {
                TableRow tableRowДочерная = null;
                try {
                    View convertViewДочерния = LayoutInflater.from(getContext()).inflate(R.layout.fragment_order_trasport_for_single_row_type_tc_up,
                            null, false);
                    TableRow tableLayoutДочернная = (TableRow) convertViewДочерния.findViewById(R.id.tableRowChildOt_typets_up);
                    // TODO: 26.05.2023  цикл может сказать главный идем по СЦО #1
                    tableRowДочерная = (TableRow) tableLayoutДочернная.findViewById(R.id.tableRowChildOt_typets_up);
                    if (tableRowДочерная.getParent() != null) {
                        /*       ((ViewGroup)tableRowДочерная.getParent()).recomputeViewAttributes(tableRowДочерная); // <- fix
                         *//*     ((ViewGroup)tableRowДочерная.getParent()).removeViewInLayout(tableRowДочерная); // <- fix*/
                        ((ViewGroup) tableRowДочерная.getParent()).removeView(tableRowДочерная); // <- fix
                    }

                    MaterialTextView materialTextViewДанныеAddRowType = tableRowДочерная.findViewById(R.id.ot_key_ordertype_ts);
                    MaterialTextView materialTextViewДанныеAddRowGosNomer = tableRowДочерная.findViewById(R.id.ot_key_ordertype_godnomer);
                    // TODO: 29.05.2023 Заполеяем  Вид ТС

                    materialTextViewДанныеAddRowType.setText("Вид тс");
                    materialTextViewДанныеAddRowGosNomer.setText("Гос.номер");


                    Log.d(getContext().getClass().getName(), "\n"
                            + " время: " + new Date() + "\n+" +
                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(getContext().getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(),
                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                return tableRowДочерная;
            }

            // TODO: 30.05.2023 Получение Get получение Дочерненго Элемента
            @SuppressLint("Range")
            @NonNull
            private TableRow методGettableДочернаяForGosNomer(@NonNull Cursor cursorgetTypeTSAndGosNomers) {
                TableRow tableRowДочерная = null;
                try {
                    View convertViewДочерния = LayoutInflater.from(getContext()).inflate(R.layout.fragment_order_trasport_for_single_row_gos_nomer,
                            null, false);
                    TableLayout tableLayoutДочернная = (TableLayout) convertViewДочерния.findViewById(R.id.tablelayout_singleot_gosnomer);
                    // TODO: 26.05.2023  цикл может сказать главный идем по СЦО #1
                    tableRowДочерная = (TableRow) tableLayoutДочернная.findViewById(R.id.tableRowChildOt_gosnomer);
                    if (tableRowДочерная.getParent() != null) {
                        /*       ((ViewGroup)tableRowДочерная.getParent()).recomputeViewAttributes(tableRowДочерная); // <- fix
                         *//*     ((ViewGroup)tableRowДочерная.getParent()).removeViewInLayout(tableRowДочерная); // <- fix*/
                        ((ViewGroup) tableRowДочерная.getParent()).removeView(tableRowДочерная); // <- fix
                    }
                    if (cursorgetTypeTSAndGosNomers != null && cursorgetTypeTSAndGosNomers.getCount() > 0) {
                       Long SuccessAddRow = cursorgetTypeTSAndGosNomers.getLong(cursorgetTypeTSAndGosNomers.getColumnIndex("uuid"));
                        Long Successid_Gosnomer = cursorgetTypeTSAndGosNomers.getLong(cursorgetTypeTSAndGosNomers.getColumnIndex("uuid_track"));
                        Bundle bundleДочерний = new Bundle();
                        bundleДочерний.putLong("SuccessAddRow", SuccessAddRow);
                        bundleДочерний.putLong("Successid_Gosnomer", Successid_Gosnomer);
                        tableRowДочерная.setTag(bundleДочерний);
                        tableRowДочерная.setId(SuccessAddRow.intValue());
                        Log.d(getContext().getClass().getName(), "\n"
                                + " время: " + new Date() + "\n+" +
                                " Класс в процессе... " + this.getClass().getName() + "\n" +
                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + "  SuccessAddRow " + SuccessAddRow);
                    }
                    Log.d(getContext().getClass().getName(), "\n"
                            + " время: " + new Date() + "\n+" +
                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(getContext().getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(),
                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                return tableRowДочерная;
            }

            // TODO: 25.05.2023 класс Первое Действие Заполения Gruop BY
            class SubClassGetDateOrderGroupBy {
                // TODO: 25.05.2023  метод первый
                void методGetDateOrderGroupBy(@NonNull MaterialCardView materialCardViewGroupBy, @NonNull Bundle bundleGrpuopByOrder) {
                    try {
                        MaterialTextView materialTextViewДатаЗаказа = materialCardViewGroupBy.findViewById(R.id.ot_date_order_value);//ДАТА
                        String DateOrderGroupBy = (String) bundleGrpuopByOrder.get("dateorders");
                        // TODO: 18.04.2023  Заполение Данными уже на экран
                        методЗаполенияЗаказаТранспорта(bundleGrpuopByOrder, materialTextViewДатаЗаказа, DateOrderGroupBy);
                        // TODO: 12.05.2023
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(getContext()).recordnewerror(e.toString(),
                                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }

                }
            }

            // TODO: 25.05.2023  вторая задача заполения ЦФО
            class SubClassGetCFOOrder {
                // TODO: 25.05.2023  метод для второй ЦФО Операции
                TableLayout tableLayoutot;
                Bundle bundleGrpuopByOrder;

                public SubClassGetCFOOrder(@NonNull Bundle bundleGrpuopByOrder) {
                    this.tableLayoutot = tableLayoutot;
                    this.bundleGrpuopByOrder = bundleGrpuopByOrder;
                }

                private Cursor методGetCursorCFO() throws Exception {
                    Cursor cursorOtGetCFO = null;
                    try {
                        String УсловиеПоискаЦФО = (String) bundleGrpuopByOrder.get("dateordersForCFO");
                        // TODO: 04.05.2023  получаем первоночальыне Данные  #1
                        String Текущаятаблицы = "view_ordertransport";
                        ModuleQuety moduleQuety = new ModuleQuety(getContext());
                        cursorOtGetCFO = moduleQuety.getModuleQuery(Текущаятаблицы,
                                " SELECT *  FROM " + Текущаятаблицы + " AS D  WHERE " +
                                        " D.name  IS NOT NULL  AND D.dateorders = '" + УсловиеПоискаЦФО + "'   ORDER BY  D.dateorders ",
                                null);
                        Log.d(this.getClass().getName(), "\n"
                                + " время: " + new Date() + "\n+" +
                                " Класс в процессе... " + this.getClass().getName() + "\n" +
                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " cursor " + cursor);
                        // TODO: 25.05.2023 ;
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName()
                                + " Линия  :"
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(getContext()).recordnewerror(e.toString(),
                                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                    return cursorOtGetCFO;
                }


                // TODO: 28.05.2023 gosNomer
                private Cursor методGetCursorGosNomer(@NonNull String getDateOrdersForGosNomers, @NonNull Integer getCFOGosNomers) throws Exception {
                    Cursor cursorOtGetGosNomer = null;
                    try {
                        // TODO: 05.05.2023  ПОЛУЧАЕМ ДАННЫЕ
                        String Текущаятаблицы = "view_ordertransport";
                        ModuleQuety moduleQuety = new ModuleQuety(getContext());
                        cursorOtGetGosNomer = moduleQuety.getModuleQuery(Текущаятаблицы,
                                " SELECT  *  FROM  " + Текущаятаблицы + "  " +
                                        " WHERE name  IS NOT NULL  AND id_cfo !=0 AND dateorders='" + getDateOrdersForGosNomers.trim() + "'  "
                                        + " AND id_cfo='" + getCFOGosNomers.toString() + "'  " +
                                        "  AND status!=5 ORDER BY date_update ", null);
                        Log.d(this.getClass().getName(), "\n"
                                + " время: " + new Date() + "\n+" +
                                " Класс в процессе... " + this.getClass().getName() + "\n" +
                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " cursor " + cursorOtGetGosNomer);
                        // TODO: 25.05.2023 ;
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                                + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(getContext()).recordnewerror(e.toString(),
                                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                    return cursorOtGetGosNomer;
                }

                // TODO: 26.05.2023 заполненеи данных через ЦИКЛ
                void методЗаполениеДаннымиВЦиклеЦФО(@NonNull Cursor cursorListCFO,
                                                    @NonNull TableLayout tableLayoutРодительская) {
                    try {
                        // TODO: 26.05.2023  цикл может сказать главный идем по СЦО
                        do {
                            View convertViewДочерния = LayoutInflater.from(getContext()).inflate(R.layout.fragment_order_trasport_for_single_row_cfo,
                                    tableLayoutРодительская, false);
                            TableLayout tableLayoutДочерннаяCFO =
                                    (TableLayout) convertViewДочерния.findViewById(R.id.tablelayout_singleotrow_cfo_bnytri);

                            // TODO: 26.05.2023  ДочернийЭлемиент
                            TableRow tableRowДочерная = методGettableДочернаяForCFO(cursorListCFO, convertViewДочерния);

                            // TODO: 30.05.2023  заполяем ЦФО
                            методSetDateCFO(cursorListCFO, tableRowДочерная);


                            // TODO: 26.05.2023 Заполнение Данными Вид Траспорта и Гос Номер #2
                            методSeTypeTSAndGosNomers(cursorListCFO, tableLayoutДочерннаяCFO);


                            // TODO: 25.05.2023 ФИНАЛЬНОЕ ДЕЙСТВИЕ вСТАВКА СТРОКИ УЖЕ ЗАПОЛЕНО В tABLEPOUY
                            методAddtableRowЦФО(tableRowДочерная, tableLayoutРодительская);

                            Log.d(getContext().getClass().getName(), "\n"
                                    + " время: " + new Date() + "\n+" +
                                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                    + " cursorgetCFO.getPosition() " + cursorListCFO.getPosition());
                        } while (cursorListCFO.moveToNext());
                        // TODO: 29.05.2023 clear cursor
                        cursorListCFO.close();
                        Log.d(getContext().getClass().getName(), "\n"
                                + " время: " + new Date() + "\n+" +
                                " Класс в процессе... " + this.getClass().getName() + "\n" +
                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                + " cursorListCFO " + cursorListCFO);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(getContext()).recordnewerror(e.toString(),
                                this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }

                }


                @SuppressLint("Range")
                private void методSetDateCFO(@NonNull Cursor cursorgetCFO,
                                             @NonNull TableRow tableRowДочерная) {
                    try {
                        MaterialTextView materialTextViewДанныеAddRow = tableRowДочерная.findViewById(R.id.ot_date_order_singlevalue);
                        //  MaterialTextView    materialTextViewШабкаAddRow =  tableRowДочерная.findViewById(R.id.ot_key_order_singlevalue);
                        String dateordersCfo = (String) cursorgetCFO.getString(cursorgetCFO.getColumnIndex("cfo")).trim();
                        // TODO: 18.04.2023  Заполение Данными уже на экран
                        методЗаполенияЗаказаТранспорта(bundleGrpuopByOrder, materialTextViewДанныеAddRow, dateordersCfo);
                        // TODO: 26.05.2023 set Шабка Данных
                        //  materialTextViewШабкаAddRow.setText("Цфо");

                        Log.d(getContext().getClass().getName(), "\n"
                                + " время: " + new Date() + "\n+" +
                                " Класс в процессе... " + this.getClass().getName() + "\n" +
                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                + " cursorgetCFO.getPosition() " + cursorgetCFO.getPosition());
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

                // TODO: 28.05.2023 третий цикл Гос.Номер  и Вид Траспорта
                @SuppressLint("Range")
                private void методSeTypeTSAndGosNomers(@NonNull Cursor cursorgetForCurrentCFO
                        , @NonNull TableLayout tableLayoutДочерннаяCFO) {
                    try {
                        String getDateOrdersForGosNomers = cursorgetForCurrentCFO.getString(cursorgetForCurrentCFO.getColumnIndex("dateorders"));
                        Integer getCFOGosNomers = cursorgetForCurrentCFO.getInt(cursorgetForCurrentCFO.getColumnIndex("id_cfo"));
                        Cursor cursorgetTypeTSAndGosNomers = методGetCursorGosNomer(getDateOrdersForGosNomers, getCFOGosNomers);
                        // TODO: 06.06.2023

                        TableRow tableRowДочернаяUP = методGettableДочернаяForTypeTCUP(cursorgetTypeTSAndGosNomers);
                        // TODO: 29.05.2023  Вставка новой строки
                        методAddtableRowВидТСAndГосНомер(tableRowДочернаяUP, tableLayoutДочерннаяCFO);

                        do {
                            // TODO: 26.05.2023  ДочернийЭлемиент
                            TableRow tableRowДочерная = методGettableДочернаяForTypeTC(cursorgetTypeTSAndGosNomers);


                            // TODO: 26.05.2023  метод Заполнение Вида ТС
                            методВставкаВидТС(cursorgetTypeTSAndGosNomers, tableRowДочерная);

                            Log.d(getContext().getClass().getName(), "\n"
                                    + " время: " + new Date() + "\n+" +
                                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                    + " cursorgetCFO.getPosition() " + cursorgetTypeTSAndGosNomers.getPosition());

                            // TODO: 26.05.2023  метод Заполнение Гос.Номер
                            методВставкаГосНомер(cursorgetTypeTSAndGosNomers, tableRowДочерная);

                            // TODO: 31.05.2023

                            // TODO: 29.05.2023  Вставка новой строки
                            методAddtableRowВидТСAndГосНомер(tableRowДочерная, tableLayoutДочерннаяCFO);

                            // TODO: 01.06.2023   Выравниваем Линнии  в  Row
                            методВыравнивемЛиниюДизайнВСтавлнойRow(tableRowДочерная, tableLayoutДочерннаяCFO);


                            // TODO: 09.06.2023 Слушатетелоь КЛика по Строчку
                            методКликаПоДочернейRow(tableRowДочерная);

                            Log.d(getContext().getClass().getName(), "\n"
                                    + " время: " + new Date() + "\n+" +
                                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                    + " cursorgetCFO.getPosition() " + cursorgetTypeTSAndGosNomers.getPosition());
                        } while (cursorgetTypeTSAndGosNomers.moveToNext());
                        // TODO: 29.05.2023  clear cursor
                        cursorgetTypeTSAndGosNomers.close();
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

                @SuppressLint("Range")
                private void методВставкаВидТС(@NonNull Cursor cursorgetTypeTS,
                                               @NonNull TableRow tableRowДочерная) {
                    try {
                  /*          // TODO: 26.05.2023  ДочернийЭлемиент
                            TableRow tableRowДочерная = методGettableДочернаяForTypeTC(cursorgetTypeTS);*/
                        // TODO: 26.05.2023  Элемент Для Шабки
                        // MaterialTextView    materialTextViewШабкаAddRow =  tableRowДочерная.findViewById(R.id.ot_key_ordertype_ts);
                        MaterialTextView materialTextViewДанныеAddRow = tableRowДочерная.findViewById(R.id.ot_value_ordertype_ts);
                        // TODO: 29.05.2023 Заполеяем  Вид ТС
                        String ВидТС = cursorgetTypeTS.getString(cursorgetTypeTS.getColumnIndex("name"));
                        if (ВидТС != null) {
                            materialTextViewДанныеAddRow.setText(ВидТС.trim());
                        }
                        //  materialTextViewШабкаAddRow.setText("Вид тс");

                        Log.d(getContext().getClass().getName(), "\n"
                                + " время: " + new Date() + "\n+" +
                                " Класс в процессе... " + this.getClass().getName() + "\n" +
                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                + " cursorgetTypeTS.getPosition() " + cursorgetTypeTS.getPosition());
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

                @SuppressLint("Range")
                private void методВставкаГосНомер(@NonNull Cursor cursorgetGosNomers,
                                                  @NonNull TableRow tableRowДочерная) {
                    try {
                        // TODO: 26.05.2023  ДочернийЭлемиент
                        /*    TableRow tableRowДочерная = методGettableДочернаяForGosNomer(cursorgetGosNomers);*/

                        // TODO: 26.05.2023  Элемент Для Шабки
                        // MaterialTextView    materialTextViewШабкаAddRow =  tableRowДочерная.findViewById(R.id.ot_key_ordertype_godnomer);
                        MaterialTextView materialTextViewДанныеAddRow = tableRowДочерная.findViewById(R.id.ot_value_ordertype_gos_nomer);
                        // TODO: 29.05.2023 Заполеяем  Вид ТС
                        String ГосНомер = cursorgetGosNomers.getString(cursorgetGosNomers.getColumnIndex("gosmomer"));
                        if (ГосНомер != null) {
                            materialTextViewДанныеAddRow.setText(ГосНомер.trim());
                        }
                        //  materialTextViewШабкаAddRow.setText("Гос.номер");

                        // TODO: 29.05.2023  Вставка новой строки
                        /*    методAddtableRowГосНомер(tableRowДочерная, tableLayoutРодительская);*/
                        Log.d(getContext().getClass().getName(), "\n"
                                + " время: " + new Date() + "\n+" +
                                " Класс в процессе... " + this.getClass().getName() + "\n" +
                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                + " cursorgetGosNomers.getPosition() " + cursorgetGosNomers.getPosition());
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

                // TODO: 26.05.2023 заполение Строчки
                private void методAddtableRowЦФО(@NonNull TableRow tableRowДочернаяВсталяемая,
                                                 @NonNull TableLayout tableLayoutРодительская) {
                    try {
                        // TODO: 30.05.2023 clear child
                        Bundle bundletablДочернийскаяBungle = (Bundle) tableRowДочернаяВсталяемая.getTag();
                        Long UUIDДочернийская = 0l;
                        Long Successid_cfo = 0l;
                        String Successname_cfo = null;
                        if (bundletablДочернийскаяBungle != null) {
                            UUIDДочернийская = bundletablДочернийскаяBungle.getLong("SuccessAddRow");
                            Successid_cfo = bundletablДочернийскаяBungle.getLong("Successid_cfo");
                            Successname_cfo = bundletablДочернийскаяBungle.getString("Successname_cfo");
                        }
                        if (tableRowДочернаяВсталяемая.getParent() != null) {
                            ((ViewGroup) tableRowДочернаяВсталяемая.getParent()).removeView(tableRowДочернаяВсталяемая); // <- fix
                        }
                        Integer IdRowДочернегоВставляемого = tableRowДочернаяВсталяемая.getId();
                        Log.d(getContext().getClass().getName(), "\n"
                                + " время: " + new Date() + "\n+" +
                                " Класс в процессе... " + this.getClass().getName() + "\n" +
                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                + " tableLayoutРодительская " + tableLayoutРодительская.getChildCount() + " IdRowДочернегоВставляемого " + IdRowДочернегоВставляемого);
                        // TODO: 30.05.2023  clear PArent
                        Boolean ФлагЧтоНадоНовыйЭлемент = true;
                        if (tableLayoutРодительская != null && tableLayoutРодительская.getChildCount() > 0) {
                            for (int i = 0; i < tableLayoutРодительская.getChildCount(); i++) {
                                TableRow tableRowродительскаяУжеСуществует = (TableRow) tableLayoutРодительская.getChildAt(i);
                                if (tableRowродительскаяУжеСуществует != null) {
                                    Bundle bundletableRowродительскаяУжеСуществует = (Bundle) tableRowродительскаяУжеСуществует.getTag();
                                    if (bundletableRowродительскаяУжеСуществует != null) {
                                        Long UUidУжеСуществует = bundletableRowродительскаяУжеСуществует.getLong("SuccessAddRow");
                                        Long Successid_cfoУжеСуществует = bundletableRowродительскаяУжеСуществует.getLong("Successid_cfo");
                                        String Successname_cfoУжеСуществует = bundletableRowродительскаяУжеСуществует.getString("Successname_cfo");
                                        // TODO: 01.06.2023  Ещу Одно Условие
                                        if (Successname_cfoУжеСуществует != null) {
                                            if (Successname_cfoУжеСуществует.equalsIgnoreCase(Successname_cfo) == true) {
                                                ФлагЧтоНадоНовыйЭлемент = false;
                                                break;
                                            }
                                        }

                                        // TODO: 30.05.2023  get Chilred
                                        if (UUidУжеСуществует.compareTo(UUIDДочернийская) == 0) {
                                            if (Successid_cfoУжеСуществует > 0) {
                                                if (Successid_cfo.compareTo(Successid_cfoУжеСуществует) == 0) {
                                                    tableLayoutРодительская.removeView(tableRowродительскаяУжеСуществует);
                                                    ФлагЧтоНадоНовыйЭлемент = false;
                                                    break;
                                                }
                                            }
                                        }
                                        Log.d(getContext().getClass().getName(), "\n"
                                                + " время: " + new Date() + "\n+" +
                                                " Класс в процессе... " + this.getClass().getName() + "\n" +
                                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                                + " UUidУжеСуществует " + UUidУжеСуществует + "Successid_cfo " + " Successid_cfoУжеСуществует " + Successid_cfoУжеСуществует +
                                                " Successname_cfoУжеСуществует " + Successname_cfoУжеСуществует + " Successname_cfo " + Successname_cfo + " ФлагЧтоНадоНовыйЭлемент " + ФлагЧтоНадоНовыйЭлемент);
                                        // TODO: 01.06.2023   второе условие
                                    }
                                }
                            }
                            if (ФлагЧтоНадоНовыйЭлемент == true) {
                                // TODO: 30.05.2023
                                tableLayoutРодительская.addView(tableRowДочернаяВсталяемая);
                                // TODO: 14.06.2023
                                tableRowДочернаяВсталяемая.startAnimation(animationvibrRow);


                            }


                            // TODO: 01.06.2023 refre screen
                        }
                        Log.d(getContext().getClass().getName(), "\n"
                                + " время: " + new Date() + "\n+" +
                                " Класс в процессе... " + this.getClass().getName() + "\n" +
                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                + " tableLayoutРодительская " + tableLayoutРодительская.getChildCount());
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


                // TODO: 26.05.2023 заполение Строчки
                private void методAddtableRowВидТС(@NonNull TableRow tableRowДочерная,
                                                   @NonNull TableLayout tableLayoutРодительская) {
                    try {
                        // TODO: 30.05.2023 clear child
                        Bundle bundletablДочернийскаяBungle = (Bundle) tableRowДочерная.getTag();
                        Long UUIDДочернийская = 0l;
                        Long Successid_uuid_track = 0l;
                        if (bundletablДочернийскаяBungle != null) {
                            UUIDДочернийская = bundletablДочернийскаяBungle.getLong("SuccessAddRow");
                            Successid_uuid_track = bundletablДочернийскаяBungle.getLong("Successid_uuid_track");
                        }
                        if (tableRowДочерная.getParent() != null) {
                            ((ViewGroup) tableRowДочерная.getParent()).removeView(tableRowДочерная); // <- fix
                        }
                        Integer IdRowДочернегоВставляемого = tableRowДочерная.getId();
                        Log.d(getContext().getClass().getName(), "\n"
                                + " время: " + new Date() + "\n+" +
                                " Класс в процессе... " + this.getClass().getName() + "\n" +
                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                + " tableLayoutРодительская " + tableLayoutРодительская.getChildCount() + " IdRowДочернегоВставляемого " + IdRowДочернегоВставляемого);
                        // TODO: 30.05.2023  clear PArent
                        if (tableLayoutРодительская != null && tableLayoutРодительская.getChildCount() > 0) {
                            Boolean ФлагВставлятьИлИнет = true;
                            for (int i = 0; i < tableLayoutРодительская.getChildCount(); i++) {
                                TableRow tableRowродительскаяBungle = (TableRow) tableLayoutРодительская.getChildAt(i);
                                if (tableRowродительскаяBungle != null) {
                                    Bundle bundletableRowродительскаяBungle = (Bundle) tableRowродительскаяBungle.getTag();
                                    if (bundletableRowродительскаяBungle != null) {
                                        Long UUidРодительской = bundletableRowродительскаяBungle.getLong("SuccessAddRow");
                                        Long Successid_uuid_trackРодительская = bundletableRowродительскаяBungle.getLong("Successid_uuid_track");
                                        // TODO: 30.05.2023  get Chilred
                                        if (UUidРодительской.compareTo(UUIDДочернийская) == 0) {
                                            if (Successid_uuid_track.compareTo(Successid_uuid_trackРодительская) == 0) {
                                                ФлагВставлятьИлИнет = false;
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                            // TODO: 30.05.2023
                            if (ФлагВставлятьИлИнет == true) {
                                tableLayoutРодительская.addView(tableRowДочерная);
                                tableLayoutРодительская.requestLayout();
                                tableLayoutРодительская.refreshDrawableState();
                            } else {
                                tableLayoutРодительская.removeView(tableRowДочерная);
                                tableLayoutРодительская.refreshDrawableState();
                                // tableLayoutРодительская.requestLayout();
                            }
                        }
                        Log.d(getContext().getClass().getName(), "\n"
                                + " время: " + new Date() + "\n+" +
                                " Класс в процессе... " + this.getClass().getName() + "\n" +
                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                + " tableLayoutРодительская " + tableLayoutРодительская.getChildCount());
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

                // TODO: 26.05.2023 заполение Строчки
                private void методAddtableRowВидТСAndГосНомер(@NonNull TableRow tableRowДочернаяВсталяемая,
                                                              @NonNull TableLayout tableLayoutРодительская) {
                    try {
                        // TODO: 30.05.2023 clear child
                        Bundle bundletablДочернийскаяBungle = (Bundle) tableRowДочернаяВсталяемая.getTag();
                        Long UUIDДочернийская = 0l;
                        Long Successid_uuid_track = 0l;
                        Long Successid_Gosnomer = 0l;
                        if (bundletablДочернийскаяBungle != null) {
                            UUIDДочернийская = bundletablДочернийскаяBungle.getLong("SuccessAddRow");
                            Successid_uuid_track = bundletablДочернийскаяBungle.getLong("Successid_uuid_track");
                            Successid_Gosnomer = bundletablДочернийскаяBungle.getLong("Successid_Gosnomer");
                        }
                        if (tableRowДочернаяВсталяемая.getParent() != null) {
                            ((ViewGroup) tableRowДочернаяВсталяемая.getParent()).removeView(tableRowДочернаяВсталяемая); // <- fix
                        }
                        Integer IdRowДочернегоВставляемого = tableRowДочернаяВсталяемая.getId();
                        Log.d(getContext().getClass().getName(), "\n"
                                + " время: " + new Date() + "\n+" +
                                " Класс в процессе... " + this.getClass().getName() + "\n" +
                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                + " tableLayoutРодительская " + tableLayoutРодительская.getChildCount() + " IdRowДочернегоВставляемого " + IdRowДочернегоВставляемого);
                        // TODO: 30.05.2023  clear PArent
                        // TODO: 30.05.2023  clear PArent
                        Boolean ФлагЧтоНадоНовыйЭлемент = true;
                        if (tableLayoutРодительская.getChildCount() > 0) {
                            for (int i = 0; i < tableLayoutРодительская.getChildCount(); i++) {
                                TableRow tableRowродительскаяУжеСуществует = (TableRow) tableLayoutРодительская.getChildAt(i);
                                if (tableRowродительскаяУжеСуществует != null) {
                                    Bundle bundletableRowродительскаяУжеСуществует = (Bundle) tableRowродительскаяУжеСуществует.getTag();
                                    if (bundletableRowродительскаяУжеСуществует != null) {
                                        Long UUidУжеСуществует = bundletableRowродительскаяУжеСуществует.getLong("SuccessAddRow");
                                        Long Successid_uuid_УжеСуществует = bundletableRowродительскаяУжеСуществует.getLong("Successid_uuid_track");
                                        Long Successid_GosnomerУжеСуществует = bundletableRowродительскаяУжеСуществует.getLong("Successid_Gosnomer");
                                        // TODO: 30.05.2023  get Chilred
                                        if (UUidУжеСуществует.compareTo(UUIDДочернийская) == 0) {
                                            if (Successid_uuid_УжеСуществует > 0) {
                                                if (Successid_uuid_track.compareTo(Successid_uuid_УжеСуществует) == 0) {
                                                    ФлагЧтоНадоНовыйЭлемент = false;
                                                    break;
                                                }
                                            }
                                            if (Successid_GosnomerУжеСуществует > 0) {
                                                if (Successid_Gosnomer.compareTo(Successid_GosnomerУжеСуществует) == 0) {
                                                    ФлагЧтоНадоНовыйЭлемент = false;
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            if (ФлагЧтоНадоНовыйЭлемент == true) {
                                // TODO: 30.05.2023 Вставка Строчки ROW
                                tableLayoutРодительская.addView(tableRowДочернаяВсталяемая);
                            }
                        } else {

                            if (ФлагЧтоНадоНовыйЭлемент == true) {
                                // TODO: 30.05.2023 Вставка Строчки ROW
                                tableLayoutРодительская.addView(tableRowДочернаяВсталяемая);
                            }
                        }
                        Log.d(getContext().getClass().getName(), "\n"
                                + " время: " + new Date() + "\n+" +
                                " Класс в процессе... " + this.getClass().getName() + "\n" +
                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                + " tableLayoutРодительская " + tableLayoutРодительская.getChildCount());
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

                // TODO: 26.05.2023 заполение Строчки
                private void методAddtableRowГосНомер(@NonNull TableRow tableRowДочерная,
                                                      @NonNull TableLayout tableLayoutРодительская) {
                    try {
                        // TODO: 30.05.2023 clear child
                        Bundle bundletablДочернийскаяBungle = (Bundle) tableRowДочерная.getTag();
                        Long UUIDДочернийская = 0l;
                        Long Successid_Gosnomer = 0l;
                        if (bundletablДочернийскаяBungle != null) {
                            UUIDДочернийская = bundletablДочернийскаяBungle.getLong("SuccessAddRow");
                            Successid_Gosnomer = bundletablДочернийскаяBungle.getLong("Successid_Gosnomer");
                        }
                        if (tableRowДочерная.getParent() != null) {
                            ((ViewGroup) tableRowДочерная.getParent()).removeView(tableRowДочерная); // <- fix
                        }
                        Integer IdRowДочернегоВставляемого = tableRowДочерная.getId();
                        Log.d(getContext().getClass().getName(), "\n"
                                + " время: " + new Date() + "\n+" +
                                " Класс в процессе... " + this.getClass().getName() + "\n" +
                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                + " tableLayoutРодительская " + tableLayoutРодительская.getChildCount() + " IdRowДочернегоВставляемого " + IdRowДочернегоВставляемого);
                        // TODO: 30.05.2023  clear PArent
                        if (tableLayoutРодительская != null && tableLayoutРодительская.getChildCount() > 0) {
                            Boolean ФлагВставлятьИлИнет = true;
                            for (int i = 0; i < tableLayoutРодительская.getChildCount(); i++) {
                                TableRow tableRowродительскаяBungle = (TableRow) tableLayoutРодительская.getChildAt(i);
                                if (tableRowродительскаяBungle != null) {
                                    Bundle bundletableRowродительскаяBungle = (Bundle) tableRowродительскаяBungle.getTag();
                                    if (bundletableRowродительскаяBungle != null) {
                                        Long UUidРодительской = bundletableRowродительскаяBungle.getLong("SuccessAddRow");
                                        Long Successid_GosnomerРодительская = bundletableRowродительскаяBungle.getLong("Successid_Gosnomer");
                                        // TODO: 30.05.2023  get Chilred
                                        if (UUidРодительской.compareTo(UUIDДочернийская) == 0) {
                                            if (Successid_Gosnomer.compareTo(Successid_GosnomerРодительская) == 0) {
                                                ФлагВставлятьИлИнет = false;
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                            // TODO: 30.05.2023
                            if (ФлагВставлятьИлИнет == true) {
                                tableLayoutРодительская.addView(tableRowДочерная);
                                tableLayoutРодительская.requestLayout();
                                tableLayoutРодительская.refreshDrawableState();
                            } else {
                                tableLayoutРодительская.removeView(tableRowДочерная);
                                tableLayoutРодительская.refreshDrawableState();
                            }
                        }
                        Log.d(getContext().getClass().getName(), "\n"
                                + " время: " + new Date() + "\n+" +
                                " Класс в процессе... " + this.getClass().getName() + "\n" +
                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                + " tableLayoutРодительская " + tableLayoutРодительская.getChildCount());
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


                // TODO: 29.05.2023 КОНЕЦ КЛАССА

            }

            // TODO: 29.05.2023  Class Adapter RecyreView New New Order Transport
            class SubClassAdapterMyRecyclerview {

                private Context context;
                private TableLayout tableLayoutДочернная;

                public SubClassAdapterMyRecyclerview(@NonNull Context context) {
                    this.context = context;
                    this.tableLayoutДочернная = tableLayoutДочернная;
                }

                // TODO: 29.05.2023  Заполение RecyView
                void методЗаполенияRecycleView(@NonNull Cursor cursor) {
                    try {
                        myRecycleViewAdapter = new MyRecycleViewAdapter(cursor);
                        myRecycleViewAdapter.notifyDataSetChanged();
                        recyclerView_OrderTransport.setAdapter(myRecycleViewAdapter);
                        recyclerView_OrderTransport.getAdapter().notifyDataSetChanged();
                        Log.d(getContext().getClass().getName(), "\n"
                                + " время: " + new Date() + "\n+" +
                                " Класс в процессе... " + this.getClass().getName() + "\n" +
                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                + " cursor " + cursor + "  myRecycleViewAdapter " + myRecycleViewAdapter);
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

                // TODO: 28.02.2022 начало  MyViewHolder для Заказы Материалов
                protected class MyViewHolder extends RecyclerView.ViewHolder {
                    private MaterialCardView materialCardView;
                    private TableLayout tableLayoutРодительская;

                    private CheckBox checkBoxot;

                    public MyViewHolder(@NonNull View itemView) {
                        super(itemView);
                        try {
                            Cursor cursor = myRecycleViewAdapter.cursor;
                            if (cursor != null && cursor.getCount() > 0 && cursor.isClosed() == false) {
                                // TODO: 29.05.2023
                                МетодИнициализацииКомпонетовЗаданияCardView(itemView);
                                Log.d(getContext().getClass().getName(), "\n"
                                        + " время: " + new Date() + "\n+" +
                                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        "   itemView   " + itemView);
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

                    private void МетодИнициализацииКомпонетовЗаданияCardView(@NonNull View itemView) {
                        try {
                            // TODO: 30.05.2023  родительская
                            LinearLayout linearLayoutGroupBYЗаказыТранспорта = (LinearLayout) itemView.findViewById(android.R.id.text1);
                            materialCardView = (MaterialCardView) linearLayoutGroupBYЗаказыТранспорта.findViewById(R.id.materialCardView_single_ot);//cardview
                            tableLayoutРодительская = (TableLayout) linearLayoutGroupBYЗаказыТранспорта.findViewById(R.id.tablelayoutot_groupby);//cardview
// TODO: 30.05.2023  дочерная
                            Log.d(getContext().getClass().getName(), "\n"
                                    + " время: " + new Date() + "\n+" +
                                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    "   itemView   " + itemView);
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
                    // TODO: 29.05.2023


                }

                class MyRecycleViewAdapter extends RecyclerView.Adapter<MyViewHolder> {
                    private Cursor cursor;
                    private Bundle bundleGrpuopByOrder;

                    public MyRecycleViewAdapter(@NotNull Cursor cursor) {
                        this.cursor = cursor;
                        if (cursor != null) {
                            if (cursor.getCount() > 0) {
                                Log.i(this.getClass().getName(), " cursor  " + cursor.getCount());
                            }
                        }
                    }

                    @Override
                    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull List<Object> payloads) {
                        try {
                            ///todo ЩЕЛКАЕМ КАЖ7ДУЮ СТРОЧКУ ОТДЕЛЬНО
                            if (cursor != null && cursor.getCount() > 0 && cursor.isClosed() == false) {
                                cursor.moveToPosition(position);
                                // TODO: 12.05.2023  Получаем Данные Gropup By Первый Этап
                                bundleGrpuopByOrder = методGroupByДанныеBungle(cursor);
                                // TODO: 29.05.2023
                                Log.d(getContext().getClass().getName(), "\n"
                                        + " время: " + new Date() + "\n+" +
                                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() + "   position   " + position + " bundleGrpuopByOrder "
                                        + bundleGrpuopByOrder);

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
                        super.onBindViewHolder(holder, position, payloads);
                    }

                    @Override
                    public void setHasStableIds(boolean hasStableIds) {
                        super.setHasStableIds(hasStableIds);
                    }

                    @Override
                    public void onViewRecycled(@NonNull MyViewHolder holder) {
                        super.onViewRecycled(holder);
                    }

                    @Override
                    public boolean onFailedToRecycleView(@NonNull MyViewHolder holder) {
                        return super.onFailedToRecycleView(holder);
                    }

                    @Override
                    public void onViewAttachedToWindow(@NonNull MyViewHolder holder) {
                        try {
                            super.onViewAttachedToWindow(holder);
                            // TODO: 30.05.2023  Удаление Если Есть
                    /*        TableLayout tableLayoutРодительска=  holder.tableLayoutРодительская;
                            if (tableLayoutРодительска!=null && tableLayoutРодительска.getChildCount()>2) {
                                TableRow tableRowДочернная = (TableRow) tableLayoutРодительска.findViewById(R.id.tableRowChildOt);
                                if (tableRowДочернная != null) {
                                    //tableLayoutРодительска.removeView(tableRowДочернная);
                                    recyclerView_OrderTransport.removeView(tableLayoutРодительска);
                                    View v = recyclerView_OrderTransport.getLayoutManager().findViewByPosition(holder.getLayoutPosition());
                                    Log.d(getContext().getClass().getName(), "\n"
                                            + " время: " + new Date() + "\n+" +
                                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                                }

                            }*/
                            Log.d(getContext().getClass().getName(), "\n"
                                    + " время: " + new Date() + "\n+" +
                                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
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
                    public void onViewDetachedFromWindow(@NonNull MyViewHolder holder) {
                        super.onViewDetachedFromWindow(holder);
                    }

                    @Override
                    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
                        try {
                            recyclerView.removeAllViews();
                            recyclerView.getRecycledViewPool().clear();
                            Log.d(getContext().getClass().getName(), "\n"
                                    + " время: " + new Date() + "\n+" +
                                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(getContext().getClass().getName(),
                                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(getContext()).recordnewerror(e.toString(),
                                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
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
                            Log.d(getContext().getClass().getName(), "\n"
                                    + " время: " + new Date() + "\n+" +
                                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    "   position   " + position);
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
                    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View viewЗаказыТраспорта = null;
                        try {
                            if (cursor == null) {
                                viewЗаказыТраспорта = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_progressing_ordertransport, parent, false);//todo old simple_for_takst_cardview1
                                Log.i(this.getClass().getName(), "   viewГлавныйВидДляRecyclleViewДляСогласования" + viewЗаказыТраспорта);
                                getsubClassAdapterMyRecyclerview.МетодБиндингOrderTransportGetCursor();
                            } else {
                                if (cursor.getCount() > 0 && cursor.isClosed() == false) {
                                    viewЗаказыТраспорта = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_order_trasport_groupby1, parent, false);//todo old  simple_for_assionamaterial
                                    Log.i(this.getClass().getName(), "   viewЗаказыТраспорта" + viewЗаказыТраспорта + "  sqLiteCursor.getCount()  " + cursor.getCount());
                                    // TODO: 29.05.2023 програсс бар
                                    // TODO: 23.05.2023  програсс бар
                                    методГазимПрогрессаБар();
                                } else {
                                    viewЗаказыТраспорта = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_isnull_ordertransport, parent, false);//todo old simple_for_takst_cardview1
                                    Log.i(this.getClass().getName(), "   viewГлавныйВидДляRecyclleViewДляСогласования" + viewЗаказыТраспорта + "  sqLiteCursor.getCount()  " + cursor.getCount());
                                    // TODO: 29.05.2023
                                    // TODO: 23.05.2023  програсс бар
                                    методГазимПрогрессаБар();
                                }
                            }
                            //   recyclerView_OrderTransport.scrollToPosition(5);
                            // TODO: 13.10.2022  добавляем новый компонент в Нащ RecycreView
                            myViewHolder = new MyViewHolder(viewЗаказыТраспорта);
                            subClassOrdersTransport.МетодПерегрузкаRecyceView();
                            Log.d(getContext().getClass().getName(), "\n"
                                    + " время: " + new Date() + "\n+" +
                                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    "   cursor   " + cursor + "  myViewHolder  " + myViewHolder);
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
                    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
                        try {
                            if (cursor != null && cursor.getCount() > 0 && cursor.isClosed() == false) {
                                // TODO: 29.05.2023 Главный Метод ЗАполнения Данными
                                методГлавныйЗаполениеДанными(holder, cursor, position);
                                // TODO: 31.05.2023
                                МетодПерегрузкаRecyceView();
                            }
                            Log.d(getContext().getClass().getName(), "\n"
                                    + " время: " + new Date() + "\n+" +
                                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    "   position   " + position + " cursor " + cursor);
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
                    private void методГлавныйЗаполениеДанными(@NonNull MyViewHolder holder, @NonNull Cursor cursor, int position) {
                        try {
                            if (cursor != null) {
                                // TODO: 30.05.2023  Удаление Если Есть
                                // TODO: 29.05.2023  заполение   set ДАте
                                методForRecyreViewSetDate(holder, cursor);
                                // TODO: 29.05.2023  заполение   set ЦФО
                                методForRecyreViewSetCFO(holder, cursor, position);
                                // TODO: 29.05.2023  Обораюотываем Чек сверху справа
                                методОформеленияCheckBox(holder);

                            }
                            Log.d(getContext().getClass().getName(), "\n"
                                    + " время: " + new Date() + "\n+" +
                                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                    + " cursor " + cursor);

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


                    private void методForRecyreViewSetDate(@NonNull MyViewHolder holder, @NonNull Cursor cursor) {
                        try {
                            // TODO: 25.05.2023 первая часть GROUP BY  #1
                            new SubClassGetDateOrderGroupBy().методGetDateOrderGroupBy(holder.materialCardView, bundleGrpuopByOrder);
                            Log.d(getContext().getClass().getName(), "\n"
                                    + " время: " + new Date() + "\n+" +
                                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    "   cursor   " + cursor);
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

                    private void методForRecyreViewSetCFO(@NonNull MyViewHolder holder, @NonNull Cursor cursor, int position) {
                        try {
                            // TODO: 25.05.2023 вторая часть ЦФО get Cursor #2
                            Cursor cursorgetForCurrentCFO = new SubClassGetCFOOrder(bundleGrpuopByOrder).методGetCursorCFO();
                            // TODO: 26.05.2023 Заполнение ЦФО #3
                            new SubClassGetCFOOrder(bundleGrpuopByOrder).методЗаполениеДаннымиВЦиклеЦФО(cursorgetForCurrentCFO, holder.tableLayoutРодительская);

                            Log.d(getContext().getClass().getName(), "\n"
                                    + " время: " + new Date() + "\n+" +
                                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    "   cursor   " + cursor);
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

                    void методОформеленияCheckBox(@NonNull MyViewHolder holder) {
                        try {
               /*         CheckBox   checkBoxot= (CheckBox) holder.materialCardView.findViewById(R.id.checkBoxot);//cardview
                       Integer СтатусЗаказаТраспорта=     cursorGroupByParent.getInt(cursorGroupByParent.getColumnIndex("status"));
                            if(СтатусЗаказаТраспорта==0){
                                checkBoxot.setChecked(true);
                            }else {
                                checkBoxot.setChecked(false);
                            }*/
                            Log.d(getContext().getClass().getName(), "\n"
                                    + " время: " + new Date() + "\n+" +
                                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(getContext()).recordnewerror(e.toString(),
                                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }


                    // TODO: 08.11.2022 метод перехода на дитализацию
                    private void МетодаКликаПоtableRow(TableRow rowПервыеДанные) {
                        try {
                            rowПервыеДанные.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Bundle bundleПереходДетализацию = (Bundle) v.getTag();
                                    Log.d(this.getClass().getName(), "МетодаКликаПоtableRow v  " + v + " bundleПереходДетализацию " + bundleПереходДетализацию);
                                /*    if (bundleПереходДетализацию != null) {
                                        МетодЗапускаАнимацииКнопок((View) rowПервыеДанные);
                                        // TODO: 09.11.2022  переходим на детализацию Полученихы Материалов
                                        fragmentTransaction = fragmentManager.beginTransaction();
                                        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                                        Fragment              fragmentAdmissionMaterialsDetailing = new FragmentDetailingMaterials();
                                        bundleПереходДетализацию.putBinder("binder",binderДляПолучениеМатериалов);
                                        fragmentAdmissionMaterialsDetailing.setArguments(bundleПереходДетализацию);
                                        fragmentTransaction.replace(R.id.activity_admissionmaterias_mainface, fragmentAdmissionMaterialsDetailing);//.layout.activity_for_fragemtb_history_tasks
                                        fragmentTransaction.commit();
                                        fragmentTransaction.show(fragmentAdmissionMaterialsDetailing);
                                        Log.d(this.getClass().getName(), " fragmentAdmissionMaterialsDetailing " + fragmentAdmissionMaterialsDetailing);
                                        Log.d(this.getClass().getName(), "  v  " + v);
                                    }*/
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


                    @Override
                    public long getItemId(int position) {
                        // TODO: 04.03.2022
                        Log.i(this.getClass().getName(), "     getItemId holder.position " + position);
                        return super.getItemId(position);
                    }

                    @Override
                    public int getItemCount() {
                        int КоличесвоСтрок = 0;
                        try {
                            if (cursor != null) {
                                if (cursor.getCount() > 0) {
                                    КоличесвоСтрок = cursor.getCount();
                                    Log.d(this.getClass().getName(), "sqLiteCursor.getCount()  " + cursor.getCount());
                                } else {
                                    КоличесвоСтрок = 1;
                                    Log.d(this.getClass().getName(), "sqLiteCursor.getCount()  " + cursor.getCount());
                                }
                            } else {
                                КоличесвоСтрок = 1;
                                Log.d(this.getClass().getName(), "sqLiteCursor.getCount()  " + cursor);
                            }
                            Log.d(getContext().getClass().getName(), "\n"
                                    + " время: " + new Date() + "\n+" +
                                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    "  cursor " + cursor);
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
            }
        }


// TODO: 28.04.2023  КОНЕЦ SubClassNewOrderTranport           //// TODO: 28.04.2023  КОНЕЦ SubClassNewOrderTranport   //// TODO: 28.04.2023  КОНЕЦ SubClassNewOrderTranport
    }


    // TODO: 15.06.2023  конец КЛАССА Удаление выьраного Row Заказа


    // TODO: 26.04.2023 Конец Фрагмента FragmentOrderTransportOne     // TODO: 26.04.2023 Конец Фрагмента FragmentOrderTransportOne     // TODO: 26.04.2023 Конец Фрагмента FragmentOrderTransportOne
}