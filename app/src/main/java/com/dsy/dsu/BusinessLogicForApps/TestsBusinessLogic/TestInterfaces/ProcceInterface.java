package com.dsy.dsu.BusinessLogicForApps.TestsBusinessLogic.TestInterfaces;

import com.dsy.dsu.BusinessLogicForApps.TestsBusinessLogic.TestInterfaces.Interface.IFunc1;

public class ProcceInterface implements IFunc1 {


    @Override
    public short applyAsByte(short s) {
        System.out.printf("ProcceInterface s "+s);
        return s;
    }
}
