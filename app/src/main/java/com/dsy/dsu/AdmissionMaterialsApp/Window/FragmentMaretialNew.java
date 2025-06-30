package com.dsy.dsu.AdmissionMaterialsApp.Window;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.hardware.camera2.CameraDevice;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.SearchView;
import androidx.cursoradapter.widget.CursorAdapter;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.FilterQueryProvider;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.dsy.dsu.BusinessLogicForApps.GetPublicID.GetttingPublicID;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.AdmissionMaterialsApp.Service.ServiceCameraTake;
import com.dsy.dsu.BusinessLogicForApps.Services.ServiceForAdminissionMaterial;
import com.dsy.dsu.R;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;


public class FragmentMaretialNew extends Fragment implements CameraXInterface{
    private RecyclerView recyclerView;
    private BottomNavigationView bottomNavigationView;
    @SuppressLint("RestrictedApi")
    private BottomNavigationItemView bottomNavigationItemViewвыход;
    @SuppressLint("RestrictedApi")
    private BottomNavigationItemView bottomNavigationItemView2создать;
    @SuppressLint("RestrictedApi")
    private BottomNavigationItemView bottomNavigationItemView3обновить;
    private ProgressBar progressBarСозданиеМатерила;
    private Handler handler;
    private MyRecycleViewAdapter myRecycleViewAdapter;
    private MyViewHolder myViewHolder;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Animation animation;
    private Animation animationscroll;
    private  Integer ПубличныйIDДляФрагмента;


    private Cursor CursorДляОдногоМатериалаБышВесов;
    private Cursor CursorДляАвтомобиля;
    private Cursor CursorДляКонтрагента;
    private    Cursor CursorДляГруппаМатериалов;
      private   Cursor CursorДляЦФО;

    private  Object ВытаскиваемIDМатериаловИзСправочника;
    private  View view=null;
    private SharedPreferences preferencesМатериалы;

    private  ScrollView scrollViewНовыйматериал;
    private  AsyncTaskLoader<Cursor> asyncTaskLoaderForNewMaterial;

    // TODO: 15.12.2022 получение материалов
    private  ServiceForAdminissionMaterial.LocalBinderДляПолучениеМатериалов binderДляПолучениеМатериалов;

    private     DatePickerDialog ДатаДляКалендаря;

    private  AlertDialog alertDialogCreateImage;
    private   SubClassCreateNewImageForMateril subClassCreateNewImageForMateril;

    private CopyOnWriteArrayList<ImageView> copyOnWriteArrayListGetImages;

    private   CopyOnWriteArrayList<LinkedHashMap<String,Bitmap>> copyOnWriteArrayListSuccessAddImages;
    private        ActivityResultLauncher<Intent> someActivityResultLauncherUpImage;

    private  ServiceCameraTake.LocalBinderCamera localBinderCamera;
    private  Uri cam_uri;

    private CameraXInterface cameraXInterface;

       private   String patchFileName="SousAvtoFile/Photos";

       private  ServiceConnection serviceConnectionMaterialNew;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            fragmentManager = getActivity().getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            preferencesМатериалы = getContext().getSharedPreferences("sharedPreferencesХранилище", Context.MODE_MULTI_PROCESS);
            Bundle data=         getArguments();
            binderДляПолучениеМатериалов=  (ServiceForAdminissionMaterial.LocalBinderДляПолучениеМатериалов) data.getBinder("binder");

            МетодHandlerCallBack();
            subClassCreateNewImageForMateril=new SubClassCreateNewImageForMateril();

            subClassCreateNewImageForMateril.  методGetDataForNewFragment();

            subClassCreateNewImageForMateril.    методБиндингСлужбыTakePhoto();


            методCAllBackWihtGenerratorNewImage();

            // TODO: 03.11.2022  ПОСЛЕ ПОЛУЧЕННЫХ ДАННЫХ
            Log.d(getContext().getClass().getName(), "\n" + " CursorДляЦФО "
                    + CursorДляЦФО + " CursorДляОдногоМатериалаБышВесов " + CursorДляОдногоМатериалаБышВесов +
                    " CursorДляАвтомобиля " + CursorДляАвтомобиля + " CursorДляКонтрагента " +CursorДляКонтрагента
                    + " CursorДляГруппаМатериалов " +CursorДляГруппаМатериалов );

            Log.d(this.getClass().getName(), "  onCreate  FragmentCreateAdmissionmaterialbinder    "+binderДляПолучениеМатериалов);
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

    private void методCAllBackWihtGenerratorNewImage() {

        fragmentManager.setFragmentResultListener("BackFromGeneratorImage", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                // TODO: 03.11.2022  ПОСЛЕ ПОЛУЧЕННЫХ ДАННЫХ
                try{
                Bitmap bitmapPhoto = result.getParcelable("bitmapPhoto");

                String bitmapPhotoOnlyUUID = result.getString("bitmapPhotoOnlyUUID");

                методCallsBackFromCameraX(bitmapPhoto,bitmapPhotoOnlyUUID);

                Log.d(getContext().getClass().getName(), "\n" + " CursorДляЦФО "
                        + CursorДляЦФО + " CursorДляОдногоМатериалаБышВесов " + CursorДляОдногоМатериалаБышВесов +
                        " CursorДляАвтомобиля " + CursorДляАвтомобиля + " CursorДляКонтрагента " +CursorДляКонтрагента
                        + " bitmapPhoto " +bitmapPhoto   + " bitmapPhotoOnlyUUID " +bitmapPhotoOnlyUUID );
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
        });
        Log.d(this.getClass().getName(), "  onCreate  FragmentCreateAdmissionmaterialbinder    "+binderДляПолучениеМатериалов);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        try{
            view= inflater.inflate(R.layout.fragment_admission_new_material, container, false);
            Log.d(this.getClass().getName(), "  onCreateView  view   "+view);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(getContext().getClass().getName(),
                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).recordnewerror(e.toString(),
                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return view;
    }

    @SuppressLint({"RestrictedApi", "WrongConstant"})
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try{
            recyclerView = view.findViewById(R.id.RecyclerView);
            recyclerView.setVisibility(View.VISIBLE);
            bottomNavigationView = view.findViewById(R.id.BottomNavigationView);
            bottomNavigationView.setLabelVisibilityMode(NavigationBarView.LABEL_VISIBILITY_UNLABELED);
            bottomNavigationItemViewвыход = bottomNavigationView.findViewById(R.id.id_lback);
            bottomNavigationItemViewвыход.setShifting(false);
            bottomNavigationItemViewвыход.setIconSize(70);
            bottomNavigationItemViewвыход.setItemPosition(1);
            bottomNavigationItemViewвыход.setTitle("11");
            bottomNavigationItemView2создать = bottomNavigationView.findViewById(R.id.id_create);
            bottomNavigationItemView2создать.setVisibility(View.GONE);
            bottomNavigationItemView3обновить = bottomNavigationView.findViewById(R.id.id_async);
            bottomNavigationItemView3обновить.setVisibility(View.GONE);
            progressBarСозданиеМатерила =  view.findViewById(R.id.ProgressBar);
            scrollViewНовыйматериал=  (ScrollView) view.findViewById(R.id.scrollview_new_materials);
            progressBarСозданиеМатерила.setVisibility(View.VISIBLE);
            animation = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_row_newscanner1);
            animationscroll = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_scrolls);
            // TODO: 25.07.2023
            copyOnWriteArrayListGetImages=new CopyOnWriteArrayList<>();
            // TODO: Создаем и инициализцурием RecureView
            МетодИнициализацииRecycreView();
            МетодЗаполенияRecycleViewДляЗадач();//todo заполения recycreview
            МетодКпопкиЗначков();
            МетоКликаПоКнопкеBack();

            // TODO: 27.07.2023 методы по слушателем UP Back Image
            методCallsBackUpImageActivityResult();

            // TODO: 17.04.2023
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
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
    public void onStart() {
        super.onStart();
        try {
            if (  CursorДляЦФО!=null) {
                myRecycleViewAdapter.cursorДляЦФО=CursorДляЦФО;
                myRecycleViewAdapter.notifyDataSetChanged();

                RecyclerView.Adapter recyclerViewОбновление=         recyclerView.getAdapter();
                recyclerViewОбновление.notifyDataSetChanged();
                recyclerView.swapAdapter(recyclerViewОбновление,true);
// TODO: 26.07.2023  тест код
            }
            // TODO: 17.04.2023
            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        try{

            localBinderCamera.getService().onDestroy();

        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ " cameraXInterface " +cameraXInterface);
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

    // TODO: 26.07.2023 CallsBAckUpImageNew
    void методCallsBackUpImageActivityResult(){
        // You can do the assignment inside onAttach or onCreate, i.e, before the activity is displayed
          someActivityResultLauncherUpImage = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        try{
                            if (result.getResultCode() == Activity.RESULT_OK && result.getData()!=null) {
                            // There are no request codes
                            Intent dataUpImage = result.getData();
                                dataUpImage.setAction(       "ServiceCameraTake.UpImage");

                                // TODO: 24.07.2023  UP file Image
                                copyOnWriteArrayListSuccessAddImages=
                                        localBinderCamera.getService()
                                                .метоСлужбыTakePhotos(dataUpImage,copyOnWriteArrayListGetImages,getActivity() );

                         if(copyOnWriteArrayListSuccessAddImages.size()>0){
                                subClassCreateNewImageForMateril.    методЗакрытиеNewCreateIMAGE(alertDialogCreateImage);
                         }



                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                    + " dataUpImage " +dataUpImage      + " copyOnWriteArrayListSuccessAddImages " +copyOnWriteArrayListSuccessAddImages);
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
                });
    }

// TODO: 30.07.2023  CREATE NEW IMAGE
void методCallsBackFromCameraX(@NonNull  Bitmap bitmapNewCompleteImage, @NonNull   String bitmapPhotoOnlyUUID){
    try{
            if (bitmapNewCompleteImage != null){
                // TODO: 31.07.2023  Создание Новой Фотографии
                Intent dataCreateNewImage = new Intent("ServiceCameraTake.NewFromCameraImage");
                Bundle bundleNewPhotoFromCamera=new Bundle();
                bundleNewPhotoFromCamera.putParcelable("bitmapNewCompleteImage",bitmapNewCompleteImage);
                bundleNewPhotoFromCamera.putString("bitmapPhotoOnlyUUID",bitmapPhotoOnlyUUID.toString());
                dataCreateNewImage.putExtras(bundleNewPhotoFromCamera);
                // TODO: 24.07.2023  UP file Image
                copyOnWriteArrayListSuccessAddImages=
                        localBinderCamera.getService()
                                .метоСлужбыTakePhotos(dataCreateNewImage,copyOnWriteArrayListGetImages,getActivity() );

                if(copyOnWriteArrayListSuccessAddImages.size()>0){
                    subClassCreateNewImageForMateril.    методЗакрытиеNewCreateIMAGE(alertDialogCreateImage);

                }

                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" +copyOnWriteArrayListSuccessAddImages+ " cam_uri  " + cam_uri+
                        " bitmapNewCompleteImage " +bitmapNewCompleteImage);
            }
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





    private Cursor МетодПолучениеДанныхДляЦФО(Intent intentДляПолучениеСправочкинов) {
        try{
        Intent intent=new Intent();
        intent.putExtras(new Bundle());
            CursorДляЦФО=     МетодДляПолучениеДанныхИзСлужбыДляСозданияНовогоМатериала("cfo",intent ,"ПолучениеМатериалоСозданиеНового");
        Log.d(this.getClass().getName(), " CursorДляЦФО " + CursorДляЦФО);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(getContext().getClass().getName(),
                "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getContext()).recordnewerror(e.toString(),
                this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return CursorДляЦФО;
    }

    private Cursor МетодПолучениеДляГруппыМатериалов(Intent intentДляПолучениеСправочкинов) {
        try{
        Intent intent=new Intent();
        intent.putExtras(new Bundle());
        CursorДляГруппаМатериалов=      МетодДляПолучениеДанныхИзСлужбыДляСозданияНовогоМатериала("type_materials",intent, "ПолучениеМатериалоСозданиеНового");
        Log.d(this.getClass().getName(), " CursorДляГруппаМатериалов " + CursorДляГруппаМатериалов);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(getContext().getClass().getName(),
                "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getContext()).recordnewerror(e.toString(),
                this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return CursorДляГруппаМатериалов;
    }

    private Cursor МетоПолучениеДанныхДляОдногоМатериала(Intent intentДляПолучениеСправочкинов, @NonNull Long ФильтрВесовых) {
        CursorДляОдногоМатериалаБышВесов=null;
        try {
        Bundle bundle=new Bundle();
        bundle.putString("ТаблицаОбработкиСпинера","материал");
        bundle.putString("ФильтрКолонок","nomen_vesov");
        bundle.putLong("ФильтрДляПоискаДляОдногоМатериалаВесовые",ФильтрВесовых);
        intentДляПолучениеСправочкинов.putExtras(bundle);
        CursorДляОдногоМатериалаБышВесов=          МетодДляПолучениеДанныхИзСлужбыДляСозданияНовогоМатериала("nomen_vesov",intentДляПолучениеСправочкинов,
                "ПолучениеМатериалоСозданиеНового");
        Log.d(this.getClass().getName(), " CursorДляОдногоМатериалаБышВесов " + CursorДляОдногоМатериалаБышВесов);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(getContext().getClass().getName(),
                "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getContext()).recordnewerror(e.toString(),
                this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return  CursorДляОдногоМатериалаБышВесов;
    }

    // TODO: 26.12.2022  автомобили
    private Cursor МетоПолучениеДанныхДляАвтомобилей(@NonNull Intent intentДляПолучениеСправочкинов,
                                                   @NonNull String ФильтрВесовых) {
        try{
        Bundle bundle=new Bundle();
        bundle.getString("ФильтрДляПоискаАвтомобили",ФильтрВесовых);
        intentДляПолучениеСправочкинов.putExtras(bundle);
        CursorДляАвтомобиля= МетодДляПолучениеДанныхИзСлужбыДляСозданияНовогоМатериала("track",intentДляПолучениеСправочкинов,
                "ПолучениеАвтомобильДляСозданиеНовгоМатерила");
        Log.d(this.getClass().getName(), " CursorДляАвтомобиля " + CursorДляАвтомобиля);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(getContext().getClass().getName(),
                "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getContext()).recordnewerror(e.toString(),
                this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return  CursorДляАвтомобиля;
    }
    // TODO: 26.12.2022  Контрагент
    private Cursor МетоПолучениеДанныхДляКонтрагент(Intent intentДляПолучениеСправочкинов,
                                                  @NonNull String ФильтрВесовых) {
        try{
        Bundle bundle=new Bundle();
        bundle.putString("ТаблицаОбработкиСпинера","контрагенты");
        bundle.putString("ФильтрКолонок","company");
        bundle.getString("ФильтрДляПоискаКонтрагенты",ФильтрВесовых);
        intentДляПолучениеСправочкинов.putExtras(bundle);
        CursorДляКонтрагента= МетодДляПолучениеДанныхИзСлужбыДляСозданияНовогоМатериала("company",intentДляПолучениеСправочкинов,
                "ПолучениеКонтрагентовДляСозданиеНовгоМатерила");
        Log.d(this.getClass().getName(), " \n" + "CursorДляКонтрагента " + CursorДляКонтрагента);
    } catch (Exception e) {
        e.printStackTrace();
        Log.e(getContext().getClass().getName(),
                "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
        new RecordNewErros(getContext()).recordnewerror(e.toString(),
                this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                Thread.currentThread().getStackTrace()[2].getLineNumber());
    }
        return  CursorДляКонтрагента;
    }


    private void МетодЗапускаАнимацииКнопок(View v) {
        v.animate().rotationX(-40l);
        handler .postDelayed(()->{
            v.animate().rotationX(0);
        },300);
    }
    private void МетоКликаПоКнопкеBack(){
        try{
            bottomNavigationItemViewвыход.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        МетодЗапускаАнимацииКнопок(v);//todo только анимауия
                        handler.postDelayed(()->{
                            методBackToFragmentAdmissionMaterilas(v);
                            Log.d(this.getClass().getName(), " bottomNavigationItemViewвыход " + bottomNavigationItemViewвыход);
                        },50);
                        Log.d(this.getClass().getName(), "  v  " + v);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(getContext().getClass().getName(),
                                "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(getContext()).recordnewerror(e.toString(),
                                this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }}
            });
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

    private void методBackToFragmentAdmissionMaterilas(@NonNull View v) {
        try{
            // TODO: 09.11.2022  переходим на детализацию Полученихы Материалов
                МетодЗапускаАнимацииКнопок(v);//todo только анимауия
                Fragment      fragmentПолученыеМатериалов = new FragmentAdmissionMaterials();
                Bundle bundleСозданиеНовогоМатериала=new Bundle();
                bundleСозданиеНовогоМатериала.putBinder("binder",binderДляПолучениеМатериалов);
            fragmentПолученыеМатериалов.setArguments(bundleСозданиеНовогоМатериала);
            String fragmentNewImageNameaddToBackStack=   fragmentПолученыеМатериалов.getClass().getName();
            fragmentTransaction.addToBackStack(fragmentNewImageNameaddToBackStack);


            Fragment    FragmentУжеЕСтьИлиНЕт=     fragmentManager.findFragmentByTag(fragmentNewImageNameaddToBackStack);
            if (FragmentУжеЕСтьИлиНЕт==null) {
                fragmentTransaction.replace(R.id.activity_admissionmaterias_mainface, fragmentПолученыеМатериалов).commit();//.layout.activity_for_fragemtb_history_tasks
                fragmentTransaction.show(fragmentПолученыеМатериалов);
            }

            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " binderДляПолучениеМатериалов " +binderДляПолучениеМатериалов );
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


    void МетодHandlerCallBack() {
        handler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull android.os.Message msg) {
                try {
                    Log.d(this.getClass().getName(), " msg  " + msg);
                    Bundle bundle = msg.getData();
                    Log.d(this.getClass().getName(), " bundle  " + bundle);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(getContext().getClass().getName(),
                            "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                    new RecordNewErros(getContext()).recordnewerror(e.toString(),
                            this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                            Thread.currentThread().getStackTrace()[2].getLineNumber());
                }
                return true;
            }
        });
    }
    private void МетодИнициализацииRecycreView() {
        try{
            Log.d(this.getClass().getName(), " recyclerView  "+recyclerView);
            recyclerView.setVisibility(View.VISIBLE);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(linearLayoutManager);
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

    void МетодЗаполенияRecycleViewДляЗадач() {
        try {
            myRecycleViewAdapter = new MyRecycleViewAdapter(CursorДляЦФО);
            myRecycleViewAdapter.notifyDataSetChanged();
            recyclerView.setAdapter(myRecycleViewAdapter);
            Log.d(this.getClass().getName(), "CursorДляЦФО   " + CursorДляЦФО);
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

    // TODO: 02.08.2022
    protected   Cursor МетодДляПолучениеДанныхИзСлужбыДляСозданияНовогоМатериала(@NonNull String  ФлагКакаяТаблицаОбработки,
                                                                                 @NonNull Intent intent,
                                                                                 @NonNull String ФлагКакаяРаботаНужнаДляВыполнения){
        Cursor cursor = null;
        try{
            ПубличныйIDДляФрагмента     =    new GetttingPublicID().getttingPublicID(getContext());

            Log.d(getContext().getClass().getName(), "\n"
                    + " ПубличныйIDДляФрагмента: " + ПубличныйIDДляФрагмента);
            Bundle bundleДляПЕредачи=intent.getExtras();
            bundleДляПЕредачи.putString("Таблица",ФлагКакаяТаблицаОбработки);
            Intent intentПолучениеМатериалов = new Intent(getContext(), ServiceForAdminissionMaterial.class);
            intentПолучениеМатериалов.setAction(ФлагКакаяРаботаНужнаДляВыполнения);
            intentПолучениеМатериалов.putExtras(bundleДляПЕредачи);
                cursor = (Cursor) binderДляПолучениеМатериалов.getService().
                        МетодCлужбыПолучениеМатериалов(getContext(), intentПолучениеМатериалов);
                Log.d(this.getClass().getName(), "   cursor " + cursor);
                if (cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    // TODO: 15.10.2022
                    Log.d(this.getClass().getName(), "   cursor " + cursor);

            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
                    Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
        return  cursor;
    }

    @Override
    public Bitmap onGetFinishEditDialogNewPhotos() {
        return null;
    }

    @Override
    public void onSEtFinishEditDialogNewPhotos(@NonNull Bitmap bitmap) {

    }


    // TODO: 28.02.2022 начало  MyViewHolderДляЧата
    protected class MyViewHolder extends RecyclerView.ViewHolder {
        private MaterialTextView textViewcfo,marerialtextgroupmaterial
                ,materialtext_onematerial_ves,
                valueavtomobil,valuekontragent;
        private TextInputEditText textipputcountassinmaterail,textipputmaretialttn;
        private MaterialTextView textviewmaterialttn ,textviewmaterialttndata;
        private ImageView im1,im2, im3,im4 , im5,im6, im7,im8 ,im9,im10;
        private  TextView textipputmaretialttdata;

        private MaterialButton bottomcreateassionmaterial;
        private AlertDialog alertDialog;
        private    ListView  listViewДляЦФО =null;
        private  Cursor cursorДляВсехМатериалов;

        private ImageView  bottom_create_image;

        // TODO: 28.10.2022
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            try {
                if (CursorДляЦФО!=null) {
                    if(     CursorДляЦФО.getCount()>0  ) {
                        // TODO: 26.07.2023  заполяем ИНициализируем Данными
                        МетодИнициализацииНовогоМатериалаCardView(itemView);
                        Log.d(this.getClass().getName(), "   itemView   " + itemView);
                    }
                }
                Log.d(this.getClass().getName(), "   itemView   " + itemView  + " CursorДляЦФО " +CursorДляЦФО);
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
        private void МетодИнициализацииНовогоМатериалаCardView(@NonNull View itemView) {
            try {
                Log.d(this.getClass().getName(), " отработоатл new SubClassBuccessLogin_ГлавныйКлассБизнесЛогикиФрагмент1 itemView   " + itemView);
                textViewcfo=itemView.findViewById(R.id.textViewcfo);
               TextView   textviewnamecfo=itemView.findViewById(R.id.textviewnamecfo);

                marerialtextgroupmaterial=itemView.findViewById(R.id.marerialtextgroupmaterial);
                materialtext_onematerial_ves =itemView.findViewById(R.id.materialtext_onematerial_ves);
                textipputcountassinmaterail = itemView.findViewById(R.id.textipputcountassinmaterail);
                valueavtomobil = itemView.findViewById(R.id.valueavtomobil);
                valuekontragent = itemView.findViewById(R.id.valuekontragent);
                bottomcreateassionmaterial = itemView.findViewById(R.id.bottomcreateassionmaterial);
                // TODO: 20.07.2023  кнопка     добавление Image
                bottom_create_image = itemView.findViewById(R.id.bottom_create_image);
                // TODO: 20.07.2023 рисунки на экране
                im1 = itemView.findViewById(R.id.im1);
                im2 = itemView.findViewById(R.id.im2);
                im3 = itemView.findViewById(R.id.im3);
                im4 = itemView.findViewById(R.id.im4);

                im5 = itemView.findViewById(R.id.im5);
                im6 = itemView.findViewById(R.id.im6);
                im7 = itemView.findViewById(R.id.im7);
                im8 = itemView.findViewById(R.id.im8);

                im9 = itemView.findViewById(R.id.im9);
                im10 = itemView.findViewById(R.id.im10);

                // TODO: 24.07.2023 заполения Данныз всех вставляемых новых Image
                copyOnWriteArrayListGetImages.add(im1);
                copyOnWriteArrayListGetImages.add(im2);
                copyOnWriteArrayListGetImages.add(im3);
                copyOnWriteArrayListGetImages.add(im4);

                copyOnWriteArrayListGetImages.add(im5);
                copyOnWriteArrayListGetImages.add(im6);
                copyOnWriteArrayListGetImages.add(im7);
                copyOnWriteArrayListGetImages.add(im8);

                copyOnWriteArrayListGetImages.add(im9);
                copyOnWriteArrayListGetImages.add(im10);



                // TODO: 07.12.2022 новые
                textipputmaretialttn = itemView.findViewById(R.id.textipputmaretialttn);
                textipputmaretialttdata  = itemView.findViewById(R.id.textipputmaretialttdata);
                textviewmaterialttn  = itemView.findViewById(R.id.textviewmaterialttn);
                textviewmaterialttndata = itemView.findViewById(R.id.textviewmaterialttndata);


                   // TODO: 30.06.2023  слушатель
                    методДатаКликаДляНовогоМатериала(textipputmaretialttdata);

                Log.d(this.getClass().getName(), "\n" + " class " +
                        Thread.currentThread().getStackTrace()[2].getClassName()
                        + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + "   textipputmaretialttdata " +textipputmaretialttdata);
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
    }

    // TODO: 01.07.2023  второй


    // TODO: 28.02.2022 начало  MyViewHolderДляЧата


    void методДатаКликаДляНовогоМатериала(@NonNull  TextView textipputmaretialttdata){
  try{
      textipputmaretialttdata.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              // TODO: 30.06.2023  создание новой даты
            методGetDateForNewOrder(textipputmaretialttdata);
              Log.d(this.getClass().getName(), "\n" + " class " +
                      Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                      " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                      " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
          }
      });

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










    class MyRecycleViewAdapter extends RecyclerView.Adapter<MyViewHolder> {
        private  Cursor  cursorДляЦФО ;
        @SuppressLint("SuspiciousIndentation")
        public MyRecycleViewAdapter(@NotNull  Cursor cursorRecyclerView) {
            this.cursorДляЦФО = cursorRecyclerView;
                Log.i(this.getClass().getName(), " cursorДляЦФО  " + cursorRecyclerView);

        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull List<Object> payloads) {
            try {
                ///todo ЩЕЛКАЕМ КАЖДУЮ СТРОЧКУ ОТДЕЛЬНО
                //  ХэшДааныеСтрока = (ConcurrentSkipListMap<String, String>) ArrayListДанныеОтСканироваиниеДивайсов.get(position);
                Log.i(this.getClass().getName(), "   onBindViewHolder  position" + position + " holder  "+holder);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            super.onBindViewHolder(holder, position, payloads);
        }

        @Override
        public void setHasStableIds(boolean hasStableIds) {
            super.setHasStableIds(hasStableIds);
        }

        @Override
        public void onViewRecycled(@NonNull MyViewHolder holder) {
            super.onViewRecycled(holder);
        }

        @Override
        public boolean onFailedToRecycleView(@NonNull MyViewHolder holder) {
            return super.onFailedToRecycleView(holder);
        }

        @Override
        public void onViewAttachedToWindow(@NonNull MyViewHolder holder) {
            super.onViewAttachedToWindow(holder);
        }

        @Override
        public void onViewDetachedFromWindow(@NonNull MyViewHolder holder) {
            super.onViewDetachedFromWindow(holder);
        }

        @Override
        public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {

            recyclerView.removeAllViews();

            recyclerView.getRecycledViewPool().clear();
            super.onAttachedToRecyclerView(recyclerView);
        }

        @Override
        public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
            super.onDetachedFromRecyclerView(recyclerView);
        }

        @Override
        public int getItemViewType(int position) {
            Log.i(this.getClass().getName(), "      holder.textView1  position " + position);
            try {
                Log.i(this.getClass().getName(), "   getItemViewType  position" + position);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return super.getItemViewType(position);
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View viewПолучениеМатериалов = null;
            try {
                    if(   CursorДляЦФО==null  ){
                        viewПолучениеМатериалов = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_load_actimavmaretialov_new, parent, false);//todo old simple_for_takst_cardview1
                        Log.i(this.getClass().getName(), "   viewГлавныйВидДляRecyclleViewДляСогласования" + viewПолучениеМатериалов + " binderДляПолучениеМатериалов " +binderДляПолучениеМатериалов);
                    }else {
                        if(cursorДляЦФО.getCount()>0){

                                // TODO: 26.07.2023  Data
                               // viewПолучениеМатериалов = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_for_new_assitionmaterial_cardview_new2, parent, false);//todo simple_for_new_assitionmaterial_cardview1_test
                                viewПолучениеМатериалов = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_for_new_assitionmaterial_cardview_new3, parent, false);//todo simple_for_new_assitionmaterial_cardview1_test
                                Log.i(this.getClass().getName(), "   viewГлавныйВидДляRecyclleViewДляСогласования" + viewПолучениеМатериалов + " binderДляПолучениеМатериалов " +binderДляПолучениеМатериалов);
                            }else{
                                // TODO: 26.07.2023 is null
                                viewПолучениеМатериалов = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_isnull_actimavmaretisl_sprachnikov, parent, false);//todo old simple_for_takst_cardview1
                                Log.i(this.getClass().getName(), "   viewГлавныйВидДляRecyclleViewДляСогласования" + viewПолучениеМатериалов+ "  cursorДляЦФО " + cursorДляЦФО);

                        }
                    }
                // TODO: 13.10.2022  добавляем новый компонент в Нащ RecycreView
                myViewHolder = new MyViewHolder(viewПолучениеМатериалов);
                // TODO: 26.07.2023  перегрзука  внешнего вида
                МетодПерегрузкаRecyceView();
                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " binderДляПолучениеМатериалов " +binderДляПолучениеМатериалов+
                        "   myViewHolder" + myViewHolder + "  binderДляПолучениеМатериалов " +binderДляПолучениеМатериалов );
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return myViewHolder;
        }
        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            try {
                Log.i(this.getClass().getName(), "   создание согласования" + myViewHolder + " binderДляПолучениеМатериалов " + binderДляПолучениеМатериалов);
                if (cursorДляЦФО!=null) {
                    if (    cursorДляЦФО.getCount()>0 ) {
                        // TODO: 26.07.2023 Второй Шаг ЗаполняемДЫнними
                        МетодЗаполняемДаннымиПолучениеМАтериалов(holder);
                        МетодАнимации(holder);
                    }
                }
                Log.i(this.getClass().getName(), "   создание согласования" + myViewHolder + " binderДляПолучениеМатериалов " + binderДляПолучениеМатериалов);
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

        private void МетодАнимации(MyViewHolder holder) {
            try {
                  holder.bottomcreateassionmaterial.startAnimation(animationscroll);
                  holder.bottom_create_image.startAnimation(animationscroll);
           holder. textViewcfo.startAnimation(animation);
                holder.   marerialtextgroupmaterial.startAnimation(animation);
                holder. materialtext_onematerial_ves.startAnimation(animation);
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
        ///todo первый метод #1
        private void МетодЗаполняемДаннымиПолучениеМАтериалов(@NonNull MyViewHolder holder) {
            try {
                Log.i(this.getClass().getName(), "  holder "+holder  );
                    МетодДанныеЦФО(holder);
                    МетодДанныеГруппаМатериалов(holder);
                    МетодДанныеОдинМатериалВесовые(holder);
                // TODO: 22.01.2025 Автомобили
                    МетодДанныеАвтомобили(holder);
                // TODO: 22.01.2025  Контрагенты
                    МетодДанныеКонтрагент(holder);


                    МетоднажатиеСозданиеМатериалов(holder);
                    методСозданиеNewImage(holder);
                // TODO: 22.01.2025
                    // TODO: 19.12.2022  ДВА НОВЫХ МЕТОДА ттн И ДАТА ТТН
                    // TODO: 23.12.2022  новые два поля Автомобиль и Котрагент
                    МетодДанныеНастйрокаВводаКоличества(holder);

                    // TODO: 16.12.2022  указываем флаг что мы один раз прошли по строчкем

                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " holder.valueavtomobil "+ holder.valueavtomobil  + " CursorДляАвтомобиля " +CursorДляАвтомобиля);
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
        // TODO: 09.12.2022  метод обрабатываем Номер ТТН и даты ТТН
        private void МетодДатаТТН(@NonNull MyViewHolder holder) {
            try {
                Log.i(this.getClass().getName(), "  holder "+holder  );
                Boolean ФлагВыбиралУжеЦФОИзСпинера=         preferencesМатериалы.getBoolean("ДляСпинераУжеВибиралЦФО",false);
                if(ФлагВыбиралУжеЦФОИзСпинера) {
                    // TODO: 09.12.2022 возвраящяем данные для ЦФО
                    String УжеВыбраннаяТТН = preferencesМатериалы.getString("НазваниеВыбраногоДатаТТН", "");
                    // TODO: 09.12.2022 Востановление ТТН и ДатыТТН
                    holder.      textipputmaretialttdata.setText(УжеВыбраннаяТТН);
                    holder. textipputmaretialttdata.refreshDrawableState();
                    holder.  textipputmaretialttdata.requestLayout();
                }
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
        // TODO: 09.12.2022  метод обрабатываем Номер ТТН и
        private void МетодТТН(@NonNull MyViewHolder holder) {
            try {
                Log.i(this.getClass().getName(), "  holder "+holder  );
                Boolean ФлагВыбиралУжеЦФОИзСпинера=         preferencesМатериалы.getBoolean("ДляСпинераУжеВибиралЦФО",false);
                if(ФлагВыбиралУжеЦФОИзСпинера ) {
                    // TODO: 09.12.2022 возвраящяем данные для ЦФО
                    String ВыбранаяДатаТТГУже = preferencesМатериалы.getString("ПозицияВыбраногоТТН", "");
                    // TODO: 09.12.2022 Востановление ТТН и ДатыТТН
                    holder.      textipputmaretialttn.setText(ВыбранаяДатаТТГУже);
                    holder.    textipputmaretialttn.refreshDrawableState();
                    holder. textipputmaretialttn.requestLayout();
                }
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
        // TODO: 19.10.2022 заполеения ЦФО ФРАГМЕНТ ПОЛУЧЕНИЕ МАТЕРИАЛОВ
        private void МетодДанныеЦФО(@NonNull MyViewHolder holder) {
            try {
                // TODO: 15.12.2022 НОВЫЙ ПОСИК ДЛЯ ЦФО
                SubClassNewFilterSFOНовыйФильтДанных ЦФОстоблик=     new SubClassNewFilterSFOНовыйФильтДанных(cursorДляЦФО);
                ЦФОстоблик.МетодЗапускаНовогоФильтра( holder.textViewcfo,holder, "ЦФО","cfo","name" );

                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + "cursorДляЦФО " +cursorДляЦФО);
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

        private void МетодПоискаФильтр(@NonNull
                                       androidx.appcompat.widget.SearchView searchViewДляНовогоЦФО,
                                       @NonNull SimpleCursorAdapter simpleCursorAdapterЦФО,
                                       @NonNull MyViewHolder holder,
                                       @NonNull View v,
                                       @NonNull String ТаблицаДляФильтра,
                                       @NonNull MaterialTextView materialTextView) {
            try{

                searchViewДляНовогоЦФО.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        try{
                        Log.d(this.getClass().getName()," position");
                            if (hasFocus==true) {
                                // searchViewДляНовогоЦФО.setQueryHint("Поиск цфо");
                                if(materialTextView.getId()== holder.textViewcfo.getId()) {
                                    ((androidx.appcompat.widget.SearchView) v).setQueryHint("Поиск цфо");
                                }
                                if(materialTextView.getId()== holder.marerialtextgroupmaterial.getId()) {
                                    ((androidx.appcompat.widget.SearchView) v).setQueryHint("Поиск гр. материалов");
                                }
                                if(materialTextView.getId()== holder.materialtext_onematerial_ves.getId()) {
                                    ((androidx.appcompat.widget.SearchView) v).setQueryHint("Поиск материал");
                                }
                            }
                        } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(v.getContext()).recordnewerror(e.toString(),
                                this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                    }
                });



                searchViewДляНовогоЦФО.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        try{
                        Log.d(this.getClass().getName()," position");
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(v.getContext()).recordnewerror(e.toString(),
                                this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        try{
                            Log.d(this.getClass().getName()," position");
                            Filter filter= simpleCursorAdapterЦФО.getFilter();
                            filter.filter(newText);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(v.getContext()).recordnewerror(e.toString(),
                                    this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                        return true;
                    }
                });
                simpleCursorAdapterЦФО.setFilterQueryProvider(new FilterQueryProvider() {
                    @Override
                    public Cursor runQuery(CharSequence constraint) {
                        Log.d(this.getClass().getName()," position");
                        try{
                            // TODO: 30.06.2023  метод полчение данных для поиска
                            методGetCursorForQuertyFilter(constraint);

                            // TODO: 17.04.2023
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " constraint " +constraint);
                            
                            handler.post(()->{
                                simpleCursorAdapterЦФО.swapCursor(holder.cursorДляВсехМатериалов);
                                simpleCursorAdapterЦФО.notifyDataSetChanged();
                             holder.   listViewДляЦФО.setSelection(0);
                                if (holder.cursorДляВсехМатериалов.getCount()==0) {
                                    searchViewДляНовогоЦФО.setBackgroundColor(Color.RED);
                                    handler.postDelayed(() -> {
                                        searchViewДляНовогоЦФО.setBackgroundColor(Color.parseColor("#F2F5F5"));
                                    }, 250);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(v.getContext()).recordnewerror(e.toString(),
                                    this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                        return holder.cursorДляВсехМатериалов;
                    }

                   void методGetCursorForQuertyFilter(CharSequence constraint) {
                        try{
                        if(materialTextView.getId()== holder.materialtext_onematerial_ves.getId()) {
                            Bundle bundleДляПосика = (Bundle)  holder.marerialtextgroupmaterial.getTag();
                            Integer ВытаскиваемIDГруппыМатериаловДляПоследующегоПоиска = bundleДляПосика.getInt("ПолучаемIDЦфо");
                            holder.cursorДляВсехМатериалов = (Cursor)
                                    МетодДляНовогоТабеляПолучаемДанныеИзПосикаТОлькоДляОдногоМатериалаБывшейВесовой(ТаблицаДляФильтра,
                                            constraint.toString(), ВытаскиваемIDГруппыМатериаловДляПоследующегоПоиска);
                    }else if (materialTextView.getId()==  holder.valueavtomobil.getId()){
                            Log.d(this.getClass().getName(), "   holder. valueavtomobil.getId() " +   holder.valueavtomobil.getId());
                            holder.cursorДляВсехМатериалов = (Cursor)
                                    МетодДляНовогоТабеляПолучаемДанныеИзПосикаТОлькоДляОдногоМатериалаБывшейВесовой(ТаблицаДляФильтра, constraint.toString(), 0);

                    }else if (materialTextView.getId()== holder.valuekontragent.getId()){
                            holder.cursorДляВсехМатериалов = (Cursor)
                                    МетодДляНовогоТабеляПолучаемДанныеИзПосикаТОлькоДляОдногоМатериалаБывшейВесовой(ТаблицаДляФильтра, constraint.toString(), 0);
                        }else{
                            holder.cursorДляВсехМатериалов = (Cursor)
                                    МетодДляНовогоТабеляПолучаемДанныеИзПосикаТОлькоДляОдногоМатериалаБывшейВесовой(ТаблицаДляФильтра, constraint.toString(),0);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                        new RecordNewErros(v.getContext()).recordnewerror(e.toString(),
                                this.getClass().getName(),
                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                    }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(v.getContext()).recordnewerror(e.toString(),
                        this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }
        // TODO: 02.08.2022
        protected   LinkedHashMap<String, Object> МетодДляНовогоТабеляПолучаемДанные(@NonNull String  ФлагКакаяТаблицаОбработки, @NotNull String Фильтр){
            Cursor cursor = null;
            LinkedHashMap<String, Object> linkedHashMap=null;
            try{
                Integer   ПубличныйIDДляФрагмента    =   new GetttingPublicID().getttingPublicID(getContext());

                Log.d(getContext().getClass().getName(), "\n"
                        + " ПубличныйIDДляФрагмента: " + ПубличныйIDДляФрагмента);
                Bundle bundleДляПЕредачи=new Bundle();
                bundleДляПЕредачи.putString("Таблица",ФлагКакаяТаблицаОбработки);
                bundleДляПЕредачи.putString("ФильтрДляПоиска",Фильтр);
                Intent intentПолучениеМатериалов = new Intent(getContext(), ServiceForAdminissionMaterial.class);
                intentПолучениеМатериалов.setAction("ПолучениеМатериалоСозданиеНового");
                intentПолучениеМатериалов.putExtras(bundleДляПЕредачи);
                if (binderДляПолучениеМатериалов!=null) {
                    cursor = (Cursor) binderДляПолучениеМатериалов.getService().МетодCлужбыПолучениеМатериалов(getContext(), intentПолучениеМатериалов);
                    Log.d(this.getClass().getName(), "   cursor " + cursor);
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return linkedHashMap;
        }
        // TODO: 02.08.2022
        protected   LinkedHashMap<String, Object> МетодДляНовогоТабеляПолучаемДанныеИзПосика(@NonNull String  ФлагКакаяТаблицаОбработки, @NotNull String Фильтр){
            Cursor cursor = null;
            LinkedHashMap<String, Object> linkedHashMap=null;
            try{
                Integer   ПубличныйIDДляФрагмента  =   new GetttingPublicID().getttingPublicID(getContext());

                Log.d(getContext().getClass().getName(), "\n"
                        + " ПубличныйIDДляФрагмента: " + ПубличныйIDДляФрагмента);
                Bundle bundleДляПЕредачи=new Bundle();
                bundleДляПЕредачи.putString("Таблица",ФлагКакаяТаблицаОбработки);
                bundleДляПЕредачи.putString("ФильтрДляПоиска",Фильтр);
                Intent intentПолучениеМатериалов = new Intent(getContext(), ServiceForAdminissionMaterial.class);
                intentПолучениеМатериалов.setAction("ПолучениеМатериалоИзНовгоПоиска");
                intentПолучениеМатериалов.putExtras(bundleДляПЕредачи);
                if (binderДляПолучениеМатериалов!=null) {
                    cursor = (Cursor) binderДляПолучениеМатериалов.getService().МетодCлужбыПолучениеМатериалов(getContext(), intentПолучениеМатериалов);
                    Log.d(this.getClass().getName(), "   cursor " + cursor);
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return linkedHashMap;
        }

        // TODO: 02.08.2022
        protected   Cursor МетодДляНовогоТабеляПолучаемДанныеИзПосикаТОлькоДляОдногоМатериалаБывшейВесовой(@NonNull String  ФлагКакаяТаблицаОбработки, @NotNull String Фильтр,Integer ЗначениеГруппыМатериаловДляПосика){
            Cursor cursor = null;
            LinkedHashMap<String, Object> linkedHashMap=null;
            try{
                Integer   ПубличныйIDДляФрагмента       =new GetttingPublicID().getttingPublicID(getContext());
                Log.d(getContext().getClass().getName(), "\n"
                        + " ПубличныйIDДляФрагмента: " + ПубличныйIDДляФрагмента);
                Bundle bundleДляПЕредачи=new Bundle();
                bundleДляПЕредачи.putString("Таблица",ФлагКакаяТаблицаОбработки);
                bundleДляПЕредачи.putString("ФильтрДляПоиска",Фильтр);
                bundleДляПЕредачи.putInt("ЗначениеГруппыМатериаловДляПосика",ЗначениеГруппыМатериаловДляПосика);
                Intent intentПолучениеМатериалов = new Intent(getContext(), ServiceForAdminissionMaterial.class);
                intentПолучениеМатериалов.setAction("ПолучениеМатериалоИзНовгоПоиска");
                intentПолучениеМатериалов.putExtras(bundleДляПЕредачи);
                if (binderДляПолучениеМатериалов!=null) {
                    cursor = (Cursor) binderДляПолучениеМатериалов.getService().МетодCлужбыПолучениеМатериалов(getContext(), intentПолучениеМатериалов);
                    Log.d(this.getClass().getName(), "   cursor " + cursor);
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return cursor;
        }


        // TODO: 19.10.2022 ГРУППА МАТЕРИАЛОВ
        private void МетодДанныеГруппаМатериалов(@NonNull MyViewHolder holder) {
            try {
                // TODO: 17.11.2022  если пользователь уже выбирал

                SubClassNewFilterSFOНовыйФильтДанных sfoНовыйФильтДанныхГруппаМатериалов=       new SubClassNewFilterSFOНовыйФильтДанных(CursorДляГруппаМатериалов);
                sfoНовыйФильтДанныхГруппаМатериалов   .МетодЗапускаНовогоФильтра( holder.marerialtextgroupmaterial, holder,"Группа материалов",
                        "type_materials","name" );


                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " holder.marerialtextgroupmateria " +holder.marerialtextgroupmaterial);

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


        // TODO: 19.10.2022 Создание Автомобили
        private void МетодДанныеАвтомобили(@NonNull MyViewHolder holder) {
            try {
                // TODO: 17.11.2022  если пользователь уже выбирал

                SubClassNewFilterSFOНовыйФильтДанных НовыйФильтДанныхАвтомобили=       new SubClassNewFilterSFOНовыйФильтДанных(CursorДляАвтомобиля);
                НовыйФильтДанныхАвтомобили   .МетодЗапускаНовогоФильтра( holder.valueavtomobil, holder,"Автомобили",
                        "track","fullname" );


                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " holder.valueavtomobil " +holder.valueavtomobil);

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

        // TODO: 19.10.2022 Создание котррагента
        private void МетодДанныеКонтрагент(@NonNull MyViewHolder holder) {
            try {
                // TODO: 17.11.2022  если пользователь уже выбирал

                SubClassNewFilterSFOНовыйФильтДанных НовыйФильтДанныхКонтрагенты=       new SubClassNewFilterSFOНовыйФильтДанных(CursorДляКонтрагента);
                НовыйФильтДанныхКонтрагенты   .МетодЗапускаНовогоФильтра( holder.valuekontragent, holder,"Контрагент",
                        "company","name" );


                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " holder.valuekontragent " +holder.valuekontragent);

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




        // TODO: 19.10.2022 ПОЛУЧАЕМ ОДИН МАТЕРИАЛ ПОСЛЕ ВСЕХ ВЕСОВЫЕ
        private void МетодДанныеОдинМатериалВесовые(@NonNull MyViewHolder holder) {
            try {
                // TODO: 15.12.2022 НОВЫЙ ПОСИК ДЛЯ Одного МАТЕРАИАЛ ОДДИН БЫШИЙ ВЕСОВОЙ

                SubClassNewFilterSFOНовыйФильтДанных sfoНовыйФильтДанныхВесовая=       new SubClassNewFilterSFOНовыйФильтДанных(CursorДляОдногоМатериалаБышВесов);
                sfoНовыйФильтДанныхВесовая.МетодЗапускаНовогоФильтра( holder.materialtext_onematerial_ves,holder,"материал",
                        "nomen_vesov","name" );

                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                        + " CursorДляОдногоМатериалаБышВесов " +CursorДляОдногоМатериалаБышВесов);

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

        // TODO: 19.10.2022 ПОЛУЧАЕМ Автомобиля




        // TODO: 19.10.2022 ПОЛУЧАЕМ Контагент
        private void МетодДанныеНастйрокаВводаКоличества(@NonNull MyViewHolder holder) {
            try {

                // TODO: 23.01.2023
                KeyboardVisibilityEvent.setEventListener(getActivity(), new KeyboardVisibilityEventListener() {
                    @Override
                    public void onVisibilityChanged(boolean isOpen) {
                        // TODO: 23.01.2023
                   if(isOpen==true){

                       scrollViewНовыйматериал.scrollBy(0, +500);
                    scrollViewНовыйматериал.fullScroll(View.FOCUS_DOWN);
                       scrollViewНовыйматериал.smoothScrollTo(0, 500);
                    scrollViewНовыйматериал.smoothScrollTo(0,scrollViewНовыйматериал.getBottom()-1);

                   }else{
                       scrollViewНовыйматериал.scrollBy(0, 0);
                       scrollViewНовыйматериал.fullScroll(View.FOCUS_UP);
                       scrollViewНовыйматериал.smoothScrollTo(0, 0);
                       scrollViewНовыйматериал.smoothScrollTo(0,scrollViewНовыйматериал.getBottom());
                   }
                        scrollViewНовыйматериал.refreshDrawableState();
                    }
                });
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


        // TODO: 20.10.2022 нажатие на кнопку
        private  void МетоднажатиеСозданиеМатериалов(@NonNull MyViewHolder holder){
            try{
                holder. bottomcreateassionmaterial.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d(this.getClass().getName(), " МетоднажатиеСозданиеМатериалов  ");
                        МетодЗапускаАнимацииКнопок(v);//todo только анимауия
                        Log.d(this.getClass().getName(), " binderДляПолучениеМатериалов " + binderДляПолучениеМатериалов);
                        handler.postDelayed(()->{
                            Intent intentСамоПолучениеНовогоМатериала=new Intent("СамоСозданиеНовогоМатериала");
                            // TODO: 21.10.2022  перед созданием нового материала получанеим Данные которые нужно вставить
                            Integer IDДляВставкиЦФО= 0;
                            if (   holder.textViewcfo.getText().length()>0) {
                                Bundle bundle=(Bundle)   holder.textViewcfo.getTag();
                                IDДляВставкиЦФО=      bundle.getInt("ПолучаемIDЦфо",0);
                            }
                            Integer IDДляВставкиГруппыматериалов= 0;
                            if (  holder.  marerialtextgroupmaterial.length()>0) {
                                Bundle bundle=(Bundle)    holder.marerialtextgroupmaterial.getTag();
                                IDДляВставкиГруппыматериалов=      bundle.getInt("ПолучаемIDЦфо",0);
                            }
                            Integer IDДляВставкиОдногоматериала= 0;
                            if (   holder. materialtext_onematerial_ves.length()>0) {
                                Bundle bundle=(Bundle)    holder.materialtext_onematerial_ves.getTag();
                                IDДляВставкиОдногоматериала=      bundle.getInt("ПолучаемIDЦфо",0);
                            }

                            Long ВыбраноеЗначениеКоличествоМатериала= 0l;
                            if ( holder.textipputcountassinmaterail.getText().length()>0) {
                                ВыбраноеЗначениеКоличествоМатериала =Long.parseLong( holder.textipputcountassinmaterail.getText().toString());
                            }
                            String ВыбраноеТТНМатериала=new String();
                            if ( holder.textipputmaretialttn.getText().length()>0) {
                                ВыбраноеТТНМатериала = holder.textipputmaretialttn.getText().toString();
                            }
                            String ВыбраноеТТНДата=new String();
                            if ( holder.textipputmaretialttdata.getText().length()>0) {
                                ВыбраноеТТНДата = holder. textipputmaretialttdata.getText().toString();
                            }
                            // TODO: 27.12.2022 ДВА НОВЫХ ПОЛЯ АВТОМОБИЛИ И ИЛИ  КОНТРАГЕНТЫ
                            Integer ВыбраноеАвтомобили=0;
                            if ( holder.valueavtomobil.getText().length()>0) {
                                Bundle bundleАвтомобили=  (Bundle)  holder. valueavtomobil.getTag();
                                ВыбраноеАвтомобили=  bundleАвтомобили.getInt("ПолучаемIDЦфо");
                                Log.d(this.getClass().getName(), " ВыбраноеАвтомобили " +ВыбраноеАвтомобили);

                            }
                            Integer ВыбраноеКонтагенты=0;
                            if (  holder.valuekontragent.getText().length()>0) {
                                Bundle bundleКонтрагенты=  (Bundle)   holder.valuekontragent.getTag();
                                ВыбраноеКонтагенты=  bundleКонтрагенты.getInt("ПолучаемIDЦфо");
                                Log.d(this.getClass().getName(), " ВыбраноеКонтагенты " +ВыбраноеКонтагенты);
                            }
                            Log.d(this.getClass().getName(), " IDДляВставкиМатериалы " +IDДляВставкиГруппыматериалов +
                                    " IDДляВставкиВесовые "+IDДляВставкиОдногоматериала+"IDДляВставкиЦФО " +IDДляВставкиЦФО+
                                    " ВыбраноеЗначениеКоличествоМатериала " +ВыбраноеЗначениеКоличествоМатериала+ " ВыбраноеТТНМатериала " +ВыбраноеТТНМатериала+
                                    " ВыбраноеАвтомобили " +ВыбраноеАвтомобили + " ВыбраноеКонтагенты " +ВыбраноеКонтагенты);
                            // TODO: 21.10.2022 проверка выбрали ли все ТРИ СПРАВОЧНИКА ДЛЯ СОЗДАНИЯ НОВОГО МАТЕРИАЛА
                            if(IDДляВставкиГруппыматериалов>0 && IDДляВставкиОдногоматериала>0
                                    &&  IDДляВставкиЦФО>0 && ВыбраноеЗначениеКоличествоМатериала>0f && ВыбраноеКонтагенты>0 ){
                                Bundle bundleДляСоздании=new Bundle();
                                bundleДляСоздании.putInt("cfo",IDДляВставкиЦФО);
                                bundleДляСоздании.putInt("type_material",IDДляВставкиГруппыматериалов);
                                bundleДляСоздании.putInt("nomen_vesov",IDДляВставкиОдногоматериала);
                                bundleДляСоздании.putLong("count",ВыбраноеЗначениеКоличествоМатериала);
                                // TODO: 27.12.2022 для двух новых полей автомиби и контрагент
                                bundleДляСоздании.putString("ttn",ВыбраноеТТНМатериала);
                                bundleДляСоздании.putString("datattn",ВыбраноеТТНДата);
                                // TODO: 27.12.2022  два новых поля для создание материла и контрагент
                                bundleДляСоздании.putInt("tracks",ВыбраноеАвтомобили);
                                bundleДляСоздании.putInt("companys",ВыбраноеКонтагенты);
                                intentСамоПолучениеНовогоМатериала.putExtras(bundleДляСоздании);

                                // TODO: 31.07.2023 САМА ВСТАВКА НОВГО МАТЕРИЛА 
                                Long ХэшРезультататСозданияСозданиеНовогоМатериала =
                                        binderДляПолучениеМатериалов.getService().МетодCлужбыСозданиеНовогоМатериала(getContext(),intentСамоПолучениеНовогоМатериала);
                                // TODO: 21.10.2022  результат  создание нового материала
                                Log.d(this.getClass().getName(), " ХэшРезультататСозданияСозданиеНовогоМатериала  "+ХэшРезультататСозданияСозданиеНовогоМатериала+
                                        " ХэшРезультататСозданияСозданиеНовогоМатериала " +ХэшРезультататСозданияСозданиеНовогоМатериала);




                                 // TODO: 31.07.2023  Реакция На Результат Вставки НовыхДанных
                                if (ХэшРезультататСозданияСозданиеНовогоМатериала>0) {
                                    // TODO: 31.07.2023  Записываем Новые ФОтографии
                                    if (copyOnWriteArrayListSuccessAddImages.size()>0) {
                                        Long ХэшРезультататСозданияНовыхИлиВыбранныхФотографий = binderДляПолучениеМатериалов.getService().
                                                МетодВставкаНовойИлиВыбранойФотографииImageUpAndCreate(copyOnWriteArrayListSuccessAddImages,
                                                        ХэшРезультататСозданияСозданиеНовогоМатериала);

                                        Log.d(this.getClass().getName(), " ХэшРезультататСозданияНовыхИлиВыбранныхФотографий "
                                                +ХэшРезультататСозданияНовыхИлиВыбранныхФотографий );

                                    }

                                    // TODO: 31.07.2023 после вставки новгой материалов и новой ФОТО
                                    // TODO: 31.07.2023 метод После Успешной Вставки ЗапускаемСинхронизацию и Запись ДАнных в
                                        методЗаполениеПослеУспешнойВставкиНовгоМатерилаSharedPreferences(v,
                                                IDДляВставкиЦФО, IDДляВставкиГруппыматериалов,
                                                IDДляВставкиОдногоматериала, ВыбраноеАвтомобили,
                                                ВыбраноеКонтагенты, holder);

                                        методBackToFragmentAdmissionMaterilas(v);

                                    // TODO: 27.09.2023 clear
                                    if (copyOnWriteArrayListSuccessAddImages.size()>0) {
                                        copyOnWriteArrayListSuccessAddImages.clear();
                                    }
                                    if(copyOnWriteArrayListGetImages.size()>0){
                                        copyOnWriteArrayListGetImages.clear();
                                    }

                                             // TODO: 07.08.2023 CLEAR ДАННЫЕ
                                        Log.d(this.getClass().getName(), " IDДляВставкиГруппыматериалов " +IDДляВставкиГруппыматериалов +
                                                " IDДляВставкиОдногоматериала "+IDДляВставкиОдногоматериала+"IDДляВставкиЦФО " +IDДляВставкиЦФО);

                                }else {

                                    Snackbar.make(v, "Материалал не добавился !!!" +
                                            " !!!",Snackbar.LENGTH_LONG).setAction("Action",null).show();

                                }




                            }else {
                                Log.d(this.getClass().getName(), " IDДляВставкиГруппыматериалов " +IDДляВставкиГруппыматериалов +
                                        " IDДляВставкиОдногоматериала "+IDДляВставкиОдногоматериала+"IDДляВставкиЦФО " +IDДляВставкиЦФО);
                                Snackbar.make(v, "Выберете все три справочника и/или количество" +
                                        " !!!",Snackbar.LENGTH_LONG).setAction("Action",null).show();
                            }
                            Log.d(this.getClass().getName(), "  v  " + v);
                        },150);
                    }
                });
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










        private void методЗаполениеПослеУспешнойВставкиНовгоМатерилаSharedPreferences
                (View v, Integer IDДляВставкиЦФО, Integer IDДляВставкиГруппыматериалов,
                 Integer IDДляВставкиОдногоматериала, Integer ВыбраноеАвтомобили,
                 Integer ВыбраноеКонтагенты,
                 @NonNull MyViewHolder holder) {
            try {

                // TODO: 17.11.2022 запоманаем выбраное цфо
                SharedPreferences.Editor editor = preferencesМатериалы.edit();
                editor.putBoolean("ДляСпинераУжеВибиралЦФО",true);
                // TODO: 09.12.2022 цфо
                editor.putInt("ПозицияВыбраногоЦФО", IDДляВставкиЦФО);
                editor.putString("НазваниеВыбраногоЦФО", holder.textViewcfo.getText().toString());
                // TODO: 09.12.2022 группа материалов
                editor.putInt("ПозицияВыбраногоГруппыМатериалов", IDДляВставкиГруппыматериалов);
                editor.putString("НазваниеВыбраногоГруппыМатериалов", holder.marerialtextgroupmaterial.getText().toString());
                // TODO: 09.12.2022 сам матералов
                editor.putInt("ПозицияВыбраногоМатериала", IDДляВставкиОдногоматериала);
                editor.putString("НазваниеВыбраногоМатериала", holder.materialtext_onematerial_ves.getText().toString());
                // TODO: 09.12.2022 два поля ТТН и ТТН ДАТА
                Boolean     ФлагДляСкрытыхМатериалов = preferencesМатериалы.getBoolean("ФлагДляСкрытыхМатериалов",false);
                if (ФлагДляСкрытыхМатериалов==true) {
                    editor.putString("ПозицияВыбраногоТТН", holder.textipputmaretialttn.getText().toString());
                    editor.putString("НазваниеВыбраногоДатаТТН", holder.textipputmaretialttdata.getText().toString());
                }
                // TODO: 27.12.2022  ДВА НОВЫХ ПОЛЯ МАТЕРИАЛОВ КОНТРАГЕНТ И АВТОМОБИЛЬ
                // TODO: 09.12.2022 Автомобиль запоминаем
                editor.putInt("ПозицияВыбраногоАвтомобили", ВыбраноеАвтомобили);
                editor.putString("НазваниеВыбраногоАвтомобили", holder.valueavtomobil.getText().toString());
                // TODO: 09.12.2022 Автомобиль Контрогент
                editor.putInt("ПозицияВыбраногоКонтрагент", ВыбраноеКонтагенты);
                editor.putString("НазваниеВыбраногоКонтрагент", holder.valuekontragent .getText().toString());
                // TODO: 27.12.2022 запоминаем параметры
                editor.commit();

                Log.d(this.getClass().getName(), " IDДляВставкиГруппыматериалов " +IDДляВставкиГруппыматериалов +
                        " IDДляВставкиОдногоматериала "+IDДляВставкиОдногоматериала+"IDДляВставкиЦФО " +IDДляВставкиЦФО);
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

        // TODO: 20.07.2023 КНОПКА добавления нового Image
private  void методСозданиеNewImage(@NonNull MyViewHolder holder){
    try{
        holder. bottom_create_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.animate().rotationX(-40l);
                handler .postDelayed(()->{
                    v.animate().rotationX(0);
                    // TODO: 20.07.2023 создане нового рисунка
                    SubClassCreateNewImageForMateril subClassCreateNewImageForMateril=new SubClassCreateNewImageForMateril();

                    subClassCreateNewImageForMateril.new SubClassUploadImageAnFragmentImage().методЗагрузкиИлиСозданиеImage(holder);

                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                },50);

            }
        });
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
        public long getItemId(int position) {
            // TODO: 04.03.2022
            Log.i(this.getClass().getName(), "     getItemId holder.position " + position);
            return super.getItemId(position);
        }
        @Override
        public int getItemCount() {
            int КоличесвоСтрок = 0;
            try {
                КоличесвоСтрок = 1;
                Log.d(this.getClass().getName(), " binderДляПолучениеМатериалов "+ binderДляПолучениеМатериалов);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(getContext().getClass().getName(),
                        "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(),
                        this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
            return КоличесвоСтрок;
        }



        // TODO: 15.12.2022 класс для всех новый фильр дл я данных
        public class SubClassNewFilterSFOНовыйФильтДанных {
       private       MaterialTextView materialTextView=null;;
            private  MaterialButton    materialButtonЗакрытьДиалогSearveView=null;
            private    Cursor cursor=null;;
            private    MyViewHolder holder=null;;
            private    int    КакойИмменоВидЗагружатьДляНовогоПосика=R.layout.simple_for_new_spinner_searchview;
            protected SubClassNewFilterSFOНовыйФильтДанных(@NonNull Cursor cursor){
                this.cursor = cursor;
                Log.d(this.getClass().getName(), "   cursor " +cursor);
            }
            protected   void МетодЗапускаНовогоФильтра( @NonNull MaterialTextView materialTextТекущийВыбранныйСправочник,
                                                         @NonNull MyViewHolder holder,
                                                         @NonNull String ФлагРежимНовогоФильтра,
                                                         @NonNull String ТаблицаДляФильтра,
                                                        @NonNull String КакойСтолбикЗагружатьВSimpleAdapter) {
                this.materialTextView = materialTextТекущийВыбранныйСправочник;
                this.holder = holder;
                // TODO: 16.12.2022
                materialTextТекущийВыбранныйСправочник.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        try{
                        // TODO: 20.12.2022  оперделяем какой вид загружать когда выбран и когда не выбрана гурппа метарилов
                            MaterialAlertDialogBuilder materialAlertDialogBuilderСправочникСоПоиском = new MaterialAlertDialogBuilder(v.getContext()){
                            @NonNull
                            @Override
                            public MaterialAlertDialogBuilder setView(View view) {
                                try{

                                    // TODO: 14.12.2022
                                   materialButtonЗакрытьДиалогSearveView =    (MaterialButton) view.findViewById(R.id.bottom_newscanner1);
                                    // TODO: 26.07.2023  клик чтобы закрыть поиск
                                    МетодЗакрытиеНовогоПосика(materialButtonЗакрытьДиалогSearveView);
                                // TODO: 19.12.2022 какой КУРСОР БУДЕТ ВЫПОЛНЯТЬСЯ
                                    SearchView searchViewДляНовогоЦФО= null;

                                      //  МетодКоторыйОперделянтТекуийКурсор(holder, cursor);

                                    Log.d(this.getClass().getName(), "   ХэшиАрайЛистДляСпиноровЦФО " + cursor);
                                    holder.  listViewДляЦФО =    (ListView) view.findViewById(R.id.SearchViewList);
                                    if (holder.  listViewДляЦФО!=null) {
                                    holder.   listViewДляЦФО.setTextFilterEnabled(true);
                                    searchViewДляНовогоЦФО = (SearchView) view.findViewById(R.id.searchview_newscanner);
                                    // TODO: 14.12.2022
                                    searchViewДляНовогоЦФО.setDrawingCacheEnabled(true);
                                        // TODO: 17.01.2024
                                        TextView textViewСтрокаПосика = searchViewДляНовогоЦФО.findViewById(com.google.android.material.R.id.search_src_text);
                                        textViewСтрокаПосика.setTextColor(Color.rgb(0,102,102));
                                        textViewСтрокаПосика.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));

                                }


                                    // TODO: 01.08.2023  Метод Выбираем  Какие ДАные БУдут ЗАгруженны и В зависимости выбран был или нет Старшый Материал

                                    методВЫбираемКакиеДанныеНужноЗагрузить(searchViewДляНовогоЦФО,materialTextТекущийВыбранныйСправочник);


                                    // TODO: 17.04.2023
                                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " holder.cursorДляВсехМатериалов " +holder.cursorДляВсехМатериалов);




                                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                            "materialButtonЗакрытьДиалогSearveView"+  materialButtonЗакрытьДиалогSearveView);


                            } catch (Exception e) {
                                e.printStackTrace();
                                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                new RecordNewErros(v.getContext()).recordnewerror(e.toString(),
                                        this.getClass().getName(),
                                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                            }

                                return super.setView(view);
                                // TODO: 20.12.2022  тут конец выбеленого
                            }

                             // TODO: 01.08.2023 выбирает какие данные надо загружать
                             private void методВЫбираемКакиеДанныеНужноЗагрузить(SearchView searchViewДляНовогоЦФО, @NonNull MaterialTextView materialTextТекущийВыбранныйСправочник) {
                                try{
                                    // TODO: 22.01.2025
                                 if (materialTextТекущийВыбранныйСправочник.getId()==  holder.materialtext_onematerial_ves.getId()) {
                                     // TODO: 22.01.2025
                                     if (holder. marerialtextgroupmaterial.getText().length()>0) {
                                         // TODO: 01.08.2023 Когда есть данных и СТраший Компонет ВЫбрал Группу Материалов

                                         Intent intentДляПолучениеСправочкинов=new Intent("НовыеМатериалыПолучениеСправочников");
                                         Bundle bundle= (Bundle)  holder. marerialtextgroupmaterial.getTag();
                                         Long ФильтрВесовых=bundle.getLong("oneNewMaterialUUID");

                                         CursorДляОдногоМатериалаБышВесов=       МетоПолучениеДанныхДляОдногоМатериала(intentДляПолучениеСправочкинов,ФильтрВесовых);
                                      cursor=CursorДляОдногоМатериалаБышВесов;

                                         КакойИмменоВидЗагружатьДляНовогоПосика =R.layout.simple_for_new_spinner_searchview;
                                         // TODO: 01.08.2023 метод с Полынми Данными
                                         методКогдаДАнныеВыбраныСправочникиSimpleCursor(searchViewДляНовогоЦФО);
                                     } else {
                                         // TODO: 01.08.2023 Загрузка НЕ подынми Данными
                                         методDontCursorSimplrCursor( v);
                                     }
                                 }else{
                                     // TODO: 01.08.2023 метод с Полынми Данными
                                     методКогдаДАнныеВыбраныСправочникиSimpleCursor(searchViewДляНовогоЦФО);
                                 }
                                 Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                         " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                         " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                         "materialButtonЗакрытьДиалогSearveView"+  materialButtonЗакрытьДиалогSearveView);

                             } catch (Exception e) {
                                 e.printStackTrace();
                                 Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                         " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                 new RecordNewErros(v.getContext()).recordnewerror(e.toString(),
                                         this.getClass().getName(),
                                         Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                             }
                             }

                             private void методКогдаДАнныеВыбраныСправочникиSimpleCursor(SearchView searchViewДляНовогоЦФО) {
                                try{
                                 ///TODO ГЛАВНЫЙ АДАПТЕР чата
                                 SimpleCursorAdapter    simpleCursorAdapterДляНовыхСтпавочниках= new SimpleCursorAdapter(v.getContext(),
                                         R.layout.simple_newspinner_dwonload_newfiltersearch,// R.layout.simple_newspinner_dwonload_cfo2,
                                         cursor,
                                         new String[]{ КакойСтолбикЗагружатьВSimpleAdapter,"_id"},
                                         new int[]{android.R.id.text1,android.R.id.text1}, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
                                 SimpleCursorAdapter.ViewBinder БиндингДляЦФО = new SimpleCursorAdapter.ViewBinder(){

                                     @Override
                                     public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                                         switch (view.getId()) {
                                             case android.R.id.text1:
                                                 Log.d(this.getClass().getName()," position");
                                                 if (cursor.getCount()>0) {
                                                     try{
                                                         MaterialTextView materialTextViewЭлементСписка=(MaterialTextView) view;
                                                         Integer ИндексНазвание = cursor.getColumnIndex(КакойСтолбикЗагружатьВSimpleAdapter);///user_update  --old/// uuid
                                                         String  Название = cursor.getString(ИндексНазвание);
                                                         // TODO: 13.12.2022  производим состыковку

                                                             Integer UUIDНазвание = cursor.getColumnIndex("uuid");///user_update  --old/// uuid
                                                             Long UUID = cursor.getLong(UUIDНазвание);
                                                             Bundle bundle=new Bundle();

                                                         if (materialTextТекущийВыбранныйСправочник.getId()==  holder.textViewcfo.getId()) {
                                                                bundle.putString("NewMaterialЦФО",Название);
                                                                bundle.putLong("NewMaterialUUID",UUID);
                                                         }
                                                         if (materialTextТекущийВыбранныйСправочник.getId()==  holder. marerialtextgroupmaterial.getId()) {
                                                                 bundle.putString("groupNewMaterialЦФО",Название);
                                                                bundle.putLong("oneNewMaterialUUID",UUID);
                                                         }
                                                         if (materialTextТекущийВыбранныйСправочник.getId()==  holder.materialtext_onematerial_ves.getId()) {
                                                                bundle.putString("oneNewMaterialЦФО",Название);
                                                                bundle.putLong("oneNewMaterialUUID",UUID);
                                                         }
                                                         if (materialTextТекущийВыбранныйСправочник.getId()==  holder.valueavtomobil.getId()) {
                                                             bundle.putString("NewMaterialAvto",Название);
                                                             bundle.putLong("NewMaterialAvtoUUID",UUID);
                                                         }

                                                         if (materialTextТекущийВыбранныйСправочник.getId()==  holder.valuekontragent.getId()) {
                                                             bundle.putString("NewMaterialKontragent",Название);
                                                             bundle.putLong("NewMaterialKontragentUUID",UUID);
                                                         }



// TODO: 22.01.2025
                                                             materialTextViewЭлементСписка.setTag(bundle);

                                                         Log.d(this.getClass().getName()," НазваниеЦФО"+ Название+
                                                                 " UUID "+UUID);
                                                         // TODO: 20.01.2022
                                                         Log.d(this.getClass().getName()," НазваниеЦФО "+Название);
                                                         boolean ДлинаСтрокивСпиноре = Название.length() >40;
                                                         if (ДлинаСтрокивСпиноре==true) {
                                                             StringBuffer sb = new StringBuffer(Название);
                                                             sb.insert(40, System.lineSeparator());
                                                             Название = sb.toString();
                                                             Log.d(v.getContext().getClass().getName(), " Название " + "--" + Название);/////
                                                         }
                                                         ((MaterialTextView)view).setText(Название);

                                                         materialTextViewЭлементСписка.startAnimation(animation);
                                                         // TODO: 13.12.2022 слушатель

                                                         // TODO: 13.12.2022 филь
                                                     } catch (Exception e) {
                                                         e.printStackTrace();
                                                         Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                                 " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                                         new RecordNewErros(v.getContext()).recordnewerror(e.toString(),
                                                                 this.getClass().getName(),
                                                                 Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                                     }
                                                     return true;
                                                 } else {
                                                     Log.d(this.getClass().getName()," position");
                                                     return false;
                                                 }
                                         }
                                         return false;
                                     }
                                 };
                                 simpleCursorAdapterДляНовыхСтпавочниках.setViewBinder(БиндингДляЦФО);
                                 simpleCursorAdapterДляНовыхСтпавочниках.notifyDataSetChanged();
                                    holder.listViewДляЦФО.removeAllViewsInLayout();
                                 holder.listViewДляЦФО.setAdapter(simpleCursorAdapterДляНовыхСтпавочниках);
                                 holder.listViewДляЦФО.startAnimation(animation);
                                 holder.listViewДляЦФО.setSelection(0);
                                 holder.listViewДляЦФО.refreshDrawableState();
                                 holder.listViewДляЦФО.requestLayout();

                                 // TODO: 13.12.2022  Поиск
                                 // TODO: 13.12.2022  Поиск и его слушель
                                 МетодПоискаФильтр(searchViewДляНовогоЦФО,simpleCursorAdapterДляНовыхСтпавочниках,holder,v,ТаблицаДляФильтра,materialTextТекущийВыбранныйСправочник);
                                 // TODO: 26.07.2023  клик по данным
                                 методКликПоЦФО();

                                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                            "materialButtonЗакрытьДиалогSearveView"+  materialButtonЗакрытьДиалогSearveView);

                             } catch (Exception e) {
                                 e.printStackTrace();
                                 Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                         " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                 new RecordNewErros(v.getContext()).recordnewerror(e.toString(),
                                         this.getClass().getName(),
                                         Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                             }
                             }

                         }
                                .setTitle(ФлагРежимНовогоФильтра)
                                .setCancelable(false)
                                .setIcon( R.drawable.icon_newscannertwo)
                                .setView(getLayoutInflater().inflate( КакойИмменоВидЗагружатьДляНовогоПосика, null ));

                            // TODO: 04.08.2023  запускаем нвоый справочник со сПОИИСКОМ
                            if (  holder.alertDialog==null) {
                                holder.alertDialog=       materialAlertDialogBuilderСправочникСоПоиском.show();
                            } else {
                                if (! holder.alertDialog.isShowing()) {
                                    holder.alertDialog=       materialAlertDialogBuilderСправочникСоПоиском.show();
                                }
                            }

                        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                        layoutParams.copyFrom(     holder.alertDialog.getWindow().getAttributes());
                        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                        layoutParams.height =WindowManager.LayoutParams.MATCH_PARENT;
                        layoutParams.gravity = Gravity.CENTER;
                            holder. alertDialog.getWindow().setAttributes(layoutParams);
                        // TODO: 13.12.2022 ВТОРОЙ СЛУШАТЕЛЬ НА КНОПКУ
                        Log.d(this.getClass().getName(), " binderДляПолучениеМатериалов "+ binderДляПолучениеМатериалов);
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



                    private void методDontCursorSimplrCursor(@NonNull View v) {
                        try {
                            ArrayList<HashMap<String, Object>> ЛистНетданных= new ArrayList<HashMap<String, Object>> ();
                            HashMap<String, Object> map = new HashMap<>();
                            map.put("allimage", "Не создано !!!");
                            map.put("allimage", "Не создано !!!");
                            map.put("allimage", "Не создано !!!");
                            ЛистНетданных.add(map);
                            SimpleAdapter АдаптерКогдаНетданных = new SimpleAdapter(v.getContext(),
                                    ЛистНетданных,
                                    R.layout.simple_for_new_spinner_searchview_kogda_net_group,
                                    new String[]{ "allimage"},
                                    new int[]{ android.R.id.text1});

                            SimpleAdapter.ViewBinder БиндингКогдаНетДАнных = new SimpleAdapter.ViewBinder() {
                                @Override
                                public boolean setViewValue(View view, Object data, String textRepresentation) {
                                    try{
                                        switch (view.getId()) {
                                            case android.R.id.text1:
                                                LinearLayout linearLayoutНеВыбралиСправчник=(LinearLayout) view;
                                                MaterialTextView multiAutoCompleteTextView=
                                                        (MaterialTextView)       linearLayoutНеВыбралиСправчник.findViewById(R.id.MaterialTextViewisnulldontparent_maretials);

                                                multiAutoCompleteTextView.setHint("Вы не выбрали группу материалов !!!");

                                                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+ " multiAutoCompleteTextView "+multiAutoCompleteTextView);
                                                return true;
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                        new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    }
                                    return false;
                                }
                            };
                            АдаптерКогдаНетданных.setViewBinder(БиндингКогдаНетДАнных);
                            АдаптерКогдаНетданных.notifyDataSetChanged();
                            holder.listViewДляЦФО.removeAllViewsInLayout();
                            holder.listViewДляЦФО.startAnimation(animation);
                            holder.listViewДляЦФО.setSelection(0);
                            holder.listViewДляЦФО.setAdapter(АдаптерКогдаНетданных);
                            holder.listViewДляЦФО.refreshDrawableState();
                            holder.listViewДляЦФО.requestLayout();
                            // TODO: 19.04.2023 слушаелти

                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }






                    private void методКликПоЦФО() {
                        holder.listViewДляЦФО.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                try{
                                    MaterialTextView materialTextViewЭлементСписка=    (MaterialTextView)       parent.getAdapter().getView(position, view,parent );

                                    if (materialTextViewЭлементСписка!=null) {
                                        //MaterialTextView materialTextViewЭлементСписка=(MaterialTextView) view;
                                        materialTextViewЭлементСписка.startAnimation(animation);
                                        Bundle bundle=(Bundle)   ((MaterialTextView)view).getTag();
                                        // TODO: 22.01.2025
                                        String NewMaterialЦФО = null;
                                        Long NewMaterialUUID = null;

                                        if (materialTextТекущийВыбранныйСправочник.getId()==  holder.textViewcfo.getId()) {
                                             NewMaterialЦФО=   bundle.getString("NewMaterialЦФО","");
                                             NewMaterialUUID =   bundle.getLong("NewMaterialUUID",0l);
                                        }
                                        if (materialTextТекущийВыбранныйСправочник.getId()==  holder. marerialtextgroupmaterial.getId()) {
                                            NewMaterialЦФО=   bundle.getString("groupNewMaterialЦФО","");
                                            NewMaterialUUID =   bundle.getLong("oneNewMaterialUUID",0l);
                                        }
                                        if (materialTextТекущийВыбранныйСправочник.getId()==  holder.materialtext_onematerial_ves.getId()) {
                                            NewMaterialЦФО=   bundle.getString("oneNewMaterialЦФО","");
                                            NewMaterialUUID =   bundle.getLong("oneNewMaterialUUID",0l);
                                        }

                                        if (materialTextТекущийВыбранныйСправочник.getId()==  holder.valueavtomobil.getId()) {
                                            NewMaterialЦФО=   bundle.getString("NewMaterialAvto","");
                                            NewMaterialUUID =   bundle.getLong("NewMaterialAvtoUUID",0l);
                                        }

                                        if (materialTextТекущийВыбранныйСправочник.getId()==  holder.valuekontragent.getId()) {
                                            NewMaterialЦФО=   bundle.getString("NewMaterialKontragent","");
                                            NewMaterialUUID =   bundle.getLong("NewMaterialKontragentUUID",0l);
                                        }



                                        Log.d(getContext().getClass().getName(), "\n"
                                                + " BootCompletedReceiver sous .... bremy: " + new Date()+"\n+" +
                                                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+
                                                " NewMaterialЦФО" +NewMaterialЦФО  + " NewMaterialUUID " +NewMaterialUUID+"\n"
                                                + " materialTextТекущийВыбранныйСправочник.getId()  " +materialTextТекущийВыбранныйСправочник.getId());


                                        if (NewMaterialUUID>0) {
                                            materialTextТекущийВыбранныйСправочник.setTag(bundle);
                                            materialTextТекущийВыбранныйСправочник.setTextSize(12l);
                                            materialTextТекущийВыбранныйСправочник.setText(NewMaterialЦФО);
                                            materialTextТекущийВыбранныйСправочник.setTooltipText(NewMaterialUUID.toString());
                                            materialTextТекущийВыбранныйСправочник.refreshDrawableState();
                                            materialTextТекущийВыбранныйСправочник.forceLayout();
                                        }

                                        if (    materialTextТекущийВыбранныйСправочник.getText().toString().length()==0) {
                                            Snackbar.make(view, " Вы не выбрали цфо !!! "
                                                    , Snackbar.LENGTH_LONG).show();
                                            materialTextТекущийВыбранныйСправочник.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                                            materialTextТекущийВыбранныйСправочник.setTextColor(Color.GRAY);
                                            Log.d(this.getClass().getName()," bundle.keySet().size() "+bundle.keySet().size());
                                        } else {
                                            materialTextТекущийВыбранныйСправочник.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                                            materialTextТекущийВыбранныйСправочник.setTextColor(Color.BLACK);
                                            // TODO: 24.07.2023  закрытие Новго Создание РИсунков и или Upload Image
                                            subClassCreateNewImageForMateril.  методЗакрытиеNewCreateIMAGE(holder.alertDialog);
                                            Log.d(this.getClass().getName()," bundle.keySet().size() "+bundle.keySet().size());
                                        }
                                        Log.d(getContext().getClass().getName(), "\n"
                                                + " BootCompletedReceiver sous .... bremy: " + new Date()+"\n+" +
                                                " Класс в процессе... " +  this.getClass().getName()+"\n"+
                                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+
                                                " position" +position);
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    new RecordNewErros(getContext()).recordnewerror(e.toString(),
                                            this.getClass().getName(),
                                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                }
                            }

                        });
                    }


                    void методGetCursorForQuertyFilter(CharSequence constraint) {
                        try{
                            if(materialTextТекущийВыбранныйСправочник.getId()== holder.materialtext_onematerial_ves.getId()) {
                                Bundle bundleДляПосика = (Bundle)  holder.marerialtextgroupmaterial.getTag();
                                Integer ВытаскиваемIDГруппыМатериаловДляПоследующегоПоиска = bundleДляПосика.getInt("ПолучаемIDЦфо");
                                holder.cursorДляВсехМатериалов = (Cursor)
                                        МетодДляНовогоТабеляПолучаемДанныеИзПосикаТОлькоДляОдногоМатериалаБывшейВесовой(ТаблицаДляФильтра, constraint.toString(),
                                                ВытаскиваемIDГруппыМатериаловДляПоследующегоПоиска);
                            }else if (materialTextТекущийВыбранныйСправочник.getId()== holder.valueavtomobil.getId()){
                                Log.d(this.getClass().getName(), "   holder. valueavtomobil.getId() " +  holder. valueavtomobil.getId());
                                holder.cursorДляВсехМатериалов = (Cursor)
                                        МетодДляНовогоТабеляПолучаемДанныеИзПосикаТОлькоДляОдногоМатериалаБывшейВесовой(ТаблицаДляФильтра, constraint.toString(), 0);

                            }else if (materialTextТекущийВыбранныйСправочник.getId()==   holder.valuekontragent.getId()){
                                holder.cursorДляВсехМатериалов = (Cursor)
                                        МетодДляНовогоТабеляПолучаемДанныеИзПосикаТОлькоДляОдногоМатериалаБывшейВесовой(ТаблицаДляФильтра, constraint.toString(), 0);
                            }else{
                                holder.cursorДляВсехМатериалов = (Cursor)
                                        МетодДляНовогоТабеляПолучаемДанныеИзПосикаТОлькоДляОдногоМатериалаБывшейВесовой(ТаблицаДляФильтра, constraint.toString(),0);
                            }
                            // TODO: 17.04.2023
                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(getContext()).recordnewerror(e.toString(),
                                    this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }


























                    private void МетодЗакрытиеНовогоПосика(@NonNull  MaterialButton materialButtonЗакрытьДиалог) {
                        materialButtonЗакрытьДиалог.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                try{
                                    Log.d(this.getClass().getName()," position");
                                    Log.d(this.getClass().getName(),"МетодСозданиеТабеля  v "+v);
                                    subClassCreateNewImageForMateril.     методЗакрытиеNewCreateIMAGE(holder.alertDialog);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    new RecordNewErros(v.getContext()).recordnewerror(e.toString(),
                                            this.getClass().getName(),
                                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                }
                            }
                        });
                    }
                });
            }

            // TODO: 19.12.2022  курсор текущий операйции  какой Спинер
            // TODO: 16.12.2022  для Второго Компонета ГРУППА МАТЕРИАЛОВ, после ВЫБЫБОРА ГУРППЫ МАТЕРИАЛОВ ДАЛЕЕ ИНИЦИАЛИЗУЕМ ОДИН МАТЕРИАЛОВ
            // TODO: 16.12.2022  третий вариант для ВЫбора Материала
            // TODO: 19.12.2022 Конец КЛАССА    SubClassNewFilterSFOНовыйФильтДанных  , НОВЫЙ ФИЛЬТР
        }


        // TODO: 19.12.2022 Конец КЛАССА    SubClassNewFilterSFOНовыйФильтДанных  , НОВЫЙ ФИЛЬТР
    }










    private void МетодКпопкиЗначков()
            throws ExecutionException, InterruptedException {
        try {
            bottomNavigationView.getOrCreateBadge(R.id.id_async).setNumber(0);//.getOrCreateBadge(R.id.id_taskHome).setVisible(true);
            bottomNavigationView.getOrCreateBadge(R.id.id_async).setBackgroundColor(Color.RED);
            //TODO
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
    private void МетодПерегрузкаRecyceView() {
        try {
            bottomNavigationView.requestLayout();
            recyclerView.requestLayout();
            recyclerView.refreshDrawableState();
            bottomNavigationView.refreshDrawableState();
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

    // TODO: 02.08.2022

        void  методGetDateForNewOrder( @NonNull      TextView textipputmaretialttdata){
            try{
                final String[] FullNameCFO = new String[1];
                final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd", new Locale("ru"));
                Calendar newDate = Calendar.getInstance();
                //TODODATA
                if (ДатаДляКалендаря==null) {
                    ДатаДляКалендаря =
                            new DatePickerDialog(getContext(), android.R.style.Theme_Holo_InputMethod , new DatePickerDialog.OnDateSetListener() {////Theme_Holo_Dialog_MinWidth  //Theme_Holo_Panel
                                public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                    newDate.set(year, monthOfYear, dayOfMonth);
                                    try {
                                        //  String ДатаДляНовогоЗаказаТраспорта= DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(newDate.getTime());
                                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd,MMM yyyy" , new Locale("ru"));
                                        SimpleDateFormat simpleDateFormatBungle = new SimpleDateFormat("yyyy-MM-dd" , new Locale("ru"));
                                        Date ДатаВыбранаяПользователь= newDate.getTime();
                                        // TODO: 09.06.2023
                                        Calendar myCal = new GregorianCalendar();
                                        Date ДатаСейчасСитемная= myCal.getTime();
                                        // TODO: 09.06.2023  проверяем что выбрана дата старше чем сейчас
                                        if (        ДатаВыбранаяПользователь.after(ДатаСейчасСитемная)) {
                                            String    ДатаДляНовогоЗаказаТраспорта = simpleDateFormat.format(ДатаВыбранаяПользователь);
                                            String    ДатаДляНовогоЗаказаТраспортаBungle = simpleDateFormatBungle.format(ДатаВыбранаяПользователь);
                                            // TODO: 16.05.2023 дата
                                            методЗаписиНовуюДату(ДатаДляНовогоЗаказаТраспорта, textipputmaretialttdata,ДатаДляНовогоЗаказаТраспортаBungle);
                                            Log.d(getContext() .getClass().getName(), "\n"
                                                    + " время: " + new Date()+"\n+" +
                                                    " Класс в процессе... " +   getContext().getClass().getName()+"\n"+
                                                    " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()+" ДатаДляНовогоЗаказаТраспорта " +ДатаДляНовогоЗаказаТраспорта);
                                        }

                                        Log.d(getContext() .getClass().getName(), "\n"
                                                + " время: " + new Date()+"\n+" +
                                                " Класс в процессе... " +   getContext().getClass().getName()+"\n"+
                                                " метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName());
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                        new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    }
                                }

                            }, newDate.get(Calendar.YEAR), newDate.get(Calendar.MONTH), newDate.get(Calendar.DAY_OF_MONTH) +1);
                }
                ДатаДляКалендаря.setTitle("Календарь");
                ДатаДляКалендаря.setButton(DatePickerDialog.BUTTON_POSITIVE, "Создать", ДатаДляКалендаря);
                ДатаДляКалендаря.setButton(DatePickerDialog.BUTTON_NEGATIVE, "Закрыть", ДатаДляКалендаря);
                if (!ДатаДляКалендаря.isShowing()) {
                    ДатаДляКалендаря .show();
                }
                ДатаДляКалендаря.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
                ДатаДляКалендаря.getButton(DatePickerDialog.BUTTON_NEGATIVE).setBackgroundColor(Color.WHITE);
                ДатаДляКалендаря.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
                ДатаДляКалендаря.getButton(DatePickerDialog.BUTTON_POSITIVE).setBackgroundColor(Color.WHITE);
                ////

                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }

        }

        private void методЗаписиНовуюДату(String ДатаДляНовогоЗаказаТраспорта,
                                          @NonNull TextView textipputmaretialttdata,
                                          @NonNull  String ДатаДляНовогоЗаказаТраспортаBungle) {
            try{
                Bundle bundle=new Bundle();
                bundle.putString("GetDateOrder",ДатаДляНовогоЗаказаТраспортаBungle.trim());
                textipputmaretialttdata.setTag(bundle);
                textipputmaretialttdata.setText(ДатаДляНовогоЗаказаТраспорта.toString());
                textipputmaretialttdata.refreshDrawableState();
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }














    // TODO: 20.07.2023 класс создание нового создане нового Image
    public class SubClassCreateNewImageForMateril{
        // TODO: 19.07.2023  первый класс создание нового изображения
        public class SubClassUploadImageAnFragmentImage {
            MaterialButton  materialButtoтtЗакрываемСозданиеImage;
            protected   void методЗагрузкиИлиСозданиеImage(@NonNull MyViewHolder holder) {
                // TODO: 16.12.2022
                        try{
                            Animation   animation1= AnimationUtils.loadAnimation(getContext(),R.anim.slide_in_row_newscanner1);
                            // TODO: 20.12.2022  оперделяем какой вид загружать когда выбран и когда не выбрана гурппа метарилов
                            MaterialAlertDialogBuilder materialAlertDialogBuilderКамера  = new MaterialAlertDialogBuilder(getContext()){
                                @NonNull
                                @Override
                                public MaterialAlertDialogBuilder setView(View view) {
                                    try{
                                        // TODO: 14.12.2022
                                        GridView gridViewImage =    (GridView) view.findViewById(R.id.listView_create_image);
                                        // TODO: 26.07.2023 Клик по закрытие  
                                         materialButtoтtЗакрываемСозданиеImage =    (MaterialButton) view.findViewById(R.id.bottom_close_create_image);
                                        // TODO: 26.07.2023
                                        ArrayList<HashMap<String, Object>> ЛистДляСозданиеРисунки= new ArrayList<HashMap<String, Object>> ();
                                        HashMap<String, Object> map = new HashMap<>();
                                        map.put("alldonttbels", "Не создано !!!");
                                        map.put("allimage", " dont");
                                        ЛистДляСозданиеРисунки.add(map);
                                        SimpleAdapter simpleAdapterNewImage = new SimpleAdapter(getContext(),
                                                ЛистДляСозданиеРисунки,
                                                R.layout.list_item_all_for_create_image_for_material,
                                                new String[]{"alldonttbels","allimage"},
                                                new int[]{android.R.id.text2,android.R.id.text1});

                                        SimpleAdapter.ViewBinder БиндингКогдаНетДАнных = new SimpleAdapter.ViewBinder() {
                                            @Override
                                            public boolean setViewValue(View view, Object data, String textRepresentation) {
                                                try{
                                                    switch (view.getId()) {
                                                        case android.R.id.text1:
                                                            LinearLayout    linearLayoutCreateNewImageТранспорта = (LinearLayout) view.findViewById(android.R.id.text1);
                                                            // TODO: 23.07.2023  загружаем уже готовую фотографию , созданое зарание
                                                            AppCompatImageButton appCompatImageButtonUpImage=
                                                                    linearLayoutCreateNewImageТранспорта.findViewById(R.id.appcompatimagebutton_up);
                                                            // TODO: 27.07.2023 Клик по данным сделать Up загрузить уже созданую фотографию
                                                            методКликПоДаннымUpImage(appCompatImageButtonUpImage);

                                                         // TODO: 23.07.2023  Создание НОВОЙ  SIMPLE ФОТОГРАФИИ
                                                            AppCompatImageButton appcompatimagebutton_simple_create=
                                                                    linearLayoutCreateNewImageТранспорта.findViewById(R.id.appcompatimagebutton_simple_create);
                                                            // TODO: 27.07.2023 клик для создание новой фотографии 
                                                            методКликПоДаннымCreateSimpleImage(appcompatimagebutton_simple_create);
                                                            методLongКликПоДаннымCreateSimpleImage(appcompatimagebutton_simple_create);
                                                            // TODO: 20.07.2023 методы после создание выбора
                                                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n");
                                                            return  true;
                                                    }
                                                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" + " MainParentUUID " );
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                                    new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                                                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                                }
                                                return false;
                                            }
                                        };
                                        simpleAdapterNewImage.setViewBinder(БиндингКогдаНетДАнных);
                                        simpleAdapterNewImage.notifyDataSetChanged();
                                        gridViewImage.setAdapter(simpleAdapterNewImage);
                                        gridViewImage.refreshDrawableState();
                                        gridViewImage.requestLayout();
                                        simpleAdapterNewImage.registerDataSetObserver(new DataSetObserver() {
                                            @Override
                                            public void onChanged() {
                                                super.onChanged();
                                                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" + " MainParentUUID " );
                                            }

                                            @Override
                                            public void onInvalidated() {
                                                super.onInvalidated();
                                                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" + " MainParentUUID " );
                                            }
                                        });
                                        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" + " MainParentUUID " );
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                                " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                        new RecordNewErros(getContext()).recordnewerror(e.toString(),
                                                this.getClass().getName(),
                                                Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    }

                                    return super.setView(view);
                                    // TODO: 20.12.2022  тут конец выбеленого
                                }


                            }
                                    .setTitle("Новые фотографии")
                                    .setCancelable(false)
                                    .setIcon( R.drawable.icon_for_create_image2)
                                    .setView(getLayoutInflater().inflate( R.layout.simple_for_new_search_create_image, null ));

                            // TODO: 31.07.2023  запускаем
                            if(alertDialogCreateImage==null){
                                alertDialogCreateImage=    materialAlertDialogBuilderКамера.show();
                            }else{
                                if(!alertDialogCreateImage.isShowing()){
                                    alertDialogCreateImage=    materialAlertDialogBuilderКамера.show();
                                }
                            }

                            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                            layoutParams.copyFrom(   alertDialogCreateImage.getWindow().getAttributes());
                            layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
                            layoutParams.height =WindowManager.LayoutParams.WRAP_CONTENT;
                         /*   layoutParams.width = 600;
                            layoutParams.height =1000;*/
                            layoutParams.gravity = Gravity.CENTER;
                            alertDialogCreateImage.getWindow().setAttributes(layoutParams);

                            // TODO: 20.07.2023 слушатели  закрытие
                            методЗакрываемСозданиеИлиUpIamge(materialButtoтtЗакрываемСозданиеImage, alertDialogCreateImage);

                            // TODO: 13.12.2022 ВТОРОЙ СЛУШАТЕЛЬ НА КНОПКУ

                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                    +" binderДляПолучениеМатериалов "+ binderДляПолучениеМатериалов);
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

            private void методКликПоДаннымUpImage(@NonNull AppCompatImageButton appCompatImageButton) {
                // TODO: 20.07.2023
                appCompatImageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try{
                            if (v!=null) {
                                //MaterialTextView materialTextViewЭлементСписка=(MaterialTextView) view;
                                SubClassUploadImageFromSDCars imageFromSDCars=new SubClassUploadImageFromSDCars();
                                imageFromSDCars.методUploadImetImage();
                                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                        "materialTextViewЭлементСписка"+  v);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(getContext()).recordnewerror(e.toString(),
                                    this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }
                });

            }

            // TODO: 21.07.2023
            private void методКликПоДаннымCreateSimpleImage(@NonNull AppCompatImageButton appCompatImageButton) {
                // TODO: 20.07.2023
                appCompatImageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try{
                            if (v!=null) {
                                //MaterialTextView materialTextViewЭлементСписка=(MaterialTextView) view;
                                SubClassUploadImageFromSDCars imageFromSDCars=new SubClassUploadImageFromSDCars();
                                imageFromSDCars.методSimpleCreateImage();
                                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                        "materialTextViewЭлементСписка"+  v);
                                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                        "materialTextViewЭлементСписка"+  v);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(getContext()).recordnewerror(e.toString(),
                                    this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }
                });

            }
            // TODO: 27.07.2023 Long Clcikc 
            private void методLongКликПоДаннымCreateSimpleImage(@NonNull AppCompatImageButton appCompatImageButton) {
                // TODO: 20.07.2023
                appCompatImageButton.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        try{
                            if (v!=null) {
                                //MaterialTextView materialTextViewЭлементСписка=(MaterialTextView) view;
                                SubClassUploadImageFromSDCars imageFromSDCars=new SubClassUploadImageFromSDCars();
                                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                        "materialTextViewЭлементСписка"+  v);
                                Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                        " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                        " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"+
                                        "materialTextViewЭлементСписка"+  v);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(getContext()).recordnewerror(e.toString(),
                                    this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                        return true;
                    }
                });

            }

// TODO: 20.07.2023 при созданнии или Upload Image
                    private void методЗакрываемСозданиеИлиUpIamge(@NonNull  MaterialButton materialButtonЗакрытьДиалог,
                                                                   @NonNull AlertDialog alertDialogCreateImage ) {
                        materialButtonЗакрытьДиалог.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                try{
                                    Log.d(this.getClass().getName()," Image Update and Create Image");
                                    методЗакрытиеNewCreateIMAGE(alertDialogCreateImage);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                                    new RecordNewErros(v.getContext()).recordnewerror(e.toString(),
                                            this.getClass().getName(),
                                            Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
                                }
                            }
                        });
                    }

            }  //todo END SubClassUploadImageAnFragmentImage













        // TODO: 20.07.2023 класс ЗагрузкиImage из Хранилишща Телефона
        class SubClassUploadImageFromSDCars{
            CameraDevice cameraDevice;
      void методUploadImetImage(){
    try{
        // TODO: 24.07.2023  Поднимаем файл из Image уже созданого
        //Intent intentUpgetImage=new Intent(Intent.ACTION_GET_CONTENT);
        Intent intentUpgetImage = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intentUpgetImage.setType("image/*");
        intentUpgetImage.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intentUpgetImage.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION );
        String[] mimeTypes = {"image/jpeg", "image/png"};
        intentUpgetImage.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        Bundle bundleUpImage=new Bundle();
        bundleUpImage.putInt("ResyltatImageCallsBack",500);
        intentUpgetImage.putExtras(bundleUpImage);
        someActivityResultLauncherUpImage.launch(intentUpgetImage);

        // TODO: 20.07.2023
        Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                  " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                  " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
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
// TODO: 21.07.2023 create


           void методSimpleCreateImage(){
                try{
                    // TODO Создание НОвой ФОтографии Image
                    DialogFragment fragmentCamera= FragmentCameraNewPhoto.newInstance();
                    Bundle data=new Bundle();
                    data.putBinder("binder",binderДляПолучениеМатериалов);
                    fragmentCamera.setArguments(data);

                    String fragmentNewImageNameaddToBackStack=   fragmentCamera.getClass().getName();

                    Fragment    FragmentУжеЕСтьИлиНЕт=     fragmentManager.findFragmentByTag(fragmentNewImageNameaddToBackStack);


                    if (FragmentУжеЕСтьИлиНЕт==null) {
                        fragmentTransaction.addToBackStack(fragmentNewImageNameaddToBackStack);

                        fragmentCamera.show(getActivity().getSupportFragmentManager(), "FragmentCameraNewPhoto");

                        // TODO: 01.08.2023
                        if (   alertDialogCreateImage.isShowing()) {
                            alertDialogCreateImage.dismiss();
                            alertDialogCreateImage.cancel();
                        }
                    }

                    Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                            " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                            " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                            + " binderДляПолучениеМатериалов " +binderДляПолучениеМатериалов );

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
            // TODO: 26.07.2023
        }

        // TODO: 24.07.2023  Класс Обработываем Полученый Созданный Новый Рисунок или Загружаем Уже Существуеший









        private void методЗакрытиеNewCreateIMAGE(@NonNull AlertDialog alertDialogCreateImage ) {
            try{
                alertDialogCreateImage.dismiss();
                alertDialogCreateImage.cancel();
                Log.d(getContext().getClass().getName(), "\n" + " alertDialogCreateImage "
                        + alertDialogCreateImage );
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                        " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext() ).recordnewerror(e.toString(),
                        this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }



        private void методGetDataForNewFragment() {
            try{
                asyncTaskLoaderForNewMaterial =new AsyncTaskLoader(getContext()) {
                    @Nullable
                    @Override
                    public Cursor loadInBackground() {
                        Cursor       CursorДляЦФО=null;
                        try{
                            Intent intentДляПолучениеСправочкинов=new Intent("НовыеМатериалыПолучениеСправочников");
                            // TODO: 20.10.2022 #1
                            CursorДляЦФО=   МетодПолучениеДанныхДляЦФО(intentДляПолучениеСправочкинов);
                            // TODO: 20.10.2022 #3
                            CursorДляГруппаМатериалов=      МетодПолучениеДляГруппыМатериалов(intentДляПолучениеСправочкинов);


                            CursorДляОдногоМатериалаБышВесов=       МетоПолучениеДанныхДляОдногоМатериала(intentДляПолучениеСправочкинов,0l);

                            // TODO: 20.10.2022 #5 автомобили
                            CursorДляАвтомобиля=        МетоПолучениеДанныхДляАвтомобилей(intentДляПолучениеСправочкинов, "");
                            // TODO: 20.10.2022 #6 контргаенты
                            CursorДляКонтрагента=        МетоПолучениеДанныхДляКонтрагент(intentДляПолучениеСправочкинов, "");

                            Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                                    + " CursorДляЦФО " +CursorДляЦФО
                                    + " CursorДляГруппаМатериалов " +CursorДляГруппаМатериалов+
                                    " CursorДляАвтомобиля " +CursorДляАвтомобиля+
                                    " CursorДляКонтрагента " +CursorДляКонтрагента);

                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(getContext().getClass().getName(),
                                    "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                                            " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(getContext()).recordnewerror(e.toString(),
                                    this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                        return CursorДляЦФО;
                    }

                };
                asyncTaskLoaderForNewMaterial.deliverResult(CursorДляЦФО);
                asyncTaskLoaderForNewMaterial.forceLoad();
                asyncTaskLoaderForNewMaterial.registerListener(new Random().nextInt(), new Loader.OnLoadCompleteListener() {
                    @Override
                    public void onLoadComplete(@NonNull Loader loader, @Nullable Object data) {
                        try{
                            // TODO: 26.07.2023  после получение данных
                            CursorДляЦФО= (Cursor) data;
                            onStart();
                            progressBarСозданиеМатерила.setVisibility(View.GONE);
                            Log.d(this.getClass().getName(), "  onCreate  CursorДляЦФО    "+CursorДляЦФО);
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
                });
                Log.d(this.getClass().getName(), "  onCreate  FragmentCreateAdmissionmaterialbinder    "+binderДляПолучениеМатериалов);
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


        private void методБиндингСлужбыTakePhoto() {
            try {
                Intent intentЗапускБиндингаtajePhoto = new Intent(getContext(), ServiceCameraTake.class);
                intentЗапускБиндингаtajePhoto.setAction("com.ServiceCameraTake");
                serviceConnectionMaterialNew=     new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        try {
                            if (service.isBinderAlive()) {
                                // TODO: 30.06.2023
                                localBinderCamera = (ServiceCameraTake.LocalBinderCamera) service;

                                Log.d(getContext().getClass().getName(), "\n"
                                        + " время: " + new Date() + "\n+" +
                                        " Класс в процессе... " + this.getClass().getName() + "\n" +
                                        " onServiceConnected  метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                        + "    onServiceDisconnected  ServiceForAdminissionMaterial" + " service "
                                        + service.isBinderAlive());

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }

                    @Override
                    public void onServiceDisconnected(ComponentName name) {
                        try {
                            Log.d(getContext().getClass().getName(), "\n"
                                    + " время: " + new Date() + "\n+" +
                                    " Класс в процессе... " + this.getClass().getName() + "\n" +
                                    "  onServiceDisconnected метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
                                    + "    onServiceDisconnected  bibinderСогласованияbinderМатериалыnder");
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                                    + Thread.currentThread().getStackTrace()[2].getLineNumber());
                            new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                                    Thread.currentThread().getStackTrace()[2].getMethodName(),
                                    Thread.currentThread().getStackTrace()[2].getLineNumber());
                        }
                    }
                };
                getActivity().  bindService(intentЗапускБиндингаtajePhoto,serviceConnectionMaterialNew , Context.BIND_AUTO_CREATE);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
                        + Thread.currentThread().getStackTrace()[2].getLineNumber());
                new RecordNewErros(getContext()).recordnewerror(e.toString(), this.getClass().getName(),
                        Thread.currentThread().getStackTrace()[2].getMethodName(),
                        Thread.currentThread().getStackTrace()[2].getLineNumber());
            }
        }

    }//todo END SubClassCreateNewImageForMateril

    // TODO: 25.07.2023


    // TODO: 27.07.2023  New Class Camera2 Take Photo Test
    class ClassCAmera2TakePhoto{

       void методCameraTakePhoto(){
           try{

           Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                   " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                   " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
       } catch (Exception e) {
            e.printStackTrace();
            Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
                    " Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
            new RecordNewErros(getContext() ).recordnewerror(e.toString(),
                    this.getClass().getName(),
                    Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
        }
       }






    }
}
