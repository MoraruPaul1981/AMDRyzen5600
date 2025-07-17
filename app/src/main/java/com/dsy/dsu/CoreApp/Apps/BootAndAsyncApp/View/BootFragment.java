package com.dsy.dsu.CoreApp.Apps.BootAndAsyncApp.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.dsy.dsu.CoreApp.Apps.BootAndAsyncApp.Model.BinesslogicActivityBoot.GetFinishAffinityFragment;
import com.dsy.dsu.CoreApp.Apps.BootAndAsyncApp.Model.EventsBus.MessageEvensBusAppAfterSyncing;
import com.dsy.dsu.CoreApp.Apps.BootAndAsyncApp.Model.EventsBus.MessageEvensBusNetworkStatuses;
import com.dsy.dsu.CoreApp.Apps.BootAndAsyncApp.Model.EventsBus.MessageEvensBusPrograssBar;
import com.dsy.dsu.CoreApp.Apps.BootAndAsyncApp.Model.EventsBus.MessageEvensBusUpdatePO;
import com.dsy.dsu.CoreApp.Apps.BootAndAsyncApp.Model.ModuleSingleWorkManager.ModuleSingleWorkManager;
import com.dsy.dsu.CoreApp.Apps.BootAndAsyncApp.Model.BinesslogicActivityBoot.GetComponentActivityBootService;
import com.dsy.dsu.CoreApp.Apps.BootAndAsyncApp.Model.BinesslogicActivityBoot.GetComponentPrograssbar;
import com.dsy.dsu.CoreApp.Apps.DashboardCoreApp.Model.endingasynsdashboard.LaunchMainAppAfterSyncing;


import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.BusinessLogicForApps.Hilt.getSSLSocketFactory2.QualifiergetsslSocketFactory2;
import com.dsy.dsu.BusinessLogicForApps.JbossAdress.JbossHilt.intarfaces.QualifierPortJboss;
import com.dsy.dsu.R;

import com.google.android.material.navigation.NavigationView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Date;
import java.util.LinkedHashMap;

import javax.inject.Inject;
import javax.net.ssl.SSLSocketFactory;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
@AndroidEntryPoint
public class BootFragment extends DialogFragment {
    private FragmentManager fragmentManager;
    protected ProgressBar progressbarbootandasync;
    protected DrawerLayout drawerLayoutAsync;
    protected NavigationView navigationViewAsyncApp;
    protected LifecycleOwner getlifecycleOwner  ;
    protected GetComponentActivityBootService blInnerMainActivityBootAndAsync;
    private ImageView imageView_faceapp_settings;
    private ImageView imageviewbootlogo;


    @Inject
    ModuleSingleWorkManager moduleSingleWorkManager;

    @Inject
    @QualifiergetsslSocketFactory2
    public SSLSocketFactory getsslSocketFactory2;

          @Inject
          @QualifierPortJboss
          protected LinkedHashMap<Integer,String> getHiltPortJboss;



    // TODO: Rename and change types and number of parameters
    // TODO: Rename and change types and number of parameters
    public static BootFragment newInstance( ) {
        BootFragment fragment = new BootFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            // TODO: 15.04.2025
            fragmentManager = getActivity(). getSupportFragmentManager();
            // TODO: 22.08.2023  анимауия
            /*setStyle(DialogFragment.STYLE_NORMAL,android.R.style.Theme_Material_Dialog_Alert);//Theme_Dialog*/
            // setStyle(DialogFragment.STYLE_NORMAL,android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);//Theme_Dialog
            //  setStyle(DialogFragment.STYLE_NORMAL,android.R.style.Theme_DeviceDefault_Light_NoActionBar_TranslucentDecor);//Theme_Dialog
            // setStyle(DialogFragment.STYLE_NORMAL,android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);//Theme_Dialog
            //  setStyle(DialogFragment.STYLE_NORMAL,android.R.style.Theme_DeviceDefault_Dialog_Alert);//Theme_Dialog
            // setStyle(DialogFragment.STYLE_NORMAL,android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth);//Theme_Dialog
            // setStyle(DialogFragment.STYLE_NO_INPUT,android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth);//Theme_Dialog
            //setStyle(DialogFragment.STYLE_NO_INPUT,android.R.style.Theme_DeviceDefault_Light_Panel);//Theme_Dialog
            //  setStyle(DialogFragment.STYLE_NO_INPUT,android.R.style.Theme_DeviceDefault_InputMethod);//Theme_Dialog
            // setStyle(DialogFragment.STYLE_NO_INPUT,android.R.style.Theme_DeviceDefault_Light_Panel);//Theme_Dialog
            // setStyle(DialogFragment.STYLE_NO_INPUT,android.R.style.Theme_DeviceDefault_Panel);//Theme_Dialog
            // setStyle(DialogFragment.STYLE_NO_INPUT,android.R.style.Theme_DeviceDefault_Settings);//Theme_Dialog
            //setStyle(DialogFragment.STYLE_NO_TITLE,android.R.style.Theme_DeviceDefault_Wallpaper_NoTitleBar);//Theme_Dialog
            // setStyle(DialogFragment.STYLE_NO_FRAME | DialogFragment.STYLE_NO_INPUT,android.R.style.Theme_DeviceDefault_Wallpaper_NoTitleBar);//Theme_Dialog
            //  setStyle(  DialogFragment.STYLE_NO_FRAME | DialogFragment.STYLE_NO_TITLE,android.R.style.Theme_DeviceDefault_Wallpaper_NoTitleBar);//Theme_Dialog
            //    setStyle(   DialogFragment.STYLE_NO_TITLE,android.R.style.Theme_DeviceDefault_Wallpaper_NoTitleBar);//Theme_Dialog
            //  setStyle(   DialogFragment.STYLE_NO_INPUT ,android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);//Theme_Dialog
            // setStyle(   DialogFragment.STYLE_NO_FRAME ,android.R.style.Theme_DeviceDefault_Light_NoActionBar_Overscan);//Theme_Dialog
            // setStyle(   DialogFragment.STYLE_NO_FRAME ,android.R.style.Theme_DeviceDefault_Light_NoActionBar_Overscan);//Theme_Dialog
            //setCancelable(false);

            //setStyle(DialogFragment.STYLE_NO_FRAME,android.R.style.Theme_Material_Dialog);
            // setStyle(   DialogFragment.STYLE_NORMAL ,android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);//Theme_Dialog
            // setStyle(   DialogFragment.STYLE_NORMAL ,android.R.style.Theme_Material_Dialog_MinWidth);//Theme_Dialog
            // setStyle(   DialogFragment.STYLE_NO_TITLE ,android.R.style.Theme_Material_Light);//Theme_Dialog
            // setStyle(   DialogFragment.STYLE_NO_TITLE ,android.R.style.Theme_Material_Light_NoActionBar_Overscan);//Theme_Dialog
            //setStyle(   DialogFragment.STYLE_NORMAL,android.R.style.Theme_Material_Light_NoActionBar_Overscan);//Theme_Dialog

            // setStyle(   DialogFragment.STYLE_NORMAL ,android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);//Theme_Dialog  Без Часов
            //    setStyle(   DialogFragment.STYLE_NORMAL ,android.R.style.Theme_DeviceDefault_Light_NoActionBar_TranslucentDecor);//Theme_Dialog
            // setStyle(   DialogFragment.STYLE_NORMAL ,android.R.style.Theme_DeviceDefault_Light_DarkActionBar);//Theme_Dialog
            // setStyle(   DialogFragment.STYLE_NORMAL ,android.R.style.Theme_DeviceDefault_Light_DialogWhenLarge_NoActionBar);//Theme_Dialog   с часами сверху
            // setStyle(   DialogFragment.STYLE_NORMAL ,android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);//Theme_Dialog  Без Часов
            // setStyle(   DialogFragment.STYLE_NORMAL ,android.R.style.Theme_Black_NoTitleBar );
            //  setStyle(   DialogFragment.STYLE_NORMAL ,android.R.style.Theme_Material_Light_Dialog_Presentation);
/*            setStyle(   DialogFragment.STYLE_NORMAL ,android.R.style.Theme_DeviceDefault_NoActionBar_Fullscreen  );
            setStyle(   DialogFragment.STYLE_NO_TITLE,android.R.style.Theme_DeviceDefault_Wallpaper_NoTitleBar);*/
            // setStyle(   DialogFragment.STYLE_NORMAL ,android.R.style.Theme_Material_Light_NoActionBar_Fullscreen);
            // setStyle(   DialogFragment.STYLE_NORMAL ,android.R.style.Theme_DeviceDefault_NoActionBar_Fullscreen );
         //   setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_NoTitleBar_Fullscreen);
            //setStyle(   DialogFragment.STYLE_NORMAL ,android.R.style.Theme_DeviceDefault_NoActionBar_Fullscreen  );
            setStyle(   DialogFragment.STYLE_NORMAL ,android.R.style.Theme_Material_Light_NoActionBar );
            setShowsDialog(true);

            // TODO: 15.04.2025
            getlifecycleOwner=this;

            // TODO: 15.08.2023
            Log.d(this.getClass().getName(),"\n" + " class "
                    + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=null;
        try{
            view= inflater.inflate(R.layout.fragment_boot, container, false);
            // TODO: 15.04.2025
         getDialog().setCancelable(false);

            getDialog().getWindow().setStatusBarColor(Color.BLACK);

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
        return  view; //TODO inflater.inflate(R.layout.activity_main__tabel_four_colums, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        try{
            super.onViewCreated(view, savedInstanceState);
            // TODO: 03.04.2025
                // TODO: 17.04.2025
                drawerLayoutAsync       = (DrawerLayout) view.findViewById(R.id.drawerLayout_fragment_bootasync);
                progressbarbootandasync = (ProgressBar) drawerLayoutAsync.findViewById(R.id.progressbarbootandasync); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА//////КНОПКА ТАБЕЛЬНОГО УЧЕТА
                drawerLayoutAsync.setBackgroundColor(Color.WHITE);         //TODO устанвливает цвета
                drawerLayoutAsync.setDrawingCacheBackgroundColor(Color.RED);//todo
                navigationViewAsyncApp    = (NavigationView) drawerLayoutAsync.findViewById(R.id.navigator_asyncapp); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА
                imageView_faceapp_settings = (ImageView) drawerLayoutAsync.findViewById(R.id.imageView_faceapp_settings); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА
                imageviewbootlogo = (ImageView) drawerLayoutAsync.findViewById(R.id.imageviewbootlogo); /////КНОПКА ТАБЕЛЬНОГО УЧЕТА


                // TODO: 15.04.2025 запускам бизнес логику фрагмента boot
                blInnerMainActivityBootAndAsync=new GetComponentActivityBootService(getsslSocketFactory2,
                        progressbarbootandasync, getActivity(), drawerLayoutAsync,
                        navigationViewAsyncApp,getContext(),  getlifecycleOwner,imageView_faceapp_settings);



            blInnerMainActivityBootAndAsync .  МетодБоковаяПанельОткрытьЗАкрыть();
            blInnerMainActivityBootAndAsync .listerNavigationViewAsyncApp();
            blInnerMainActivityBootAndAsync .  workerNavigationViewAsyncApp(fragmentManager);
            blInnerMainActivityBootAndAsync .  workerImageViewsettings();
             new GetFinishAffinityFragment(getContext()).  finishAffinityFragment(this);



            // TODO: 15.04.2025
            moduleSingleWorkManager.startingSingleWorkManger();

            Log.d(this.getClass().getName(),"\n" + " class " +
                    Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

            Log.d(this.getClass().getName(),"\n" + " class " +
                    Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
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
    public void onStart() {
        super.onStart();
        try{
            blInnerMainActivityBootAndAsync .  registeEventBusFirst(this);
            Log.d(this.getClass().getName(),"\n" + " class "
                    + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
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
        try{
            blInnerMainActivityBootAndAsync .  unregisterEventBusFirst(this);

        Log.d(this.getClass().getName(),"\n" + " class "
                + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
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




    // TODO: 15.04.2025  EvenBus   TODO: 15.04.2025  EvenBus   TODO: 15.04.2025  EvenBus   TODO: 15.04.2025  EvenBus   TODO: 15.04.2025  EvenBus  TODO: 15.04.2025  EvenBus  TODO: 15.04.2025  EvenBus
    // TODO: 23.01.2024 EventBus for Prograssbar
    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
    public void EventMessageEvensBusPrograssBar(MessageEvensBusPrograssBar messageEvensBusPrograssBar){
        try{
            GetComponentPrograssbar get_componentPrograssbar =new GetComponentPrograssbar(progressbarbootandasync,getContext());
            get_componentPrograssbar.getEventBusPrograssBar(messageEvensBusPrograssBar);
            Log.d(getContext().getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                    + "   starting... onRestart" + " starting... onRestart");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    // TODO: 23.01.2024 EventBus for Update PO
    @Subscribe (threadMode =  ThreadMode.MAIN_ORDERED)
    public void EventMessageEvensBusUpdatePO(MessageEvensBusUpdatePO messageEvensBusUpdatePO){
        try{


            blInnerMainActivityBootAndAsync   .getEventBusUpdatePo(messageEvensBusUpdatePO,getHiltPortJboss);

            Log.d(getContext().getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                    + "   starting... onRestart" + " starting... onRestart");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }

    // TODO: 23.01.2024 EventBus for Status
    @Subscribe (threadMode = ThreadMode.MAIN_ORDERED)
    public void EventMessageEvensBusAyns(MessageEvensBusNetworkStatuses messageEvensBusNetworkStatuses){
        try{

            blInnerMainActivityBootAndAsync .getEventBusNetworkStatuses(messageEvensBusNetworkStatuses);

            Log.d(getContext().getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                    + "   starting... onRestart" + " starting... onRestart");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


    // TODO: 23.01.2024 EventBus for AppAfterSyncing
    @Subscribe (threadMode = ThreadMode.MAIN_ORDERED)
    public void EventMessageEvensAppAfterSyncing(MessageEvensBusAppAfterSyncing messageEvensBusAppAfterSyncing){
        try{

            Bundle    getbundleLaunchMainAppAfterSyncing= messageEvensBusAppAfterSyncing.mess.getExtras();

            Boolean getUserAuthenticated=   getbundleLaunchMainAppAfterSyncing.getBoolean("launchMainAppAfterSyncing",false);///"В процесс"

            LaunchMainAppAfterSyncing launchMainAppaftersyncing =new LaunchMainAppAfterSyncing(getActivity());
            if (getUserAuthenticated){
                // TODO: 01.04.2024 Все в порядке ЗАпускам Саму Программу DashBord
                launchMainAppaftersyncing.appAfterSyncingDashboard(fragmentManager);
            }else {
                // TODO: 28.04.2023 НЕт Анутифтикации Пароль
                // TODO: 28.04.2023 НЕт Анутифтикации Пароль
                launchMainAppaftersyncing.appAfterSyncingActivityPassword(getActivity(),  "СамыйПервыйЗапускСинхронизации"  );
            }
            Log.d(getContext().getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+"\n"+
                    "messageEvensBusAppAfterSyncing " +messageEvensBusAppAfterSyncing);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

// TODO: 15.04.2025 end class
}