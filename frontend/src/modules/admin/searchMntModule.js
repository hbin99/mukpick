import { createAction, handleActions } from 'redux-actions';
import * as adminAPI from '../../lib/api/admin';
import createRequestSaga, {
  createRequestActionTypes,
} from '../../lib/createRequestSaga';
import { takeLatest } from 'redux-saga/effects';

const [SEARCH_TEXT_LIST, SEARCH_TEXT_LIST_SUCCESS, SEARCH_TEXT_LIST_FAILURE] =
  createRequestActionTypes('search_mnt/SEARCH_TEXT_LIST');

export const getSearchMntList = createAction(
  SEARCH_TEXT_LIST,
  ({ searchText, asc, start, limit }) => ({
    searchText,
    asc,
    start,
    limit
  }),
);

const searchMntListSaga = createRequestSaga(
  SEARCH_TEXT_LIST,
  adminAPI.getSearchMntList,
);

export function* searchMntSaga() {
  yield takeLatest(SEARCH_TEXT_LIST, searchMntListSaga);
}

const [
  DELETE_SEARCH_TEXT,
  DELETE_SEARCH_TEXT_SUCCESS,
  DELETE_SEARCH_TEXT_FAILURE,
] = createRequestActionTypes('search_mnt/DELETE_SEARCH_TEXT');

export const deleteSearchText = createAction(
  DELETE_SEARCH_TEXT,
  ({ searchNo }) => ({ searchNo }),
);

const deleteTextSaga = createRequestSaga(
  DELETE_SEARCH_TEXT,
  adminAPI.deleteSearchText,
);

export function* deleteSearchTextSaga() {
  yield takeLatest(DELETE_SEARCH_TEXT, deleteTextSaga);
}

const [
  CHANGE_VALID_DATE,
  CHANGE_VALID_DATE_SUCCESS,
  CHANGE_VALID_DATE_FAILURE,
] = createRequestActionTypes('search_mnt/CHANGE_VALID_DATE');

export const changeSearchValidDate = createAction(
  CHANGE_VALID_DATE,
  ({ searchNo, changeDate }) => ({ searchNo, changeDate }),
);

const changeSearchValidSaga = createRequestSaga(
  CHANGE_VALID_DATE,
  adminAPI.changeSearchValidDate,
);

export function* changeValidateSaga() {
  yield takeLatest(CHANGE_VALID_DATE, changeSearchValidSaga);
}

const [
  TRANSFER_TO_FOOD_MNT,
  TRANSFER_TO_FOOD_MNT_SUCCESS,
  TRANSFER_TO_FOOD_MNT_FAILURE,
] = createRequestActionTypes('search_mnt/TRANSFER_TO_FOOD_MNT');

export const transferToFoodMnt = createAction(
  TRANSFER_TO_FOOD_MNT,
  ({ searchNo }) => ({ searchNo }),
);

const transferToFoodMntSaga = createRequestSaga(
  TRANSFER_TO_FOOD_MNT,
  adminAPI.transferToFoodMnt,
);

export function* transferSearchTextSaga() {
  yield takeLatest(TRANSFER_TO_FOOD_MNT, transferToFoodMntSaga);
}

const CHANGE_SEARCH_COND = 'search_mnt/CHANGE_INPUT';

export const changeSearchCond = createAction(
  CHANGE_SEARCH_COND,
  ({ key, value }) => ({
    key,
    value,
  }),
);

const initialState = {
  searchTextList: [],
  error: null,
  searchNo: null,
  hasNext: null,
  searchCond: {
    searchText: '',
    isShow: null,
    asc: true,
    start: 0,
    limit: null
  },
};

const searchMntModule = handleActions(
  {
    [CHANGE_SEARCH_COND]: (state, { payload: { key, value } }) => ({
      ...state,
      searchCond: {
        ...state.searchCond,
        [key]: value,
      },
    }),
    [SEARCH_TEXT_LIST_SUCCESS]: (state, { payload: searchTextList }) => ({
      ...state,
      searchTextList,
    }),
    [SEARCH_TEXT_LIST_FAILURE]: (state, { payload: error }) => ({
      ...state,
      error,
    }),
    [DELETE_SEARCH_TEXT_SUCCESS]: (state, { payload: searchNo }) => ({
      ...state,
      searchTextList: state.searchTextList.filter(
        (item) => item.searchNo !== searchNo,
      ),
    }),
    [DELETE_SEARCH_TEXT_FAILURE]: (state, { payload: error }) => ({
      ...state,
      error,
    }),
    [CHANGE_VALID_DATE_SUCCESS]: (state, { payload: searchData }) => ({
      ...state,
      searchTextList: state.searchTextList.map((item) =>
        searchData.searchNo === item.searchNo ? searchData : item,
      ),
    }),
    [CHANGE_VALID_DATE_FAILURE]: (state, { payload: error }) => ({
      ...state,
      error,
    }),
    [TRANSFER_TO_FOOD_MNT_SUCCESS]: (state, { payload: searchNo }) => ({
      ...state,
      searchTextList: state.searchTextList.filter(
        (item) => item.searchNo !== searchNo,
      ),
    }),
    [TRANSFER_TO_FOOD_MNT_FAILURE]: (state, { payload: error }) => ({
      ...state,
      error,
    }),
  },
  initialState,
);

export default searchMntModule;
