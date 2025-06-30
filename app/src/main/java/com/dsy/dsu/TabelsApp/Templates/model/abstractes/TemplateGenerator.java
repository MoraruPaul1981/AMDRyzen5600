package com.dsy.dsu.TabelsApp.Templates.model.abstractes;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;

public abstract class TemplateGenerator {
    // TODO: 26.06.2025

    public   abstract   Long addingTemplateForTabel(@NonNull Bundle bundleItemCompletetemplate);

    public   abstract  Integer  proccesingNewTamplate(@NonNull View v, @NonNull String namenewtemplate);

    public   abstract  Integer addTemplateOperationsFinal(@NonNull ContentValues contentvaluesTemplateOperationsAdding, @NonNull String getNameTable);

    public   abstract  void messageForCreatingNewTemplates(String Создание , String СообщениеДиалога,@NonNull String namenewtemplate,@NonNull Bundle bundleFromActivityListPeoples  );

    public   abstract     void backToActivityListTabels(@NonNull Bundle bundlebackActivitylistPeoples);

    public   abstract   void choiceMessageCompleteAddtoTimesheetOrFillFio( @NonNull Bundle bundleFromActivityListPeoples );
}
