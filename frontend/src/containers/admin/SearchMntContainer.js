import { useEffect, useRef, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import qs from 'qs';
import {
  changeSearchCond,
  changeSearchValidDate,
  deleteSearchText,
  getSearchMntList,
  transferToFoodMnt,
} from '../../modules/admin/searchMntModule';
import { withRouter, useHistory } from 'react-router-dom';
import SearchMnt from '../../components/admin/search_mnt/SearchMnt';
import SearchBar from '../../components/admin/search_mnt/SearchBar';


const SearchMntContainer = ({ location }) => {
  const history = useHistory();
  const dispatch = useDispatch();
  const { searchTextList, error, loading, searchText,asc,start,limit,hasNext } = useSelector(
    ({ searchMnt, loading }) => ({
      searchTextList: searchMnt.searchTextList,
      error: searchMnt.error,
      searchText: searchMnt.searchCond.searchText,
      asc: searchMnt.searchCond.asc,
      start: searchMnt.searchCond.start,
      limit: searchMnt.searchCond.limit,
      hasNext: searchMnt.hasNext,
      loading: loading['search_mnt/SEARCH_TEXT_LIST'],
    }),
  );

  const [bottom, setBottom] = useState(null);
  const bottomObserver = useRef(null);

  /* 검색 */
  useEffect(() => {
    let { q } = qs.parse(location.search, {
      ignoreQueryPrefix: true,
    });
    // if(hasNext == null || hasNext){
      dispatch(getSearchMntList({ searchText: q, asc}));
    // }
  }, [dispatch, location.search ]);

  const onChange = (e) => {
    const { value, name } = e.target;
    dispatch(changeSearchCond({ key: name, value }));
  };

  const onKeyDown = (e) => {
    if (e.keyCode === 13) {
      if (searchText) {
        history.push('?q=' + searchText);
      } else {
        history.push('/admin/search-mnt');
      }
    }
  };

  const deleteItem = (searchNo) => {
    dispatch(deleteSearchText({ searchNo }));
  };

  const changeValidDate = (searchNo, changeDate) => {
    dispatch(changeSearchValidDate({ searchNo, changeDate }));
  };

  const transferSearchText = (searchNo) => {
    dispatch(transferToFoodMnt({ searchNo }));
  };

  /* 새로고침 시 쿼리스트링 초기화 */
  useEffect(() => {
    history.push('/admin/search-mnt');
  }, [history]);


  return (

    <>
      <SearchMnt
        loading={loading}
        error={error}
        searchTextList={searchTextList}
        deleteSearchText={deleteItem}
        changeValidDate={changeValidDate}
        transferSearchText={transferSearchText}
        searchBar={
          <SearchBar
            name={'searchText'}
            searchText={searchText}
            onChange={onChange}
            onKeyDown={onKeyDown}
          />
        }
      />
    </>
  );
};

export default withRouter(SearchMntContainer);
