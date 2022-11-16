package com.ideas2it.util.enumeration;

import com.ideas2it.util.exception.EmployeeManagementException;
import com.ideas2it.util.Constants;

public enum BloodGroup {
    A_POSITIVE(1),
    A_NEGATIVE(2),
    B_POSITIVE(3),
    B_NEGATIVE(4),
    O_POSITIVE(5),
    O_NEGATIVE(6),
    AB_POSITIVE(7),
    AB_NEGATIVE(8),
    A1_POSITIVE(9),
    A1_NEGATIVE(10),
    A1B_POSITIVE(11),
    A1B_NEGATIVE(12),
    A2B_POSITIVE(13),
    A2B_NEGATIVE(14),
    OTHERS(15);

    public final int value;

    BloodGroup(final int value) {
     this.value = value;
    }
    
    /**
    * Get the choice from the user and return the enum constant based on user choice.
    *
    * @param choice  the user choice to get the enum constant 
    * @return        enum constant                       
    */  
    public static BloodGroup getBloodGroup(int value) throws EmployeeManagementException {
        BloodGroup result = null;
        for(BloodGroup bloodGroup : BloodGroup.values()) {
            if(bloodGroup.value == value) {
                result = bloodGroup;
                break;
            }
        }
        if (null == result) {
            throw new EmployeeManagementException(Constants.INVALID_OPTION);
        }
        return result;
    }
} 