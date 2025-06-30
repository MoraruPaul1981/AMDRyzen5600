package com.dsy.dsu.PaysCommitApp.Model.BI_RecyreView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import androidx.annotation.UiThread;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.PaysCommitApp.View.RecyreView.MyViewHolderPayCommingPay;
import com.dsy.dsu.R;
import com.dsy.dsu.BusinessLogicForApps.Services.ServiceForApprove;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.ByteSource;
import com.jakewharton.rxbinding4.view.RxView;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.functions.Supplier;
import kotlin.Unit;

// TODO: 08.11.2023  класс не посредственого выоления операции cancel или  OK 
@SuppressLint("SuspiciousIndentation")
public
class ProccesingCancelOrOKPay {
    // TODO: 08.11.2023 метод
    Context context;
    ServiceForApprove.LocalBinderДляСогласования binderСогласования1C;

    JsonNode jsonNode1сСогласованияAllRows;
    Animation animation1;
    ObjectMapper objectMapper;

    public ProccesingCancelOrOKPay(@NonNull Context context,
                                   @NonNull ServiceForApprove.LocalBinderДляСогласования binderСогласования1C) {
        this.context = context;
        this.  animation1 = AnimationUtils.loadAnimation(context, R.anim.slide_in_scrolls);
        this. binderСогласования1C = binderСогласования1C;
    }


    public StringBuffer proccerCancelOrOKPay(@NonNull  Context context,
                                     @NonNull Intent intentзаданиеНаВыполение,    @NonNull  String getHiltCommintgPays) {

     StringBuffer ОТветОт1СОперациисДанными =new StringBuffer();
        try{
            // TODO: 14.11.2023  ОПРАВЛЯЕМ ОТКАЗ иилиОК  в сошгоасованиеию СОГЛАСОВПНИЕ ИЛИ ОТКАЗ

                            ОТветОт1СОперациисДанными =
                                    binderСогласования1C.getService().
                                            МетодЗапускаСогласованияВнутриСлужбы(intentзаданиеНаВыполение, context,getHiltCommintgPays);


                            Log.d(this.getClass().getName(), "\n" + " class "
                                    + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                    + " ОТветОт1СОперациисДанными " + ОТветОт1СОперациисДанными);


        } catch (Exception e) {
            e.printStackTrace();
            Log.e(context.getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        return      ОТветОт1СОперациисДанными;

    }


    // TODO: 10.11.2023 get Binary
    @CheckResult
    byte[] proccerGetBinaty1c(@NonNull Intent заданиеНаВыполение,@NonNull View v,
                              @NonNull ServiceForApprove.LocalBinderДляСогласования binderСогласования1C,
                              @NonNull  String getHiltCommintgPays) {
        byte[] getFileNewOt1cPayCommit = null;
        try{
            // TODO: 10.11.2023 получаем файл от 1с Соглосования Binaty
            getFileNewOt1cPayCommit =
                    binderСогласования1C.getService().
                            МетодПолучаемNewFile1CСогласованияЧерезСлужбу(заданиеНаВыполение, context,getHiltCommintgPays );


            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()
                    + "\n" + "   getFileNewOt1cPayCommit " +   getFileNewOt1cPayCommit);



        } catch (Exception e) {
            e.printStackTrace();
            Log.e(context.getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return    getFileNewOt1cPayCommit ;
    }






















    void metodAddTExtView1cPayCommit(@NonNull String НазваниеТекущегот1С,
                                     @NonNull TableLayout tablelayoutRowpaycommit,
                                     @NonNull TableRow tableRowpaycommit,
                                     @NotNull String РасширенияФайла) {
        try{
            TextView textnameRowpaycommit = tableRowpaycommit.findViewById(R.id.textnameRowpaycommit);
            TextView textvalueRowpaycommit = tableRowpaycommit.findViewById(R.id.textvalueRowpaycommit);

                // TODO: 10.11.2023 добалвем назваение файла
                textvalueRowpaycommit.setText(НазваниеТекущегот1С.trim());
                textvalueRowpaycommit.setTooltipText(НазваниеТекущегот1С.trim());
                // TODO: 13.11.2023 название расфирения
                textnameRowpaycommit.setText("Файл "+"(."+РасширенияФайла+")");
                textnameRowpaycommit.setTooltipText(РасширенияФайла.trim());

                // TODO: 03.11.2023 Tag
                Bundle bundleChildreRow = new Bundle();
                bundleChildreRow.putString("ВinNameFile", НазваниеТекущегот1С.trim());
                bundleChildreRow.putString("expansion", РасширенияФайла.trim());

                tablelayoutRowpaycommit.setTag(bundleChildreRow);
                textvalueRowpaycommit.setTag(bundleChildreRow);
                // TODO: 18.01.2024

                textnameRowpaycommit.requestLayout();
                textvalueRowpaycommit.refreshDrawableState();
                textnameRowpaycommit.requestLayout();
                textvalueRowpaycommit.refreshDrawableState();


            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(context.getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
    // TODO: 10.11.2023 меняем дизацйн ноых файлов

    void metodAddTExtViewChengeDisainPayCommit(@NonNull TableRow tableRowpaycommit) {
        try{
            TextView textnameRowpaycommit = tableRowpaycommit.findViewById(R.id.textnameRowpaycommit);
            TextView textvalueRowpaycommit = tableRowpaycommit.findViewById(R.id.textvalueRowpaycommit);

            //    textvalueRowpaycommit.setTextColor(Color.parseColor("#787070"));

            textvalueRowpaycommit.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    try{
                    textvalueRowpaycommit.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    int height =  textvalueRowpaycommit.getHeight();
                    int width =  textvalueRowpaycommit.getWidth();
                    textnameRowpaycommit.setHeight( height);
                    textnameRowpaycommit.requestLayout();
                    textnameRowpaycommit.forceLayout();



                    String s=textvalueRowpaycommit.getText().toString();
                    SpannableString ss=new SpannableString(s);
                    ss.setSpan(new UnderlineSpan(), 0, s.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    textvalueRowpaycommit.setText(ss);

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(context.getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(context).recordnewerror(e.toString(),
                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                }
            });




            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(context.getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


    // TODO: 11.11.2023  програсс бакр при загрузке файла 1с согооасования

    @UiThread
    private void metodPrograssbarDowloadFile1cPayCommit(@NonNull MyViewHolderPayCommingPay holder,
                                                        @NotNull Boolean Flag ) {
        try{

            if (Flag) {
                holder.progressbar_commingpay.setVisibility(View.VISIBLE);
            } else {
                holder.progressbar_commingpay.setVisibility(View.INVISIBLE);
            }
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(context.getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    // TODO: 10.11.2023  метод  клик по файлва Для Отправки

    void metodClicksForTextViewPayCommit(@NonNull TableRow tableRowpaycommit,
                                         @NonNull MyViewHolderPayCommingPay holder,
                                         @NonNull Integer ПубличныйidPay,
                                         @NonNull    String getHiltCommintgPays) {
        try{

            final Handler[] handler = new Handler[1];

            TextView textnameRowpaycommit = tableRowpaycommit.findViewById(R.id.textnameRowpaycommit);
            TextView textvalueRowpaycommit = tableRowpaycommit.findViewById(R.id.textvalueRowpaycommit);
            // TODO: 10.11.2023 клик по файлов
            RxView.clicks(textvalueRowpaycommit)
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .filter(s -> !s.toString().isEmpty())
                    .map(new Function<Unit, Object>() {
                        @Override
                        public Object apply(Unit unit) throws Throwable {
                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                    +"  s.toString(); " + unit.toString());
                            handler[0] =   textnameRowpaycommit.getHandler();


                            metodPrograssbarDowloadFile1cPayCommit(holder,true );

                            Animation animationscroll = AnimationUtils.loadAnimation(context, R.anim.slide_in_scrolls);

                            textvalueRowpaycommit.startAnimation(animationscroll);

                            return textvalueRowpaycommit.getText().toString();
                        }
                    })
                    .doOnError(new io.reactivex.rxjava3.functions.Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Throwable {
                            throwable.printStackTrace();
                            Log.e(context.getClass().getName(),
                                    "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(context).recordnewerror(throwable.toString(),
                                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    })
                    .onErrorComplete(new Predicate<Throwable>() {
                        @Override
                        public boolean test(Throwable throwable) throws Throwable {
                            throwable.printStackTrace();
                            Log.e(context.getClass().getName(),
                                    "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(context).recordnewerror(throwable.toString(),
                                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                            return false;
                        }
                    })
                    .subscribe( GettextvalueRowpaycommit->{




                        // TODO: 18.01.2024 получаем файл 
                        startdownloadFileOt1cCommintPay(tableRowpaycommit, holder, ПубличныйidPay,
                                handler, textvalueRowpaycommit,
                                GettextvalueRowpaycommit,binderСогласования1C,getHiltCommintgPays);


                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

                    });


            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(context.getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    private void startdownloadFileOt1cCommintPay(@NonNull TableRow tableRowpaycommit, @NonNull MyViewHolderPayCommingPay holder,
                                                 @NonNull Integer ПубличныйidPay, Handler[] handler,
                                                 @NonNull    TextView textvalueRowpaycommit, Object GettextvalueRowpaycommit,
                                                 @NonNull ServiceForApprove.LocalBinderДляСогласования binderСогласования1C,
                                                 @NonNull    String getHiltCommintgPays) {
        try{
        handler[0].postDelayed(()->{
            try{
                // TODO: 18.01.2024  получаем файл от 1с

                metodCompleteStartDownloadfILE1C(holder, textvalueRowpaycommit, GettextvalueRowpaycommit, ПубличныйidPay,binderСогласования1C,getHiltCommintgPays);

                metodPrograssbarDowloadFile1cPayCommit(holder,false );

                tableRowpaycommit.clearAnimation();
                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(context.getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(context).recordnewerror(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        },100);

        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(context.getClass().getName(),
                "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(context).recordnewerror(e.toString(),
                this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }


    // TODO: 13.11.2023 метод Загрузки Старта Новго Файла От 1с Complete 
    private void metodCompleteStartDownloadfILE1C(@NonNull MyViewHolderPayCommingPay holder,
                                                  @NonNull  TextView textvalueRowpaycommit,
                                                  @NonNull Object GetNameSingleNewFile1c,
                                                   @NonNull Integer ПубличныйidPay,
                                                  @NonNull ServiceForApprove.LocalBinderДляСогласования binderСогласования1C,
                                                  @NonNull  String getHiltCommintgPays) {
        try{
            final byte[][] getFileNewOt1cPayCommit = {null};
            Completable.fromSupplier(new Supplier<byte[]>() {
                        @Override
                        public byte[] get() throws Throwable {

                            getFileNewOt1cPayCommit[0] =     metodSubscrionGets1cСограсование(textvalueRowpaycommit,
                                    GetNameSingleNewFile1c.toString(),holder,ПубличныйidPay,binderСогласования1C,getHiltCommintgPays);

                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                    +"  GetNameSingleNewFile1c " + GetNameSingleNewFile1c +" getFileNewOt1cPayCommit " + getFileNewOt1cPayCommit[0]);

                            return getFileNewOt1cPayCommit[0];
                        }
                    })
                    .subscribe(new CompletableObserver() {
                        @Override
                        public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                            Vibrator v2 = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
                            v2.vibrate(VibrationEffect.createOneShot(80, VibrationEffect.EFFECT_HEAVY_CLICK));

                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                    +"  GetNameSingleNewFile1c " + GetNameSingleNewFile1c +" время " + new Date().toLocaleString());
                        }

                        @Override
                        public void onComplete() {
                            // TODO: 13.11.2023  после получениеоперацйии получение файла показыаем програсс баром



                            // TODO: 13.11.2023 МетодЗапускает Если Файл Есть Пднимаем Его Птом
                            reakziyHaSizeFile1cPayCommi(getFileNewOt1cPayCommit[0],textvalueRowpaycommit);




                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                    +"  GetNameSingleNewFile1c " + GetNameSingleNewFile1c +" getFileNewOt1cPayCommit " + getFileNewOt1cPayCommit[0]);
                        }

                        @Override
                        public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                    +"  GetNameSingleNewFile1c " + GetNameSingleNewFile1c +" время " + new Date().toLocaleString());
                        }
                    });

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(context.getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }




    private void metodUpsAllsFileOt1cUserS( @NonNull   Bundle bundleChildreRow,@NonNull   byte[] getFileNewOt1cPayCommit) {
        // TODO: 14.11.2023 ПОказываем файл пользоватолю
        try {
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                SuccessGet1CpayCommitProccesing successGet1CpayCommitProccesing=new SuccessGet1CpayCommitProccesing(context,getFileNewOt1cPayCommit,bundleChildreRow);


                String РасширенияФайлаОт1С = bundleChildreRow.getString("expansion", "");
                switch (РасширенияФайлаОт1С.trim()){
                    case "xlsx":
                    case "xltx":
                    case "xlsm":
                        // TODO: 18.01.2024  подпимаем файл определного формата
                       // successGet1CpayCommitProccesing.filesuccessDownDisk1CpayCommitProccesing("application/vnd.ms-excel");
                        successGet1CpayCommitProccesing.filesuccessDownDisk1CpayCommitProccesing("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
                        break;

                    case "xls":
                    case "xla":
                    case "xlt":
                        // TODO: 18.01.2024  подпимаем файл определного формата
                        successGet1CpayCommitProccesing.filesuccessDownDisk1CpayCommitProccesing("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

                        break;
                    case "mdb":
                    case "accdb":
                        // TODO: 18.01.2024  подпимаем файл определного формата
                        successGet1CpayCommitProccesing.filesuccessDownDisk1CpayCommitProccesing("application/vnd.ms-access");

                        break;
                    case "ppt":
                    case "pot":
                    case "pps":
                        // TODO: 18.01.2024  подпимаем файл определного формата
                        successGet1CpayCommitProccesing.filesuccessDownDisk1CpayCommitProccesing("application/vnd.ms-powerpoint");
                        break;

                    case "docx":
                    case "dotx":
                    case "docm":
                        // TODO: 18.01.2024  подпимаем файл определного формата
                        successGet1CpayCommitProccesing.filesuccessDownDisk1CpayCommitProccesing("application/vnd.openxmlformats-officedocument.wordprocessingml.document");

                        break;
                    case "doc":
                    case "dot":
                        // TODO: 18.01.2024  подпимаем файл определного формата
                        successGet1CpayCommitProccesing.filesuccessDownDisk1CpayCommitProccesing("application/msword");
                        break;
                    case "jpg":
                        // TODO: 18.01.2024  подпимаем файл определного формата
                        successGet1CpayCommitProccesing.filesuccessDownDisk1CpayCommitProccesing("image/jpg");
                        break;
                    case "jpeg":
                        // TODO: 18.01.2024  подпимаем файл определного формата
                        successGet1CpayCommitProccesing.filesuccessDownDisk1CpayCommitProccesing("image/jpeg");

                        break;
                    case "png":
                        // TODO: 18.01.2024  подпимаем файл определного формата
                        successGet1CpayCommitProccesing.filesuccessDownDisk1CpayCommitProccesing("image/png");
                        break;
                    case "pdf":
                        // TODO: 18.01.2024  подпимаем файл определного формата
                        successGet1CpayCommitProccesing.filesuccessDownDisk1CpayCommitProccesing("application/pdf");
                        break;
                    case "zip":
                        // TODO: 18.01.2024  подпимаем файл определного формата
                        successGet1CpayCommitProccesing.filesuccessDownDisk1CpayCommitProccesing("application/zip");
                        break;
                    case "rar":
                        // TODO: 18.01.2024  подпимаем файл определного формата
                        successGet1CpayCommitProccesing.filesuccessDownDisk1CpayCommitProccesing("application/vnd.rar");

                        break;
                    case "txt":
                        // TODO: 18.01.2024  подпимаем файл определного формата
                        successGet1CpayCommitProccesing.filesuccessDownDisk1CpayCommitProccesing("text/*");
                        break;
                }


            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");


        } catch (Exception e) {
            e.printStackTrace();
            Log.e(context.getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }





    // TODO: 10.11.2023 Метод Подписка Subcrion Получение файла от 1с Сограсование
    private byte[] metodSubscrionGets1cСограсование(@NotNull TextView textvalueRowpaycommit,
                                                    @NotNull Object GetNameSingleNewFile1c,
                                                    @NonNull MyViewHolderPayCommingPay holder
                                                   ,@NonNull Integer ПубличныйidPay,
                                                    @NonNull ServiceForApprove.LocalBinderДляСогласования binderСогласования1C,
                                                    @NonNull  String getHiltCommintgPays) {
        byte[] getFileNewOt1cPayCommit=null;
        try{
            // TODO: 26.12.2022  конец основгого кода
            Intent заданиеGetNewFile1C=new Intent();
            // TODO: 17.11.2021
            заданиеGetNewFile1C.setAction( "ЗапускаемПолучениеBinatyОт1с");
            Bundle bundleДляПередачиВСлужбуСогласования=(Bundle)   textvalueRowpaycommit.getTag();

            bundleДляПередачиВСлужбуСогласования.putInt("PROCESS_IDСогласования", Integer.parseInt("28"));
            bundleДляПередачиВСлужбуСогласования.putString("backfile", GetNameSingleNewFile1c.toString().trim() );
            bundleДляПередачиВСлужбуСогласования.putInt("ПередаемСтатусСогласования", 4);///TODO выполнил Согласования
            bundleДляПередачиВСлужбуСогласования.putInt("ПубличныйidPay", ПубличныйidPay);//ПубличныйidPay

            // TODO: 13.11.2023  номер документа
            String dsu1number=      holder.tx_nomer.getTag().toString().trim();


            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    +"  dsu1number " + dsu1number+" время " + new Date().toLocaleString());



            bundleДляПередачиВСлужбуСогласования.putString("dsu1number",  dsu1number);//ПубличныйidPay

            заданиеGetNewFile1C.putExtras(bundleДляПередачиВСлужбуСогласования);

            textvalueRowpaycommit.setTag(bundleДляПередачиВСлужбуСогласования);

            ///TODO выполнил ОТКАЗ CANCEL
            getFileNewOt1cPayCommit =proccerGetBinaty1c(заданиеGetNewFile1C, textvalueRowpaycommit,binderСогласования1C,getHiltCommintgPays);

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    +"  getFileNewOt1cPayCommit " + getFileNewOt1cPayCommit+" время " + new Date().toLocaleString());
            // TODO: 26.12.2022  конец основгого кода
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(context.getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());

        }
        return  getFileNewOt1cPayCommit;
    }



    void reakziyHaSizeFile1cPayCommi(@NotNull byte[] getFileNewOt1cPayCommit,@NonNull  TextView textvalueRowpaycommit){
        try{
            if (getFileNewOt1cPayCommit!=null) {
                if (getFileNewOt1cPayCommit.length>0) {


                    // TODO: 18.01.2024  когдла успешно получили АЙЛ О ОТ 1С  СОГЛАСОВАНИЯ
                    // TODO: 08.11.2023 после успешно операции перепоудчаем даные  1с Сограсование
                    InputStream inputStreamFile        = ByteSource.wrap(getFileNewOt1cPayCommit).openBufferedStream();
                    // TODO: 07.10.2023 end
                    if (inputStreamFile.available()>0) {
                        BufferedReader  РидерileNewOt1cPayCommit = new BufferedReader(new InputStreamReader(inputStreamFile, StandardCharsets.UTF_8));
                        StringBuffer БуферРидерileNewOt1cPayCommit= РидерileNewOt1cPayCommit.lines().collect(StringBuffer::new, (sb, i) -> sb.append(i),
                                StringBuffer::append);

                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                + " s " +inputStreamFile+" inputStreamFile.available()" +inputStreamFile.available()+" БуферРидерileNewOt1cPayCommit " +БуферРидерileNewOt1cPayCommit);

                        // TODO: 18.01.2024
                        if(!БуферРидерileNewOt1cPayCommit.toString().trim().matches("(.*)Файл не найден(.*)")){


                            // TODO: 13.11.2023 ЗАгружаем ФАйл Н адИСк



                            // TODO: 14.11.2023 Поднимаем Файл с Диска И Показываем Его Пользователю
                            metodUpsAllsFileOt1cUserS( (Bundle) textvalueRowpaycommit.getTag(),getFileNewOt1cPayCommit);



                        }else{
                            Toast.makeText(context, "Нет файла 1С !!!"    , Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(context, "Нет файла 1С !!!"    , Toast.LENGTH_SHORT).show();
                    }
                    // TODO: 18.01.2024  close
                    inputStreamFile.close();

                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " s " +inputStreamFile+" inputStreamFile" +inputStreamFile.available());


                }else{
                    Toast.makeText(context, "Нет файла 1С !!!"    , Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(context, "Нет файла 1С !!!"    , Toast.LENGTH_SHORT).show();
            }

            // TODO: 26.12.2022  конец основгого кода
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(context.getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }




}