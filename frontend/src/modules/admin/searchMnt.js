import { createAction, handleActions } from 'redux-actions';
import * as adminAPI from '../../lib/api/admin';
import createRequestSaga, {
  createRequestActionTypes,
} from '../../lib/createRequestSaga';
import { takeLatest } from 'redux-saga/effects';

const [SEARCH_TEXT_LIST, SEARCH_TEXT_LIST_SUCCESS, SEARCH_TEXT_LIST_FAILURE] =
  createRequestActionTypes('search_mnt/SEARCH_TEXT_LIST');
const CHANGE_SEARCH_COND = 'search_mnt/CHANGE_INPUT';

export const searchMntList = createAction(
  SEARCH_TEXT_LIST,
  ({ searchText, asc }) => ({
    searchText,
    asc,
  }),
);

export const changeSearchCond = createAction(
  CHANGE_SEARCH_COND,
  ({ key, value }) => ({
    key,
    value,
  }),
);

const searchMntListSaga = createRequestSaga(
  SEARCH_TEXT_LIST,
  adminAPI.getSearchMntList,
);
export function* searchMntSaga() {
  yield takeLatest(SEARCH_TEXT_LIST, searchMntListSaga);
}

const initialState = {
  searchTextList: null,
  error: null,

  searchNo: null,
  searchText: '',
  asc: null,
};

const searchMnt = handleActions(
  {
    [CHANGE_SEARCH_COND]: (state, { payload: { key, value } }) => ({
      ...state,
      [key]: value,
    }),
    [SEARCH_TEXT_LIST_SUCCESS]: (state, { payload: searchTextList }) => ({
      ...state,
      searchTextList,
    }),
    [SEARCH_TEXT_LIST_FAILURE]: (state, { payload: error }) => ({
      ...state,
      error,
    }),
  },
  initialState,
);

export default searchMnt;
