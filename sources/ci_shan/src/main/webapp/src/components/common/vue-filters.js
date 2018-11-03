import moment from 'moment';

export const genderFilter = (gender) => {
    if (gender == 1) {
        return '男'
    }
    if (gender == 2) {
        return '女'
    }
    if (gender == 3) {
        return '未知'
    }
    if (gender == 4) {
        return '保密'
    }
    return "";
}


function data_format(time) {
    if (time != null)
        return new moment(time).format("YYYY-MM-DD")
}

function data_time_format(time) {
    if (time != null)
        return new moment(time).format("YYYY-MM-DD HH:mm:ss")
}

function category_status_format(status) {
    if (status == 0) {
        return "未使用";
    }
    if (status == 1) {
        return "已使用";
    }
    return "丢失"
}

function event_status_format(status) {
    if(status == 0){
        return "筹备中";
    }
    if(status == 1){
        return "进行中";
    }
    if(status == 2){
        return "已取消";
    }
    if(status == 4){
        return "已结束";
    }
}

export default function (Vue) {
    Vue.filter('gender_filter', genderFilter);
    Vue.filter('date_filter', data_format);
    Vue.filter('date_time_filter', data_time_format);
    Vue.filter('category_status_filter', category_status_format);
    Vue.filter('event_status_filter', event_status_format);
}
