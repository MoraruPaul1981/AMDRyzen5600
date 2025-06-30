package com.dsy.dsu.BusinessLogicForApps.TestsBusinessLogic.TestInterfaces;

import com.dsy.dsu.BusinessLogicForApps.TestsBusinessLogic.TestInterfaces.Interface.IFunc1;

public class Procesing2nterface implements IFunc1 {


    @Override
    public short applyAsByte(short s) {

        System.out.printf("Procesing2nterface s" + s);

        return s;
    }
}
