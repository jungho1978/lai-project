package com.lge.lai.sample.validation;

import static org.junit.Assert.*;

import org.junit.Test;

import com.lge.lai.common.annotation.InterfaceWith;
import com.lge.lai.common.annotation.InterfaceWith.Call;
import com.lge.lai.common.annotation.InterfaceWith.Component;
import com.lge.lai.common.validation.InterfaceValidator;

public class ActivityTest {

    InterfaceValidator validator = new InterfaceValidator(getClass());

    @Test
    @InterfaceWith(
            toPackage = "com.android.dialer",
            callType = Call.EXPLICIT,
            componentType = Component.ACTIVITY)
    public void explicitCall() {
        validator.validates(null);
    }
}
