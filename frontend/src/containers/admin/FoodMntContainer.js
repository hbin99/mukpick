import { useEffect } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import FoodMnt from '../../components/admin/food_mnt/FoodMnt';
import SearchBar from '../../components/admin/search_mnt/SearchBar';
import { changeSearchCond, deleteFood, getFoodMntList, updateFood } from '../../modules/admin/foodMntModule';
import qs from 'qs';
import { useHistory, withRouter } from 'react-router';

const FoodMntContainer = ({ location }) => {
  const dispatch = useDispatch();
  const history = useHistory();

  const { foodMntList, foodName, loading, error } = useSelector(
    ({ loading, foodMnt }) => ({
      foodMntList: foodMnt.foodMntList,
      foodName: foodMnt.searchCond.foodName,
      error: foodMnt.error,
      loading: loading['search_mnt/SEARCH_TEXT_LIST'],
    }),
  );

  const onChange = (e) => {
    const { value, name } = e.target;

    dispatch(changeSearchCond({ key: name, value: value }));
  };

  const deleteItem = (foodNo) => {
    dispatch(deleteFood({foodNo}));
  };

  const updateItem = (foodNo, updateInfo, value) => {
    const foodData ={};
    switch (updateInfo) {
      case 'isShow':
        foodData['isShow'] = value;
        break;
      case 'foodName':
        console.log()
        foodData['foodName'] = value;
        break;

      default:
        break;
    }
    dispatch(updateFood({foodNo,foodData}));
  }

  const onKeyDown = (e) => {
    if (e.keyCode === 13) {
      if (foodName) {
        history.push('?q=' + foodName);
      } else {
        history.push('/admin/food-mnt');
      }
    }
  };

  useEffect(() => {
    const { q, isShow, sort } = qs.parse(location.search, {
      ignoreQueryPrefix: true,
    });
    dispatch(getFoodMntList({ foodName: q, isShow: isShow, asc: sort }));
  }, [dispatch, location.search]);
  return (
    <>
      <FoodMnt
        searchBar={
          <SearchBar
            name={'foodName'}
            searchText={foodName}
            onChange={onChange}
            onKeyDown={onKeyDown}
          />
        }
        foodMntList={foodMntList}
        loading={loading}
        error={error}
        updateFoodInfo={updateItem}
        deleteFoodInfo={deleteItem}
      />
    </>
  );
};

export default withRouter(FoodMntContainer);
