package com.dsy.dsu.TabelsApp.Tabel.New;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Filter;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cursoradapter.widget.CursorAdapter;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.loader.content.AsyncTaskLoader;


import com.dsy.dsu.BusinessLogicForApps.CoreBinessLogic.CoreBinessLogics;

import com.dsy.dsu.BusinessLogicForApps.Dates.GetMainDateForApp;
import com.dsy.dsu.BusinessLogicForApps.GetPublicID.GetttingPublicID;
import com.dsy.dsu.BusinessLogicForApps.VersionCurentTable;
import com.dsy.dsu.CoreApp.Apps.ErrorsCoreApp.model.bl_readnewerrors.RecordNewErros;
import com.dsy.dsu.BusinessLogicForApps.GreatUuidGenerations.GreatUuidGeneration;
import com.dsy.dsu.BusinessLogicForApps.Services.ServiceForAdminissionMaterial;
import com.dsy.dsu.R;
import com.dsy.dsu.TabelsApp.Tabel.ListTabels.MainActivity_List_Tabels;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textview.MaterialTextView;
import com.sous.backasync.launch.ModuleQuety;

import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
//класс активити MainActivityNewTabels
public class MainActivityNewTabels extends AppCompatActivity {
	private MaterialTextView СпинерВыборЦФО,СпинерВыборДата;
	private MaterialButton КнопкаСозданиеТабеля;
	private  MaterialButton buttonbackarrow;
	private  ServiceForAdminissionMaterial.LocalBinderДляПолучениеМатериалов binderДляПолучениеМатериалов;
	private   Cursor CursorДляСпиноровЦФО;
	private  Handler handler;
	private AsyncTaskLoader<Cursor>asyncTaskLoader;
	private SharedPreferences preferences;
	private Boolean ФдагЧтоУжеОдинРАзБылПервыйПроход=false;
	private Animation animation;
	 private  Integer getNewDayOfMonth;
	 private  Integer getNewYear;
	 private  Integer getNewMonth;
	// TODO: 15.12.2022 методы
	@SuppressLint("MissingInflatedId")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try {
				super.onCreate(savedInstanceState);
			//setContentView(R.layout.activitymain_createtablesavehistory);
			setContentView(R.layout.activitymain_createnewtabel);
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
			getSupportActionBar().hide(); ///скрывать тул бар
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
					| WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
					| WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
			getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
			///todo кнопака создание нового пустого табеля без сотрудников
			КнопкаСозданиеТабеля = findViewById(R.id.КнопкаСозданиеНовогоТабеля);
			СпинерВыборЦФО=findViewById(R.id.materialspiritnew_tabel);
			buttonbackarrow =findViewById(R.id.buttonbackarrow);
			СпинерВыборДата=(MaterialTextView) findViewById(R.id.ЗначениеДатаСоздаваемогоТабеля);////СЮДА ДАТА ПРИШЛА ОТ ДРУГОВВА АКТИВ
			preferences = getSharedPreferences("sharedPreferencesХранилище", Context.MODE_MULTI_PROCESS);
			animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_row_newscanner1);
			// TODO: 01.11.2022 методы до начало запуска







			МетодHandlerCallBack();
			МетодСозданиеКодBACK();
			методБиндингСлужбы();

			МетодПришлиПеременныеИзMainActivityListtabel();
			// TODO: 23.06.2025

			Log.d(getApplicationContext().getClass().getName(), "\n"
					+ " время: " + new Date() + "\n+" +
					" Класс в процессе... " + this.getClass().getName() + "\n" +
					" метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()  );
		} catch (Exception e) {
			e.printStackTrace();
			Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
					" Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
			new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
					this.getClass().getName(),
					Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
		}
	}

void  МетодПришлиПеременныеИзMainActivityListtabel(){
		try{
			Intent Интент_FromMainActivityTaberl = getIntent();
			Bundle     bundleСозданиеНовогоТабеля=Интент_FromMainActivityTaberl.getExtras();
			getNewDayOfMonth =  bundleСозданиеНовогоТабеля.getInt("getNewDayOfMonth", 0);
			getNewYear =    bundleСозданиеНовогоТабеля.getInt("getNewYear",0 );
			getNewMonth =  bundleСозданиеНовогоТабеля.getInt("getNewMonth", 0);

			Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
					" metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
					" line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
					+ " bundleСозданиеНовогоТабеля "+bundleСозданиеНовогоТабеля);
		} catch (Exception e) {
		e.printStackTrace();
		Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
				" Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
		new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
				this.getClass().getName(),
				Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());

	}
	}


	String  selectedDateActivityProcessCreatingTabel(){
		// TODO: 23.06.2025
		String  getSelectedDateProcess=new String();
		try{
			Date date = new SimpleDateFormat("yyyy-MM-dd")
					.parse(""+getNewYear.toString()+"-"+getNewMonth.toString()+"-"+getNewDayOfMonth.toString());

			getSelectedDateProcess=  new SimpleDateFormat("LLLL yyyy", new Locale("ru")).format(date);
			StringBuffer stringBufferNewDate=new StringBuffer(getSelectedDateProcess);

			getSelectedDateProcess=     stringBufferNewDate.substring(0,1).toUpperCase()+stringBufferNewDate.substring(1,stringBufferNewDate.length()).toLowerCase();

			Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
					" metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
					" line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " getSelectedDateProcess " +getSelectedDateProcess);
		} catch (Exception e) {
			e.printStackTrace();
			Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
					" Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
			new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
					this.getClass().getName(),
					Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());

		}
		return  getSelectedDateProcess;
	}







	private void методБиндингСлужбы() {
		try {
			ServiceConnection serviceConnectionМатериалы = new ServiceConnection() {
				@Override
				public void onServiceConnected(ComponentName name, IBinder service) {
					try {
						if (service.isBinderAlive()) {
							binderДляПолучениеМатериалов = (ServiceForAdminissionMaterial.LocalBinderДляПолучениеМатериалов) service;
							Log.d(getApplicationContext().getClass().getName(), "\n"
									+ " время: " + new Date() + "\n+" +
									" Класс в процессе... " + this.getClass().getName() + "\n" +
									" onServiceConnected  метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
									+ "    onServiceDisconnected  ServiceForAdminissionMaterial" + " binderДляПолучениеМатериалов "
									+ binderДляПолучениеМатериалов.isBinderAlive());

							onStart();
						}
					} catch (Exception e) {
						e.printStackTrace();
						Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
								+ Thread.currentThread().getStackTrace()[2].getLineNumber());
						new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
								Thread.currentThread().getStackTrace()[2].getMethodName(),
								Thread.currentThread().getStackTrace()[2].getLineNumber());
					}
				}
				@Override
				public void onServiceDisconnected(ComponentName name) {
					try {
						Log.d(getApplicationContext().getClass().getName(), "\n"
								+ " время: " + new Date() + "\n+" +
								" Класс в процессе... " + this.getClass().getName() + "\n" +
								"  onServiceDisconnected метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()
								+ "    onServiceDisconnected  bibinderСогласованияbinderМатериалыnder");
					} catch (Exception e) {
						e.printStackTrace();
						Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
								+ Thread.currentThread().getStackTrace()[2].getLineNumber());
						new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
								Thread.currentThread().getStackTrace()[2].getMethodName(),
								Thread.currentThread().getStackTrace()[2].getLineNumber());
					}
				}
			};
			Intent intentЗапускБиндингаМатериалы = new Intent(getApplicationContext(), ServiceForAdminissionMaterial.class);
			intentЗапускБиндингаМатериалы.setAction("com.ServiceForAdminissionMaterial");
		   bindService(intentЗапускБиндингаМатериалы, serviceConnectionМатериалы, Context.BIND_AUTO_CREATE);
		} catch (Exception e) {
			e.printStackTrace();
			Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
					+ Thread.currentThread().getStackTrace()[2].getLineNumber());
			new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
					Thread.currentThread().getStackTrace()[2].getMethodName(),
					Thread.currentThread().getStackTrace()[2].getLineNumber());
		}
	}


	@Override
	protected void onStart() {
		super.onStart();
		try{
			if (binderДляПолучениеМатериалов!=null) {

				МетодПолучениеМатериала("");

			  new ClassSearchNew().  МетодСпинерЦФО();

				String  getSelectedDateProcess=   selectedDateActivityProcessCreatingTabel();


				МетодСпинерДаты(getSelectedDateProcess);

				МетодСозданиеТабеля();

			}
			Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
					" metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
					" line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
					+ " binderДляПолучениеМатериалов " +binderДляПолучениеМатериалов);
		} catch (Exception e) {
		e.printStackTrace();
		Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
				" Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
		// TODO: 01.09.2021 метод вызова
		new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
				this.getClass().getName(),
				Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
		///////
	}
	}
	@Override
	protected void onResume() {
		super.onResume();
		try{

		} catch (Exception e) {
			e.printStackTrace();
			Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
					" Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
			// TODO: 01.09.2021 метод вызова
			new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
					this.getClass().getName(),
					Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
			///////
		}
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		//////TODO  данный код срабатывает когда произошда ошивка в базе
	}

	@Override
	public void onStop() {
		super.onStop();
		try{
		} catch (Exception e) {
			e.printStackTrace();
			Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
					" Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
			new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
					this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
					Thread.currentThread().getStackTrace()[2].getLineNumber());
		}
	}


	/////todo метод создание BACK
	private void МетодСозданиеКодBACK() {
		try{
			buttonbackarrow.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Log.d(this.getClass().getName(), " кликнем для созданни новго сотрдника при нажатии  ");
					Intent ИнтентВозврящемсяНазад = new Intent();
					ИнтентВозврящемсяНазад.setClass(getApplication(), MainActivity_List_Tabels.class); // Т
					ИнтентВозврящемсяНазад.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(ИнтентВозврящемсяНазад);
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

















































/////TODO Цифроввое имени табеля














	//TODO метод провереям если название табеля в базе
	private boolean МетодПроверяемЕслиТакойНазваниеТабеляВБазеУжеЕсть(@NotNull  Long UUIDНазваниеЦФО,
																	  @NotNull  Integer МесяцДляАнализаиВставкиЕслиТакогоНет,
																	  @NotNull Integer ГодляАнализаиВставкиЕслиТакогоНет) {

		try {
			// TODO: 14.05.2025
			String Текущаятаблицы="tabel";
			ModuleQuety moduleQuety=new ModuleQuety(getApplicationContext());
			Cursor    Курсор_ПроверяемЕслиТакоеНазваниеТабеляУжеЕстьИлиНет=
					moduleQuety.getModuleQuery(Текущаятаблицы," SELECT D.cfo, D.month_tabels ,D.year_tabels FROM "+Текущаятаблицы+" AS D" +
					"  WHERE D.cfo= '"+UUIDНазваниеЦФО +"'" +
							" AND D.month_tabels= '"+МесяцДляАнализаиВставкиЕслиТакогоНет +"'" +
							" AND D.year_tabels= '"+ГодляАнализаиВставкиЕслиТакогоНет+ "'" +
							"   AND D.status_send!='Удаленная'" +
					" ORDER BY   D.date_update DESC   " ,null);

			Log.d(this.getClass().getName(), "\n"
					+ " время: " + new Date() + "\n+" +
					" Класс в процессе... " + this.getClass().getName() + "\n" +
					" метод в процессе... " + Thread.currentThread().getStackTrace()[2].getMethodName()  + " Курсор_ПроверяемЕслиТакоеНазваниеТабеляУжеЕстьИлиНет " +Курсор_ПроверяемЕслиТакоеНазваниеТабеляУжеЕстьИлиНет);
		if (   Курсор_ПроверяемЕслиТакоеНазваниеТабеляУжеЕстьИлиНет.getCount() > 0) {
			Курсор_ПроверяемЕслиТакоеНазваниеТабеляУжеЕстьИлиНет.close();
			return true;
		}else {
			Курсор_ПроверяемЕслиТакоеНазваниеТабеляУжеЕстьИлиНет.close();
			return false;
		}

	} catch (Exception e) {
		e.printStackTrace();
		Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
				" Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
			new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
		  this.getClass().getName(),
				Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
	}
		return false;
	}


// TODO: 11.07.2023
	class  ClassSearchNew {

	private void МетодСпинерЦФО() {

		try {
			СпинерВыборЦФО.setOnClickListener(new View.OnClickListener() {
				private AlertDialog alertDialogNewTabel = null;
				private ListView listViewДляЦФО = null;
				private MaterialButton materialButtonЗакрытьДиалог = null;
				private androidx.appcompat.widget.SearchView searchViewДляНовогоЦФО = null;
				private TextView textViewsearchViewДляНовогоЦФО = null;

				@Override
				public void onClick(View v) {
					try {
						// onStart();
						alertDialogNewTabel = new MaterialAlertDialogBuilder(v.getContext()) {
							@NonNull
							@Override
							public MaterialAlertDialogBuilder setView(View view) {
								listViewДляЦФО = (ListView) view.findViewById(R.id.SearchViewList);
								listViewДляЦФО.setTextFilterEnabled(true);
								// TODO: 14.12.2022
								materialButtonЗакрытьДиалог = (MaterialButton) view.findViewById(R.id.bottom_newscanner1);
								searchViewДляНовогоЦФО = (androidx.appcompat.widget.SearchView) view.findViewById(R.id.searchview_newscanner);
								searchViewДляНовогоЦФО.setQueryHint("Поиск цфо");
								searchViewДляНовогоЦФО.setSubmitButtonEnabled(true);
								searchViewДляНовогоЦФО.setIconified(true);
								// TODO: 14.12.2022

								textViewsearchViewДляНовогоЦФО = searchViewДляНовогоЦФО.findViewById(com.google.android.material.R.id.search_src_text);
								textViewsearchViewДляНовогоЦФО.setTextColor(Color.rgb(0, 102, 102));
								textViewsearchViewДляНовогоЦФО.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
								///TODO ГЛАВНЫЙ АДАПТЕР чата
								SimpleCursorAdapter simpleCursorAdapterЦФО = new SimpleCursorAdapter(v.getContext(),
										R.layout.simple_newspinner_dwonload_newfiltersearch, CursorДляСпиноровЦФО,
										new String[]{"name", "_id"},
										new int[]{android.R.id.text1, android.R.id.text1},
										CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);//R.layout.simple_newspinner_dwonload_cfo2
								SimpleCursorAdapter.ViewBinder БиндингДляЦФО = new SimpleCursorAdapter.ViewBinder() {

									@Override
									public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
										switch (view.getId()) {
											case android.R.id.text1:
												Log.d(this.getClass().getName(), " position");
												if (cursor.getCount() > 0) {
													try {
														MaterialTextView materialTextViewЭлементСписка = ((MaterialTextView) view);
														Integer ИндексНазваниеЦФО = cursor.getColumnIndex("name");///user_update  --old/// uuid
														String НазваниеЦФО = cursor.getString(ИндексНазваниеЦФО).trim();
														// TODO: 13.12.2022  производим состыковку

														if (НазваниеЦФО.length()>2) {
															Integer UUIDНазваниеЦФО = cursor.getColumnIndex("uuid");///user_update  --old/// uuid
															Long UUIDЦФО = cursor.getLong(UUIDНазваниеЦФО);
															Bundle bundle = new Bundle();
															bundle.putString("НазваниеЦФО", НазваниеЦФО);
															bundle.putLong("UUIDНазваниеЦФО", UUIDЦФО);
															materialTextViewЭлементСписка.setTag(bundle);

															Log.d(this.getClass().getName(), " НазваниеЦФО" + НазваниеЦФО +
																	" UUIDНазваниеЦФО " + UUIDНазваниеЦФО);
															// TODO: 17.11.2022  если пользователь уже выбирал
															Boolean ФлагВыбиралУжеЦФОИзСпинера = preferences.getBoolean("ДляСпинераУжеВибиралЦФО", false);
															if (ФлагВыбиралУжеЦФОИзСпинера && ФдагЧтоУжеОдинРАзБылПервыйПроход == false) {
																String НазваниеВыбраногоЦФО = preferences.getString("НазваниеВыбраногоЦФО", "");
																((MaterialTextView) view).setText(НазваниеВыбраногоЦФО);
															}
															ФдагЧтоУжеОдинРАзБылПервыйПроход = true;
															// TODO: 20.01.2022
															Log.d(this.getClass().getName(), " НазваниеЦФО " + НазваниеЦФО);
															boolean ДлинаСтрокивСпиноре = НазваниеЦФО.length() > 40;
															if (ДлинаСтрокивСпиноре) {
																StringBuffer sb = new StringBuffer(НазваниеЦФО);
																sb.insert(40, System.lineSeparator());
																НазваниеЦФО = sb.toString();
																Log.d(getApplicationContext().getClass().getName(), " НазваниеЦФО " + "--" + НазваниеЦФО);/////
															}


															materialTextViewЭлементСписка.setText(НазваниеЦФО);

															materialTextViewЭлементСписка.startAnimation(animation);
															// TODO: 13.12.2022 слушатель
															методКликеПоданнымиЦФО(materialTextViewЭлементСписка);
														}
														// TODO: 13.12.2022 филь
														Log.d(this.getClass().getName(), "  НазваниеЦФО" + НазваниеЦФО);
													} catch (Exception e) {
														e.printStackTrace();
														Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
																" Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
														new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
																this.getClass().getName(),
																Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
													}
													return true;
												} else {
													Log.d(this.getClass().getName(), " position");
													return false;
												}
										}
										return false;
									}

									private void методКликеПоданнымиЦФО(@NonNull MaterialTextView materialTextViewЭлементСписка) {
										try {
											materialTextViewЭлементСписка.setOnClickListener(new View.OnClickListener() {
												@Override
												public void onClick(View v) {
													materialTextViewЭлементСписка.startAnimation(animation);
													Bundle bundle = (Bundle) materialTextViewЭлементСписка.getTag();
													String НазваниеЦФО = bundle.getString("НазваниеЦФО", "");
													Long UUIDНазваниеЦФО = bundle.getLong("UUIDНазваниеЦФО", 0l);
													СпинерВыборЦФО.setTag(bundle);
													СпинерВыборЦФО.setText(НазваниеЦФО);
													СпинерВыборЦФО.setTooltipText(UUIDНазваниеЦФО.toString());
									  /*      searchViewДляНовогоЦФО.setQueryHint(НазваниеЦФО);
											searchViewДляНовогоЦФО.setQuery(НазваниеЦФО,true);*/

													СпинерВыборЦФО.startAnimation(animation);
													alertDialogNewTabel.dismiss();
													alertDialogNewTabel.cancel();

													if (СпинерВыборЦФО.getText().toString().length() == 0) {
														Snackbar.make(view, " Вы не выбрали цфо !!! "
																, Snackbar.LENGTH_LONG).show();
														СпинерВыборЦФО.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
														СпинерВыборЦФО.setTextColor(Color.GRAY);
														Log.d(this.getClass().getName(), " bundle.keySet().size() " + bundle.keySet().size());
													} else {
														СпинерВыборЦФО.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
														СпинерВыборЦФО.setTextColor(Color.BLACK);
														Log.d(this.getClass().getName(), " bundle.keySet().size() " + bundle.keySet().size());
													}
													СпинерВыборЦФО.refreshDrawableState();
													СпинерВыборЦФО.requestLayout();
													Log.d(this.getClass().getName(), " position");
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
								};
								simpleCursorAdapterЦФО.setViewBinder(БиндингДляЦФО);
								simpleCursorAdapterЦФО.notifyDataSetChanged();
								listViewДляЦФО.setAdapter(simpleCursorAdapterЦФО);
								simpleCursorAdapterЦФО.notifyDataSetChanged();
								listViewДляЦФО.setSelection(0);
								listViewДляЦФО.refreshDrawableState();
								listViewДляЦФО.requestLayout();
								// TODO: 13.12.2022  Поиск и его слушель
								МетодПоискаФильтр(searchViewДляНовогоЦФО, simpleCursorAdapterЦФО,listViewДляЦФО);

								Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
										" metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
										" line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " cursorForViewPager.getPosition() ");
								return super.setView(view);
							}
						}
								.setTitle("ЦФО")
								.setCancelable(false)
								.setIcon( R.drawable.icon_dsu1_new_tamples)
								.setView(getLayoutInflater().inflate(R.layout.simple_for_new_spinner_searchview, null))
								.show();
						WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
						layoutParams.copyFrom(alertDialogNewTabel.getWindow().getAttributes());
						layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
						layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
						layoutParams.gravity = Gravity.CENTER;
						alertDialogNewTabel.getWindow().setAttributes(layoutParams);
						alertDialogNewTabel.setTitle("ЦФО "+"("+String.valueOf(CursorДляСпиноровЦФО.getCount()+")"));
						// TODO: 13.12.2022 ВТОРОЙ СЛУШАТЕЛЬ НА КНОПКУ
						// TODO: 11.07.2023
						методЗакрываемПосик(materialButtonЗакрытьДиалог, alertDialogNewTabel);

						Log.d(this.getClass().getName(), "\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
								" metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
								" line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n" + " cursorForViewPager.getPosition() ");
					} catch (Exception e) {
						e.printStackTrace();
						Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
								" Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
						new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
								this.getClass().getName(),
								Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
					}
				}

				private void методЗакрываемПосик(@NonNull MaterialButton materialButtonЗакрытьДиалог, @NonNull AlertDialog alertDialog) {
					materialButtonЗакрытьДиалог.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							try {
								Log.d(this.getClass().getName(), " position");
								Log.d(this.getClass().getName(), "МетодСозданиеТабеля  v " + v);
								СпинерВыборЦФО.setText("");
								СпинерВыборЦФО.refreshDrawableState();
								СпинерВыборЦФО.forceLayout();
								alertDialog.dismiss();
								alertDialog.cancel();

								searchViewДляНовогоЦФО.setQueryHint("Поиск цфо");
								searchViewДляНовогоЦФО.setQuery("", true);
								searchViewДляНовогоЦФО.refreshDrawableState();
								searchViewДляНовогоЦФО.requestLayout();
							} catch (Exception e) {
								e.printStackTrace();
								Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
										" Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
								new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
										this.getClass().getName(),
										Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
							}
						}
					});
				}

				private void МетодПоискаФильтр(@NonNull androidx.appcompat.widget.SearchView searchViewДляНовогоЦФО,
											   @NonNull SimpleCursorAdapter simpleCursorAdapterЦФО,@NonNull ListView listViewДляЦФО ) {
					try {
						searchViewДляНовогоЦФО.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
							@Override
							public boolean onQueryTextSubmit(String query) {
								Log.d(this.getClass().getName(), " position");
								return false;
							}

							@Override
							public boolean onQueryTextChange(String newText) {
								if (newText!=null && newText.length()>0) {
									// TODO: 26.12.2022  конец основгого кода
									Log.d(this.getClass().getName(), " position");
									Filter filter = simpleCursorAdapterЦФО.getFilter();
									filter.filter(newText);
									return true;
								}else {
									return false;
								}
							}
						});
						searchViewДляНовогоЦФО.setOnCloseListener(new SearchView.OnCloseListener() {
							@Override
							public boolean onClose() {
								// TODO: 05.02.2025
								try{
									if (CursorДляСпиноровЦФО!=null) {
										CursorДляСпиноровЦФО=            МетодДляНовогоТабеляПолучаемДанные("cfo","");

										startswapCursor(simpleCursorAdapterЦФО,  listViewДляЦФО, alertDialogNewTabel);
									}

									alertDialogNewTabel.cancel();

									Log.d(this.getClass().getName(), "\n" + " class " +
										Thread.currentThread().getStackTrace()[2].getClassName()
										+ "\n" +
										" metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
										" line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
										+ "    CursorДляСпиноровЦФО " +CursorДляСпиноровЦФО);

						} catch (Exception e) {
							e.printStackTrace();
							Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
									" Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
							new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
									this.getClass().getName(),
									Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
						}
									return false;

							}
						});
						searchViewДляНовогоЦФО.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
							@Override
							public void onFocusChange(View v, boolean hasFocus) {
								Log.d(this.getClass().getName(), "\n" + " class " +
										Thread.currentThread().getStackTrace()[2].getClassName()
										+ "\n" +
										" metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
										" line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
										+ "    CursorДляСпиноровЦФО " +CursorДляСпиноровЦФО);
							}
						});




						simpleCursorAdapterЦФО.setFilterQueryProvider(new FilterQueryProvider() {
							@Override
							public Cursor runQuery(CharSequence constraint) {
								Log.d(this.getClass().getName(), " position");
								try {
									CursorДляСпиноровЦФО = МетодДляНовогоТабеляПолучаемДанныеИзНовогоПоиска("cfo", constraint.toString());
									handler.post(() -> {
										if (CursorДляСпиноровЦФО.getCount()>0 && constraint!=null) {

											startswapCursor(simpleCursorAdapterЦФО,listViewДляЦФО, alertDialogNewTabel);

												searchViewДляНовогоЦФО.setBackgroundColor(Color.parseColor("#F2F5F5"));


												Log.d(this.getClass().getName(), "\n" + " class " +
														Thread.currentThread().getStackTrace()[2].getClassName()
														+ "\n" +
														" metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
														" line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
														+ "    CursorДляСпиноровЦФО " +CursorДляСпиноровЦФО);

										}else {


											searchViewДляНовогоЦФО.setBackgroundColor(Color.RED);
											startswapCursor(simpleCursorAdapterЦФО ,listViewДляЦФО, alertDialogNewTabel);

										handler.postDelayed(() -> {
											searchViewДляНовогоЦФО.setBackgroundColor(Color.parseColor("#F2F5F5"));

											CursorДляСпиноровЦФО=            МетодДляНовогоТабеляПолучаемДанные("cfo","");
											startswapCursor(simpleCursorAdapterЦФО ,listViewДляЦФО, alertDialogNewTabel);
											Log.d(this.getClass().getName(), "\n" + " class " +
													Thread.currentThread().getStackTrace()[2].getClassName()
													+ "\n" +
													" metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
													" line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
													+ "    CursorДляСпиноровЦФО " +CursorДляСпиноровЦФО);
										}, 500);
										}
										Log.d(this.getClass().getName(), "\n" + " class " +
												Thread.currentThread().getStackTrace()[2].getClassName()
												+ "\n" +
												" metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
												" line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
												+ "    CursorДляСпиноровЦФО " +CursorДляСпиноровЦФО);
									});
								} catch (Exception e) {
									e.printStackTrace();
									Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
											" Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
									new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
											this.getClass().getName(),
											Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
								}
								return CursorДляСпиноровЦФО;
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




	private void startswapCursor(@NonNull SimpleCursorAdapter simpleCursorAdapterЦФО,@NonNull ListView listViewДляЦФО,@NonNull AlertDialog alertDialog) {
		try{
		simpleCursorAdapterЦФО.swapCursor(CursorДляСпиноровЦФО);
		simpleCursorAdapterЦФО.notifyDataSetChanged();
		listViewДляЦФО.setSelection(0);
			alertDialog.setTitle("ЦФО "+"("+String.valueOf(CursorДляСпиноровЦФО.getCount()+")"));
			Log.d(this.getClass().getName(), "\n" + " class " +
					Thread.currentThread().getStackTrace()[2].getClassName()
					+ "\n" +
					" metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
					" line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
					+ "    CursorДляСпиноровЦФО " +CursorДляСпиноровЦФО);

	} catch (Exception e) {
		e.printStackTrace();
		Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
				" Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
		new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
				this.getClass().getName(),
				Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
	}
	}


} // TODO: 11.07.2023  NEW SEaerch  CLASS

	private void МетодСпинерДаты(@NonNull  String  getSelectedDateProcess) {
		try{
			СпинерВыборДата.setText(getSelectedDateProcess);
			СпинерВыборДата.forceLayout();
			СпинерВыборДата.refreshDrawableState();
			Log.d(this.getClass().getName(), "\n" + " class " +
					Thread.currentThread().getStackTrace()[2].getClassName()
					+ "\n" +
					" metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
					" line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
					+ "    getSelectedDateProcess " +getSelectedDateProcess);
	} catch (Exception e) {
		e.printStackTrace();
		Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
				" Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
		new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
				this.getClass().getName(),
				Thread.currentThread().getStackTrace()[2].getMethodName(), Thread.currentThread().getStackTrace()[2].getLineNumber());
	}
	}

	@UiThread
	protected void СообщениеФинальноеКотороеСообщаетПользователюВстакиНовгоТАбеля(String ШабкаДиалога, final String СообщениеДиалога, boolean статус) {
		///////СОЗДАЕМ ДИАЛОГ ДА ИЛИ НЕТ///////СОЗДАЕМ ДИАЛОГ ДА ИЛИ НЕТ
		int Значек = 0;
		if (статус){
			Значек =R.drawable.icon_dsu1_new_create_tabel4;
		}else{
			Значек =R.drawable.icon_dsu1_new_create_tabel5error;
		}
		try{
			final AlertDialog DialogBoxsПростомрДанных = new MaterialAlertDialogBuilder(this)
					.setTitle(ШабкаДиалога)
					.setMessage(СообщениеДиалога)
					.setNegativeButton("ОК", null)
					.setIcon( Значек)
					.show();
			final Button MessageBoxUpdateЗАкрытьСозданиеТабеля = DialogBoxsПростомрДанных.getButton(AlertDialog.BUTTON_NEGATIVE);
			MessageBoxUpdateЗАкрытьСозданиеТабеля.setOnClickListener(new View.OnClickListener() {
				///MessageBoxUpdate метод CLICK для DIALOBOX
				@Override
				public void onClick(View v) {
					DialogBoxsПростомрДанных.dismiss();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
					+ Thread.currentThread().getStackTrace()[2].getLineNumber());
			new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
		  this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
					Thread.currentThread().getStackTrace()[2].getLineNumber());
		}
	}


	void МетодПослеСозданеиУспешногоТабеля(@NonNull Bundle bundleПослеСоздаенияНовгоТабелч) {
		try{
			Log.d(this.getClass().getName(), " bundleПослеСоздаенияНовгоТабелч"+bundleПослеСоздаенияНовгоТабелч  );
			Integer ID_ЦФО=      bundleПослеСоздаенияНовгоТабелч.getInt("ПолучаемIDЦфо",0);
			String Название_ЦФО=   bundleПослеСоздаенияНовгоТабелч.getString("НазваниеЦФО","");
			Integer Месяц_ЦФО=      bundleПослеСоздаенияНовгоТабелч.getInt("МесяцВырезалиИзБуфераТабель",0);
			Integer Год_ЦФО=      bundleПослеСоздаенияНовгоТабелч.getInt("ГодВырезалиИзБуфераТабель",0);
			Long Новый_UUID_ЦФО=   bundleПослеСоздаенияНовгоТабелч.getLong("СгенерированныйUUIDДляНовогоТабеля",0l);
			// TODO: 15.12.2022  передаем данные
		Intent Интент_ПослеСозданиеНовогоТабеля=new Intent();
		Интент_ПослеСозданиеНовогоТабеля.setClass(getApplication(),  MainActivity_List_Tabels.class); // ТУТ ЗАПВСКАЕТЬСЯ ВЫБОР ПРИЛОЖЕНИЯ
		Интент_ПослеСозданиеНовогоТабеля.putExtra("ГодВырезалиИзБуфераТабель", Год_ЦФО);
		Интент_ПослеСозданиеНовогоТабеля.putExtra("МесяцВырезалиИзБуфераТабель", Месяц_ЦФО);
		Интент_ПослеСозданиеНовогоТабеля.putExtra("ЦифровоеИмяНовгоТабеля", ID_ЦФО  );
		Интент_ПослеСозданиеНовогоТабеля.putExtra("СгенерированныйUUIDДляНовогоТабеля", Новый_UUID_ЦФО);

			// TODO: 15.12.2022  названеи нового цфо
			Интент_ПослеСозданиеНовогоТабеля.putExtra("ДепартаментТабеляПослеПодбораBACK", Название_ЦФО);
			Интент_ПослеСозданиеНовогоТабеля.putExtra("ПолноеИмяТабеляПослеСозданиеНовогоСотрудника",  Название_ЦФО);
			Интент_ПослеСозданиеНовогоТабеля.putExtra("ПолученноеТекущееЗначениеСпинераДата", Название_ЦФО);
			Интент_ПослеСозданиеНовогоТабеля.putExtra("СгенерированныйНазваниеНовогоТабеля", Название_ЦФО);
			Log.d(this.getClass().getName(), " Название_ЦФО"+Название_ЦФО);
		startActivity(Интент_ПослеСозданиеНовогоТабеля);
	} catch (Exception e) {
		e.printStackTrace();
		Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
				+ Thread.currentThread().getStackTrace()[2].getLineNumber());
		new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
				this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
				Thread.currentThread().getStackTrace()[2].getLineNumber());
	}
	}







	////СООБЩЕНИЕ ИЗ ИТОРИИИ ТАБЛЕЙ ОТПРАВЛЯЕМ СООБЩЕНЕИ И ЗНАЧЕНИЕ В ДУГОЕ АКТИВТИ О СОЗДАНИЮ НОВОГО ТАБЕЛЯ

  /*  Iterator<Entry<String, String>>iterator=map.entrySet();
while(iterator.hasNext()){
		final Entry<String, String>next=iterator.next();
		next.getKey(); next.getValue();
	}*/




























	//TODO метод получени месяа для записи в одну колонку

	private Integer  МетодПолучениниеГодЕслиТАкойТАбельВБазеКлиент() throws ParseException {
		Integer ПолучениыйГодДЛяНовгоТабеля = 0;
		try{
			Date currentDate = new Date();
			DateFormat dateFormat = new SimpleDateFormat("y", new Locale("ru")); // Форматирование времени как "день.месяц.год"
			String ПолучаемГодВТексте = dateFormat.format(currentDate);
			ПолучениыйГодДЛяНовгоТабеля=Integer.parseInt(ПолучаемГодВТексте);
	} catch (Exception e) {
		e.printStackTrace();
		Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
				+ Thread.currentThread().getStackTrace()[2].getLineNumber());
			new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
		  this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
				Thread.currentThread().getStackTrace()[2].getLineNumber());
	}
		return   ПолучениыйГодДЛяНовгоТабеля ;
	}

	// TODO: 20.09.2021  класс по созаднию нового втабеля
	/////////КНОПКА СОЗДАНИЕ НОВОГО ТАБЕЛЯ НА МЕСЯЦ (АКВИТИ СОЗДАНИНЕ НОВГО ТАБЕЛЯ НА МЕСЯЦ)
	private void МетодСозданиеТабеля() {
		try{
			КнопкаСозданиеТабеля.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					/////TODO после выбора цфо и подразделение создаем табель
					if (  СпинерВыборЦФО.getText().toString().length()==0) {
						Snackbar.make(v, " Вы не выбрали цфо !!! "
								, Snackbar.LENGTH_LONG).show();
						Log.d(this.getClass().getName(),"МетодСозданиеТабеля  v "+v);
					} else {
						// TODO: 14.12.2022 созадем новый табель
					   Bundle bundle=(Bundle)      СпинерВыборЦФО.getTag();
						String НазваниеЦФО=   bundle.getString("НазваниеЦФО","");
						Long UUIDНазваниеЦФО =   bundle.getLong("UUIDНазваниеЦФО",0l);
						Log.d(this.getClass().getName(),"МетодСозданиеТабеля  v "+v);
						// TODO: 17.11.2022  запоминаем выбраное ЦФО
						SharedPreferences.Editor editor = preferences.edit();
						editor.putBoolean("ДляСпинераУжеВибиралЦФО",true);
						editor.putString("НазваниеВыбраногоЦФО",НазваниеЦФО);
						editor.putLong("UUIDНазваниеЦФО",UUIDНазваниеЦФО);
						editor.commit();


						// TODO: 07.10.2024  Созадем нОВЫЙ ТАБЕЛЬ
						/////TODO создаем НОВЫЙ ТАБЕЛЬ
						МетодФиналСоздаемТабельСПолученнымиДанными(bundle);

						Log.d(this.getClass().getName(),"\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
								" metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
								" line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");

					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" +
					Thread.currentThread().getStackTrace()[2].getMethodName() +
					" Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
			new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
					this.getClass().getName(),
					Thread.currentThread().getStackTrace()[2].getMethodName(),
					Thread.currentThread().getStackTrace()[2].getLineNumber());
		}
	}

	//////ДАННЫЙ МЕТОД НЕПОСТРДСТВЕННО ЗАПИСЫВАЕТ ДАННЫХ Е БАЗУ ДАННЫХ В ТАБЛИЦУ СОЗДАНИЕ ТАБЕЛЕЙ
	protected void МетодФиналСоздаемТабельСПолученнымиДанными(@NonNull  Bundle  bundleДляСозданияНовгоТабелч) {
		try {
			// TODO: 14.12.2022 данные для создаени новго табеля
			Long UUIDНазваниеЦФО=      bundleДляСозданияНовгоТабелч.getLong("UUIDНазваниеЦФО",0);
			String НазваниеЦФО=   bundleДляСозданияНовгоТабелч.getString("НазваниеЦФО","");
			//TODO ФУНКЙИИ ОПРЕДЕЛЕНИ МЕСЯЦА И ГОДА
			Log.d(this.getClass().getName()," UUIDНазваниеЦФО " +UUIDНазваниеЦФО + "НазваниеЦФО" +  НазваниеЦФО);
			// TODO: 15.12.2022 добадяем в передачу дальше
			bundleДляСозданияНовгоТабелч.putInt("МесяцВырезалиИзБуфераТабель", getNewMonth);
			bundleДляСозданияНовгоТабелч.putInt("ГодВырезалиИзБуфераТабель", getNewYear);

			bundleДляСозданияНовгоТабелч.putString("НазваниеЦФО",НазваниеЦФО);
			bundleДляСозданияНовгоТабелч.putLong("UUIDНазваниеЦФО",UUIDНазваниеЦФО);



			//////TODO  Метод определяем елси такое Навание Табеля Проверраем
			boolean РезультатЕслиТакоеНазвание =
					МетодПроверяемЕслиТакойНазваниеТабеляВБазеУжеЕсть(UUIDНазваниеЦФО
							, getNewMonth
							, getNewYear);
			Log.d(this.getClass().getName(), " РезультатЕслиТакоеНазвание" +РезультатЕслиТакоеНазвание );


			// TODO: 14.12.2022 оперделяем содаес новый табель или нет
			if (РезультатЕслиТакоеНазвание==true){
				СообщениеФинальноеКотороеСообщаетПользователюВстакиНовгоТАбеля("Создание Табеля",
						"Этот Табель уже есть !!! ",false );
			}else {


				// TODO: 14.12.2022 СОЗДАЕМ НОВЫЙ ТАБЕЛЕ ПОСЛЕ ПОЛУЧЕНИЕ ДАННЫХ ДЛЯ СОЗДАЕНИЯ
				ContentValues АдаптерВставкиНовгоТабеля = new ContentValues();


		 Long   СгенерированныйUUIDДляНовогоТабеля=  (Long)   new GreatUuidGeneration(getApplicationContext()).greatUuidGeneration();
			АдаптерВставкиНовгоТабеля.put("uuid", СгенерированныйUUIDДляНовогоТабеля);



			////TODO ДАТА
			String СгенерированованныйДатаДляДаннойОперации=     new GetMainDateForApp(getApplicationContext()).getMainDateForApp();
			АдаптерВставкиНовгоТабеля.put("date_update",СгенерированованныйДатаДляДаннойОперации);


				// TODO: 09.10.2024 Public ID
				Integer getPublicID = new GetttingPublicID().getttingPublicID(getApplicationContext());
				// TODO: 14.12.2022
				АдаптерВставкиНовгоТабеля.put("user_update", getPublicID);


				// TODO: 18.03.2023  получаем ВЕСИЮ ДАННЫХ
				Long РезультатУвеличинаяВерсияДАныхЧата =
						new VersionCurentTable(getApplicationContext()).upVersionCurentTable(   "tabel"  );
				Log.d(this.getClass().getName(), " РезультатУвеличинаяВерсияДАныхЧата  " + РезультатУвеличинаяВерсияДАныхЧата);



				АдаптерВставкиНовгоТабеля.put("current_table", РезультатУвеличинаяВерсияДАныхЧата);
				АдаптерВставкиНовгоТабеля.put("cfo",UUIDНазваниеЦФО);
				АдаптерВставкиНовгоТабеля.put("year_tabels", getNewYear);
				АдаптерВставкиНовгоТабеля.put("month_tabels", getNewMonth);
				АдаптерВставкиНовгоТабеля.put("status_send", " ");
			//    АдаптерВставкиНовгоТабеля.  putNull("_id");

				// TODO: 14.12.2022  само создание нового табеля
			long   РезультатВставкиНовогоТабеляЧерезКонтрейнер = new CoreBinessLogics(getApplicationContext()).
							ВставкаДанныхЧерезКонтейнерТолькоПриСозданииНовогоСотрудникаУниверсальная("tabel",
									АдаптерВставкиНовгоТабеля );

				Log.d(this.getClass().getName(), "  РезультатВставкиНовогоТабеляЧерезКонтрейнер[0]  "+ РезультатВставкиНовогоТабеляЧерезКонтрейнер);

				if (РезультатВставкиНовогоТабеляЧерезКонтрейнер> 0 ) {
					АдаптерВставкиНовгоТабеля.clear();
					// TODO: 31.10.2022 успешное создание нового табеля
					МетодПослеСозданеиУспешногоТабеля(bundleДляСозданияНовгоТабелч);
				} else {
					СообщениеФинальноеКотороеСообщаетПользователюВстакиНовгоТАбеля("Создание Табеля", "Табель не создан !!!", false);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
					+ Thread.currentThread().getStackTrace()[2].getLineNumber());
			new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
					this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
					Thread.currentThread().getStackTrace()[2].getLineNumber());
		}
	}









	// TODO: 02.08.2022  код ля биндинга службы одноразовой синхронизации
	public void МетодПолучениеМатериала(@NotNull String Фильтр) {
		try {
			asyncTaskLoader=new AsyncTaskLoader<Cursor>(getApplicationContext()) {
				@Nullable
				@Override
				public Cursor loadInBackground() {
					try{
						// TODO: 31.10.2022  запускам получение данных и внешний вид
						CursorДляСпиноровЦФО=            МетодДляНовогоТабеляПолучаемДанные("cfo",Фильтр);
					} catch (Exception e) {
						e.printStackTrace();
						Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName()
								+ " Линия  :"
								+ Thread.currentThread().getStackTrace()[2].getLineNumber());
						new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName()
								, Thread.currentThread().getStackTrace()[2].getMethodName(),
								Thread.currentThread().getStackTrace()[2].getLineNumber());
					}
					return CursorДляСпиноровЦФО;
				}
			};
			asyncTaskLoader.startLoading();
		} catch (Exception e) {
			e.printStackTrace();
			Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
					" Линия  :"
					+ Thread.currentThread().getStackTrace()[2].getLineNumber());
			new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
					this.getClass().getName(), Thread.currentThread().getStackTrace()[2].getMethodName(),
					Thread.currentThread().getStackTrace()[2].getLineNumber());
		}finally {
			CursorДляСпиноровЦФО=     asyncTaskLoader.loadInBackground();
			asyncTaskLoader.reset();
		}
	}
	// TODO: 02.08.2022
	protected   Cursor МетодДляНовогоТабеляПолучаемДанные(@NonNull String  ФлагКакаяТаблицаОбработки){
		Cursor cursor = null;
		try{
		 Integer   ПубличныйIDДляФрагмента     =new GetttingPublicID().getttingPublicID(getApplicationContext());
			Log.d(getApplicationContext().getClass().getName(), "\n"
					+ " ПубличныйIDДляФрагмента: " + ПубличныйIDДляФрагмента);
			Bundle bundleДляПЕредачи=new Bundle();
			bundleДляПЕредачи.putString("Таблица",ФлагКакаяТаблицаОбработки);
			Intent intentПолучениеМатериалов = new Intent(getApplicationContext(), ServiceForAdminissionMaterial.class);
			intentПолучениеМатериалов.setAction("ПолучениеМатериалоСозданиеНового");
			intentПолучениеМатериалов.putExtras(bundleДляПЕредачи);
			if (binderДляПолучениеМатериалов!=null) {
				cursor = (Cursor) binderДляПолучениеМатериалов.getService().МетодCлужбыПолучениеМатериалов(getApplicationContext(), intentПолучениеМатериалов);
				Log.d(this.getClass().getName(), "   cursor " + cursor);
				if (cursor.getCount() > 0) {
					cursor.moveToFirst();
					// TODO: 15.10.2022
						Log.d(this.getClass().getName(), "   cursor " + cursor);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
					+ Thread.currentThread().getStackTrace()[2].getLineNumber());
			new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
					Thread.currentThread().getStackTrace()[2].getMethodName(),
					Thread.currentThread().getStackTrace()[2].getLineNumber());
		}
		return  cursor;
	}
	// TODO: 02.08.2022
	protected   Cursor МетодДляНовогоТабеляПолучаемДанные(@NonNull String  ФлагКакаяТаблицаОбработки, @NotNull String Фильтр){
		Cursor cursor = null;
		try{
			Integer   ПубличныйIDДляФрагмента     =new GetttingPublicID().getttingPublicID(getApplicationContext());
			Log.d(getApplicationContext().getClass().getName(), "\n"
					+ " ПубличныйIDДляФрагмента: " + ПубличныйIDДляФрагмента);
			Bundle bundleДляПЕредачи=new Bundle();
			bundleДляПЕредачи.putString("Таблица",ФлагКакаяТаблицаОбработки);
			bundleДляПЕредачи.putString("ФильтрДляПоиска",Фильтр);
			Intent intentПолучениеМатериалов = new Intent(getApplicationContext(), ServiceForAdminissionMaterial.class);
			intentПолучениеМатериалов.setAction("ПолучениеМатериалоСозданиеНового");
			intentПолучениеМатериалов.putExtras(bundleДляПЕредачи);
			if (binderДляПолучениеМатериалов!=null) {
				cursor = (Cursor) binderДляПолучениеМатериалов.getService().МетодCлужбыПолучениеМатериалов(getApplicationContext(), intentПолучениеМатериалов);
				Log.d(this.getClass().getName(), "   cursor " + cursor);
					Log.d(this.getClass().getName(), "   cursor " + cursor);
			   // }
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
					+ Thread.currentThread().getStackTrace()[2].getLineNumber());
			new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
					Thread.currentThread().getStackTrace()[2].getMethodName(),
					Thread.currentThread().getStackTrace()[2].getLineNumber());
		}
		return  cursor;
	}
	// TODO: 02.08.2022
	protected  Cursor МетодДляНовогоТабеляПолучаемДанныеИзНовогоПоиска(@NonNull String  ФлагКакаяТаблицаОбработки, @NotNull String Фильтр){
		Cursor cursor = null;
		try{
			Integer   ПубличныйIDДляФрагмента     = new GetttingPublicID().getttingPublicID(getApplicationContext());
			Log.d(getApplicationContext().getClass().getName(), "\n"
					+ " ПубличныйIDДляФрагмента: " + ПубличныйIDДляФрагмента);
			Bundle bundleДляПЕредачи=new Bundle();
			bundleДляПЕредачи.putString("Таблица",ФлагКакаяТаблицаОбработки);
			bundleДляПЕредачи.putString("ФильтрДляПоиска",Фильтр);
			Intent intentПолучениеМатериалов = new Intent(getApplicationContext(), ServiceForAdminissionMaterial.class);
			intentПолучениеМатериалов.setAction("ПолучениеМатериалоИзНовгоПоиска");
			intentПолучениеМатериалов.putExtras(bundleДляПЕредачи);
			if (binderДляПолучениеМатериалов!=null) {
				cursor = (Cursor) binderДляПолучениеМатериалов.getService().МетодCлужбыПолучениеМатериалов(getApplicationContext(), intentПолучениеМатериалов);
				Log.d(this.getClass().getName(), "   cursor " + cursor);
				Log.d(this.getClass().getName(), "   cursor " + cursor);
				// }
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.e(this.getClass().getName(), "Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() + " Линия  :"
					+ Thread.currentThread().getStackTrace()[2].getLineNumber());
			new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(), this.getClass().getName(),
					Thread.currentThread().getStackTrace()[2].getMethodName(),
					Thread.currentThread().getStackTrace()[2].getLineNumber());
		}
		return  cursor;
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
					Log.e(getApplicationContext().getClass().getName(),
							"Ошибка " + e + " Метод :" + Thread.currentThread().getStackTrace()[2].getMethodName() +
									" Линия  :" + Thread.currentThread().getStackTrace()[2].getLineNumber());
					new RecordNewErros(getApplicationContext()).recordnewerror(e.toString(),
							this.getClass().getName().toString(), Thread.currentThread().getStackTrace()[2].getMethodName().toString(),
							Thread.currentThread().getStackTrace()[2].getLineNumber());
				}
				return true;
			}
		});
	}
}





