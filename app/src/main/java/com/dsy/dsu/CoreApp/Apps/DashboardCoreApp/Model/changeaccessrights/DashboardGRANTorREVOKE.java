package com.dsy.dsu.CoreApp.Apps.DashboardCoreApp.Model.changeaccessrights;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.dsy.dsu.CoreApp.Apps.DashboardCoreApp.Model.hilts.QualifierDashboards;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.BusinessLogicForApps.GrantsRights.InGRANTs;
import com.google.android.material.button.MaterialButton;

import javax.inject.Inject;

import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

@QualifierDashboards
@Module
@InstallIn(SingletonComponent.class)
public class DashboardGRANTorREVOKE implements InGRANTs {


    Context context;
    public @Inject DashboardGRANTorREVOKE(@ApplicationContext Context context) {

        this.context=context;

        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
    }


    @SuppressLint("SuspiciousIndentation")
    @Override
    public Integer getGrantRemote(@NonNull Context context, @NonNull Integer getHiltPublicId) {
        // TODO: 11.01.2024
        Integer getGrantRemote = 0;
        try {
            @SuppressLint("Range") Single<Integer> jsonNodeSingle = Single.fromCallable(() -> {
                        // TODO: 28.12.2023
                        Integer getGrant = 0;
                        Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabasecurrentoperations/" + "chat_users" + "");
                        ContentResolver contentResolver = context.getContentResolver();
                        Cursor Курсор_Права = contentResolver.query(uri, new String[]{},
                                new String(" SELECT *  FROM    chat_users WHERE _id=" + getHiltPublicId.toString() + "    ORDER BY _id  LIMIT   1  "),
                                new String[]{}, null);///   "  //// SELECT * FROM  viewtabel WHERE year_tabels=?  AND month_tabels=?  AND cfo=?  AND status_send!=?

                        if (Курсор_Права.getCount() > 0) {
                            Курсор_Права.moveToFirst();

                            // TODO: 11.01.2024
                            getGrant = Курсор_Права.getInt(Курсор_Права.getColumnIndex("rights"));
                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " getGrant " + getGrant);

                        }

                        Курсор_Права.close();
                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + "Курсор_Права  " + Курсор_Права);
                        return getGrant;
                    }).subscribeOn(Schedulers.single())
                    .doOnError(new io.reactivex.rxjava3.functions.Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Throwable {
                            throwable.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + throwable + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(context).recordnewerror(throwable.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    });
            getGrantRemote = jsonNodeSingle.blockingGet();

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " getGrantRemote " + getGrantRemote);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                    + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return getGrantRemote;
    }

    @Override
    public void setGrantRemote(@NonNull Context context, MaterialButton КнопкаЗаявкаНаТранспорт,
                               MaterialButton КнопкаСогласование,
                               MaterialButton КнопкаСогласЦен,
                               MaterialButton КнопкаПоступлениеМатериалов,
                               MaterialButton КнопкаТабель,
                               Integer getGrantRemote) {
        // TODO: 11.01.2024
        try {


            switch (getGrantRemote) {
                // TODO: 11.01.2024  Согласование 6 7 режим только
                case 6:
                case 7:
                    // TODO: 11.01.2024  

                    setRichtFor6and7(КнопкаЗаявкаНаТранспорт, КнопкаСогласование, КнопкаСогласЦен, КнопкаПоступлениеМатериалов, КнопкаТабель);

                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " ПРАВА getGrantRemote " + getGrantRemote);
                    break;



                case 2:
                    setRichtFor2(КнопкаЗаявкаНаТранспорт, КнопкаСогласование, КнопкаСогласЦен, КнопкаПоступлениеМатериалов, КнопкаТабель);
                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " ПРАВА getGrantRemote " + getGrantRemote);
                    break;
                // TODO: 11.01.2024  режим 2  табель 
                default:
                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " ПРАВА getGrantRemote " + getGrantRemote);
                    break;


            }

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " ПРАВА getGrantRemote " + getGrantRemote);


            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                    + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }

    private void setRichtFor6and7(MaterialButton КнопкаЗаявкаНаТранспорт,
                                  MaterialButton КнопкаСогласование,
                                  MaterialButton КнопкаСогласЦен,
                                  MaterialButton КнопкаПоступлениеМатериалов,
                                  MaterialButton КнопкаТабель) {

        try {
            КнопкаПоступлениеМатериалов.setVisibility(View.GONE);
            КнопкаПоступлениеМатериалов.requestLayout();
            КнопкаПоступлениеМатериалов.refreshDrawableState();

            КнопкаТабель.setVisibility(View.GONE);
            КнопкаТабель.refreshDrawableState();
            КнопкаТабель.requestLayout();

            КнопкаЗаявкаНаТранспорт.setVisibility(View.GONE);
            КнопкаЗаявкаНаТранспорт.refreshDrawableState();
            КнопкаЗаявкаНаТранспорт.requestLayout();


            // TODO: 11.01.2024

            КнопкаСогласование.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    КнопкаСогласование.getViewTreeObserver().removeOnGlobalLayoutListener(this);


                    LinearLayout.LayoutParams layoutParams = getLayoutParams(КнопкаСогласование);

                    КнопкаСогласование.setLayoutParams(layoutParams);
                    КнопкаСогласование.requestLayout();
                    КнопкаСогласование.forceLayout();
                    КнопкаСогласование.refreshDrawableState();
                }
            });

            КнопкаСогласЦен.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    КнопкаСогласЦен.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                    LinearLayout.LayoutParams layoutParams = getLayoutParams(КнопкаСогласЦен);

                    КнопкаСогласЦен.setLayoutParams(layoutParams);
                    КнопкаСогласЦен.requestLayout();
                    КнопкаСогласЦен.forceLayout();
                    КнопкаСогласЦен.refreshDrawableState();
                }
            });

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                    + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }

    @NonNull
    private   LinearLayout.LayoutParams getLayoutParams(MaterialButton Кнопкаправа6и7) {
        LinearLayout.LayoutParams layoutParams = null;
        try{
        layoutParams = (LinearLayout.LayoutParams) Кнопкаправа6и7.getLayoutParams();

            layoutParams.topMargin=10;
        layoutParams.height = 300;
           /* layoutParams.setMargins(40,20,40,5);*/

        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return layoutParams;
    }

//TODO

    private void setRichtFor2(MaterialButton КнопкаЗаявкаНаТранспорт,
                                  MaterialButton КнопкаСогласование,
                                  MaterialButton КнопкаСогласЦен,
                                  MaterialButton КнопкаПоступлениеМатериалов,
                                  MaterialButton КнопкаТабель) {

        try {
            КнопкаПоступлениеМатериалов.setVisibility(View.VISIBLE);
            КнопкаПоступлениеМатериалов.requestLayout();
            КнопкаПоступлениеМатериалов.refreshDrawableState();

            КнопкаТабель.setVisibility(View.VISIBLE);
            КнопкаТабель.refreshDrawableState();
            КнопкаТабель.requestLayout();

            КнопкаЗаявкаНаТранспорт.setVisibility(View.VISIBLE);
            КнопкаЗаявкаНаТранспорт.refreshDrawableState();
            КнопкаЗаявкаНаТранспорт.requestLayout();


            // TODO: 11.01.2024

            КнопкаСогласование.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    КнопкаСогласование.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                    КнопкаСогласование.setHeight(100);
                    КнопкаСогласование.requestLayout();
                    КнопкаСогласование.forceLayout();
                    КнопкаСогласование.refreshDrawableState();
                }
            });

            КнопкаСогласЦен.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    КнопкаСогласЦен.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                    КнопкаСогласЦен.setHeight(100);
                    КнопкаСогласЦен.requestLayout();
                    КнопкаСогласЦен.forceLayout();
                    КнопкаСогласЦен.refreshDrawableState();
                }
            });

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :"
                    + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

    }



}
