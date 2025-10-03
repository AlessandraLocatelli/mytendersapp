package com.bolzanoprovince.tendering.model.enums;

public enum TenderType {
    NEGOTIATED_PROCEDURE(true),
    OPEN_PROCEDURE(false),
    RESTRICTED_PROCEDURE(false);

    private final boolean requiresVendors;

    TenderType(boolean requiresVendors) {
        this.requiresVendors = requiresVendors;
    }

    public boolean requiresVendors() {
        return requiresVendors;
    }

}

