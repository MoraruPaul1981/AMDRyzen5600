package com.dsy.dsu.BusinessLogicForApps.FirebaseAndOneSignal.OneSignal.registOnesignal;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.BusinessLogicForApps.GetPublicID.GetttingPublicID;
import com.dsy.dsu.BusinessLogicForApps.VersionCurentTable;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.CoreApp.Apps.SettingsCoreApp.Model.GetSettingTableSaves;
import com.sous.backasync.launch.ModuleDeleting;

import java.util.Date;

import io.reactivex.rxjava3.core.Flowable;

class WriterNewKeyOneSignal {
    private Context context;
    private String НовыйIdОТСервтераOneSignal;


    public WriterNewKeyOneSignal(@NonNull Context context,
                                 @NonNull String новыйIdОТСервтераOneSignal) {
        // TODO: 12.04.2024
        this.context = context;
        this.НовыйIdОТСервтераOneSignal = новыйIdОТСервтераOneSignal;
    }

    void writeingNewOneSingle() {
        try {
            // TODO: 14.11.2021  ПОВТОРЫЙ ЗАПУСК ВОРК МЕНЕДЖЕР
            // TODO: 30.09.2021 МЕТОД ЗАПУСКА СИНХРОНИЗАЦИИ ЧАТА ПО РАСПИСАНИЮ , НЕ ВЗАВИСИМОСТИ ОТ СОЗДАВАЛ ЛИ СООБЩЕНИЕ ИЛИ НЕТ
            Integer PublicId = new GetttingPublicID().getttingPublicID(context);
            Log.d(this.getClass().getName(), "ПубличныйIDДляФрагмента  ИЗ ВСЕХ ТАБЕЛЕЙ PublicId "
                    + PublicId);

            /// TODO ########################################################втоаря часть  settings_tabels    ПЕРВАЯ ОБРАБОТКА ТАБЛИЦА  settings_tabels
            Flowable.fromArray("settings_tabels", "view_onesignal")
                    .onBackpressureBuffer()
                    .blockingForEach(ТаблицаДляПолучениеКлючаONESIGNAL -> {

                        // TODO: 13.01.2022 САМА ВСТАВКА НОВОГО КЛЮЧА В ТАБЛИЦУ НАСТРОЙКИ СИСТЕМЫ
                        Integer writerNewKeyOneSignals
                                = new GetSettingTableSaves().getWritingOneSingalSetingTable(context, НовыйIdОТСервтераOneSignal, PublicId);

                        Log.i(this.getClass().getName(), "  writerNewKeyOneSignals   "
                                + writerNewKeyOneSignals);
                        ////TODO УВЕЛИЧИВАЕМ ВЕРИСЮ ДАННЫХ  В ТАБЛИЦЕ MODIFICATION CLIENT
                        /// TODO ########################################################ЧАСТЬ ТРЕТЬЯ УДАЛЕНИЯ ДАННЫХ В ТАБЛИЦАХ настройки системы
                        if (writerNewKeyOneSignals > 0) {
                            // TODO: 29.12.2021 ЧАСТЬ ТЕРТЬЯ УДАЛАЕНИЯ ЛИШНЕХ КЛЮЧЕЙ СТАРЫХ ИЗ ДВУХ ТАБЛЦ ПО ТЕКУЩЕМУ ПОЛЬЗОВАТЕЛ
                            Integer РезультатПосикаИУдалениявТаблицах_settings_tabels =
                                    МетодПослеУспешнойОбновленияКлючаОтOneSignalИщемУдаляемДубли(context,
                                            ТаблицаДляПолучениеКлючаONESIGNAL,
                                            PublicId,
                                            НовыйIdОТСервтераOneSignal);


                            // TODO: 29.12.2021 ПОСЛЕ УСПЕШНОГО ЗАПИСАВАНИЕ НВОГО КЛЮЧА УДАЛЯЕМ ДУБЛИЗЗНАПЧЕНИЙ ЕСЛИ  ОНОИ ИИСТЬ
                            Log.d(this.getClass().getName(), "РезультатПосикаИУдалениявТаблицах_settings_tabels "
                                    + РезультатПосикаИУдалениявТаблицах_settings_tabels + "  ПубличныйIDДляФрагмента " + PublicId +
                                    " НовыйIdОТСервтераOneSignal " + НовыйIdОТСервтераOneSignal +
                                    " ТаблицаКоторуюнадоДляПосикаИУдаленияБудлейКлбчейONESIGNAL_ДЛЯ_ТАБЛИЦЫ_settings_tabels "
                                    + ТаблицаДляПолучениеКлючаONESIGNAL);


                            // TODO: 24.02.2022 увеличение данных после смены ключа
                            if (ТаблицаДляПолучениеКлючаONESIGNAL.equalsIgnoreCase("settings_tabels")) {
                                // TODO: 24.02.2022
                                МетодУвеличениеВерсииДанныхПриСменеКлючаOneSingnal(context,
                                        ТаблицаДляПолучениеКлючаONESIGNAL);
                                // TODO: 29.12.2021 ПОСЛЕ УСПЕШНОГО ЗАПИСАВАНИЕ НВОГО КЛЮЧА УДАЛЯЕМ ДУБЛИЗЗНАПЧЕНИЙ ЕСЛИ  ОНОИ ИИСТЬ
                                Log.d(this.getClass().getName(), "РезультатПосикаИУдалениявТаблицах_settings_tabels "
                                        + РезультатПосикаИУдалениявТаблицах_settings_tabels + "  ПубличныйIDДляФрагмента " + PublicId +
                                        " НовыйIdОТСервтераOneSignal " + НовыйIdОТСервтераOneSignal +
                                        " ТаблицаКоторуюнадоДляПосикаИУдаленияБудлейКлбчейONESIGNAL_ДЛЯ_ТАБЛИЦЫ_settings_tabels "
                                        + ТаблицаДляПолучениеКлючаONESIGNAL);
                            }
                        }


                    });

            // TODO: 02.05.2021
            Log.d(context.getClass().getName(), "\n"
                    + " время: " + new Date() + "\n+" +
                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " НовыйIdОТСервтераOneSignal " + НовыйIdОТСервтераOneSignal);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }


    }


    private void МетодУвеличениеВерсииДанныхПриСменеКлючаOneSingnal(Context context
            , String ТаблицаКоторуюнадоИзменитьВерсиюДанныхТАюдицы_VIEW_ONESIGNAL) {
        try {

            // TODO: 18.03.2023  получаем ВЕСИЮ ДАННЫХ
            Long РезультатУвеличинаяВерсияПриУвеличенияПриПолученияКлючаONESINGLE =
                    new VersionCurentTable(context).upVersionCurentTable(
                            ТаблицаКоторуюнадоИзменитьВерсиюДанныхТАюдицы_VIEW_ONESIGNAL);
            Log.d(this.getClass().getName(), " РезультатУвеличинаяВерсияПриУвеличенияПриПолученияКлючаONESINGLE  " +
                    РезультатУвеличинаяВерсияПриУвеличенияПриПолученияКлючаONESINGLE);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


    // TODO: 29.12.2021
    protected Integer МетодПослеУспешнойОбновленияКлючаОтOneSignalИщемУдаляемДубли(@NonNull  Context context,
                                                                                   @NonNull    String ТаблицаКоторуюнадоДляПосикаИУдаленияБудлейКлбчейONESIGNAL,
                                                                                   @NonNull    Integer ПубличныйIDДляФрагмента,
                                                                                   @NonNull String НовыйIdОТСервтераOneSignal) {

        Integer РЕзультаПосикаИУдаления = 0;
        try {
            // TODO: 14.05.2025
            ModuleDeleting moduleDeleting = new ModuleDeleting(context);
            // TODO: 03.02.2025 update new back
            РЕзультаПосикаИУдаления=    moduleDeleting.getModuleDelete(ТаблицаКоторуюнадоДляПосикаИУдаленияБудлейКлбчейONESIGNAL,
                    "onesignal <> ? AND  user_update =?",new String[]{НовыйIdОТСервтераOneSignal,ПубличныйIDДляФрагмента.toString() });

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " РЕзультаПосикаИУдаления "+РЕзультаПосикаИУдаления );

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return РЕзультаПосикаИУдаления;
    }


}
