/*菜单模拟数据,作者:高振中,日期:2018-08-10 09:21:48*/
'use strict';
var Mock = require('mockjs')
var Random = Mock.Random;
module.exports = {

    'POST /api/adminFunc/queryPage': function (req, res, next) {
        var data = Mock.mock({
            "content|10": [{
                func_id: "@integer(100,200)",//主键
                type: "@integer(100,200)",//类型
                color: "@word(5,10)",//颜色
                icon: "@word(5,10)",//图标
                name: "@word(5,10)",//名称
                parent_id: "@integer(100,200)",//父结点ID
                tip: "@word(5,10)",//提示信息
                remark: "@word(5,10)",//备注
                status: "@integer(100,200)",//状态
                url: "@word(5,10)",//地址
                order_num: "@integer(100,200)",//排序编号
            }],
            "number": '@integer(100,200)',
            "size": 10,
            "totalElements": 500,
        });
        setTimeout(function () {
            res.json(data);
        }, 500);
    },
    'POST /api/adminFunc/update': function (req, res, next) {
        setTimeout(function () {
            res.json({});
        }, 500);
    },
    'POST /api/adminFunc/save': function (req, res, next) {
        setTimeout(function () {
            res.json({});
        }, 500);
    },
    'POST /api/adminFunc/queryList': function (req, res, next) {
        var data = Mock.mock({
            "content|10": [{
                func_id: "@integer(100,200)",//主键
                type: "@integer(100,200)",//类型
                color: "@word(5,10)",//颜色
                icon: "@word(5,10)",//图标
                name: "@word(5,10)",//名称
                parent_id: "@integer(100,200)",//父结点ID
                tip: "@word(5,10)",//提示信息
                remark: "@word(5,10)",//备注
                status: "@integer(100,200)",//状态
                url: "@word(5,10)",//地址
                order_num: "@integer(100,200)",//排序编号
            }]
        });
        setTimeout(function () {
            res.json(data.content);
        }, 500);
    },
    'POST /api/adminFunc/getTree': function (req, res, next) {
        var data = Mock.mock({
            "content|10": [{
                func_id: "@integer(100,200)",//主键
                type: "@integer(100,200)",//类型
                color: "@word(5,10)",//颜色
                icon: "@word(5,10)",//图标
                name: "@word(5,10)",//名称
                parent_id: "@integer(100,200)",//父结点ID
                tip: "@word(5,10)",//提示信息
                remark: "@word(5,10)",//备注
                status: "@integer(100,200)",//状态
                url: "@word(5,10)",//地址
                order_num: "@integer(100,200)",//排序编号
            }]
        });
        setTimeout(function () {
            res.json(data.content);
        }, 500);
    },
    'POST /api/adminFunc/delete': function (req, res, next) {
        setTimeout(function () {
            res.json({});
        }, 500);
    },
}