package com.dsy.dsu.BusinessLogicForApps.AfterSynchRemoveDeletedStatus;


import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;

import java.util.stream.Stream;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.hilt.android.qualifiers.ApplicationContext;

@Named
public class GetAfterSynchRemoveDeletedStatus {

    Context context;

    public @Inject GetAfterSynchRemoveDeletedStatus(@ApplicationContext Context context) {
        this.context = context;
    }

    public void afterSynchRemoveDeletedStatus(@NonNull Context context) {
        try {
            Stream<String> streamУдалениеСтатусаУдаленный=Stream.of("data_tabels","tabel","get_materials_data" );
            streamУдалениеСтатусаУдаленный.forEach(Таблица->{
                Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabase/" + Таблица + "");
                ContentResolver resolver = context.getContentResolver();
                Integer  УдалениеДанныхСоСтатусомУдаленная=   resolver.delete(uri,"status_send=?  ",new String[]{"1СУдалено" });

                Integer    УдалениеДанныхСоСтатусомУдаленнаяТабель=   resolver.delete(uri,"status_send=?  ",new String[]{"Удаленная" });

                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                        " УдалениеДанныхСоСтатусомУдаленная "+ УдалениеДанныхСоСтатусомУдаленная);
            });

            Stream<String> streamУдалениеСтатусаУдаленныйВтрой=Stream.of( "order_tc");
            streamУдалениеСтатусаУдаленныйВтрой.forEach(Таблица->{
                Uri uri = Uri.parse("content://com.dsy.dsu.providerdatabase/" + Таблица + "");
                ContentResolver resolver = context.getContentResolver();
                Integer  УдалениеДанныхСоСтатусомУдаленная=   resolver.delete(uri,"  status=?",new String[]{ "6"});
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                        " УдалениеДанныхСоСтатусомУдаленная "+ УдалениеДанныхСоСтатусомУдаленная);
            });


            Log.d(context.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(context).recordnewerror(e.toString(), this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            Log.e(context.getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
        }
    }


}
