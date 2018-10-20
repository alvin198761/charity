/*捐助物资模拟数据},作者:唐植超,日期:2018-10-20 12:46:47*/
'use strict';
var Mock = require('mockjs')
var Random = Mock.Random;
module.exports = {

    'POST /api/category/queryPage': function (req, res, next) {
        var data = Mock.mock({
            'content|10': [{
                id: "@integer(100,200)",//主键
                type: "@integer(100,200)",//type
                name: "@word(5,10)",// name
                count: "@integer(100,200)",//count
                create_date: "@integer(100,200)",//create_date
                author: "@integer(100,200)",//author
                remark: "@word(5,10)",// remark
                c_id: "@integer(100,200)",//c_id
                status: "@integer(100,200)",//status
            }],
            'number': '@integer(100,200)',
            'size': 10,
            'totalElements': 500,
        });
        setTimeout(function () {
            res.json(data);
        }, 500);
    },

    'POST /api/category/update': function (req, res, next) {
        setTimeout(function () {
            res.json({});
        }, 500);
    },

    'POST /api/category/save': function (req, res, next) {
        setTimeout(function () {
            res.json({});
        }, 500);
    },

    'POST /api/category/queryList': function (req, res, next) {
        var data = Mock.mock({
            'content|10': [{
                id: "@integer(100,200)",//主键
                type: "@integer(100,200)",//type
                name: "@word(5,10)",// name
                count: "@integer(100,200)",//count
                create_date: "@integer(100,200)",//create_date
                author: "@integer(100,200)",//author
                remark: "@word(5,10)",// remark
                c_id: "@integer(100,200)",//c_id
                status: "@integer(100,200)",//status
            }]
        });
        setTimeout(function () {
            res.json(data.content);
        }, 500);
    },

    'POST /api/category/delete': function (req, res, next) {
        setTimeout(function () {
            res.json({});
        }, 500);
    },
}