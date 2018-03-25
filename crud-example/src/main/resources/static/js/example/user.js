var user = {
    model: 'user',
    path: '/user',
    editFlag: false,
    rowIndex: null,
    editInitData: {},
    query: function () {
        http.query(user.path);
    },
    formatOperation: function (val, row, index) {
        return common.formatOperation(user.model, val, row, index);
    },
    show: function (editFlag, index) {
        common.show(user.model, editFlag, index);
    },
    del: function (index) {
        common.deleteOperation(user.model, index);
    },
    save: function () {
        common.saveOperation(user.model);
    }
};