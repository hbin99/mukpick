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

export function* foodInfoListSaga() {
  yield takeLatest(FOOD_INFO_LIST, foodMntListSaga);
}

const [DELETE_FOOD_INFO, DELETE_FOOD_INFO_SUCCESS, DELETE_FOOD_INFO_FAILURE] =
  createRequestActionTypes('food_mnt/DELETE_FOOD_INFO');

const deleteFood = createAction(DELETE_FOOD_INFO, ({ foodNo }) => ({ foodNo }));

const deleteFoodSaga = createRequestSaga(DELETE_FOOD_INFO, adminAPI.deleteFood);

const initialState = {
  foodMntList: null,
  error: null,
  searchCond: {
    foodName: '',
    isShow: null,
    asc: null,
    start: 0,
    end: 10
  },
};

const foodMnt = handleActions(
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
  },
  initialState,
);

export default foodMnt;
