import { combineReducers } from 'redux';
import { all } from 'redux-saga/effects';
import loading from './loading';
import findUser, { memberSaga } from './member/findUser';
import user,{loginSaga} from './member/login';
import searchMntModule, {
  searchMntSaga,
  deleteSearchTextSaga,
  changeValidateSaga,
  transferSearchTextSaga,
} from './admin/searchMntModule';
import foodMntModule, { foodInfoListSaga } from './admin/foodMntModule';

const rootReducer = combineReducers({
  loading,
  searchMnt: searchMntModule,
  foodMnt: foodMntModule,
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
