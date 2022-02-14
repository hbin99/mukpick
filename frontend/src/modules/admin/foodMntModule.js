import createRequestSaga, {
  createRequestActionTypes,
} from '../../lib/createRequestSaga';

import * as adminAPI from '../../lib/api/admin';
import { createAction, handleActions } from 'redux-actions';
import { takeLatest } from 'redux-saga/effects';

const CHANGE_SEARCH_COND = 'food_mnt/CHANGE_SEARCH_COND';

export const changeSearchCond = createAction(
  CHANGE_SEARCH_COND,
  ({ key, value }) => ({
    key,
    value,
  }),
);

const [FOOD_INFO_LIST, FOOD_INFO_LIST_SUCCESS, FOOD_INFO_LIST_FAILURE] =
  createRequestActionTypes('food_mnt/FOOD_INFO_LIST');

export const getFoodMntList = createAction(
  FOOD_INFO_LIST,
  ({ foodName, isShow, asc }) => ({ foodName, isShow, asc }),
);


const foodMntListSaga = createRequestSaga(
  FOOD_INFO_LIST,
  adminAPI.getFoodMntList,
);

export function* getFoodInfoListSaga() {
  yield takeLatest(FOOD_INFO_LIST, foodMntListSaga);
}

const [DELETE_FOOD_INFO, DELETE_FOOD_INFO_SUCCESS, DELETE_FOOD_INFO_FAILURE] =
  createRequestActionTypes('food_mnt/DELETE_FOOD_INFO');

export const deleteFood = createAction(DELETE_FOOD_INFO, ({ foodNo }) => ({ foodNo }));

const deleteFoodSaga = createRequestSaga(DELETE_FOOD_INFO, adminAPI.deleteFood);

export function* deleteFoodInfoSaga(){
  yield takeLatest(DELETE_FOOD_INFO, deleteFoodSaga);
}


const [UPDATE_FOOD_INFO, UPDATE_FOOD_INFO_SUCCESS, UPDATE_FOOD_INFO_FAILURE] =
  createRequestActionTypes('food_mnt/UPDATE_FOOD_INFO');

export const updateFood = createAction(UPDATE_FOOD_INFO, ({foodNo, foodData}) => ({foodNo, foodData}));

const updateFoodSaga = createRequestSaga(UPDATE_FOOD_INFO, adminAPI.modifyFoodInfo);

export function* updateFoodInfoSaga(){
  yield takeLatest(UPDATE_FOOD_INFO, updateFoodSaga);
}

const RESET_ERROR = 'food_mnt/RESET_ERROR';
export const resetError = createAction(RESET_ERROR);

const initialState = {
  foodMntList: null,
  error: null,
  searchCond: {
    foodName: '',
    isShow: null,
    asc: true,
    start: 0,
    end: 10
  },
};

const foodMntModule = handleActions(
  {
    [CHANGE_SEARCH_COND]: (state, { payload: { key, value } }) => ({
      ...state,
      searchCond: {
        ...state.searchCond,
        [key]: value,
      },
    }),
    [FOOD_INFO_LIST_SUCCESS]: (state, { payload: foodMntList }) => ({
      ...state,
      foodMntList,
    }),
    [FOOD_INFO_LIST_FAILURE]: (state, { payload: error }) => ({
      ...state,
      error,
    }),

    [DELETE_FOOD_INFO_SUCCESS]: (state, { payload: foodNo }) => ({
      ...state,
      foodMntList: state.foodMntList.filter(
        (item) => item.foodNo !== foodNo,
      ),
    }),
    [DELETE_FOOD_INFO_FAILURE]: (state, { payload: error }) => ({
      ...state,
      error,
    }),
    [UPDATE_FOOD_INFO_SUCCESS]: (state, {payload: foodInfo}) => ({
      ...state,
      foodMntList : state.foodMntList.map(item => {
        if (foodInfo.foodNo === item.foodNo){
          item.foodName = foodInfo.foodName;
          item.isShow = foodInfo.isShow;
        }
        return item;
      })
    }),
    [UPDATE_FOOD_INFO_FAILURE]: (state, {payload:error}) => ({
      ...state,
      error
    }),
    [RESET_ERROR]: (state) => ({
      ...state,
      error: null
    })
  },
  initialState,
);

export default foodMntModule;
