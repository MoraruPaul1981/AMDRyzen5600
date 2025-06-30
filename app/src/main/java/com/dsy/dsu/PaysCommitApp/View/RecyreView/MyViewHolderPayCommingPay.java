package com.dsy.dsu.PaysCommitApp.View.RecyreView;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.R;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;

// TODO: 15.03.2022  перенесееный код
// TODO: 28.02.2022 начало  MyViewHolderДляЧата
public class MyViewHolderPayCommingPay extends RecyclerView.ViewHolder {// TODO: 28.02.2022 начало  MyViewHolderДляЧата
    // TODO: 28.02.2022
    public MaterialTextView tx_sum, tx_nomer, tx_zfo, tx_kontragent, tx_statiy, tx_namelklatura, tx_organizations;
    public MaterialButton     arrowpay_nested_receriview_commitingpay;
    public MaterialCardView cardview_commingpay;
    public MaterialButton butt_cancel,butt_successcommit;


    public ProgressBar progressbar_commingpay;

    public TableLayout  tablelayoutnewfilefrom1c;

    public TableRow tableRowFisrt, tablerowsecond,tableRowpayButtons;
    public RecyclerView recycleview_nesters_commininggpay;

    public Context context;
    public   JsonNode jsonNode1сСогласования;
    public MaterialTextView obiyectRaskhoda, Avans, PoDogovoruSMP, PredelnayaData, NomerScheta, DataScheta, Otvetstvenniy, Commentariy;



    // TODO: 02.03.2022
    public MyViewHolderPayCommingPay(@NonNull View itemView,
                                     @NonNull Context context,
                                     @NonNull JsonNode jsonNode1сСогласования) {
        super(itemView);
        try{
            this.context=context;
            this.jsonNode1сСогласования = jsonNode1сСогласования;
            // TODO: 02.03.2022
            МетодИнициализацииКомпонетовЗаданияCardView(itemView);
            // TODO: 01.03.2022
            Log.d(this.getClass().getName(), "  private class MyViewHolderДляЧата extends RecyclerView.ViewHolder  itemView   " + itemView);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }

    // TODO: 14.03.2022
    private void МетодИнициализацииКомпонетовЗаданияCardView(@NonNull View itemView) {
        try {
                tx_sum = itemView.findViewById(R.id.tx_sum);
                tx_nomer = itemView.findViewById(R.id.tx_nomer);
                tx_zfo = itemView.findViewById(R.id.tx_zfo);
                tx_kontragent = itemView.findViewById(R.id.tx_kontragent);
                tx_statiy = itemView.findViewById(R.id.tx_statiy);
                tx_namelklatura = itemView.findViewById(R.id. tx_namelklatura);
                tx_organizations = itemView.findViewById(R.id.tx_organizations);


                 cardview_commingpay = itemView.findViewById(R.id.cardview_commingpay);

                butt_successcommit = itemView.findViewById(R.id.butt_successcommit);
                butt_cancel = itemView.findViewById(R.id.butt_cancel);
             progressbar_commingpay = itemView.findViewById(R.id.progressbar_commingpay);

                // TODO: 12.01.2024 Click Arrow Nestesd
            arrowpay_nested_receriview_commitingpay= itemView.findViewById(R.id.arrowpay_nested_receriview_commitingpay);
            recycleview_nesters_commininggpay = itemView.findViewById(R.id.recycleview_nesters_commininggpay);



            // TODO: 17.01.2024 переменые доля Скрытого Nested  RecyreView    // TODO: 17.01.2024 переменые доля Скрытого Nested  RecyreView

            obiyectRaskhoda = itemView.findViewById(R.id.obiyectRaskhoda);
            Avans = itemView.findViewById(R.id.Avans);
            PoDogovoruSMP = itemView.findViewById(R.id.PoDogovoruSMP);
            PredelnayaData = itemView.findViewById(R.id.PredelnayaData);
            NomerScheta = itemView.findViewById(R.id.NomerScheta);
            DataScheta = itemView.findViewById(R.id.DataScheta);
            Otvetstvenniy = itemView.findViewById(R.id.Otvetstvenniy);
            Commentariy = itemView.findViewById(R.id.Commentariy);






            // TODO: 26.01.2024 данные для послддующего скрытия
            tableRowFisrt=  itemView.findViewById(R.id.tableRowFisrt);
            tablerowsecond = itemView.findViewById(R.id.tablerowsecond);
            tableRowpayButtons = itemView.findViewById(R.id.tableRowpayButtons);
            tablelayoutnewfilefrom1c = itemView.findViewById(R.id.tablelayoutnewfilefrom1c);



            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()  + " mTV_1value  " + obiyectRaskhoda);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            // TODO: 01.09.2021 метод вызова
            new RecordNewErros(context).recordnewerror(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        /////
    }
} // TODO: 28.02.2022 конец  MyViewHolderДляЧата
// TODO: 28.02.2022 конец  MyViewHolderДляЧата