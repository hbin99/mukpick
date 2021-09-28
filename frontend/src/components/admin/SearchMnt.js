import { Table } from 'react-bootstrap';

const SearchTextItem = ({ item }) => {
  const { searchNo, searchText, validDate, registerDate, searchCnt } = item;
  return (
    <tr>
      <td>{searchNo}</td>
      <td>{searchText}</td>
      <td>{searchCnt}</td>
      <td>{validDate}</td>
      <td>{registerDate}</td>
    </tr>
  );
};

const SearchTextList = ({ list }) => {
  return (
    <Table bordered responsive="md" hover>
      <thead>
        <tr>
          <th>No.</th>
          <th>검색어</th>
          <th>검색횟수</th>
          <th>유효기간</th>
          <th>등록일</th>
        </tr>
      </thead>
      <tbody>
        {list.map((item) => (
          <SearchTextItem item={item} key={item.searchNo} />
        ))}
      </tbody>
    </Table>
  );
};

const SearchMnt = ({ loading, searchTextList, searchBar }) => {
  return (
    <>
      <h1>검색어 관리</h1>
      {searchBar}
      {!loading && searchTextList && <SearchTextList list={searchTextList} />}
    </>
  );
};

export default SearchMnt;
