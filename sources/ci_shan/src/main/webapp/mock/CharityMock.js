/*慈善方模拟数据},作者:唐植超,日期:2018-10-20 12:46:47*/
'use strict';
var Mock = require('mockjs')
var Random = Mock.Random;
module.exports = {

    'POST /api/charity/queryPage': function (req, res, next) {
        var data = Mock.mock({
            'content|10': [{
                id: "@integer(100,200)",//主键
                type: "@integer(100,200)",//type
                charity_name: "@word(5,10)",// charity_name
                phone_no: "@word(5,10)",// phone_no
                gender: "@word(5,10)",// gender
                address: "@word(5,10)",// address
                create_date: "@integer(100,200)",//create_date
                author: "@integer(100,200)",//author
                remark: "@word(5,10)",// remark
                p_id: "@integer(100,200)",//p_id
                category: "@integer(100,200)",//category
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

    'POST /api/charity/update': function (req, res, next) {
        setTimeout(function () {
            res.json({});
        }, 500);
    },

    'POST /api/charity/save': function (req, res, next) {
        setTimeout(function () {
            res.json({});
        }, 500);
    },

    'POST /api/charity/queryList': function (req, res, next) {
        var data = Mock.mock({
            'content|10': [{
                id: "@integer(100,200)",//主键
                type: "@integer(100,200)",//type
                charity_name: "@word(5,10)",// charity_name
                phone_no: "@word(5,10)",// phone_no
                gender: "@word(5,10)",// gender
                address: "@word(5,10)",// address
                create_date: "@integer(100,200)",//create_date
                author: "@integer(100,200)",//author
                remark: "@word(5,10)",// remark
                p_id: "@integer(100,200)",//p_id
                category: "@integer(100,200)",//category
                status: "@integer(100,200)",//status
            }]
        });
        setTimeout(function () {
            res.json(data.content);
        }, 500);
    },

    'POST /api/charity/delete': function (req, res, next) {
        setTimeout(function () {
            res.json({});
        }, 500);
    },
}