package org.alvin.cishan.common.exception;

public interface ErrorCode {
    int EOK = 0;
    int EFLASHUPLOAD = 1;
//    int EHTTPPROTO = -1001;        //后台http访问失败
//    int ENOTSUPPORTED = -1002;
    int EBADPARAM = -1003;       // 参数不合法，contextInfo: param name list
//    int EJSONFMTINVAL = -1004;
    int EGENERALEXCEPTION = -1005;   // rdesc
    int EACESSDENIED = -1006;   // rdesc
    int ESERVERACESSDENIED = -1007; //服务器发送的请求被拒绝
    int ERAPIDREQUEST = -1008;

    // NOT found
    int ENOTFOUND = -10001;   // 找不到{0}
    int ENOTFOUNDPROJECT = -10002;   // 找不到对应的project id={0}
    int ENOTFOUNDOP = -10003;   // 找不到对应的操作 opId={0}
    int ENOTFOUNDBUSINESS = -10004;   // 找不到对应的业务 businessUuid={0}
    int ENOTFOUNDBUOP = -10005;   // 找不到对应的业务操作 buopId={0}
    int ENOTFOUNDIMESSAGE = -10006;   // 该用户下找不到对应的消息
    int ENOTFOUNDUSER = -10007;   // 找不到用户
    int ENOTFOUNDOUTER = -10008;  //找不到外部服务需求信息
    int ENOTUAMUSER = -10009;  //不是 uam 账号

    //DEL project
    int EOPBINDED = -10012;   // 项目有未完成操作绑定, project id {0}
    int EMISMATCHBUOP = -10024;   // 业务与操作不匹配  buopId={0} businessUuid={1}
    int ENOTLOGIN = EACESSDENIED;   // 用户未登录或者已经超时

    //MOD project
    int EFILEUPLOADFAIL = -10025;
    int EPROJECTNAMEDUP = -10026;
    int EPROJECTBIDDUP = -10027;


    // for login & register error
    int EPASS = -10030; //密码不正确
    int ECAPTCHAMISMATCH = -10031;//验证码错误
    int EUSEREXISTS = -10032;   // 用户名已存在

    int EEMAILERROR = -10034;     //邮箱格式错误
    int EUSERNOTEXIST = -10036;   //用户不存在
    int EEMAILEXISTS = -10037; //邮箱已存在
    int ESOURCEIDEXISTS = -10039; //员工编号已被使用
    int ESMSCODELIMIT = -10040; //短信验证码今日发送已达到上限
    int ESMSCODEERROR = -10041; //短信验证码不正确或已过期
    int ESMSCODESENDERROR = -10042; //短信验证码发送失败
    int ETICKETERROR = -10043; //ticket验证不通过（错误或过期）

    // 用户访问接口时权限不正确
    int EAUTHPROJECT = -10010;   // 对该项目没有权限  projectId={0}
    int EAUTHOP = -10011;   // 对部分操作没有查看权限

    int EAPPNOTREGISTER = -10013;//应用未注册或不存在

    int SIGN_INCORRECT = -10038; //用户访问接口时验证参数不正确
}
