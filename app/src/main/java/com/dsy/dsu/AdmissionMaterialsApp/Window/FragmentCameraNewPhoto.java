package com.dsy.dsu.AdmissionMaterialsApp.Window;


import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.util.Size;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.OrientationEventListener;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;
import androidx.camera.core.UseCase;
import androidx.camera.core.UseCaseGroup;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.dsy.dsu.AdmissionMaterialsApp.bl_admissonmaterils.PesssionCameta;
import com.dsy.dsu.BusinessLogicForApps.GreatUuidGenerations.GreatUuidGeneration;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.R;
import com.google.android.material.button.MaterialButton;
import com.google.common.util.concurrent.ListenableFuture;

import java.io.File;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Nullable;


public class FragmentCameraNewPhoto extends DialogFragment  implements CameraXInterface {

    private ImageButton imageButtonCameraback;
    private MaterialButton button_create_new_image;
    private  BisinessLogica bisinessLogica;
    private     ProcessCameraProvider processCameraProvider;
    private ImageCapture imageCapture;
    private     PreviewView preview_view;
    private  ExecutorService mExecutorService;

   private CameraXInterface cameraXInterface;
   private       Camera camera;
    public   Bitmap bitmapNewPhotoFromCameraX;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    public static final int ALL_PERSSION_CODE=1;
    public static final int CAMERA_PERSSION_CODE=2;


    private   String patchFileName="SousAvtoFile/AppMaterial/Photos";
    public FragmentCameraNewPhoto() {
        // Required empty public constructor
    }

    public static FragmentCameraNewPhoto newInstance() {
        Bundle args = new Bundle();
        FragmentCameraNewPhoto fragment = new FragmentCameraNewPhoto();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            fragmentManager = getActivity().getSupportFragmentManager() ;
            fragmentTransaction = fragmentManager.beginTransaction();
            bisinessLogica=new BisinessLogica();
            // TODO: 04.10.2023 разрешения для всего
            // TODO: 04.10.2023 разрешения для всего
            // TODO: 02.08.2023
            mExecutorService = Executors.newSingleThreadExecutor();


            // TODO: 21.08.2023 насйтироки Разые дизайна Фрагмента
            //setStyle(DialogFragment.STYLE_NORMAL,android.R.style.Theme_Material_Dialog_Alert);
           // setStyle(DialogFragment.STYLE_NORMAL,android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);//Theme_Dialog
            //  setStyle(DialogFragment.STYLE_NORMAL,android.R.style.Theme_DeviceDefault_Light_NoActionBar_TranslucentDecor);//Theme_Dialog
            // setStyle(DialogFragment.STYLE_NORMAL,android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);//Theme_Dialog
            //  setStyle(DialogFragment.STYLE_NORMAL,android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar);//Theme_Dialog
            // setStyle(DialogFragment.STYLE_NORMAL,android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth);//Theme_Dialog
             //setStyle(DialogFragment.STYLE_NO_INPUT,android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth);//Theme_Dialog
            //setStyle(DialogFragment.STYLE_NO_INPUT,android.R.style.Theme_DeviceDefault_Light_Panel);//Theme_Dialog
              //setStyle(DialogFragment.STYLE_NO_INPUT,android.R.style.Theme_DeviceDefault_InputMethod);//Theme_Dialog
            // setStyle(DialogFragment.STYLE_NO_INPUT,android.R.style.Theme_DeviceDefault_Light_Panel);//Theme_Dialog
            // setStyle(DialogFragment.STYLE_NO_INPUT,android.R.style.Theme_DeviceDefault_Panel);//Theme_Dialog
            // setStyle(DialogFragment.STYLE_NO_INPUT,android.R.style.Theme_DeviceDefault_Settings);//Theme_Dialog
            //setStyle(DialogFragment.STYLE_NO_TITLE,android.R.style.Theme_DeviceDefault_Wallpaper_NoTitleBar);//Theme_Dialog
            // setStyle(DialogFragment.STYLE_NO_FRAME | DialogFragment.STYLE_NO_INPUT,android.R.style.Theme_DeviceDefault_Wallpaper_NoTitleBar);//Theme_Dialog
            //  setStyle(  DialogFragment.STYLE_NO_FRAME | DialogFragment.STYLE_NO_TITLE,android.R.style.Theme_DeviceDefault_Wallpaper_NoTitleBar);//Theme_Dialog
            //    setStyle(   DialogFragment.STYLE_NO_TITLE,android.R.style.Theme_DeviceDefault_Wallpaper_NoTitleBar);//Theme_Dialog
            //  setStyle(   DialogFragment.STYLE_NO_INPUT ,android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);//Theme_Dialog
            // setStyle(   DialogFragment.STYLE_NO_FRAME ,android.R.style.Theme_DeviceDefault_Light_NoActionBar_Overscan);//Theme_Dialog
            // setStyle(   DialogFragment.STYLE_NO_FRAME ,android.R.style.Theme_DeviceDefault_Light_NoActionBar_Overscan);//Theme_Dialog
            //setCancelable(false);

            setStyle(DialogFragment.STYLE_NO_FRAME,android.R.style.Theme_Material_Light_DialogWhenLarge_NoActionBar);
            // setStyle(   DialogFragment.STYLE_NORMAL ,android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);//Theme_Dialog
            // setStyle(   DialogFragment.STYLE_NORMAL ,android.R.style.Theme_Material_Dialog_MinWidth);//Theme_Dialog
            // setStyle(   DialogFragment.STYLE_NO_TITLE ,android.R.style.Theme_Material_Light);//Theme_Dialog
            // setStyle(   DialogFragment.STYLE_NO_TITLE ,android.R.style.Theme_Material_Light_NoActionBar_Overscan);//Theme_Dialog
            //setStyle(   DialogFragment.STYLE_NORMAL,android.R.style.Theme_Material_Light_NoActionBar_Overscan);//Theme_Dialog

            // setStyle(   DialogFragment.STYLE_NORMAL ,android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);//Theme_Dialog  Без Часов
            //    setStyle(   DialogFragment.STYLE_NORMAL ,android.R.style.Theme_DeviceDefault_Light_NoActionBar_TranslucentDecor);//Theme_Dialog
            // setStyle(   DialogFragment.STYLE_NORMAL ,android.R.style.Theme_DeviceDefault_Light_DarkActionBar);//Theme_Dialog
            // setStyle(   DialogFragment.STYLE_NORMAL ,android.R.style.Theme_DeviceDefault_Light_DialogWhenLarge_NoActionBar);//Theme_Dialog   с часами сверху
            // setStyle(   DialogFragment.STYLE_NORMAL ,android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);//Theme_Dialog  Без Часов
            // setStyle(   DialogFragment.STYLE_NORMAL ,android.R.style.Theme_Black_NoTitleBar );
            //  setStyle(   DialogFragment.STYLE_NORMAL ,android.R.style.Theme_Material_Light_Dialog_Presentation);
            //setStyle(   DialogFragment.STYLE_NORMAL ,android.R.style.Theme_Material_Light_NoActionBar );



            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(getContext().getClass().getName(),
                "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getContext()).recordnewerror(e.toString(),
                this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewRoot=null;
        try{
            viewRoot= inflater.inflate(R.layout.fragment_camera, container, false);
            imageButtonCameraback=(ImageButton) viewRoot.findViewById(R.id.imageButtonCameraback);
            button_create_new_image=(MaterialButton) viewRoot.findViewById(R.id.button_create_new_image);
            bisinessLogica.  методСлушатели();
             preview_view =viewRoot. findViewById(R.id.preview_view);
            // TODO: 02.08.2023  TEST CODE
            preview_view.setScaleType(PreviewView.ScaleType.FIT_CENTER);

            bisinessLogica.new ClassCameraX().    методЗапускаКамерыX();

            // TODO: 20.07.2023
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
        } catch (Exception e) {
        e.printStackTrace();
        Log.e(getContext().getClass().getName(),
                "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getContext()).recordnewerror(e.toString(),
                this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return viewRoot;
    }


    @Override
    public void onResume() {
        super.onResume();
        try{
            // TODO: 02.08.2023
          //  getTargetFragment().onAttach(getContext());
          bisinessLogica. методНастройкиФИнаногоВидаКамеры();
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  + " bitmapNewPhotoFromCameraX " +bitmapNewPhotoFromCameraX );
        } catch (Exception e) {
        e.printStackTrace();
        Log.e(getContext().getClass().getName(),
                "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getContext()).recordnewerror(e.toString(),
                this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            if (context instanceof CameraXInterface) {
                cameraXInterface = (CameraXInterface) context;
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ " cameraXInterface " +cameraXInterface);
            }
            // TODO: 19.10.2022  слушатель после получение даннных в Курсом
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(getContext().getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).recordnewerror(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
    }


    @SuppressLint("RestrictedApi")
    @Override
    public void onStop() {
        super.onStop();
        try{
        processCameraProvider.shutdown();
        processCameraProvider.unbindAll();
        mExecutorService.shutdown();
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(getContext().getClass().getName(),
                "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getContext()).recordnewerror(e.toString(),
                this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        try{
        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  + " bitmapNewPhotoFromCameraX " +bitmapNewPhotoFromCameraX );
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(getContext().getClass().getName(),
                "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getContext()).recordnewerror(e.toString(),
                this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }

    }

    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );

    }

    @Override
    public Bitmap onGetFinishEditDialogNewPhotos( ) {
        try{
        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  + " bitmapNewPhotoFromCameraX " +bitmapNewPhotoFromCameraX );
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(getContext().getClass().getName(),
                "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getContext()).recordnewerror(e.toString(),
                this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return bitmapNewPhotoFromCameraX;
    }

    @Override
    public void onSEtFinishEditDialogNewPhotos(@NonNull Bitmap bitmap) {
        try{
            bitmap=  bitmapNewPhotoFromCameraX;
        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  + " bitmapNewPhotoFromCameraX " +bitmapNewPhotoFromCameraX );
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(getContext().getClass().getName(),
                "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getContext()).recordnewerror(e.toString(),
                this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
    }


    // TODO: 01.08.2023 Бизнес Логика Камера BisinessLogica           // TODO: 01.08.2023 Бизнес Логика Камера BisinessLogica           // TODO: 01.08.2023 Бизнес Логика Камера BisinessLogica
    public   class BisinessLogica {


        private void методСлушатели() {
            imageButtonCameraback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: 20.07.2023
try{
                    getDialog().dismiss();
                    getDialog().cancel();

                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                }
            });
            button_create_new_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: 20.07.2023
                    try {

                        PesssionCameta pesssionCameta = new PesssionCameta(getActivity());
                        Boolean EnableCamera = pesssionCameta.checkPesssionCameta();
                        Boolean EnableFile = pesssionCameta.checkPesssionFile();


                        if (   EnableCamera && EnableFile) {


                            Vibrator v2 = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
                            v2.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));

                            bisinessLogica.new ClassCameraX().takePicture();


                        }else{
                            Toast.makeText(getActivity(), "Нет разрешения на Камеру !!!", Toast.LENGTH_SHORT).show();
                            Log.d(this.getClass().getName(), "  onViewCreated  FragmentDetailingMaterials  binderДляПолучениеМатериалов  ");

                        }
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );

                      //  getActivity().b
                    } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                            + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                }
            });
        }

        private void методНастройкиФИнаногоВидаКамеры() {
            try{
                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                layoutParams.copyFrom(    getDialog().getWindow().getAttributes());
                layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                 layoutParams.height =WindowManager.LayoutParams.WRAP_CONTENT;
                //layoutParams.height =1350;
                layoutParams.gravity = Gravity.CENTER;
                getDialog().getWindow().setAttributes(layoutParams);
                getDialog().setCancelable(false);
                // TODO: 20.07.2023
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }



        // TODO: 02.08.2023  class launch CAMERAX
        class ClassCameraX {
            private void методЗапускаКамерыX() {
                ListenableFuture<ProcessCameraProvider> providerListenableFuture = ProcessCameraProvider.getInstance(getContext());

                providerListenableFuture.addListener(()->{
                    try {
                        while (!providerListenableFuture.isDone());
                        processCameraProvider = providerListenableFuture.get();
                        // TODO: 02.08.2023
                        bindPreview();
                        // TODO: 20.07.2023
                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(getContext().getClass().getName(),
                                "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(getContext()).recordnewerror(e.toString(),
                                this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }

                }, ContextCompat.getMainExecutor(getContext()));
            }

            @SuppressLint("RestrictedApi")
            private void bindPreview() {
                try{

// TODO: 22.09.2023 получение ратации ЭКРАНА
                    Integer rotation=   методGetRotasionsSreen();

                    imageCapture =
                            new ImageCapture.Builder()
                                    .setCameraSelector(CameraSelector.DEFAULT_BACK_CAMERA)
                                    .setIoExecutor(mExecutorService)
                                    .setCaptureMode(ImageCapture.CAPTURE_MODE_MAXIMIZE_QUALITY)
                                    .setTargetResolution(new Size(2448, 3092))
                                            .setTargetRotation(rotation)
                                    .setFlashMode(ImageCapture.FLASH_MODE_ON)
                                    .setJpegQuality(100)
                                    .setUseCaseEventCallback(new UseCase.EventCallback() {
                                        @Override
                                        public void onAttach(@NonNull CameraInfo cameraInfo) {
                                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"   + " preview_view " +preview_view );
                                        }

                                        @Override
                                        public void onDetach() {
                                            processCameraProvider.shutdown();
                                            processCameraProvider.unbindAll();
                                            mExecutorService.shutdown();
                                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"   + " preview_view " +preview_view );
                                        }
                                    }).build();


                    // TODO: 22.09.2023  END SETTTINGS
                    Preview preview = new Preview.Builder()
                            .setTargetResolution(new Size(2448, 3092))
                            .setTargetRotation(Surface.ROTATION_90)
                            .build();
                    preview.setSurfaceProvider(preview_view.getSurfaceProvider());

                    //
                    CameraSelector cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA;
                    UseCaseGroup useCaseGroup = new UseCaseGroup.Builder()
                            .addUseCase(preview)
                            .addUseCase(imageCapture)
                            .build();
                    camera=processCameraProvider.bindToLifecycle(getViewLifecycleOwner(), cameraSelector, useCaseGroup);
                    // bind
                    //  camera = processCameraProvider.bindToLifecycle(getActivity(), cameraSelector, preview, imageCapture);
                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(getContext().getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(),
                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
            }

            private int методGetRotasionsSreen() {
                final int[] rotation = new int[1];
                OrientationEventListener orientationEventListener = new OrientationEventListener(getContext()) {
                    @Override
                    public void onOrientationChanged(int orientation) {
                      try{
                        // Monitors orientation values to determine the target rotation value
                        if (orientation >= 45 && orientation < 135) {
                            rotation[0] = Surface.ROTATION_270;
                        } else if (orientation >= 135 && orientation < 225) {
                            rotation[0] = Surface.ROTATION_180;
                        } else if (orientation >= 225 && orientation < 315) {
                            rotation[0] = Surface.ROTATION_90;
                        } else {
                            rotation[0] = Surface.ROTATION_0;
                        }
                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(getContext().getClass().getName(),
                                "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(getContext()).recordnewerror(e.toString(),
                                this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                    }
                };

                return  rotation[0];
            }


            private void takePicture() {
                try{
                    File  patchFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), File.separator + patchFileName);
                    if (!patchFile.exists()) {
                        patchFile.mkdirs();
                        patchFile.setReadable(true);
                        patchFile.setWritable(true);
                    }
                    Long UUID = (Long)
                            new GreatUuidGeneration(getContext()).greatUuidGeneration();
                    String NameNewPhotosCamerax=UUID.toString() +".jpg";
            File fileNewPhotoFromCameraX = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                    +File.separator+patchFileName +File.separator+NameNewPhotosCamerax);
                ImageCapture.OutputFileOptions outputFileOptions =
                        new ImageCapture.OutputFileOptions.Builder(fileNewPhotoFromCameraX).build();


                    // TODO: 04.08.2023 первый варант записи сначала в файл # 2

                imageCapture.takePicture(outputFileOptions, mExecutorService,
                        new ImageCapture.OnImageSavedCallback() {
                            @SuppressLint("RestrictedApi")
                            @Override
                            public void onImageSaved(ImageCapture.OutputFileResults outputFileResults) {
                                System.out.println("SAVED");
                                try{
                                    Uri uri = outputFileResults.getSavedUri();
                                    if(uri != null) {

                                        ContextCompat.getMainExecutor(getActivity()).execute(()-> {
                                            try{

                                                  /*  Toast toast = Toast.makeText(getContext(),
                                                            " Успешно Создание  Фото  !!!", Toast.LENGTH_LONG);
                                                    toast.show();

                                                    Vibrator v2 = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
                                                    v2.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));*/


                                        ContentResolver cr =getActivity(). getContentResolver();
                                        InputStream inputStream = cr.openInputStream(uri);
                                         bitmapNewPhotoFromCameraX = BitmapFactory.decodeStream(inputStream);
                                                // TODO: 04.08.2023 clear
                                                inputStream.close();
                                  // TODO: 03.08.2023 Получену Фотографию отправляем а Activirty Куда Нужгно
                                        if (bitmapNewPhotoFromCameraX!=null) {
                                            cameraXInterface.onSEtFinishEditDialogNewPhotos(bitmapNewPhotoFromCameraX);

                                            Bundle bundleNewImage=new Bundle();
                                            bundleNewImage.putParcelable("bitmapPhoto",bitmapNewPhotoFromCameraX);
                                            bundleNewImage.putString("bitmapPhotoOnlyUUID",UUID.toString());



                                            fragmentManager.setFragmentResult("BackFromGeneratorImage",bundleNewImage);

                                            processCameraProvider.shutdown();
                                            processCameraProvider.unbindAll();

                                            mExecutorService.shutdown();

                                            getDialog().dismiss();
                                            getDialog().cancel();
                                        }else {
                                            Toast toastErrorCamera=       Toast.makeText(getContext(),
                                                    "   Фото нет создано !!!", Toast.LENGTH_LONG);
                                            toastErrorCamera.show();
                                            // TODO: 03.08.2023  image
                                        }


                                                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " uri " +uri );

                                        } catch (Exception e) {
                                            e.printStackTrace();
                                            Log.e(getContext().getClass().getName(),
                                                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                            new RecordNewErros(getContext()).recordnewerror(e.toString(),
                                                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                                        }

                                    });


                                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " uri " +uri + " bitmapNewPhotoFromCameraX " +bitmapNewPhotoFromCameraX );
                                    }

                                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " uri " +uri );
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Log.e(getContext().getClass().getName(),
                                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    new RecordNewErros(getContext()).recordnewerror(e.toString(),
                                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                                }


                            }
                            @SuppressLint("RestrictedApi")
                            @Override
                            public void onError(ImageCaptureException error) {
                                // TODO: 02.08.2023
                                    ContextCompat.getMainExecutor(getActivity()).execute(()->{
                                        try{
                                          /*  Toast toast=       Toast.makeText(getContext(),
                                                    "Фото не создано !!!", Toast.LENGTH_LONG);
                                            toast.show();*/
                                                    processCameraProvider.shutdown();
                                        processCameraProvider.unbindAll();

                                            mExecutorService.shutdown();
                                            // TODO: 03.08.2023
                                        getDialog().dismiss();
                                        getDialog().cancel();
                                            Log.d(this.getClass().getName(),"\n" + " error " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " error " +error );
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        Log.e(getContext().getClass().getName(),
                                                "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                        new RecordNewErros(getContext()).recordnewerror(e.toString(),
                                                this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    }
                                    });
                            }
                        }
                );
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  );
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            }
        }//todo END CLASS ClassCameraX


    }//TODO END BisinessLogica //TODO END BisinessLogica //TODO END BisinessLogica //TODO END BisinessLogica //TODO END BisinessLogica

}
