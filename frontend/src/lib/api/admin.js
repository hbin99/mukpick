import client from './client';
import qs from 'qs';
/* 검색관리 페이지 리스트 조회 */

export const getSearchMntList = ({ searchText, sort }) => {
  const queryString = qs.stringify({
    searchText,
    sort,
  });
  return client.get(`/api/admin/search?${queryString}`);
};
