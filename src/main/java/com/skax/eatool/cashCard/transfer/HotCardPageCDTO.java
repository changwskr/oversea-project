package com.skax.eatool.cashCard.transfer;

import java.io.Serializable;
import java.util.List;

import com.skax.eatool.framework.transfer.PageDTO;

public class HotCardPageCDTO extends PageDTO {
    public HotCardPageCDTO(List pageItems, int pageNumber,
            int totalLineCount) {
        super(pageItems, pageNumber, 10, totalLineCount);
    }

    public HotCardPageCDTO(List pageItems, int pageNumber,
            int totalLineCount, int linePerPage) {
        super(pageItems, pageNumber, linePerPage, totalLineCount);
    }
}
