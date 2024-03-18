package com.fsse2401.Project.data.CartItem.Dto.response;

import com.fsse2401.Project.data.CartItem.dominaObject.Result;

public class ResultStatusResponseDto {
    private String result;

    public ResultStatusResponseDto(Result result) {
        this.result = result.name();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
