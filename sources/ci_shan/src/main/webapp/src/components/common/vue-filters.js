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


export default function (Vue) {
  Vue.filter('gender-filter', genderFilter);
  Vue.filter('date-filter', data_format);
  Vue.filter('date-time-filter', data_time_format);
}
