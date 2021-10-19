import { combineReducers } from 'redux';
import { all } from 'redux-saga/effects';
import loading from './loading';
import findUser, { memberSaga } from './member/findUser';
import user,{loginSaga} from './member/login';
import searchMnt, {
  searchMntSaga,
  deleteSearchTextSaga,
  changeValidateSaga,
  transferSearchTextSaga,
} from './admin/searchMnt';

const rootReducer = combineReducers({
  loading,
  searchMnt,
  member: findUser,
  user
});

export function* rootSaga() {
  yield all([
    searchMntSaga(),
    deleteSearchTextSaga(),
    changeValidateSaga(),
    transferSearchTextSaga(),
    memberSaga(),
    loginSaga()
  ]);
}

export default rootReducer;
