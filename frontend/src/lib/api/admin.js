import client from './client';
import qs from 'qs';

/* 검색관리 - 검색어 리스트 조회 */
export const getSearchMntList = ({ searchText, sort }) => {
  const queryString = qs.stringify({
    searchText,
    sort,
  });
  return client.get(`/api/admin/search?${queryString}`);
};

/* 검색관리 - 검색어 삭제 */
export const deleteSearchText = ({ searchNo }) => {
  return client.delete(`/api/admin/search/${searchNo}`);
};

export const changeSearchValidDate = ({ searchNo, changeDate }) => {
  return client.patch(`/api/admin/search/${searchNo}`, { date: changeDate });
};

export const transferToFoodMnt = ({ searchNo }) => {
  return client.post(`/api/admin/search/${searchNo}`);
};
