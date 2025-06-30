package com.dsy.dsu.AdmissionMaterialsApp.Window;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.BusinessLogicForApps.Services.ServiceForAdminissionMaterial;
import com.dsy.dsu.BusinessLogicForApps.Services.ServiceUpdatesPO;
import com.dsy.dsu.R;

import java.util.Date;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity_AdmissionMaterials extends AppCompatActivity implements CameraXInterface {
    private Activity activity;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment fragment_ДляПолучениеМатериалов;
    private LinearLayout activity_admissionmaterias_face ;

    public   Bitmap bitmapNewPhotoFromCameraX;
    public static final int ALL_PERSSION_CODE=1;
    public static final int CAMERA_PERSSION_CODE=2;

    private  ServiceForAdminissionMaterial.LocalBinderДляПолучениеМатериалов binderДляПолучениеМатериалов;

    private   BusinessLogic businessLogic;
    private ServiceUpdatesPO.localBinderОбновлениеПО localBinderОбновлениеПО;//TODO новаЯ
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
        setContentView(R.layout.activity_main_admission_materials);
        activity=this;
        fragmentManager = getSupportFragmentManager();
        getSupportActionBar().hide(); ///скрывать тул бар
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
           activity_admissionmaterias_face =  (LinearLayout) findViewById(R.id.activity_admissionmaterias_mainface);
            ViewGroup.LayoutParams params = activity_admissionmaterias_face.getLayoutParams();
            params.height= ViewGroup.LayoutParams.WRAP_CONTENT;
            activity_admissionmaterias_face.setLayoutParams(params);
            Log.d(this.getClass().getName(), "  onViewCreated  FragmentAdmissionMaterials");



            // TODO: 06.08.2023  Start Бизнес ЛОгикуу
            businessLogic=new BusinessLogic();

            businessLogic. методБиндингСлужбы();
            // TODO: 04.10.2023 разрешения для всего
            // TODO: 04.10.2023 разрешения для всего


                businessLogic.МетодЗапускФрагментаПриемМатериалов();


       Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                + Thread.currentThread().getStackTrace()[2].getMethodName().toString() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).
                recordnewerror(e.toString(),
                        this.getClass().getName().toString(),
                        Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

            Log.d("checkCameraPermissions", "requestCode "+requestCode +  " permissions "+  permissions  +" grantResults " +grantResults);

    }

    @Override
    public Bitmap onGetFinishEditDialogNewPhotos( ) {
        try{

            Log.d(this.getClass().getName(), "   bitmapNewPhotoFromCameraX " + bitmapNewPhotoFromCameraX);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  bitmapNewPhotoFromCameraX;
    }

    @SuppressLint("SuspiciousIndentation")
    @Override
    public void onSEtFinishEditDialogNewPhotos(@NonNull Bitmap bitmap) {
        try{
        bitmapNewPhotoFromCameraX=bitmap;

   /*         Fragment fragmentListAdd = (FragmentMaretialNew)   fragmentManager.findFragmentById(R.layout.fragment_admission_new_material);
            ((FragmentMaretialNew)fragmentListAdd).методCallsBackNewImageFromCameraActivityResult(bitmapNewPhotoFromCameraX);*/


            Log.d(this.getClass().getName(), "   bitmapNewPhotoFromCameraX " + bitmapNewPhotoFromCameraX+ " bitmap " +bitmap);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


    // TODO: 27.07.2023 class business logoc

    class BusinessLogic{
        protected void МетодЗапускФрагментаПриемМатериалов() {
            try{
                fragmentManager.clearBackStack(null);
                fragmentTransaction = fragmentManager.beginTransaction();

               // fragmentTransaction.setCustomAnimations(android.R.anim.slide_out_right, android.R.anim.slide_in_left);

                fragment_ДляПолучениеМатериалов = new FragmentAdmissionMaterials();
                String FragmentMainМатериалы=   fragment_ДляПолучениеМатериалов.getClass().getName();

                Fragment    FragmentУжеЕСтьИлиНЕт=     fragmentManager.findFragmentByTag(FragmentMainМатериалы);
                if (FragmentУжеЕСтьИлиНЕт==null) {
                fragmentTransaction.add(R.id.activity_admissionmaterias_mainface,
                                fragment_ДляПолучениеМатериалов)
                        .setPrimaryNavigationFragment(fragment_ДляПолучениеМатериалов)
                        .setReorderingAllowed(true).commit();//.layout.activity_for_fragemtb_history_tasks//.layout.activity_for_fragemtb_history_tasks
                fragmentTransaction.show(fragment_ДляПолучениеМатериалов);
                }
                Log.d(this.getClass().getName(), " fragment_ДляПолучениеМатериалов "
                        + fragment_ДляПолучениеМатериалов);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                        + Thread.currentThread().getStackTrace()[2].getMethodName().toString() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getApplicationContext()).
                        recordnewerror(e.toString(),
                                this.getClass().getName().toString(),
                                Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

        // TODO: 06.08.2023  Биндинг Службы
        private void методБиндингСлужбы() {
            try {
           ServiceConnection       serviceConnectionМатериалы = new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        try {
                            if (service.isBinderAlive()) {
                                // TODO: 30.06.2023
                     Boolean ПингаСлужбы=           service.pingBinder();

                                if (ПингаСлужбы==true) {
                                    binderДляПолучениеМатериалов = (ServiceForAdminissionMaterial.LocalBinderДляПолучениеМатериалов) service;


                                    Bundle bundlebinderДляПолучениеМатериалов=new Bundle();
                                    bundlebinderДляПолучениеМатериалов.putBinder("binderДляПолучениеМатериалов",binderДляПолучениеМатериалов);

                                    fragmentManager.setFragmentResult("BackFromActivityBinder",bundlebinderДляПолучениеМатериалов);
                                }

                                Log.d(getApplicationContext().getClass().getName(), "\n"
                                        + " время: " + new Date() + "\n+" +
                                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                                        " onServiceConnected  метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                        + "    onServiceDisconnected  ServiceForAdminissionMaterial" + " service "
                                        + service.isBinderAlive()  + " binderДляПолучениеМатериалов "+binderДляПолучениеМатериалов);

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }
                    @Override
                    public void onServiceDisconnected(ComponentName name) {
                        try {
                            Log.d(getApplicationContext().getClass().getName(), "\n"
                                    + " время: " + new Date() + "\n+" +
                                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                                    "  onServiceDisconnected метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                    + "    onServiceDisconnected  bibinderСогласованияbinderМатериалыnder");
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }
                };
                Intent intentЗапускБиндингаМатериалы = new Intent(getApplicationContext(), ServiceForAdminissionMaterial.class);
                intentЗапускБиндингаМатериалы.setAction("com.ServiceForAdminissionMaterial");
             Boolean asBoeen=   bindService(intentЗапускБиндингаМатериалы, serviceConnectionМатериалы, Context.BIND_AUTO_CREATE);

                Log.d(getApplicationContext().getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        "  onServiceDisconnected метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                        + "    onServiceDisconnected  bibinderСогласованияbinderМатериалыnder");
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }





































    }//TODO end BusinessLogic

}  // TODO: 31.07.2023  END ACTIVITY