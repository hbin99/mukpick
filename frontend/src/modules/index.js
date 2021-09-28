import { combineReducers } from 'redux';
import { all } from 'redux-saga/effects';
import loading from './loading';
import searchMnt, { searchMntSaga } from './admin/searchMnt';

const rootReducer = combineReducers({
  loading,
  searchMnt,
});

export function* rootSaga() {
  yield all([searchMntSaga()]);
}

export default rootReducer;
