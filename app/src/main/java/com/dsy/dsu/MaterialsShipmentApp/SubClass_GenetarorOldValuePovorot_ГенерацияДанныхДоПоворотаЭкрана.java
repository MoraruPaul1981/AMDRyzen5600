package com.dsy.dsu.MaterialsShipmentApp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.R;
import com.google.android.material.textview.MaterialTextView;

import java.io.Serializable;
import java.util.Optional;

 class SubClass_GenetarorOldValuePovorot_ГенерацияДанныхДоПоворотаЭкрана extends MainActivity_shipment_of_materials {
    // TODO: 18.07.2022
    protected RecyclerView МетодЗапоеленияДаннымиПриВозвратеПослеПоворотаЭкрана(Bundle outState, @NonNull Fragment fragment_дляОтгрузкаМатериаловПерваяКнопка) {
        // outState.putSerializable("fragment_дляОтгрузкаМатериаловПерваяКнопка",(Serializable) fragment_дляОтгрузкаМатериаловПерваяКнопка);

        RecyclerView recyclerView = null;
        try {
            Spinner spinnerФрагмента = ((Spinner) fragment_дляОтгрузкаМатериаловПерваяКнопка.getView().findViewById(R.id.Spinner_shipment_of_materials));

            Log.d(this.getClass().getName(), "spinnerФрагмента " + spinnerФрагмента);

            TextView materialTextView = (TextView) spinnerФрагмента.getSelectedView();

            Integer IDВыбраногоЦФОДляЗагрузкиЛимитаМатерилов = 0;

            if (materialTextView != null) {
                Object materialTextViewЕслиTag = materialTextView.getTag();
                String ВЫтаскиваемTAgВлимитеМатериалов = null;
                if (materialTextViewЕслиTag != null) {
                    ВЫтаскиваемTAgВлимитеМатериалов = Optional.ofNullable(materialTextViewЕслиTag.toString()).orElse("");
                }
                IDВыбраногоЦФОДляЗагрузкиЛимитаМатерилов =
                        Optional.ofNullable(ВЫтаскиваемTAgВлимитеМатериалов).map(Integer::new).orElse(0);
                // TODO: 18.07.2022  само заполенеия в систменый бандл #1
                if (IDВыбраногоЦФОДляЗагрузкиЛимитаМатерилов>0) {
                    // TODO: 18.07.2022  заполняем только если есть публичный ID СФО с спинаре фрагмента
                    outState.putInt("ПослеRestartIDВыбраногоЦФОДляЗагрузкиЛимитаМатерилов", IDВыбраногоЦФОДляЗагрузкиЛимитаМатерилов);
                    String СамоНазваниЦФОПослеRestart = materialTextView.getText().toString();
                    // TODO: 18.07.2022  само заполенеия в систменый бандл #2
                    outState.putString("ПослеRestartСамоНазваниЦФОПослеRestart", СамоНазваниЦФОПослеRestart.trim());
                    // TODO: 18.07.2022  попытка получени данных из самого фрагмента уже заполнего компонета
                    MaterialTextView textView1ФрагментСодержимое;
                    // TODO: 18.07.2022 value#1 
                    textView1ФрагментСодержимое  = (MaterialTextView)      fragment_дляОтгрузкаМатериаловПерваяКнопка.getView().findViewById(R.id.value1);
                    Log.d(this.getClass().getName(), "БуферРезультатПолучениеДанныхОт1СВторойЭтап " + БуферРезультатПолучениеДанныхОт1СВторойЭтап);
                    outState.putSerializable(         "БуферРезультатПолучениеДанныхОт1СВторойЭтап",(Serializable) БуферРезультатПолучениеДанныхОт1СВторойЭтап);


                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            ///метод запись ошибок в таблицу
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }

        return  recyclerView;

    }
}
