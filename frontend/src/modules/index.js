import { combineReducers } from 'redux';
import { all } from 'redux-saga/effects';
import loading from './loading';
import findId, { memberSaga } from './member/findId';
import searchMnt, {
  searchMntSaga,
  deleteSearchTextSaga,
  changeValidateSaga,
  transferSearchTextSaga,
} from './admin/searchMnt';

const rootReducer = combineReducers({
  loading,
  searchMnt,
  member: findId
});

export function* rootSaga() {
  yield all([
    searchMntSaga(),
    deleteSearchTextSaga(),
    changeValidateSaga(),
    transferSearchTextSaga(),
    memberSaga()
  ]);
}

export default rootReducer;
