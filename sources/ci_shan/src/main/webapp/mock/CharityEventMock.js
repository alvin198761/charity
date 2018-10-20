/*善行活动模拟数据},作者:唐植超,日期:2018-10-20 12:46:47*/
'use strict';
var Mock = require('mockjs')
var Random = Mock.Random;
module.exports = {

    'POST /api/charityEvent/queryPage': function (req, res, next) {
        var data = Mock.mock({
            'content|10': [{
                id: "@integer(100,200)",//主键
                type: "@integer(100,200)",//type
                name: "@word(5,10)",// name
                content: "@word(5,10)",// content
                chairman_id: "@integer(100,200)",//chairman_id
                target_id: "@integer(100,200)",//target_id
                event_time: "@integer(100,200)",//event_time
                author: "@integer(100,200)",//author
                remark: "@word(5,10)",// remark
                chairmain_pnone: "@word(5,10)",// chairmain_pnone
                target_phone: "@word(5,10)",// target_phone
                status: "@integer(100,200)",//status
                create_time: "@integer(100,200)",//create_time
            }],
            'number': '@integer(100,200)',
            'size': 10,
            'totalElements': 500,
        });
        setTimeout(function () {
            res.json(data);
        }, 500);
    },

    'POST /api/charityEvent/update': function (req, res, next) {
        setTimeout(function () {
            res.json({});
        }, 500);
    },

    'POST /api/charityEvent/save': function (req, res, next) {
        setTimeout(function () {
            res.json({});
        }, 500);
    },

    'POST /api/charityEvent/queryList': function (req, res, next) {
        var data = Mock.mock({
            'content|10': [{
                id: "@integer(100,200)",//主键
                type: "@integer(100,200)",//type
                name: "@word(5,10)",// name
                content: "@word(5,10)",// content
                chairman_id: "@integer(100,200)",//chairman_id
                target_id: "@integer(100,200)",//target_id
                event_time: "@integer(100,200)",//event_time
                author: "@integer(100,200)",//author
                remark: "@word(5,10)",// remark
                chairmain_pnone: "@word(5,10)",// chairmain_pnone
                target_phone: "@word(5,10)",// target_phone
                status: "@integer(100,200)",//status
                create_time: "@integer(100,200)",//create_time
            }]
        });
        setTimeout(function () {
            res.json(data.content);
        }, 500);
    },

    'POST /api/charityEvent/delete': function (req, res, next) {
        setTimeout(function () {
            res.json({});
        }, 500);
    },
}