/*用户模拟数据,作者:高振中,日期:2018-08-10 09:21:48*/
'use strict';
var Mock = require('mockjs')
var Random = Mock.Random;
module.exports = {

  'POST /api/adminSysUser/queryPage': function (req, res, next) {
    var data = Mock.mock({
      "content|10": [{
        user_id: "@integer(100,200)",//主键
        name: "@word(5,10)",//姓名
        code: "@word(5,10)",//会员编号
        phone: "@word(5,10)",//手机号
        gender: "@integer(100,200)",//性别
        status: "@integer(100,200)",//状态
        department_id: "@integer(100,200)",//部门ID
        wechat: "@word(5,10)",//微信号
        remark: "@word(5,10)",//备注
      }],
      "number": '@integer(100,200)',
      "size": 10,
      "totalElements": 500,
    });
    setTimeout(function () {
      res.json(data);
    }, 500);
  },
  'POST /api/adminSysUser/update': function (req, res, next) {
    setTimeout(function () {
      res.json({});
    }, 500);
  },
  'POST /api/adminSysUser/save': function (req, res, next) {
    setTimeout(function () {
      res.json({});
    }, 500);
  },
  'POST /api/adminSysUser/queryList': function (req, res, next) {
    var data = Mock.mock({
      "content|10": [{
        user_id: "@integer(100,200)",//主键
        name: "@word(5,10)",//姓名
        code: "@word(5,10)",//会员编号
        phone: "@word(5,10)",//手机号
        gender: "@integer(100,200)",//性别
        status: "@integer(100,200)",//状态
        department_id: "@integer(100,200)",//部门ID
        wechat: "@word(5,10)",//微信号
        remark: "@word(5,10)",//备注
      }]
    });
    setTimeout(function () {
      res.json(data.content);
    }, 500);
  },
  'POST /api/adminSysUser/delete': function (req, res, next) {
    setTimeout(function () {
      res.json({});
    }, 500);
  },
}