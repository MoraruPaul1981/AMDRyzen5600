package com.dsy.dsu.PaysCommitApp.Model.BI_RecyreView;

// TODO: 08.11.2023 ЗАПОЛЕНИЕ ФАЛОМИ


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.PaysCommitApp.View.RecyreView.MyViewHolderPayCommingPay;
import com.dsy.dsu.R;
import com.dsy.dsu.BusinessLogicForApps.Services.ServiceForApprove;
import com.google.android.material.card.MaterialCardView;

import org.jetbrains.annotations.NotNull;

import java.util.Random;

import io.reactivex.rxjava3.annotations.NonNull;

class AddFileFromPayCommiting{
   private Context context;
    private  ServiceForApprove.LocalBinderДляСогласования binderСогласования1C;

    public AddFileFromPayCommiting(Context context, ServiceForApprove.LocalBinderДляСогласования binderСогласования1C) {
        this.context = context;
        this.binderСогласования1C = binderСогласования1C;
    }


    // TODO: 08.11.2023 метод додабвление файлов от 1с
    void   addingNewFilePay(@NotNull TableLayout tableLayoutcommitpayfiles,
                            @NotNull Context context,
                            @NotNull String НазваниеТекущегот1С,
                            @NotNull String РасширенияФайла,
                            @NonNull MyViewHolderPayCommingPay holder,
                            @NonNull Integer ПубличныйidPay,
                            @NonNull  String getHiltCommintgPays){
        try{

            // TODO: 03.11.2023 Childern
            MaterialCardView materialCardViewRowpaycommit =
                    (MaterialCardView) LayoutInflater.from(context).inflate(R.layout.simpleforfileaycommit_newfile1c_pay, null);

            // TODO: 03.11.2023 Childern
            TableLayout tablelayoutnewrowcommingpay = materialCardViewRowpaycommit.findViewById(R.id.tablelayoutnewrowcommingpay);

            TableRow tableRowpaycommitingpay =   tablelayoutnewrowcommingpay.findViewById(R.id.tableRowpaycommitingpay);

// TODO: 18.01.2024 для получение файла

                ProccesingCancelOrOKPay proccesingCancelOrOKPay=new ProccesingCancelOrOKPay(context,binderСогласования1C);


                // TODO: 03.11.2023 Set Datas NEW
                proccesingCancelOrOKPay . metodAddTExtView1cPayCommit(НазваниеТекущегот1С, tablelayoutnewrowcommingpay, tableRowpaycommitingpay,РасширенияФайла);

                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                        " НазваниеТекущегот1С " +НазваниеТекущегот1С+" РасширенияФайла " +РасширенияФайла);

                // TODO: 10.11.2023  меняем Дизан textview FILE
                proccesingCancelOrOKPay .   metodAddTExtViewChengeDisainPayCommit(tableRowpaycommitingpay);


                // TODO: 10.11.2023  Клик по TExtView  FIle
                proccesingCancelOrOKPay.   metodClicksForTextViewPayCommit(tableRowpaycommitingpay,holder,ПубличныйidPay,getHiltCommintgPays);


                // TODO: 03.11.2023 Delete Datas
            tablelayoutnewrowcommingpay.recomputeViewAttributes(tableRowpaycommitingpay);
            tablelayoutnewrowcommingpay.removeViewInLayout(tableRowpaycommitingpay);
            tablelayoutnewrowcommingpay.removeView(tableRowpaycommitingpay);
            tablelayoutnewrowcommingpay.setId(new Random().nextInt());
            tablelayoutnewrowcommingpay.recomputeViewAttributes(tableRowpaycommitingpay);


                // TODO: 03.11.2023 Final Add Row in Parent Tableyout
                metodParentAddRowFinal(tableRowpaycommitingpay, tableLayoutcommitpayfiles);




            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                    " НазваниеТекущегот1С " +НазваниеТекущегот1С);

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



    private void metodParentAddRowFinal(TableRow rowПервыеДанные,
                                        @NonNull TableLayout tableLayoutРодительская) {
        try {
            tableLayoutРодительская.removeView(rowПервыеДанные);
            tableLayoutРодительская.removeViewInLayout(rowПервыеДанные);
            tableLayoutРодительская.addView(rowПервыеДанные);
            tableLayoutРодительская.requestLayout();
            tableLayoutРодительская.refreshDrawableState();

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
}//todo end classs class AddFileFromPayCommiting
