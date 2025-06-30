package com.dsy.dsu.AdmissionMaterialsApp.Service;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.R;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Predicate;

public class ServiceCameraTake extends Service {
    public LocalBinderCamera binder ;

    private Handler handlerbackgroupCamera;
    private Animation animationscroll;
    private CopyOnWriteArrayList<LinkedHashMap<String, Bitmap>> copyOnWriteArrayListSuccessAddImages ;

    private   CopyOnWriteArrayList<ImageView> AfrerImageInProsseing ;
    // TODO: 30.07.2023 new varivable


    @Override
    public void onCreate() {
        super.onCreate();
        try {
            binder = new LocalBinderCamera();

           copyOnWriteArrayListSuccessAddImages = new CopyOnWriteArrayList<>();

          AfrerImageInProsseing = new CopyOnWriteArrayList<>();
            // TODO: 28.07.2023
            animationscroll = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_scrolls);
            // TODO: 28.07.2023
            методHandlerCamera();
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
                    this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


    @Override
    public void onDestroy() {
try{
    super.onDestroy();

    if (copyOnWriteArrayListSuccessAddImages!=null) {
        copyOnWriteArrayListSuccessAddImages.clear();
    }
    if (AfrerImageInProsseing!=null) {
        AfrerImageInProsseing.clear();
    }

        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
                this.getClass().getName(),
                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }

    public class LocalBinderCamera extends Binder {
        public ServiceCameraTake getService() {
            Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
            return ServiceCameraTake.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        return binder;
    }


    // TODO: 29.07.2023  main bound metod

    public CopyOnWriteArrayList<LinkedHashMap<String, Bitmap>> метоСлужбыTakePhotos(@NonNull Intent intent,
                                                                                     @NonNull CopyOnWriteArrayList<ImageView> copyOnWriteArrayListGetImages,
                                                                                     @NonNull Activity activityNewImage) {
        try {
            //////////////////////TODO SERVICE
            // TODO: 29.07.2023 Up Photos
            if (intent.getAction().equalsIgnoreCase("ServiceCameraTake.UpImage")) {
                // TODO: 28.07.2023 вариан первый #1 UP Photo
                copyOnWriteArrayListSuccessAddImages = new SubClassCompleteNewImageUpAndCreate().методОбраобткиUPCompleteImages(intent,copyOnWriteArrayListGetImages);

                Log.d(getApplicationContext().getClass().getName(), "\n"
                        + " время: " + new Date() + "\n+" +
                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                        " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        "  copyOnWriteArrayListSuccessAddImages " + copyOnWriteArrayListSuccessAddImages + " intent.getAction() " + intent.getAction());
            } else {
                if (intent.getAction().equalsIgnoreCase("ServiceCameraTake.NewFromCameraImage")) {
                    // TODO: 28.07.2023 вариан первый #1 NEw Image Photo
                    copyOnWriteArrayListSuccessAddImages = new SubClassCompleteNewImageUpAndCreate().методОбрабокаNewCreateImageComplete(intent,copyOnWriteArrayListGetImages);

                    Log.d(getApplicationContext().getClass().getName(), "\n"
                            + " время: " + new Date() + "\n+" +
                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            "  copyOnWriteArrayListSuccessAddImages " + copyOnWriteArrayListSuccessAddImages + " intent.getAction() " + intent.getAction());
                }


            }

            // TODO: 22.09.2023  clear
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
                    this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return copyOnWriteArrayListSuccessAddImages;
    }


    public void методHandlerCamera() {
        try {
            handlerbackgroupCamera = new Handler(new Handler.Callback() {
                @Override
                public boolean handleMessage(@NonNull Message msg) {
                    Log.d(getApplicationContext().getClass().getName(), "\n"
                            + " время: " + new Date() + "\n+" +
                            " Класс в процессе... " + this.getClass().getName() + "\n" +
                            " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                    return true;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
                    this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


    // TODO: 28.07.2023 КЛАССЫ С БИЗЕНС ЛОГИКОЙ  КАМЕРА  // TODO: 28.07.2023 КЛАССЫ С БИЗЕНС ЛОГИКОЙ  КАМЕРА  // TODO: 28.07.2023 КЛАССЫ С БИЗЕНС ЛОГИКОЙ  КАМЕРА  // TODO: 28.07.2023 КЛАССЫ С БИЗЕНС ЛОГИКОЙ  КАМЕРА
    class SubClassCompleteNewImageUpAndCreate {
        CopyOnWriteArrayList<LinkedHashMap<String, Bitmap>> методОбраобткиUPCompleteImages(@Nullable Intent intentUpPhotos,
                                                                                         @NonNull CopyOnWriteArrayList<ImageView> copyOnWriteArrayListGetImages) {
            try {
                // TODO: 24.07.2023
                if (intentUpPhotos != null) {
                    Uri selectedImage = intentUpPhotos.getData();
                    // TODO: 09.10.2023  название Image
                  String ВыбраноеИмяImage=  metodGetNameSelect(selectedImage);

                        InputStream stream = getApplicationContext().getContentResolver().openInputStream(selectedImage);
                        BufferedInputStream bufferedInputStreamUpLoadImage = new BufferedInputStream(stream,2048);//4096
                        Bitmap   bitmapUpImage = BitmapFactory.decodeStream(bufferedInputStreamUpLoadImage);


                        // TODO: 08.08.2023
                        copyOnWriteArrayListSuccessAddImages = методЗаполянемImageViewNewImage(bitmapUpImage ,ВыбраноеИмяImage,copyOnWriteArrayListGetImages);

                    // TODO: 20.07.2023
                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()
                            + "\n" + "  bitmapUpImage " + bitmapUpImage + " copyOnWriteArrayListSuccessAddImages.size() " + copyOnWriteArrayListSuccessAddImages.size()  +  "bitmapPhotoNamegetUUID ");
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getApplicationContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return copyOnWriteArrayListSuccessAddImages;
        }

        @SuppressLint("SuspiciousIndentation")
        private String metodGetNameSelect(Uri selectedImage) {
            String НазваниеImageSelect=new String();
            try{
            final     String data = selectedImage.getPath();
                int lastNamebuffer=lastNamebuffer = data.lastIndexOf("Photos/");
                if (lastNamebuffer == -1) {
                    lastNamebuffer=lastNamebuffer = data.lastIndexOf("Screenshots/");
                }
                if (lastNamebuffer>=0) {
                    НазваниеImageSelect=    data.substring(lastNamebuffer,data.length());
                    int lastZnakbuffer=     НазваниеImageSelect.lastIndexOf("/")+1;
                    НазваниеImageSelect=    НазваниеImageSelect.substring(lastZnakbuffer,НазваниеImageSelect.length());

                    int lastPointbuffer=    НазваниеImageSelect.length()-4;
                    НазваниеImageSelect=    НазваниеImageSelect.substring(0 ,lastPointbuffer);
                }

                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()
                    + "\n"  +data  + " НазваниеImageSelect " +НазваниеImageSelect);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(getApplicationContext().getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());

        }
            return  НазваниеImageSelect;
        }

        // TODO: 30.07.2023 NEW IMAGE FROM PHOTO

        CopyOnWriteArrayList<LinkedHashMap<String, Bitmap>> методОбрабокаNewCreateImageComplete(@Nullable Intent intentNewImage,
                                                                                              @NonNull CopyOnWriteArrayList<ImageView> copyOnWriteArrayListGetImages) {
            try {
                // TODO: 24.07.2023
                if (intentNewImage != null) {
                    Bundle bundleNewCompleetImages = intentNewImage.getExtras();
                    Bitmap bitmapNewCompleteImage  = bundleNewCompleetImages.getParcelable("bitmapNewCompleteImage") ;
                    String NameNewImage  = bundleNewCompleetImages.getString("bitmapPhotoOnlyUUID") ;


                    // TODO: 24.07.2023
                    copyOnWriteArrayListSuccessAddImages = методЗаполянемImageViewNewImage(bitmapNewCompleteImage,NameNewImage,copyOnWriteArrayListGetImages);
                    // TODO: 20.07.2023
                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber()
                            + "\n" + "  bitmapNewCompleteImage " + bitmapNewCompleteImage + " copyOnWriteArrayListSuccessAddImages.size() " + copyOnWriteArrayListSuccessAddImages.size()
                             +  " NameNewImage " +NameNewImage   + " bitmapNewCompleteImage " +bitmapNewCompleteImage);
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getApplicationContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return copyOnWriteArrayListSuccessAddImages;
        }



        // TODO: 24.07.2023  заполяем дааныых
        private CopyOnWriteArrayList<LinkedHashMap<String, Bitmap>> методЗаполянемImageViewNewImage(@NonNull Bitmap bitmapUpImage
                ,@NonNull  String ВыбраноеИмяImage,
                                                                                                  @NonNull CopyOnWriteArrayList<ImageView> copyOnWriteArrayListGetImages) {
            try {
                final boolean[] ФлагЧтоУжеОднаВставкаУжеБыла = {false};
                Flowable.fromIterable(copyOnWriteArrayListGetImages)
                        .onBackpressureBuffer()
                        .takeWhile(new Predicate<ImageView>() {
                            @Override
                            public boolean test(ImageView imageView) throws Throwable {
                                if (ФлагЧтоУжеОднаВставкаУжеБыла[0] == false) {
                                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                                            "ФлагЧтоУжеОднаВставкаУжеБыла[0] " + ФлагЧтоУжеОднаВставкаУжеБыла[0]);
                                    return true;
                                } else {
                                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +
                                            "ФлагЧтоУжеОднаВставкаУжеБыла[0] " + ФлагЧтоУжеОднаВставкаУжеБыла[0]);
                                    return false;
                                }
                            }
                        })
                        .filter(image -> image.getDrawingCache() == null)
                        .doOnNext(new Consumer<ImageView>() {
                            @Override
                            public void accept(ImageView imageView) throws Throwable {
                                try {
                                    // TODO: 25.07.2023  добавление новго Image
                                    Long УжеЕслиТАкойIDImage =
                                            AfrerImageInProsseing.stream()
                                                    .filter(s -> s.getId()==imageView.getId()).collect(Collectors.counting());

                                    if (УжеЕслиТАкойIDImage == 0) {
                                        // TODO: 24.07.2023  set Image
                                        методВставкиImageGenerator(imageView, bitmapUpImage,    AfrerImageInProsseing);
                                        // TODO: 25.07.2023 ставим флаг что вставка одна успешно стработал
                                        ФлагЧтоУжеОднаВставкаУжеБыла[0] = true;


                                        методЗапоелнияУжеДобавленыхImage(ВыбраноеИмяImage, bitmapUpImage);


                                    }

                                    Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                            + " bitmapUpImage " + bitmapUpImage + " imageView.getId() " + imageView.getId() + " УжеЕслиТАкойIDImage " + УжеЕслиТАкойIDImage+
                                            " AfrerImageInProsseing " +AfrerImageInProsseing);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
                                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    Log.e(getApplicationContext().getClass().getName(), " Ошибка СЛУЖБА Service_ДляЗапускаодноразовойСинхронизации   ");
                                }
                            }
                        })
                        .doOnError(new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Throwable {
                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                        })
                        .onErrorComplete(new Predicate<Throwable>() {
                            @Override
                            public boolean test(Throwable throwable) throws Throwable {
                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                return false;
                            }
                        })
                        .doOnComplete(new Action() {
                            @Override
                            public void run() throws Throwable {
                                // TODO: 24.07.2023
                                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }
                        })
                        .blockingSubscribe();


                Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getApplicationContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return copyOnWriteArrayListSuccessAddImages;
        }


        private void методВставкиImageGenerator(@NonNull ImageView imageView, @NonNull Bitmap bitmapUpImage,@NonNull   CopyOnWriteArrayList<ImageView> AfrerImageInProsseing) {
            try {
                imageView.setImageBitmap(bitmapUpImage);
                imageView.startAnimation(animationscroll);
                imageView.refreshDrawableState();
                imageView.requestLayout();
                AfrerImageInProsseing.add(imageView);
                Log.d(getApplicationContext().getClass().getName(), "\n" + " imageView.getId() " + imageView.getId());
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
                        this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }


        private void методЗапоелнияУжеДобавленыхImage(@NonNull String ВыбраноеИмяImage, @NonNull Bitmap bitmapUpImage) {
            try {
                LinkedHashMap<String, Bitmap> linkedHashMapCompeleImage = new LinkedHashMap<>();
                linkedHashMapCompeleImage.put(ВыбраноеИмяImage, bitmapUpImage);
                copyOnWriteArrayListSuccessAddImages.add(linkedHashMapCompeleImage);
                Log.d(getApplicationContext().getClass().getName(), "\n" + " copyOnWriteArrayListSuccessAddImages "
                        + copyOnWriteArrayListSuccessAddImages);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
                        this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

    } // TODO: 24.07.2023  end SubClassCompleteNewImageUpAndCreate
}

    
    
    
    
