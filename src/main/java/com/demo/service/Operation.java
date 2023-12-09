package com.demo.service;

import lombok.Getter;

@Getter
public enum Operation {
    moreThan(">"),
    lessThan("<"),
    equal("=");

    private final String operationType;

    Operation(String operationType) {
        this.operationType = operationType;
    }

}
