import {dsy} from './dsy.js';

export function findZoneCode(zoneCode) {
  //还原出zoneCode 全路径
  let codes = [];
  for (let i = 0; i < dsy.length; i++) {
    var province = dsy[i];
    //特别行政区
    if(province.children == null){
      if(zoneCode == province.value){
        codes.push(province.value);
        return codes;
      }
      continue;
    }
    //
    for (let j = 0; j < province.children.length; j++) {
      var city = province.children[j];
      if (city.children != null) {
        for (let k = 0; k < city.children.length; k++) {
          var country = city.children[k];
          if (country.value == zoneCode) {
            codes.push(province.value);
            codes.push(city.value);
            codes.push(country.value);
            return codes;
          }
        }
      } else {
        if (city.value == zoneCode) {
          codes.push(province.value);
          codes.push(city.value);
          return codes;
        }
      }
    }
  }
  return codes;
}


export function filterZoneCode(zoneCode) {
  //还原出zoneCode 全路径
  let codes = [];
  for (let i = 0; i < dsy.length; i++) {
    var province = dsy[i];
    //特别行政区
    if(province.children == null){
      if(zoneCode == province.value){
        codes.push(province.label);
        return codes;
      }
      continue;
    }
    //
    for (let j = 0; j < province.children.length; j++) {
      var city = province.children[j];
      if (city.children != null) {
        for (let k = 0; k < city.children.length; k++) {
          var country = city.children[k];
          if (country.value == zoneCode) {
            codes.push(province.label);
            codes.push(city.label);
            codes.push(country.label);
            return codes;
          }
        }
      } else {
        if (city.value == zoneCode) {
          codes.push(province.label);
          codes.push(city.label);
          return codes;
        }
      }
    }
  }
  return codes;
}
