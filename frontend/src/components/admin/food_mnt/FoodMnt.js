import { memo, useCallback, useState } from 'react';
import { Table, Button } from 'react-bootstrap';
import AskModal from '../../common/AskModal';
import InputModal from '../../common/InputModal';

const FoodInfoItem = memo(({ item, deleteFoodInfo,updateFoodInfo }) => {
  const { foodNo, foodName, isShow, isDefault, registerDate } = item;
  const [isShowDeleteModal, setIsShowDeleteModal] = useState(false);
  const [isShowModal, setIsShowModal] = useState(false);
  const [isShowRenameModal, setIsShowRenameModal] = useState(false);
  const changeShowDelete = useCallback(() => {
    setIsShowDeleteModal(!isShowDeleteModal);
  },[setIsShowDeleteModal,isShowDeleteModal]);

  const changeIsShow = useCallback(() => {
    setIsShowModal(!isShowModal);
  },[setIsShowModal, isShowModal]);

  const onDelete = useCallback(() => {
    deleteFoodInfo(foodNo);
    changeShowDelete();
  },[deleteFoodInfo, changeShowDelete,foodNo]);

  const onShowOrHide = () => {
    updateFoodInfo(foodNo, 'isShow', isShow === 'Y' ? 'N': 'Y');
    setIsShowModal(!isShowModal);
  };

  const changeShowRename = useCallback(()=>{
    setIsShowRenameModal(!isShowRenameModal);
  },[setIsShowRenameModal,isShowRenameModal]);

  const onRename = useCallback((inputText) => {
    updateFoodInfo(foodNo, 'foodName', inputText);

  },[updateFoodInfo,foodNo,foodName]);

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
          confirmText={isShow === 'Y' ? '숨기기' : '보이기'}
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
        <Button onClick={changeShowRename}>이름 변경</Button>
        <InputModal
          title={'음식명 변경'}
          description={`변경할 음식명을 입력해주세요.`}
          visible={isShowRenameModal}
          onCancel={changeShowRename}
          onSave={onRename}
          inputText={foodName}
          confirmText={'변경하기'}
        />
      </td>
    </tr>
  );
});

const FoodInfoList = memo(({ list, deleteFoodInfo,updateFoodInfo }) => {
  return (
    <div className={"grid_box"}>
      <Table bordered responsive="md" hover className={"grid_box"}>
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
              key={index}
              deleteFoodInfo={deleteFoodInfo}
              updateFoodInfo={updateFoodInfo}
            />
          ))}
        </tbody>
      </Table>
    </div>
  );
});

const  FoodMnt = ({ searchBar, foodMntList, loading, deleteFoodInfo,updateFoodInfo }) => {
  return (
    <>
      <h1>음식명 관리</h1>
      {searchBar}
      {!loading && foodMntList && (
        <FoodInfoList list={foodMntList} deleteFoodInfo={deleteFoodInfo} updateFoodInfo={updateFoodInfo}/>
      )}
    </>
  );
};

export default memo(FoodMnt);
