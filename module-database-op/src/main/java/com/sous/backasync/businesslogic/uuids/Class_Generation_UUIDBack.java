package com.sous.backasync.businesslogic.uuids;

import static java.util.Calendar.getInstance;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.sous.backasync.businesslogic.errors.RecordNewErroBack;
import com.sous.backasync.launch.ModuleQuety;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Class_Generation_UUIDBack {
    private Context context;
    private  Integer ПубличныйID =0;

    private   ModuleQuety moduleQuety;
    public Class_Generation_UUIDBack(Context context) {
        this.context = context;
        // TODO: 03.02.2025
    }
    public Long МетодГенерацииUUID() {
        Long UUID = 0l;
        try {
            if (context!=null) {
                // TODO ГЕНЕРАЦИЯ UUID ВВИДЕ ЦИФРЫ
                //"yyyyMMddHHmmssSSS" //"EEEEE MMMMM yyyy HH:mm:ss.SSSSSSZ"
                Date Дата = getInstance().getTime();
                // DateFormat dateFormat = new SimpleDateFormat("yyyyddMMHHmmssSS", new Locale("ru"));
                DateFormat dateFormat = new SimpleDateFormat("yyddMMHHmmssS", new Locale("ru"));
                dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
                String СгенерированоДатаДляUUIDШагПервый = dateFormat.format(Дата);
                Long СгенерированоДатаВТипеLong = Long.parseLong(СгенерированоДатаДляUUIDШагПервый);

                //todo гененируем если есть публичный id
                moduleQuety=new ModuleQuety(context);
                Cursor getbackasyncQueryPulicID   =moduleQuety.getModuleQuery("successlogin",
                        " SELECT  sus.publicid  FROM successlogin  as sus   ORDER BY sus.id DESC  " ,
                        null);

                if(getbackasyncQueryPulicID.getCount()>0){
                    getbackasyncQueryPulicID.moveToFirst();
                    ПубличныйID =         getbackasyncQueryPulicID.getInt(0);
                    Log.d(this.getClass().getName(), " ID  " + ПубличныйID);
                }

                // TODO: 24.03.2021 ЕслиВубличногоНЕтТоНАходим ЕГо
                if ( ПубличныйID >0) {
                    String  ПубличноеIDПолученныйИзСервлетаДляUUIDПромежуточный=String.valueOf(ПубличныйID);
                    String  СгенерированоДатаВТипеLongПромежуточный=String.valueOf(СгенерированоДатаВТипеLong);

                    // TODO: 02.08.2023  генерируем  UUID
                    String UUIDПромкжеточный = ПубличноеIDПолученныйИзСервлетаДляUUIDПромежуточный+СгенерированоДатаВТипеLongПромежуточный;
                    BigInteger UUIDПромежуточныйBig= new BigInteger(UUIDПромкжеточный);
                    UUID=        UUIDПромежуточныйBig.longValue();
                    Log.w(this.getClass().getName(), "   UUIDФинал " + UUID);
                }
                getbackasyncQueryPulicID.close();
            }

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  +
                    " ПубличныйID "+ПубличныйID);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(context.getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErroBack(context).recordnewerrorBack(e.toString(),
                    this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        // TODO: 06.09.2021  новый UUID
        return UUID;
    }

}
