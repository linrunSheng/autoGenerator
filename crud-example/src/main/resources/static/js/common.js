var common = {
    getContextPath: function () {
        var pathName = window.location.pathname;
        var index = pathName.substr(1).indexOf("/");
        var result = pathName.substr(0, index + 1);
        return result;
    },
    isNull: function () {
        return value == null || value == "" || value == undefined;
    },

    datagrid: function (tableId, datagridObj) {
        datagridObj.rownumbers = datagridObj.rownumbers == false ? false : true;
        datagridObj.singleSelect = datagridObj.singleSelect == false ? false : true;
        datagridObj.pagination = datagridObj.pagination == false ? false : true;
        datagridObj.queryParams = datagridObj.queryParams == null ? {} : datagridObj.queryParams;
        datagridObj.fitColumns = datagridObj.fitColumns == false ? false : true;
        datagridObj.toolbar = datagridObj.toolbar == null ? '' : datagridObj.toolbar;
        datagridObj.pageSize = datagridObj.pageSize == null ? 10 : datagridObj.pageSize;
        datagridObj.onLoadSuccess = datagridObj.onLoadSuccess ? function () {
        } : function (data) {
            $.parser.parse($('.operBtn').parent());
        };
        datagridObj.onBeforeLoad = datagridObj.onBeforeLoad ? function () {
        } : function (param) {
            if(param.sort && param.order){
                param.sc = param.sort +' '+param.order;
            }
        };
        var options = {method: 'get'};
        $.extend(options, datagridObj);
        $(tableId).datagrid(options);
    },
    formatOperation: function (model, val, row, index) {
        return '<a href="#" class=" easyui-linkbutton operBtn" data-options="iconCls:\'icon-edit\',plain:true" onclick="' + model + '.show(true,' + index + ')'
            + '">编辑</a>&nbsp;&nbsp;<a href="#" class=" easyui-linkbutton operBtn" data-options="iconCls:\'icon-cancel\',plain:true" onclick="' + model + '.del(' + index + ')">删除</a>';
    },
    show: function (model, editFlag, index) {
        var obj = window[model];
        //为空时新增
        obj.editFlag = editFlag;
        obj.rowIndex = index;
        $("#editForm").form("clear");
        if (!editFlag) {
            $("#edit-window").window("setTitle", "新增");
            obj.editInitData = {};
        } else {
            $('#table').datagrid('selectRow', index);
            var row = $("#table").datagrid("getSelected");
            if (row == null) {
                $.messager.alert('提示', '请选择行', 'info');
                return;
            }
            obj.editInitData = row;
            $("#edit-window").window("setTitle", "编辑");
            $("#editForm").form("load", row);
        }
        $("#edit-window").window("open");
    },
    deleteOperation: function (model, index) {
        var obj = window[model];
        $('#table').datagrid('selectRow', index);
        var row = $("#table").datagrid("getSelected");
        if (row == null) {
            $.messager.alert('提示', '请选择行', 'info');
            return;
        }
        $.messager.confirm('提示', '您确定要删除该记录吗?', function (r) {
            if (r) {
                http.del(obj.path, row.id, function (data) {
                    if (data.success) {
                        $.messager.alert('提示', '删除成功！', 'info');
                        obj.query();
                    } else {
                        $.messager.alert('提示', '删除失败！', 'warning');
                    }
                })
            }
        });
    },
    saveOperation: function (model) {
        var obj = window[model];
        if ($("#editForm").form('validate')) {
            var data = $("#editForm").serializeJSON();
            if (obj.editFlag) {
                http.update(obj.path, data, function (resp) {
                    common.saveCallback(model, resp);
                })
            } else {
                http.add(obj.path, data, function (resp) {
                    common.saveCallback(model, resp);
                })
            }
        }
    },
    saveCallback: function (model, data) {
        if (data.success) {
            $.messager.alert('提示', '保存成功！', 'info',function () {
                window[model].query();
                $("#edit-window").window("close");
            });
        } else {
            $.messager.alert('提示', '保存失败！', 'warning');
        }
    }
}

var http = {
    query: function (path) {
        var param = $("#searchForm").serializeJSON();
        common.datagrid('#table', {
            queryParams: param,
            url: common.getContextPath() + path,
            toolbar: "#toolbar",
            fitColumns: true,
            pagination: true
        });
    },
    del: function (path, id, callback) {
        $.ajax({
            url: common.getContextPath() + path + '/' + id,
            type: 'delete',
            dataType: "json",
            success: function (data) {
                callback(data);
            }
        });
    },
    add: function (path, data, callback) {
        $.ajax({
            url: common.getContextPath() + path,
            type: "post",
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "json",
            success: function (resp) {
                callback(resp);
            }
        });
    },
    update: function (path, data, callback) {
        $.ajax({
            url: common.getContextPath() + path,
            type: "put",
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "json",
            success: function (resp) {
                callback(resp);
            }
        });
    }
}

$.extend($.fn.validatebox.defaults.rules, {
    exists: {
        validator: function (value, param) {
            var obj = param[1];
            var needValid = false;
            //新增校验
            if (!window[obj].editFlag) {
                needValid = true;
            } else {
                var editInitData = window[obj].editInitData;
                //编辑时未修改则校验
                if (editInitData[param[0]] == value) {
                    needValid = false;
                } else {
                    needValid = true;
                }
            }
            var validRet = false;
            if (needValid) {
                $.ajax({
                    url: common.getContextPath() + '/user/validation?' + param[0] + "=" + value,
                    method: "get",
                    async: false,
                    dataType: "json",
                    success: function (obj) {
                        validRet = obj.valid;
                    }
                });
            } else {
                validRet = true;
            }
            return validRet;
        },
        message: '已存在相同字段.'
    }
});



