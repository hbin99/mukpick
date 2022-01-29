import { useState } from 'react';
import { Table, Button } from 'react-bootstrap';
import { getFullDateFormat } from '../../../lib/utils';
import AskModal from '../../common/AskModal';
import ErrorMessage from '../../common/ErrorMessage';
import SelectDateTimeModal from '../../common/SelectDateTimeModal';
import '../../../lib/css/admin.css';
const SearchTextItem = ({
  item,
  deleteSearchText,
  changeValidDate,
  transferSearchText,
}) => {
  const [isShowConfirmDate, setIsShowConfirmDate] = useState(false);
  const [isShowDeleteModal, SetIsShowDeleteModal] = useState(false);
  const [isShowTransferModal, SetIsShowTransferModal] = useState(false);

  const { searchNo, searchText, validDate, registerDate, searchCnt } = item;

  const changeShowConfirm = () => {
    setIsShowConfirmDate(!isShowConfirmDate);
  };

  const changeShowDelete = () => {
    SetIsShowDeleteModal(!isShowDeleteModal);
  };

  const changeShowTransfer = () => {
    SetIsShowTransferModal(!isShowTransferModal);
  };

  const onConfirm = (changeDate) => {
    changeValidDate(searchNo, changeDate);
    changeShowConfirm();
  };

  const onDelete = () => {
    deleteSearchText(searchNo);
    changeShowDelete();
  };

  const onTransfer = () => {
    transferSearchText(searchNo);
    changeShowTransfer();
  };
  return (
    <tr>
      <td>{searchNo}</td>
      <td>{searchText}</td>
      <td>{searchCnt}</td>
      <td>
        {getFullDateFormat(validDate)}
        <Button onClick={changeShowConfirm}>변경하기</Button>
        <SelectDateTimeModal
          title={'유효기간 변경'}
          validDate={validDate}
          description={'변경할 날짜를 선택해주세요.'}
          changeShowConfirm={changeShowConfirm}
          onConfirm={onConfirm}
          visible={isShowConfirmDate}
        />
      </td>
      <td>{getFullDateFormat(registerDate)}</td>
      <td>
        <Button onClick={changeShowDelete}>삭제</Button>

        <Button onClick={changeShowTransfer}>음식관리로 이동</Button>
        <>
          <AskModal
            title={'검색어 삭제'}
            description={`정말 [${searchText}] 검색어를 삭제하시겠습니까?`}
            visible={isShowDeleteModal}
            onCancel={changeShowDelete}
            onConfirm={onDelete}
          />
          <AskModal
            title={'음식관리로 이동'}
            description={`정말 [${searchText}] 검색어를 음식관리 페이지로 이동하시겠습니까?`}
            visible={isShowTransferModal}
            onCancel={changeShowTransfer}
            onConfirm={onTransfer}
          />
        </>
      </td>
    </tr>
  );
};

const SearchTextList = ({
  list,
  deleteSearchText,
  changeValidDate,
  transferSearchText,
}) => {
  return (
    <div className={"grid_box"}>
    <Table bordered responsive="md" hover >
      <thead>
        <tr>
          <th>No.</th>
          <th>검색어</th>
          <th>검색횟수</th>
          <th>유효기간</th>
          <th>등록일</th>
          <th>{/* option button */}</th>
        </tr>
      </thead>
      <tbody>
        {list.map((item) => (
          <SearchTextItem
            item={item}
            key={item.searchNo}
            deleteSearchText={deleteSearchText}
            changeValidDate={changeValidDate}
            transferSearchText={transferSearchText}
          />
        ))}
      </tbody>
    </Table>
    </div>
  );
};

const SearchMnt = ({
  loading,
  searchTextList,
  searchBar,
  deleteSearchText,
  changeValidDate,
  transferSearchText,
  error,
}) => {
  if (error) {
    return <ErrorMessage error={error} />;
  }

  return (
    <>
      <div className={"page_title"}>
        <h1>검색어 관리</h1>
      </div>
      {searchBar}

        {!loading && searchTextList && !error && (
          <SearchTextList
            list={searchTextList}
            deleteSearchText={deleteSearchText}
            changeValidDate={changeValidDate}
            transferSearchText={transferSearchText}
          />
        )}

    </>
  );
};

export default SearchMnt;
