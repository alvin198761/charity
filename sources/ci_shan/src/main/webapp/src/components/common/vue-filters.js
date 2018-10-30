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


export const eventTypeFilter = (gender) => {
    if (gender == 1) {
        return '捐款'
    }
    if (gender == 2) {
        return '物资'
    }
    return "其他";
}


export default function (Vue) {
  Vue.filter('gender_filter', genderFilter);
  Vue.filter('date_filter', data_format);
  Vue.filter('date_time_filter', data_time_format);
  Vue.filter('event_type_filter', eventTypeFilter);
}
