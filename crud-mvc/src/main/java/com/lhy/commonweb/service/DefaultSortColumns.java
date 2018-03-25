package com.lhy.commonweb.service;

import com.lhy.commonweb.model.RequestPage;

public class DefaultSortColumns implements SortColumnBinding {

    @Override
    public String getSortColumns() {
        return RequestPage.DEFAULT_SORT_COLUMNS;
    }
}
