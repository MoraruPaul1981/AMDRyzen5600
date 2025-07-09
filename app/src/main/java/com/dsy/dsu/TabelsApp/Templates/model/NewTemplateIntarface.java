package com.dsy.dsu.TabelsApp.Templates.model;

import android.content.ContentValues;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.annotation.NonNull;



// TODO: 06.07.2025 New Templaes
  abstract class NewTemplateIntarface {
    // TODO: 26.06.2025
     public   abstract  Integer newTamplate(@NonNull View v, @NonNull String namenewtemplate,   @NonNull Handler getHandler);
    public   abstract  Integer operationsNewTemplate(@NonNull ContentValues contentvaluesTemplateOperationsAdding,
                                                     @NonNull String getNameTable);
}//TODO END CLASS




// TODO: 06.07.2025 add complte Temples  in Tabel
abstract class AddingTemplateIntarface {
    // TODO: 26.06.2025
    public   abstract   Long addingTemplateInTabel(@NonNull  View v,@NonNull Bundle bundleItemCompletetemplate);
    public   abstract  Integer operationsCompleteAfterTemplate(@NonNull ContentValues contentvaluesTemplateOperationsAdding,
                                                               @NonNull String getNameTable);
}//TODO END CLASS




// TODO: 06.07.2025 Code System
interface SystemTemplateIntarface {
    // TODO: 26.06.2025
       void backToActivityListTabels(@NonNull Bundle bundlebackActivitylistPeoples);


    void launchToActivityFindTempales(@NonNull Bundle bundlebackActivitylistPeoples);


}//TODO END CLASS


// TODO: 06.07.2025 New Templaes
abstract class MessageTemplateIntarface {
    // TODO: 26.06.2025

    public   abstract  void messageNewTemplate( @NonNull String СообщениеДиалога, @NonNull String namenewtemplate,
                                                 @NonNull Bundle bundleFromActivityListPeoples,@NonNull Handler getHandler  );

    public   abstract  void messageCompleteTemplate( @NonNull String СообщениеДиалога, @NonNull String namenewtemplate,
                                              @NonNull Bundle bundleFromActivityListPeoples ,@NonNull Handler getHandler  );
}//TODO END CLASS

















