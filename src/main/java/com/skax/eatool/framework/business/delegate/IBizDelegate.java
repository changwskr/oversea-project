package com.skax.eatool.framework.business.delegate;

import com.skax.eatool.framework.exception.BizDelegateException;

public interface IBizDelegate {
    void initialize() throws BizDelegateException;

    void cleanup() throws BizDelegateException;
}
