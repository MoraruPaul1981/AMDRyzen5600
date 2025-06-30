package com.dsy.dsu.PaysCommitApp.Model.BI_RecyreView;

import android.content.Context;
import android.util.Log;
import android.widget.TableLayout;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.PaysCommitApp.View.RecyreView.MyViewHolderPayCommingPay;
import com.dsy.dsu.BusinessLogicForApps.Services.ServiceForApprove;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

import io.reactivex.rxjava3.annotations.NonNull;

// TODO: 10.11.2023  класс Самой вставки файлов от 1с
class AddFilesot1CPaycommitting{

    private  ServiceForApprove.LocalBinderДляСогласования binderСогласования1C;

    Context context;

    public AddFilesot1CPaycommitting(@NonNull  Context context,
                                     @NonNull ServiceForApprove.LocalBinderДляСогласования binderСогласования1C) {
        this.context = context;
        this.binderСогласования1C = binderСогласования1C;
    }

    void addfilessot1CPaycommitting(@NotNull TableLayout tableLayoutpayfilescommitingpays,
                                    @NonNull JsonNode jsonNode1сСогласованияRow,
                                    @NonNull MyViewHolderPayCommingPay holder,
                                    @NonNull Integer ПубличныйidPay,
                                    @NonNull  String getHiltCommintgPays){
        try{
                // TODO: 10.11.2023  Заполняем ДАнные Из Массива Файлов
                ArrayNode datasetArray = (ArrayNode)jsonNode1сСогласованияRow.get("filenames");

            datasetArray.elements().forEachRemaining(new Consumer<JsonNode>() {
                @Override
                public void accept(JsonNode jsonNodeМассивФайлы1cBinaty) {
                    try{
                        // TODO: 10.11.2023 ццикл массив  крутим файцлы вставки
                        ArrayFileNewPay1c(      jsonNodeМассивФайлы1cBinaty,  tableLayoutpayfilescommitingpays,holder, ПубличныйidPay,getHiltCommintgPays);

                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                " jsonNode1сСогласованияRow " +jsonNode1сСогласованияRow);

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
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                        " jsonNode1сСогласованияRow " +jsonNode1сСогласованияRow);

            tableLayoutpayfilescommitingpays.refreshDrawableState();
            tableLayoutpayfilescommitingpays.requestLayout();

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                    " jsonNode1сСогласованияRow " +jsonNode1сСогласованияRow);
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


    // TODO: 10.11.2023  класс крутим файлы от 1с
    void  ArrayFileNewPay1c(@NotNull  JsonNode      МассивИменСограсований,
                            @NonNull TableLayout tableLayoutpayfilescommitingpays,
                            @NonNull MyViewHolderPayCommingPay holder,
                            @NonNull Integer ПубличныйidPay,
                            @NonNull  String getHiltCommintgPays){
        try{


            String НазваниеТекущегот1С    = МассивИменСограсований.get("ВinNameFile").asText().trim();
            String РасширенияФайла=   МассивИменСограсований.get("expansion").asText().trim();

            // TODO: 10.11.2023  добалвем новую строчку
            AddFileFromPayCommiting addFileFromPayCommiting=new AddFileFromPayCommiting(context,binderСогласования1C);

            addFileFromPayCommiting.addingNewFilePay(tableLayoutpayfilescommitingpays,context,НазваниеТекущегот1С,РасширенияФайла,holder,ПубличныйidPay,getHiltCommintgPays);

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " НазваниеТекущегот1С " +НазваниеТекущегот1С+" РасширенияФайла " +РасширенияФайла);
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








/////todo END CLASS  class AddFilesot1CPaycommitting
}/////todo END CLASS  class AddFilesot1CPaycommitting
