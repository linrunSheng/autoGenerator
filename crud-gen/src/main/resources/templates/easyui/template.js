var ${modelName?uncap_first} = {
    model: '${modelName?uncap_first}',
    path: '/${controllerAttribute.controllerRequestMapping}',
    editFlag: false,
    rowIndex: null,
    editInitData: {},
    query: function () {
        http.query(${modelName?uncap_first}.path);
    },
    formatOperation: function (val, row, index) {
        return common.formatOperation(${modelName?uncap_first}.model, val, row, index);
    },
    show: function (editFlag, index) {
        common.show(${modelName?uncap_first}.model, editFlag, index);
    },
    del: function (index) {
        common.deleteOperation(${modelName?uncap_first}.model, index);
    },
    save: function () {
        common.saveOperation(${modelName?uncap_first}.model);
    }
};