package com.zb.misscmszb.vo;

import com.zb.misscmszb.bean.Code;
import com.zb.misscmszb.core.util.ResponseUtil;
import org.springframework.http.HttpStatus;

/**
 * 删除成功视图对象
 */
public class DeletedVO extends UnifyResponseVO<String> {

    public DeletedVO() {
        super(Code.DELETED.getCode());
        ResponseUtil.setCurrentResponseHttpStatus(HttpStatus.OK.value());
    }

    public DeletedVO(int code) {
        super(code);
        ResponseUtil.setCurrentResponseHttpStatus(HttpStatus.OK.value());
    }

    public DeletedVO(String message) {
        super(message);
        ResponseUtil.setCurrentResponseHttpStatus(HttpStatus.OK.value());
    }

    public DeletedVO(int code, String message) {
        super(code, message);
        ResponseUtil.setCurrentResponseHttpStatus(HttpStatus.OK.value());
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
