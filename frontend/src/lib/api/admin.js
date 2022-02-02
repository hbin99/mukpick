import client from './client';
import qs from 'qs';

/* 검색관리 - 검색어 리스트 조회 */
export const getSearchMntList = ({ searchText, sort,asc, start, limit }) => {
  const queryString = qs.stringify({
    searchText,
    sort,
    asc,
    start,
    limit
  });
  return client.get(`/api/admin/search?${queryString}`);
};

/* 검색관리 - 검색어 삭제 */
export const deleteSearchText = ({ searchNo }) => {
  return client.delete(`/api/admin/search/${searchNo}`);
};

/* 검색 관리 - 유효기간 수정 */
export const changeSearchValidDate = ({ searchNo, changeDate }) => {
  return client.patch(`/api/admin/search/${searchNo}`, { date: changeDate });
};

/* 검색 관리 - 음식 관리로 이동 */
export const transferToFoodMnt = ({ searchNo }) => {
  return client.post(`/api/admin/search/${searchNo}`);
};

/* 음식 관리 - 음식 리스트 조회 */
export const getFoodMntList = ({ foodName, isShow, sort }) => {
  const queryString = qs.stringify({
    foodName,
    isShow,
    sort,
  });
  return client.get(`/api/admin/food?${queryString}`);
};

/* 음식 관리 - 음식 삭제 */
export const deleteFood = ({ foodNo }) => {
  return client.delete(`/api/admin/food/${foodNo}`);
};
