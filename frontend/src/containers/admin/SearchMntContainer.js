import { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import qs from 'qs';
import { changeSearchCond, searchMntList } from '../../modules/admin/searchMnt';
import { withRouter, useHistory } from 'react-router-dom';
import SearchMnt from '../../components/admin/SearchMnt';
import SearchBar from '../../components/admin/SearchBar';
const SearchMntContainer = ({ location }) => {
  const history = useHistory();
  const dispatch = useDispatch();
  const { searchTextList, error, loading, searchText } = useSelector(
    ({ searchMnt, loading }) => ({
      searchTextList: searchMnt.searchTextList,
      error: searchMnt.error,
      loading: loading['search_mnt/SEARCH_TEXT_LIST'],
      searchText: searchMnt.searchText,
    }),
  );

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

  useEffect(() => {
    const { q, sort } = qs.parse(location.search, {
      ignoreQueryPrefix: true,
    });

    dispatch(searchMntList({ searchText: q, asc: sort }));
  }, [dispatch, location.search]);
  return (
    <>
      <SearchMnt
        loading={loading}
        error={error}
        searchTextList={searchTextList}
        searchBar={
          <SearchBar
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
