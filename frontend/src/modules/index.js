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
import foodMnt, { foodInfoListSaga } from './admin/foodMnt';

const rootReducer = combineReducers({
  loading,
  searchMnt,
  foodMnt,
  member: findUser,
  user
});

export function* rootSaga() {
  yield all([
    searchMntSaga(),
    deleteSearchTextSaga(),
    changeValidateSaga(),
    transferSearchTextSaga(),
    foodInfoListSaga(),
    memberSaga(),
    loginSaga()

  ]);
}

export default rootReducer;
