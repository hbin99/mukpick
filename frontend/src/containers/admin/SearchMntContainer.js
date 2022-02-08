import { memo, useCallback, useEffect } from 'react';
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


const SearchMntContainer = memo(({ location }) => {
  const history = useHistory();
  const dispatch = useDispatch();
  const { searchTextList, error, loading, searchText,asc } = useSelector(
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

  // const [bottom, setBottom] = useState(null);
  // const bottomObserver = useRef(null);

  /* 검색 */
  useEffect(() => {
    let { q } = qs.parse(location.search, {
      ignoreQueryPrefix: true,
    });
    // if(hasNext == null || hasNext){
      dispatch(getSearchMntList({ searchText: q, asc}));
    // }
  }, [ dispatch,asc, location.search ]);

  const onChange = useCallback((e) => {
    const { value, name } = e.target;
    dispatch(changeSearchCond({ key: name, value }));
  },[dispatch]);

  const onKeyDown = useCallback((e) => {
    if (e.keyCode === 13) {
      if (searchText) {
        history.push('?q=' + searchText);
      } else {
        history.push('/admin/search-mnt');
      }
    }
  },[searchText,history]);

  const deleteItem = useCallback((searchNo) => {
    dispatch(deleteSearchText({ searchNo }));
    if(!searchTextList.length){
      dispatch(getSearchMntList({searchText: '', asc}));
    }
  },[dispatch,searchTextList.length,asc]);

  const changeValidDate = (searchNo, changeDate) => {
    dispatch(changeSearchValidDate({ searchNo, changeDate }));
  };

  const transferSearchText = (searchNo) => {
    dispatch(transferToFoodMnt({ searchNo }));
  };

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
});

export default withRouter(SearchMntContainer);
