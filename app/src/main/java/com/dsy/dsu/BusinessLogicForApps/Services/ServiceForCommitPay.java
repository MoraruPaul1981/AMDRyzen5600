package com.dsy.dsu.BusinessLogicForApps.Services;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.PaysCommitApp.Model.BLCommitPay.BinessLogicGetSearchs;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.Random;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class ServiceForCommitPay extends IntentService {

    public Bindercommitpay localBinderОбщий = new Bindercommitpay();

    public ServiceForCommitPay() {
        super("ServiceForCommitPay");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        try{
            // TODO: 17.04.2023
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

        } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(getApplicationContext().getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
    }
    @SuppressLint("NewApi")
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        try{
            Log.d(getApplicationContext().getClass().getName(), "\n"
                    + " время: " + new Date()+"\n+" +
                    " Класс в процессе... " +  this.getClass().getName()+"\n"+
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        //   return super.onBind(intent);
        localBinderОбщий.setCallingWorkSourceUid(new Random().nextInt());
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
    }
        return localBinderОбщий;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        Log.d(newBase.getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +
                " Класс в процессе... " +  newBase.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
        super.attachBaseContext(newBase);
    }

    @SuppressLint("WrongThread")
    @Override
    protected void onHandleIntent(Intent intent) {
   try{
        // TODO: 25.11.2023 бизнес логика try
        class CommitPayTry extends  BinessLogicGetSearchs{

            public CommitPayTry(Context context) {
                super(context);
            }

            @Override
            public void runningJsonNodeCommitPay(@NonNull Intent intent, @NonNull Context context) {
                super.runningJsonNodeCommitPay(intent, context);
            }
        }
       if (intent.getAction().equalsIgnoreCase("GetSerachJsonCommitPay")) {
           CommitPayTry commitPayTry=new CommitPayTry(getApplicationContext());
           commitPayTry.runningJsonNodeCommitPay(intent,getApplicationContext());
       }


       Log.d(getApplicationContext().getClass().getName(), "\n"
                + " время: " + new Date()+"\n+" +  this.getClass().getName()+"\n"+
                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());


   } catch (NullPointerException e) {
       e.printStackTrace();
       Log.e(getApplicationContext().getClass().getName(),
               "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                       " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
       new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
               this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
               Thread.currentThread().getStackTrace()[2].getLineNumber());

   } catch (ArithmeticException e) {
       e.printStackTrace();
       Log.e(getApplicationContext().getClass().getName(),
               "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                       " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
       new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
               this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
               Thread.currentThread().getStackTrace()[2].getLineNumber());


    } catch (InputMismatchException e) {
        e.printStackTrace();
        Log.e(getApplicationContext().getClass().getName(),
                "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
                this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());

   } catch (Exception e) {
        e.printStackTrace();
        Log.e(getApplicationContext().getClass().getName(),
                "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
                this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }


// TODO: 30.06.2022 сама не постредствено запуск метода
    }

















    public class Bindercommitpay extends Binder {
        public ServiceForCommitPay getService() {
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
            return ServiceForCommitPay.this;
        }

        @SuppressLint("NewApi")
        @Override
        protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {
          try{
            data = Parcel.obtain();
            reply = Parcel.obtain();
            data.writeString("fffff");
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
        }
            return    reply.readBoolean();
        }
    }


    // TODO: 23.11.2023 main metod service Commit PAy 




    // TODO: 25.11.2023 bineccess logic



}