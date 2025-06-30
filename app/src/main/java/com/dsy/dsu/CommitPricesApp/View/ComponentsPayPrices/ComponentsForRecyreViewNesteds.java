package com.dsy.dsu.CommitPricesApp.View.ComponentsPayPrices;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.dsy.dsu.CommitPricesApp.Model.LiveDataPrices.GetLiveDataForrecyreViewPrices;
import com.dsy.dsu.CommitPricesApp.Model.SendDataTo1C.StartSendJsonToCOmmintPrices;
import com.dsy.dsu.CommitPricesApp.View.MyRecycleViewNested.MyRecycleViewIsAdaptersNestedCommintPrices;
import com.dsy.dsu.CommitPricesApp.View.MyRecycleViewNested.MyViewHoldersNestedCommintPrices;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.R;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.TextNode;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;
import com.jakewharton.rxbinding4.view.RxView;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import kotlin.Unit;

public class ComponentsForRecyreViewNesteds {


    private Context context;
    private  int getAbsoluteAdapterPosition;

    private  MaterialCardView cardview_commingprices_neasted;

    // TODO: 30.12.2023 компоненты для запоелния
    private MaterialTextView mTV_commitingprices_count;

    private     MaterialButton arrow_nested_receriview;

    private     MaterialTextView mTV_Nomenklatura,mTV_StatyaDDS_value
            ,mTV_EdIzm_value,mTV_Data_value,
            mTV_Kolichestvo_value,mTV_CFORaskhoda_value,mTV_Kontragent_value,mTV_NumberDOc_value,mTV_NumberRow_value;


    private ObjectMapper objectMapper;

    private Integer getHiltPublicId;

    private MyRecycleViewIsAdaptersNestedCommintPrices myRecycleViewIsAdaptersNestedCommintPrices;
    private JsonNode jsonNodeNested;

    private   String getHiltCommintgPrices;

    private GetLiveDataForrecyreViewPrices getLiveDataForrecyreViewPrices;
    private MutableLiveData<Intent> getHiltMutableLiveDataPay;

    private LifecycleOwner lifecycleOwner;

    private  RecyclerView recycleview_comminingppricesNesteds;

    private MyViewHoldersNestedCommintPrices holderNested;

    public ComponentsForRecyreViewNesteds(@NotNull MyViewHoldersNestedCommintPrices holderNested,
                                          @NotNull  Context context,
                                          @NotNull int getAbsoluteAdapterPosition,
                                          @NotNull MaterialCardView cardview_commingprices_neasted,
                                          @NotNull ObjectMapper objectMapper,
                                          @NotNull Integer getHiltPublicId,
                                          @NotNull MyRecycleViewIsAdaptersNestedCommintPrices myRecycleViewIsAdaptersNestedCommintPrices,
                                          @NotNull JsonNode jsonNodeNested,
                                          @NotNull String getHiltCommintgPrices,
                                          @NotNull GetLiveDataForrecyreViewPrices getLiveDataForrecyreViewPrices,
                                          @NotNull MutableLiveData<Intent> getHiltMutableLiveDataPay,
                                          @NotNull    LifecycleOwner lifecycleOwner,
                                          @NotNull RecyclerView recycleview_comminingppricesNesteds) {
        this.holderNested = holderNested;
        this.context = context;
        this.getAbsoluteAdapterPosition = getAbsoluteAdapterPosition;
        this.cardview_commingprices_neasted = cardview_commingprices_neasted;
        this.objectMapper = objectMapper;
        this.getHiltPublicId = getHiltPublicId;
        this.myRecycleViewIsAdaptersNestedCommintPrices = myRecycleViewIsAdaptersNestedCommintPrices;
        this.jsonNodeNested = jsonNodeNested;
        this.getHiltCommintgPrices = getHiltCommintgPrices;
        this.getLiveDataForrecyreViewPrices = getLiveDataForrecyreViewPrices;
        this.getHiltMutableLiveDataPay = getHiltMutableLiveDataPay;
        this.lifecycleOwner = lifecycleOwner;
        this.recycleview_comminingppricesNesteds = recycleview_comminingppricesNesteds;
    }




    public MaterialTextView getmTV_commitingprices_count(@NotNull JsonNode jsonNodeNestedRow) {
  try{
      mTV_commitingprices_count=    holderNested.itemView.findViewById(R.id.mTV_commitingprices_count) ;
      JsonNode jsonNode=   jsonNodeNestedRow.deepCopy();
      TextNode textNodeCena=( TextNode)   jsonNode.findValue("Cena").deepCopy();
      String cena=  textNodeCena.asText().trim();
      if (! cena.isEmpty()) {
          mTV_commitingprices_count.setText(cena+" (руб)");
      }
      mTV_commitingprices_count.requestLayout();

      Log.d(this.getClass().getName(), "\n"
                + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());

    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }

        return mTV_commitingprices_count;
    }

    public MaterialButton getArrow_nested_receriview(@NotNull  int position,@NotNull JsonNode jsonNodeNestedRow) {
        try{
            arrow_nested_receriview=    holderNested.itemView.findViewById(R.id.arrow_nested_receriview) ;

            RxView.clicks(  arrow_nested_receriview)
                    .throttleFirst(10, TimeUnit.SECONDS)
                    .filter(s -> !s.toString().isEmpty())
                    .map(new Function<Unit, MaterialButton>() {
                        @Override
                        public MaterialButton apply(Unit unit) throws Throwable {

                            // TODO: 30.12.2023 вибрация
                            Vibrator v2 = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
                            v2.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));

                            cardview_commingprices_neasted.animate().rotationXBy(5);

                            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );

                            return    arrow_nested_receriview;
                        }
                    })
                    .doOnError(new Consumer<Throwable>() {
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
                    .subscribe( MaterialButtonNested-> {


                        ///todo revboot нажимаем для подтвердить
                       eventButtonArrow(MaterialButtonNested,position);

                        cardmaretialrotadefult();

                        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );

                    });

            Log.d(this.getClass().getName(), "\n"
                    + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return arrow_nested_receriview;
    }

    private void eventButtonArrow(@NotNull  MaterialButton MaterialButtonNested,@NotNull  int position) {
        Handler handler=     arrow_nested_receriview.getHandler();
        handler.postDelayed(()->{
            // TODO: 11.01.2024
            try{
                // TODO: 10.01.2024  запускаем Генерацию JSON  согласование 1с
                Bundle bundleДанныеДляPost=(Bundle)          cardview_commingprices_neasted.getTag();
                StartSendJsonToCOmmintPrices startSendJsonToCOmmintPrices=new StartSendJsonToCOmmintPrices(context,objectMapper,getHiltPublicId);
                byte[] ByteFor1CCommintPrices=   startSendJsonToCOmmintPrices.startSendJson1c(bundleДанныеДляPost);

                if (ByteFor1CCommintPrices!=null) {
                    // TODO: 10.01.2024  Отправляем Сгенерированый JSON
                    String UUID=   bundleДанныеДляPost.getString("UUID").trim();
                    

                    getLiveDataForrecyreViewPrices.setObservableLiveDataRecyreViewCommitPrices(lifecycleOwner,
                            context,
                            getHiltMutableLiveDataPay
                            ,ByteFor1CCommintPrices,getHiltPublicId,getHiltCommintgPrices,UUID,mTV_commitingprices_count,
                            myRecycleViewIsAdaptersNestedCommintPrices,position,cardview_commingprices_neasted,
                            jsonNodeNested,holderNested,recycleview_comminingppricesNesteds);





                    // TODO: 30.01.2024  запускаем Mutable 
                    sendLiveDataRecyreViewEventCallBacl1c(  );


                    Log.d(this.getClass().getName(),"\n"
                            + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()
                            +  " MaterialButtonNested " + MaterialButtonNested);

                }else {

                    Toast.makeText(context, "Не прошла операция !!!"
                            +"\n"+mTV_commitingprices_count.getText().toString(), Toast.LENGTH_LONG).show();
                }




                Log.d(this.getClass().getName(),"\n"
                        + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()
                        +  " MaterialButtonNested " + MaterialButtonNested
                        + " bundleДанныеДляPost " +bundleДанныеДляPost);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }

        },100);
    }

    private void cardmaretialrotadefult() {
        cardview_commingprices_neasted.animate().rotationXBy(-5);
    }

    private void sendLiveDataRecyreViewEventCallBacl1c(  ) {

        try{
            Intent intentCallBackRcyreCiew1cPayEvent=new Intent("CallBackRecyreViewPrices");
            getHiltMutableLiveDataPay.postValue(intentCallBackRcyreCiew1cPayEvent);

            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" );

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }
    public MaterialTextView getmTV_Nomenklatura(@NotNull JsonNode jsonNodeNestedRow) {
        try{
            mTV_Nomenklatura=    holderNested.itemView.findViewById(R.id.mTV_Nomenklatura) ;
            JsonNode jsonNode=    jsonNodeNestedRow.deepCopy();
            TextNode textNodeNomenklatura=( TextNode)   jsonNode.findValue("Nomenklatura").deepCopy();
            // TODO: 30.12.2023  
            if ( !textNodeNomenklatura.asText().isEmpty()) {
                // TODO: 30.12.2023  set
                mTV_Nomenklatura.setText(textNodeNomenklatura.asText().trim());
            }
            mTV_Nomenklatura.requestLayout();
            Log.d(this.getClass().getName(), "\n"
                    + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return mTV_Nomenklatura;
    }

    public MaterialTextView getmTV_StatyaDDS_value(@NotNull JsonNode jsonNodeNestedRow) {
        try{
            mTV_StatyaDDS_value=    holderNested.itemView.findViewById(R.id.mTV_StatyaDDS_value) ;
            JsonNode jsonNode=   jsonNodeNestedRow.deepCopy();
            TextNode textNodeStatyaDDS=( TextNode)   jsonNode.findValue("StatyaDDS").deepCopy();
                // TODO: 30.12.2023  
            if (!textNodeStatyaDDS.asText().isEmpty()) {
                // TODO: 30.12.2023 set
                mTV_StatyaDDS_value.setText(textNodeStatyaDDS.asText().trim());
            }

            mTV_StatyaDDS_value.requestLayout();
            Log.d(this.getClass().getName(), "\n"
                    + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return mTV_StatyaDDS_value;
    }

    public MaterialTextView getmTV_EdIzm_value(@NotNull JsonNode jsonNodeNestedRow) {
        try{
            mTV_EdIzm_value=    holderNested.itemView.findViewById(R.id.mTV_EdIzm_value) ;
            JsonNode jsonNode=     jsonNodeNestedRow.deepCopy();
            TextNode textNodeEdIzm=( TextNode)   jsonNode.findValue("EdIzm").deepCopy();
            // TODO: 30.12.2023  
            if ( !textNodeEdIzm.asText().isEmpty()) {
                // TODO: 30.12.2023  set
                mTV_EdIzm_value.setText(textNodeEdIzm.asText().trim());
            }

            mTV_EdIzm_value.requestLayout();
            Log.d(this.getClass().getName(), "\n"
                    + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return mTV_EdIzm_value;
    }



    public MaterialTextView getmTV_Data_value(@NotNull JsonNode jsonNodeNestedRow) {
        try{
            mTV_Data_value=    holderNested.itemView.findViewById(R.id.mTV_Data_value) ;
            JsonNode jsonNode=     jsonNodeNestedRow.deepCopy();
            TextNode textNodeData=( TextNode)   jsonNode.findValue("Data").deepCopy();
            if ( !textNodeData.asText().isEmpty()) {
                String data=  textNodeData.asText().trim();
            Date датаEdIzm =
                    new  SimpleDateFormat("dd.MM.yyyy HH:mm:ss", new Locale("ru")).parse(data);//TODO "2023-08-01 19:00:59.781"

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss",new Locale("ru"));
            LocalDate dateTime = LocalDate.parse(textNodeData.asText().trim(), formatter);

         SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd.MM.yyyy");
            String ДатаEdIzm=simpleDateFormat.format(датаEdIzm);
            // TODO: 30.12.2023  

                // TODO: 30.12.2023 set
                mTV_Data_value.setText(ДатаEdIzm);
            }
            mTV_Data_value.requestLayout();
            Log.d(this.getClass().getName(), "\n"
                    + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return mTV_Data_value;
    }

    public MaterialTextView getmTV_Kolichestvo_value(@NotNull JsonNode jsonNodeNestedRow) {
        try{
            mTV_Kolichestvo_value=    holderNested.itemView.findViewById(R.id.mTV_Kolichestvo_value) ;
            JsonNode jsonNode=    jsonNodeNestedRow.deepCopy();
            TextNode textNodeKolichestvo=( TextNode)   jsonNode.findValue("Kolichestvo").deepCopy();

            if ( !textNodeKolichestvo.asText().isEmpty()) {
                // TODO: 30.12.2023 set
                mTV_Kolichestvo_value.setText(textNodeKolichestvo.asText().trim());
            }
            mTV_Kolichestvo_value.requestLayout();

            Log.d(this.getClass().getName(), "\n"
                    + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return mTV_Kolichestvo_value;
    }

    public MaterialTextView getmTV_CFORaskhoda_value(@NotNull JsonNode jsonNodeNestedRow) {
        try{
            mTV_CFORaskhoda_value=    holderNested.itemView.findViewById(R.id.mTV_CFORaskhoda_value) ;
            JsonNode jsonNode=     jsonNodeNestedRow.deepCopy();
            TextNode textNodeCFORaskhoda=( TextNode)   jsonNode.findValue("CFORaskhoda").deepCopy();
            // TODO: 30.12.2023
            if (! textNodeCFORaskhoda.asText().isEmpty()) {
                mTV_CFORaskhoda_value.setText(textNodeCFORaskhoda.asText().trim());
            }
            mTV_CFORaskhoda_value.requestLayout();
            Log.d(this.getClass().getName(), "\n"
                    + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return mTV_CFORaskhoda_value;
    }

    public MaterialTextView getmTV_Kontragent_value(@NotNull JsonNode jsonNodeNestedRow) {
        try{
            mTV_Kontragent_value=    holderNested.itemView.findViewById(R.id.mTV_Kontragent_value) ;
            JsonNode jsonNode=     jsonNodeNestedRow.deepCopy();
            if (jsonNode.has("Kontragent")) {
                TextNode textNodeKontragent=( TextNode)   jsonNode.findValue("Kontragent").deepCopy();
                // TODO: 30.12.2023
                if (! textNodeKontragent.asText().isEmpty()) {
                    mTV_Kontragent_value.setText(textNodeKontragent.asText().trim());
                }
            }
            mTV_Kontragent_value.requestLayout();
            Log.d(this.getClass().getName(), "\n"
                    + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return mTV_Kontragent_value;
    }




    public MaterialTextView getmTV_NumberRow_value(@NotNull JsonNode jsonNodeNestedRow) {
        try{
            mTV_NumberRow_value=    holderNested.itemView.findViewById(R.id.mTV_NumberRow_value) ;
            JsonNode jsonNode=    jsonNodeNestedRow.deepCopy();
            if (jsonNode.has("NStr")) {
                TextNode textNodeNStr=( TextNode)   jsonNode.findValue("NStr").deepCopy();
                // TODO: 30.12.2023
                if (! textNodeNStr.asText().isEmpty()) {
                    mTV_NumberRow_value.setText(String.valueOf(textNodeNStr.asInt()));
                }
            }
            mTV_NumberRow_value.requestLayout();
            Log.d(this.getClass().getName(), "\n"
                    + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return mTV_NumberRow_value;
    }

    public MaterialCardView setagMaterialCardViewNestad(@NotNull JsonNode jsonNodeNestedRow) {

        try{
            JsonNode jsonNode=     jsonNodeNestedRow.deepCopy();
            TextNode textNodeUUID=( TextNode)   jsonNode.findValue("UUID").deepCopy();
            TextNode textNodeCena=( TextNode)   jsonNode.findValue("Cena").deepCopy();
            if(!textNodeUUID.asText().isEmpty()){
                String  UUID= textNodeUUID.asText();
                Long  UUIDlong= textNodeUUID.asLong();
                // TODO: 10.01.2024 цена
                String  Цена= textNodeCena.asText();
                // TODO: 10.01.2024 цена

                Bundle bundleuuid=new Bundle();
                bundleuuid.putString("UUID",UUID);
                bundleuuid.putString("Цена",Цена);
                bundleuuid.putLong("UUIDlong",UUIDlong);
                // TODO: 30.12.2023 uuid get
                cardview_commingprices_neasted.setTag(bundleuuid);
            }
            cardview_commingprices_neasted.forceLayout();

            Log.d(this.getClass().getName(), "\n"
                    + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() );

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        return cardview_commingprices_neasted;
    }







}






