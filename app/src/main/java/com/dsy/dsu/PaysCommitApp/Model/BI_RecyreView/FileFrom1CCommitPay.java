package com.dsy.dsu.PaysCommitApp.Model.BI_RecyreView;

import android.content.Context;
import android.util.Log;
import android.widget.TableLayout;

import com.dsy.dsu.PaysCommitApp.View.RecyreView.MyViewHolderPayCommingPay;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.BusinessLogicForApps.Services.ServiceForApprove;
import com.fasterxml.jackson.databind.JsonNode;

import org.jetbrains.annotations.NotNull;

import io.reactivex.rxjava3.annotations.NonNull;

// TODO: 03.11.2023  класс добпдонительного добвления поля Файлыв Более Онлгого
public class FileFrom1CCommitPay {
    private MyViewHolderPayCommingPay holder;
    private Context context;

    private  ServiceForApprove.LocalBinderДляСогласования binderСогласования1C;
    public FileFrom1CCommitPay(@NonNull MyViewHolderPayCommingPay holder,
                               @NonNull Context context,
                               @NonNull ServiceForApprove.LocalBinderДляСогласования binderСогласования1C) {
        this.holder = holder;
        this.context = context;
        this.binderСогласования1C = binderСогласования1C;

    }

    public void startFileFrom1CCommitPay(@NotNull JsonNode jsonNode1сСогласованияRow,
                                         @NonNull MyViewHolderPayCommingPay holder,
                                         @NonNull Integer ПубличныйidPay,
                                         @NonNull  String getHiltCommintgPays) {
        try{
            // TODO: 03.11.2023 Parent
            TableLayout tablelayoutnewfilefrom1c = (TableLayout) holder.tablelayoutnewfilefrom1c;
                // TODO: 10.11.2023  Сама Вставка Данных Файлов От 1с Согласование

                AddFilesot1CPaycommitting addFilesot1CPaycommitting = new AddFilesot1CPaycommitting(context, binderСогласования1C);

                addFilesot1CPaycommitting.addfilessot1CPaycommitting(tablelayoutnewfilefrom1c,
                        jsonNode1сСогласованияRow, holder, ПубличныйidPay,getHiltCommintgPays);

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                    " jsonNode1сСогласованияRow " +jsonNode1сСогласованияRow
                    + " tableLayoutcommitpayfiles.getChildCount() " +tablelayoutnewfilefrom1c.getChildCount());

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


    // TODO: 09.11.2023  insert from Paren Row





    //todo end class FileFrom1CCommitPay
}//todo end class FileFrom1CCommitPay
