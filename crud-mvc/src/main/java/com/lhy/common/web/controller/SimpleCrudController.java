package com.lhy.common.web.controller;

import java.io.Serializable;

public interface SimpleCrudController<T extends Serializable, P extends Serializable>
        extends SimpleCreateController<T>, SimpleUpdateController<T>, SimpleDeleteController<T, P>, SimpleQueryController<T>, SimpleGetController<T, P>, SimpleValidateController<T> {
}
