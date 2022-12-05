package com.ideas2it.employeemanagement.util.enumeration;

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
} 