import { useState } from 'react';
import { Table, Button } from 'react-bootstrap';
import AskModal from '../../common/AskModal';

const FoodInfoItem = ({ item, deleteFoodInfo }) => {
  const { foodNo, foodName, isShow, isDefault, registerDate } = item;
  const [isShowDeleteModal, setIsShowDeleteModal] = useState(false);
  const [isShowModal, setIsShowModal] = useState(false);

  const changeShowDelete = () => {
    setIsShowDeleteModal(!isShowDeleteModal);
  };

  const changeIsShow = () => {
    setIsShowModal(!isShowModal);
  };

  const onDelete = () => {
    deleteFoodInfo(foodNo);
  };

  const onShowOrHide = (e) => {};

  return (
    <tr>
      <td>{foodNo}</td>
      <td>{foodName}</td>
      <td>
        {isShow}
        <Button onClick={changeIsShow}>
          {isShow === 'Y' ? '숨기기' : '보이기'}
        </Button>
        <AskModal
          title={`음식명 ${isShow === 'Y' ? '숨기기' : '보이기'}`}
          description={`정말 [${foodName}]을 ${
            isShow === 'Y' ? '숨기' : '보이'
          }시겠습니까?`}
          visible={isShowModal}
          onCancel={changeIsShow}
          onConfirm={onShowOrHide}
          confirmText={'숨기기'}
        />
      </td>
      <td>{isDefault}</td>
      <td>{registerDate}</td>
      <td>
        <Button onClick={changeShowDelete}>삭제</Button>
        <AskModal
          title={'음식명 삭제'}
          description={`정말 [${foodName}]을 삭제하시겠습니까?`}
          visible={isShowDeleteModal}
          onCancel={changeShowDelete}
          onConfirm={onDelete}
          confirmText={'삭제'}
        />
      </td>
    </tr>
  );
};

const FoodInfoList = ({ list, deleteFoodInfo }) => {
  return (
    <Table bordered responsive="md" hover>
      <thead>
        <tr>
          <th>NO.</th>
          <th>음식명</th>
          <th>보이기/숨기기</th>
          <th>기본 값</th>
          <th>등록일</th>
          <th>{/* option button */}</th>
        </tr>
      </thead>
      <tbody>
        {list.map((item, index) => (
          <FoodInfoItem
            item={item}
            key={item.foodNo}
            deleteFoodInfo={deleteFoodInfo}
          />
        ))}
      </tbody>
    </Table>
  );
};

const  FoodMnt = ({ searchBar, foodMntList, loading, deleteFoodInfo }) => {
  return (
    <>
      <h1>음식명 관리</h1>
      {searchBar}
      {!loading && foodMntList && (
        <FoodInfoList list={foodMntList} deleteFoodInfo={deleteFoodInfo} />
      )}
    </>
  );
};

export default FoodMnt;
